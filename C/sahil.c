#include <stdio.h>//included standard input and output header file
int main()//main function
{
   int m, n, rows, coloums,d = 0,c=0,i,j,k,l;;//declaration
   printf("Enter the Rows: ");
   scanf("%d", &m);//input of m from user
   printf("Enter the Coloum: ");
   scanf("%d", &n);//input of n from user
   int ARRAY[m][n];//Declared an array with m row and n coloum
   for (rows = 0; rows < m; rows++)
   {
      for (coloums = 0; coloums < n; coloums++)//used nested loop to loop through array
         scanf("%d", &ARRAY[rows][coloums]);//took all the value from user
   }l=ARRAY[0][0];//Assigned frist value of array to l
   printf("\n=========================Array========================\n");
	for(i=0;i<rows;i++){
		k=1;
		for(j=0;j<coloums;j++){//used nested loop
			printf(" %d  ",ARRAY[i][j]);//printed all the values in array
			if(k==coloums){//used if statement
				printf("\n\n");//if condition of if statement is true then printf will print two endline
			}
			k++;//increment of k by 1
			}
	}
   printf("\n * * * * * * * * * * * * * * * * * * * * * * \n");
   printf(" *    Array  *   Index    *\tAddress    *");
   printf("\n * * * * * * * * * * * * * * * * * * * * * * ");
   for (rows = 0; rows < m; rows++)
   {
      for (coloums = 0; coloums < n; coloums++)//used nested loop
      {
    printf("\n *   \t%d    *  [%d] [%d]   *  \t%d    *", ARRAY[rows][coloums],rows,coloums,&ARRAY[rows][coloums]);
      }//printed value, index and address
   }

   printf("\n * * * * * * * * * * * * * * * * * * * * * * \n");
   printf("\n * * * * * * * * * * SUM * * * * * * * * * * \n");
   printf(" *\t");
   for (rows = 0; rows < m; rows++)
   {
      for (coloums = 0; coloums < n; coloums++)//Used nested loop
      {
         c = c + ARRAY[rows][coloums];//added c and value of array at that index and stored it in c
         printf("%d", ARRAY[rows][coloums]);//Print the value of array
         if (d < m * n - 1)// used if statement
         {
            printf("+");//if condition if if statement is true then it will print +
            d++;//increment d by 1
         }

      }

   }printf("                            *");
   printf("\n *\tSum of all element is %d           *\n", c);//printed the value of c
   printf(" * * * * * * * * * * * * * * * * * * * * * * \n\n");
}
