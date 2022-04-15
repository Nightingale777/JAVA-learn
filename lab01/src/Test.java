import java.util.LinkedList;
import java.util.Scanner;
import java.lang.*;

class User {
    public static int UserNum = 0;
    private String Name;
    private String Sex;
    private String Aadhaar;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getAadhaar() {
        return Aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        Aadhaar = aadhaar;
    }

    public User(String name, String sex, String aadhaar) {
        Name = name;
        Sex = sex;
        Aadhaar = aadhaar;
    }

    @Override
    public String toString() {
        return "Name:" + Name + '\n' +
                "Sex:" + Sex + '\n' +
                "Aadhaar:" + Aadhaar + '\n';
    }
}

class Line {
    String line_id;
    int capacity;

}

public class Test {
    public static boolean isLegal(String num, char sex) {
        if (num.length() != 12) {
            return false;
        }
        int num1 = Integer.parseInt(num.substring(0, 4));
        int num2 = Integer.parseInt(num.substring(4, 8));
        int num3 = Integer.parseInt(num.substring(8, 11));
        int num4 = Integer.parseInt(num.substring(11));
        if (num1 < 1 || num1 > 1237) {
            return false;
        }
        if (num2 < 20 || num2 > 460) {
            return false;
        }
        if (num3 < 0 || num3 > 100) {
            return false;
        }
        if (num4 != 0 && num4 != 1 && num4 != 2) {
            return false;
        }
        if ((sex == 'F' && num4 != 0) || (sex == 'M' && num4 != 1) || (sex == 'O' && num4 != 2)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean is_root = false;
        boolean arg_ill, name_ill, sex_ill, num_ill, num_exit;
        LinkedList<User> users = new LinkedList<>();
        //User users[] = new User[2000];
        while (true) {
            name_ill = false;
            num_exit = false;
            String argStr = input.nextLine();

            if (argStr.equals("QUIT")) {
                System.out.println("----- Good Bye! -----");
                break;
            }
            if (argStr.equals("TunakTunakTun")) {
                if (is_root) {
                    System.out.println("WanNiBa");
                    continue;
                }
                is_root = true;
                System.out.println("DuluDulu");
                continue;
            }
            if (argStr.equals("NutKanutKanut")) {
                if (!is_root) {
                    System.out.println("WanNiBa");
                    continue;
                }
                is_root = false;
                System.out.println("DaDaDa");
                continue;
            }

            String[] arr = argStr.split("\\s+");

            if (arr[0].equals("allLine")) {

            }

            if (arr[0].equals("addUser")) {
                if (arr.length != 4) {
                    System.out.println("Arguments illegal");
                    continue;
                }

                for (int i = 0; i < arr[1].length(); i++) {
                    char a = arr[1].charAt(i);
                    if (!(Character.isAlphabetic(a) || a == '_')) {
                        name_ill = true;
                        break;
                    }
                }
                if (name_ill) {
                    System.out.println("Name illegal");
                    continue;
                }
                if (!(arr[2].equals("M") || arr[2].equals("F") || arr[2].equals("O"))) {
                    System.out.println("Sex illegal");
                    continue;
                }

                if (!isLegal(arr[3], arr[2].charAt(0))) {
                    System.out.println("Aadhaar number illegal");
                    continue;
                }

                for (User usr : users) {
                    if (arr[3].equals(usr.getAadhaar())) {
                        num_exit = true;
                        break;
                    }
                }
                if (num_exit) {
                    System.out.println("Aadhaar number exist");
                    continue;
                }

                User usr1 = new User(arr[1], arr[2], arr[3]);
                users.add(usr1);
                //users[User.UserNum++] = usr1;

                System.out.println(usr1);
                //System.out.println(User.UserNum);
            }
        }
    }
}
