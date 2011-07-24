package mimis.application.irtoy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mimis.util.Reader;

public class Config {
    public static final String FILE = "C:\\Users\\Rik\\Dropbox\\Gedeeld\\Mimis\\IRToy\\WinLIRC\\remote.txt";

    protected Log log = LogFactory.getLog(getClass());
    protected static Config config;
    protected File file;
    protected ArrayList<Remote> remoteList;

    private Config() {
        file = new File(FILE);
    }

    public static Config getInstance() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public Remote getRemote(String name) {
        if (remoteList == null) {
            remoteList = new ArrayList<Remote>();
            try {
                parse();
            } catch (IOException e) {
                log.error(e);
                return null;
            }
        }
        for (Remote remote : remoteList) {
            if (remote.getName().equals(name)) {
                return remote;
            }
        }
        return null;
    }

    protected void parse() throws IOException {
        String contents = Reader.getContents(file);
        Pattern pattern = Pattern.compile("begin remote(.+?)end remote", Pattern.DOTALL);
        Matcher remoteMatcher = pattern.matcher(contents);
        while (remoteMatcher.find()) {
            pattern = Pattern.compile("begin raw_codes(.+)end raw_codes", Pattern.DOTALL);
            Matcher rawMatcher = pattern.matcher(remoteMatcher.group(1));
            if (rawMatcher.find()) {
                String raw = rawMatcher.group(1);
                pattern = Pattern.compile("name[ ]+(.+)");
                Matcher nameMatcher = pattern.matcher(remoteMatcher.group(1));
                if (nameMatcher.find()) {
                    String name = nameMatcher.group(1);
                    pattern = Pattern.compile("name[ ]+([^\n]+)(.*?)(?=name)", Pattern.DOTALL);
                    Remote remote = new Remote(name);
                    Matcher commandMatcher = pattern.matcher(raw);
                    while (commandMatcher.find()) {
                        String data = commandMatcher.group(2);
                        pattern = Pattern.compile("([0-9]+)");
                        Matcher dataMatcher = pattern.matcher(data);
                        StringBuffer stringBuffer = new StringBuffer();
                        while (dataMatcher.find()) {
                            stringBuffer.append((char) Math.round(Double.valueOf(dataMatcher.group(1)) / 21.3333));
                        }
                        stringBuffer.append(0xffff);
                        Command command = new Command(commandMatcher.group(1), stringBuffer.toString());
                        remote.add(command);
                    }
                    remoteList.add(remote);
                }
            }
        }
    }
}
