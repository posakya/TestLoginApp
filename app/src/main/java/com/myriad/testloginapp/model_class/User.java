package com.myriad.testloginapp.model_class;

public class User {

    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private Double device_id;
    private Location location;

    public User(){
        //empty constructor
    }

    public User(String id, String first_name, String last_name, String email, Double device_id, Location location) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.device_id = device_id;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Double device_id) {
        this.device_id = device_id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", device_id=" + device_id +
                ", location=" + location +
                '}';
    }
}
