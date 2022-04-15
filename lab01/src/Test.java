import java.util.*;
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
    Map<String, Station> stations;

    public Line(String line_id, int capacity, int cur_train, Map<String, Station> stations) {
        this.line_id = line_id;
        this.capacity = capacity;
        this.cur_train = cur_train;
        this.stations = stations;
    }

    @Override
    public String toString() {
        StringBuilder sta_print = new StringBuilder();
        List<Station> station_list = new ArrayList<>(stations.values());
        station_list.sort(new Comparator<>() {
            @Override
            public int compare(Station o1, Station o2) {
                return o1.distance - o2.distance;
            }
        });
        for (Station sta : station_list) {
            sta_print.append(' ').append(sta.name).append(':').append(sta.distance);
        }
        String s = sta_print.toString();
        return line_id + ' ' + cur_train + '/' + capacity  + s;
    }
}

class Train {
    String train_id;
    String line_id;
    class Ticket {
        double price;
        int count;
    }
    Map<String, Ticket> ticketMap;

    public Train(String train_id, String line_id, Map<String, Ticket> ticketMap) {
        this.train_id = train_id;
        this.line_id = line_id;
        this.ticketMap = ticketMap;
    }
    //    double price1;
//    double price2;
//    double price3;
//    int count1;
//    int count2;
//    int count3;
//
//    public Train(String train_id, String line_id, double price1, double price2, double price3, int count1, int count2, int count3) {
//        this.train_id = train_id;
//        this.line_id = line_id;
//        this.price1 = price1;
//        this.price2 = price2;
//        this.price3 = price3;
//        this.count1 = count1;
//        this.count2 = count2;
//        this.count3 = count3;
//    }

//    public Train(String train_id, String line_id, double price1, double price2, int count1, int count2) {
//        this.train_id = train_id;
//        this.line_id = line_id;
//        this.price1 = price1;
//        this.price2 = price2;
//        this.price3 = -9999;
//        this.count1 = count1;
//        this.count2 = count2;
//        this.count3 = -9999;
//    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if (train_id.charAt(0) == 'K') {
            str.append(' ').append("[1A]").append(String.format("%.2f", ticketMap.get("1A").price)).append(':').append(ticketMap.get("1A").count);
            str.append(' ').append("[2A]").append(String.format("%.2f", ticketMap.get("1A").price)).append(':').append(ticketMap.get("1A").count);
        }
        else if (train_id.charAt(0) == '0') {
            str.append(' ').append("[CC]").append(String.format("%.2f", ticketMap.get("CC").price)).append(':').append(count1);
            str.append(' ').append("[SB]").append(String.format("%.2f", ticketMap.get("SB").price)).append(':').append(count2);
            str.append(' ').append("[GG]").append(String.format("%.2f", price3)).append(':').append(count3);
        }
        else if (train_id.charAt(0) == 'G') {
            str.append(' ').append("[SC]").append(String.format("%.2f", price1)).append(':').append(count1);
            str.append(' ').append("[HB]").append(String.format("%.2f", price2)).append(':').append(count2);
            str.append(' ').append("[SB]").append(String.format("%.2f", price3)).append(':').append(count3);
        }
        String s = str.toString();
        return train_id + ':' + ' ' + line_id + s;
    }
}

public class Test {
    public static boolean judgeInt(String num){
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) < '0' || num.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    public static boolean judgeDouble(String num){
        for (int i = 0; i < num.length(); i++) {
            if ((num.charAt(i) < '0' || num.charAt(i) > '9') &&
                num.charAt(i) != '.') {
                return false;
            }
        }
        return true;
    }

    public static boolean judgeTrainId(String train_id) {
        if (train_id.length() != 5) return false;
        for (int i = 1; i < 5; i++) {
            if (train_id.charAt(i) < '0' || train_id.charAt(i) > '9')
                return false;
        }
        return true;
    }

