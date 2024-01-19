CREATE TABLE italy_cities (
  istat int PRIMARY KEY NOT NULL UNIQUE,
  comune varchar(255) ,
  regione varchar_ignorecase(50) ,
  provincia varchar_ignorecase(2) ,
  prefisso varchar(7) ,
  cod_fisco varchar(10)  unique,
  superficie double ,
  num_residenti int 
);

ALTER table italy_cities add unique(comune, regione, provincia);
CREATE INDEX index_number_inhabitants ON italy_cities(num_residenti);

CREATE TABLE italy_geo (
  istat int PRIMARY KEY NOT NULL UNIQUE,
  comune varchar(255) ,
  lng varchar(255) ,
  lat varchar(255) ,
  foreign key(istat) references italy_cities(istat) ON DELETE CASCADE ON UPDATE CASCADE
);


