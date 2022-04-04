import java.util.Scanner;

public class HuiWenTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        if (isInteger(str)) {
            if (hasZero(str)) {
                System.out.println("有前导零");
            }
            else {
                Integer num = Integer.valueOf(str);
                if (isHuiWen(num)) {
                    System.out.println(num + "是回文数！");
                } else {
                    System.out.println(num + "不是回文数!");
                }
            }
        }
        else {
            System.out.println("不是纯整数");
        }

    }
    public static boolean isInteger(String str) {
        for (int i = 0; i < str.length(); i++) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57)
                return false;
        }
        return true;
    }
    public static boolean hasZero(String str) {
        int len = str.length();
        if (len == 1)   return false;
        if (str.charAt(0) == '0')
            return true;
        return false;
    }

    public static boolean isHuiWen(int n) {
        int m = reverse(n);
        if (m == n) {
            return true;
        } else {
            return false;
        }
    }

    public static int reverse(int n) {
        int temp = 0;// 临时变量
        int j = 0;// 倒置后的数字
        temp = n;// 将输入的数字赋值给临时变量
        while (temp != 0) {
            j = j * 10 + temp % 10;
            temp /= 10;
        }
        return j;
    }

    public static String removeZero(String str) {
        int len = str.length(), i = 0;
        while (i < len && str.charAt(i) == '0') {
            i++;
        }
        return str.substring(i);
    }
}
