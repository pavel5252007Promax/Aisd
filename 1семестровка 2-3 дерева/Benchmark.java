import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Benchmark {
    private static final int REPEATS = 30;
    private static final int[] SIZES = {150, 175, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900, 950, 1000, 1250, 1500, 1750, 2000, 2250, 2500, 2750, 3000, 3250, 3500, 3750, 4000, 4250, 4500, 2750, 5000, 5250, 5500, 5750, 6000, 6250, 6500, 6750, 7000, 7250, 7500, 7750, 8000, 8250, 8500, 8750, 9000, 9250, 9500, 9750, 10000};

    public static void main(String[] args) {
        System.out.println("size insertTime_ns searchTime_ns deleteTime_ns");

        for (int size : SIZES) {
            long totalInsert = 0, totalSearch = 0, totalDelete = 0;

            for (int r = 0; r < REPEATS; r++) {
                int[] keys = generateUniqueKeys(size);

                TwoThreeTree tree = new TwoThreeTree();
                long start = System.nanoTime();
                for (int key : keys) {
                    tree.insert(key);
                }
                totalInsert += System.nanoTime() - start;

                int searchKey = keys[size / 2];
                start = System.nanoTime();
                boolean found = tree.search(searchKey);
                totalSearch += System.nanoTime() - start;

                start = System.nanoTime();
                boolean deleted = tree.delete(searchKey);
                totalDelete += System.nanoTime() - start;
            }

            double avgInsert = totalInsert / (double) REPEATS;
            double avgSearch = totalSearch / (double) REPEATS;
            double avgDelete = totalDelete / (double) REPEATS;

            System.out.printf("%d %.2f %.2f %.2f%n", size, avgInsert, avgSearch, avgDelete);
        }
    }

    private static int[] generateUniqueKeys(int n) {
        Random rand = new Random();
        List<Integer> list = new ArrayList<>();
        while (list.size() < n) {
            int key = rand.nextInt(1000000);
            if (!list.contains(key)) {
                list.add(key);
            }
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
