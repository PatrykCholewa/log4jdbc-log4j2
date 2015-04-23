package net.sf.log4jdbc.sql;

import java.sql.Connection;

/**
 * Created by aliev on 23/04/15
 */
public interface ConnectionCustomizer {
  void onPreExecute(Connection connection, long tstart, String dumpedSql, String methodCall);

  void onPostExecute(Connection connection, long duration, String dumpedSql, String methodCall);

  void onException(Connection connection, long duration, String dumpedSql, String methodCall);
}
