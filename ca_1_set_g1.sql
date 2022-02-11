create table Course(
Course_id int,
Course_Name varchar(50),
Student_Id varchar(50),
Student_Name varchar(50),
DOB date,
Email varchar(70),
Address varchar(100),
Phone_Number int);

insert into Course values(101,'BCA','RD2101A1','ANKIT MEENA','01-jan-2002','ankitmeena9783226195@gmail.com','Jaipur',9549112724);
insert into Course values(102,'MCA','RD2101A2','SAHIL TALWAR','09-oct-2002','sahiltalwar95@gmail.com','Jalandhar',9145142724);
insert into Course values(103,'BBA','RD2101A3','ANKIT KUMAR','06-july-2001','ankitkumar195@gmail.com','darjling',9249112734);
insert into Course values(104,'MCA','RD2101A4','ESHA MISHRA','23-jan-2002','eshamishra6195@gmail.com','soul',9349112756);
insert into Course values(105,'MBA','RD2101A5','ANANYA TRIPATHI','13-march-2003','ananyatripathi5@gmail.com','varanasi',9449112778);
insert into Course values(106,'BBA','RD2101A6','RAHUL DEY','30-sep-2002','rahuldey6195@gmail.com','dausa',9549112790);
insert into Course values(107,'BCA','RD2101A7','AAZAN','25-dec-2002','aazan195@gmail.com','jodhpur',9949112778);
insert into Course values(108,'MCA','RD2101A8','TRISHA','20-nov-2003','trisha26195@gmail.com','jasilmer',9849112765);
insert into Course values(109,'BA','RD2101A9','AJITESH','10-may-2002','ajitesh95@gmail.com','mout abu',9649112712);
insert into Course values(110,'CA','RD2101A10','SOHIL KHAN','15-feb-2001','sohilkhan95@gmail.com','jodhpur',9749112756);

select * from Course;

select id,name,Course from Course;
select Student_name,Student_id,DOB from Course;
select Student_name,Email,Address,Phone_Number from Course;

select *from Course where name='BCA';

drop table Course;