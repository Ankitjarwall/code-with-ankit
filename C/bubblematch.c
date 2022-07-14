#include <stdio.h>
int main()
{
    int matchno, i, k, swap;
    printf("Enter the number of inning: ");
    scanf("%d", &matchno);
    int score[matchno];
    for (int i = 0; i < matchno; i++)
    {
        printf("Enter the score of %d match : ", i);
        scanf("%d", &score[i]);
    }

    for (int i = 0; i < matchno - 1; i++)
    {
        for (int k = 0; k < matchno - 1 - i; k++)
        {
            if (score[k] > score[k + 1])
            {
                swap = score[k];
                score[k] = score[k + 1];
                score[k + 1] = swap;
            }
        }
    }

    printf("********Innings in Ascending order********\n");
    for (int i = 0; i < matchno; i++)
    {
        printf("The inning score %d\n", score[i]);
    }
    printf("The highest score is %d", score[matchno - 1]);
}