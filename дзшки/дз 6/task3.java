public class task3 {
    public static void main(String[] args) {
        String orig = "Pepe popo fa fa khe";
        String reverse = "";
        String curr = "";
        for (int i = 0; i < orig.length(); i++) {
            curr += orig.charAt(i);
            if (orig.charAt(i) == ' ') {
                reverse = curr + reverse;
                curr = "";
            }
            if (i == orig.length() - 1) {
                reverse = curr + " " + reverse;
                curr = "";
            }
        }
        System.out.println(reverse);
    }
}
