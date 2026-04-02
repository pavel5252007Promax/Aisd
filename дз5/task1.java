import java.util.HashSet;
import java.util.Set;

public class task1 {
    public static void main(String[] args) {
        int[] nums = {2, 5, 8, 19};
        int suma = 6;
        Set<Integer> digits = new HashSet<>();

        for (int curr : nums) {
            int count = suma - curr;
            if (digits.contains(count)) {
                System.out.println(count + " " + curr);
                return;
            }

            digits.add(curr);
        }

        System.out.println("пары не нашлось");
    }
}