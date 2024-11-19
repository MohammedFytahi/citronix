package com.example.Citronix.dto;

import lombok.Data;

@Data
public class FarmUpdateDTO {
    private String name;
    private String location;
    private Double area;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }
}
