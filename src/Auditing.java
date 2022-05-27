import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class Auditing {
    FileWriter csvActions;
    public Auditing() throws IOException {
        this.csvActions = new FileWriter("auditing.csv");
        this.csvActions.append("name_of_action,timestamp\n");
        this.csvActions.flush();
    }

    public void writeAnAction(String nameOfAction) throws IOException {
        this.csvActions.append(nameOfAction);
        this.csvActions.append(",");
        Date date = new Date();
        this.csvActions.append(String.valueOf(new Timestamp(date.getTime())));
        this.csvActions.append("\n");
        this.csvActions.flush();
    }
    public void closeTheFile() throws IOException {
        this.csvActions.close();
    }
}
