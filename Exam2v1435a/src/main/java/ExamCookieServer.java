import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * Runs a Jetty server and servlet that safely displays cookie values.
 */
public class ExamCookieServer {
  /*
   * TODO EXAM INSTRUCTIONS:
   * ONLY modify the method marked with a "TODO" comment. Do not modify any
   * other methods or change any method signatures. You may, if necessary, add
   * additional methods or members.
   */

  /**
   * Returns the decoded cookie value SAFELY (i.e. avoids cross-site scripting
   * or XSS attacks using escaping). 
   *  
   * @param cookie the cookie to get the value from
   * @return the safe, decoded cookie value 
   */
  public static String getSafeValue(Cookie cookie) {
	  System.out.println("Bubba");
    // TODO Modify this method implementation to return the correct output.
    // TODO The implementation should be no more than 5 lines of code.
     return null;
  }
  
  /*
   * Everything below this is primarily to help run a Jetty web server and test 
   * locally. It can be useful to look at to understand what the code expects,
   * but is not necessary to fully understand the code to answer this question. 
   */
  
  /**
   * Creates a cookie with the specified name and value. The value is URL
   * encoded to work with version 0 or version 1 cookies.
   * 
   * @param name the name of the cookie
   * @param value the raw value of the cookie (un-encoded)
   * @return the cookie
   * 
   * @see URLEncoder#encode(String, Charset)
   * @see CookieServlet#doPost(HttpServletRequest, HttpServletResponse)
   */
  public static Cookie createCookie(String name, String value) {
    var utf8 = StandardCharsets.UTF_8;
    var encoded = URLEncoder.encode(value, utf8);
    
    var cookie = new Cookie(name, encoded);
    cookie.setVersion(0);
    cookie.setMaxAge(1800);
    
    return cookie;
  }
  
  /**
   * If no arguments are provided, starts the Jetty web server on port 8080.
   * Open http://localhost:8080/ in your browser to view this example.
   * 
   * @param args optional first argument used for testing
   * @throws Exception if unable to run server
   */
  public static void main(String[] args) throws Exception {
    // start Jetty server for local testing if no args provided
    if (args.length == 0) {
      ServletHandler handler = new ServletHandler();
      handler.addServletWithMapping(CookieServlet.class, "/");

      Server server = new Server(8080);
      server.setHandler(handler);
      server.start();
      server.join();
    }

    // use the first argument for remote testing
    assert args.length > 0;
    Cookie test = createCookie("Test", args[0]);
    System.out.println(getSafeValue(test));
  }

  /**
   * On GET, safely displays the name and value of all cookies returned by the
   * client (browser). On POST, adds some test cookies to the client (browser).
   */
  public static class CookieServlet extends HttpServlet {
    /** Class version for serialization, in [YEAR][TERM] format (unused). */
    private static final long serialVersionUID = 2020801435;

    /**
     * On an HTTP POST request, adds several cookies. Useful for testing if the
     * cookie name and values are being displayed correctly.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
      // add cookie with visited date and time 
      ZonedDateTime today = ZonedDateTime.now();
      String visited = today.format(DateTimeFormatter.RFC_1123_DATE_TIME);
      response.addCookie(createCookie("Visited", visited));

      // add cookie with greeting
      String greeting = (today.getHour() >= 12) ? "Good day!" : "Good morning.";
      response.addCookie(createCookie("Greeting", greeting));

      // add cookie with javascript
      String javascript = "<script>alert(\"" + greeting + "\");</script>";
      response.addCookie(createCookie("Javascript", javascript));

      // add cookie with html
      String html = "hello <!-- world -->";
      response.addCookie(createCookie("HTML", html));

      // redirect and redisplay form
      response.sendRedirect(request.getRequestURI());
    }    
    
    /**
     * On a HTTP GET request, displays all of the cookies included by the client
     * (browser). Useful for local testing.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
      PrintWriter writer = response.getWriter();
      Cookie[] cookies = request.getCookies();
      
      writer.printf("<html>%n%n");
      writer.printf("<head>%n");
      writer.printf("    <title>%s</title>%n", "Cookies!");
      writer.printf("</head>%n%n");
      writer.printf("<body>%n%n");

      if (cookies == null || cookies.length == 0) {
        writer.printf("<h1>%s</h1>%n", "No Cookies!");
        writer.printf("<p>Press the button below to add some test cookies.</p>%n");
      }
      else {
        writer.printf("<h1>%s</h1>%n%n", "Cookies!");

        // output start of table
        writer.printf("<table>%n");
        writer.printf("    <thead>%n");
        writer.printf("        <tr>%n");
        writer.printf("            <th>%s</th>%n", "Cookie Name");
        writer.printf("            <th>%s</th>%n", "Cookie Value");
        writer.printf("        </tr>%n");
        writer.printf("    </thead>%n%n");
        writer.printf("    <tbody>%n");

        // output each row
        for (Cookie cookie : cookies) {
          // get cookie name
          String name = cookie.getName();

          // this uses the method you must implement
          String value = getSafeValue(cookie); 

          // display name and value on table
          writer.printf("        <tr>%n");
          writer.printf("            <td>%s</td>%n", name);
          writer.printf("            <td>%s</td>%n", value);
          writer.printf("        </tr>%n");
        }

        writer.printf("    </tbody>%n");
        writer.printf("</table>%n%n");
      }

      writer.printf("<br/>%n%n");
      writer.printf("<form method=\"%s\" action=\"%s\">%n", "POST", "/");
      writer.printf("    <button>%s</button>%n", "Add Cookies!");
      writer.printf("</form>%n%n");

      writer.printf("</body>%n");
      writer.printf("</html>%n");

      response.setStatus(HttpServletResponse.SC_OK);
      response.flushBuffer();
    }
  }
  
  /*
   * The members below are here just to make sure any useful packages
   * are imported properly. Your code does not necessarily need to use
   * any or all of these.
   */

  /** Placeholder to make sure useful classes are imported. */
  public static final Class<URLEncoder> ENCODER = URLEncoder.class;
  
  /** Placeholder to make sure useful classes are imported. */
  public static final Class<URLDecoder> DECODER = URLDecoder.class;
  
  /** Placeholder to make sure useful classes are imported. */
  public static final Class<StringEscapeUtils> ESCAPER = StringEscapeUtils.class;
    
  /** Placeholder to make sure useful classes are imported. */
  public static final Class<Charset> CHARSET = Charset.class;
}
