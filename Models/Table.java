package Models;

import java.util.List;

public class Table {
    //fields
    private static String name;
    private static List<Team> participants;
    
    //constructor
    public Table() {
    }

    //getters and setters
    public static String getName() {
        return name;
    }
    public static void setName(String name) {
        Table.name = name;
    }
    public static List<Team> getParticipants() {
        return participants;
    }
    public static void setParticipants(List<Team> participants) {
        Table.participants = participants;
    }
    
    
    
}
