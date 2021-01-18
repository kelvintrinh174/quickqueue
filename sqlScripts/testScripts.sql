set schema 'quickqueue';
select * FROM items;
select * FROM cart_item;
select * FROM users;
select * FROM carts;
select * FROM orders;

insert into users  (username,"password",first_name,last_name,email,user_role)
values ('kelvintrinh174','12345','Kelvin','Trinh','kelvintrinh174@gmail.com','CUSTOMER'),
('colinshaw2',	'c',	'Colin',	'Shaw',	'cs@gmail.com',	'SHOPPER');







----------------------------------------------------------------------
--testing for adding items

insert into items values(1),(6);

insert into carts (cart_customer_id, cart_shopper_id, cart_status)
values (1, null, 'ACTIVE');

select * FROM carts where (carts.cart_id = 1 and carts.cart_status = 'ACTIVE')

delete from carts where (carts.cart_id > 3 and carts.cart_id < 39)