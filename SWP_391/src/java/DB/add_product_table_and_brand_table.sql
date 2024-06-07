create table products(
	id int IDENTITY(1,1) PRIMARY KEY,
	code nvarchar(50),
	name nvarchar(50) not null,
	description nvarchar(500),
	price int,
	category_id int,
	form_id int,
	brand_id int,
	material_id int,
	group_id int,
	created_at datetime,
	edited_at datetime,
	deleted_at datetime
)

create table product_details(
	id int IDENTITY(1,1) PRIMARY KEY,
	product_id int,
	color_id int,
	size_id int,
	inventory_number int,
	image_url_1 varchar,
	image_url_2 varchar,
	image_url_3 varchar,
	image_url_4 varchar,
	created_at datetime,
	edited_at datetime,
	deleted_at datetime
)
create table brands(
	id int IDENTITY(1,1) PRIMARY KEY,
	name nvarchar(50) not null,
	description nvarchar(500),
	created_at datetime,
	updated_at datetime,
	deleted_at datetime,
)

ALTER TABLE products ADD CONSTRAINT product_category_id_fkey FOREIGN KEY (category_id) REFERENCES categories(id);
ALTER TABLE products ADD CONSTRAINT product_form_id_fkey FOREIGN KEY (form_id) REFERENCES forms(id);
ALTER TABLE products ADD CONSTRAINT product_brand_id_fkey FOREIGN KEY (brand_id) REFERENCES brands(id);
ALTER TABLE products ADD CONSTRAINT product_material_id_fkey FOREIGN KEY (material_id) REFERENCES materials(id);
ALTER TABLE products ADD CONSTRAINT product_group_id_fkey FOREIGN KEY (group_id) REFERENCES groups(id);

ALTER TABLE product_details ADD CONSTRAINT product_detail_color_id_fkey FOREIGN KEY (color_id) REFERENCES colors(id);
ALTER TABLE product_details ADD CONSTRAINT product_detail_size_id_fkey FOREIGN KEY (size_id) REFERENCES sizes(id);
ALTER TABLE product_details ADD CONSTRAINT product_detail_product_id_fkey FOREIGN KEY (product_id) REFERENCES products(id);
