package org.example.service;

import org.example.AssignIMPL;
import org.example.entity.Assignment;
import org.example.entity.Driver;
import org.example.entity.Route;
import org.example.handleDB.SaveData;

import java.util.*;
import java.util.stream.Collectors;


public class TableAssignment implements AssignIMPL {
    private int maxTrips=15;
    private int numberOfDrivers; //số lượng Driver
    private int numberOfRoutes; // số lượng Route
    private ArrayList<Driver> driver; // danh sách Driver
    private ArrayList<Route> route; // danh sách Route
    private Map<String,Map<String,Integer>>assignDrivingTurns;// tạo map<String,Map<String,Integer>>  lưu key là DriverId ứng với value là cặp routeId và số lượt


    public TableAssignment(int numberOfDrivers, int numberOfRoutes) {
        this.numberOfDrivers= numberOfDrivers;
        this.numberOfRoutes = numberOfRoutes;
        driver=new ArrayList<>();
        route=new ArrayList<>();
        assignDrivingTurns=new HashMap<>();
    }


    public TableAssignment() {

    }


    @Override
    public void inputListDrivers(Scanner sc) {
        System.out.println("\n-Input a list of includes "+numberOfDrivers+" Driver :");
        for(int i=0;i<numberOfDrivers;i++) {
            System.out.print("Name of driver st."+(i+1)+": ");
            String name=sc.nextLine();
            System.out.print("Address: ");
            String address=sc.nextLine();
            System.out.print("PhoneNumber: ");
            String nbPhone=sc.nextLine();
            System.out.print("Level: ");
            String lev=sc.nextLine();
            System.out.println();
            Driver dr = new Driver(name,address,nbPhone,lev);
            driver.add(dr);

        }
        System.out.println("- Driver List : ");
        for(int i=0;i<numberOfDrivers;i++){
            System.out.println(" +," + driver.get(i));
        }
        System.out.println();
    }

    @Override
    public void inputListRoutes(Scanner sc) {

        System.out.println("\n-Input a list of includes "+numberOfRoutes+" Route :");
        for(int i=0;i<numberOfRoutes;i++) {
            System.out.print("Input distance: ");
            int distance=Integer.parseInt(sc.nextLine());
            System.out.print("Input number of breakpoints: ");
            int breakpoint=Integer.parseInt(sc.nextLine());
            System.out.println();
            Route r=new Route(distance,breakpoint);
            route.add(r);
        }
        System.out.println("-Route List:");
        for(int i=0;i<numberOfRoutes;i++){
            System.out.println(" +,"+ route.get(i));
        }
        System.out.println();


    }

    @Override
    public void assignRouteForDriver(Scanner sc) {
        if(driver.isEmpty() || route.isEmpty()){
            System.out.println("\nPlease, Chose option 1 or 2 !!!");
            System.out.println();
            return;
        }

        System.out.println("\n-Existing Driver:");
        for(int i=0;i<numberOfDrivers;i++){
            System.out.println("+, "+driver.get(i));
        }

        System.out.println("\n-Existing Route:");
        for(int i=0;i<numberOfRoutes;i++){
            System.out.println("+, "+ route.get(i));
        }

        String driverID; // nhập mã Driver
        do{
            System.out.print("\nEnter Drive ID : ");
            driverID=sc.nextLine();
            if(driverID.length()!=5) System.out.println("! Please , Enter the number with 5 digit !");
        }while(driverID.length()!=5);

        String routeID; // nhập mã Route
        do{
            System.out.print("Enter Route ID: ");
            routeID=sc.nextLine();
            if(routeID.length()!=3) System.out.println("! Please , Enter the number with 3 digit !");
        }while(routeID.length()!=3);

        System.out.print("Enter the number of trips :"); // nhập số lượt
        int numberTrips=Integer.parseInt(sc.nextLine());
        if(numberTrips>maxTrips){
            System.out.println("\nOver Route numbers allowed !!!\n");
            return;
        }


        // check xem mã Driver và mã Route có tồn tại không
        if(Integer.parseInt(driverID)>numberOfDrivers || Integer.parseInt(routeID)>numberOfRoutes) {
            System.out.println("\nDriver or Route not found don't exist , Try again !!!\n");
            return;
        }

        //Xử lí
        // assignDrivingTurns lưu key là mã Driver ứng với value là cặp mã Route và số lượt
        // check xem thằng mã Driver đã nằm trong map chưa
        if(assignDrivingTurns.containsKey(driverID)){
            Map<String,Integer> mp=  assignDrivingTurns.get(driverID); // mp lấy cái map<String,Integer> lưu mã Route và số lượt tương ứng
            if(mp.get(routeID)!=null) { // trường hợp này rơi vào nếu trước đó ta chọn thằng Driver 00000 với Route 001 , lần sau chọn tiếp thằng 00000 với 001 mp.get(routeID) sẽ null gây lỗi
                if ((mp.get(routeID) + numberTrips) > maxTrips) { // check quá giới hạn
                    System.out.println("\nYou've driven enough this Route!\n");
                    return;
                }
                Assignment assignment=new Assignment(driverID,routeID,numberTrips + mp.get(routeID));
                SaveData.saveAssign(assignment);
                mp.put(routeID, numberTrips + mp.get(routeID)); // nếu tồn tại cả mã Driver và mã Route rồi thì cập nhật
                assignDrivingTurns.put(driverID, mp);
            }
            else {
                mp.put(routeID, numberTrips);
                assignDrivingTurns.put(driverID, mp);
            }
        }
        else{ // thêm vào
            Assignment assignment=new Assignment(driverID,routeID,numberTrips);
            SaveData.saveAssign(assignment);
            Map<String,Integer> mp=new HashMap<>();
            mp.put(routeID,numberTrips);
            assignDrivingTurns.put(driverID,mp);
        }

        System.out.println("\nAssign is Accepted !\n");
    }

