import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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

class Station {
    String name;
    int distance;

    public Station(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return name + ' ' + distance + ' ';
    }
}

class Line {
    String line_id;
    int capacity;
    int cur_train;
    LinkedList<Station> stations;

    public Line(String line_id, int capacity, int cur_train, LinkedList<Station> stations) {
        this.line_id = line_id;
        this.capacity = capacity;
        this.cur_train = cur_train;
        this.stations = stations;
    }

    @Override
    public String toString() {
        return line_id + ' ' + cur_train + '/' + capacity + ' ' +
                stations;
    }
}

public class Test {
    public static boolean isLegal(String num, char sex) {
        if (num.length() != 12) {
            return false;
        }
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) < '0' || num.charAt(i) > '9') {
                return false;
            }
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
        boolean flag;

        LinkedList<User> users = new LinkedList<>();
        Map<String, Line> lineMap = new HashMap<String, Line>();
        //User users[] = new User[2000];
        while (true) {
            name_ill = false;
            num_exit = false;
            String argStr = input.nextLine();
            String[] arr = argStr.split("\\s+");

            if (argStr.equals("QUIT")) {
                System.out.println("----- Good Bye! -----");
                break;
            }
            else if (argStr.equals("TunakTunakTun")) {
                if (is_root) {
                    System.out.println("WanNiBa");
                    continue;
                }
                is_root = true;
                System.out.println("DuluDulu");
            }
            else if (argStr.equals("NutKanutKanut")) {
                if (!is_root) {
                    System.out.println("WanNiBa");
                    continue;
                }
                is_root = false;
                System.out.println("DaDaDa");
            }

            else if (arr[0].equals("lineInfo")) {
                if (lineMap.get(arr[1]) == null) {
                    System.out.println("Line does not exist");
                }
                System.out.println(lineMap.get(arr[1]));
            }

            else if (arr[0].equals("addLine")) {
                if (arr.length % 2 == 0) {
                    System.out.println("Arguments illegal");
                    continue;
                }
                flag = false;
                for (int i = 4; i < arr.length; i+=2) {
                    for (int j = 0; j < arr[i].length(); j++) {
                        if (arr[i].charAt(j) < '0' || arr[i].charAt(j) > '9') {
                            System.out.println("Arguments illegal");
                            flag = true;
                            break;
                        }
                    }
                    if (flag) break;
                }
                if (flag) continue;

                Map<String, Boolean> station_duplicate = new HashMap<String, Boolean>();
                for (int i = 3; i < arr.length; i+=2) {
                    if (station_duplicate.get(arr[i]) != null) {
                        System.out.println("Station duplicate");
                        flag = true;
                        break;
                    }
                    station_duplicate.put(arr[i], true);
                }
                if (flag) continue;
                if (lineMap.get(arr[1]) != null) {
                    System.out.println("Line already exists");
                    continue;
                }
                int capacity = Integer.parseInt(arr[2]);
                if (capacity <= 0) {
                    System.out.println("Capacity illegal");
                    continue;
                }
                LinkedList<Station> stations = new LinkedList<>();
                for (int i = 3; i < arr.length; i+=2) {
                    stations.add(new Station(arr[i], Integer.parseInt(arr[i+1])));
                }
                lineMap.put(arr[1], new Line(arr[1], capacity, 0, stations));
                System.out.println("Add Line success");
            }

            else if (arr[0].equals("delLine")) {
                if (lineMap.get(arr[1]) == null) {
                    System.out.println("Line does not exist");
                }
                lineMap.remove(arr[1]);
                System.out.println("Del Line success");
            }

            else if (arr[0].equals("addUser")) {
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
            else {
                System.out.println("Command does not exist");
            }
        }
    }
}
