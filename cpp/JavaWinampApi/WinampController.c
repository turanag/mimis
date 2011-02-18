/* meu .h */
#include "WinampController.h"

/* mingw */
#include <windows.h>
#include <w32api.h>
#include <winuser.h>

/* winamp sdk */
#include "wa_ipc.h"
#include "WINAMPCMD.H"

const int WA_CLOSE = 40001 ;
const int WA_PLAY = WINAMP_BUTTON2;
const int WA_STOP = WINAMP_BUTTON4;
const int WA_PAUSE = WINAMP_BUTTON3;
const int WA_PREVTRACK = WINAMP_BUTTON1;
const int WA_NEXTTRACK = WINAMP_BUTTON5;
const int WA_FWD5SECS  = WINAMP_FFWD5S;
const int WA_REW5SECS  = WINAMP_REW5S;

const int WA_PLAYLISTLEN = IPC_GETLISTLENGTH;
const int WA_SETVOLUME = IPC_SETVOLUME;
const int WA_SETPLAYLISTPOS = IPC_SETPLAYLISTPOS;
const int WA_WRITEPLAYLIST  = IPC_WRITEPLAYLIST;
const int WA_ENQUEUEFILE = IPC_ENQUEUEFILE;

const int WA_VOLUMEUP = WINAMP_VOLUMEUP;
const int WA_VOLUMEDOWN = WINAMP_VOLUMEDOWN;

const int WA_CLEARPLAYLIST = IPC_DELETE;
const int WA_NOTHING = 0;
const int WA_TRACK_LENGTH = 1;

const int WA_RESTART = IPC_RESTARTWINAMP;
const int WA_REFRESHPLCACHE = IPC_REFRESHPLCACHE;
const int WA_GETSHUFFLESTATUS = IPC_GET_SHUFFLE;
const int WA_GETREPEATSTATUS = IPC_GET_REPEAT;
const int WA_SETSHUFFLESTATUS = IPC_SET_SHUFFLE;
const int WA_SETREPEATSTATUS = IPC_SET_REPEAT;

const int WA_GETSTATUS = IPC_ISPLAYING;

const int WA_GETLISTPOS = IPC_GETLISTPOS;
const int WA_GETTITLE = IPC_GETPLAYLISTTITLE;

const int WA_VERSION  = IPC_GETVERSION;
const int WA_FILENAMEINLIST = IPC_GETPLAYLISTFILE;
const int WA_GETFILEINFO = IPC_GET_EXTENDED_FILE_INFO;

HWND hwnd_winamp = NULL;
INT  position    = 0;
STARTUPINFO si;
PROCESS_INFORMATION pi;
char messageReturn[255];
wchar_t* wMessageReturn;

LPDWORD temp;

void initWinampHandle() {
     hwnd_winamp = NULL; 
     if (hwnd_winamp == NULL) {
        hwnd_winamp = FindWindow("Winamp v1.x", NULL);
     }
     if (hwnd_winamp == NULL) {
        hwnd_winamp = FindWindow("Winamp v2.x", NULL);
     }
     if (hwnd_winamp == NULL) {
        hwnd_winamp = FindWindow("Winamp v3.x", NULL);
     }
}

jboolean runWinamp(unsigned char* pathWinamp) {
     
      /*  STARTUPINFO si;
        PROCESS_INFORMATION pi;*/
            
        ZeroMemory( &si, sizeof(si) );
        si.cb = sizeof(si);
        ZeroMemory( &pi, sizeof(pi) );

    
        // Start the child process. 
        if(!CreateProcess(pathWinamp, 
                          NULL, 
                          0, 
                          0, 
                          FALSE, 
                          CREATE_NEW_CONSOLE, 
                          0, 
                          0, 
                          &si, 
                          &pi)) 
        {
            return FALSE;
        }
        
        DWORD dwResult = WaitForInputIdle(pi.hProcess,INFINITE);
        if (dwResult != 0) return FALSE;
        
        CloseHandle(pi.hProcess);
        CloseHandle(pi.hThread);
        
        return TRUE;
     
     
}

int getListPos() {
          
          initWinampHandle();
          if (hwnd_winamp != NULL) {

             return SendMessage(hwnd_winamp,WM_USER,WA_NOTHING,WA_GETLISTPOS);
             
          }
          return -1;
                              
}

