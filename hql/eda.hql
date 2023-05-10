
SELECT ROUND(AVG("fatal_casualities_number"), 4) as "fatal_casualities_percent",
 ROUND(AVG("serious_casualities_number"), 4) as "serious_casualities_percent",
 ROUND(AVG("slight_casualities_number"), 4) as "slight_casualities_percent"

FROM (SELECT CASE WHEN casualty_severity = 1 THEN 1 ELSE 0 END as "fatal_casualities_number",
	     CASE WHEN casualty_severity = 2 THEN 1 ELSE 0 END as "serious_casualities_number",
             CASE WHEN casualty_severity = 3 THEN 1 ELSE 0 END as "slight_casualities_number"
      FROM casualty) as t;


SELECT ROUND(AVG("fatal_casualities_number"), 4) as "possibly_fatal_casualities_percent",
 ROUND(AVG("serious_casualities_number"), 4) as "slight_casualities_percent",
FROM (SELECT CASE WHEN casualty_severity = 1 OR casualty_severity = 2 THEN 1 ELSE 0 END as "possibly_fatal_casualties",
             CASE WHEN casualty_severity = 3 THEN 1 ELSE 0 END as "slight_casualities_number"
      FROM casualty) as t;


