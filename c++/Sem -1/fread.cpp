//        READ
// #include <stdlib.h>
// #include <stdio.h>
// int main()
// {
//     FILE *ankit;
//     ankit = fopen("ccode.txt", "r");
//     int sum, sum1;
//     fscanf(ankit,"%d", &sum);
//     fscanf(ankit,"%d", &sum1);
//     printf("%d\n",sum);
//     printf("%d",sum1);
//     fclose(ankit);
//     return 0;
// }

//          write
// #include <stdio.h>
// #include <stdio.h>
// int main()
// {
//     FILE *ankit;
//     ankit = fopen("ccode.txt", "w");
//     int sum, sum1;
//     printf("Enter the number : ");
//     scanf("%d", &sum);

//     printf("Enter the number : ");
//     scanf("%d", &sum1);

//     fprintf(ankit, "%d %d", sum, sum1);

//     fclose(ankit);

//     ankit = fopen("ccode.txt", "r");
//     fscanf(ankit, "%d", &sum);
//     fscanf(ankit, "%d", &sum1);
//     printf("%d %d", sum, sum1);
//     fclose(ankit);
//     return 0;
// }

#include <stdio.h>
#include <stdlib.h>
int main()
{
    FILE *ankit;
    ankit = fopen("ccode.txt", "w+");
    int sum, sum1;
    printf("Enter the number : ");
    scanf("%d", &sum);

    printf("Enter the number : ");
    scanf("%d", &sum1);

    fprintf(ankit, "%d %d", sum, sum1);

    fscanf(ankit, "%d", &sum);
    fscanf(ankit, "%d", &sum1);
    printf("%d %d", sum, sum1);
    fclose(ankit);
    
}
