package main;

import controllers.TableController;
import java.io.IOException;
import other.ConsoleHelper;

public class Main {
    private static TableController tb = new TableController();
    
public static void main(String[] args) throws IOException {
        mainLogic(chooseAction());
        ConsoleHelper.reader.close();
    }
    
    private static void mainLogic(int choice) {
        switch (choice) {
            case 1:
                tb.selectChampionship();
                mainLogic(chooseAction());
                break;
            case 2:
                tb.createChampionship();
                mainLogic(chooseAction());
                break;
            case 3:
                tb.deleteChampionship();
                mainLogic(chooseAction());
                break;
            case 4:
                tb.showChampionship();
                mainLogic(chooseAction());
                break;
            case 5:
                tb.updateChampionship();
                mainLogic(chooseAction());
                break;
            case 6:
                System.exit(0);
                break;
        }
    }
    
    private static int chooseAction() {
        System.out.println();
        System.out.println("\t" + "Select an action.");
        System.out.println("1. Select a table.");
        System.out.println("2. Create new table.");
        System.out.println("3. Remove championship.");
        System.out.println("4. Show table.");
        System.out.println("5. Make changes.");
        System.out.println("6. Exit.");
        System.out.println();

        return ConsoleHelper.readInt(1, 6);
    }
}