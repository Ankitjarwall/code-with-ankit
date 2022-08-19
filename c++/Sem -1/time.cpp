#include <iostream>
using namespace std;
int a = 0;
class time
{
private:
    int p_hour, p_min, p_sec, p_hour1, p_min1, p_sec1;
    int total_hour, total_min, total_sec;

public:
    void get(int fhour, int fmin, int fsec, int fhour1, int fmin1, int fsec1)
    {
        p_hour = fhour;
        p_min = fmin;
        p_sec = fsec;
        p_hour1 = fhour1;
        p_min1 = fmin1;
        p_sec1 = fsec1;
    }
    void sum()
    {
        total_sec = p_sec1 + p_sec;
        total_min = p_min1 + p_min;
        total_hour = p_hour1 + p_hour;

        if (total_sec > 59)
        {
            a = total_sec / 60;
            total_min = total_min + a;
            a = total_sec % 60;
            total_sec = a;
        }
        if (total_min > 59)
        {
            a = total_min / 60;
            total_hour = total_hour + a;
            a = total_min % 60;
            total_min = a;
        }
    }
    void disp()
    {
        cout << "Hour : " << total_hour
             << " Min : " << total_min
             << " Sec : " << total_sec;
    }
} add;

int main()
{
    int hour, min, sec, hour1, min1, sec1;
    cout << "1 : Enter the HH MM SS : ";
    cin >> hour >> min >> sec;
    cout << "2 : Enter the HH MM SS : ";
    cin >> hour1 >> min1 >> sec1;

    add.get(hour, min, sec, hour1, min1, sec1);
    add.sum();
    add.disp();
    return 0;
}