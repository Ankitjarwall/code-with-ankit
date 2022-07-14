import java.util.Scanner;
public class input{
    public static void main(String[] args)
    {
        int num;
        float fnum;
        double dnum;
        String name;
        char cname;
        
        Scanner get=new Scanner(System.in);

        System.out.print("Int : ");
        num=get.nextInt();
        System.out.print("Float : ");
        fnum=get.nextFloat();
        System.out.print("Double : ");
        dnum=get.nextDouble();
        System.out.print("String : ");
        name=get.nextLine();
        System.out.print("Char : ");
        //cname=get.nextChar();

        System.out.println("Int : "+num+"");
        System.out.println("Float : "+num1+"");
        System.out.println("Double : "+num2+"");
        System.out.println("String : "+name+"");


    }
}