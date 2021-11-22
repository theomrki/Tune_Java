
DELIMITER $

CREATE PROCEDURE get_all_pieces()
BEGIN
  SELECT  
    piece.id_piece AS id,
    piece.denomination AS title, 
    CONCAT_WS(' ', person.firstname, person.lastname) AS author,
    piece.duration AS duration
    FROM piece
  INNER JOIN authors
    ON authors.id_piece = piece.id_piece
  INNER JOIN person 
    ON person.id_person = authors.id_person;
END $

CREATE PROCEDURE get_all_bands()
BEGIN
  SELECT
    band.id_band,
    band.denomination AS band_label,  
    CONCAT_WS(' ', person.firstname, person.lastname) AS correspondent
    FROM band
  INNER JOIN person
    ON person.id_person = band.id_person;
END $ 

CREATE PROCEDURE get_all_musicians()
BEGIN
  SELECT 
    musician.id_musician,
    CONCAT_WS(' ', person.firstname, person.lastname) AS member,
    instrument.denomination
    FROM musician
  INNER JOIN person
    ON person.id_person = musician.id_person
  INNER JOIN play
    ON play.id_musician = musician.id_musician
  INNER JOIN instrument
    ON instrument.id_instrument = play.id_instrument
;END$

CREATE PROCEDURE get_all_meetings()
BEGIN
  SELECT 
    meeting.id_meeting,
    meeting.label,
    meeting.expected_visitors
    FROM meeting;
END $

CREATE PROCEDURE get_all_places()
BEGIN
  SELECT
    city.id_city,
    city.id_country,
    city.city_name AS city_name,
    country.country_name AS country_name
    FROM city
  INNER JOIN country
    ON country.country_id = city.id_country;
END $

CREATE PROCEDURE get_bands_playing_piece(IN p_id_piece INT)
BEGIN
  SELECT  
    band.id_band,
    band.denomination AS band_label,  
    CONCAT_WS(' ', person.firstname, person.lastname) AS correspondent
    FROM band
  INNER JOIN performance
    ON performance.id_band = band.id_band
  INNER JOIN person
    ON person.id_person = band.id_person
  INNER JOIN play_piece 
    ON play_piece.id_performance = performance.id_performance
  WHERE id_piece = p_id_piece;
END $

CREATE PROCEDURE get_meetings_by_piece_and_band(
  IN p_id_piece INT,
  IN p_id_band INT
) 
BEGIN 
  SELECT 
    meeting.id_meeting,
    meeting.label, 
    meeting.expected_visitors
    FROM meeting
  INNER JOIN during 
    ON during.id_meeting = meeting.id_meeting
  INNER JOIN performance
    ON performance.id_performance = during.id_performance
  INNER JOIN band 
    ON band.id_band = performance.id_band
  INNER JOIN play_piece 
    ON play_piece.id_performance = performance.id_performance
  WHERE play_piece.id_piece = p_id_piece
    AND band.id_band = p_id_band;
END $

CREATE PROCEDURE get_musician_by_meeting_and_speciality(
  IN p_id_meeting INT,
  IN p_id_speciality iNT
)
BEGIN
  SELECT 
    musician.id_musician,
    CONCAT_WS(' ', person.firstname, person.lastname) AS member,
    instrument.denomination
    FROM meeting
  INNER JOIN during
    ON during.id_meeting = meeting.id_meeting
  INNER JOIN performance
    ON performance.id_performance = during.id_performance
  INNER JOIN band 
    ON band.id_band = performance.id_band
  INNER JOIN member_of_band
    ON member_of_band.id_band = band.id_band
  INNER JOIN musician
    ON musician.id_musician = member_of_band.id_musician
  INNER JOIN person
    ON person.id_person = musician.id_person
  INNER JOIN play
    ON play.id_musician = musician.id_musician
  INNER JOIN instrument
    ON instrument.id_instrument = play.id_instrument
  WHERE meeting.id_meeting = p_id_meeting
    AND musician.id_speciality = p_id_speciality;
END $

CREATE PROCEDURE get_piece_by_country_longer_than(
  IN p_id_country INT,
  IN p_seconds INT
)
BEGIN
  SELECT piece.id_piece, piece.denomination, piece.duration 
  FROM piece
  INNER JOIN play_piece 
    ON play_piece.id_piece = piece.id_piece
  INNER jOIN performance 
    ON performance.id_performance = play_piece.id_performance
  INNER JOIN during 
    ON during.id_performance = performance.id_performance
  INNER JOIN meeting 
    ON meeting.id_meeting = during.id_meeting
  INNER JOIN takes_place
    ON takes_place.id_meeting = meeting.id_meeting
  INNER JOIN address
    ON address.id_address = takes_place.id_address
  INNER JOIN city
    ON city.id_city = address.id_city
  INNER JOIN country
    ON country.country_id = city.id_country
  WHERE piece.duration > p_seconds
    AND city.id_country = p_id_country
;END $

CREATE PROCEDURE get_meetings_by_band_count(
  IN p_band_count INT
)
BEGIN
  SELECT meeting.label, meeting.expected_visitors
    FROM meeting
  INNER JOIN during 
    ON during.id_meeting = meeting.id_meeting
  INNER JOIN performance
    ON performance.id_performance = during.id_performance
  HAVING COUNT(DISTINCT performance.id_band) = p_band_count;
END $ 

CREATE PROCEDURE get_meetings_by_instrument(
  IN p_id_instrument INT
)
BEGIN 
  SELECT meeting.label, meeting.expected_visitors
    FROM meeting
  INNER JOIN during
    ON during.id_meeting = meeting.id_meeting
  INNER JOIN performance
    ON performance.id_performance = during.id_performance
  INNER JOIN band
    ON band.id_band = performance.id_band
  INNER JOIN member_of_band
    ON member_of_band.id_band = band.id_band
  INNER JOIN musician
    ON musician.id_musician = member_of_band.id_musician
  INNER JOIN play
    ON play.id_musician = musician.id_musician
  WHERE play.id_instrument = p_id_instrument;
END $

DELIMITER ;