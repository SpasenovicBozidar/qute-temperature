package at.htl.temperature.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Table(name = "S_TEMPERATURE")
@Entity
public class Temperature {

    @Id
    @Column(name = "T_YYYYMM")
    private int yyyymm;

    @Column(name = "T_LOCATION")
    private String location;

    @Column(name = "T_MONTH")
    private String month;
    @Column(name = "T_TEMPERATURE")
    private double temperature;
    @Column(name = "T_YEAR")
    private int year;



    public Temperature() {
    }



    public int calcWidth() {
        return (int) (temperature * 3);
    }

    public Temperature(int yyyymm, String location, String month, double temperature, int year) {
        this.yyyymm = yyyymm;
        this.location = location;
        this.month = month;
        this.temperature = temperature;
        this.year = year;
    }

    public int getYyyymm() {
        return yyyymm;
    }

    public void setYyyymm(int yyyymm) {
        this.yyyymm = yyyymm;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "yyyymm=" + yyyymm +
                ", location='" + location + '\'' +
                ", month='" + month + '\'' +
                ", temperature=" + temperature +
                ", year=" + year +
                '}';
    }


}
