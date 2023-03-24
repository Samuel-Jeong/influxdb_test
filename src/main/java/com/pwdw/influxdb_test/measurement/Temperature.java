package com.pwdw.influxdb_test.measurement;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import lombok.Data;

@Data
@Measurement(name = "temperature")
public class Temperature {

    @Column
    private String date;
    @Column
    private String time;
    @Column(tag = true)
    private float temperature;
    @Column
    private int weight;

    public Temperature(String date, String time, float temperature) {
        this.date = date;
        this.time = time;
        this.temperature = temperature;
    }

    public Temperature(String date, String time, float temperature, int weight) {
        this.date = date;
        this.time = time;
        this.temperature = temperature;
        this.weight = weight;
    }

    public Temperature() {}

}
