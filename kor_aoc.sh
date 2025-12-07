#!bash

if [ -z "$1" ]; then
	echo Ange år \(tvåsiffrigt\).
	exit 1
else
	aar=$1
fi

if [ -z "$2" ]; then
	echo Ange en lucka.
	exit 1
else
	lucka=$2
fi

if [ -z "$3" ]; then
	infil=""
else
	infil=$3
fi

JAVALOC="$(which java)"

if [ -z "$6" ]; then
	if [ -z "$JAVALOC" ]; then
		echo Java saknas.
		exit 1
	else
		java=$JAVALOC
	fi
else
	java=$3/bin/java
fi

if [ -z "$5" ]; then
	src_dir=./src
else 
	src_dir=$5
fi

if [ -z "$4" ]; then
	if [ -d ./build/ ]; then
		build_dir=./build/
	else 
		mkdir build
	fi
else 
	build_dir=$4
fi

if [ ! -d "$build_dir" ]; then 
	echo Inget under /build
	exit 1
fi

$java -cp $build_dir:$src_dir/aoc/res/ aoc.AOC $aar $lucka $infil
