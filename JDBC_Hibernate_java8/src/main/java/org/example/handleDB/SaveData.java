package org.example.handleDB;
import org.example.entity.Assignment;
import org.example.entity.Route;
import org.example.utils.ConnectJDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import org.example.entity.Driver;

public class SaveData {
    public static void saveDriver(ArrayList<Driver> drivers){
        String sql="INSERT INTO driver (name, address, phonenumber, level) VALUES (?, ?, ?, ?)";
        try(Connection conn=ConnectJDBCUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);){
            for(Driver dr:drivers){
                stmt.setString(1,dr.getName());
                stmt.setString(2,dr.getAddress());
                stmt.setString(3,dr.getPhoneNumber());
                stmt.setString(4,dr.getLevel());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void saveRoute(ArrayList<Route> routes){
        String sql="INSERT INTO route (distance, breakpoints) VALUES (?, ?)";
        try(Connection conn=ConnectJDBCUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);){
            for(Route r:routes){
                stmt.setInt(1,r.getDistance());
                stmt.setInt(2,r.getBreakpoints());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public static void saveAssign(Assignment asg){
        String sql="INSERT INTO assignment (driver_id, route_id,numberoftrips) VALUES (?, ?, ?)";
        try(Connection conn=ConnectJDBCUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);){
            stmt.setInt(1,Integer.parseInt(asg.getDriverID()));
            stmt.setInt(2,Integer.parseInt(asg.getRouteID()));
            stmt.setInt(3,asg.getNumberOfTrips());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
