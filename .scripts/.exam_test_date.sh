cd Exam2*;

echo ""
echo "Testing Cookie Value: Today's Date..."
echo ""

current=`date +'%A, %B %d %Y %T %Z'`
result=`mvn -q -ntp exec:java -Dexec.mainClass="ExamCookieServer" -Dexec.args="\"$current\""`

echo "Received: ${result}"
echo "Expected: ${current}"
echo ""

if [[ "${current}" == "${result}" ]]; then
	echo "[PASS]: Results match!"
	echo ""
else
	echo "[FAIL]: Results do NOT match!"
	echo ""
	exit 1;
fi
