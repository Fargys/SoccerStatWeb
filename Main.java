import java.io.*;

import Table.Table;
import ConsoleHelper.ConsoleHelper;

class Main {
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;
    // Current table
    private static Table table = new Table("default");

    public static void main(String[] args) throws IOException {
        mainLogic(chooseAction());
    }

    /**
     * Main logic
     *
     * @param choice Item selected by user.
     */
    private static void mainLogic(int choice) {
        switch (choice) {
            case 1:
                table.printTable();
                mainLogic(chooseAction());
                break;
            case 2:
                table.change();
                mainLogic(chooseAction());
                break;
            case 3:
                table.cleanTable();
                mainLogic(chooseAction());
                break;
            case 4:
                table = table.produceTable();
                mainLogic(chooseAction());
                break;
            case 5:
                saveTable();
                mainLogic(chooseAction());
                break;
            case 6:
                loadTable();
                mainLogic(chooseAction());
                break;
            case 7:
                break;
        }
    }

    /**
     * Method prints to console list of possible actions.
     *
     * @return number of item selected by user.
     */
    private static int chooseAction() {
        System.out.println();
        System.out.println("\t" + "Выберите нужное действие.");
        System.out.println("1. Показать таблицу.");
        System.out.println("2. Внести изменения.");
        System.out.println("3. Очистить таблицу.");
        System.out.println("4. Создать новую таблицу.");
        System.out.println("5. Сохранить таблицу.");
        System.out.println("6. Загрузить таблицу.");
        System.out.println("7. Выйти.");

        return ConsoleHelper.readInt(1, 7);
    }

    /**
     * Method for serialization object.
     */
    private static void saveTable() {
        Table.saveData.add(table.getName());

        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("C:\\Projects\\1. Olimp\\" + table.getName())));
            oos.writeObject(table);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Таблица успешно сохранена");
    }

    /**
     * Method for deserialization object.
     */
    private static void loadTable() {
        try {
            ois = new ObjectInputStream(new BufferedInputStream(
                    new FileInputStream("C:\\Projects\\1. Olimp\\" + chooseTable())));
            table = (Table) ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Файл не найден");
        }
    }

    /**
     * Method asks user to select table from the list
     * @return name of selected table.
     */
    private static String chooseTable(){
        String result = "";
        System.out.println();
        System.out.println("Выбирите нужную таблицу");
        for(int i = 0; i < Table.saveData.size(); i++){
            System.out.println((i + 1) + ". " + Table.saveData.get(i));
        }

        return Table.saveData.get(ConsoleHelper.readInt(1, Table.saveData.size()) - 1);
    }

}