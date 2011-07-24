package mimis.application.irtoy;

public class Command {
    protected String name;
    protected String data;

    public Command(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }
}
