insert into customer(first_name, last_name, birth_date) values('John', 'Z', now());
insert into customer(first_name, last_name, birth_date) values('Vladimir', 'T', now());
insert into customer(first_name, last_name, birth_date) values('George', 'R', now());

insert into exchange_value(currency_from, currency_to, customer_id, traded, gained, trade_date) values('USD', 'MDL', 1, 60000, 3391.31, '2024-01-10');
insert into exchange_value(currency_from, currency_to, customer_id, traded, gained, trade_date) values('EUR', 'USD', 2, 1500, 1637.7, '2024-01-10');
insert into exchange_value(currency_from, currency_to, customer_id, traded, gained, trade_date) values('MDL', 'EUR', 3, 10000, 193164.00, '2024-01-10');