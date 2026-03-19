public class TASK_2 {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2,3,  4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 20, 21, 22}; // пропустили 16
        int first = arr[0];
        int len = arr.length + 1;
        int last = first + len - 1;
        int sumaDigits = 0;
        int arrSum = 0;
        for (int i = first; i <= last; i++) {
            sumaDigits += i;
        }
        for (int i : arr) {
            arrSum += i;
        }
        System.out.println(sumaDigits - arrSum);
    }
}
