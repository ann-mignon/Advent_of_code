#!bash

JAVAC_LOC="$(which javac)"

if [ -z "$3" ]; then
	if [ -z "$JAVAC_LOC" ]; then
		echo Javac saknas.
		exit 1
	else
		javac=$JAVAC_LOC
	fi
else 
	javac=$3/bin/javac
fi

if [ -z "$2" ]; then
	src_dir=./src/aoc
else 
	src_dir=$2
fi

if [ -z "$1" ]; then
	build_dir=./build
else 
	build_dir=$1
fi

find . -path  "$src_dir/*.java" > sources.txt && $javac -d $build_dir -cp $build_dir -sourcepath $src_dir @sources.txt
