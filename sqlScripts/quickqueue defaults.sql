drop schema if exists quickqueue cascade;
create schema quickqueue;
set schema 'quickqueue';

create type "user-role" as enum('SHOPPER','CUSTOMER');
create type "cart-status" as enum('ACTIVE','SUBMITTED','COMPLETE');
create type "order-status" as enum('PENDING','ACTIVE','CLOSED');


create table users( 
	user_id serial primary key,
	username text unique not null,
	"password" text not null,
	first_name text not null,
	last_name text not null,
	email text unique not null,
	user_role "user-role" not null
);


create table carts(
	cart_id serial primary key,
	cart_customer_id integer references users(user_id) not null,
	cart_shopper_id integer references users(user_id),
	cart_status "cart-status" not null
	--get all carts for the user
	--make sure only one is active
);

--write check makes sure everyy user has only 1 active cart

create table items(
	item_id integer primary key
);

create table cart_item(
cart_id integer references carts(cart_id),
item_id integer references items(item_id),
quantity integer check( quantity > 0) not null,
primary key (cart_id, item_id)
);

create table orders(
	order_id serial primary key,
	order_net_amount numeric(12,2) not null,
	order_gross_amount numeric(12,2) not null,
	order_tax_amount numeric(12,2) not null,
	order_submitted timestamp not null,
	order_resolved timestamp,
	order_additional_instructions text,
	order_customer integer references users(user_id) not null,
	order_shopper integer references users(user_id),
	order_status "order-status" not null,
	cart_id integer references carts(cart_id) not null
);


--ddl stuff
--


--I dont know why this isn't working
--GRANT CREATE ON SCHEMA quickqueue TO developers;
--GRANT USAGE ON SCHEMA quickqueue TO developers;
--
--GRANT ALL PRIVILEGES ON DATABASE quickqueue TO developers;
--
--GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA quickqueue TO developers;
--
--grant developers to kelvin, aleks, ravi;


--this DDL works
--GRANT CREATE ON SCHEMA quickqueue TO kelvin;
--GRANT USAGE ON SCHEMA quickqueue TO kelvin;
--
--GRANT CREATE ON SCHEMA quickqueue TO aleks;
--GRANT USAGE ON SCHEMA quickqueue TO aleks;
--
--GRANT CREATE ON SCHEMA quickqueue TO ravi;
--GRANT USAGE ON SCHEMA quickqueue TO ravi;
--
--GRANT ALL PRIVILEGES ON DATABASE quickqueue TO kelvin;
--GRANT ALL PRIVILEGES ON DATABASE quickqueue TO aleks;
--GRANT ALL PRIVILEGES ON DATABASE quickqueue TO ravi;
--


--https://tableplus.com/blog/2018/04/postgresql-how-to-grant-access-to-users.html
--all PRIVILEGES means SELECT | INSERT | UPDATE | DELETE | TRUNCATE | REFERENCES | TRIGGER 
--GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA quickqueue TO kelvin;
--GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA quickqueue TO aleks;
--GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA quickqueue TO ravi;

--GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA quickqueue TO kelvin;
--GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA quickqueue TO aleks;
--GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA quickqueue TO ravi
--;
--GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA quickqueue TO kelvin;
--GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA quickqueue TO aleks;
--GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA quickqueue TO aleks;



