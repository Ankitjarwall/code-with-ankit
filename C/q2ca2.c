#include <stdio.h>
#include <string.h>

struct sahil
{
    int b; //4 //-4
    int a; //4 // -4  
    double mark1; //8 
    float avg; //4 //-4 
    char name[5];    //5 //-3 
    double mark; //8 
    
} s;

union sahil1
{
    int b; //4 //-4
    int a; //4 // -4  
    float av2;//4  //-4
    char name[5];    //5 //-3 
    double mark; //8 

} s1;

int main()
{
    printf("s size- %d\n", sizeof(s));
    printf("s1 size- %d", sizeof(s1));
    return 0;
}