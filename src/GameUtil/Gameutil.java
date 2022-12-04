package GameUtil;


public class Gameutil {
    public static boolean isStringDigit(String s)
    {
        if (s.length()==0) return false;
        for (int i=0;i<s.length();i++)
        {
            char ch=s.charAt(i);
            if (!Character.isDigit(ch)) return false;
        }
        return true;
    }

}
