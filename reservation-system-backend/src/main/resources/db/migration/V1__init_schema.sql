CREATE EXTENSION IF NOT EXISTS btree_gist;

CREATE TYPE user_role AS ENUM ('USER', 'MANAGER', 'ADMIN');
CREATE TYPE room_status AS ENUM ('active', 'maintenance', 'out_of_order');
CREATE TYPE reservation_status AS ENUM ('pending', 'confirmed', 'cancelled');

CREATE TABLE users (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    public_id UUID DEFAULT gen_random_uuid() UNIQUE NOT NULL,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255),
    google_id VARCHAR(255) UNIQUE,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    role user_role DEFAULT 'USER' NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW(),
    deleted_at TIMESTAMPTZ
);

CREATE UNIQUE INDEX idx_users_email ON users(email) WHERE deleted_at IS NULL;
CREATE INDEX idx_users_public_id ON users(public_id);

CREATE TABLE buildings (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    public_id UUID DEFAULT gen_random_uuid() UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    description TEXT,
    timezone VARCHAR(50) DEFAULT 'Europe/Warsaw' NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW(),
    deleted_at TIMESTAMPTZ
);

CREATE INDEX idx_buildings_public_id ON buildings(public_id);

CREATE TABLE building_schedules (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    public_id UUID DEFAULT gen_random_uuid() UNIQUE NOT NULL,
    building_id INT REFERENCES buildings(id) ON DELETE CASCADE,
    day_of_week INT NOT NULL CHECK (day_of_week BETWEEN 0 AND 6),
    open_time TIME NOT NULL,
    close_time TIME NOT NULL
);

ALTER TABLE building_schedules
ADD CONSTRAINT no_overlapping_schedules
EXCLUDE USING gist (
    building_id WITH =,
    day_of_week WITH =,
    timerange(open_time, close_time, '[)') WITH &&
);

CREATE TABLE rooms (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    public_id UUID DEFAULT gen_random_uuid() UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    building_id INT NOT NULL REFERENCES buildings(id) ON DELETE RESTRICT,
    capacity INT NOT NULL CHECK (capacity > 0),
    status room_status DEFAULT 'active',
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW(),
    deleted_at TIMESTAMPTZ,
    description TEXT
);

CREATE INDEX idx_rooms_public_id ON rooms(public_id);
CREATE UNIQUE INDEX idx_rooms_unique_name_per_building
    ON rooms(building_id, name)
    WHERE deleted_at IS NULL;

CREATE TABLE amenities (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    public_id UUID DEFAULT gen_random_uuid() UNIQUE NOT NULL,
    name VARCHAR(100) UNIQUE NOT NULL,
    icon VARCHAR(255),
    deleted_at TIMESTAMPTZ
);

CREATE UNIQUE INDEX idx_amenities_name ON amenities(name) WHERE deleted_at IS NULL;
CREATE INDEX idx_amenities_public_id ON amenities(public_id);

CREATE TABLE room_amenities (
    room_id INT REFERENCES rooms(id) ON DELETE CASCADE,
    amenity_id INT REFERENCES amenities(id) ON DELETE CASCADE,
    quantity INT DEFAULT 1 CHECK (quantity > 0),
    PRIMARY KEY (room_id, amenity_id)
);

CREATE TABLE reservations (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    public_id UUID DEFAULT gen_random_uuid() UNIQUE NOT NULL,
    room_id INT REFERENCES rooms(id) ON DELETE RESTRICT,
    user_id INT REFERENCES users(id) ON DELETE RESTRICT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    attendees_count INT NOT NULL CHECK (attendees_count > 0),
    start_time TIMESTAMPTZ NOT NULL,
    end_time TIMESTAMPTZ NOT NULL,
    status reservation_status NOT NULL DEFAULT 'pending',
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW(),
    CONSTRAINT chk_reservation_time CHECK (start_time < end_time)
);

CREATE INDEX idx_reservations_public_id ON reservations(public_id);

CREATE TABLE manager_buildings (
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    building_id INT REFERENCES buildings(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, building_id)
);

CREATE INDEX idx_manager_buildings_building_id ON manager_buildings(building_id);

CREATE OR REPLACE FUNCTION check_manager_role()
RETURNS TRIGGER AS $$
DECLARE
user_role_val user_role;
BEGIN
    -- Pobranie roli przypisywanego użytkownika
SELECT role INTO user_role_val
FROM users
WHERE id = NEW.user_id;

-- Weryfikacja, czy użytkownik ma odpowiednią rolę
IF user_role_val != 'MANAGER' THEN
        RAISE EXCEPTION 'Cannot assign building. User has role % but MANAGER is required.', user_role_val;
END IF;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_check_manager_role
    BEFORE INSERT OR UPDATE ON manager_buildings
                         FOR EACH ROW EXECUTE FUNCTION check_manager_role();

ALTER TABLE reservations
ADD CONSTRAINT no_overlapping_reservations
EXCLUDE USING gist (
    room_id WITH =,
    tstzrange(start_time, end_time, '[)') WITH &&
) WHERE (status IN ('confirmed', 'pending'));

CREATE OR REPLACE FUNCTION prevent_deleting_active_rooms()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.deleted_at IS NOT NULL AND OLD.deleted_at IS NULL THEN
        IF EXISTS (
            SELECT 1 FROM reservations
            WHERE room_id = NEW.id
              AND status IN ('confirmed', 'pending')
              AND start_time > NOW()
        ) THEN
            RAISE EXCEPTION 'Cannot delete room with active future reservations. Cancel them first.';
        END IF;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_prevent_deleting_rooms
BEFORE UPDATE ON rooms
FOR EACH ROW EXECUTE FUNCTION prevent_deleting_active_rooms();

CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_set_updated_at_users
    BEFORE UPDATE ON users FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER trg_set_updated_at_buildings
    BEFORE UPDATE ON buildings FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER trg_set_updated_at_rooms
    BEFORE UPDATE ON rooms FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER trg_set_updated_at_reservations
    BEFORE UPDATE ON reservations FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE INDEX idx_rooms_building_id ON rooms(building_id) WHERE deleted_at IS NULL;
CREATE INDEX idx_reservations_room_id ON reservations(room_id);
CREATE INDEX idx_reservations_user_id ON reservations(user_id);
CREATE INDEX idx_reservations_start_time ON reservations(start_time);