package hu.danceschool;

import hu.danceschool.controller.DanceService;
import hu.danceschool.model.service.*;

import java.util.Scanner;

public class App {

    private final DanceService danceService;
    private final Console console;
    private final FileWriter fileWriter;

    public App() {
        DataApi dataApi = new DataApi(new FileReader(), new DataParser());
        danceService = new DanceService(dataApi.getData("tancrend.txt"));
        console = new Console(new Scanner(System.in));
        fileWriter = new FileWriter("szereplok.txt");
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("2.feladat");
        System.out.println("Az első tánc neve: " + danceService.getFirstDanceName());
        System.out.println("Az utolsó tánc neve: " + danceService.getLastDanceName());
        System.out.println("3.feladat");
        String danceName = "samba";
        System.out.println("A " + danceName + " táncot " + danceService.countDances(danceName) + " pár táncolta. ");
        System.out.println("4. feladat");
        String girlName = "Vilma";
        System.out.println(girlName + " a következő táncokban szerepelt: " + danceService.getDancesByGirlName(girlName));
        System.out.print("Adja meg a tánc nevét: ");
        danceName = console.read();
        System.out.println(danceService.getBoyName(danceName, girlName));
        fileWriter.write(danceService.getNames());
    }

}
