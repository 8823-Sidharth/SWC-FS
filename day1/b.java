class PowerGrid {

    private byte sectors = 0;

    public void turnOn(int index) {
        sectors = (byte) (sectors | (1 << index));
    }

    public void turnOff(int index) {
        sectors = (byte) (sectors & ~(1 << index));
    }

    public boolean isOn(int index) {
        return (sectors & (1 << index)) != 0;
    }

    public void show() {
        System.out.print("Sectors: ");
        for (int i = 7; i >= 0; i--) {
            System.out.print((sectors >> i) & 1);
        }
        System.out.println();
    }
}

public class Main {

    public static void main(String[] args) {

        PowerGrid grid = new PowerGrid();

        grid.turnOn(0);
        grid.turnOn(3);
        grid.turnOn(7);

        grid.show();

        System.out.println("Sector 0 ON? " + grid.isOn(0));
        System.out.println("Sector 2 ON? " + grid.isOn(2));
        System.out.println("Sector 7 ON? " + grid.isOn(7));

        grid.turnOff(3);

        grid.show();

        System.out.println("Sector 3 ON? " + grid.isOn(3));
    }
}