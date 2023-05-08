set hive.support.quoted.identifiers=none;
set hive.cli.print.header=true;

create table merged 
as select a.*, v.`(accident_index)?+.+`,  c.`(accident_index|vehicle_reference)?+.+`
	from accident_part a 
	join vehicle_part v 
	on a.accident_index = v.accident_index 

	join casualty_part c 
	on a.accident_index = c.accident_index 
		and v.vehicle_reference = c.vehicle_reference;
