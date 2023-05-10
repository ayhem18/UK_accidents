set hive.support.quoted.identifiers=none;
set hive.cli.print.header=true;



drop table if exists merged;
drop table if exists t1;


create table t1 as
	select a.*, v.`(accident_index)?+.+`
	from accident a
       join vehicle v
       on a.accident_index = v.accident_index ;

select count(*) from t1;

create table merged as
	select t1.*, c.`(accident_index|vehicle_reference)?+.+`
	from t1
	join casualty c
	on t1.accident_index = c.accident_index and t1.vehicle_reference = c.vehicle_reference;

select count(*) from merged;




