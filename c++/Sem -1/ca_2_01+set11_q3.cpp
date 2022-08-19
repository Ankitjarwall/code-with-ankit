C++ provides an inline functions to reduce the function 
call overhead. Inline function is a function that is 
expanded in line when it is called. When the inline 
function is called whole code of the inline function
 gets inserted or substituted at the point of inline 
 function call. 
 
 This substitution is performed by the 
 C++ compiler at compile time. Inline function may 
 increase efficiency if it is small.

The syntax for defining the function inline is:

inline return-type function-name(parameters)
{
    // function code
} 

Inline functions provide following advantages:
1) Function call overhead doesn’t occur.
2) It also saves the overhead of push/pop variables
on the stack when function is called.
3) It also saves overhead of a return call from a function.

4) When you inline a function, you may enable compiler
to perform context specific optimization on the body of
function. Such optimizations are not possible for normal
function calls. Other optimizations can be obtained by
 considering the flows of calling context and the called context.
5) Inline function may be useful (if it is small) for 
embedded systems because inline can yield less code than
 the function call preamble and return.

inline function fails in these cases :
1) If a function contains a loop. (for, while, do-while)
2) If a function contains static variables.
3) If a function is recursive.
4) If a function return type is other than void, and the 
return statement doesn’t exist in function body.
5) If a function contains switch or goto statement.


#include <iostream>
using namespace std;

inline void sum(int a, int b);
inline void sum(int a, int b)
{
    cout << "The Sum is = " << a + b;
}

int main()
{
    int value = 10, value1 = 5;
    sum(value, value1);
    return 0;
}