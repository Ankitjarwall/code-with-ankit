#include <iostream>
using namespace std;
int main()
{
    int array[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int num = 122, mid = 0, ub = 0, lb = 0, flag;

    while (lb <= ub)
    {
        mid = (lb + ub) / 2;
        if (array[mid] == num)
        {
            flag = mid;
            break;
        }
        else if (array[mid] > num)
        {
            lb = mid + 1;
        }
        else
        {
            ub = mid - 1;
        }
    }

    if (flag == 0)
    {
        cout << "element!!";
    }
    else
    {
        cout << "Not here!!";
    }
    return 0;
}