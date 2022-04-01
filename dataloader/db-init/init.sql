drop database if exists geneinfo;

create database geneinfo;

use geneinfo;

create table genetype(
  id int,
  name varchar(128),
  PRIMARY KEY(id)
);

create table allgenes(
  tax_id int,
  gene_id int,
  symbol varchar(128),
  locus_tag varchar(128),
  synonyms varchar(1024),
  dbxrefs varchar(1024),
  chromosome varchar(128),
  map_location varchar(128),
  description varchar(2048),
  type_of_gene int,
  sfna varchar(128),
  fnfna varchar(512),
  nomenclature_status varchar(1),
  other_designations varchar(8000),
  mod_date TIMESTAMP,
  feature_type varchar(512),
  PRIMARY KEY(gene_id),
  FOREIGN KEY(type_of_gene) REFERENCES genetype(id)
);

CREATE USER IF NOT EXISTS 'geneloader'@'localhost' IDENTIFIED BY 'geneloaderpw';
GRANT ALL PRIVILEGES ON geneinfo.* TO 'geneloader'@'localhost';
FLUSH PRIVILEGES;

CREATE USER IF NOT EXISTS 'geneloader'@'%' IDENTIFIED BY 'geneloaderpw';
GRANT ALL PRIVILEGES ON geneinfo.* TO 'geneloader'@'%';
FLUSH PRIVILEGES;