    @Override
    public void sortByName() {
        if(driver.isEmpty() || route.isEmpty()){
            System.out.println("\nPlease, Chose option 1 or 2 !!!");
            System.out.println();
            return;
        }
        Collections.sort(driver, new Comparator<Driver>() {
            @Override
            public int compare(Driver o1, Driver o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }


    public int cntRoute(Driver x){
        int cnt=0;
        if(assignDrivingTurns.get(x.getId())==null) return 0;
        for(int i=0;i<numberOfRoutes;i++){
            if(assignDrivingTurns.get(x.getId()).get(route.get(i).getId())!=null) cnt++;
        }
        return cnt;
    }

    @Override
    public void sortByNumberRoutes() {
        if(driver.isEmpty() || route.isEmpty()){
            System.out.println("\nPlease, Chose option 1 or 2 !!!");
            System.out.println();
            return;
        }
        Collections.sort(driver, new Comparator<Driver>() {
            @Override
            public int compare(Driver o1, Driver o2) {
                if(cntRoute(o1)>cntRoute(o2)) return -1;
                else return 1;
            }
        });
    }

    @Override
    public void seeDriverStatus() {
        if(driver.isEmpty() || route.isEmpty()){
            System.out.println("\nPlease, Chose option 1 or 2 !!!");
            System.out.println();
            return;
        }

        for(Driver lx:driver){
            System.out.println("\n-Assignment for Driver "+lx.getId()+"-"+lx.getName()+" :");
            for(Route t:route){
                if(assignDrivingTurns.containsKey(lx.getId()) && assignDrivingTurns.get(lx.getId()).containsKey(t.getId())){
                    System.out.println(" +,Number of Trips of Route "+t.getId()+ " is: "+assignDrivingTurns.get(lx.getId()).get(t.getId())+" (trip)");
                }
                else System.out.println(" +,The Route "+t.getId()+" hasn't been assigned yet.");
            }
        }
        System.out.println();
    }

    @Override
    public void statistical() {
        if(driver.isEmpty() || route.isEmpty()){
            System.out.println("\nPlease, Chose option 1 or 2 !!!");
            System.out.println();
            return;
        }
        for(int i=0;i<numberOfDrivers;i++) {
            int total = 0;
            System.out.println("\nAssign for Driver "+ driver.get(i).getId()+"-"+driver.get(i).getName()+" is:");
            if(assignDrivingTurns.get(driver.get(i).getId())!=null){
                for (int j = 0; j < numberOfRoutes; j++) {
                    if (assignDrivingTurns.get(driver.get(i).getId()).get(route.get(j).getId()) != null) {
                        int turns=assignDrivingTurns.get(driver.get(i).getId()).get(route.get(j).getId());
                        int distance=turns*route.get(j).getDistance();

                        System.out.println(route.stream().map(route -> "+,The distance of Route " + route.getId() + " is " + route.getDistance() + "(km) and the number of trips is " + turns + "=>  : " + distance + "(km)")
                                .collect(Collectors.joining()));

                        total+=distance;
                    }
                    else{
                        System.out.println(" +,The Route "+route.get(j).getId() +" hasn't been assigned yet. ");
                    }
                }
                System.out.println("=>Total distance traveled is :"+ total+"(km)");
            }
            else {
                System.out.println("The Driver "+driver.get(i).getId()+"-"+driver.get(i).getName()+" hasn't been assigned a route yet !");
            }
            System.out.println();
        }
    }

    @Override
    public void saveDriverInDB() {
        SaveData.saveDriver(driver);
    }

    @Override
    public void saveRouteInDB() {
        SaveData.saveRoute(route);
    }


}

