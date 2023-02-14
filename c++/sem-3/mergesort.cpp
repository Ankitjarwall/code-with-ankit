#include <iostream>
using namespace std;

void merge(int ar[], int l, int mid, int r)
{
    int len1 = mid - l + 1, len2 = r - mid, *ar1 = new int[len1], *ar2 = new int[len2];
    int a = 0, j = 0, k = l;

    for (int i = 0; i < len1; i++)
    {
        ar1[i] = ar[i + l];
    }

    for (int i = 0; i < len2; i++)
    {
        ar2[i] = ar[mid + 1 + i];
    }

    while (a < len1 && j < len2)
    {
        if (ar1[a] < ar2[j])
        {
            ar[k] = ar1[a];
            a++;
        }
        else
        {
            ar[k] = ar2[j];
            j++;
        }
        k++;
    }

    while (a < len1)
    {
        ar[k] = ar1[a];
        a++;
        k++;
    }
    while (j < len2)
    {
        ar[k] = ar2[j];
        j++;
        k++;
    }
}

void mergesort(int ar[], int l, int r)
{
    if (l >= r)
    {
        return;
    }
    int mid = (l + r) / 2;
    mergesort(ar, l, mid);
    mergesort(ar, mid + 1, r);
    merge(ar, l, mid, r);
}

int main()
{
    int ar[5] = {20, 56, 7, 32, 4};
    int size = 5;

    cout << "Elements after sorting:" << endl;
    mergesort(ar, 0, size - 1);
    for (int i = 0; i < size; i++)
    {
        cout << ar[i] << endl;
    }
    return 0;
}