package main;

import command.CommandExecutor;
import command.Operation;
import java.io.IOException;
import other.ConsoleHelper;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        Operation operation;
        
        do {
            operation = ConsoleHelper.askOperation();
            CommandExecutor.execute(operation);
        } while (operation != Operation.EXIT);
    }
}
