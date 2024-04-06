package org.example.main;

import org.example.service.TableAssignment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of Drivers: ");
        int n = Integer.parseInt(sc.nextLine());
        System.out.print("Enter number of Routes : ");
        int m = Integer.parseInt(sc.nextLine());


        TableAssignment tableAssignment=new TableAssignment(n,m);

        System.out.println();

        while(true) {
            System.out.println("-Pick your choice ->:");
            System.out.println("+,Option 1: Enter Driver list and save in Database");
            System.out.println("+,Option 2: Enter Route list save in Database");
            System.out.println("+,Option 3: Manage the number of Trips of the Driver for each Route save in Database");
            System.out.println("+,Option 4: Sort by the driver name ");
            System.out.println("+,Option 5: Sort by the number of Trips (decrease)");
            System.out.println("+,Option 6: Make a statistical table of each Driver's daily driving distance");
            System.out.println("+,Option 7: View each driver's status");
            System.out.println("+,Option 8: Out !");

            System.out.print("=>Choose 1-->8 : ");
            try{
                int choice=Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        tableAssignment.inputListDrivers(sc);
                        tableAssignment.saveDriverInDB();
                        break;
                    case 2:
                        tableAssignment.inputListRoutes(sc);
                        tableAssignment.saveRouteInDB();
                        break;
                    case 3:
                        tableAssignment.assignRouteForDriver(sc);
                        break;
                    case 4:
                        tableAssignment.sortByName();
                        tableAssignment.seeDriverStatus();
                        break;
                    case 5:
                        tableAssignment.sortByNumberRoutes();
                        tableAssignment.seeDriverStatus();
                        break;
                    case 6:
                        tableAssignment.statistical();
                        break;
                    case 7:
                        tableAssignment.seeDriverStatus();
                        break;
                    case 8:
                        return;
                    default:
                        System.out.println("\n=> outside the selection range !!!\n");
                }
            }catch (Exception e){
                System.out.println("\nYou entered the wrong format, please select an integer from 1->8 to use Options !\n");

            }
        }
    }
}
