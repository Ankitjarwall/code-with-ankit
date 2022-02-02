// C program to play song Jingle Bell
// using the BEEP() function

#include <stdio.h>
#include <windows.h>

// Driver Code
int main()
{
    int x;

    // Loop for sound Jingle
    for (x = 0; x < 2; x++)
    {
        Beep(523, 500);
    }

    // sound Bell
    Beep(523, 800);

    Sleep(200);

    // Loop for sound Jingle
    for (x = 0; x < 2; x++)
    {
        Beep(523, 500);
    }

    // sound Bell
    Beep(523, 800);

    // Sound for rest of the tone
    Sleep(200);

    Beep(523, 500);

    Sleep(50);

    Beep(659, 400);

    Sleep(50);

    Beep(440, 400);

    Sleep(50);

    Beep(494, 400);

    Sleep(50);

    Beep(523, 750);

    Sleep(400);

    Beep(600, 400);

    Sleep(100);

    Beep(600, 350);

    Sleep(200);

    Beep(600, 300);

    Sleep(150);

    Beep(600, 250);

    Sleep(150);

    Beep(600, 150);

    Sleep(150);

    Beep(550, 250);

    Sleep(150);

    Beep(555, 350);

    Sleep(50);

    Beep(555, 200);
    Sleep(150);

    Beep(555, 200);

    Sleep(150);

    Beep(690, 200);

    Sleep(150);

    Beep(690, 200);

    Sleep(150);

    Beep(610, 200);

    Sleep(150);

    Beep(535, 160);

    Sleep(100);

    Beep(500, 150);

    Beep(500, 50);

    Sleep(200);

    Beep(700, 200);

    return 0;
}
