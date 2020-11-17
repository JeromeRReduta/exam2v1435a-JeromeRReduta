import java.io.FileNotFoundException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Simple class used to test the Apache Log4j2 logging setup. 
 */
public class ExamLoggerSetup {
  /*
   * TODO EXAM INSTRUCTIONS:
   * Do NOT modify this class. Instead create a log configuration file in the
   * correct location to pass the tests. See the README for the exact output.
   */
  
  /** Logger used by this class. */
  public static final Logger log = LogManager.getLogger();
    
  /**
   * Outputs log messages of every level if run without any arguments.
   * Otherwise, uses the provided arguments to test the logging setup.
   * 
   * @param args optionally used for testing
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      // output all log levels for local debugging
      log.trace("Tart!");
      log.debug("Danish.");
      log.info("Ice cream!");
      log.warn("Walnuts?");
      log.error("Eclair...", new Exception());
      log.fatal("Flan.", new FileNotFoundException());
    }
    else {
      assert args.length > 0;
      String name = args[0];
      String message = args[1];
      
      // output a specific log level for remote testing
      Level level = Level.getLevel(name);
      
      if (level.isMoreSpecificThan(Level.WARN)) {
        log.log(level, message, new Exception(message));
      }
      else {
        log.log(level, message);
      }
    }
  }
}
