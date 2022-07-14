#include <stdio.h> //header file
int main()         //declaration of variables.
{
    int match1, Omatch1, match2, Omatch2, match3, Omatch3;
    float over1, Oover1, over2, Oover2, over3, Oover3, NRR1,NRR2,NRR3;
    char y, team1[10], team2[10], team3[10];
    do
    {
        printf("\nEnter Team name : "); //input - KKR
        scanf("%s", &team1);
        printf("\t\t%s Score: ", team1); //input - 227
        scanf("%d", &match1);
        printf("\t\tOver Faced: "); //input - 5
        scanf("%f", &over1);
        printf("\t\tOpponent Score : "); //input - 198
        scanf("%f", &Omatch1);
        printf("\t\tOpponent Bowled: "); //input - 6
        scanf("%f", &Oover1);

        printf("\nEnter Team name : "); //input - CSK
        scanf("%s", &team2);
        printf("\t\t%s Score: ", team2); //input -190
        scanf("%d", &match2);
        printf("\t\tOver Faced: "); //input - 9
        scanf("%f", &over2);
        printf("\t\tOpponent Score : "); //input - 165
        scanf("%f", &Omatch2);
        printf("\t\tOpponent Bowled: "); //input -10
        scanf("%f", &Oover2);

        printf("\nEnter Team name : "); //input - RRR
        scanf("%s", &team3);
        printf("\t\t%s Score: ", team3); //input - 165
        scanf("%d", &match3);
        printf("\t\tOver Faced: "); //input -8
        scanf("%f", &over3);
        printf("\t\tOpponent Score : "); //input -166
        scanf("%f", &Omatch3);
        printf("\t\tOpponent Bowled: "); //input -7
        scanf("%f", &Oover3);

        NRR1 = (match1 / over1) - (Omatch1 / Oover1); //NRR Formula: NRR=RRF-RRA
        printf("\n\tTeam %s NRR= %f", team1, NRR1);

        NRR2 = (match2 / over2) - (Omatch2 / Oover2);
        printf("\n\tTeam %s NRR= %f", team2, NRR2);

        NRR3 = (match3 / over3) - (Omatch3 / Oover3);
        printf("\n\tTeam %s NRR= %f", team3, NRR3);

        printf("\n\nPress Y for continue, \nPress any key to exit :  ");
        fflush(stdin);
        scanf("%c", &y);
    } while (y == 'y');
}
