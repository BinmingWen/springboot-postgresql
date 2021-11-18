## postgresql入门教程

#### 1.创建数据库

	1.创建数据库

* 图形界面操作

* 命令操作

  > create database wen_db;

##### 2.创建表

* 图形界面操作

* 命令操作

  ~~~sql
  CREATE TABLE public.student2
  (
  	id integer not null,
  	name character(100),
  	subjects character(10),
  	constraint student2_key primary key(id)
  )with(	OIDS=FALSE);
  Alter table public.student2 owner to postgres;
  comment on table public.student2 is '这是一个学生信息表';
  ~~~

  

##### 3.删除表格

* 图形界面操作

* 命令操作

  > drop table table_name(表名)

##### 4.对架构模式的理解

**postgre默认是使用public架构的，可以使用图形端创建架构，也可以使用*create schema schema_name(创建架构)***

#### 2.查询操作

* 图形界面操作

* 采用sql语句实现增删改查（类似mysql)

  ~~~sql
  insert into public."Student"(id,name,subjects) values(3,'王五','数学');
  
  select * from public."Student";
  
  update public."Student" set subjects = '科学' where id = 3;
  
  delete from public."Student" where id = 2;
  ~~~

  

#### 3.连接

**创建员工表**

~~~
create table public.employees
(
	id serial primary key,
	name text,
	age integer,
	address character(100)
)with(OIDS=false);
alter table public.employees owner to postgres;
comment on table public.employees is '员工表'
~~~

**部门表**

~~~sql
create table public.employees
(
	id serial primary key,
	name text,
	age integer,
	address character(100)
)with(OIDS=false);
alter table public.employees owner to postgres;
comment on table public.employees is '员工表'
~~~

**插入数据**

~~~sql
-- 插入数据
INSERT INTO department VALUES(1,'IT', 1);
INSERT INTO department VALUES(2,'Engineering', 2);
INSERT INTO department VALUES(3,'HR', 7);

insert into employees values(1,'Tom',22,'广州天河');
insert into employees values(2,'James',23,'广州越秀');
insert into employees values(3,'Merry',24,'广州白云');
insert into employees values(4,'Tomes',29,'深圳宝安');
insert into employees values(5,'Ferry',26,'深圳南山');
insert into employees values(6,'Jone',25,'深圳福田');
~~~

* 内连接(INNER JOIN)

  > select employees.name,employees.age,employees.address,department.dept from employees 
  >
  > inner join department 
  >
  > on employees.id = department.id;

* 左外连接(LEFT OUTER JOIN)

  > select employees.name,employees.age,employees.address,department.dept from employees 
  >
  > left join department 
  >
  > on employees.id = department.id;

* 右外连接(RIGHT OUTER JOIN)

  > select employees.name,employees.age,employees.address,department.dept from employees 
  >
  > left join department 
  >
  > on employees.id = department.id;

* 全连接(FULL OUTER JOIN)

  > select employees.name,employees.age,employees.address,department.dept from employees 
  >
  > full outer join department 
  >
  > on employees.id = department.id;

* 跨连接(CROSS JOIN)

  > select employees.name,employees.age,employees.address,department.dept from employees 
  >
  > cross join department;

#### 4.视图

~~~sql
//创建视图
create view current_employee AS select name,age,address from employees;
select * from current_employee
~~~

#### 5.存储函数（过程）

~~~sql
//函数
create or replace function totalRecodes()
RETURNS integer AS $total$
declare 
	total integer;
begin
	select count(*) into total from employees;
	return total;
end;
$total$ language plpgsql;

select totalRecodes();
~~~

#### 6.权限管理

~~~sql
//授权
create user jepusi with password 'passwrod';
grant all on employees to jepusi
revoke all on employees from jepusi;
drop user jepusi
~~~

#### 7.自动递增

**使用smallserial，serial, bigserial修饰字段实现类似于mysql的auto_increment自增功能**

#### 8.锁

> lock table name in lock_name

* `name`：要锁定的现有表的锁名称(可选模式限定)。 如果在表名之前指定了`ONLY`，则仅该表被锁定 如果未指定`ONLY`，则表及其所有后代表(如果有)被锁定。

* `lock_mode`：锁模式指定此锁与之冲突的锁。 如果未指定锁定模式，则使用最严格的访问模式`ACCESS EXCLUSIVE`。 可能的值是：`ACCESS SHARE`，`ROW SHARE`，`ROW EXCLUSIVE`，`SHARE UPDATE EXCLUSIVE`，`SHARE`，`SHARE ROW EXCLUSIVE`，`EXCLUSIVE`，`ACCESS EXCLUSIVE`。

  

#### 9.事务控制

以下命令用于控制事务：

- `BEGIN TRANSACTION`：开始事务。
- `COMMIT`：保存更改，或者您可以使用`END TRANSACTION`命令。
- `ROLLBACK`：回滚更改。

事务控制命令仅用于DML命令`INSERT`，`UPDATE`和`DELETE`。 创建表或删除它们时不能使用它们，因为这些操作会在数据库中自动提交。

~~~sql
//开启事务
select * from employees;
delete from employees where id = 1;
commit;

rollback;
~~~

#### 10.创建索引

> create index index_name on table_name(colum_name)

~~~sql
//创建索引
create index index_emp on employees(id);
~~~

#### 11.触发器

* PostgreSQL在以下情况下执行/调用触发器：在尝试操作之前(在检查约束并尝试`INSERT`，`UPDATE`或`DELETE`之前)。或者在操作完成后(在检查约束并且`INSERT`，`UPDATE`或`DELETE`完成后)。或者不是操作(在视图中`INSERT`，`UPDATE`或`DELETE`的情况下)

* 对于操作修改的每一行，都会调用一个标记为`FOR EACH ROWS`的触发器。 另一方面，标记为`FOR EACH STATEMENT`的触发器只对任何给定的操作执行一次，而不管它修改多少行。

* 您可以为同一事件定义同一类型的多个触发器，但条件是按名称按字母顺序触发。当与它们相关联的表被删除时，触发器被自动删除。

~~~sql
//创建日志表
CREATE TABLE AUDIT(  
    EMP_ID INT NOT NULL,  
    ENTRY_DATE TEXT NOT NULL  
);
//编写日志触发器函数
create or replace function audilogfunc() returns trigger AS $example_table$
	begin
		insert into audit(EMP_ID,ENTRY_DATE) values(new.ID,current_timestamp);
		return new;
	end;
$example_table$ language plpgsql;
//创建触发器
create trigger example_table after insert on employees for each row execute procedure audilogfunc();

//插入数据
insert into employees values(7,'张三',25,'深圳福田');
insert into employees values(8,'李四',27,'深圳福田');

//查询数据
select * from employees;
select * from audit
~~~

### Springboot整合postgresql

##### 1.创建数据库

##### 2.改pom文件

##### 3.配置yml文件

##### 4.添加实体类

##### 5.添加xml

##### 6.service

##### 7.controller

