package Assessment1;

import java.util.Arrays;

public class Question8 {
    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        s1=s1.toLowerCase();
        s2=s2.toLowerCase();
        char[] str1=s1.toCharArray();
        char[] str2=s2.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("Cat","Act"));
    }
}

