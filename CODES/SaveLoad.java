import java.io.*;
import java.time.Duration;
import java.time.Instant;

public class SaveLoad {
    private time time; // Assuming timer is your class name, adjust if needed

    public SaveLoad(time time) {
        this.time = time;
    }

    public void save() {
        // Create a DataStorage object and set the remainingElapsedTime and windowCloseTime
        SaveLoad.DataStorage data = new SaveLoad.DataStorage();
        data.remainingTimeInMillis = time.elapsedTime;
        data.windowCloseTime = Instant.now();

        // Write the DataStorage object to the file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.ser"))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.ser")))) {
            SaveLoad.DataStorage data = (SaveLoad.DataStorage) ois.readObject();

            if (data.windowCloseTime != null) {
                Duration duration = Duration.between(data.windowCloseTime, Instant.now());
                long durationInMillis = duration.toMillis();
                time.elapsedTime = data.remainingTimeInMillis - durationInMillis;
            } else {
                // Handle the case when windowCloseTime is null, e.g., no data was saved before
                time.elapsedTime = data.remainingTimeInMillis;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static class DataStorage implements Serializable {
        private static final long serialVersionUID = 1L;
        long remainingTimeInMillis;
        Instant windowCloseTime;
    }
}
