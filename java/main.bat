@echo off
set path=%path%;native
java -cp bin;cfg;resource;lib/commons-logging-1.1.1.jar;lib/jacob-1.15-M3.jar;lib/TableLayout.jar;lib/nativecall-0.4.1.jar;lib/nativeloader-200505172341.jar mimis.Main
pause