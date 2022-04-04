interface IMessage {
    public void print();
}
class Music implements IMessage {
    String name;
    String type;

    public Music(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void print() {
        System.out.println("音乐名称：" + name);
        System.out.println("音乐类型：" + type);
    }
}

class Mobile implements IMessage {
    String name;
    String type;

    public Mobile(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void print() {
        System.out.println("手机品牌：" + name);
        System.out.println("手机型号：" + type);
    }
}

public class Test {
    public static void main(String args[]) {
        IMessage music = new Music("清明雨上", "许嵩");
        IMessage mobile = new Mobile("小米", "小米10pro");
        music.print();
        mobile.print();
    }
}
