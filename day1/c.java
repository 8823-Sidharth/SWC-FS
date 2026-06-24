class DNA {

    private StringBuilder sequence;

    public DNA(int size) {
        sequence = new StringBuilder(size);
    }

    public void addData(char[] data) {
        for (char ch : data) {
            sequence.append(ch);
        }
    }

    public void mutate(String oldPart, String newPart) {
        int pos = sequence.indexOf(oldPart);

        if (pos != -1) {
            sequence.replace(pos, pos + oldPart.length(), newPart);
        }
    }

    public void show() {
        System.out.println(sequence);
    }

    public String getDNA() {
        return sequence.toString();
    }
}

public class Main {

    public static void main(String[] args) {

        DNA dna = new DNA(100000);

        char[] data = {
                'A', 'C', 'T', 'G',
                'A', 'A', 'C', 'T',
                'G', 'G'
        };

        dna.addData(data);

        System.out.println("Original DNA:");
        dna.show();

        dna.mutate("AACT", "TTGA");

        System.out.println("\nAfter Mutation:");
        dna.show();
    }
}