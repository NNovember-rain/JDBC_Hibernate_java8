package org.example.entity;

public class Route {
    private static int cnt=1;
    private String id;
    private int distance,breakpoints;

    public Route(int distance, int breakpoints) {
        id=String.format("%03d",cnt++);
        this.distance = distance;
        this.breakpoints = breakpoints;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getBreakpoints() {
        return breakpoints;
    }

    public void setBreakpoints(int breakpoints) {
        this.breakpoints = breakpoints;
    }

    public String toString(){
        return this.id+" "+this.distance+"(km) "+this.breakpoints;
    }
}

