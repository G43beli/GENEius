## data
The gene data (~36.5M records, ~5GB) can be downloaded from here: LINK TODO

Place the downloaded tsv file in the root folder of this project into a folder called ./data and name it 'gene_info'.

## purpose
This python script will load the tsv file and extract its data into a pandas dataframe. The dataframe will then be converted into a SQLite table and saved in a database file under this path: ./data/genesDb.db. Due to the high volumne of data, the script can run for several minutes.
