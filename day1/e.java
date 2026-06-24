import java.util.concurrent.atomic.AtomicInteger;

class Hive {

    private AtomicInteger returned = new AtomicInteger(0);
    private volatile boolean abort = false;

    public void land() {
        int count = returned.incrementAndGet();

        System.out.println(
                Thread.currentThread().getName()
                        + " landed. Total = " + count
        );
    }

    public void stopFlights() {
        abort = true;
        System.out.println("\nEmergency Activated!");
    }

    public boolean isStopped() {
        return abort;
    }

    public int getCount() {
        return returned.get();
    }
}

public class Main {

    public static void main(String[] args)
            throws InterruptedException {

        Hive hive = new Hive();
        Thread[] drones = new Thread[10];

        for (int i = 0; i < 10; i++) {

            drones[i] = new Thread(() -> {

                try {
                    Thread.sleep((long)(Math.random() * 2000));

                    if (hive.isStopped()) {
                        System.out.println(
                                Thread.currentThread().getName()
                                        + " redirected"
                        );
                        return;
                    }

                    hive.land();

                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

            }, "Drone-" + (i + 1));

            drones[i].start();
        }

        Thread.sleep(1000);
        hive.stopFlights();

        for (Thread d : drones) {
            d.join();
        }

        System.out.println(
                "\nReturned Drones = " + hive.getCount()
        );
    }
}