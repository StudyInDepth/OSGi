package postgraduatems.web.servlets.faculty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by dotoan on 5/17/14.
 */
public class Test {
    public static void main(String[] args) {
        String s = "";
        String s1 = "     ";
        String pattern = "\\s+";
        String ss = "    a    dfdf   dfd f dfdf  fdf  ";
       // ss = ss.trim();
        ss = ss.replaceAll("\\s+", " ");
        System.out.println(ss);
    }
}
