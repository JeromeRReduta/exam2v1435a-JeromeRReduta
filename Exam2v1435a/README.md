# Question 1: Apache Log4j2 Setup

For this code snippet question, you must create an Apache Log4j2 configuration file an an appropriate location to generate the desired output. 

  - Output only `WARN`, `ERROR`, and `FATAL` messages to the console.
  - Output `TRACE`, `DEBUG`, `INFO`, `WARN`, `ERROR`, and `FATAL` messages to a `debug.log` file.
  - Only include the log message to the console; suppress all exception output.
  - Only include the level, file name and line number (in `file:line` format), log message, and up to 2 lines from exception stack traces.

You can run the `ExamLoggerSetup` to test your log4j2 configuration locally. Look at the the autograder output to see exactly what is expected for the console and file output for various different cases.

#### Resources

  - Package [org.apache.logging.log4j](https://logging.apache.org/log4j/2.x/log4j-api/apidocs/index.html), includes [Level](https://logging.apache.org/log4j/2.x/log4j-api/apidocs/org/apache/logging/log4j/Level.html), [Logger](https://logging.apache.org/log4j/2.x/log4j-api/apidocs/org/apache/logging/log4j/Logger.html), and [LogManager](https://logging.apache.org/log4j/2.x/log4j-api/apidocs/org/apache/logging/log4j/LogManager.html) classes.
  
  - Package [org.apache.logging.log4j.core](https://logging.apache.org/log4j/2.x/log4j-core/apidocs/index.html),  includes [ConsoleAppender](https://logging.apache.org/log4j/2.x/log4j-core/apidocs/org/apache/logging/log4j/core/appender/ConsoleAppender.html), [FileAppender](https://logging.apache.org/log4j/2.x/log4j-core/apidocs/org/apache/logging/log4j/core/appender/FileAppender.html), and [PatternLayout](https://logging.apache.org/log4j/2.x/log4j-core/apidocs/org/apache/logging/log4j/core/layout/PatternLayout.html) classes.
  
  - The [Log4j2 Manual](https://logging.apache.org/log4j/2.x/manual/index.html), includes guides on [configuration](https://logging.apache.org/log4j/2.x/manual/configuration.html), [appenders](https://logging.apache.org/log4j/2.x/manual/appenders.html), and [layouts](https://logging.apache.org/log4j/2.x/manual/layouts.html).
  
  - The relevant [LoggerSetup](https://github.com/usf-cs212-fall2020/homework-LoggerSetup-template) homework template and [Debugging](https://github.com/usf-cs212-fall2020/lectures/tree/main/Debugging) lecture code.

# Question 2: Servlets and Cookies

For this code snippet question, you must fill in a method in the `ExamCookieServer` class to return URL decoded cookie values safely, using escaping to avoid cross-site scripting (XSS) attacks. Run the code in the `ExamCookieServer` class to test this example locally. You will need to open `localhost:8080` in a browser that allows cookies. There is an `Add Cookies!` button that will add some cookies to help you test the code.

#### Resources

  - Package [javax.servlet.http](https://javaee.github.io/javaee-spec/javadocs/index.html?javax/servlet/http/package-summary.html), includes [HttpServletRequest](https://javaee.github.io/javaee-spec/javadocs/index.html?javax/servlet/http/HttpServletRequest.html), [HttpServletResponse](https://javaee.github.io/javaee-spec/javadocs/index.html?javax/servlet/http/HttpServletResponse.html), and [Cookie](https://javaee.github.io/javaee-spec/javadocs/index.html?javax/servlet/http/Cookie.html) classes.

  - Package [org.eclipse.jetty](https://www.eclipse.org/jetty/javadoc/current/), includes [Server](https://www.eclipse.org/jetty/javadoc/current/org/eclipse/jetty/server/Server.html) and [ServletHandler](https://www.eclipse.org/jetty/javadoc/current/org/eclipse/jetty/servlet/ServletHandler.html) classes.

  - Package [java.net](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/net/package-summary.html), includes [URLEncoder](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/net/URLEncoder.html) and [URLDecoder](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/net/URLDecoder.html) classes.

  - Package [org.apache.commons.text](https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/package-summary.html), includes the [StringEscapeUtils](https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/StringEscapeUtils.html) class.

  - User guides for [Eclipse Jetty](https://www.eclipse.org/jetty/documentation/current/) and [Apache Commons Text](https://commons.apache.org/proper/commons-text/userguide.html).

  - The [Sessions](https://github.com/usf-cs212-fall2020/lectures/tree/main/Sessions) lecture code.
