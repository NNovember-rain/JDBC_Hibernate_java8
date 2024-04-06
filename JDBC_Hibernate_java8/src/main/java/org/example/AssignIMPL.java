package org.example;

import java.util.Scanner;

public interface AssignIMPL {
    void inputListDrivers(Scanner sc);
    void inputListRoutes(Scanner sc);
    void assignRouteForDriver(Scanner sc);
    void sortByName();
    void sortByNumberRoutes();
    void seeDriverStatus();
    void statistical();
    public void saveDriverInDB();
    public void saveRouteInDB();
}
