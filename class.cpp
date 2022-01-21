#include<iostream>
#include<iomanip>
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
        cout<<setw<<"value "<<a<<endl;  
        cout<<setw<<"value "<<b<<endl;  
        cout<<"value "<<a+b<<setw<<endl;  
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