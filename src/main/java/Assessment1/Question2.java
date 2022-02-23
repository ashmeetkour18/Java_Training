package Assessment1;

import java.util.Arrays;
import java.util.List;

public class Question2 {
    public static void main(String[] args) {
        List<Integer> integerList= Arrays.asList(1,2,3,4,5,6,7);
        int sum=11;
        System.out.println(addUptoSum(integerList,sum));

    }

    private static boolean addUptoSum(List<Integer> integerList, int sum) {
        for(int i=0;i<integerList.size();i++){
            for (int j=0;j<integerList.size();j++){
                if(integerList.get(i)+integerList.get(j)==sum)
                    return true;
            }
        }
        return false;
    }
}
