package org.example.entity;

public class Driver {
    private static int cnt=1;
    private String id;
    private String Name,address,phoneNumber,level;

    public Driver(String name, String address, String phoneNumber, String level) {
        id=String.format("%05d",cnt++);
        Name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(int id) {
        this.id = String.valueOf(id);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String toString(){
        return this.id+" "+ this.getName()+" "+this.address+" "+this.phoneNumber+" "+this.level;
    }
}
