select * from customers;
select * from customer_contacts;
create table customer_contacts (customer_nit varchar(250) not null, contact_id bigint(20) not null);
alter table customer_contacts add constraint fk_customer_nit foreign key (customer_nit) references customers(nit);
alter table customer_contacts add constraint fk_contact_id foreign key (contact_id) references contacts(id);

insert into contacts (id,contact_name, email, birth_date) values (1010226725,"Jefferson Castañeda", "jefferdcc@gmail.com","1996-03-05");
alter table contacts add column customer_nit varchar(250);
alter table contacts add constraint fk_customer_nit foreign key (customer_nit) references customers(nit);

update contacts set customer_nit = "830.071.876-1" where id = 1010226725;

insert into customer_contacts(customer_nit, contact_id) values ("830.071.876-1",1010226725);
drop table customer_contacts;
create table contacts (id bigint(20) primary key, contact_name varchar (255) not null, email varchar (250), birth_date Date);
alter table contacts change column nit customer_nit varchar(250);
select * from contacts;
insert into contacts (id, contact_name, email, birth_date, customer_nit) values (1010226725,"Jefferson Castañeda", "jefferdcc@gmail.com", "1996-03-05", "830.071.876-1");
create table offices (address varchar(225) primary key, city_location varchar (100));

create table customer_offices (office_address varchar(225) not null, customer_nit varchar(250) not null);
drop table customer_offices;
alter table customer_offices add constraint fk_customer_nit_offices foreign key (customer_nit) references customers(nit);
alter table customer_offices add constraint fk_office_address foreign key (office_address) references offices(address);
drop table customer_offices;

create table tels (tel bigint(20) primary key, indicative varchar(50), contact_owner bigint(20), constraint fk_contact_owner foreign key (contact_owner) references contacts(id));