class LockException extends Exception {

    public LockException(String msg) {
        super(msg);
    }
}

class DataException extends RuntimeException {

    public DataException(String msg) {
        super(msg);
    }
}

class Stream implements AutoCloseable {

    public Stream() {
        System.out.println("Stream Opened");
    }

    public void read(String data) throws LockException {

        if (data.equals("LOCKED")) {
            throw new LockException("File is locked.");
        }

        if (data.equals("CORRUPT")) {
            throw new DataException("Invalid sensor data.");
        }

        System.out.println("Data Read: " + data);
    }

    public void close() {
        System.out.println("Stream Closed");
    }
}

public class Main {

    public static void process(String data)
            throws LockException {

        try (Stream s = new Stream()) {
            s.read(data);
        }
    }

    public static void main(String[] args) {

        try {
            process("25C");
        } catch (LockException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println();

        try {
            process("CORRUPT");
        } catch (LockException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (DataException e) {
            System.out.println("Warning: " + e.getMessage());
        }

        System.out.println();

        try {
            process("LOCKED");
        } catch (LockException e) {
            System.out.println("Failure: " + e.getMessage());
        }
    }
}