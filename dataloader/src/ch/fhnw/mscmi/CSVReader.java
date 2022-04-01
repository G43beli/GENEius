package ch.fhnw.mscmi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CSVReader {

    private BufferedReader br = null;
    private String filename;
    private int counter;
    private String separator;
    private boolean reading = false;

    public CSVReader(String filename, String separator) throws Exception {
        this.filename = filename;
        this.separator = separator;
    }

    public String [] next() throws Exception {
        if (!reading) {
            startReading();
        }
        String line;
        line = br.readLine();
        if (line == null) {
            return null;
        } else {
            counter++;
            return line.split(separator);
        }
    }

    public int numberOfProcessedLines() {
        return counter;
    }

    public void startReading() throws Exception {
        reading = true;
        File file = new File(filename);
        FileReader fr = new FileReader(file);
        br = new BufferedReader(fr);
        counter = 0;
    }

    public void stopReading() throws Exception {
        br.close();
        reading = false;
    }
}
