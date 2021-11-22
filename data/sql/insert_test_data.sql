
SET FOREIGN_KEY_CHECKS = 0;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/countries.csv'
INTO TABLE country
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/cities.csv'
INTO TABLE city
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/addresses.csv'
INTO TABLE address
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/persons.csv'
INTO TABLE person
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/pieces.csv'
INTO TABLE piece
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/authors.csv'
INTO TABLE authors
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/contacts.csv'
INTO TABLE contact
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/families.csv'
INTO TABLE family
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/categories.csv'
INTO TABLE category
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/instruments.csv'
INTO TABLE instrument
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/musicians.csv'
INTO TABLE musician
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/bands.csv'
INTO TABLE band
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/members.csv'
INTO TABLE member_of_band
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/directories.csv'
INTO TABLE directory
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/meetings.csv'
INTO TABLE meeting
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/performances.csv'
INTO TABLE performance
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/during.csv'
INTO TABLE during
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/play_piece.csv'
INTO TABLE play_piece
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/takes_place.csv'
INTO TABLE takes_place
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

LOAD DATA INFILE 'C:/Users/mouar/Desktop/a_tune_of_java-main/data/test/plays.csv'
INTO TABLE play
COLUMNS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

SET FOREIGN_KEY_CHECKS = 1;








