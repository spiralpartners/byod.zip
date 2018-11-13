@echo off

IF EXIST "%USERPROFILE%\oithomes\java\" (
cd %USERPROFILE%\oithomes\java
)
@set MSYSTEM=MINGW64
@C:\oit\PortableGit-2.19.1-64\usr\bin\mintty.exe C:\oit\PortableGit-2.19.1-64\usr\bin\bash.exe --login -i