#include <iostream>

using namespace std;
void insertion(int[], int);
void sort_array(int, int[]);
void linear_search(int , int []);
void disp(int[]);

int main()
{
    int array[10] = {5, 4, 3, 2, 9, 6, 7, 8, 1}, input = 0, value = 0;

    value = sizeof(array)/4;
menu:
    cout << "\ninsertion : 1\tSort : 2\nlinear search : 3\tDisplay : 4\nExit : 5\nEnter input : ";
    cin >> input;

    switch (input)
    {
    case 1:
        insertion( array,value);
        
        break;

    case 2:
        sort_array(value, array);
       
        break;

    case 3:
        linear_search(value, array);
       
        break;
    case 4:
        disp(array);
        break;

    default:
        cout << "Invalid!!!";
        break;
    }
    goto menu;

    return 0;
}

void linear_search(int fvalue, int farray[])
{
    int novalue = 0;
    int fuser_input, findex = 0;
    cout << "Search Element : ";
    cin >> fuser_input;

    for (int i = 0; i < fvalue; i++)
    {
        if (fuser_input == farray[i])
        {
            findex = i;
            cout << "\nElement " << fuser_input << " is in array at index : " << findex << endl;
            novalue++;
        }
    }
    if (novalue == 0)
    {
        cout << "No element.." << endl;
    }
}

void sort_array(int fvalue, int farray[])
{
    int ftemp = 0;
    for (int i = 0; i < fvalue; i++)
    {
        for (int j = 0; j <fvalue-i; j++)
        {
            if (farray[j] < farray[j+1])
            {
                ftemp = farray[j];
                farray[j] = farray[j+1];
                farray[j+1] = ftemp;
            }
        }
    }
    disp(farray);
}

void disp(int farray[])
{
    for (int i = 0; i < 10; i++)
    {
        cout << farray[i] << " ";
    }
    cout << endl;
}

void insertion(int farray[], int fvalue)
{
    int index = 0, z = fvalue - 1, element = 0;
    cout << "Enter the Index : ";
    cin >> index;
    cout << "Enter the Element value : ";
    cin >> element;
    for (int i = fvalue; i > 0; i--)
    {
        if (i >= index)
        {
            farray[i] = farray[z];
            z--;
        }

        if (index == i)
        {
            farray[i] = element;
        }
    }
    disp(farray);
}