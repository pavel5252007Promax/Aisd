public class task1 {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,0};
        int target = 10;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > target) {
                System.out.println(i);  
                return;
            }
        }
        System.out.println(arr.length); 
    }
}