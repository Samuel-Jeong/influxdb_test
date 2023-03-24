package com.pwdw.influxdb_test.util;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private static final String FILENAME = "/Users/jamesj/Desktop/SORTIE/20230324/jongro1.csv";

    private CsvReader() {}

    public static List<String[]> read() {
        List<String[]> data = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(FILENAME))) {
            String[] s;
            while ((s = reader.readNext()) != null) {
                data.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

}
