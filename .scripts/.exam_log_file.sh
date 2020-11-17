cd Exam2*;

echo ""
echo "Testing Logging: File Output"
echo ""

expected="TRACE ExamLoggerSetup.java:29 Tart!
DEBUG ExamLoggerSetup.java:30 Danish.
INFO ExamLoggerSetup.java:31 Ice cream!
WARN ExamLoggerSetup.java:32 Walnuts?
ERROR ExamLoggerSetup.java:33 Eclair... java.lang.Exception
	at ExamLoggerSetup.main(ExamLoggerSetup.java:33)
FATAL ExamLoggerSetup.java:34 Flan. java.io.FileNotFoundException
	at ExamLoggerSetup.main(ExamLoggerSetup.java:34)"

rm -f debug.log
mvn -q -ntp exec:java -Dexec.mainClass="ExamLoggerSetup"

echo "${expected}" > expected.log

echo ""
echo "EXPECTED"
echo "------------------------------------"
echo "${expected}"
echo "------------------------------------"
echo ""

echo "ACTUAL"
echo "------------------------------------"
cat debug.log
echo "------------------------------------"
echo ""

echo "SIDE-BY-SIDE COMPARISON"
echo "------------------------------------"
diff -b -y expected.log debug.log
match=$?
echo "------------------------------------"
echo ""

if [[ ${match} -eq 0 ]]; then
  echo "[PASS]: Results match!"
  echo ""
else
  echo "[FAIL]: Results do NOT match!"
  echo ""
  exit 1;
fi
