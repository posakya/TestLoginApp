package com.myriad.testloginapp.model_class;

public class Location {
    private Double latitude;
    private Double longitude;
    private String street_address;

    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", street_address='" + street_address + '\'' +
                '}';
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public Location(Double latitude, Double longitude, String street_address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.street_address = street_address;
    }
}
