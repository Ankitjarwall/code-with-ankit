#include <stdio.h>
#include <string.h>
int main()
{
	char word[20], word1[20], word2[20];
	int i, z = 0;
	printf("Enter the word please : ");
	scanf("%s", &word);
	printf("Word to be placed : ");
	scanf("%s", &word2);
	printf("Word to be replaced : ");
	scanf("%s", &word1);
	for (int i = 0; i < word[i]; i++)
	{
		if (word[i] == word1[z])
		{
			word[i] = word2[z];
			z++;
		}
	}
	printf("Word after changes - %s", word);
}