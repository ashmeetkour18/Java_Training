package Assignment;

    public class Hello {
        public static void main(String[] args) {
            String s=args[args.length-1]; //input number
            int length=Integer.parseInt(s);//casting it into integer from string
            String str="";
            for(int i=0;i< args.length-1;i++)
                str=str.concat(args[i])+" "; //getting the name in a string


            for(int i=1;i<=length;i++)
                System.out.println(str); //printing the name input number times

            }
        }


