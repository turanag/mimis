#!/bin/bash

MODE=Release
if [ $# = 1 ]; then
	MODE=$1
	echo using mode $MODE
fi

N=0
F=0
S=0
N_MAX=1000000
OUTFILE=$MODE/wiitest.out

echo "##" Testing wiiscan... "(N_MAX="$N_MAX",OUTFILE="$OUTFILE",mode="$MODE")" | tee $OUTFILE
echo -n "## date "  | tee -a $OUTFILE
date | tee -a $OUTFILE

while [  $N -lt $N_MAX ]; do
	let N=N+1 
	echo "##" Loop $N " " | tee -a $OUTFILE
	echo -n "## date "  | tee -a $OUTFILE
	date | tee -a $OUTFILE
	
	rm -f $OUTFILE.tmp 2>/dev/null
	
	pushd $MODE ; wiiscan -f -v -lf ../$OUTFILE.tmp 2>&1 ; R1=$? ; popd
	# pushd $MODE ; wiiscan -v -d nintendo -lf ../$OUTFILE.tmp  ; R2=$?  ;  popd
	R2=0;
	
	cat $OUTFILE.tmp >> $OUTFILE
	
	if [ $R1 = 0 ]; then
		let S=S+1
		echo "##" Test succes $S | tee -a $OUTFILE		
	else
		let F=F+1
		echo "##" Test failed $F | tee -a $OUTFILE
	fi
		
	STATUS=$(echo $S*100/$N | bc)
	echo "##" Status $S/$N = $STATUS "%" | tee -a $OUTFILE
	echo "## (result R1="$R1 " R2="$R2 " S="$S " F="$F " N="$N ")" | tee -a $OUTFILE
	
	sleep 4
done

echo done | tee -a $OUTFILE
exit 0
