#include <stdio.h>
#include <conio.h>

struct civilwar
{
    int ant;
    char natasha[20];
    float black_panther;
} ironman = {7, "Out_Standing", 8.5};

void jarves(struct civilwar ironman);

int main()
{
    jarves(ironman);
    return 0;
}

void jarves(struct civilwar ironman)
{
    printf("Ant man ---%d\nNatasha ****%s****\nBlack Panther ---%2f", ironman.ant, ironman.natasha, ironman.black_panther);
}