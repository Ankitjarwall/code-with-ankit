#include <stdio.h>                                      //header file.
int main()                                               //main body.
{ 
    int num, i, k, swap, y = 0, z = 0;                     //declearing variable.
    printf("Enter the total number : ");                    //user input.
    scanf("%d", &num);
    int array[num];
    for (int i = 0; i < num; i++)
    {
        printf("Enter the %d num : ", i + 1);
        scanf("%d", &array[i]);
    }

    for (int i = 0; i < num - 1; i++)
    {
        for (int k = 0; k < num - 1 - i; k++)
        {
            y++;                                        //comparision count
            if (array[k] > array[k + 1])                //swapping
            {
                swap = array[k];
                array[k] = array[k + 1];
                array[k + 1] = swap;
                z++;                                    //swapping count
            }
        }
    }
    printf("The result after swaping is : \n");             //ouput
    for (int i = 0; i < num; i++)
    {
        printf("%d ", array[i]);
    }
    printf("\nThe number of swaping is : %d\n", z);
    printf("The number of comparision is : %d\n", y);
}