package Assessment1;
public class Question3 {

    public static void fibonacciIterative(long n){
        int n1=1,n2=1,n3;
        long i=0;
        while(i<n){
            n3=n1+n2;
            System.out.print(n1+" ");
            n1=n2;
            n2=n3;
            i++;
        }
    }
    public static long fibonacciRecursive(long n) {
        if(n==1)
            return n;
        else if(n==2)
            return 1;
        else return(fibonacciRecursive(n-1)+fibonacciRecursive(n-2));
    }
    /**
     * Iterative approach will be considered faster approach for finding fibonacci series
     * Because in recursive approach it maintains a stack which at a point will give stackoverflow exception
     * and also it is a time consuming approach.
     */
    public static void main(String[] args) {
        long number= 3L;
        System.out.println("fibonacci series Iterative: ");
       fibonacciIterative(number);
        System.out.println();
        System.out.println("fibonacci series recursive: ");
        for(long i=1;i<=number;i++)
            System.out.print(fibonacciRecursive(i)+" ");
    }
}
