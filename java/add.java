import java.util.Scanner;

public class add {
    public static void main(String[] args) {
        int a, b;
        Scanner v = new Scanner(System.in);
        System.out.print("Enter the value of A : ");
        a = v.nextInt();
        System.out.print("Enter the value of c : ");
        b = v.nextInt();
        System.out.println("A = " + a + "\nB = " + b + "\ntotal = " + (a + b));
    }
}
