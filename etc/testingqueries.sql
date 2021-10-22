SELECT * FROM venue
JOIN category_venue ON category_venue.venue_id = venue.id
JOIN category ON category.id = category_venue.category_id

WHERE venue.name = 'Hidden Owl Eatery'


SELECT * FROM city
WHERE id = 1


SELECT * FROM space
WHERE venue_id = 1


select id, max_occupancy FROM space
WHERE 


select to_char((DATE '2001-11-21' + interval '5 days'), 'yyyy-dd-mm')as five_days_from_now ;


SELECT (DATE '2001-02-16', DATE '2001-11-26') OVERLAPS
       (DATE '2001-10-30', DATE '2002-10-30');

SELECT venue.name, venue.description, city.name, city.state_abbreviation, category.name AS details FROM venue 
JOIN category_venue ON category_venue.venue_id = venue.id 
JOIN category ON category.id = category_venue.category_id
JOIN city ON city.id = venue.city_id
WHERE city.name = 'Bona'  AND venue.name = 'Hidden Owl Eatery'

SELECT * FROM VENUE

SELECT * FROM space

SELECT * FROM city where id = 1

START TRANSACTION

INSERT INTO reservation (number_of_attendees, start_date, end_date, reserved_for) VALUES (1

SELECT * FROM reservation
SELECT * FROM space

ROLLBACK


select daily_rate FROM space
Select cast(daily_rate as decimal) FROM space
