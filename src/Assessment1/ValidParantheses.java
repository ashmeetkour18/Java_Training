package Assessment1;

import java.util.Stack;

public class ValidParantheses {
    public static void main(String[] args) {
        String s="()[]{}";
        System.out.println(checkValidParantheses(s));

    }

    private static boolean checkValidParantheses(String s) {
        Stack<Character> characters=new Stack<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('||s.charAt(i)=='{'||s.charAt(i)=='[')
                characters.push(s.charAt(i));
            else if(s.charAt(i)==']')
            {
                if (characters.isEmpty() || characters.peek() != '[') {
                    return false;
                }
                characters.pop();
            }
            else if(s.charAt(i)=='}')
            {
                if (characters.isEmpty() || characters.peek() != '{') {
                    return false;
                }
                characters.pop();
            }
            else if(s.charAt(i)==')')
            {
                if (characters.isEmpty() || characters.peek() != '(') {
                    return false;
                }
                characters.pop();
            }
        }
        return characters.size() == 0;
    }
}
