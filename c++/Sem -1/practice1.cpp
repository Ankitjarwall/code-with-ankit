#include <iostream>
using namespace std;

class school
{
private:
    int age;
    string name;

public:
    void public_display()
    {
        cout << "Name : ";
        cin >> name;
        cout << "Age : ";
        cin >> age;
        protected_display();
    }

protected:
    void protected_display()
    {
        cout << "Displayed.\n";
        cout << "Name : " << name;
        cout << "Age : " << age;
    }
};

int main()
{
    school bca;
    bca.public_display();
    return 0;
}