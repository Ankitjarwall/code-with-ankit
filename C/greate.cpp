// #include <stdio.h>

#include<iostream>
using namespace std;

int main()
{
    int val1 = 10, val2 = 20;

    // printf("val1 =  %d", val1);
    // printf("val2 =  %d", val2);

    if (val1 > val2)
    {
       cout<<"val1 is greater then val 1: "<< val1;
    }
    else if (val1 < val2)
    {
        cout<<"val2 is greater then val 2: "<< val2;
    }
    else
    {
        cout<<"No value.";
    }

    return 0;
}