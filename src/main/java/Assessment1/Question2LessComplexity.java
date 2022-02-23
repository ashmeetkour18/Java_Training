package Assessment1;

import java.util.Arrays;
import java.util.List;

public class Question2LessComplexity {
    public static void main(String[] args) {
        List<Integer> integerList= Arrays.asList(1,2,3,4,5,6,7,7);
        int sum=14;
        System.out.println(addSum(integerList,sum));
    }

    private static boolean addSum(List<Integer> integerList, int sum) {
        for(int i=0;i<integerList.size();i++){
            int number= sum-integerList.get(i);
            if(integerList.contains(number)&& integerList.indexOf(number)!=i)
                return true;
        }
        return false;

    }
}
