public class Person {
    String name;
    int age;
    String sex;
    public Person() {
        name = "张三";
        age = 1000;
        sex = "男";
    }
    public Person(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    public void setAge(int age) {
        if (age >= 0 && age <= 130)
            this.age = age;
        else
            System.out.println("年龄不合法");
    }
    public int getAge() {
        return age;
    }
    public void working() {
        System.out.println(name + "is working");
    }
    public void showAge() {
        System.out.println(age);
    }
}

class TestPerson {
    public static void main(String args[]) {
        Person ZhangSan = new Person();
        Person LiuYong = new Person("刘庸", 28, "男");
        ZhangSan.setAge(150);
        ZhangSan.setAge(20);
        LiuYong.working();
        ZhangSan.working();
        LiuYong.showAge();
    }
}
