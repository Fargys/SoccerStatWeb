package command;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    
    private final static Map<Operation, Command> OPERATIONS = new HashMap<>();
    
    static {
        OPERATIONS.put(Operation.CREATE, new CreateCommand());
        OPERATIONS.put(Operation.SHOW, new ShowCommand());
        OPERATIONS.put(Operation.UPDATE, new UpdateCommand());
        OPERATIONS.put(Operation.DELETE, new DeleteCommand());
        OPERATIONS.put(Operation.CLEAR, new ClearCommand());
        OPERATIONS.put(Operation.EXIT, new ExitCommand());
    }

    public static final void execute(Operation operation) {
            OPERATIONS.get(operation).execute();
    }
}