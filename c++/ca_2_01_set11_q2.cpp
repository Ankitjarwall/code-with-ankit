A static member is shared by all objects of the class.
We can define class members static using static keyword.
 When we declare a member of a class as static it means 
 no matter how many objects of the class are created, 
 there is only one copy of the static member.

 All static data is initialized to zero when the first
 object is created, if no other initialization is present. 
 We can't put it in the class definition but it can be
 initialized outside the class as done in the following
 example by redeclaring the static variable, using the
 scope resolution operator :: to identify which class 
 it belongs to.
 
syntax :
 static data_type data_member;  

 example :
 

#include <iostream>
using namespace std;

class cyber
{
    static int lan;
    char password[4];
};
int main()
{
    cyber cafe;
    cout << "Size of Object : " << sizeof cafe;

    return 0;
}

=============output=============

Here we get the size of object 4 bytes,the reason is 
static data members/function use shared memory and they are not
part of class.Hence they are accessable through any object of a
class.

Real life example :
Cyber cafe.
A school/collage with a library and playgroud.