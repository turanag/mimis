#include <windows.h>
#include <iostream>

using namespace std;

BOOL CALLBACK EnumProc(HWND hWnd, LPARAM lParam);

int main() {
    EnumWindows(EnumProc, 0);
    return 0;
}

BOOL CALLBACK EnumProc(HWND hWnd, LPARAM lParam) {
	TCHAR title[500];
	ZeroMemory(title, sizeof(title));
	GetWindowText(hWnd, title, sizeof(title) / sizeof(title[0]));
	printf("%d\n%s\n", hWnd, title);
	return TRUE;
}
