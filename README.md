## data
The gene data (~36.5M records, ~5GB) can be downloaded from here: ftp://ftp.ncbi.nlm.nih.gov/gene/DATA/gene_info.gz

Place the downloaded tsv file in the root folder of this project into a folder called ./dataloader/data and name it 'gene_info.tsv'.

## purpose
This repository will hold the projects for the different exercises in the module "Medical Software Development".  

- dataanalyzer:
  - a python script which will load the tsv file and extract its data into a pandas dataframe to perform basic analyzing and row counting
- dataloader:
  - a java project for loading the data into a MySQL database. Due to the high volumne of data, the script can run for several minutes.
  - works with Apache Ant