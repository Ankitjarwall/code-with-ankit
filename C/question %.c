#include<stdio.h>
int main()
{
    int total;
    int math, hindi, english;
    printf("Enter the Math mark =");
    scanf("%d",&math);
    printf("Enter the hindi mark =");
    scanf("%d",&hindi);
    printf("Enter the English mark =");
    scanf("%d",&english);
    total=(math+english+hindi);
    if((total<40) ||math<33||hindi<33||english<33)
        {printf("You are fail.");
    }

    else{
            printf("you are pass.");
    }
    return 0;
}


