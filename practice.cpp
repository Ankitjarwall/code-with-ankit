// #include <iostream>

// using namespace std;
// int main()
// {
//     int start, end, z, y, i, n = 0, total;
//     cout << "Starting range: ";
//     cin >> start;
//     cout << "End range: ";
//     cin >> end;

//     for (; start <= end; start++)
//     {
//         total = 0;
//         for (i = start; i != 0; i = i / 10)
//         {
//             y = i % 10;
//             z = 1;
//             for (n = start; n != 0; n = n / 10)
//             {
//                 z = y * z;
//             }
//             total = z + total;
//         }
//         if (start == total)
//         {
//             cout << total << " ";
//         }
//     }
//     return 0;
// }

// #include <iostream>
// using namespace std;

// struct lpu
// {
//     char name[20];
//     int roll, english, hindi, math;
// } student;

// int main()
// {

//     int total_mark;
//     float percentage;

//     cout << "NAME: ";
//     fflush(stdin);
//     cin >> student.name;
//     cout << "ROLL_NO: ";
//     cin >> student.roll;

//     cout << "English : ";
//     cin >> student.english;
//     cout << "Hindi : ";
//     cin >> student.hindi;
//     cout << "Maths : ";
//     cin >> student.math;
//     total_mark = student.english + student.hindi + student.math;
//     percentage = total_mark / 3.0;

//     cout << "\n*RESULT *";
//     cout << "\nName: " << student.name << endl;
//     cout << "Roll_No: " << student.roll << endl;
//     cout << "Total_No: " << total_mark << "\t";
//     cout << "Percentage: " << percentage << endl;
//     return 0;
// }

#include <iostream>
#include <math.h>
using namespace std;
int main()
{
    int n, i, k = 0, sum=0;
    int arr[10];
    cin >> n;
    while (n)//50
    {
        cout << n << " ,";//50
        arr[sum] = n % 10;//5
        sum = sum + 1;//1
        n = n / 10;//5
    }
    for (i = 0; i < sum; i++)
    {
        //cout << k << " ,";
        k = k + pow(arr[i], sum);
    }
    //cout << k;
    return 0;
}