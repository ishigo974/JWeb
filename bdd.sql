create TABLE articles (
    id int(11) not null,
    title varchar(250) not null,
    price varchar(250) not null,
    content varchar(900) not null,
    img varchar(250) not null,
    PRIMARY KEY (id)
);
create TABLE comments (
    name varchar(255) not null,
    content varchar(255) not null,
    article int(11) not null,
    id int(11) not null,
    PRIMARY KEY (id)
);
create TABLE news (
    id int(11) not null,
    title varchar(250) not null,
    content varchar(900) not null,
    PRIMARY KEY (id)
);
create TABLE users (
    id int(11) not null,
    email varchar(250) not null,
    pswd varchar(250) not null,
    name varchar(250) not null,
    newsletter tinyint(1) not null,
    admin tinyint(1) not null,
    PRIMARY KEY (id)
);
CREATE UNIQUE INDEX email ON users (email ASC);

create TABLE articles (
    id int(11) not null,
    title varchar(250) not null,
    price varchar(250) not null,
    content varchar(900) not null,
    img varchar(250) not null,
    PRIMARY KEY (id)
);