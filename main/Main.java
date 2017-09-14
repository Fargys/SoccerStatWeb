package Main;

import Controllers.TableController;
import Helpers.ConsoleHelper;
import java.io.IOException;

public class Main {
    
    private static TableController tb = new TableController();
    
public static void main(String[] args) throws IOException {
        mainLogic(chooseAction());
        ConsoleHelper.reader.close();
    }
    
    private static void mainLogic(int choice) {
        switch (choice) {
            case 1:
                tb.selectTableFromDataBase();
                mainLogic(chooseAction());
                break;
            case 2:
                tb.createTable();
                mainLogic(chooseAction());
                break;
            case 3:
                tb.removeChampionship();
                mainLogic(chooseAction());
                break;
            case 4:
                tb.showChampionship();
                mainLogic(chooseAction());
                break;
            case 5:
                tb.change();
                mainLogic(chooseAction());
                break;
            case 6:
                break;
           
            
        }
    }
    
    private static int chooseAction() {
        System.out.println();
        System.out.println("\t" + "Выберите нужное действие.");
        System.out.println("1. Выбрать таблицу.");
        System.out.println("2. Создать новую таблицу.");
        System.out.println("3. Удалить чемпионат.");
        System.out.println("4. Показать таблицу.");
        System.out.println("5. Внести изменения.");
        System.out.println("6. Выйти.");
        System.out.println();

        return ConsoleHelper.readInt(1, 6);
    }
}