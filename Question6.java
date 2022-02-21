package Assessment1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Question6 {
    private static List<Integer> EvenFromList(List<Integer> list) {
        return list.stream().filter(integerList -> integerList % 2 == 0).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> list=Arrays.asList(1,20,15,23,28,32,5,6);
        System.out.println(EvenFromList(list));
    }
}
