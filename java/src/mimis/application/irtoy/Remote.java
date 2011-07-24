package mimis.application.irtoy;

import java.util.ArrayList;

public class Remote {
    protected String name;
    protected ArrayList<Command> commandList;
    
    public Remote(String name) {
        this.name = name;
        commandList = new ArrayList<Command>();
    }

    public void add(Command command) {
        commandList.add(command);        
    }

    public String getName() {
        return name;
    }

    public Command getCommand(String name) {
        for (Command command : commandList) {
            if (command.getName().equals(name)) {
                return command;
            }
        }
        return null;
    }
}
