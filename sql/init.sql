create database myself character set 'utf8';
create table affair(
id int unsigned auto_increment primary key ,
what varchar(20),
why varchar(100),
how varchar(100),
comment varchar(200),
status varchar(10),
create_time DateTime,
start_time DateTime,
done_time DateTime,
duration varchar(20)
);
