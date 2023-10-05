package entities;

import java.util.List;
import java.util.ArrayList;
public class Archive {
    private static Archive instance;
    private List<Record> records;

    private Archive() {
        records = new ArrayList<>();
    }

    public static Archive getInstance() {
        if (instance == null) {
            synchronized (Archive.class) {
                if (instance == null) {
                    instance = new Archive();
                }
            }
        }
        return instance;
    }

    public void addRecord(Record record) {
        records.add(record);
    }

    public List<Record> getRecords() {
        return records;
    }
}
