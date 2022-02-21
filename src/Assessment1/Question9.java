package Assessment1;

import java.util.ArrayList;
import java.util.List;

public class Question9 {
    public static void main(String[] args) {
        List<Integer> integerList=new ArrayList<>();
        integerList.add(-1);
        integerList.add(5);
        integerList.add(-3);
        integerList.add(-10);
        integerList.add(30);
        integerList.add(-4);
        System.out.println(removeNegative(integerList));
    }

    private static List<Integer> removeNegative(List<Integer> integerList) {
        integerList.removeIf(i -> i < 0);
        return integerList;
    }
}