    public static boolean isLegal(String num, char sex) {
        if (num.length() != 12) {
            return false;
        }
        if (!judgeInt(num)) {
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
        boolean flag;

        LinkedList<User> users = new LinkedList<>();
        Map<String, Line> lineMap = new HashMap<>();
        Map<String, Train> trainMap = new HashMap<>();
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

            else if (arr[0].equals("listLine")) {
                if (lineMap.isEmpty()) {
                    System.out.println("No Lines");
                }
                else {
                    List<Line> line_arr = new ArrayList<>(lineMap.values());
//                    for (String key : lineMap.keySet()) {
//                        line_arr.add(lineMap.get(key));
//                    }
                    line_arr.sort(new Comparator<>() {
                        @Override
                        public int compare(Line o1, Line o2) {
                            return o1.line_id.compareTo(o2.line_id);
                        }
                    });
                    line_arr.forEach(System.out::println);
                }
            }

            else if (arr[0].equals("addLine")) {
                if (!is_root) {
                    System.out.println("Command does not exist");
                    continue;
                }
                if (arr.length % 2 == 0) {
                    System.out.println("Arguments illegal");
                    continue;
                }
                flag = false;
                for (int i = 4; i < arr.length; i+=2) {
                    if (!judgeInt(arr[i])) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    System.out.println("Arguments illegal");
                    continue;
                }

                Map<String, Station> stationMap = new HashMap<>();
                for (int i = 3; i < arr.length; i+=2) {
                    if (stationMap.get(arr[i]) != null) {
                        System.out.println("Station duplicate");
                        flag = true;
                        break;
                    }
                    stationMap.put(arr[i], new Station(arr[i], Integer.parseInt(arr[i+1])));
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
                lineMap.put(arr[1], new Line(arr[1], capacity, 0, stationMap));
                System.out.println("Add Line success");
            }

            else if (arr[0].equals("delLine")) {
                if (lineMap.get(arr[1]) == null) {
                    System.out.println("Line does not exist");
                }
                lineMap.remove(arr[1]);
                System.out.println("Del Line success");
            }

            else if (arr[0].equals("addStation")) {
                if (!is_root) {
                    System.out.println("Command does not exist");
                    continue;
                }
                if (lineMap.get(arr[1]) == null) {
                    System.out.println("Line does not exist");
                }
                else {
                    Line line_temp = lineMap.get(arr[1]);
                    if (line_temp.stations.get(arr[2]) != null) {
                        System.out.println("Station duplicate");
                    }
                    else {
                        if (judgeInt(arr[3])) {
                            line_temp.stations.put(arr[2], new Station(arr[2], Integer.parseInt(arr[3])));
                            System.out.println("Add Station success");
                        }
                        else {
                            System.out.println("Arguments illegal");
                        }
                    }
                }
            }

            else if (arr[0].equals("delStation")) {
                if (!is_root) {
                    System.out.println("Command does not exist");
                    continue;
                }
                if (lineMap.get(arr[1]) == null) {
                    System.out.println("Line does not exist");
                }
                else {
                    Line line_temp = lineMap.get(arr[1]);
                    if (line_temp.stations.get(arr[2]) == null) {
                        System.out.println("Station does not exist");
                    }
                    else {
                        line_temp.stations.remove(arr[3]);
                        System.out.println("Delete Station success");
                    }
                }
            }

            else if (arr[0].equals("addTrain")) {
                // 仅管理员模式
                if (!is_root) {
                    System.out.println("Command does not exist");
                    continue;
                }
                if (arr.length != 9 || arr.length != 7) {
                    System.out.println("Arguments illegal");
                }
                else {
                    flag = false;
                    if (arr[0].charAt(0) == 'K') {
                        if (arr.length != 7) {
                            flag = true;
                        }

                    }
                    else if (arr[0].charAt(0) == 'G' || arr[0].charAt(0) == '0') {
                        if (arr.length != 9) {
                            flag = true;
                        }
                    }
                    //该处没判断车次代码不合法的情况

                    if (flag) {
                        System.out.println("Arguments illegal");
                    }
                    else {
                        if (judgeTrainId(arr[1])) {
                            if (trainMap.get(arr[1]) == null) {
                                Line line_insert = lineMap.get(arr[2]);
                                if (line_insert != null &&
                                        line_insert.cur_train < line_insert.capacity) {
                                    flag = false;
                                    for (int i = 3; i < arr.length; i+=2) {
                                        if (!judgeDouble(arr[i])) {
                                            System.out.println("Price illegal");
                                            flag = true;
                                            break;
                                        }
                                        if (!judgeInt(arr[i+1])) {
                                            System.out.println("Ticket num illegal");
                                            flag = true;
                                            break;
                                        }
                                    }
                                    if (!flag) {
                                        if (arr[1].charAt(0) == 'K') {
                                            trainMap.put(arr[1], new Train(arr[1], arr[2],
                                                    Double.parseDouble(arr[3]), Double.parseDouble(arr[5]),
                                                    Integer.parseInt(arr[4]), Integer.parseInt(arr[6])));
                                            lineMap.get(arr[2]).cur_train++;
                                            System.out.println("Add Train Success");
                                        }
                                        else {
                                            trainMap.put(arr[1], new Train(arr[1], arr[2],
                                                    Double.parseDouble(arr[3]), Double.parseDouble(arr[5]), Double.parseDouble(arr[7]),
                                                    Integer.parseInt(arr[4]), Integer.parseInt(arr[6]), Integer.parseInt(arr[8])));
                                            lineMap.get(arr[2]).cur_train++;
                                            System.out.println("Add Train Success");
                                        }
                                    }
                                }
                                else {
                                    System.out.println("Line illegal");
                                }
                            }
                            else if (trainMap.get(arr[1]) != null){
                                System.out.println("Train serial duplicate");
                            }
                        }
                        else {
                            System.out.println("Train serial illegal");
                        }
                    }
                }
            }

            else if (arr[0].equals("delTrain")) {
                // 仅管理员模式
                if (!is_root) {
                    System.out.println("Command does not exist");
                    continue;
                }
                if (trainMap.get(arr[1]) != null) {
                    trainMap.remove(arr[1]);
                    System.out.println("Del Train Success");
                }
                else {
                    System.out.println("DTrain does not exist");
                }
            }

            else if (arr[0].equals("checkTicket")) {
                // 仅标准模式
                if (is_root) {
                    System.out.println("Command does not exist");
                }
                if (judgeTrainId(arr[1])) {
                    Train train_temp = trainMap.get(arr[1]);
                    if (train_temp != null) {
                        Line line_temp = lineMap.get(train_temp.line_id);
                        if (line_temp.stations.get(arr[2]) == null ||
                            line_temp.stations.get(arr[3]) == null)  {
                            System.out.println("Station does not exist");
                        }
                        else {
                            flag = false;
                            if (arr[1].charAt(0) == 'K') {
                                if (arr[4] != "1A" && arr[4] != "2A") {
                                    flag = true;
                                    System.out.println("Seat does not match");
                                }
                            }
                            else if (arr[1].charAt(0) == '0') {
                                if (arr[4] != "CC" && arr[4] != "SB" && arr[4] != "GG") {
                                    flag = true;
                                    System.out.println("Seat does not match");
                                }
                            }
                            else if (arr[1].charAt(0) == 'G') {
                                if (arr[4] != "SC" && arr[4] != "HC" && arr[4] != "SB") {
                                    flag = true;
                                    System.out.println("Seat does not match");
                                }
                            }
                            if (!flag) {
                                StringBuilder str = new StringBuilder();
                                int count;
                                double price;
                                switch (arr[1].charAt(0)) {
                                    case '0' -> {
                                        count = switch (arr[4]) {
                                            case "CC" -> train_temp.count1;
                                            case "SB" -> train_temp.count2;
                                            case "GG" -> train_temp.count3;
                                        };
                                        price = switch (arr[4]) {
                                            case "CC" -> train_temp.price1;
                                            case "SB" -> train_temp.price2;
                                            case "GG" -> train_temp.price3;
                                        };
                                    }
                                    case 'G' -> {
                                        count = switch (arr[4]) {
                                            case "SC" -> train_temp.count1;
                                            case "HC" -> train_temp.count2;
                                            case "SB" -> train_temp.count3;
                                        };
                                        price = switch (arr[4]) {
                                            case "SC" -> train_temp.price1;
                                            case "HC" -> train_temp.price2;
                                            case "SB" -> train_temp.price3;
                                        };
                                    }
                                    case 'K' -> {
                                        count = switch (arr[4]) {
                                            case "1A" -> train_temp.count1;
                                            case "2A" -> train_temp.count2;
                                        };
                                        price = switch (arr[4]) {
                                            case "1A" -> train_temp.price1;
                                            case "2A" -> train_temp.price2;
                                        };
                                    }
                                }
                                str.append('[').append(train_temp.train_id).append(": ").append(arr[2]).append("->").append(arr[3]).append(']');
                                str.append(' ').append("seat:").append(arr[4]);
                                str.append(' ').append("remain:").append(train_temp.count1);
                                System.out.println();
                            }
                        }
                    }
                    else {
                        System.out.println("Train serial does not exist");
                    }
                }
                else {
                    System.out.println("Train serial illegal");
                }

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
