public class task3 {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 6, 7, 7, 99};
        int n = 6;
        int r = arr.length - 1;
        int l = 0;
        while (l <= r) {
            if (arr[l] + arr[r] == n) {
                System.out.println(arr[l] + " " + arr[r]);
                break;
            } else if (arr[l] + arr[r] <= n) {
                l++;
            } else if (arr[l] + arr[r] >= n) {
                r--;
            }
        }
    }
}
