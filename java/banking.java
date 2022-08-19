import java.util.Scanner;

class current
{
    int account,withdraw_amount,balance,password;
    String name;

    current()
    {
        balance=10000;
    }
    void input()
    {
        Scanner get =new Scanner(System.in);
        System.out.println("\n\t******Current******");
        System.out.print("Enter your User Name : ");
        name=get.nextLine();
        System.out.print("Enter the Account number : ");
        account=get.nextInt();
        get.nextLine();
        System.out.print("Enter the Pin : ");
        password=get.nextInt();

        check_current_details(name,account,password);
    }

    void check_current_details(String fname,int faccount, int fpassword)
    {
        if(faccount==12345&&fpassword==2002)
        {
            
            withdraw();
        }
        else if(faccount==67890&&fpassword==2000)
        {
            withdraw();
        }
        else{
            System.out.println("\n\t\tCURRENT\nInvalid User Name / Account number or password...");
            System.out.println("Retry...");
            input();
        }
    }

    void withdraw()
    {
        Scanner get=new Scanner(System.in);
        System.out.print("Enter the Amount Withdraw : ");
        withdraw_amount=get.nextInt();
        balance=-withdraw_amount;
        disp();
    }
    void disp()
    {
        System.out.println("\nName : "+name);
        System.out.println("Account : "+account);
        System.out.println("Withdraw Amount : "+withdraw_amount);
        System.out.println("Balance : "+balance);
        exist();
    }
    void exist()
    {
        System.out.println("\nThank you for Coming...\nSee you Again...");
    }
}

class saving
{
    int account,withdraw_amount,balance,password;
    String name;

    saving()
    {
        balance=10000;
    }

    void input()
    {
        Scanner get =new Scanner(System.in);
        System.out.println("\n\t******Saving******");
        System.out.print("Enter your User Name : ");
        name=get.nextLine();
        System.out.print("Enter the Account number : ");
        account=get.nextInt();
        get.nextLine();
        System.out.print("Enter the Password : ");
        password=get.nextInt();

        check_saving_details(name,account,password);
    }

    void check_saving_details(String fname,int faccount, int fpassword)
    {
        if(faccount==12345&&fpassword==2002)
        {
            
            withdraw();
        }
        else if(faccount==67890&&fpassword==2000)
        {
            withdraw();
        }
        else{
            System.out.println("\n\t\tSAVING \nInvalid User Name / Account number or password...");
            System.out.println("Retry...");
            input();
        }
    }
    void withdraw()
    {
        Scanner get=new Scanner(System.in);
        System.out.print("Enter the Amount Withdraw : ");
        withdraw_amount=get.nextInt();
        balance=-withdraw_amount;
        disp();
    }
    void disp()
    {
        System.out.println("\nName : "+name);
        System.out.println("Account : "+account);
        System.out.println("Withdraw Amount : "+withdraw_amount);
        System.out.println("Balance : "+balance);
        exist();
    }
    void exist()
    {
        System.out.println("\nThank you for Coming...\nSee you Again...");
    }
}


public class banking
{
    static public void main(String[] args)
    {
        int user;
        Scanner get=new Scanner(System.in);
        System.out.print("\t\tAccount type\n\tSaving-1\tCurrent-2)");
        System.out.println("Enter input : ");
        user=get.nextInt();
        if(user==1&user==1)
        {
            saving saccount=new saving();
            saccount.input();
        }
        else if(user==2&user==2)
        {
            current caccount=new current();
            caccount.input();
        }
        else if(user==3&user==3)
        {
            //new account;
        }
        else{
            System.out.print("Invalid !!!");
        }
    }
}

class create
{
    String NewUserName;
    int NewAccountNo,NewPssword,array[];

    System.out.print("Enter Your Name : ");
    NewUserName=get.nextInt();
};