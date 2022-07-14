#include <iostream>
#include <string.h>
using namespace std;

struct student
{
    int age, size;//8
    float number;//4
}s1;

union student1
{
    int age, size;
    float number;
};

int main()
{
    cout << "size of structure : " << sizeof(student);
    cout << "\nsize of union : " << sizeof(student1);
    return 0;
}