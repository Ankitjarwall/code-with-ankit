create table Employee (
    E_id int,
    E_name varchar(30),
    salary int,
    age int,
    city varchar(50),
    D_name varchar(50)
);
insert into Employee
values(1, 'Ankit', 1900, 20, 'Rajasthan', 'Bca');
insert into Employee
values(2, 'Arsh', 7100, 30, 'Delhi', 'Mca');
insert into Employee
values(3, 'Ananya', 6000, 40, 'Uttar pradesh', 'Bca');
insert into Employee
values(4, 'Sahil', 4000, 25, 'Punjab', 'Mca');
insert into Employee
values(5, 'Sheetal', 3000, 35, 'Haryana', 'Bio');

update Employee
set age = 20
where E_id = 2;
select *
from Employee;
alter table Employee
add School varchar(40);
delete Employee
where E_id = 1;
alter table Employee drop column school;
select *
from Employee
order by E_name;
select *
from Employee
order by E_name desc;