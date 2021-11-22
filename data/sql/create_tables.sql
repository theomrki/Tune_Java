CREATE TABLE civility(
   id_civility INT,
   title VARCHAR(8),
   PRIMARY KEY(id_civility)
);

CREATE TABLE country(
   country_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   country_name VARCHAR(255)
);

CREATE TABLE family(
   id_family INT,
   label VARCHAR(255),
   PRIMARY KEY(id_family)
);

CREATE TABLE category(
   id_category INT,
   denomination VARCHAR(255)  NOT NULL,
   id_family INT,
   PRIMARY KEY(id_category),
   FOREIGN KEY(id_family) REFERENCES family(id_family)
);

CREATE TABLE instrument(
   id_instrument INT,
   denomination VARCHAR(255)  NOT NULL,
   id_category INT NOT NULL,
   PRIMARY KEY(id_instrument),
   FOREIGN KEY(id_category) REFERENCES category(id_category)
);

CREATE TABLE piece(
   id_piece INT,
   denomination VARCHAR(255)  NOT NULL,
   duration INT,
   PRIMARY KEY(id_piece)
);

CREATE TABLE frequency(
   id_frequency INT AUTO_INCREMENT,
   label VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_frequency)
);

CREATE TABLE speciality(
   id_speciality INT AUTO_INCREMENT,
   denomination VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_speciality)
);

CREATE TABLE responsibility(
   id_responsibility INT AUTO_INCREMENT PRIMARY KEY,
   label VARCHAR(255)  NOT NULL
);

CREATE TABLE city(
   id_city INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   city_name VARCHAR(255)  NOT NULL,
   id_country INT NOT NULL,
   FOREIGN KEY(id_country) REFERENCES country(country_id)
);

CREATE TABLE meeting(
   id_meeting INT,
   label VARCHAR(255)  NOT NULL,
   expected_visitors VARCHAR(50) ,
   id_frequency INT NOT NULL,
   PRIMARY KEY(id_meeting),
   FOREIGN KEY(id_frequency) REFERENCES frequency(id_frequency)
);

CREATE TABLE address(
   id_address INT,
   number INT,
   track VARCHAR(255),
   label VARCHAR(255),
   id_city INT NOT NULL,
   PRIMARY KEY(id_address),
   FOREIGN KEY(id_city) REFERENCES city(id_city)
);

CREATE TABLE person(
   id_person INT,
   firstname VARCHAR(255)  NOT NULL,
   lastname VARCHAR(255)  NOT NULL,
   birthdate DATE,
   id_address INT,
   id_civility INT NOT NULL,
   PRIMARY KEY(id_person),
   FOREIGN KEY(id_address) REFERENCES address(id_address),
   FOREIGN KEY(id_civility) REFERENCES civility(id_civility)
);

CREATE TABLE contact(
   id_contact INT,
   phone VARCHAR(16) ,
   fax VARCHAR(16) ,
   email VARCHAR(255) ,
   id_person INT NOT NULL,
   PRIMARY KEY(id_contact),
   UNIQUE(id_person),
   FOREIGN KEY(id_person) REFERENCES person(id_person)
);

CREATE TABLE band(
   id_band INT,
   denomination VARCHAR(255)  NOT NULL,
   id_person INT NOT NULL,
   PRIMARY KEY(id_band),
   FOREIGN KEY(id_person) REFERENCES person(id_person)
);

CREATE TABLE musician(
   id_musician INT,
   id_speciality INT,
   id_person INT NOT NULL,
   PRIMARY KEY(id_musician),
   FOREIGN KEY(id_speciality) REFERENCES speciality(id_speciality),
   FOREIGN KEY(id_person) REFERENCES person(id_person)
);

CREATE TABLE performance(
   id_performance INT NOT NULL AUTO_INCREMENT,
   performance_date DATE NOT NULL,
   begin_time TIME NOT NULL,
   id_band INT NOT NULL,
   PRIMARY KEY(id_performance),
   FOREIGN KEY(id_band) REFERENCES band(id_band)
);

CREATE TABLE member_of_band(
   id_band INT,
   id_responsibility INT,
   id_musician INT,
   PRIMARY KEY(id_band, id_responsibility, id_musician),
   FOREIGN KEY(id_band) REFERENCES band(id_band),
   FOREIGN KEY(id_responsibility) REFERENCES responsibility(id_responsibility),
   FOREIGN KEY(id_musician) REFERENCES musician(id_musician)
);

CREATE TABLE play(
   id_musician INT,
   id_instrument INT,
   PRIMARY KEY(id_musician, id_instrument),
   FOREIGN KEY(id_musician) REFERENCES musician(id_musician),
   FOREIGN KEY(id_instrument) REFERENCES instrument(id_instrument)
);

CREATE TABLE authors(
   id_person INT,
   id_piece INT,
   PRIMARY KEY(id_person, id_piece),
   FOREIGN KEY(id_person) REFERENCES person(id_person),
   FOREIGN KEY(id_piece) REFERENCES piece(id_piece)
);

CREATE TABLE directory(
   id_band INT,
   id_piece INT,
   PRIMARY KEY(id_band, id_piece),
   FOREIGN KEY(id_band) REFERENCES band(id_band),
   FOREIGN KEY(id_piece) REFERENCES piece(id_piece)
);

CREATE TABLE during(
   id_meeting INT,
   id_performance INT,
   PRIMARY KEY(id_meeting, id_performance),
   FOREIGN KEY(id_meeting) REFERENCES meeting(id_meeting),
   FOREIGN KEY(id_performance) REFERENCES performance(id_performance)
);

CREATE TABLE play_piece(
   id_piece INT,
   id_performance INT,
   PRIMARY KEY(id_piece, id_performance),
   FOREIGN KEY(id_piece) REFERENCES piece(id_piece),
   FOREIGN KEY(id_performance) REFERENCES performance(id_performance)
);

CREATE TABLE takes_place(
   id_address INT,
   id_meeting INT,
   begin_date DATE,
   end_date DATE,
   PRIMARY KEY(id_address, id_meeting),
   FOREIGN KEY(id_address) REFERENCES address(id_address),
   FOREIGN KEY(id_meeting) REFERENCES meeting(id_meeting)
);
