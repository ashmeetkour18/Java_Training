package Assessment1;

import java.util.HashMap;

public class AnagramUsingHashMap {
    public static void main(String[] args) {
        String s1="care";
        String s2="race";


        System.out.println(isAnagramHashMap(s1.toLowerCase(),s2.toLowerCase()));

    }
    private static boolean isAnagramHashMap(String s1, String s2) {
        if(s1.length()!=s2.length())
            return false;
        HashMap<Character,Integer> hashMap1=new HashMap<>();
        HashMap<Character,Integer> hashMap2=new HashMap<>();
        for(int i=0;i<s1.length();i++){
            //for checking the character already in hashmap 1 if not then putting it
            //into the hashmap1 with count 1 else incrementing its count by 1;
            if(hashMap1.containsKey(s1.charAt(i)))
                hashMap1.put(s1.charAt(i), (hashMap1.get(s1.charAt(i))+1));
            else
                hashMap1.put(s1.charAt(i),1);

            //for checking the character already in hashmap 2 if not then putting it
            //into the hashmap2 with count 1 else incrementing its count by 1;
            if(hashMap2.containsKey(s2.charAt(i)))

                hashMap2.put(s2.charAt(i),(hashMap2.get(s2.charAt(i))+1));
            else
                hashMap2.put(s2.charAt(i),1);
        }
        return hashMap1.equals(hashMap2);
    }
}
