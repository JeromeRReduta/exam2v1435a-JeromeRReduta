LC_CTYPE="C.UTF-8"
cd Exam2*;

echo ""
echo "Testing Cookie Value: ${1}"
echo ""

result=`mvn -q -ntp exec:java -Dexec.mainClass="ExamCookieServer" -Dexec.args="\"${1}"\"`

echo "Original: ${1}"
echo "Expected: ${2}"
echo "Received: ${result}"
echo ""

if [[ "${2}" == "${result}" ]]; then
	echo "[PASS]: Results match!"
	echo ""
else
	echo "[FAIL]: Results do NOT match!"
	echo ""
	exit 1;
fi