void getPluginMessage(int param, int sendMessage)
{
     
        LPCVOID message = (LPCVOID)SendMessageW(hwnd_winamp, WM_USER, param, sendMessage);
        ZeroMemory( &pi, sizeof(pi));
        GetWindowThreadProcessId(hwnd_winamp, &pi.dwThreadId);
        pi.hProcess = OpenProcess(PROCESS_ALL_ACCESS,FALSE,pi.dwThreadId);          
        ReadProcessMemory(pi.hProcess, message, messageReturn,2056,temp);
        free(temp);
        CloseHandle(pi.hProcess);
        CloseHandle(pi.hThread);     
     
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_run
  (JNIEnv *env, jobject obj) {
          
          initWinampHandle();

          if ( hwnd_winamp == NULL ) {
                         
                unsigned char path[MAX_PATH]="";
        	    DWORD size = MAX_PATH;
        
             	HKEY  key;
                DWORD tipo;
        
                if (!RegOpenKey(HKEY_LOCAL_MACHINE,"Software\\Clients\\Media\\Winamp\\shell\\open\\command",&key)==ERROR_SUCCESS) 
                {
                
                   printf("0");
                   return FALSE;                                                                        
                                                                                           
                }
                   
             	if (!(RegQueryValueEx(key,"",NULL,&tipo,path,&size))==ERROR_SUCCESS)
             	{

                   RegCloseKey(key);
                   return FALSE;                                                                        
                    
                }
                
                if (!runWinamp(path)) 
                {
                   
                   RegCloseKey(key);
                   return FALSE;                      
                                      
                }
                return TRUE;
                
          }
          
          int version = SendMessage(hwnd_winamp,WM_WA_IPC,0,IPC_GETVERSION);
          return TRUE;
          
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_exit
  (JNIEnv *env, jobject obj) {

      initWinampHandle();  
      if (hwnd_winamp != NULL) {      
         SendMessageA(hwnd_winamp, WM_COMMAND, WA_CLOSE, WA_NOTHING);
         return TRUE;
      }
      
      return FALSE;

          
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_play
  (JNIEnv *env, jobject obj) {
          initWinampHandle();
          if (hwnd_winamp != NULL) {
                          
             SendMessageA(hwnd_winamp, WM_COMMAND, WA_PLAY, WA_NOTHING);
             return TRUE;
             
          }
          
          return FALSE;
}


JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_stop
  (JNIEnv *env, jobject obj) 
{
          initWinampHandle();
          if (hwnd_winamp != NULL) {
             SendMessageA(hwnd_winamp, WM_COMMAND, WA_STOP, WA_NOTHING);
             return TRUE;
          }
          
          return FALSE;
}


JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_resume
  (JNIEnv *env, jobject obj) {
          initWinampHandle();
          if (hwnd_winamp != NULL) {
              SendMessageA(hwnd_winamp, WM_COMMAND, WA_PAUSE, WA_NOTHING);
              return TRUE;
          }
          
          return FALSE;
}


JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_pause
  (JNIEnv *env, jobject obj) {
          initWinampHandle();
          if (hwnd_winamp != NULL) {
             SendMessageA(hwnd_winamp, WM_COMMAND, WA_PAUSE, WA_NOTHING);
             return TRUE;
          }
          
          return FALSE;
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_previousTrack
  (JNIEnv *env, jobject obj) {
          initWinampHandle();
          if (hwnd_winamp != NULL) {
            SendMessageA(hwnd_winamp, WM_COMMAND, WA_PREVTRACK, WA_NOTHING);
            return TRUE;
          }
          
          return FALSE;
          
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_nextTrack
  (JNIEnv *env, jobject obj) {
          initWinampHandle();
          if (hwnd_winamp != NULL) {
                SendMessageA(hwnd_winamp, WM_COMMAND, WA_NEXTTRACK, WA_NOTHING);
                return TRUE;
          }
          
          return FALSE;
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_fwd5Secs
  (JNIEnv *env, jobject obj) {
          initWinampHandle();
          if (hwnd_winamp != NULL) {
                SendMessageA(hwnd_winamp, WM_COMMAND, WA_FWD5SECS, WA_NOTHING);
                return TRUE;
          }
          
          return FALSE;
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_rew5Secs
  (JNIEnv *env, jobject obj) {
          initWinampHandle();
          if (hwnd_winamp != NULL) {
                SendMessageA(hwnd_winamp, WM_COMMAND, WA_REW5SECS, WA_NOTHING);
                return TRUE;
          }
          
          return FALSE;
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_increaseVolume
  (JNIEnv *env, jobject obj) {
          initWinampHandle();
          if (hwnd_winamp != NULL) {
             SendMessageA(hwnd_winamp, WM_COMMAND, WA_VOLUMEUP, WA_NOTHING);
             return TRUE;
          }
          
          return FALSE;
          
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_decreaseVolume
  (JNIEnv *env, jobject obj) {
          initWinampHandle();
          if (hwnd_winamp != NULL) {
             SendMessageA(hwnd_winamp, WM_COMMAND, WA_VOLUMEDOWN, WA_NOTHING);
             return TRUE;
          }
          
          return FALSE;
          
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_increaseVolumePercent
  (JNIEnv *env, jobject obj, jint percent) {
          initWinampHandle();
          int i = 0;
          if (hwnd_winamp != NULL) {
             for(i=0;i<percent;i++) {
                SendMessageA(hwnd_winamp, WM_COMMAND, WA_VOLUMEUP, WA_NOTHING);
             }
             return TRUE;
          }
          
          return FALSE;
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_decreaseVolumePercent
  (JNIEnv *env, jobject obj, jint percent) {
           initWinampHandle();
          int i = 0;
          if (hwnd_winamp != NULL) {
             for(i=0;i<percent;i++) {
                SendMessageA(hwnd_winamp, WM_COMMAND, WA_VOLUMEDOWN, WA_NOTHING);
             }
             return TRUE;
          }
          
          return FALSE;
          
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_setVolume
  (JNIEnv *env, jobject obj, jint pos) {
          initWinampHandle();
          if (hwnd_winamp != NULL) {
             SendMessageA(hwnd_winamp, WM_USER, pos, WA_SETVOLUME);
             return TRUE;
          }
          
          return FALSE;
          
}

JNIEXPORT jint JNICALL Java_com_qotsa_jni_controller_JNIWinamp_getVolume
  (JNIEnv *env, jobject obj, jint pos) {
          jint curVolume = -1;
          initWinampHandle();
          if (hwnd_winamp != NULL) {
             curVolume = (jint)SendMessageA(hwnd_winamp, WM_USER, -666, WA_SETVOLUME);
          }            
          
          return curVolume;
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_restart
  (JNIEnv *env, jobject obj) {
          initWinampHandle();
          if (hwnd_winamp != NULL) {
             SendMessageA(hwnd_winamp, WM_USER, WA_NOTHING, WA_RESTART);
             return TRUE;
          }
          
          return FALSE;
          
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_setPlaylistPosition
  (JNIEnv *env, jobject obj, jint pos) {
          initWinampHandle();
          if (hwnd_winamp != NULL) {
             SendMessageA(hwnd_winamp, WM_USER, pos, WA_SETPLAYLISTPOS);
             return TRUE;
          }
          
          return FALSE;
          
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_clearPlayList
  (JNIEnv *env, jobject obj) {
          initWinampHandle();
          if (hwnd_winamp != NULL) {
             SendMessageA(hwnd_winamp, WM_USER, WA_NOTHING, WA_CLEARPLAYLIST);
             return TRUE;
          }
          
          return FALSE;
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_refreshPlayListCache
  (JNIEnv *env, jobject obj) {
          initWinampHandle();
          if (hwnd_winamp != NULL) {
             SendMessageA(hwnd_winamp, WM_USER, WA_NOTHING, WA_REFRESHPLCACHE);
             return TRUE;
          }
          
          return FALSE;
          
}

JNIEXPORT jint JNICALL Java_com_qotsa_jni_controller_JNIWinamp_getPlayListLength
  (JNIEnv *env, jobject obj) {
          jint length = -1;
          initWinampHandle();
          if (hwnd_winamp != NULL) {
             length = (jint)SendMessageA(hwnd_winamp, WM_USER, WA_NOTHING, WA_PLAYLISTLEN);
          }            
          
          return length;
}

JNIEXPORT jint JNICALL Java_com_qotsa_jni_controller_JNIWinamp_writePlayListToFile
  (JNIEnv *env, jobject obj) {
          jint length = -1;
          initWinampHandle();
          if (hwnd_winamp != NULL) {
             length = SendMessageA(hwnd_winamp, WM_USER, WA_NOTHING, WA_WRITEPLAYLIST);
          }           
          return length;
}

JNIEXPORT jint JNICALL Java_com_qotsa_jni_controller_JNIWinamp_isShuffleStatusOn
  (JNIEnv *env, jobject obj) {
          jint status = 0;
          initWinampHandle();
          if (hwnd_winamp != NULL) {
             status = (jint)SendMessageA(hwnd_winamp, WM_USER, WA_NOTHING, WA_GETSHUFFLESTATUS);
          } else
            return -1;
          
          return status>0?1:0;
}

JNIEXPORT jint JNICALL Java_com_qotsa_jni_controller_JNIWinamp_isRepeatStatusOn
  (JNIEnv *env, jobject obj) {
          jint status = 0;
          initWinampHandle();
          if (hwnd_winamp != NULL) {
             status = (jint)SendMessageA(hwnd_winamp, WM_USER, WA_NOTHING, WA_GETREPEATSTATUS);
          } else
           return -1;
          
          return status>0?1:0;
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_setRepeatStatusOn
  (JNIEnv *env, jobject obj, jboolean status) {
          initWinampHandle();
          if (hwnd_winamp != NULL) {
             SendMessageA(hwnd_winamp, WM_USER, status, WA_SETREPEATSTATUS);
             return TRUE;
          }
          
          return FALSE;
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_setShuffleStatusOn
  (JNIEnv *env, jobject obj, jboolean status) {
          initWinampHandle();
          if (hwnd_winamp != NULL) {
             SendMessageA(hwnd_winamp, WM_USER, status, WA_SETSHUFFLESTATUS);
             return TRUE;
          }
          
          return FALSE;
}

JNIEXPORT jboolean JNICALL Java_com_qotsa_jni_controller_JNIWinamp_appendToPlayList
  (JNIEnv *env, jobject obj, jstring mp3filename) {
          initWinampHandle();
          jboolean iscopy;
          if (hwnd_winamp != NULL) {
             wMessageReturn = (wchar_t*)(*env)->GetStringChars(env, mp3filename, &iscopy);
             int length = wcslen(wMessageReturn);
             COPYDATASTRUCT cds;
             cds.dwData = IPC_PLAYFILEW;
             cds.lpData =  (void*)wMessageReturn;
             cds.cbData =  length * 2 + 2 ; // it sums white space
             
             SendMessageW(hwnd_winamp, WM_COPYDATA, WA_NOTHING, (LPARAM)&cds);
             return TRUE;
          }
          
          return FALSE;
          
}

JNIEXPORT jint JNICALL Java_com_qotsa_jni_controller_JNIWinamp_getStatus
  (JNIEnv *env, jobject obj) {
          jint status = -1;
          initWinampHandle();
          if (hwnd_winamp != NULL) {
             status = SendMessageA(hwnd_winamp, WM_USER, WA_NOTHING, WA_GETSTATUS);
          } 
          
          return status; 
          
          
}

JNIEXPORT jint JNICALL Java_com_qotsa_jni_controller_JNIWinamp_getListPos
  (JNIEnv *env, jobject obj) {

          return getListPos();          
          
}

JNIEXPORT jstring JNICALL Java_com_qotsa_jni_controller_JNIWinamp_getTitle
  (JNIEnv *env, jobject obj) {

          initWinampHandle();
          if (hwnd_winamp != NULL) {
          
             char title[500] = "";
             GetWindowText(hwnd_winamp,title,500);
             return (*env)->NewStringUTF(env,title);
                         
                                       
          }
          
          return NULL;

}

JNIEXPORT jint JNICALL Java_com_qotsa_jni_controller_JNIWinamp_getTime
  (JNIEnv *env, jobject obj, jint mode) {
          
          initWinampHandle();
          if (hwnd_winamp != NULL) {
          
             return SendMessage(hwnd_winamp,WM_USER,mode,IPC_GETOUTPUTTIME);
                          
          }
          
          return -2;
          
}

JNIEXPORT jstring JNICALL Java_com_qotsa_jni_controller_JNIWinamp_getFileNameInList
  (JNIEnv *env, jobject obj, jint index)
{

    initWinampHandle();
    
    if (hwnd_winamp != NULL) {	
	
	    getPluginMessage(index, WA_FILENAMEINLIST);
        char* filePath = messageReturn;
        jstring strReturn = (*env)->NewStringUTF(env,filePath);
        return strReturn;
            
    }
    
    return NULL;
    
}

JNIEXPORT jstring JNICALL Java_com_qotsa_jni_controller_JNIWinamp_getFileNamePlaying
  (JNIEnv *env, jobject obj)
{

    initWinampHandle();
    
    if (hwnd_winamp != NULL) {	
	
        getPluginMessage(WA_NOTHING, IPC_GET_PLAYING_FILENAME);
        wchar_t* fileName = (wchar_t*)messageReturn;
        int length = wcslen(fileName);
        jstring strReturn = (*env)->NewString(env,fileName,length);
        return strReturn;
            
    }
    
    return NULL;
    
}
