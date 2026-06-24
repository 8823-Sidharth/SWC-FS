import java.util.concurrent.atomic.AtomicInteger;

class DroneHive {

    private AtomicInteger returned = new AtomicInteger(0);
    private volatile boolean abort = false;

    public void returnDrone() {
        int count = returned.incrementAndGet();
        System.out.println(Thread.currentThread().getName() +
                " landed. Total Returned = " + count);
    }

    public void activateAbort() {
        abort = true;
        System.out.println("\nEmergency Abort Activated!");
    }

    public boolean isAbortActive() {
        return abort;
    }

    public int getReturnedCount() {
        return returned.get();
    }
}

public class Main {

    public static void main(String[] args) throws InterruptedException {

        DroneHive hive = new DroneHive();
        Thread[] drones = new Thread[10];

        for (int i = 0; i < 10; i++) {

            drones[i] = new Thread(() -> {

                try {
                    Thread.sleep((long) (Math.random() * 2000));

                    if (hive.isAbortActive()) {
                        System.out.println(Thread.currentThread().getName() +
                                " redirected due to storm");
                    } else {
                        hive.returnDrone();
                    }

                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

            }, "Drone-" + (i + 1));

            drones[i].start();
        }

        Thread.sleep(1000);
        hive.activateAbort();

        for (Thread drone : drones) {
            drone.join();
        }

        System.out.println("\nFinal Returned Drones = " +
                hive.getReturnedCount());
    }
}