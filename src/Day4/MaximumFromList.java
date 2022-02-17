package Day4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaximumFromList {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(30);
        list.add(25);
        list.add(19);
        list.add(50);
        list.add(32);
        int key=4;
        System.out.println("First maximum number : " + maximum(list));
        System.out.println("First two maximum numbers : " + secondMax(list));
        System.out.println("Max number " + kMaxNumber(list, key));
    }


    private static List<Integer> kMaxNumber(List<Integer> list, int key) {
        List<Integer> maxlist = new ArrayList<>();
        Collections.reverse(list);
        for (int i = 0; i < key; i++) {
            maxlist.add(list.get(i));
        }
        return maxlist;
    }

    private static List<Integer> secondMax(List<Integer> list1) {
        List<Integer> maxTwo = new ArrayList<>();
        Collections.sort(list1);
        maxTwo.add(list1.get(list1.size() - 1));
        maxTwo.add(list1.get(list1.size() - 2));
        return maxTwo;
    }

    public static Integer maximum(List<Integer> list1) {
        return (Collections.max(list1));
    }
}

