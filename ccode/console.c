

#include <stdio.h>

BOOL foo( const char* str );

int main( void )
{
    char arr[20];
    //foo( "input" );
	printf("input");
    scanf("%s",arr);
    printf( "these are what you input : %s\n", arr );
}

BOOL foo( const char* str )
{
    for( const char* p=str; *p; ++p )
    {
        INPUT_RECORD buf[2];
        buf[0].EventType = KEY_EVENT;
        buf[0].Event.KeyEvent.bKeyDown = TRUE;
        buf[0].Event.KeyEvent.wRepeatCount = 1;
        buf[0].Event.KeyEvent.wVirtualKeyCode = toupper(*p);
        buf[0].Event.KeyEvent.wVirtualScanCode = MapVirtualKey(toupper(*p),MAPVK_VK_TO_VSC);
        buf[0].Event.KeyEvent.uChar.AsciiChar = *p;
        buf[0].Event.KeyEvent.dwControlKeyState = 0;
        buf[1] = buf[0];
        buf[1].Event.KeyEvent.bKeyDown = FALSE;

        DWORD n = 0;
        BOOL b = WriteConsoleInputA( GetStdHandle(STD_INPUT_HANDLE), buf, 2, &n );
        if( !b || n!=2 )
            return FALSE;
    }
    return TRUE;
}