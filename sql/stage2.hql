INSERT OVERWRITE LOCAL DIRECTORY '/root/UK_accidents/queries/q1'
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ','

SELECT ROUND(AVG(fatal_casualities_number), 4) as fatal_casualities_percent,
 ROUND(AVG(serious_casualities_number), 4) as serious_casualities_percent,
 ROUND(AVG(slight_casualities_number), 4) as slight_casualities_percent
FROM (SELECT CASE WHEN cas_severity = 1 THEN 1 ELSE 0 END as fatal_casualities_number,
             CASE WHEN cas_severity = 2 THEN 1 ELSE 0 END as serious_casualities_number,
             CASE WHEN cas_severity = 3 THEN 1 ELSE 0 END as slight_casualities_number
      FROM casualty) as t;





INSERT OVERWRITE LOCAL DIRECTORY '/root/UK_accidents/queries/q2'
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ','


SELECT cas_class as casualty_class, ROUND(AVG(possibly_fatal_number), 4) as possibly_fatal_percent,
 ROUND(AVG(slight_casualities_number), 4) as slight_casualities_percent
FROM (SELECT CASE WHEN cas_severity = 1 or cas_severity = 2 THEN 1 ELSE 0 END as possibly_fatal_number,
             CASE WHEN cas_severity = 3 THEN 1 ELSE 0 END as slight_casualities_number,
             cas_class
      FROM merged) as t
group by cas_class;




INSERT OVERWRITE LOCAL DIRECTORY '/root/UK_accidents/queries/q3'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','

SELECT is_special  as is_special_accident ,
       ROUND(AVG(CASE WHEN cas_severity in (1, 2) THEN 1 ELSE 0 END) * 100, 2) as severe_casualties_ratio,
       ROUND(100 - AVG(CASE WHEN cas_severity in (1, 2) THEN 1 ELSE 0 END)*100, 2) as slight_casualties_ratio
FROM (
   SELECT cas_severity, CASE WHEN
        hit_object_in not in (0, -1) or hit_object_off not in(0, -1) or
        veh_leaving not in (0, -1) or  hazards not in (0, -1) or skidding not in (0, -1)
        THEN 1 ELSE 0 END as is_special
    FROM merged) as t
GROUP BY  is_special;






INSERT OVERWRITE LOCAL DIRECTORY '/root/UK_accidents/queries/q4'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','

SELECT is_special  as is_special_accident,
       ROUND(AVG(CASE WHEN cas_severity in (1, 2) THEN 1 ELSE 0 END) * 100, 2) as severe_casualties_ratio,
       ROUND(100 - AVG(CASE WHEN cas_severity in (1, 2) THEN 1 ELSE 0 END)*100, 2) as slight_casualties_ratio
FROM (
    SELECT cas_severity, CASE WHEN
        skidding not in (-1, 0)
        THEN 1 ELSE 0 END as is_special
    FROM merged
    where cas_class = 3) as t
GROUP BY  is_special;






INSERT OVERWRITE LOCAL DIRECTORY '/root/UK_accidents/queries/q5'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','

SELECT speed_limit  as speed_limit,
       ROUND(AVG(CASE WHEN cas_severity in (1, 2) THEN 1 ELSE 0 END) * 100, 2) as severe_casualties_ratio,
       ROUND(100 - AVG(CASE WHEN cas_severity in (1, 2) THEN 1 ELSE 0 END)*100, 2) as slight_casualties_ratio
FROM (
    SELECT cas_severity, speed_limit, area_type
    FROM merged
    where cas_class = 3 ) as t
GROUP BY speed_limit;




INSERT OVERWRITE LOCAL DIRECTORY '/root/UK_accidents/queries/q6'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','

SELECT cas_severity, AVG(cas_age) as average_age FROM (
    SELECT cas_age, (CASE WHEN cas_severity in (1, 2) THEN 1 ELSE 0 END) as cas_severity
    FROM merged
    where cas_class = 3) as t
group by cas_severity;






INSERT OVERWRITE LOCAL DIRECTORY '/root/UK_accidents/queries/q7'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','

SELECT district, COUNT(cas_severity) as accidents_number, ROUND(AVG(cas_severity), 3) as severe_casualties_ratio, SUM(cas_severity) as severe_casualties_number FROM (
    SELECT district, (CASE WHEN cas_severity in (1, 2) THEN 1 ELSE 0 END) as cas_severity
    FROM merged) as t
group by district
ORDER by  accidents_number DESC, severe_casualties_ratio DESC;





INSERT OVERWRITE LOCAL DIRECTORY '/root/UK_accidents/queries/q8'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','

SELECT hour, COUNT(cas_severity) as accidents_number, ROUND(AVG(cas_severity), 3) as severe_casualties_ratio, SUM(cas_severity) as severe_casualties_number FROM (
    SELECT CAST(SUBSTRING(time, 1, 2) AS INT) as hour, CASE WHEN cas_severity in (1, 2) THEN 1 ELSE 0 END as cas_severity from merged) 
    as t
group by hour
order by accidents_number DESC, severe_casualties_ratio DESC;


