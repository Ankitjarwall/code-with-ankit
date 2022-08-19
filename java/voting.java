import java.util.Scanner;

public class voting {
    static public void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        details vote = new details();
        System.out.print("Enter your Name : ");
        vote.name = sc.nextLine();
        System.out.print("Enter your age : ");
        vote.age = sc.nextInt();
        vote.disp();

    }
}

class details {
    int age;
    String name;

    voting tele = new voting();

    void disp() {
        System.out.println("Name : " + name);
        System.out.println("Age : " + age);
        if (age >= 18 & age <= 100) {
            System.out.println(name + " you are eligible for voting becz you are " + age + " above.");
        } else {
            System.out.println(name + " you are not eligible for voting becz your age is " + age + " ,is under 18.");
        }
    }
}