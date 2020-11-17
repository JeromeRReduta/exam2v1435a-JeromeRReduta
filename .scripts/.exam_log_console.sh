LC_CTYPE="C.UTF-8"

cd Exam2*;

echo ""
echo "Testing Logging: ${1}"
echo ""

result=`mvn -q -ntp exec:java -Dexec.mainClass="ExamLoggerSetup" -Dexec.args="${1}"`

echo "   Level: ${1}"
echo "Expected: ${2}"
echo "Received: ${result}"
echo ""

if [[ "${2//[[:space:]]/}" == "${result//[[:space:]]/}" ]]; then
	echo "[PASS]: Results match!"
	echo ""
else
	echo "[FAIL]: Results do NOT match!"
	echo ""
	exit 1;
fi
