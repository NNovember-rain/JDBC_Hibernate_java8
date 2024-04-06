package org.example.entity;


public class Assignment {
    String driverID,RouteID;
    int numberOfTrips;

    public Assignment(String driverID, String routeID, int numberOfTrips) {
        this.driverID = driverID;
        RouteID = routeID;
        this.numberOfTrips = numberOfTrips;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getRouteID() {
        return RouteID;
    }

    public void setRouteID(String routeID) {
        RouteID = routeID;
    }

    public int getNumberOfTrips() {
        return numberOfTrips;
    }

    public void setNumberOfTrips(int numberOfTrips) {
        this.numberOfTrips = numberOfTrips;
    }
}