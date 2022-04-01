package ch.fhnw.mscmi;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Loader {

    public static void main(String [] args) {
        try {
            // getting parameters
            Properties appProps = new Properties();
            appProps.load(new FileInputStream("app.properties"));

            String hostname = appProps.getProperty("hostname");
            int port = Integer.parseInt(appProps.getProperty("port"));
            String dbName = appProps.getProperty("dbName");
            String username = appProps.getProperty("username");
            String password = appProps.getProperty("password");
            String filename = appProps.getProperty("filename");
            final int BATCH_SIZE = Integer.parseInt(appProps.getProperty("batchSize"));

            // database information
            DBConnector connector = new DBConnector(hostname, port, dbName, username, password);

            // file information
            CSVReader csvReader = new CSVReader(filename, "\t");

            // skip header
            csvReader.next();

            String [] row = csvReader.next();

            long start = System.currentTimeMillis();
            long startOverall = start;
            long stop;

            List<Gene> container = new ArrayList<>();

            int counter = 0;
            while (row != null) {
                Gene g = GeneUtil.convert(row);
                container.add(g);
                row = csvReader.next();

                counter++;
                if (counter % BATCH_SIZE == 0) {
                    connector.writeRows(container);
                    container.clear();
                    stop = System.currentTimeMillis();
                    System.out.println(BATCH_SIZE + " Genes loaded in " + (stop-start) / 60000.0 + " minutes!");
                    start = stop;
                }
            }

            connector.writeRows(container);

            stop = System.currentTimeMillis();
            System.out.println("data loaded in " + (stop-startOverall) / 60000.0 + " minutes!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
