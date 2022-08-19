import java.util.Scanner;

public class input {
    public static void main(String[] args) {
        int num, d = 90;
        float fnum;
        double dnum;
        String name;

        Scanner get = new Scanner(System.in);

        System.out.print("Int : ");
        num = get.nextInt();

        System.out.print("Float : ");
        fnum = get.nextFloat();

        System.out.print("Double : ");
        dnum = get.nextDouble();

        get.nextLine();

        System.out.print("Name : ");
        name = get.nextLine();

        System.out.println("int : " + num);
        System.out.println("Float : " + fnum);
        System.out.println("Double : " + dnum);
        System.out.println("Name : " + name);

    }
}