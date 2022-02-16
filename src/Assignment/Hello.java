package Assignment;

public class Hello {

    public static void main(String[] args) {
        String s = args[args.length - 1];
        int length = Integer.parseInt(s);
        String str = "";

        int i;
        for(i = 0; i < args.length - 1; ++i) {
           str= str.concat(args[i]);
            str =str + " ";
        }

        for(i = 1; i <= length; ++i) {
            System.out.println(str);
        }

    }
}

