import java.util.Scanner;

public class sum{
    public static void main(String[] args){
        int a;

        Scanner get=new Scanner(System.in);
        
        System.out.print("Enter the table number : ");
        a=get.nextInt();

        for(int i=1;i<=10;i++)
        {
            System.out.println(a+"X"+i+"="+a*i);
        }
    }

};