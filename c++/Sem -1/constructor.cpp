
//contain error...
#include <iostream>
using namespace std;

class area
{
private:
    int r;
    float cir_area;
    int l, b, rect_area;
    int s1, s2, tr_area;

public:
    area(int z, int x)
    {

        rect_area = z * x;
    }
    area(int x)
    {
        cir_area = (r * r) * 3.14;
    }
    area(int x, int z, int y)
    {
        tr_area = (x * z) * 0.5;
    }
    
    void rectget()
    {
        cout << "Enter the value : ";
        cin >> l, b;
        area(l, b);
    }
    void triget()
    {
        cout << "Enter the value : ";
        cin >> s1, s2;
        area(s1, s2, 0);
    }
    void cirget()
    {
        cout << "Enter the value : ";
        cin >> r;
    }

    void disp()
    {
        cout << "Rectangle : " << rect_area << endl;
        cout << "Triangle : " << tr_area << endl;
        cout << "Circle : " << cir_area << endl;
    }
};
int main()
{
    area.rectget();
    area.cirget();
    area.triget();

    area.disp();

    return 0;
}