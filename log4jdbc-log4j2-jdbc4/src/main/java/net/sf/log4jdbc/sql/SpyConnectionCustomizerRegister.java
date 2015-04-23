package net.sf.log4jdbc.sql;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by aliev on 21/04/15
 */
public class SpyConnectionCustomizerRegister {
  private static volatile SpyConnectionCustomizerRegister customizer;
  private static volatile Object lock = new Object();
  private List<SpyConnectionCustomizer> connectionCustomizerList=new CopyOnWriteArrayList<SpyConnectionCustomizer>();

  public static SpyConnectionCustomizerRegister getInstance() {
    if (customizer == null) {
      synchronized (lock) {
        if (customizer == null) {
          customizer = new SpyConnectionCustomizerRegister();
        }
      }
    }
    return customizer;
  }

  public void firePreExecute(long tstart, String dumpedSql, String methodCall, Connection connection) {
    Iterator<SpyConnectionCustomizer> iterator = connectionCustomizerList.iterator();
    while (iterator.hasNext()) {
      SpyConnectionCustomizer cc = iterator.next();
      cc.onPreExecute(connection, tstart, dumpedSql, methodCall);
    }
  }

  public void firePostExecute(long duration, String dumpedSql, String methodCall, Connection connection) {
    Iterator<SpyConnectionCustomizer> iterator = connectionCustomizerList.iterator();
    while (iterator.hasNext()) {
      SpyConnectionCustomizer cc = iterator.next();
      cc.onPostExecute(connection, duration, dumpedSql, methodCall);
    }
  }

  public void fireOnException(long duration, String dumpedSql, String methodCall, Connection connection) {
    Iterator<SpyConnectionCustomizer> iterator = connectionCustomizerList.iterator();
    while (iterator.hasNext()) {
      SpyConnectionCustomizer cc = iterator.next();
      cc.onException(connection, duration, dumpedSql, methodCall);
    }
  }

  public void addCustomizer(SpyConnectionCustomizer connectionCustomizer) {
    connectionCustomizerList.add(connectionCustomizer);
  }
}
