@echo off
@chcp 1252 > nul

set aar=%1
set lucka=%2
set infil=%3
set build.dir=%4
set src.dir=%5
set java.home=%6

if [%aar%]==[] (
	echo "Ange år (tvåsiffrigt)."
	goto end
)

if [%lucka%]==[] (
	echo Ange en lucka.
	goto end
)

if [%java.home%]==[] (
	if [%JAVA_HOME%]==[] (
		echo JAVA_HOME saknas.
		goto end		
	) else (
		set java.home=%JAVA_HOME%
	)
)

if [%src.dir%]==[] (
	set src.dir=.\src
)

if [%build.dir%]==[] (
	if not exist ".\build\" (
		echo Inget under \build
		goto end
	) else (
		set build.dir=.\build\
	)
)

%java.home%/bin/java -cp %build.dir%;%src.dir%\aoc\res\ aoc.AOC %aar% %lucka% %infil%

:end
