drop database Jobeet;
create database Jobeet;

use Jobeet;

CREATE TABLE Category (
  id integer AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
	created_at datetime not null,
	updated_at datetime,
  PRIMARY KEY (`id`),
  UNIQUE(name)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

create table Job(
    id integer auto_increment,
    type varchar(255),
    company varchar(255) not null,
    logo varchar(255),
    url varchar(255),
    position varchar(255) not null,
    location varchar(255) not null,
    description text not null,
    how_to_apply text not null,
    token varchar(255) not null,
    is_public boolean,
    is_activated boolean,
    email varchar(255),
    expires_at datetime not null,
    created_at datetime not null,
    updated_at datetime,
    category_id integer,
    unique(token),
    CONSTRAINT Job_fk1 foreign key(category_id) references Category(id) on delete cascade,
    primary key(id));


create table Affiliate(
	id integer auto_increment,
	url varchar(255),
	email varchar(255),
	token varchar(255),
	created_at datetime,
	primary key(id)
);

create table CategoryAffiliate(
	id integer auto_increment,
	category_id integer,
	affiliate_id integer,
	CONSTRAINT CategoryAffiliate_fk1 foreign key(category_id) references Category(id) on delete cascade,
	CONSTRAINT CategoryAffiliate_fk2 foreign key(affiliate_id) references Affiliate(id) on delete cascade,
	primary key(id)
);
