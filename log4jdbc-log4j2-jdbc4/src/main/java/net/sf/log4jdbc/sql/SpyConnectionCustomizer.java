package net.sf.log4jdbc.sql;

import java.sql.Connection;

/**
 * Created by aliev on 21/04/15
 */
public class SpyConnectionCustomizer {
  private static volatile SpyConnectionCustomizer customizer;
  private static volatile Object lock = new Object();

  public static SpyConnectionCustomizer getInstance() {
    if (customizer == null) {
      synchronized (lock) {
        if (customizer == null) {
          customizer = new SpyConnectionCustomizer();
        }
      }
    }
    return customizer;
  }

  public void firePreExecute(long tstart, String dumpedSql, String methodCall, Connection connection) {

  }

  public void firePostExecute(long duration, String dumpedSql, String methodCall, Connection connection) {

  }

  public void fireOnException(long duration, String dumpedSql, String methodCall, Connection connection) {

  }
}
