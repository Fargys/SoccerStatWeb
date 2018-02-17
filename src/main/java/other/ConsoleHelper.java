package other;

import command.Operation;
import controllers.TeamController;
import static controllers.TeamController.POINTS_FOR_WIN;
import static controllers.TeamController.POINTS_FOR_DRAW;
import static controllers.TeamController.POINTS_FOR_LOSS;
import entity.Championship;
import entity.Team;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper implements Closeable {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    public static final int MIN_POSITIVE_VALUE = 1;
    public static final int MAX_PARTICIPANTS = 24;
    public static final int MAX_GOALS = 25;

    public static Operation askOperation() {
        int userSelection = ConsoleHelper.selectAction();
        
        return Operation.getOperation(userSelection);
}
    
    public static int selectAction() {
        writeEmptyLine();
        writeMessage("\t" + "Select an action.");
        writeMessage("1. Create.");
        writeMessage("2. Delete.");
        writeMessage("3. Show.");
        writeMessage("4. Update.");
        writeMessage("5. Clear.");
        writeMessage("6. Exit.");
        writeEmptyLine();

        return readOnlyValidInt(1, 6);
    }
    
    public static void printChampionship(String champName, List<Team> teamsForPrinting) {
        writeEmptyLine();
        writeMessage("\t" + champName);
        
        for(int i = 0; i < teamsForPrinting.size(); i++){
            Team teamForPrinting = teamsForPrinting.get(i);
            
            writeMessageWithoutTransitionToNewLine(i + 1 + ". "); TeamController.print(teamForPrinting);
        }
        
        writeEmptyLine();
    }
    
    public static Boolean chosenActionIsChampionship() {
        writeEmptyLine();
        writeMessage("\t" + "Select the object on which you want to perform the selected action:");
        writeMessage("1. Championship.");
        writeMessage("2. Team.");
        writeEmptyLine();
        
        int choosenAction = readOnlyValidInt(1, 2);
        return choosenAction == 1;
    }
    
    
    public static String getChampionshipName(){
        writeEmptyLine();
        writeMessage("Enter name of championship:");
        writeEmptyLine();
        return readString();
    }
    
    public static int getNumberOfParticipants(){
        writeEmptyLine();
        writeMessage("Enter numbers of participants of championship:");
        writeEmptyLine();
        return readOnlyPositiveInt();
    }
    
    public static List<String> getParticipantsNames(int numberOfTeams) {
        
        if(numberOfTeams > MAX_PARTICIPANTS) {
            
            writeEmptyLine();
            writeMessage("You can't create new championship which has more participants, than 24.");
            
            numberOfTeams = MAX_PARTICIPANTS;
        }
        
        writeEmptyLine();
        writeMessage("Enter the names of participants:");
        writeEmptyLine();
        
        List<String> result = new ArrayList<>();
        
        for(int i = 0; i < numberOfTeams; i++) {
            
            String teamName = readString();
            
            while(result.contains(teamName)) {
                writeEmptyLine();
                writeMessage("This name has already been added. Choose another name");
                writeEmptyLine();
                teamName = readString();
            }
            
            result.add(teamName);
        }
        
        return result;
    }
    
    public static Championship getSelectedChampionship(List<Championship> champs) {
        writeEmptyLine();
        writeMessage("Select championship:");
        
        for(int i = 0; i < champs.size(); i++){
            writeMessage(i + 1 + ". " + champs.get(i).getName());
        }
        
        writeEmptyLine();
        
        int choice = readOnlyValidInt(1, champs.size());
        return champs.get(choice - 1);
    } 
    
    public static Team getSelectedTeam(List<Team> teams) {
        writeEmptyLine();
        writeMessage("Select team:");
        
        for(int i = 0; i < teams.size(); i++){
            writeMessageWithoutTransitionToNewLine(i + 1 + ". "); TeamController.print(teams.get(i));
        }
        writeEmptyLine();
        
        int choice = readOnlyValidInt(1, teams.size());
        return teams.get(choice - 1);
    }  
    
    public static Score getScore() {
        writeEmptyLine();
        writeMessage("Enter quantity of goals of home team:");
        writeEmptyLine();
        int homeTeamGoals = readOnlyValidInt(0, MAX_GOALS);
        
        writeEmptyLine();
        writeMessage("Enter quantity of goals of guest team:");
        writeEmptyLine();
        int guestTeamGoals = readOnlyValidInt(0, MAX_GOALS);
        
        return new Score(homeTeamGoals, guestTeamGoals);
    }
    
    public static Team setNewDataForTeam(Team teamForUpdating) {
        
        writeEmptyLine();
        writeMessage("Enter new value for games:");
        writeEmptyLine();
        int newGames = readOnlyValidInt(0, Integer.MAX_VALUE);
        
        writeEmptyLine();
        writeMessage("Enter new value for wins:");
        writeEmptyLine();
        int newWins = readOnlyValidInt(0, newGames);
        
        int newDraws;
        if(newGames == newWins) newDraws = 0;
        else {
            writeEmptyLine();
            writeMessage("Enter new value for draws:");
            writeEmptyLine();
            newDraws = readOnlyValidInt(0, (newGames - newWins) );
        }
        
        
        int newLosses = newGames - newWins - newDraws;
        
        int newPoints = ( (newWins * POINTS_FOR_WIN) + (newDraws * POINTS_FOR_DRAW) + (newLosses * POINTS_FOR_LOSS) );
        
        writeEmptyLine();
        writeMessage("Enter new value for scored:");
        writeEmptyLine();
        int newScored = readOnlyValidInt(0, Integer.MAX_VALUE);
        
        writeEmptyLine();
        writeMessage("Enter new value for missed:");
        writeEmptyLine();
        int newMissed = readOnlyValidInt(0, Integer.MAX_VALUE);
        
        Team result = teamForUpdating;
        
        result.setGames(newGames);
        result.setWins(newWins);
        result.setDraws(newDraws);
        result.setLosses(newLosses);
        result.setPoints(newPoints);
        result.setScored(newScored);
        result.setMissed(newMissed);
        
        return result;
    }
    
    
    private static int readOnlyValidInt(int from, int to) {
        while (true) {
            try {
                int result = Integer.parseInt(reader.readLine());
                if (result < from || result > to) throw new IllegalArgumentException();
                return result;
            } catch (IOException e) {
                // do nothing
            } catch (IllegalArgumentException e) {
                writeEmptyLine();
                writeMessage("Invalid data. Try entering data again:");
                writeEmptyLine();
            }
        }
    }
    
    private static int readOnlyPositiveInt() {
        while (true) {
            try {
                int result = Integer.parseInt(reader.readLine());
                if(result < MIN_POSITIVE_VALUE) throw new IllegalArgumentException();
                return result;
            } catch (IOException e) {
                // do nothing
            } catch (IllegalArgumentException e) {
                writeEmptyLine();
                writeMessage("Invalid data. Try entering data again:");
                writeEmptyLine();
            }
        }
    }
    
    public static String readString() {
        while (true) {
            try {
                String result = (String) reader.readLine();
                if(result.equals("")) throw new IllegalArgumentException();
                return formatString(result);
            } catch (IOException e) {
                // do nothing
            } catch (IllegalArgumentException e) {
                writeMessage("Invalid data. Try entering data again");
                writeEmptyLine();
            }
        }
    }
    
    /**
     * The method for bringing a string to a common view
     * 
     * @param str is string for formatting
     * @return formatted string
     */
    public static String formatString(String str) {
        String lowerCaseString = str.toLowerCase();
        
        char[] array = lowerCaseString.toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        
        return new String(array);
    }
    
    
    public static void writeEmptyLine() {
        System.out.println();
    }
    
    public static void writeMessage(String message) {
        System.out.println(message);
    }
    
    public static void writeMessageWithoutTransitionToNewLine(String message) {
        System.out.print(message);
    }
    
    public static void writeGoodBye() {
        writeEmptyLine();
        writeMessage("Goodbye!");
    }
    
    @Override
    public void close() throws IOException {
        reader.close();
    }
}

