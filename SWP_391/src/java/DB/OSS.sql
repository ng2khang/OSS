CREATE DATABASE OSS
USE OSS

-- TẠO BẢNG

create table users(
	id int IDENTITY(1,1) PRIMARY KEY,
	name nvarchar(50) not null,
	email nvarchar(50) UNIQUE not null,
	password nvarchar(255) not null,
	phone nvarchar(20) not null,
	is_deleted TINYINT not null, 
	role TINYINT not null, -- 1. admin,2: sale, 3: shipper, 4:customer
	status TINYINT not null,
	created_at datetime
)



create table categories(
	id int IDENTITY(1,1) PRIMARY KEY,
	name nvarchar(50) not null,
	description nvarchar(500),
	created_at datetime,
	deleted_at datetime,
)

create table forms(
	id int IDENTITY(1,1) PRIMARY KEY,
	name nvarchar(50) not null,
	description nvarchar(500),
	created_at datetime,
	deleted_at datetime,
)

create table materials(
	id int IDENTITY(1,1) PRIMARY KEY,
	name nvarchar(50) not null,
	description nvarchar(500),
	created_at datetime,
	deleted_at datetime,
)


create table colors(
	id int IDENTITY(1,1) PRIMARY KEY,
	name nvarchar(50) not null,
	color_code nvarchar(500),
	created_at datetime,
	deleted_at datetime,
)

create table sizes(
	id int IDENTITY(1,1) PRIMARY KEY,
	name nvarchar(50) not null,
	description nvarchar(500),
	created_at datetime,
	deleted_at datetime,
)

create table groups(
	id int IDENTITY(1,1) PRIMARY KEY,
	name nvarchar(50) not null,
	description nvarchar(500),
	created_at datetime,
	deleted_at datetime,
)

CREATE TABLE administrative_regions (
	id integer NOT NULL,
	name nvarchar(255) NOT NULL,
	name_en nvarchar(255) NOT NULL,
	code_name nvarchar(255) NULL,
	code_name_en nvarchar(255) NULL,
	CONSTRAINT administrative_regions_pkey PRIMARY KEY (id)
);


-- CREATE administrative_units TABLE
CREATE TABLE administrative_units (
	id integer NOT NULL,
	full_name nvarchar(255) NULL,
	full_name_en nvarchar(255) NULL,
	short_name nvarchar(255) NULL,
	short_name_en nvarchar(255) NULL,
	code_name nvarchar(255) NULL,
	code_name_en nvarchar(255) NULL,
	CONSTRAINT administrative_units_pkey PRIMARY KEY (id)
);


-- CREATE provinces TABLE
CREATE TABLE provinces (
	code nvarchar(20) NOT NULL,
	name nvarchar(255) NOT NULL,
	name_en nvarchar(255) NULL,
	full_name nvarchar(255) NOT NULL,
	full_name_en nvarchar(255) NULL,
	code_name nvarchar(255) NULL,
	administrative_unit_id integer NULL,
	administrative_region_id integer NULL,
	CONSTRAINT provinces_pkey PRIMARY KEY (code)
);


-- provinces foreign keys

ALTER TABLE provinces ADD CONSTRAINT provinces_administrative_region_id_fkey FOREIGN KEY (administrative_region_id) REFERENCES administrative_regions(id);
ALTER TABLE provinces ADD CONSTRAINT provinces_administrative_unit_id_fkey FOREIGN KEY (administrative_unit_id) REFERENCES administrative_units(id);

CREATE INDEX idx_provinces_region ON provinces(administrative_region_id);
CREATE INDEX idx_provinces_unit ON provinces(administrative_unit_id);


-- CREATE districts TABLE
CREATE TABLE districts (
	code nvarchar(20) NOT NULL,
	name nvarchar(255) NOT NULL,
	name_en nvarchar(255) NULL,
	full_name nvarchar(255) NULL,
	full_name_en nvarchar(255) NULL,
	code_name nvarchar(255) NULL,
	province_code nvarchar(20) NULL,
	administrative_unit_id integer NULL,
	CONSTRAINT districts_pkey PRIMARY KEY (code)
);


-- districts foreign keys

ALTER TABLE districts ADD CONSTRAINT districts_administrative_unit_id_fkey FOREIGN KEY (administrative_unit_id) REFERENCES administrative_units(id);
ALTER TABLE districts ADD CONSTRAINT districts_province_code_fkey FOREIGN KEY (province_code) REFERENCES provinces(code);

CREATE INDEX idx_districts_province ON districts(province_code);
CREATE INDEX idx_districts_unit ON districts(administrative_unit_id);



-- CREATE wards TABLE
CREATE TABLE wards (
	code nvarchar(20) NOT NULL,
	name nvarchar(255) NOT NULL,
	name_en nvarchar(255) NULL,
	full_name nvarchar(255) NULL,
	full_name_en nvarchar(255) NULL,
	code_name nvarchar(255) NULL,
	district_code nvarchar(20) NULL,
	administrative_unit_id integer NULL,
	CONSTRAINT wards_pkey PRIMARY KEY (code)
);


-- wards foreign keys

ALTER TABLE wards ADD CONSTRAINT wards_administrative_unit_id_fkey FOREIGN KEY (administrative_unit_id) REFERENCES administrative_units(id);
ALTER TABLE wards ADD CONSTRAINT wards_district_code_fkey FOREIGN KEY (district_code) REFERENCES districts(code);

CREATE INDEX idx_wards_district ON wards(district_code);
CREATE INDEX idx_wards_unit ON wards(administrative_unit_id);

create table user_addresses(
	id int IDENTITY(1,1) PRIMARY KEY,
	user_id int,
	province_code nvarchar(20) NOT NULL,
	district_code nvarchar(20) NOT NULL,
	ward_code nvarchar(20) NOT NULL,
	address nvarchar(100),
	created_at datetime,
	deleted_at datetime
)
ALTER TABLE user_addresses ADD CONSTRAINT user_address_province_code_fkey FOREIGN KEY (province_code) REFERENCES provinces(code);
ALTER TABLE user_addresses ADD CONSTRAINT user_address_district_code_fkey FOREIGN KEY (district_code) REFERENCES districts(code);
ALTER TABLE user_addresses ADD CONSTRAINT user_address_ward_code_fkey FOREIGN KEY (ward_code) REFERENCES wards(code);
ALTER TABLE user_addresses ADD CONSTRAINT user_fkey FOREIGN KEY (user_id) REFERENCES users(id);