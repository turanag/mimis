#ifndef __PROCESS_H__
#define __PROCESS_H__

#include <tlhelp32.h>

pair<HANDLE,PROCESSENTRY32> ProcessWalkInit()
{
  // Take a snapshot of all processes in the system.
  HANDLE  hProcessSnap = CreateToolhelp32Snapshot(TH32CS_SNAPPROCESS,0);
  if( hProcessSnap == INVALID_HANDLE_VALUE ) throw_("CreateToolhelp32Snapshot() returned invalid handle");

  // Set the size of the structure before using it.
  PROCESSENTRY32 pe32;
  pe32.dwSize = sizeof(PROCESSENTRY32);

  // Retrieve information about the first process,
  // and exit if unsuccessful
  if(!Process32First(hProcessSnap,&pe32)) {
    CloseHandle(hProcessSnap);   // clean the snapshot object
    throw_("Process32First");     // show cause of failure
  }

  return make_pair(hProcessSnap,pe32);
}

DWORD GetProcessID(const string& processname)
{
	pair<HANDLE,PROCESSENTRY32> h=ProcessWalkInit();
	HANDLE hProcessSnap=h.first;
	PROCESSENTRY32 pe32=h.second;

	do {
		if (pe32.szExeFile==processname) {
			CloseHandle(hProcessSnap);
			return pe32.th32ProcessID;
		}
	} while( Process32Next(hProcessSnap,&pe32) );

	CloseHandle(hProcessSnap);
	return 0;
}

PROCESSENTRY32 GetProcessInfo(const DWORD id)
{
	pair<HANDLE,PROCESSENTRY32> h=ProcessWalkInit();
	HANDLE hProcessSnap=h.first;
	PROCESSENTRY32 pe32=h.second;

	int n=0;
	do {
		if (pe32.th32ProcessID==id) {
			CloseHandle(hProcessSnap);
 			return pe32;
		}
	} while( Process32Next(hProcessSnap,&pe32) );

	CloseHandle(hProcessSnap);
	ZeroMemory( &pe32, sizeof(pe32) );
	pe32.dwSize = sizeof(PROCESSENTRY32);

	return pe32;
}

DWORD GetProcessCount(const string& processname)
{
	pair<HANDLE,PROCESSENTRY32> h=ProcessWalkInit();
	HANDLE hProcessSnap=h.first;
	PROCESSENTRY32 pe32=h.second;
	int n=0;
	do {
		if (pe32.szExeFile==processname) ++n;
	} while( Process32Next(hProcessSnap,&pe32) );

	CloseHandle(hProcessSnap);
	return n;
}

bool KillProcessID(const DWORD id)
{
    HANDLE hProcess = OpenProcess( PROCESS_TERMINATE, FALSE, id);
    if(hProcess==NULL) return false; // might have gone in the meantime, so no throw_("OpenProcess() got null handle");

	const BOOL t=TerminateProcess(hProcess,-1);
	CloseHandle(hProcess);
	return t!=0;
}

void KillAllProcesses(const string& exe)
{
	// kill existing polls
	DWORD id=GetProcessID(exe);
	while(id!=0){
		KillProcessID(id);
		id=GetProcessID(exe);
	}	
}

