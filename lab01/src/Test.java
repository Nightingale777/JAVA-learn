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
        station_list.sort(Comparator.comparingInt(o -> o.distance));
        for (Station sta : station_list) sta_print.append(' ').append(sta.name).append(':').append(sta.distance);
        String s = sta_print.toString();
        return line_id + ' ' + cur_train + '/' + capacity  + s;
    }
}

class Train {
    String train_id;
    String line_id;
    static class Ticket {
        double price;
        int count;

        public Ticket(double price, int count) {
            this.price = price;
            this.count = count;
        }
    }
    Map<String, Ticket> ticketMap;

    public Train(String train_id, String line_id, Map<String, Ticket> ticketMap) {
        this.train_id = train_id;
        this.line_id = line_id;
        this.ticketMap = ticketMap;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String, Train.Ticket> entry : ticketMap.entrySet()) {
            str.append(' ').append('[').append(entry.getKey()).append(']');
            str.append(String.format("%.2f", entry.getValue().price));
            str.append(entry.getValue().count);
        }

        if (train_id.charAt(0) == 'K') {
            str.append(' ').append("[1A]").append(String.format("%.2f", ticketMap.get("1A").price)).append(':').append(ticketMap.get("1A").count);
            str.append(' ').append("[2A]").append(String.format("%.2f", ticketMap.get("2A").price)).append(':').append(ticketMap.get("2A").count);
        }
        else if (train_id.charAt(0) == '0') {
            str.append(' ').append("[CC]").append(String.format("%.2f", ticketMap.get("CC").price)).append(':').append(ticketMap.get("CC").count);
            str.append(' ').append("[SB]").append(String.format("%.2f", ticketMap.get("SB").price)).append(':').append(ticketMap.get("SB").count);
            str.append(' ').append("[GG]").append(String.format("%.2f", ticketMap.get("GG").price)).append(':').append(ticketMap.get("GG").count);
        }
        else if (train_id.charAt(0) == 'G') {
            str.append(' ').append("[SC]").append(String.format("%.2f", ticketMap.get("SC").price)).append(':').append(ticketMap.get("SC").count);
            str.append(' ').append("[HC]").append(String.format("%.2f", ticketMap.get("HC").price)).append(':').append(ticketMap.get("HC").count);
            str.append(' ').append("[SB]").append(String.format("%.2f", ticketMap.get("SB").price)).append(':').append(ticketMap.get("SB").count);
        }
        String s = str.toString();
        return train_id + ':' + ' ' + line_id + s;
    }
}

public class Test {
    public static boolean judgeInt(String num){
        for (int i = 0; i < num.length(); i++)
            if (num.charAt(i) < '0' || num.charAt(i) > '9') return false;
        return true;
    }

    public static boolean judgeDouble(String num){
        for (int i = 0; i < num.length(); i++)
            if ((num.charAt(i) < '0' || num.charAt(i) > '9') &&
                    num.charAt(i) != '.') return false;
        return true;
    }

    public static boolean judgeTrainId(String train_id) {
        if (train_id.length() != 5) return false;
        char head = train_id.charAt(0);
        if (head != 'G' && head != 'K' && head != '0') return false;
        for (int i = 1; i < 5; i++)
            if (train_id.charAt(i) < '0' || train_id.charAt(i) > '9')
                return false;
        return true;
    }

    public static boolean isLegal(String num, char sex) {
        if (num.length() != 12) return false;
        if (!judgeInt(num)) return false;
        int num1 = Integer.parseInt(num.substring(0, 4));
        int num2 = Integer.parseInt(num.substring(4, 8));
        int num3 = Integer.parseInt(num.substring(8, 11));
        int num4 = Integer.parseInt(num.substring(11));
        if (num1 < 1 || num1 > 1237) return false;
        if (num2 < 20 || num2 > 460) return false;
        if (num3 < 0 || num3 > 100) return false;
        if (num4 != 0 && num4 != 1 && num4 != 2) return false;
        if ((sex == 'F' && num4 != 0) || (sex == 'M' && num4 != 1) || (sex == 'O' && num4 != 2)) return false;
        return true;
    }
    public static boolean isLegal(String num) {
        if (num.length() != 12) return false;
        if (!judgeInt(num)) return false;
        int num1 = Integer.parseInt(num.substring(0, 4));
        int num2 = Integer.parseInt(num.substring(4, 8));
        int num3 = Integer.parseInt(num.substring(8, 11));
        int num4 = Integer.parseInt(num.substring(11));
        if (num1 < 1 || num1 > 1237) return false;
        if (num2 < 20 || num2 > 460) return false;
        if (num3 < 0 || num3 > 100) return false;
        if (num4 != 0 && num4 != 1 && num4 != 2) return false;
        return true;
    }

