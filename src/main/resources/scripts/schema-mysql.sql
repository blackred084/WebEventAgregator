CREATE SCHEMA IF NOT EXISTS eventsagregator;

CREATE TABLE IF NOT EXISTS eventsagregator.hibernate_sequence (
  next_val BIGINT(20));

CREATE TABLE IF NOT EXISTS eventsagregator.appuser (
  id INT(12) UNIQUE,
  login VARCHAR(45) NOT NULL UNIQUE,
  username VARCHAR(80) NOT NULL,
  encryptedPassword VARCHAR(60) NOT NULL,
  email VARCHAR(50) NOT NULL UNIQUE,
  creationDate TIMESTAMP(6) NOT NULL,
  publicUser BIT(1) NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS eventsagregator.event (
  id INT(12) UNIQUE,
  title VARCHAR(45) NOT NULL UNIQUE,
  description VARCHAR(80),
  startDate TIMESTAMP(6) NOT NULL,
  endDate TIMESTAMP(6) NOT NULL,
  creatorId INT(12) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS eventsagregator.like (
  id INT(12) UNIQUE,
  userId INT(12) NOT NULL,
  noteId INT(12) NOT NULL,
  creationDate TIMESTAMP(6) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS eventsagregator.note (
  id INT(12) UNIQUE,
  comment VARCHAR(80) NOT NULL,
  creationDate TIMESTAMP(6) NOT NULL,
  editionStatus VARCHAR(20) NOT NULL,
  noteType VARCHAR(20) NOT NULL,
  idOfAppUser INT(12) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS eventsagregator.observation (
  id INT(12) UNIQUE,
  observerId INT(12) NOT NULL,
  observedId INT(12) NOT NULL,
  creationDate TIMESTAMP(6) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS eventsagregator.participation (
  id INT(12) UNIQUE,
  idOfAppUser INT(12) NOT NULL,
  idOfEvent INT(12) NOT NULL,
  creationDate TIMESTAMP(6) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS eventsagregator.role (
  id INT(12) UNIQUE,
  roleName VARCHAR(45) NOT NULL UNIQUE,
  description VARCHAR(80),
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS eventsagregator.userrole (
  id INT(12) UNIQUE,
  userId INT(12) NOT NULL,
  roleId INT(12) NOT NULL,
  PRIMARY KEY (id));


CREATE TABLE IF NOT EXISTS eventsagregator.hashtag (
  id INT(12) UNIQUE,
  tag VARCHAR(45) NOT NULL UNIQUE,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS eventsagregator.eventhashtag (
  id INT(12) UNIQUE,
  hashTagId INT(12) NOT NULL,
  eventId INT(12) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS eventsagregator.status (
  id INT(12) UNIQUE,
  status VARCHAR(45) NOT NULL UNIQUE,
  description VARCHAR(80),
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS eventsagregator.userstatus (
  id INT(12) UNIQUE,
  userId INT(12) NOT NULL UNIQUE,
  statusId INT(12) NOT NULL,
  PRIMARY KEY (id));
