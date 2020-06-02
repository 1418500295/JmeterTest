import java.util.regex.Pattern;

public class First {

    public int add(int a, int b){
        return a + b;

    }

    public static void main(String[] args) {
        String s = "肆拾YH玖坊舒服舒服669杀9sfdsggdg三国杀";
        String a = Pattern.compile("[^杀]").matcher(s).replaceAll("");
        System.out.println(a);
    }
}
