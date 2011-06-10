#include <windows.h>
#include <iostream>
#include <tlhelp32.h>
#include <tchar.h>

BOOL CALLBACK EnumProc(HWND hWnd, LPARAM lParam);
BOOL GetProcessList();

using namespace std;

int main(int argc, char **argv) {
    if (argc == 2) {
        if (*argv[1] == 'w') {
            printf("handle\tpid\ttitle\n");
            EnumWindows(EnumProc, 0);
            return 0;
        } else if (*argv[1] == 'p') {
            printf("pid\tname\n");
            GetProcessList();
            return 0;
        }
    }
    return 1;
}

BOOL CALLBACK EnumProc(HWND hWnd, LPARAM lParam) {
    DWORD processId;
    GetWindowThreadProcessId(hWnd, &processId);
    TCHAR title[500];
    ZeroMemory(title, sizeof(title));
    GetWindowText(hWnd, title, sizeof(title) / sizeof(title[0]));
    printf("%d\t%d\t%s\n", hWnd, processId, title);
    return TRUE;
}

BOOL GetProcessList() {
    HANDLE hProcessSnap;
    HANDLE hProcess;
    PROCESSENTRY32 pe32;

    hProcessSnap = CreateToolhelp32Snapshot(TH32CS_SNAPPROCESS, 0);
    if (hProcessSnap == INVALID_HANDLE_VALUE) {
        return FALSE;
    }

    pe32.dwSize = sizeof(PROCESSENTRY32);
    if(!Process32First( hProcessSnap, &pe32 ) ) {
        CloseHandle(hProcessSnap);
        return FALSE;
    }

    do {
        hProcess = OpenProcess(PROCESS_ALL_ACCESS, FALSE, pe32.th32ProcessID);
        if( hProcess != NULL ) {
            CloseHandle( hProcess );
        }
        printf("%d\t%s\n", pe32.th32ProcessID,  pe32.szExeFile);
    } while (Process32Next(hProcessSnap, &pe32));

    CloseHandle( hProcessSnap );
    return TRUE;
}