/*
#include <tchar.h>
#include <stdio.h>
#include <stdlib.h>

BOOL GetProcessList( );
BOOL ListProcessModules( DWORD dwPID );
BOOL ListProcessThreads( DWORD dwOwnerPID );
void printError( TCHAR* msg );

FILE* log=fopen("d:\\plog.txt","w");

BOOL GetProcessList( )
{
  HANDLE hProcessSnap;
  HANDLE hProcess;
  PROCESSENTRY32 pe32;
  DWORD dwPriorityClass;

  // Take a snapshot of all processes in the system.
  hProcessSnap = CreateToolhelp32Snapshot( TH32CS_SNAPPROCESS, 0 );
  if( hProcessSnap == INVALID_HANDLE_VALUE )
  {
    printError( TEXT("CreateToolhelp32Snapshot (of processes)") );
    return( FALSE );
  }

  // Set the size of the structure before using it.
  pe32.dwSize = sizeof( PROCESSENTRY32 );

  // Retrieve information about the first process,
  // and exit if unsuccessful
  if( !Process32First( hProcessSnap, &pe32 ) )
  {
    printError( TEXT("Process32First") ); // show cause of failure
    CloseHandle( hProcessSnap );          // clean the snapshot object
    return( FALSE );
  }

  // Now walk the snapshot of processes, and
  // display information about each process in turn
  do
  {
    fprintf(log, "\n\n=====================================================" );
    fprintf(log, TEXT("\nPROCESS NAME:  %s"), pe32.szExeFile );
    fprintf(log, "\n-----------------------------------------------------" );

    // Retrieve the priority class.
    dwPriorityClass = 0;
    hProcess = OpenProcess( PROCESS_ALL_ACCESS, FALSE, pe32.th32ProcessID );
    if( hProcess == NULL )
      printError( TEXT("OpenProcess") );
    else
    {
      dwPriorityClass = GetPriorityClass( hProcess );
      if( !dwPriorityClass )
        printError( TEXT("GetPriorityClass") );
      CloseHandle( hProcess );
    }

    fprintf(log, "\n  Process ID        = 0x%08X", pe32.th32ProcessID );
    fprintf(log, "\n  Thread count      = %d",   pe32.cntThreads );
    fprintf(log, "\n  Parent process ID = 0x%08X", pe32.th32ParentProcessID );
    fprintf(log, "\n  Priority base     = %d", pe32.pcPriClassBase );
    if( dwPriorityClass )
      fprintf(log, "\n  Priority class    = %d", dwPriorityClass );

    // List the modules and threads associated with this process
    ListProcessModules( pe32.th32ProcessID );
    ListProcessThreads( pe32.th32ProcessID );

  } while( Process32Next( hProcessSnap, &pe32 ) );

  CloseHandle( hProcessSnap );
  return( TRUE );
}


BOOL ListProcessModules( DWORD dwPID )
{
  HANDLE hModuleSnap = INVALID_HANDLE_VALUE;
  MODULEENTRY32 me32;

  // Take a snapshot of all modules in the specified process.
  hModuleSnap = CreateToolhelp32Snapshot( TH32CS_SNAPMODULE, dwPID );
  if( hModuleSnap == INVALID_HANDLE_VALUE )
  {
    printError( TEXT("CreateToolhelp32Snapshot (of modules)") );
    return( FALSE );
  }

  // Set the size of the structure before using it.
  me32.dwSize = sizeof( MODULEENTRY32 );

  // Retrieve information about the first module,
  // and exit if unsuccessful
  if( !Module32First( hModuleSnap, &me32 ) )
  {
    printError( TEXT("Module32First") );  // show cause of failure
    CloseHandle( hModuleSnap );           // clean the snapshot object
    return( FALSE );
  }

  // Now walk the module list of the process,
  // and display information about each module
  do
  {
    fprintf(log, TEXT("\n\n     MODULE NAME:     %s"),   me32.szModule );
    fprintf(log, TEXT("\n     Executable     = %s"),     me32.szExePath );
    fprintf(log, "\n     Process ID     = 0x%08X",         me32.th32ProcessID );
    fprintf(log, "\n     Ref count (g)  = 0x%04X",     me32.GlblcntUsage );
    fprintf(log, "\n     Ref count (p)  = 0x%04X",     me32.ProccntUsage );
    fprintf(log, "\n     Base address   = 0x%08X", (DWORD) me32.modBaseAddr );
    fprintf(log, "\n     Base size      = %d",             me32.modBaseSize );

  } while( Module32Next( hModuleSnap, &me32 ) );

  CloseHandle( hModuleSnap );
  return( TRUE );
}

BOOL ListProcessThreads( DWORD dwOwnerPID ) 
{ 
  HANDLE hThreadSnap = INVALID_HANDLE_VALUE; 
  THREADENTRY32 te32; 
 
  // Take a snapshot of all running threads  
  hThreadSnap = CreateToolhelp32Snapshot( TH32CS_SNAPTHREAD, 0 ); 
  if( hThreadSnap == INVALID_HANDLE_VALUE ) 
    return( FALSE ); 
 
  // Fill in the size of the structure before using it. 
  te32.dwSize = sizeof(THREADENTRY32); 
 
  // Retrieve information about the first thread,
  // and exit if unsuccessful
  if( !Thread32First( hThreadSnap, &te32 ) ) 
  {
    printError( TEXT("Thread32First") ); // show cause of failure
    CloseHandle( hThreadSnap );          // clean the snapshot object
    return( FALSE );
  }

  // Now walk the thread list of the system,
  // and display information about each thread
  // associated with the specified process
  do 
  { 
    if( te32.th32OwnerProcessID == dwOwnerPID )
    {
      fprintf(log, "\n\n     THREAD ID      = 0x%08X", te32.th32ThreadID ); 
      fprintf(log, "\n     Base priority  = %d", te32.tpBasePri ); 
      fprintf(log, "\n     Delta priority = %d", te32.tpDeltaPri ); 
    }
  } while( Thread32Next(hThreadSnap, &te32 ) ); 

  CloseHandle( hThreadSnap );
  return( TRUE );
}

void printError( TCHAR* msg )
{
  DWORD eNum;
  TCHAR sysMsg[256];
  TCHAR* p;

  eNum = GetLastError( );
  FormatMessage( FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS,
         NULL, eNum,
         MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), // Default language
         sysMsg, 256, NULL );

  // Trim the end of the line and terminate it with a null
  p = sysMsg;
  while( ( *p > 31 ) || ( *p == 9 ) )
    ++p;
  do { *p-- = 0; } while( ( p >= sysMsg ) &&
                          ( ( *p == '.' ) || ( *p < 33 ) ) );

  // Display the message
  fprintf(log, TEXT("\n  WARNING: %s failed with error %d (%s)"), msg, eNum, sysMsg );
}
*/

#endif //  __PROCESS_H__