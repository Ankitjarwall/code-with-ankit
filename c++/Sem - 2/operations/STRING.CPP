#include<iostream>
using namespace std;

void display(string *naam)
{
    string name1=*naam;
    cout << "len  :" << name1.size();
}
int main()
{
    string name = "Ankit";
    display(&name);

    return 0;
}
