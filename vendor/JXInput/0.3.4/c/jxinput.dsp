# Microsoft Developer Studio Project File - Name="jxinput" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 6.00
# ** NICHT BEARBEITEN **

# TARGTYPE "Win32 (x86) Dynamic-Link Library" 0x0102

CFG=jxinput - Win32 Debug
!MESSAGE Dies ist kein g�ltiges Makefile. Zum Erstellen dieses Projekts mit NMAKE
!MESSAGE verwenden Sie den Befehl "Makefile exportieren" und f�hren Sie den Befehl
!MESSAGE 
!MESSAGE NMAKE /f "jxinput.mak".
!MESSAGE 
!MESSAGE Sie k�nnen beim Ausf�hren von NMAKE eine Konfiguration angeben
!MESSAGE durch Definieren des Makros CFG in der Befehlszeile. Zum Beispiel:
!MESSAGE 
!MESSAGE NMAKE /f "jxinput.mak" CFG="jxinput - Win32 Debug"
!MESSAGE 
!MESSAGE F�r die Konfiguration stehen zur Auswahl:
!MESSAGE 
!MESSAGE "jxinput - Win32 Release" (basierend auf  "Win32 (x86) Dynamic-Link Library")
!MESSAGE "jxinput - Win32 Debug" (basierend auf  "Win32 (x86) Dynamic-Link Library")
!MESSAGE 

# Begin Project
# PROP AllowPerConfigDependencies 0
# PROP Scc_ProjName ""
# PROP Scc_LocalPath ""
CPP=cl.exe
MTL=midl.exe
RSC=rc.exe

!IF  "$(CFG)" == "jxinput - Win32 Release"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 0
# PROP BASE Output_Dir "Release"
# PROP BASE Intermediate_Dir "Release"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 0
# PROP Output_Dir "Release"
# PROP Intermediate_Dir "Release"
# PROP Ignore_Export_Lib 0
# PROP Target_Dir ""
# ADD BASE CPP /nologo /MT /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_WINDOWS" /D "_MBCS" /D "_USRDLL" /D "JXINPUT_EXPORTS" /Yu"stdafx.h" /FD /c
# ADD CPP /nologo /MT /W3 /GX /O2 /I "C:\j2sdk1.4.2\include" /I "C:\j2sdk1.4.2\include\win32" /D "WIN32" /D "NDEBUG" /D "_WINDOWS" /D "_MBCS" /D "_USRDLL" /D "JXINPUT_EXPORTS" /FR /Yu"stdafx.h" /FD /c
# ADD BASE MTL /nologo /D "NDEBUG" /mktyplib203 /win32
# ADD MTL /nologo /D "NDEBUG" /mktyplib203 /win32
# ADD BASE RSC /l 0x407 /d "NDEBUG"
# ADD RSC /l 0x407 /d "NDEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /dll /machine:I386
# ADD LINK32 dxguid.lib dinput8.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /dll /machine:I386 /out:"..\build\jxinput.dll"

!ELSEIF  "$(CFG)" == "jxinput - Win32 Debug"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 1
# PROP BASE Output_Dir "Debug"
# PROP BASE Intermediate_Dir "Debug"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 1
# PROP Output_Dir "Debug"
# PROP Intermediate_Dir "Debug"
# PROP Ignore_Export_Lib 0
# PROP Target_Dir ""
# ADD BASE CPP /nologo /MTd /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_WINDOWS" /D "_MBCS" /D "_USRDLL" /D "JXINPUT_EXPORTS" /Yu"stdafx.h" /FD /GZ /c
# ADD CPP /nologo /MTd /W3 /Gm /GX /ZI /Od /I "C:\j2sdk1.4.2\include" /I "C:\j2sdk1.4.2\include\win32" /D "WIN32" /D "_DEBUG" /D "_WINDOWS" /D "_MBCS" /D "_USRDLL" /D "JXINPUT_EXPORTS" /FR /Yu"stdafx.h" /FD /GZ /c
# ADD BASE MTL /nologo /D "_DEBUG" /mktyplib203 /win32
# ADD MTL /nologo /D "_DEBUG" /mktyplib203 /win32
# ADD BASE RSC /l 0x407 /d "_DEBUG"
# ADD RSC /l 0x407 /d "_DEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /dll /debug /machine:I386 /pdbtype:sept
# ADD LINK32 dxguid.lib dinput8.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /dll /debug /machine:I386 /out:"..\Classes\jxinput.dll" /pdbtype:sept

!ENDIF 

# Begin Target

# Name "jxinput - Win32 Release"
# Name "jxinput - Win32 Debug"
# Begin Group "Quellcodedateien"

# PROP Default_Filter "cpp;c;cxx;rc;def;r;odl;idl;hpj;bat"
# Begin Source File

SOURCE=.\de_hardcode_jxinput_directinput_DirectInputDriver.cpp
# End Source File
# Begin Source File

SOURCE=.\dllmain.cpp
# End Source File
# Begin Source File

SOURCE=.\jxinput.cpp
# End Source File
# Begin Source File

SOURCE=.\JXInputManager.cpp
# End Source File
# Begin Source File

SOURCE=.\StdAfx.cpp
# ADD CPP /Yc"stdafx.h"
# End Source File
# End Group
# Begin Group "Header-Dateien"

# PROP Default_Filter "h;hpp;hxx;hm;inl"
# Begin Source File

SOURCE=.\de_hardcode_jxinput_directinput_DirectInputDriver.h

!IF  "$(CFG)" == "jxinput - Win32 Release"

# PROP Ignore_Default_Tool 1
USERDEP__DE_HA="..\classes\de\hardcode\jxinput\directinput\DirectInputDriver.class"	
# Begin Custom Build
InputPath=.\de_hardcode_jxinput_directinput_DirectInputDriver.h

"de_hardcode_jxinput_directinput_DirectInputDriver.h" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	cd ..\Classes 
	C:\j2sdk1.4.2\bin\javah -classpath . -d ..\win32 de.hardcode.jxinput.directinput.DirectInputDriver 
	cd ..\win32 
	
# End Custom Build

!ELSEIF  "$(CFG)" == "jxinput - Win32 Debug"

# PROP Ignore_Default_Tool 1
USERDEP__DE_HA="..\classes\de\hardcode\jxinput\directinput\DirectInputDriver.class"	
# Begin Custom Build
InputPath=.\de_hardcode_jxinput_directinput_DirectInputDriver.h

"de_hardcode_jxinput_directinput_DirectInputDriver.h" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	cd ..\Classes 
	C:\j2sdk1.4.2\bin\javah -classpath . -d ..\win32 de.hardcode.jxinput.directinput.DirectInputDriver 
	cd ..\win32 
	
# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\jxinput.h
# End Source File
# Begin Source File

SOURCE=.\JXInputManager.h
# End Source File
# Begin Source File

SOURCE=.\StdAfx.h
# End Source File
# End Group
# Begin Group "Ressourcendateien"

# PROP Default_Filter "ico;cur;bmp;dlg;rc2;rct;bin;rgs;gif;jpg;jpeg;jpe"
# End Group
# Begin Source File

SOURCE=.\ReadMe.txt
# End Source File
# End Target
# End Project
