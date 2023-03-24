package com.pwdw.influxdb_test.service;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.pwdw.influxdb_test.config.DefaultConfig;
import com.pwdw.influxdb_test.measurement.Temperature;
import com.pwdw.influxdb_test.util.CsvReader;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
public class InfluxService {

    private final DefaultConfig defaultConfig;

    public InfluxService(DefaultConfig defaultConfig) {
        this.defaultConfig = defaultConfig;
    }

    @PostConstruct
    public void init() throws InterruptedException {
        int i = 0;
        for (String[] data : CsvReader.read()) {
            if (i == 0) { i++; continue; } // 첫 번째줄은 생략 (필드)
            Temperature temperature = new Temperature(
                    data[0],
                    data[1],
                    Float.parseFloat(data[2])
                    //data[3],
            );
            write(temperature);
            log.info("{}", temperature);

            Thread.sleep(1000);
        }
    }

    private void write(Temperature temperature) {
        InfluxDBClient influxDBClient = InfluxDBClientFactory.create(
                defaultConfig.getUrl(), defaultConfig.getToken(),
                defaultConfig.getOrganization(), defaultConfig.getBucket()
        );

        try (WriteApi writeApi = influxDBClient.makeWriteApi()) {
            writeApi.writeMeasurement(WritePrecision.NS, temperature);
        }

        influxDBClient.close();
    }

}
