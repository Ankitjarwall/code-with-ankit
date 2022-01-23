#include <iostream>

using namespace std;
int main()
{
    FILE *p;
    p = fopen("file.txt", "r");
    // char name[20];
    // cout << "Enter the name ";
    // cin >> name;
    // fputs(name, p);
    // fclose(p);

    //p = fopen("file.txt", "r");
    char name1[20];
    fgets(p,"%s",name1);
    fclose(p);

    return 0;
}