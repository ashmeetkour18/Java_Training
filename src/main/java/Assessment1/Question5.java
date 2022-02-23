package Assessment1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question5 {

    public static void main(String[] args) {
List<Integer> integerList= Arrays.asList(1,30,25,49,4,37,20,34);
int key=4;
        System.out.println(getTopKMax(integerList,key));
    }
    private static List<Integer> getTopKMax(List<Integer> list, int key) {
        List<Integer> maxlist = new ArrayList<>();
        Collections.sort(list);

        for (int i = list.size()-1; i >=key; i--) {
            maxlist.add(list.get(i));

        }
        return maxlist;
    }

}
