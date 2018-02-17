package command;

public enum Operation {
    
    CREATE,
    DELETE,
    SHOW,
    UPDATE,
    CLEAR,
    EXIT;

    public static Operation getOperation(Integer i) {
        
        switch(i) {
            case 0: throw new IllegalArgumentException();
            
            case 1: return CREATE;
            case 2: return DELETE;
            case 3: return SHOW;
            case 4: return UPDATE;
            case 5: return CLEAR;
            case 6: return EXIT;
            
            default: throw new IllegalArgumentException();
        }
    }
}