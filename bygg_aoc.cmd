@echo off
@chcp 1252 > nul

set build.dir=%1
set src.dir=%2
set java.home=%3

if [%java.home%]==[] (
	if [%JAVA_HOME%]==[] (
		echo JAVA_HOME saknas.
		goto end		
	) else ( 
		set java.home=%JAVA_HOME%
	)
)

if [%src.dir%]==[] (
	set src.dir=.\src\aoc
)

if [%build.dir%]==[] (
	if not exist ".\build\" (
		md build
	) 
	set build.dir=.\build
)

echo Bygger Anns JDK-advent till %build.dir%

dir /B /S /A-D "%src.dir%\*.java" > .\sources.txt && "%java.home%\bin\javac" -d %build.dir% -cp %build.dir% -sourcepath %src.dir% @sources.txt 2> nul

:end
