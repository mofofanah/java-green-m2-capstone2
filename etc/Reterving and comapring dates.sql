 SELECT space.id, space.venue_id, space.name, space.is_accessible, space.open_from, space.open_to, space.daily_rate::money::numeric::float8, space.max_occupancy  FROM space 
                WHERE space.venue_id = 15
                AND space.id NOT IN (SELECT space.id FROM space 
                LEFT JOIN reservation ON space.id = reservation.space_id 
                WHERE (date '10/30/2021' <= reservation.end_date 
                AND date '10/25/2021' >= reservation.start_date) 
                OR EXTRACT(MONTH FROM CAST('02/15/2021' AS DATE)) < space.open_from
                OR EXTRACT(MONTH FROM CAST('12/19/2021' AS DATE)) > space.open_to 
                OR space.max_occupancy < 100) 
                ORDER BY space.daily_rate DESC 
                --LIMIT 5 ;