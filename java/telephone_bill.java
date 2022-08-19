import java.util.Scanner;
class tele
{
    int min,price,bill;
    String customer;

    tele()
    {
        price=2;
    }
    void input()
    {
        Scanner get=new Scanner(System.in);
        System.out.print("Enter Your Name : ");
        customer=get.nextLine();
        System.out.print("Enter Your Min cunsumed : ");
        min=get.nextInt();
        calculate(min);
        System.out.print("Total price : "+bill+" pay.");
    }

    int calculate(int fmin)
    {
        bill=fmin*price;
        return bill;
    }
}


public class telephone_bill{
    static public void main(String[] args)
    {
        tele sum=new tele();
        sum.input();
    }
}