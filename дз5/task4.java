import java.util.Arrays;

public class task4 {
    public static void main(String[] args) {
        int[] data = {0, 5, 6, 8, 9, 7, 1, 0};

        int r = 0;
        int l = data.length - 1;
        int num = 0;

        while (num <= l) {
            if (data[num] == 0) {
                skip(data, r, num);
                r++;
                num++;
            } else if (data[num] == 2) {
                skip(data, l, num);
                l--;
            } else {
                num++;
            }
        }

        System.out.println(Arrays.toString(data));
    }

    private static void skip(int[] array, int posA, int posB) {
        int temp = array[posA];
        array[posA] = array[posB];
        array[posB] = temp;
    }
}