    private static void TrainSort(ArrayList<Train> trainArrayList) {
        trainArrayList.sort((o1, o2) -> {
            if (o1.train_id.charAt(0) == o2.train_id.charAt(0)) {
                return o1.train_id.substring(1).compareTo(o2.train_id.substring(1));
            }
            else
                return o2.train_id.charAt(0) - o1.train_id.charAt(0);
        });
    }

    public static void PrintListTrain(ArrayList<Train> trainArrayList) {
        TrainSort(trainArrayList);
        for (int i = 0; i < trainArrayList.size(); i++) {
            System.out.println("[" + (i + 1) + ']' + ' ' + trainArrayList.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean is_root = false;
        boolean is_login = false;
        boolean flag;

        //LinkedList<User> users = new LinkedList<>();
        Map<String, User> userMap = new HashMap<>();
        Map<String, Line> lineMap = new HashMap<>();
        Map<String, Train> trainMap = new HashMap<>();
        //User users[] = new User[2000];
        while (true) {
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
                if (arr.length != 1) {
                    System.out.println("Arguments illegal");
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
                if (arr.length != 1) {
                    System.out.println("Arguments illegal");
                    continue;
                }
                is_root = false;
                System.out.println("DaDaDa");
            }
            else if (arr[0].equals("lineInfo")) {
                if (arr.length != 2) {
                    System.out.println("Arguments illegal");
                    continue;
                }
                if (lineMap.get(arr[1]) == null) {
                    System.out.println("Line does not exist");
                    continue;
                }
                System.out.println(lineMap.get(arr[1]));
            }
            else if (arr[0].equals("listLine")) {
                if (arr.length != 1) {
                    System.out.println("Arguments illegal");
                    continue;
                }
                if (lineMap.isEmpty()) System.out.println("No Lines");
                else {
                    List<Line> line_arr = new ArrayList<>(lineMap.values());
                    line_arr.sort((o1, o2) -> o1.line_id.compareTo(o2.line_id));
                    for (int i = 0; i < line_arr.size(); i++) {
                        System.out.println("[" + (i + 1) + ']' + ' ' + line_arr.get(i));
                    }
                    //line_arr.forEach(System.out::println);
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
                if (arr.length < 5) {
                    System.out.println("Arguments illegal");
                    continue;
                }
                flag = false;
                for (int i = 4; i < arr.length; i += 2)
                    if (!judgeInt(arr[i])) {
                        flag = true;
                        break;
                    }
                if (flag) {
                    System.out.println("Arguments illegal");
                    continue;
                }

                Map<String, Station> stationMap = new HashMap<>();
                for (int i = 3; i < arr.length; i += 2) {
                    if (stationMap.get(arr[i]) != null) {
                        System.out.println("Station duplicate");
                        flag = true;
                        break;
                    }
                    stationMap.put(arr[i], new Station(arr[i], Integer.parseInt(arr[i + 1])));
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
                if (arr.length != 2) {
                    System.out.println("Arguments illegal");
                    continue;
                }
                if (lineMap.get(arr[1]) == null)
                {
                    System.out.println("Line does not exist");
                    continue;
                }
                lineMap.remove(arr[1]);
                System.out.println("Del Line success");
            }
            else if (arr[0].equals("addStation")) {
                if (!is_root) {
                    System.out.println("Command does not exist");
                    continue;
                }
                if (arr.length != 4) {
                    System.out.println("Arguments illegal");
                    continue;
                }
                if (lineMap.get(arr[1]) == null) System.out.println("Line does not exist");
                else {
                    Line line_temp = lineMap.get(arr[1]);
                    if (line_temp.stations.get(arr[2]) != null) System.out.println("Station duplicate");
                    else if (judgeInt(arr[3])) {
                        line_temp.stations.put(arr[2], new Station(arr[2], Integer.parseInt(arr[3])));
                        System.out.println("Add Station success");
                    } else System.out.println("Arguments illegal");
                }
            }
            else if (arr[0].equals("delStation")) {
                if (!is_root) {
                    System.out.println("Command does not exist");
                    continue;
                }
                if (arr.length != 3) {
                    System.out.println("Arguments illegal");
                    continue;
                }
                if (lineMap.get(arr[1]) == null) System.out.println("Line does not exist");
                else {
                    Line line_temp = lineMap.get(arr[1]);
                    if (line_temp.stations.get(arr[2]) == null) System.out.println("Station does not exist");
                    else {
                        line_temp.stations.remove(arr[2]);
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
                if (arr.length != 9 && arr.length != 7) {
                    System.out.println("Arguments illegal");
                    continue;
                }
                flag = false;
                if (arr[1].charAt(0) == 'K') {
                    if (arr.length != 7) flag = true;
                }
                else if (arr[1].charAt(0) == 'G' || arr[1].charAt(0) == '0')
                    if (arr.length != 9) flag = true;
                //该处没判断车次代码不合法的情况

                if (flag) {
                    System.out.println("Arguments illegal");
                    continue;
                }
                if (judgeTrainId(arr[1])) {
                    if (trainMap.get(arr[1]) != null) {
                        System.out.println("Train serial duplicate");
                        continue;
                    }
                    Line line_insert = lineMap.get(arr[2]);
                    if (line_insert != null &&
                            line_insert.cur_train < line_insert.capacity) {
                        for (int i = 3; i < arr.length; i += 2) {
                            if (!judgeDouble(arr[i])) {
                                System.out.println("Price illegal");
                                flag = true;
                                break;
                            }
                            if (!judgeInt(arr[i + 1])) {
                                System.out.println("Ticket num illegal");
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) if (arr[1].charAt(0) == 'K') {
                            Map<String, Train.Ticket> ticketMap = new HashMap<>();
                            ticketMap.put("1A", new Train.Ticket(Double.parseDouble(arr[3]),
                                    Integer.parseInt(arr[4])));
                            ticketMap.put("2A", new Train.Ticket(Double.parseDouble(arr[5]),
                                    Integer.parseInt(arr[6])));
                            Train train_temp = new Train(arr[1], arr[2], ticketMap);
                            trainMap.put(arr[1], train_temp);
                            line_insert.cur_train++;
                            System.out.println("Add Train Success");
                        } else if (arr[1].charAt(0) == '0') {
                            Map<String, Train.Ticket> ticketMap = new HashMap<>();
                            ticketMap.put("CC", new Train.Ticket(Double.parseDouble(arr[3]),
                                    Integer.parseInt(arr[4])));
                            ticketMap.put("SB", new Train.Ticket(Double.parseDouble(arr[5]),
                                    Integer.parseInt(arr[6])));
                            ticketMap.put("GG", new Train.Ticket(Double.parseDouble(arr[7]),
                                    Integer.parseInt(arr[8])));
                            Train train_temp = new Train(arr[1], arr[2], ticketMap);
                            trainMap.put(arr[1], train_temp);
                            line_insert.cur_train++;
                            System.out.println("Add Train Success");
                        } else if (arr[1].charAt(0) == 'G') {
                            Map<String, Train.Ticket> ticketMap = new HashMap<>();
                            ticketMap.put("SC", new Train.Ticket(Double.parseDouble(arr[3]),
                                    Integer.parseInt(arr[4])));
                            ticketMap.put("HC", new Train.Ticket(Double.parseDouble(arr[5]),
                                    Integer.parseInt(arr[6])));
                            ticketMap.put("SB", new Train.Ticket(Double.parseDouble(arr[7]),
                                    Integer.parseInt(arr[8])));
                            Train train_temp = new Train(arr[1], arr[2], ticketMap);
                            trainMap.put(arr[1], train_temp);
                            line_insert.cur_train++;
                            System.out.println("Add Train Success");
                        }
                    }
                    else System.out.println("Line illegal");
                }
                else System.out.println("Train serial illegal");
            }
            else if (arr[0].equals("delTrain")) {
                // 仅管理员模式
                if (!is_root) {
                    System.out.println("Command does not exist");
                    continue;
                }
                if (arr.length != 2) {
                    System.out.println("Arguments illegal");
                    continue;
                }
                Train train_del = trainMap.get(arr[1]);
                if (train_del != null) {
                    trainMap.remove(arr[1]);
                    lineMap.get(train_del.line_id).cur_train--;
                    System.out.println("Del Train Success");
                } else System.out.println("Train does not exist");
            }
            else if (arr[0].equals("checkTicket")) {
                // 仅标准模式
                if (is_root)
                {
                    System.out.println("Command does not exist");
                    continue;
                }
                if (arr.length != 5) {
                    System.out.println("Arguments illegal");
                    continue;
                }
                if (judgeTrainId(arr[1])) {
                    Train train_temp = trainMap.get(arr[1]);
                    if (train_temp != null) {
                        Line line_temp = lineMap.get(train_temp.line_id);
                        if (line_temp.stations.get(arr[2]) == null ||
                                line_temp.stations.get(arr[3]) == null) System.out.println("Station does not exist");
                        else {
                            flag = false;
                            if (arr[1].charAt(0) == 'K') {
                                if (!Objects.equals(arr[4], "1A") && !Objects.equals(arr[4], "2A")) {
                                    flag = true;
                                    System.out.println("Seat does not match");
                                }
                            } else if (arr[1].charAt(0) == '0') {
                                if (!Objects.equals(arr[4], "CC") && !Objects.equals(arr[4], "SB") && !Objects.equals(arr[4], "GG")) {
                                    flag = true;
                                    System.out.println("Seat does not match");
                                }
                            } else if (arr[1].charAt(0) == 'G')
                                if (!Objects.equals(arr[4], "SC") && !Objects.equals(arr[4], "HC") && !Objects.equals(arr[4], "SB")) {
                                    flag = true;
                                    System.out.println("Seat does not match");
                                }
                            if (!flag) {
                                StringBuilder str = new StringBuilder();
                                int count;
                                double price;
                                int distance;
                                count = train_temp.ticketMap.get(arr[4]).count;
                                price = train_temp.ticketMap.get(arr[4]).price;
                                distance = Math.abs(line_temp.stations.get(arr[2]).distance - line_temp.stations.get(arr[3]).distance);
                                str.append('[').append(train_temp.train_id).append(": ").append(arr[2]).append("->").append(arr[3]).append(']');
                                str.append(' ').append("seat:").append(arr[4]);
                                str.append(' ').append("remain:").append(count);
                                str.append(' ').append("distance:").append(distance);
                                str.append(' ').append("price:").append(String.format("%.2f", distance * price));
                                System.out.println(str);
                            }
                        }
                    } else System.out.println("Train serial does not exist");
                } else System.out.println("Train serial illegal");

            }
            else if (arr[0].equals("listTrain")) {
                if (trainMap.isEmpty()) {
                    System.out.println("No Trains");
                    continue;
                }
                if (arr.length != 1 && arr.length != 2) {
                    System.out.println("Arguments illegal");
                    continue;
                }
                if (arr.length == 1) {
                    ArrayList<Train> trainArrayList = new ArrayList<>(trainMap.values());
                    PrintListTrain(trainArrayList);
                } else {
                    Line line_temp = lineMap.get(arr[1]);
                    if (line_temp == null) {
                        System.out.println("Line does not exist");
                    } else {
                        ArrayList<Train> trainArrayList = new ArrayList<>();
                        for (Map.Entry<String, Train> trainEntry : trainMap.entrySet()) {
                            if (trainEntry.getValue().line_id.equals(line_temp.line_id)) {
                                trainArrayList.add(trainEntry.getValue());
                            }
                        }
                        if (trainArrayList.isEmpty()) {
                            System.out.println("No Trains");
                        } else {
                            PrintListTrain(trainArrayList);
                        }
                    }
                }
            }
            else if (arr[0].equals("addUser")) {
                if (arr.length != 4) {
                    System.out.println("Arguments illegal");
                    continue;
                }
                flag = false;
                for (int i = 0; i < arr[1].length(); i++) {
                    char a = arr[1].charAt(i);
                    if (!(Character.isAlphabetic(a) || a == '_')) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
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

                if (userMap.get(arr[3]) != null) {
                    System.out.println("Aadhaar number exist");
                    continue;
                }
                User user_temp = new User(arr[1], arr[2], arr[3]);
                userMap.put(arr[3], user_temp);
                System.out.println(user_temp);
            }
            else if (arr[0].equals("login")) {
                if (arr.length != 3) {
                    System.out.println("Arguments illegal");
                    continue;
                }
                if (is_login) {
                    System.out.println("You have logged in");
                    continue;
                }
                if (!isLegal(arr[1]) || userMap.get(arr[1]) == null) {
                    System.out.println("User does not exist");
                    continue;
                }
                if (!userMap.get(arr[1]).equals(arr[2])) {
                    System.out.println("Wrong name");
                    continue;
                }
                is_login = true;
                System.out.println("Login success");
            }
            else if (arr[0].equals("logout")) {
                if (arr.length != 1) {
                    System.out.println("Arguments illegal");
                    continue;
                }
                if (!is_login) {
                    System.out.println("No user has logged in");
                    continue;
                }
                is_login = false;
                System.out.println("Logout success");
            }
            else if (arr[0].equals("buyTicket")) {
                if (arr.length != 6) {
                    System.out.println("Arguments illegal");
                    continue;
                }
                if (!is_login) {
                    System.out.println("Please login first");
                    continue;
                }
                if (trainMap.get(arr[1]) == null) {
                    System.out.println("Train does not exist");
                    continue;
                }
                Line line = lineMap.get(trainMap.get(arr[1]).line_id);
                if (line.stations.get(arr[2]) == null ||
                    line.stations.get(arr[3]) == null) {
                    System.out.println("Station does not exist");
                    continue;
                }
            }

            else System.out.println("Command does not exist");
        }
    }
}
