public static Integer task6(int[] arr1, int[] arr2, int[] arr3) {
    Set<Integer> set1 = new HashSet<>();
    Set<Integer> set2 = new HashSet<>();
    for (int num : arr1) {
        set1.add(num);
    }
    for (int num : arr2) {
        set2.add(num);
    }
    for (int num : arr3) {
        if (set1.contains(num) && set2.contains(num)) {
            return num;
        }
    }
    return null;
}