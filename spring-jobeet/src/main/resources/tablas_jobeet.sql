CREATE TABLE JobeetCategory (id BIGINT AUTO_INCREMENT, name VARCHAR(255)
NOT NULL COMMENT 'test', created_at DATETIME, updated_at DATETIME, slug
VARCHAR(255), UNIQUE INDEX sluggable_idx (slug), PRIMARY KEY(id))
ENGINE = INNODB;

create table JobeetJob ( 
	id BIGINT AUTO_INCREMENT,
	category_id BIGINT,  
	type varchar(255),
    company varchar(255) not null,
    logo varchar(255),
    url varchar(255),
    position varchar(255) not null,
    location varchar(255) not null,
    description varchar(4000) not null,
    how_to_apply varchar(4000) not null,
    token varchar(255) not null,
    is_public boolean not null default 1,
    is_activated boolean not null default 0,
    email varchar(255) not null,
    expires_at timestamp not null,
	CONSTRAINT JobeetJob_category_fk FOREIGN KEY (category_id) REFERENCES JobeetCategory(id) ON DELETE CASCADE,
	unique(token),
	PRIMARY KEY (id));
	
create table JobeetAffiliate(
	id BIGINT AUTO_INCREMENT,
	url varchar(255) not null,
    email varchar(255) not null,
    token varchar(255) not null,
    is_active boolean not null default 0,
	unique(email),
	PRIMARY KEY(id)
);

create table JobeetCategoryAffiliate(
	category_id BIGINT, 
    affiliate_id BIGINT,
	CONSTRAINT JobeetCategoryAffiliate_category_fk FOREIGN KEY (category_id) REFERENCES JobeetCategory(id) ON DELETE CASCADE,
	CONSTRAINT JobeetCategoryAffiliate_affiliate_fk FOREIGN KEY (affiliate_id) REFERENCES JobeetAffiliate(id) ON DELETE CASCADE,
	PRIMARY KEY(category_id,affiliate_id)
);


