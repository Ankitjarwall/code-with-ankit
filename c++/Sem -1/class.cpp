#include<iostream>
using namespace std;


class lpu
{   
    int a,b;
    public:
    int add(int c,int d)
    {
        a=c;
        b=d;
        return 0;
       
    }
    void display()
    {
        cout<<"value A "<<a<<endl;  
        cout<<"value B "<<b<<endl;  
        cout<<"sum A + b = "<<a+b<<endl;  
    }
}name1;


int main()
{
    int a,b;
    cout<<"Enter the number : ";
    cin>>a;
    cout<<"Enter the number : ";
    cin>>b;
    name1.add(a,b);
    name1.display();

    return 0;

}