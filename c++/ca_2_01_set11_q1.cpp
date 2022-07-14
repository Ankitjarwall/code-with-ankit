#include <iostream>
using namespace std;
class math
{
public:
    void area(float a, float b)
    {
        cout << "Rectangle Area : " << a * b << "\n" << endl;
    }
    void area(float a)
    {
        const float pi = 3.14;
        cout << "Circle Area : " << (a * a) * pi << "\n" << endl;
    }
    void area(float a, double b)
    {
        cout << "Triangle Area : " << (a * b) / 2 << "\n" << endl;
    }
};

int main()
{
    float length, width, radius, height;
    double base;
    math find;
    
    cout << "Length of rectangle : ";
    cin >> length;
    cout << "width of Rectangle : ";
    cin >> width;
    find.area(length, width);

    cout << "Radius : ";
    cin >> radius;
    find.area(radius);

    cout << "Height of triangle : ";
    cin >> height;
    cout << "Base of triangle : ";
    cin >> base;
    find.area(height, base);

    return 0;
}