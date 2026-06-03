import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DopWork {
    public static boolean checkNaive(long n) {
    long a = 0;
    long b = 1;
    while (b < n) {
        long c = a + b;
        a = b;
        b = c;
    }
    return b == n;
} public static boolean checkMath(long n) {
        long kv = n * n;
        long a = 5 * kv + 4;
        long s = 5 * kv - 4;
        if (a >= 0) {
            long l = 1;
            long r = 3000000000l;
            while (l <= r) {
                long mid = (l + r) / 2;
                long kvadrt = mid * mid;

                if (kvadrt == a) {
                    return true;
                } else if (kvadrt < a) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }

        if (s >= 0) {
            long l = 1;
            long r = 3000000000L;
            while (l <= r) {
                long mid = (l + r) / 2;
                long kvadrat = mid * mid;
                if (kvadrat == s) {
                    return true;
                } else if (kvadrat < s) {
                    l = mid+1;
                } else {
                    r = mid-1;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        try {
            Scanner myscr = new Scanner(new File("digits"));
            int count = 0;
            while (count < 100) {
                long n = myscr.nextLong();
                long startNaive = System.nanoTime();
                boolean fibNaive = checkNaive(n);
                long endNaive = System.nanoTime();
                long timeNaive = endNaive - startNaive;
                long startMath = System.nanoTime();
                boolean fibMath = checkMath(n);
                long endMath = System.nanoTime();
                long timeMath = endMath - startMath;
                System.out.printf("%-14d %-12d %-12d %-10b %-10b%n", n, timeNaive, timeMath, fibNaive, fibMath);
                count++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("перепроверь файл");
        }
    }




}