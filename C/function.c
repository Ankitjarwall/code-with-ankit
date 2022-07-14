#include<stdio.h>
//prototype
float sum(float x,float y,float z);

int main()
{
    float x=5,y=5,z=5;
    printf("\n1st no is %f.\n2nd no is %f.");
    printf("\n 3rd no is %f.\nThe avg of three no is %f.",x,y,z,sum);
    return 0;
}

//defination
float sum(float x,float y,float z){
    float sum;
    sum =(x+y+z)/3;
    return sum;
}