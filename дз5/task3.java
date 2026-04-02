import java.util.Arrays;
import java.util.Comparator;

public class task2 {
    public static void main(String[] args) {
        String[] str = {"пепе", "фа", "щнеле"};

        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String niz = s1.toLowerCase();
                String hight = s2.toLowerCase();
                int len = Math.min(niz.length(), hight.length());
                for (int i = 0; i < len; i++) {
                    char c1 = niz.charAt(i);
                    char c2 = hight.charAt(i);
                    if (c1 != c2) {
                        return c1 - c2;
                    }
                }
                return niz.length() - hight.length();
            }
        });

        for (String s : str) {
            System.out.print(s + "   ");
        }
    }
}