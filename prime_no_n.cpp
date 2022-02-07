#include <iostream>
using namespace std;
int main()
{
    int a, k, i;
    cout << "Enter the number :";
    cin >> a;
    for (i = 2; i < a; i++)
    {
        for (k = 2; k < i; k++)
        {
            if (i % k == 0)
            {
                cout << "";
                break;
            }
        }
        if (k == i)
        {
            cout << k << " ";
        }
    }
    return 0;
}


// #include <stdio.h>
// #include <iostream.h>
// int main()
// {
// int in,fn;
// int i,j,flag;
// int count=0;
// cout<<"Enter initial number:";
// cin>>in;
// cout<<"Enter final number:";
// cin>>fn;
// if(in>fn)
// {
// fn=(in+fn)-(in=fn);
// }
// for(i=in;i<=fn;i++)
// {
// flag=1;
// for(j=2;j<=i/2;j++)
// {
// if(i%j==0)
// {
// flag=0;
// break;
// }
// }
// if(flag==1 && i>=2)
// {
// cout<<i<<" ";
// count++;
// }
// }
// cout<<endl<<"Total primes are = "<<count;
// return 0;
// }