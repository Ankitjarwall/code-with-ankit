#include <iostream>

using namespace std;

class Solution
{
public:
    int count = 0;
    int countOdds(int low, int high)
    {
        int count = 0;
        count = (low + high) / 2;
        return (count);
    }
} sol;

int main()
{
    cout << sol.countOdds(3, 7);
}