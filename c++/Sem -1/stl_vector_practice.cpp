#include <iostream>
#include <vector>
using namespace std;

void newuser();
void dispuser();
void first();
void deletee();

vector<int> roll;
vector<string> name;
int nroll = 0, i = 0, input = 0;
string nname;

void newuser()
{
    cout << "Enter your name : ";
    cin >> nname;
    cout << "Enter the roll : ";
    cin >> nroll;
    name.push_back(nname);
    roll.push_back(nroll);
    first();
}

void dispuser()
{
    cout << "\tStudent : " << i << endl;
    cout << "Name : " << name.at(i) << endl;
    cout << "Roll : " << roll.at(i) << endl;
    i++;
    first();
}

void first()
{
    cout << "Log in : 1\tSign Up : 2\tDelete user : 3\tExit : 4\n\nEnter input : ";
    cin >> input;
    switch (input)
    {
    case 1:
        newuser();
        break;
    case 2:
        dispuser();
        break;
    case 3:
        deletee();
        break;
    case 4:
        exit;
        break;
    default:
        cout << "Invalid input...";
        break;
    }
}

void deletee()
{
    name.pop_back();
    roll.pop_back();
    cout << "User id " << i - 1 << " deleted... ";
    first();
}
int main()
{
    first();
    return 0;
}