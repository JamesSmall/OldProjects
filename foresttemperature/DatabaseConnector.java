/*      */ package foresttemperature;
/*      */ 
/*      */ import java.sql.Connection;
/*      */ import java.sql.DriverManager;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Statement;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import javax.swing.JOptionPane;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class DatabaseConnector
/*      */ {
/*   24 */   private static String userName = "OSStudent";
/*   25 */   private static String passWord = "";
/*      */   
/*      */   private static final String DefaultUserName = "OSStudent";
/*      */   
/*      */   private static final String DefaultPassWord = "";
/*      */   
/*      */   private static final String DRIVER = "com.mysql.jdbc.Driver";
/*      */   
/*      */   private static final String URL = "jdbc:mysql://localhost/forest";
/*      */   
/*      */   private static final String URLWODatabase = "jdbc:mysql://localhost";
/*      */   private static final int MINWORDSIZE = 5;
/*      */   private static final int MAXADDTOSIZE = 10000;
/*      */   
/*      */   public DatabaseConnector()
/*      */     throws InstantiationException, ClassNotFoundException, IllegalAccessException
/*      */   {
/*   42 */     Class.forName("com.mysql.jdbc.Driver").newInstance();
/*      */   }
/*      */   
/*      */   public DatabaseConnector(String user, String pass) throws InstantiationException, ClassNotFoundException, SQLException, IllegalAccessException
/*      */   {
/*   47 */     Class.forName("com.mysql.jdbc.Driver").newInstance();
/*   48 */     userName = user;
/*   49 */     passWord = pass;
/*      */   }
/*      */   
/*      */   public static void SetDefaultUser()
/*      */   {
/*   54 */     userName = "OSStudent";
/*   55 */     passWord = "";
/*      */   }
/*      */   
/*      */   public void setUser(String user, String pass) throws SQLException
/*      */   {
/*   60 */     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost", user, pass);Throwable localThrowable2 = null;
/*      */     try {
/*   62 */       conn.setAutoCommit(true);
/*   63 */       userName = user;
/*   64 */       passWord = pass;
/*   65 */       JOptionPane.showMessageDialog(null, "User Accepted", "accepted", 1);
/*      */     }
/*      */     catch (Throwable localThrowable1)
/*      */     {
/*   60 */       localThrowable2 = localThrowable1;throw localThrowable1;
/*      */ 
/*      */     }
/*      */     finally
/*      */     {
/*      */ 
/*   66 */       if (conn != null) { if (localThrowable2 != null) try { conn.close(); } catch (Throwable x2) { localThrowable2.addSuppressed(x2); } else { conn.close();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void createNewUser(String name, String pass, int command)
/*      */     throws SQLException, UserError
/*      */   {
/*   81 */     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/forest", userName, passWord);Throwable localThrowable3 = null;
/*   82 */     try { Statement stmt = conn.createStatement();Throwable localThrowable4 = null;
/*      */       try {
/*   84 */         conn.setAutoCommit(true);
/*      */         
/*   86 */         if (name.length() < 5)
/*      */         {
/*   88 */           stmt.close();
/*   89 */           conn.close();
/*   90 */           throw new UserError("naming error", "the name was too small, the name must be at least 5 charaters");
/*      */         }
/*   92 */         if ((pass.length() < 5) || (pass.matches(pass.toLowerCase())) || (pass.matches(pass.toUpperCase()))) {
/*   93 */           stmt.close();
/*   94 */           conn.close();
/*   95 */           throw new UserError("password error", "the password did not need qualifications, the password must be at least 5 charaters  and must mix upper and lower case");
/*      */         }
/*      */         
/*   98 */         if (command == 0)
/*      */         {
/*  100 */           String execute = "CREATE USER '" + name + "'" + "identified by '" + pass + "'";
/*  101 */           stmt.execute(execute);
/*  102 */           execute = "GRANT INSERT,SELECT ON Forest. * TO  '" + name + "'";
/*  103 */           stmt.execute(execute);
/*      */ 
/*      */         }
/*  106 */         else if (command == 1)
/*      */         {
/*  108 */           String execute = "CREATE USER '" + name + "'" + "identified by '" + pass + "'";
/*  109 */           stmt.execute(execute);
/*  110 */           execute = "GRANT INSERT,SELECT,DELETE,UPDATE ON Forest. * TO  '" + name + "'";
/*  111 */           stmt.execute(execute);
/*      */ 
/*      */         }
/*  114 */         else if (command == 2)
/*      */         {
/*  116 */           String execute = "CREATE USER '" + name + "'" + "identified by '" + pass + "'";
/*  117 */           stmt.execute(execute);
/*  118 */           execute = "GRANT ALL PRIVILEGES ON * TO  '" + name + "'";
/*  119 */           stmt.execute(execute);
/*      */         }
/*      */         else
/*      */         {
/*  123 */           stmt.close();
/*  124 */           conn.close();
/*  125 */           throw new UserError("No selection error", "the user has not select a type of user for creation");
/*      */         }
/*      */       }
/*      */       catch (Throwable localThrowable1)
/*      */       {
/*      */         String execute;
/*   81 */         localThrowable4 = localThrowable1;throw localThrowable1; } finally {} } catch (Throwable localThrowable2) { localThrowable3 = localThrowable2;throw localThrowable2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*      */     finally
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  127 */       if (conn != null) if (localThrowable3 != null) try { conn.close(); } catch (Throwable x2) { localThrowable3.addSuppressed(x2); } else conn.close();
/*      */     }
/*      */   }
/*      */   
/*  131 */   public void deleteUser(String user) throws SQLException, UserError { if (user.contentEquals(userName)) {
/*  132 */       throw new UserError("attempted to delete self", "a user cannot delete him or her self!");
/*      */     }
/*      */     
/*  135 */     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/forest", userName, passWord);Throwable localThrowable3 = null;
/*  136 */     try { Statement stmt = conn.createStatement();Throwable localThrowable4 = null;
/*  137 */       try { conn.setAutoCommit(true);
/*  138 */         String execute = "DROP USER '" + user + "'";
/*  139 */         stmt.execute(execute);
/*      */       }
/*      */       catch (Throwable localThrowable1)
/*      */       {
/*  135 */         localThrowable4 = localThrowable1;throw localThrowable1; } finally {} } catch (Throwable localThrowable2) { localThrowable3 = localThrowable2;throw localThrowable2;
/*      */ 
/*      */     }
/*      */     finally
/*      */     {
/*  140 */       if (conn != null) if (localThrowable3 != null) try { conn.close(); } catch (Throwable x2) { localThrowable3.addSuppressed(x2); } else conn.close();
/*      */     }
/*      */   }
/*      */   
/*      */   public void cleanDataBase()
/*      */     throws SQLException
/*      */   {
/*  147 */     List<Integer> IDHolder = new ArrayList();
/*  148 */     List<List<Integer>> SuperID = new ArrayList();
/*  149 */     List<int[]> check = new ArrayList();
/*  150 */     List<int[]> markforDelete = new ArrayList();
/*  151 */     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/forest", userName, passWord);Throwable localThrowable3 = null;
/*      */     try {
/*  153 */       conn.setAutoCommit(true);
/*      */       
/*      */ 
/*  156 */       Statement stmt = conn.createStatement();Throwable localThrowable4 = null;
/*      */       
/*      */ 
/*      */ 
/*      */       try
/*      */       {
/*  162 */         IDHolder.clear();
/*  163 */         String execute = "select idHour from hour";
/*  164 */         ResultSet rs = stmt.executeQuery(execute);
/*  165 */         while (rs.next())
/*      */         {
/*  167 */           IDHolder.add(Integer.valueOf(rs.getInt(1)));
/*      */         }
/*  169 */         for (int x = 0; x < IDHolder.size(); x++)
/*      */         {
/*  171 */           execute = "select idHour from Minute where idHour = " + IDHolder.get(x);
/*  172 */           if (!stmt.executeQuery(execute).next()) {
/*  173 */             execute = "delete from hour where idHour = " + ((Integer)IDHolder.get(x)).intValue();
/*  174 */             stmt.execute(execute);
/*      */           }
/*      */         }
/*  177 */         IDHolder.clear();
/*      */         
/*  179 */         execute = "select idDay from hour";
/*  180 */         rs = stmt.executeQuery(execute);
/*  181 */         while (rs.next())
/*      */         {
/*  183 */           IDHolder.add(Integer.valueOf(rs.getInt(1)));
/*      */         }
/*  185 */         for (x = 0; x < IDHolder.size(); x++)
/*      */         {
/*  187 */           execute = "select idDay from hour where idDay = " + IDHolder.get(x);
/*  188 */           if (!stmt.executeQuery(execute).next()) {
/*  189 */             execute = "delete from day where idDay = " + ((Integer)IDHolder.get(x)).intValue();
/*  190 */             stmt.execute(execute);
/*      */           }
/*      */         }
/*  193 */         IDHolder.clear();
/*      */         
/*  195 */         execute = "select idMonth from Month";
/*  196 */         rs = stmt.executeQuery(execute);
/*  197 */         while (rs.next())
/*      */         {
/*  199 */           IDHolder.add(Integer.valueOf(rs.getInt(1)));
/*      */         }
/*  201 */         for (x = 0; x < IDHolder.size(); x++)
/*      */         {
/*  203 */           execute = "select idMonth from day where idMonth = " + IDHolder.get(x);
/*  204 */           if (!stmt.executeQuery(execute).next()) {
/*  205 */             execute = "delete from month where idMonth = " + ((Integer)IDHolder.get(x)).intValue();
/*  206 */             stmt.execute(execute);
/*      */           }
/*      */         }
/*  209 */         IDHolder.clear();
/*      */         
/*  211 */         execute = "select idYear from year";
/*  212 */         rs = stmt.executeQuery(execute);
/*  213 */         while (rs.next())
/*      */         {
/*  215 */           IDHolder.add(Integer.valueOf(rs.getInt(1)));
/*      */         }
/*  217 */         for (x = 0; x < IDHolder.size(); x++)
/*      */         {
/*  219 */           execute = "select idYear from Month where idYear = " + IDHolder.get(x);
/*  220 */           if (!stmt.executeQuery(execute).next()) {
/*  221 */             execute = "delete from Year where idYear = " + ((Integer)IDHolder.get(x)).intValue();
/*  222 */             stmt.execute(execute);
/*      */           }
/*      */         }
/*  225 */         stmt.close();
/*      */       }
/*      */       catch (Throwable localThrowable1)
/*      */       {
/*  156 */         localThrowable4 = localThrowable1;throw localThrowable1;
/*      */       }
/*      */       finally {}
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  227 */       conn.close();
/*      */     }
/*      */     catch (Throwable localThrowable2)
/*      */     {
/*  151 */       localThrowable3 = localThrowable2;throw localThrowable2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*      */     finally
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  228 */       if (conn != null) if (localThrowable3 != null) try { conn.close(); } catch (Throwable x2) { localThrowable3.addSuppressed(x2); } else conn.close();
/*      */     } }
/*      */   
/*  231 */   public void deleteCreatedData() throws SQLException { Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/forest", userName, passWord);Throwable localThrowable3 = null;
/*      */     try {
/*  233 */       conn.setAutoCommit(true);
/*  234 */       String execute = "delete from minute where created = true";
/*  235 */       Statement stmt = conn.createStatement();Throwable localThrowable4 = null;
/*  236 */       try { stmt.execute(execute);
/*      */       }
/*      */       catch (Throwable localThrowable1)
/*      */       {
/*  235 */         localThrowable4 = localThrowable1;throw localThrowable1;
/*      */       }
/*      */       finally {}
/*      */     }
/*      */     catch (Throwable localThrowable2)
/*      */     {
/*  231 */       localThrowable3 = localThrowable2;throw localThrowable2;
/*      */ 
/*      */ 
/*      */     }
/*      */     finally
/*      */     {
/*      */ 
/*  238 */       if (conn != null) if (localThrowable3 != null) try { conn.close(); } catch (Throwable x2) { localThrowable3.addSuppressed(x2); } else conn.close(); }
/*  239 */     cleanDataBase();
/*      */   }
/*      */   
/*      */ 
/*      */   private List<List<List<Object>>> sortData(int[] year, int[] month, int[] day, int[] hour, int[] minute, double[] temp)
/*      */     throws SQLException
/*      */   {
/*  246 */     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/forest", userName, passWord);Throwable localThrowable3 = null;
/*      */     List<List<List<Object>>> sortedData;
/*  248 */     try { conn.setAutoCommit(true);
/*  249 */       Statement stmt = conn.createStatement();Throwable localThrowable4 = null;
/*      */       try
/*      */       {
/*  252 */         sortedData = new ArrayList();
/*  253 */         List<List<Object>> yearsort = new ArrayList();List<List<Object>> monthsort = new ArrayList();List<List<Object>> daysort = new ArrayList();List<List<Object>> hoursort = new ArrayList();List<List<Object>> minutesort = new ArrayList();
/*  254 */         int j = -1;int[][] key = new int[minute.length][4];
/*      */         
/*      */         label356:
/*      */         
/*  258 */         for (int i = 0; i < minute.length; i++)
/*      */         {
/*  260 */           j--;
/*      */           
/*      */ 
/*  263 */           for (int r = 0; r < yearsort.size(); r++)
/*      */           {
/*      */ 
/*  266 */             if (year[i] == ((Integer)((List)yearsort.get(r)).get(0)).intValue())
/*      */             {
/*  268 */               key[i][0] = ((Integer)((List)yearsort.get(r)).get(1)).intValue();
/*      */               break label356;
/*      */             }
/*      */           }
/*  272 */           String statement = "SELECT idYear FROM Year WHERE timeyear = " + year[i];
/*  273 */           ResultSet rs = stmt.executeQuery(statement);
/*  274 */           List<Object> dataholder = new ArrayList();
/*  275 */           if (!rs.next()) {
/*  276 */             key[i][0] = j;
/*  277 */             dataholder.add(Integer.valueOf(year[i]));
/*  278 */             dataholder.add(Integer.valueOf(j));
/*      */           }
/*      */           else {
/*  281 */             key[i][0] = rs.getInt("idYear");
/*  282 */             dataholder.add(Integer.valueOf(year[i]));
/*  283 */             dataholder.add(Integer.valueOf(key[i][0]));
/*      */           }
/*  285 */           yearsort.add(dataholder);
/*      */         }
/*      */         
/*  288 */         j = -1;
/*  289 */         label703: for (i = 0; i < minute.length; i++)
/*      */         {
/*  291 */           j--;
/*      */           
/*      */ 
/*  294 */           for (int r = 0; r < monthsort.size(); r++)
/*      */           {
/*  296 */             if ((month[i] == ((Integer)((List)monthsort.get(r)).get(0)).intValue()) && (key[i][0] == ((Integer)((List)monthsort.get(r)).get(2)).intValue()))
/*      */             {
/*  298 */               key[i][1] = ((Integer)((List)monthsort.get(r)).get(1)).intValue();
/*      */               break label703;
/*      */             }
/*      */           }
/*  302 */           String statement = "SELECT idMonth FROM Month WHERE timeMonth = " + month[i] + " AND idYear = " + key[i][0];
/*  303 */           ResultSet rs = stmt.executeQuery(statement);
/*  304 */           List<Object> dataholder = new ArrayList();
/*  305 */           if (!rs.next()) {
/*  306 */             key[i][1] = j;
/*  307 */             dataholder.add(Integer.valueOf(month[i]));
/*  308 */             dataholder.add(Integer.valueOf(j));
/*  309 */             dataholder.add(Integer.valueOf(key[i][0]));
/*      */           }
/*      */           else {
/*  312 */             key[i][1] = rs.getInt("idMonth");
/*  313 */             dataholder.add(Integer.valueOf(month[i]));
/*  314 */             dataholder.add(Integer.valueOf(key[i][1]));
/*  315 */             dataholder.add(Integer.valueOf(key[i][0]));
/*      */           }
/*  317 */           monthsort.add(dataholder);
/*      */         }
/*      */         
/*  320 */         j = -1;
/*  321 */         label1135: for (i = 0; i < minute.length; i++)
/*      */         {
/*  323 */           j--;
/*      */           
/*      */ 
/*  326 */           for (int r = 0; r < daysort.size(); r++)
/*      */           {
/*  328 */             if ((day[i] == ((Integer)((List)daysort.get(r)).get(0)).intValue()) && (key[i][1] == ((Integer)((List)daysort.get(r)).get(2)).intValue()) && (key[i][0] == ((Integer)((List)daysort.get(r)).get(3)).intValue()))
/*      */             {
/*  330 */               key[i][2] = ((Integer)((List)daysort.get(r)).get(1)).intValue();
/*      */               break label1135;
/*      */             }
/*      */           }
/*  334 */           String statement = "SELECT idDay FROM Day WHERE timeDay = " + day[i] + " AND idYear = " + key[i][0] + " AND idMonth = " + key[i][1];
/*  335 */           ResultSet rs = stmt.executeQuery(statement);
/*  336 */           List<Object> dataholder = new ArrayList();
/*  337 */           if (!rs.next()) {
/*  338 */             key[i][2] = j;
/*  339 */             dataholder.add(Integer.valueOf(day[i]));
/*  340 */             dataholder.add(Integer.valueOf(j));
/*  341 */             dataholder.add(Integer.valueOf(key[i][1]));
/*  342 */             dataholder.add(Integer.valueOf(key[i][0]));
/*      */           }
/*      */           else {
/*  345 */             key[i][2] = rs.getInt("idDay");
/*  346 */             dataholder.add(Integer.valueOf(day[i]));
/*  347 */             dataholder.add(Integer.valueOf(key[i][2]));
/*  348 */             dataholder.add(Integer.valueOf(key[i][1]));
/*  349 */             dataholder.add(Integer.valueOf(key[i][0]));
/*      */           }
/*  351 */           daysort.add(dataholder);
/*      */         }
/*      */         
/*  354 */         j = -1;
/*  355 */         label1656: for (i = 0; i < minute.length; i++)
/*      */         {
/*  357 */           j--;
/*      */           
/*  359 */           for (int r = 0; r < hoursort.size(); r++)
/*      */           {
/*      */ 
/*  362 */             if ((hour[i] == ((Integer)((List)hoursort.get(r)).get(0)).intValue()) && (((Integer)((List)hoursort.get(r)).get(4)).intValue() == key[i][0]) && (((Integer)((List)hoursort.get(r)).get(3)).intValue() == key[i][1]) && (((Integer)((List)hoursort.get(r)).get(2)).intValue() == key[i][2])) {
/*  363 */               key[i][3] = ((Integer)((List)hoursort.get(r)).get(1)).intValue();
/*      */               break label1656;
/*      */             }
/*      */           }
/*  367 */           String statement = "SELECT idHour FROM Hour WHERE timeHour = " + hour[i] + " AND idYear = " + key[i][0] + " AND idMonth = " + key[i][1] + " AND idDay = " + key[i][2];
/*      */           
/*  369 */           ResultSet rs = stmt.executeQuery(statement);
/*  370 */           List<Object> dataholder = new ArrayList();
/*  371 */           if (!rs.next())
/*      */           {
/*  373 */             key[i][3] = j;
/*  374 */             dataholder.add(Integer.valueOf(hour[i]));
/*  375 */             dataholder.add(Integer.valueOf(j));
/*  376 */             dataholder.add(Integer.valueOf(key[i][2]));
/*  377 */             dataholder.add(Integer.valueOf(key[i][1]));
/*  378 */             dataholder.add(Integer.valueOf(key[i][0]));
/*      */           }
/*      */           else
/*      */           {
/*  382 */             key[i][3] = rs.getInt("idHour");
/*  383 */             dataholder.add(Integer.valueOf(hour[i]));
/*  384 */             dataholder.add(Integer.valueOf(key[i][3]));
/*  385 */             dataholder.add(Integer.valueOf(key[i][2]));
/*  386 */             dataholder.add(Integer.valueOf(key[i][1]));
/*  387 */             dataholder.add(Integer.valueOf(key[i][0]));
/*      */           }
/*  389 */           hoursort.add(dataholder);
/*      */         }
/*      */         
/*  392 */         j = -1;
/*  393 */         for (i = 0; i < minute.length; i++)
/*      */         {
/*  395 */           List<Object> dataholder = new ArrayList();
/*  396 */           boolean check = false;
/*  397 */           j--;
/*  398 */           for (int r = 0; r < minutesort.size(); r++)
/*      */           {
/*  400 */             if ((key[i][3] == ((Integer)((List)minutesort.get(r)).get(4)).intValue()) && (key[i][2] == ((Integer)((List)minutesort.get(r)).get(5)).intValue()) && (key[i][1] == ((Integer)((List)minutesort.get(r)).get(6)).intValue()) && (key[i][0] == ((Integer)((List)minutesort.get(r)).get(7)).intValue()) && (minute[i] - 20 < ((Integer)((List)minutesort.get(r)).get(1)).intValue()) && (minute[i] + 20 > ((Integer)((List)minutesort.get(r)).get(1)).intValue()))
/*      */             {
/*  402 */               check = true;
/*  403 */               break;
/*      */             }
/*      */           }
/*  406 */           dataholder.add(Boolean.valueOf(check));
/*  407 */           String statement = "SELECT idMinute FROM Minute WHERE timeMinute < " + (minute[i] + 20) + " AND timeMinute > " + (minute[i] - 20) + " AND idYear = " + key[i][0] + " AND idMonth = " + key[i][1] + " AND idDay = " + key[i][2] + " AND idHour = " + key[i][3];
/*      */           
/*  409 */           ResultSet rs = stmt.executeQuery(statement);
/*  410 */           if (!rs.next())
/*      */           {
/*  412 */             dataholder.add(Integer.valueOf(minute[i]));
/*  413 */             dataholder.add(Double.valueOf(temp[i]));
/*  414 */             dataholder.add(Integer.valueOf(j));
/*  415 */             dataholder.add(Integer.valueOf(key[i][3]));
/*  416 */             dataholder.add(Integer.valueOf(key[i][2]));
/*  417 */             dataholder.add(Integer.valueOf(key[i][1]));
/*  418 */             dataholder.add(Integer.valueOf(key[i][0]));
/*  419 */             minutesort.add(dataholder);
/*      */           }
/*      */           else
/*      */           {
/*  423 */             dataholder.add(Integer.valueOf(minute[i]));
/*  424 */             dataholder.add(Double.valueOf(temp[i]));
/*  425 */             dataholder.add(Integer.valueOf(rs.getInt("idMinute")));
/*  426 */             dataholder.add(Integer.valueOf(key[i][3]));
/*  427 */             dataholder.add(Integer.valueOf(key[i][2]));
/*  428 */             dataholder.add(Integer.valueOf(key[i][1]));
/*  429 */             dataholder.add(Integer.valueOf(key[i][0]));
/*  430 */             minutesort.add(dataholder);
/*      */           }
/*      */         }
/*  433 */         sortedData.add(yearsort);
/*  434 */         sortedData.add(monthsort);
/*  435 */         sortedData.add(daysort);
/*  436 */         sortedData.add(hoursort);
/*  437 */         sortedData.add(minutesort);
/*      */       }
/*      */       catch (Throwable localThrowable1)
/*      */       {
/*  249 */         localThrowable4 = localThrowable1;throw localThrowable1;
/*      */       }
/*      */       finally {}
/*      */     }
/*      */     catch (Throwable localThrowable2)
/*      */     {
/*  246 */       localThrowable3 = localThrowable2;throw localThrowable2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*      */     finally
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  439 */       if (conn != null) if (localThrowable3 != null) try { conn.close(); } catch (Throwable x2) { localThrowable3.addSuppressed(x2); } else conn.close(); }
/*  440 */     return sortedData;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addDataSet(int[] year, int[] month, int[] day, int[] hour, int[] minute, double[] temp, boolean generated, boolean ignoreDuplicateError)
/*      */     throws UserError, SQLException, RuntimeException
/*      */   {
/*  452 */     int Pos = 0;
/*  453 */     boolean hasHitDuplicate = ignoreDuplicateError;
/*      */     
/*      */ 
/*  456 */     if (temp.length < 10000) {
/*  457 */       addDataSetToDatabase(year, month, day, hour, minute, temp, generated, ignoreDuplicateError);
/*  458 */       return;
/*      */     }
/*  460 */     int cycles = temp.length / 10000 + 1;
/*  461 */     for (int x = 0; x < cycles; x++)
/*      */     {
/*  463 */       if (x == cycles - 1) {
/*  464 */         int DistanceFromMax = temp.length % 10000;
/*  465 */         int[][] dataTransefer = new int[5][DistanceFromMax];
/*  466 */         double[] tempTransefer = new double[DistanceFromMax];
/*  467 */         for (int y = 0; y < DistanceFromMax; y++)
/*      */         {
/*  469 */           dataTransefer[0][y] = year[Pos];
/*  470 */           dataTransefer[1][y] = month[Pos];
/*  471 */           dataTransefer[2][y] = day[Pos];
/*  472 */           dataTransefer[3][y] = hour[Pos];
/*  473 */           dataTransefer[4][y] = minute[Pos];
/*  474 */           tempTransefer[y] = temp[Pos];
/*  475 */           Pos++;
/*      */         }
/*  477 */         hasHitDuplicate = addDataSetToDatabase(dataTransefer[0], dataTransefer[1], dataTransefer[2], dataTransefer[3], dataTransefer[4], tempTransefer, generated, hasHitDuplicate);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  482 */         int[][] dataTransefer = new int[5]['✐'];
/*  483 */         double[] tempTransefer = new double['✐'];
/*  484 */         for (int y = 0; y < 10000; y++)
/*      */         {
/*  486 */           dataTransefer[0][y] = year[Pos];
/*  487 */           dataTransefer[1][y] = month[Pos];
/*  488 */           dataTransefer[2][y] = day[Pos];
/*  489 */           dataTransefer[3][y] = hour[Pos];
/*  490 */           dataTransefer[4][y] = minute[Pos];
/*  491 */           tempTransefer[y] = temp[Pos];
/*  492 */           Pos++;
/*      */         }
/*  494 */         hasHitDuplicate = addDataSetToDatabase(dataTransefer[0], dataTransefer[1], dataTransefer[2], dataTransefer[3], dataTransefer[4], tempTransefer, generated, hasHitDuplicate);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean addDataSetToDatabase(int[] year, int[] month, int[] day, int[] hour, int[] minute, double[] temp, boolean generated, boolean ignoreDuplicateError) throws UserError, SQLException, RuntimeException
/*      */   {
/*  501 */     boolean ret = ignoreDuplicateError;
/*  502 */     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/forest", userName, passWord);Throwable localThrowable3 = null;
/*      */     try {
/*  504 */       conn.setAutoCommit(true);
/*  505 */       Statement stmt = conn.createStatement();Throwable localThrowable4 = null;
/*      */       try
/*      */       {
/*  508 */         boolean action = false;
/*  509 */         int j = 0;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  515 */         List<List<List<Object>>> data = sortData(year, month, day, hour, minute, temp);
/*  516 */         List<List<Object>> yearList = (List)data.get(0);
/*  517 */         List<List<Object>> monthList = (List)data.get(1);
/*  518 */         List<List<Object>> dayList = (List)data.get(2);
/*  519 */         List<List<Object>> hourList = (List)data.get(3);
/*  520 */         List<List<Object>> minuteList = (List)data.get(4);
/*  521 */         int[][] newkey = new int[minuteList.size()][4];
/*  522 */         int[][] oldkey = new int[minuteList.size()][4];
/*      */         
/*  524 */         if (!ignoreDuplicateError)
/*      */         {
/*  526 */           for (int i = 0; i < minuteList.size(); i++)
/*      */           {
/*  528 */             if ((((Boolean)((List)minuteList.get(i)).get(0)).booleanValue()) || (((Integer)((List)minuteList.get(i)).get(3)).intValue() > 0))
/*      */             {
/*  530 */               ret = !ignoreDuplicateError;
/*  531 */               j = JOptionPane.showConfirmDialog(null, "Press ok to ignore duplicates, cancel to exit", "Data Duplicates Error", 2, 3);
/*  532 */               if (j != 2)
/*      */                 break;
/*  534 */               throw new UserError("Cancled", "canceled by user");
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  548 */         String beginStmt = "INSERT IGNORE INTO Year (timeYear) Values(";
/*  549 */         String midStmt = "),(";
/*  550 */         String endStmt = ")";
/*  551 */         int[][] Statement = new int[yearList.size()][1];
/*      */         
/*  553 */         for (int i = 0; i < yearList.size(); i++)
/*      */         {
/*  555 */           if (((Integer)((List)yearList.get(i)).get(1)).intValue() < 0)
/*      */           {
/*  557 */             action = true;
/*  558 */             oldkey[j][0] = ((Integer)((List)yearList.get(i)).get(1)).intValue();
/*  559 */             Statement[j][0] = ((Integer)((List)yearList.get(i)).get(0)).intValue();
/*  560 */             j++;
/*      */           }
/*      */         }
/*  563 */         if (action)
/*      */         {
/*  565 */           String execute = beginStmt + Statement[0][0];
/*  566 */           for (i = 1; i < j; i++)
/*      */           {
/*  568 */             execute = execute + midStmt + Statement[i][0];
/*      */           }
/*  570 */           execute = execute + endStmt;
/*  571 */           stmt.execute(execute);
/*      */           
/*  573 */           execute = "SELECT idYear,timeYear FROM Year WHERE timeYear = " + Statement[0][0];
/*  574 */           for (i = 1; i < j; i++)
/*      */           {
/*  576 */             execute = execute + " OR timeYear = " + Statement[i][0];
/*      */           }
/*  578 */           ResultSet rs = stmt.executeQuery(execute);
/*      */           
/*  580 */           while (rs.next())
/*      */           {
/*  582 */             for (i = 0; i < j; i++)
/*      */             {
/*  584 */               if (rs.getInt("timeyear") == Statement[i][0])
/*      */               {
/*  586 */                 newkey[i][0] = rs.getInt("idyear");
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*  591 */           for (i = 0; i < monthList.size(); i++)
/*      */           {
/*  593 */             if (((Integer)((List)monthList.get(i)).get(2)).intValue() < 0)
/*      */             {
/*  595 */               for (int h = 0; h < j; h++)
/*      */               {
/*  597 */                 if (((Integer)((List)monthList.get(i)).get(2)).intValue() == oldkey[h][0])
/*      */                 {
/*  599 */                   ((List)monthList.get(i)).set(2, Integer.valueOf(newkey[h][0]));
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*  605 */           for (i = 0; i < dayList.size(); i++)
/*      */           {
/*  607 */             if (((Integer)((List)dayList.get(i)).get(3)).intValue() < 0)
/*      */             {
/*  609 */               for (int h = 0; h < j; h++) {
/*  610 */                 if (((Integer)((List)dayList.get(i)).get(3)).intValue() == oldkey[h][0])
/*      */                 {
/*  612 */                   ((List)dayList.get(i)).set(3, Integer.valueOf(newkey[h][0]));
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*  618 */           for (i = 0; i < hourList.size(); i++)
/*      */           {
/*  620 */             if (((Integer)((List)hourList.get(i)).get(4)).intValue() < 0)
/*      */             {
/*  622 */               for (int h = 0; h < j; h++)
/*      */               {
/*  624 */                 if (((Integer)((List)hourList.get(i)).get(4)).intValue() == oldkey[h][0]) {
/*  625 */                   ((List)hourList.get(i)).set(4, Integer.valueOf(newkey[h][0]));
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*  631 */           for (i = 0; i < minuteList.size(); i++)
/*      */           {
/*  633 */             if (((Integer)((List)minuteList.get(i)).get(7)).intValue() < 0)
/*      */             {
/*  635 */               for (int h = 0; h < j; h++)
/*      */               {
/*  637 */                 if (((Integer)((List)minuteList.get(i)).get(7)).intValue() == oldkey[h][0])
/*      */                 {
/*  639 */                   ((List)minuteList.get(i)).set(7, Integer.valueOf(newkey[h][0]));
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  649 */         action = false;
/*  650 */         j = 0;
/*  651 */         beginStmt = "INSERT IGNORE INTO Month (timeMonth,idYear) Values(";
/*  652 */         midStmt = "),(";
/*  653 */         endStmt = ")";
/*  654 */         Statement = new int[monthList.size()][2];
/*  655 */         String[] item = new String[monthList.size()];
/*      */         
/*  657 */         for (i = 0; i < monthList.size(); i++)
/*      */         {
/*  659 */           if (((Integer)((List)monthList.get(i)).get(1)).intValue() < 0)
/*      */           {
/*  661 */             action = true;
/*  662 */             oldkey[j][1] = ((Integer)((List)monthList.get(i)).get(1)).intValue();
/*  663 */             Statement[j][0] = ((Integer)((List)monthList.get(i)).get(0)).intValue();
/*  664 */             Statement[j][1] = ((Integer)((List)monthList.get(i)).get(2)).intValue();
/*  665 */             item[j] = ("" + ((Integer)((List)monthList.get(i)).get(0)).intValue() + "," + ((Integer)((List)monthList.get(i)).get(2)).intValue());
/*  666 */             j++;
/*      */           }
/*      */         }
/*  669 */         if (action)
/*      */         {
/*  671 */           String execute = beginStmt + item[0];
/*  672 */           for (i = 1; i < j; i++)
/*      */           {
/*  674 */             execute = execute + midStmt + item[i];
/*      */           }
/*  676 */           execute = execute + endStmt;
/*  677 */           stmt.execute(execute);
/*      */           
/*  679 */           execute = "SELECT * FROM Month WHERE timeMonth = " + Statement[0][0];
/*  680 */           for (i = 1; i < j; i++) {
/*  681 */             execute = execute + " OR timeMonth = " + Statement[i][0];
/*      */           }
/*  683 */           ResultSet rs = stmt.executeQuery(execute);
/*      */           
/*  685 */           while (rs.next())
/*      */           {
/*  687 */             for (i = 0; i < j; i++)
/*      */             {
/*  689 */               if ((rs.getInt("timemonth") == Statement[i][0]) && (rs.getInt("idYear") == Statement[i][1]))
/*      */               {
/*  691 */                 newkey[i][1] = rs.getInt("idmonth");
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*  697 */           for (i = 0; i < dayList.size(); i++)
/*      */           {
/*  699 */             if (((Integer)((List)dayList.get(i)).get(2)).intValue() < 0)
/*      */             {
/*  701 */               for (int h = 0; h < j; h++)
/*      */               {
/*  703 */                 if (((Integer)((List)dayList.get(i)).get(2)).intValue() == oldkey[h][1])
/*      */                 {
/*  705 */                   ((List)dayList.get(i)).set(2, Integer.valueOf(newkey[h][1]));
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*  711 */           for (i = 0; i < hourList.size(); i++)
/*      */           {
/*  713 */             if (((Integer)((List)hourList.get(i)).get(3)).intValue() < 0)
/*      */             {
/*  715 */               for (int h = 0; h < j; h++)
/*      */               {
/*  717 */                 if (((Integer)((List)hourList.get(i)).get(3)).intValue() == oldkey[h][1])
/*      */                 {
/*  719 */                   ((List)hourList.get(i)).set(3, Integer.valueOf(newkey[h][1]));
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*  725 */           for (i = 0; i < minuteList.size(); i++)
/*      */           {
/*  727 */             if (((Integer)((List)minuteList.get(i)).get(6)).intValue() < 0)
/*      */             {
/*  729 */               for (int h = 0; h < j; h++)
/*      */               {
/*  731 */                 if (((Integer)((List)minuteList.get(i)).get(6)).intValue() == oldkey[h][1])
/*      */                 {
/*  733 */                   ((List)minuteList.get(i)).set(6, Integer.valueOf(newkey[h][1]));
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  744 */         action = false;
/*  745 */         j = 0;
/*  746 */         beginStmt = "INSERT IGNORE INTO Day (timeDay,idMonth,idYear) Values(";
/*  747 */         midStmt = "),(";
/*  748 */         endStmt = ")";
/*  749 */         Statement = new int[dayList.size()][3];
/*  750 */         item = new String[dayList.size()];
/*  751 */         for (i = 0; i < dayList.size(); i++)
/*      */         {
/*  753 */           if (((Integer)((List)dayList.get(i)).get(1)).intValue() < 0)
/*      */           {
/*  755 */             action = true;
/*  756 */             oldkey[j][2] = ((Integer)((List)dayList.get(i)).get(1)).intValue();
/*  757 */             Statement[j][0] = ((Integer)((List)dayList.get(i)).get(0)).intValue();
/*  758 */             Statement[j][1] = ((Integer)((List)dayList.get(i)).get(2)).intValue();
/*  759 */             Statement[j][2] = ((Integer)((List)dayList.get(i)).get(3)).intValue();
/*  760 */             item[j] = ("" + ((Integer)((List)dayList.get(i)).get(0)).intValue() + "," + ((Integer)((List)dayList.get(i)).get(2)).intValue() + "," + ((Integer)((List)dayList.get(i)).get(3)).intValue());
/*  761 */             j++;
/*      */           }
/*      */         }
/*  764 */         if (action) {
/*  765 */           String execute = beginStmt + item[0];
/*  766 */           for (i = 1; i < j; i++) {
/*  767 */             execute = execute + midStmt + item[i];
/*      */           }
/*  769 */           execute = execute + endStmt;
/*  770 */           stmt.execute(execute);
/*      */           
/*  772 */           execute = "SELECT * FROM Day WHERE timeDay = " + Statement[0][0];
/*  773 */           for (i = 1; i < j; i++) {
/*  774 */             execute = execute + " OR timeDay = " + Statement[i][0];
/*      */           }
/*  776 */           ResultSet rs = stmt.executeQuery(execute);
/*      */           
/*  778 */           while (rs.next()) {
/*  779 */             for (i = 0; i < j; i++)
/*      */             {
/*  781 */               if ((rs.getInt("timeday") == Statement[i][0]) && (Statement[i][1] == rs.getInt("idMonth")) && (Statement[i][2] == rs.getInt("idYear")))
/*      */               {
/*  783 */                 newkey[i][2] = rs.getInt("idday");
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*  789 */           for (i = 0; i < hourList.size(); i++)
/*      */           {
/*  791 */             if (((Integer)((List)hourList.get(i)).get(2)).intValue() < 0)
/*      */             {
/*  793 */               for (int h = 0; h < j; h++) {
/*  794 */                 if (((Integer)((List)hourList.get(i)).get(2)).intValue() == oldkey[h][2])
/*      */                 {
/*  796 */                   ((List)hourList.get(i)).set(2, Integer.valueOf(newkey[h][2]));
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*  802 */           for (i = 0; i < minuteList.size(); i++)
/*      */           {
/*  804 */             if (((Integer)((List)minuteList.get(i)).get(5)).intValue() < 0)
/*      */             {
/*  806 */               for (int h = 0; h < j; h++)
/*      */               {
/*  808 */                 if (((Integer)((List)minuteList.get(i)).get(5)).intValue() == oldkey[h][2])
/*      */                 {
/*  810 */                   ((List)minuteList.get(i)).set(5, Integer.valueOf(newkey[h][2]));
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  821 */         action = false;
/*  822 */         j = 0;
/*  823 */         beginStmt = "INSERT IGNORE INTO Hour (timeHour,idDay,idMonth,idYear) Values(";
/*  824 */         midStmt = "),(";
/*  825 */         endStmt = ")";
/*  826 */         Statement = new int[hourList.size()][4];
/*  827 */         item = new String[hourList.size()];
/*  828 */         for (i = 0; i < hourList.size(); i++)
/*      */         {
/*  830 */           if (((Integer)((List)hourList.get(i)).get(1)).intValue() < 0)
/*      */           {
/*  832 */             action = true;
/*  833 */             oldkey[j][3] = ((Integer)((List)hourList.get(i)).get(1)).intValue();
/*  834 */             Statement[j][0] = ((Integer)((List)hourList.get(i)).get(0)).intValue();
/*  835 */             Statement[j][1] = ((Integer)((List)hourList.get(i)).get(2)).intValue();
/*  836 */             Statement[j][2] = ((Integer)((List)hourList.get(i)).get(3)).intValue();
/*  837 */             Statement[j][3] = ((Integer)((List)hourList.get(i)).get(4)).intValue();
/*  838 */             item[j] = ("" + ((Integer)((List)hourList.get(i)).get(0)).intValue() + "," + ((Integer)((List)hourList.get(i)).get(2)).intValue() + "," + ((Integer)((List)hourList.get(i)).get(3)).intValue() + "," + ((Integer)((List)hourList.get(i)).get(4)).intValue());
/*  839 */             j++;
/*      */           }
/*      */         }
/*  842 */         if (action) {
/*  843 */           String execute = beginStmt + item[0];
/*  844 */           for (i = 1; i < j; i++) {
/*  845 */             execute = execute + midStmt + item[i];
/*      */           }
/*  847 */           execute = execute + endStmt;
/*  848 */           stmt.execute(execute);
/*      */           
/*  850 */           execute = "SELECT * FROM Hour WHERE timeHour = " + Statement[0][0];
/*  851 */           for (i = 1; i < j; i++) {
/*  852 */             execute = execute + " OR timehour = " + Statement[i][0];
/*      */           }
/*  854 */           ResultSet rs = stmt.executeQuery(execute);
/*      */           
/*  856 */           while (rs.next())
/*      */           {
/*  858 */             for (i = 0; i < j; i++)
/*      */             {
/*  860 */               if ((rs.getInt("timehour") == Statement[i][0]) && (rs.getInt("idDay") == Statement[i][1]) && (rs.getInt("idMonth") == Statement[i][2]) && (rs.getInt("idYear") == Statement[i][3]))
/*      */               {
/*  862 */                 newkey[i][3] = rs.getInt("idhour");
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*  867 */           for (i = 0; i < minuteList.size(); i++)
/*      */           {
/*  869 */             if (((Integer)((List)minuteList.get(i)).get(4)).intValue() < 0)
/*      */             {
/*  871 */               for (int h = 0; h < j; h++)
/*      */               {
/*  873 */                 if (((Integer)((List)minuteList.get(i)).get(4)).intValue() == oldkey[h][3])
/*      */                 {
/*  875 */                   ((List)minuteList.get(i)).set(4, Integer.valueOf(newkey[h][3]));
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  885 */         action = false;
/*  886 */         j = 0;
/*  887 */         beginStmt = "INSERT IGNORE INTO Minute (timeMinute,Created,Temp,idHour,idDay,idMonth,idYear) Values(";
/*  888 */         midStmt = "),(";
/*  889 */         endStmt = ")";
/*  890 */         item = new String[minuteList.size()];
/*  891 */         for (i = 0; i < minuteList.size(); i++)
/*      */         {
/*  893 */           if ((((Integer)((List)minuteList.get(i)).get(3)).intValue() < 0) && (!((Boolean)((List)minuteList.get(i)).get(0)).booleanValue()))
/*      */           {
/*  895 */             action = true;
/*  896 */             item[j] = ("" + ((Integer)((List)minuteList.get(i)).get(1)).intValue() + "," + generated + "," + ((Double)((List)minuteList.get(i)).get(2)).doubleValue() + "," + ((Integer)((List)minuteList.get(i)).get(4)).intValue() + "," + ((Integer)((List)minuteList.get(i)).get(5)).intValue() + "," + ((Integer)((List)minuteList.get(i)).get(6)).intValue() + "," + ((Integer)((List)minuteList.get(i)).get(7)).intValue());
/*  897 */             j++;
/*      */           }
/*      */         }
/*  900 */         if (action)
/*      */         {
/*  902 */           String execute = beginStmt + item[0];
/*  903 */           for (i = 1; i < j; i++) {
/*  904 */             execute = execute + midStmt + item[i];
/*      */           }
/*  906 */           execute = execute + endStmt;
/*  907 */           stmt.execute(execute);
/*      */         }
/*      */       }
/*      */       catch (Throwable localThrowable1)
/*      */       {
/*  505 */         localThrowable4 = localThrowable1;throw localThrowable1;
/*      */       }
/*      */       finally {}
/*      */     }
/*      */     catch (Throwable localThrowable2)
/*      */     {
/*  502 */       localThrowable3 = localThrowable2;throw localThrowable2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*      */     finally
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  911 */       if (conn != null) if (localThrowable3 != null) try { conn.close(); } catch (Throwable x2) { localThrowable3.addSuppressed(x2); } else conn.close(); }
/*  912 */     return ret;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateDataSet(int[] year, int[] month, int[] day, int[] hour, int[] minute, double[] temp)
/*      */     throws SQLException, UserError
/*      */   {
/*  922 */     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/forest", userName, passWord);Throwable localThrowable2 = null;
/*      */     Statement stmt;
/*  924 */     try { conn.setAutoCommit(true);
/*  925 */       stmt = conn.createStatement();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  930 */       List<List<List<Object>>> data = sortData(year, month, day, hour, minute, temp);
/*  931 */       List<List<Object>> minuteList = (List)data.get(4);
/*  932 */       for (int i = 0; i < minuteList.size(); i++)
/*      */       {
/*  934 */         if ((0 > ((Integer)((List)minuteList.get(i)).get(3)).intValue()) || (((Boolean)((List)minuteList.get(i)).get(0)).booleanValue()))
/*      */         {
/*  936 */           i = JOptionPane.showConfirmDialog(null, "some data does not exist or is created", "ignore?", 2, 3);
/*  937 */           if (i != 2)
/*      */             break;
/*  939 */           throw new UserError("canceled", "user had cancled the opertion");
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  944 */       String beginStmt = "UPDATE Minute SET Temp = ";
/*  945 */       String midStmt = ",timeMinute = ";
/*  946 */       String midStmt2 = ",created = ";
/*  947 */       String endStmt = " Where idMinute = ";
/*  948 */       for (i = 0; i < minuteList.size(); i++)
/*      */       {
/*  950 */         if (((Integer)((List)minuteList.get(i)).get(3)).intValue() > 0)
/*      */         {
/*  952 */           String execute = beginStmt + ((Double)((List)minuteList.get(i)).get(2)).doubleValue() + midStmt + ((Integer)((List)minuteList.get(i)).get(1)).intValue() + midStmt2 + false + endStmt + ((Integer)((List)minuteList.get(i)).get(3)).intValue();
/*  953 */           stmt.execute(execute);
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (Throwable localThrowable1)
/*      */     {
/*  922 */       localThrowable2 = localThrowable1;throw localThrowable1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*      */     finally
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  956 */       if (conn != null) if (localThrowable2 != null) try { conn.close(); } catch (Throwable x2) { localThrowable2.addSuppressed(x2); } else conn.close(); }
/*  957 */     stmt.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public void deleteDataSet(int startYear, int startMonth, int startDay, int startHour, int startMinute, int endYear, int endMonth, int endDay, int endHour, int endMinute)
/*      */     throws SQLException, NumberFormatException, UserError
/*      */   {
/*  964 */     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/forest", userName, passWord);Throwable localThrowable3 = null;
/*      */     try {
/*  966 */       conn.setAutoCommit(true);
/*  967 */       Statement stmt = conn.createStatement();Throwable localThrowable4 = null;
/*      */       
/*      */ 
/*      */       try
/*      */       {
/*  972 */         List<List<List<Object>>> grab = collectData(startYear, startMonth, startDay, startHour, startMinute, endYear, endMonth, endDay, endHour, endMinute, false);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  992 */         List<List<Object>> rawData = (List)grab.get(12);
/*  993 */         for (int i = 0; i < rawData.size(); i++)
/*      */         {
/*  995 */           String execute = "delete from minute where idMinute = " + ((Integer)((List)rawData.get(i)).get(2)).intValue();
/*  996 */           stmt.execute(execute);
/*      */         }
/*      */         
/*      */ 
/* 1000 */         rawData = (List)grab.get(9);
/* 1001 */         for (i = 0; i < rawData.size(); i++)
/*      */         {
/* 1003 */           String execute = "Select idMinute from Minute where idHour = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1004 */           if (!stmt.executeQuery(execute).next())
/*      */           {
/* 1006 */             execute = "delete from hour where idHour = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1007 */             stmt.execute(execute);
/*      */           }
/*      */         }
/*      */         
/* 1011 */         rawData = (List)grab.get(6);
/* 1012 */         for (i = 0; i < rawData.size(); i++)
/*      */         {
/* 1014 */           String execute = "Select idHour from Hour where idDay = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1015 */           if (!stmt.executeQuery(execute).next())
/*      */           {
/* 1017 */             execute = "delete from Day where idDay = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1018 */             stmt.execute(execute);
/*      */           }
/*      */         }
/*      */         
/* 1022 */         rawData = (List)grab.get(3);
/* 1023 */         for (i = 0; i < rawData.size(); i++)
/*      */         {
/* 1025 */           String execute = "Select idDay from Day where idMonth = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1026 */           if (!stmt.executeQuery(execute).next())
/*      */           {
/* 1028 */             execute = "delete from Month where idMonth = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1029 */             stmt.execute(execute);
/*      */           }
/*      */         }
/* 1032 */         rawData = (List)grab.get(0);
/* 1033 */         for (i = 0; i < rawData.size(); i++)
/*      */         {
/* 1035 */           String execute = "Select idMonth from Month where idYear = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1036 */           if (!stmt.executeQuery(execute).next())
/*      */           {
/* 1038 */             execute = "delete from year where idYear = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1039 */             stmt.execute(execute);
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 1044 */         rawData = (List)grab.get(13);
/* 1045 */         for (i = 0; i < rawData.size(); i++)
/*      */         {
/* 1047 */           String execute = "delete from minute where idMinute = " + ((Integer)((List)rawData.get(i)).get(2)).intValue();
/* 1048 */           stmt.execute(execute);
/*      */         }
/*      */         
/*      */ 
/* 1052 */         rawData = (List)grab.get(10);
/* 1053 */         for (i = 0; i < rawData.size(); i++)
/*      */         {
/* 1055 */           String execute = "Select idMinute from Minute where idHour = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1056 */           if (!stmt.executeQuery(execute).next())
/*      */           {
/* 1058 */             execute = "delete from hour where idHour = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1059 */             stmt.execute(execute);
/*      */           }
/*      */         }
/*      */         
/* 1063 */         rawData = (List)grab.get(7);
/* 1064 */         for (i = 0; i < rawData.size(); i++)
/*      */         {
/* 1066 */           String execute = "Select idHour from Hour where idDay = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1067 */           if (!stmt.executeQuery(execute).next())
/*      */           {
/* 1069 */             execute = "delete from Day where idDay = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1070 */             stmt.execute(execute);
/*      */           }
/*      */         }
/*      */         
/* 1074 */         rawData = (List)grab.get(4);
/* 1075 */         for (i = 0; i < rawData.size(); i++)
/*      */         {
/* 1077 */           String execute = "Select idDay from Day where idMonth = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1078 */           if (!stmt.executeQuery(execute).next())
/*      */           {
/* 1080 */             execute = "delete from Month where idMonth = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1081 */             stmt.execute(execute);
/*      */           }
/*      */         }
/* 1084 */         rawData = (List)grab.get(1);
/* 1085 */         for (i = 0; i < rawData.size(); i++)
/*      */         {
/* 1087 */           String execute = "Select idMonth from Month where idYear = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1088 */           if (!stmt.executeQuery(execute).next())
/*      */           {
/* 1090 */             execute = "delete from year where idYear = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1091 */             stmt.execute(execute);
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 1096 */         rawData = (List)grab.get(14);
/* 1097 */         for (i = 0; i < rawData.size(); i++)
/*      */         {
/* 1099 */           String execute = "delete from minute where idMinute = " + ((Integer)((List)rawData.get(i)).get(2)).intValue();
/* 1100 */           stmt.execute(execute);
/*      */         }
/*      */         
/*      */ 
/* 1104 */         rawData = (List)grab.get(11);
/* 1105 */         for (i = 0; i < rawData.size(); i++)
/*      */         {
/* 1107 */           String execute = "Select idMinute from Minute where idHour = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1108 */           if (!stmt.executeQuery(execute).next())
/*      */           {
/* 1110 */             execute = "delete from hour where idHour = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1111 */             stmt.execute(execute);
/*      */           }
/*      */         }
/*      */         
/* 1115 */         rawData = (List)grab.get(8);
/* 1116 */         for (i = 0; i < rawData.size(); i++)
/*      */         {
/* 1118 */           String execute = "Select idHour from Hour where idDay = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1119 */           if (!stmt.executeQuery(execute).next())
/*      */           {
/* 1121 */             execute = "delete from Day where idDay = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1122 */             stmt.execute(execute);
/*      */           }
/*      */         }
/*      */         
/* 1126 */         rawData = (List)grab.get(5);
/* 1127 */         for (i = 0; i < rawData.size(); i++)
/*      */         {
/* 1129 */           String execute = "Select idDay from Day where idMonth = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1130 */           if (!stmt.executeQuery(execute).next())
/*      */           {
/* 1132 */             execute = "delete from Month where idMonth = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1133 */             stmt.execute(execute);
/*      */           }
/*      */         }
/* 1136 */         rawData = (List)grab.get(2);
/* 1137 */         for (i = 0; i < rawData.size(); i++)
/*      */         {
/* 1139 */           String execute = "Select idMonth from Month where idYear = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1140 */           if (!stmt.executeQuery(execute).next())
/*      */           {
/* 1142 */             execute = "delete from year where idYear = " + ((Integer)((List)rawData.get(i)).get(1)).intValue();
/* 1143 */             stmt.execute(execute);
/*      */           }
/*      */         }
/* 1146 */         stmt.close();
/*      */       }
/*      */       catch (Throwable localThrowable1)
/*      */       {
/*  967 */         localThrowable4 = localThrowable1;throw localThrowable1;
/*      */       }
/*      */       finally {}
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1148 */       conn.close();
/*      */     }
/*      */     catch (Throwable localThrowable2)
/*      */     {
/*  964 */       localThrowable3 = localThrowable2;throw localThrowable2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*      */     finally
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1149 */       if (conn != null) { if (localThrowable3 != null) try { conn.close(); } catch (Throwable x2) { localThrowable3.addSuppressed(x2); } else { conn.close();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private List<List<List<Object>>> collectData(int startYear, int startMonth, int startDay, int startHour, int startMinute, int endYear, int endMonth, int endDay, int endHour, int endMinute, boolean generated)
/*      */     throws SQLException, NumberFormatException, UserError
/*      */   {
/* 1159 */     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/forest", userName, passWord);Throwable localThrowable3 = null;
/* 1160 */     List<List<List<Object>>> ret; List<List<Object>> rawDataYear; List<List<Object>> rawDataMonth; List<List<Object>> rawDataDay; List<List<Object>> rawDataHour; List<List<Object>> rawDataMinute; List<List<Object>> rawDataYearStart; List<List<Object>> rawDataMonthStart; List<List<Object>> rawDataDayStart; List<List<Object>> rawDataHourStart; List<List<Object>> rawDataMinuteStart; List<List<Object>> rawDataYearEnd; List<List<Object>> rawDataMonthEnd; List<List<Object>> rawDataDayEnd; List<List<Object>> rawDataHourEnd; List<List<Object>> rawDataMinuteEnd; try { Statement stmt = conn.createStatement();Throwable localThrowable4 = null;
/*      */       try {
/* 1162 */         int[] t = new int[5];
/* 1163 */         ret = new ArrayList();
/* 1164 */         rawDataYear = new ArrayList();
/* 1165 */         rawDataMonth = new ArrayList();
/* 1166 */         rawDataDay = new ArrayList();
/* 1167 */         rawDataHour = new ArrayList();
/* 1168 */         rawDataMinute = new ArrayList();
/* 1169 */         rawDataYearStart = new ArrayList();
/* 1170 */         rawDataMonthStart = new ArrayList();
/* 1171 */         rawDataDayStart = new ArrayList();
/* 1172 */         rawDataHourStart = new ArrayList();
/* 1173 */         rawDataMinuteStart = new ArrayList();
/* 1174 */         rawDataYearEnd = new ArrayList();
/* 1175 */         rawDataMonthEnd = new ArrayList();
/* 1176 */         rawDataDayEnd = new ArrayList();
/* 1177 */         rawDataHourEnd = new ArrayList();
/* 1178 */         rawDataMinuteEnd = new ArrayList();
/*      */         
/*      */ 
/*      */ 
/* 1182 */         conn.setAutoCommit(true);
/* 1183 */         int keyLength = endYear - startYear;
/* 1184 */         if (keyLength > 0)
/*      */         {
/*      */ 
/*      */ 
/* 1188 */           String execute = "SELECT * from year where timeYear < " + endYear + " and timeyear > " + startYear + " order by timeYear";
/* 1189 */           ResultSet rs = stmt.executeQuery(execute);
/* 1190 */           while (rs.next())
/*      */           {
/* 1192 */             List<Object> temp = new ArrayList();
/* 1193 */             temp.add(Integer.valueOf(rs.getInt("timeYear")));
/* 1194 */             temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1195 */             rawDataYear.add(temp);
/*      */           }
/*      */           
/* 1198 */           for (int i = 0; i < rawDataYear.size(); i++)
/*      */           {
/* 1200 */             execute = "SELECT idMonth,timeMonth from Month where idYear = " + ((Integer)((List)rawDataYear.get(i)).get(1)).intValue() + " order by timeMonth";
/* 1201 */             rs = stmt.executeQuery(execute);
/* 1202 */             while (rs.next())
/*      */             {
/* 1204 */               List<Object> temp = new ArrayList();
/* 1205 */               temp.add(Integer.valueOf(rs.getInt("timeMonth")));
/* 1206 */               temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1207 */               rawDataMonth.add(temp);
/*      */             }
/*      */           }
/*      */           
/* 1211 */           for (i = 0; i < rawDataMonth.size(); i++)
/*      */           {
/* 1213 */             execute = "SELECT timeDay,idDay from Day where idMonth = " + ((Integer)((List)rawDataMonth.get(i)).get(1)).intValue() + " order by timeDay";
/* 1214 */             rs = stmt.executeQuery(execute);
/* 1215 */             while (rs.next())
/*      */             {
/* 1217 */               List<Object> temp = new ArrayList();
/* 1218 */               temp.add(Integer.valueOf(rs.getInt("timeDay")));
/* 1219 */               temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1220 */               rawDataDay.add(temp);
/*      */             }
/*      */           }
/*      */           
/* 1224 */           for (i = 0; i < rawDataDay.size(); i++)
/*      */           {
/* 1226 */             execute = "SELECT timeHour,idHour from Hour where idday = " + ((Integer)((List)rawDataDay.get(i)).get(1)).intValue() + " order by timeHour";
/* 1227 */             rs = stmt.executeQuery(execute);
/* 1228 */             while (rs.next())
/*      */             {
/* 1230 */               List<Object> temp = new ArrayList();
/* 1231 */               temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 1232 */               temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1233 */               rawDataHour.add(temp);
/*      */             }
/*      */           }
/*      */           
/* 1237 */           for (i = 0; i < rawDataHour.size(); i++)
/*      */           {
/* 1239 */             execute = "SELECT * from Minute where idHour = " + ((Integer)((List)rawDataHour.get(i)).get(1)).intValue();
/* 1240 */             if (generated)
/*      */             {
/* 1242 */               execute = execute + " and not Created";
/*      */             }
/* 1244 */             execute = execute + " order by timeMinute";
/* 1245 */             rs = stmt.executeQuery(execute);
/* 1246 */             while (rs.next())
/*      */             {
/* 1248 */               List<Object> temp = new ArrayList();
/* 1249 */               temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 1250 */               temp.add(Double.valueOf(rs.getDouble("temp")));
/* 1251 */               temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 1252 */               temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1253 */               temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1254 */               temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1255 */               temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1256 */               rawDataMinute.add(temp);
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 1262 */           execute = "SELECT * from Year where timeYear = " + startYear;
/* 1263 */           rs = stmt.executeQuery(execute);
/* 1264 */           if (rs.next())
/*      */           {
/* 1266 */             List<Object> temp = new ArrayList();
/* 1267 */             temp.add(Integer.valueOf(rs.getInt("timeYear")));
/* 1268 */             temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1269 */             rawDataYearStart.add(temp);
/*      */             
/* 1271 */             execute = "SELECT timeMonth,idMonth from Month where timeMonth = " + startMonth + " and idYear = " + ((Integer)((List)rawDataYearStart.get(0)).get(1)).intValue();
/* 1272 */             rs = stmt.executeQuery(execute);
/* 1273 */             if (rs.next())
/*      */             {
/* 1275 */               temp = new ArrayList();
/* 1276 */               temp.add(Integer.valueOf(rs.getInt("timeMonth")));
/* 1277 */               temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1278 */               rawDataMonthStart.add(temp);
/*      */               
/* 1280 */               execute = "SELECT timeDay,idDay from Day where timeDay = " + startDay + " and idMonth = " + ((Integer)((List)rawDataMonthStart.get(0)).get(1)).intValue();
/* 1281 */               rs = stmt.executeQuery(execute);
/* 1282 */               if (rs.next())
/*      */               {
/* 1284 */                 temp = new ArrayList();
/* 1285 */                 temp.add(Integer.valueOf(rs.getInt("timeDay")));
/* 1286 */                 temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1287 */                 rawDataDayStart.add(temp);
/*      */                 
/* 1289 */                 execute = "SELECT timeHour,idHour from Hour where timeHour = " + startHour + " and idDay = " + ((Integer)((List)rawDataDayStart.get(0)).get(1)).intValue();
/* 1290 */                 rs = stmt.executeQuery(execute);
/* 1291 */                 if (rs.next())
/*      */                 {
/* 1293 */                   temp = new ArrayList();
/* 1294 */                   temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 1295 */                   temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1296 */                   rawDataHourStart.add(temp);
/*      */                   
/* 1298 */                   execute = "SELECT * from Minute where timeMinute > " + startMinute + " and idHour = " + ((Integer)((List)rawDataHourStart.get(0)).get(1)).intValue();
/* 1299 */                   if (generated)
/*      */                   {
/* 1301 */                     execute = execute + " and not Created";
/*      */                   }
/* 1303 */                   execute = execute + " order by timeMinute";
/* 1304 */                   rs = stmt.executeQuery(execute);
/* 1305 */                   while (rs.next())
/*      */                   {
/* 1307 */                     temp = new ArrayList();
/* 1308 */                     temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 1309 */                     temp.add(Double.valueOf(rs.getDouble("temp")));
/* 1310 */                     temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 1311 */                     temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1312 */                     temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1313 */                     temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1314 */                     temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1315 */                     rawDataMinuteStart.add(temp);
/*      */                   }
/*      */                 }
/*      */                 
/* 1319 */                 execute = "SELECT timeHour,idHour from Hour where timeHour > " + startHour + " and idDay = " + ((Integer)((List)rawDataDayStart.get(0)).get(1)).intValue() + " order by timeHour";
/* 1320 */                 rs = stmt.executeQuery(execute);
/* 1321 */                 t[0] = rawDataHourStart.size();
/* 1322 */                 while (rs.next())
/*      */                 {
/* 1324 */                   temp = new ArrayList();
/* 1325 */                   temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 1326 */                   temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1327 */                   rawDataHourStart.add(temp);
/*      */                 }
/*      */                 
/* 1330 */                 for (i = t[0]; i < rawDataHourStart.size(); i++)
/*      */                 {
/* 1332 */                   execute = "SELECT * FROM Minute where idHour = " + ((Integer)((List)rawDataHourStart.get(i)).get(1)).intValue();
/* 1333 */                   if (generated)
/*      */                   {
/* 1335 */                     execute = execute + " and not Created";
/*      */                   }
/* 1337 */                   execute = execute + " order by timeMinute";
/* 1338 */                   rs = stmt.executeQuery(execute);
/* 1339 */                   while (rs.next()) {
/* 1340 */                     temp = new ArrayList();
/* 1341 */                     temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 1342 */                     temp.add(Double.valueOf(rs.getDouble("temp")));
/* 1343 */                     temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 1344 */                     temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1345 */                     temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1346 */                     temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1347 */                     temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1348 */                     rawDataMinuteStart.add(temp);
/*      */                   }
/*      */                 }
/*      */               }
/*      */               
/* 1353 */               execute = "SELECT timeDay,idDay from Day where timeDay > " + startDay + " and idMonth = " + ((Integer)((List)rawDataMonthStart.get(0)).get(1)).intValue() + " order by timeDay";
/* 1354 */               rs = stmt.executeQuery(execute);
/* 1355 */               t[0] = rawDataDayStart.size();
/* 1356 */               while (rs.next())
/*      */               {
/* 1358 */                 temp = new ArrayList();
/* 1359 */                 temp.add(Integer.valueOf(rs.getInt("timeDay")));
/* 1360 */                 temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1361 */                 rawDataDayStart.add(temp);
/*      */               }
/*      */               
/* 1364 */               t[1] = rawDataHourStart.size();
/* 1365 */               for (i = t[0]; i < rawDataDayStart.size(); i++)
/*      */               {
/*      */ 
/* 1368 */                 execute = "SELECT timeHour,idHour FROM hour where idDay = " + ((Integer)((List)rawDataDayStart.get(i)).get(1)).intValue() + " order by timeHour";
/* 1369 */                 rs = stmt.executeQuery(execute);
/* 1370 */                 while (rs.next())
/*      */                 {
/* 1372 */                   temp = new ArrayList();
/* 1373 */                   temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 1374 */                   temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1375 */                   rawDataHourStart.add(temp);
/*      */                 }
/*      */               }
/*      */               
/* 1379 */               for (i = t[1]; i < rawDataHourStart.size(); i++)
/*      */               {
/* 1381 */                 execute = "SELECT * FROM Minute where idHour = " + ((Integer)((List)rawDataHourStart.get(i)).get(1)).intValue() + " order by timeMinute";
/* 1382 */                 rs = stmt.executeQuery(execute);
/* 1383 */                 while (rs.next())
/*      */                 {
/* 1385 */                   temp = new ArrayList();
/* 1386 */                   temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 1387 */                   temp.add(Double.valueOf(rs.getDouble("temp")));
/* 1388 */                   temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 1389 */                   temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1390 */                   temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1391 */                   temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1392 */                   temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1393 */                   rawDataMinuteStart.add(temp);
/*      */                 }
/*      */               }
/*      */             }
/*      */             
/* 1398 */             execute = "SELECT timeMonth,idMonth from Month where idYear = " + ((Integer)((List)rawDataYearStart.get(0)).get(1)).intValue() + " and timeMonth > " + startMonth + " order by timeMonth";
/* 1399 */             rs = stmt.executeQuery(execute);
/* 1400 */             t[0] = rawDataMonthStart.size();
/* 1401 */             while (rs.next())
/*      */             {
/* 1403 */               temp = new ArrayList();
/* 1404 */               temp.add(Integer.valueOf(rs.getInt("timeMonth")));
/* 1405 */               temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1406 */               rawDataMonthStart.add(temp);
/*      */             }
/*      */             
/* 1409 */             t[1] = rawDataDayStart.size();
/* 1410 */             for (i = t[0]; i < rawDataMonthStart.size(); i++)
/*      */             {
/* 1412 */               execute = "SELECT timeDay,idDay FROM day where idMonth = " + ((Integer)((List)rawDataMonthStart.get(i)).get(1)).intValue() + " order by timeDay";
/* 1413 */               rs = stmt.executeQuery(execute);
/* 1414 */               while (rs.next())
/*      */               {
/* 1416 */                 temp = new ArrayList();
/* 1417 */                 temp.add(Integer.valueOf(rs.getInt("timeDay")));
/* 1418 */                 temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1419 */                 rawDataDayStart.add(temp);
/*      */               }
/*      */             }
/*      */             
/* 1423 */             t[2] = rawDataHourStart.size();
/* 1424 */             for (i = t[1]; rawDataDayStart.size() > i; i++) {
/* 1425 */               execute = "SELECT idHour,timeHour FROM hour where idDay = " + ((Integer)((List)rawDataDayStart.get(i)).get(1)).intValue() + " order by timeHour";
/* 1426 */               rs = stmt.executeQuery(execute);
/* 1427 */               while (rs.next())
/*      */               {
/* 1429 */                 temp = new ArrayList();
/* 1430 */                 temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 1431 */                 temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1432 */                 rawDataHourStart.add(temp);
/*      */               }
/*      */             }
/*      */             
/* 1436 */             for (i = t[2]; i < rawDataHourStart.size(); i++)
/*      */             {
/* 1438 */               execute = "SELECT * FROM Minute where idHour = " + ((Integer)((List)rawDataHourStart.get(i)).get(1)).intValue() + " order by timeMinute";
/* 1439 */               rs = stmt.executeQuery(execute);
/* 1440 */               while (rs.next())
/*      */               {
/* 1442 */                 temp = new ArrayList();
/* 1443 */                 temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 1444 */                 temp.add(Double.valueOf(rs.getDouble("temp")));
/* 1445 */                 temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 1446 */                 temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1447 */                 temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1448 */                 temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1449 */                 temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1450 */                 rawDataMinuteStart.add(temp);
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 1457 */           execute = "SELECT * from year where timeyear = " + endYear;
/* 1458 */           rs = stmt.executeQuery(execute);
/* 1459 */           if (rs.next())
/*      */           {
/* 1461 */             List<Object> temp = new ArrayList();
/* 1462 */             temp.add(Integer.valueOf(rs.getInt("timeYear")));
/* 1463 */             temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1464 */             rawDataYearEnd.add(temp);
/*      */             
/* 1466 */             execute = "Select idMonth,timeMonth from month where timeMonth < " + endMonth + " and idYear = " + ((Integer)((List)rawDataYearEnd.get(rawDataYearEnd.size() - 1)).get(1)).intValue() + " order by timeMonth";
/* 1467 */             rs = stmt.executeQuery(execute);
/* 1468 */             while (rs.next())
/*      */             {
/* 1470 */               temp = new ArrayList();
/* 1471 */               temp.add(Integer.valueOf(rs.getInt("timeMonth")));
/* 1472 */               temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1473 */               rawDataMonthEnd.add(temp);
/*      */             }
/* 1475 */             for (i = 0; i < rawDataMonthEnd.size(); i++) {
/* 1476 */               execute = "select idDay, timeDay from day where idMonth = " + ((Integer)((List)rawDataMonthEnd.get(i)).get(1)).intValue() + " order by timeDay";
/* 1477 */               rs = stmt.executeQuery(execute);
/* 1478 */               while (rs.next()) {
/* 1479 */                 temp = new ArrayList();
/* 1480 */                 temp.add(Integer.valueOf(rs.getInt("timeDay")));
/* 1481 */                 temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1482 */                 rawDataDayEnd.add(temp);
/*      */               }
/*      */             }
/* 1485 */             for (i = 0; i < rawDataDayEnd.size(); i++) {
/* 1486 */               execute = "select idHour, timeHour from hour where idDay = " + ((Integer)((List)rawDataDayEnd.get(i)).get(1)).intValue() + " order by timeHour";
/* 1487 */               rs = stmt.executeQuery(execute);
/* 1488 */               while (rs.next()) {
/* 1489 */                 temp = new ArrayList();
/* 1490 */                 temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 1491 */                 temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1492 */                 rawDataHourEnd.add(temp);
/*      */               }
/*      */             }
/* 1495 */             for (i = 0; i < rawDataHourEnd.size(); i++) {
/* 1496 */               execute = "select * from minute where idHour = " + ((Integer)((List)rawDataHourEnd.get(i)).get(1)).intValue();
/* 1497 */               if (generated) {
/* 1498 */                 execute = execute + " and not Created";
/*      */               }
/* 1500 */               execute = execute + " order by timeMinute";
/* 1501 */               rs = stmt.executeQuery(execute);
/* 1502 */               while (rs.next()) {
/* 1503 */                 temp = new ArrayList();
/* 1504 */                 temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 1505 */                 temp.add(Double.valueOf(rs.getDouble("temp")));
/* 1506 */                 temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 1507 */                 temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1508 */                 temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1509 */                 temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1510 */                 temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1511 */                 rawDataMinuteEnd.add(temp);
/*      */               }
/*      */             }
/*      */             
/* 1515 */             execute = "Select idMonth, timeMonth from month where idYear = " + ((Integer)((List)rawDataYearEnd.get(rawDataYearEnd.size() - 1)).get(1)).intValue() + " and timeMonth = " + endMonth;
/* 1516 */             rs = stmt.executeQuery(execute);
/* 1517 */             if (rs.next())
/*      */             {
/* 1519 */               temp = new ArrayList();
/* 1520 */               temp.add(Integer.valueOf(rs.getInt("timeMonth")));
/* 1521 */               temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1522 */               rawDataMonthEnd.add(temp);
/*      */               
/* 1524 */               t[0] = rawDataDayEnd.size();
/* 1525 */               execute = "select idDay, timeDay from day where idMonth = " + ((Integer)((List)rawDataMonthEnd.get(rawDataMonthEnd.size() - 1)).get(1)).intValue() + " and timeDay < " + endDay + " order by timeDay";
/* 1526 */               rs = stmt.executeQuery(execute);
/* 1527 */               while (rs.next())
/*      */               {
/* 1529 */                 temp = new ArrayList();
/* 1530 */                 temp.add(Integer.valueOf(rs.getInt("timeDay")));
/* 1531 */                 temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1532 */                 rawDataDayEnd.add(temp);
/*      */               }
/* 1534 */               t[1] = rawDataHourEnd.size();
/* 1535 */               for (i = t[0]; i < rawDataDayEnd.size(); i++)
/*      */               {
/* 1537 */                 execute = "select idHour, timeHour from hour where idDay = " + ((Integer)((List)rawDataDayEnd.get(i)).get(1)).intValue() + " order by timeHour";
/* 1538 */                 rs = stmt.executeQuery(execute);
/* 1539 */                 while (rs.next())
/*      */                 {
/* 1541 */                   temp = new ArrayList();
/* 1542 */                   temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 1543 */                   temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1544 */                   rawDataHourEnd.add(temp);
/*      */                 }
/*      */               }
/* 1547 */               for (i = t[1]; i < rawDataHourEnd.size(); i++)
/*      */               {
/* 1549 */                 execute = "select * from Minute where idHour = " + ((Integer)((List)rawDataHourEnd.get(i)).get(1)).intValue();
/* 1550 */                 if (generated)
/*      */                 {
/* 1552 */                   execute = execute + " and not Created";
/*      */                 }
/* 1554 */                 execute = execute + " order by timeMinute";
/* 1555 */                 rs = stmt.executeQuery(execute);
/* 1556 */                 while (rs.next())
/*      */                 {
/* 1558 */                   temp = new ArrayList();
/* 1559 */                   temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 1560 */                   temp.add(Double.valueOf(rs.getDouble("temp")));
/* 1561 */                   temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 1562 */                   temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1563 */                   temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1564 */                   temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1565 */                   temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1566 */                   rawDataMinuteEnd.add(temp);
/*      */                 }
/*      */               }
/*      */               
/* 1570 */               execute = "Select idDay,timeDay from Day where idMonth = " + ((Integer)((List)rawDataMonthEnd.get(rawDataMonthEnd.size() - 1)).get(1)).intValue() + " and timeDay = " + endDay;
/* 1571 */               rs = stmt.executeQuery(execute);
/* 1572 */               if (rs.next())
/*      */               {
/* 1574 */                 temp = new ArrayList();
/* 1575 */                 temp.add(Integer.valueOf(rs.getInt("timeDay")));
/* 1576 */                 temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1577 */                 rawDataDayEnd.add(temp);
/* 1578 */                 t[0] = rawDataHourEnd.size();
/* 1579 */                 execute = "Select idHour,timeHour from hour where idDay = " + ((Integer)((List)rawDataDayEnd.get(rawDataDayEnd.size() - 1)).get(1)).intValue() + " and timeHour < " + endHour + " order by timeHour";
/* 1580 */                 rs = stmt.executeQuery(execute);
/* 1581 */                 while (rs.next())
/*      */                 {
/* 1583 */                   temp = new ArrayList();
/* 1584 */                   temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 1585 */                   temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1586 */                   rawDataHourEnd.add(temp);
/*      */                 }
/* 1588 */                 for (i = t[0]; i < rawDataHourEnd.size(); i++)
/*      */                 {
/* 1590 */                   execute = "select * from minute where idHour = " + ((Integer)((List)rawDataHourEnd.get(i)).get(1)).intValue();
/* 1591 */                   if (generated)
/*      */                   {
/* 1593 */                     execute = execute + " and not Created";
/*      */                   }
/* 1595 */                   execute = execute + " order by timeMinute";
/* 1596 */                   rs = stmt.executeQuery(execute);
/* 1597 */                   while (rs.next()) {
/* 1598 */                     temp = new ArrayList();
/* 1599 */                     temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 1600 */                     temp.add(Double.valueOf(rs.getDouble("temp")));
/* 1601 */                     temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 1602 */                     temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1603 */                     temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1604 */                     temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1605 */                     temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1606 */                     rawDataMinuteEnd.add(temp);
/*      */                   }
/*      */                 }
/* 1609 */                 execute = "select idHour, timeHour from hour where idDay = " + ((Integer)((List)rawDataDayEnd.get(rawDataDayEnd.size() - 1)).get(1)).intValue() + " and timeHour = " + endHour + " order by timeHour";
/* 1610 */                 rs = stmt.executeQuery(execute);
/* 1611 */                 if (rs.next())
/*      */                 {
/* 1613 */                   temp = new ArrayList();
/* 1614 */                   temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 1615 */                   temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1616 */                   rawDataHourEnd.add(temp);
/* 1617 */                   execute = "select * from minute where idHour = " + ((Integer)((List)rawDataHourEnd.get(rawDataHourEnd.size() - 1)).get(1)).intValue() + " and timeMinute < " + endMinute;
/* 1618 */                   if (generated)
/*      */                   {
/* 1620 */                     execute = execute + " and not Created";
/*      */                   }
/* 1622 */                   execute = execute + " order by timeMinute";
/* 1623 */                   rs = stmt.executeQuery(execute);
/* 1624 */                   while (rs.next())
/*      */                   {
/* 1626 */                     temp = new ArrayList();
/* 1627 */                     temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 1628 */                     temp.add(Double.valueOf(rs.getDouble("temp")));
/* 1629 */                     temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 1630 */                     temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1631 */                     temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1632 */                     temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1633 */                     temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1634 */                     rawDataMinuteEnd.add(temp);
/*      */                   }
/*      */                   
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/* 1642 */         else if (keyLength == 0)
/*      */         {
/* 1644 */           String execute = "SELECT * from year where timeYear = " + startYear;
/* 1645 */           ResultSet rs = stmt.executeQuery(execute);
/* 1646 */           if (rs.next()) {
/* 1647 */             List<Object> temp = new ArrayList();
/* 1648 */             temp.add(Integer.valueOf(rs.getInt("timeYear")));
/* 1649 */             temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1650 */             rawDataYear.add(temp);
/* 1651 */             rawDataYearStart.add(temp);
/* 1652 */             rawDataYearEnd.add(temp);
/* 1653 */             keyLength = endMonth - startMonth;
/* 1654 */             if (keyLength > 0)
/*      */             {
/*      */ 
/*      */ 
/* 1658 */               execute = "select timeMonth,idMonth from month where idYear = " + ((Integer)((List)rawDataYear.get(0)).get(1)).intValue() + " and timeMonth < " + endMonth + " and timeMonth > " + startMonth + " order by timeMonth";
/* 1659 */               rs = stmt.executeQuery(execute);
/* 1660 */               while (rs.next()) {
/* 1661 */                 temp = new ArrayList();
/* 1662 */                 temp.add(Integer.valueOf(rs.getInt("timeMonth")));
/* 1663 */                 temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1664 */                 rawDataMonth.add(temp);
/*      */               }
/* 1666 */               for (int i = 0; i < rawDataMonth.size(); i++)
/*      */               {
/* 1668 */                 execute = "SELECT timeDay,idDay from Day where idMonth = " + ((Integer)((List)rawDataMonth.get(i)).get(1)).intValue() + " order by timeDay";
/* 1669 */                 rs = stmt.executeQuery(execute);
/* 1670 */                 while (rs.next())
/*      */                 {
/* 1672 */                   temp = new ArrayList();
/* 1673 */                   temp.add(Integer.valueOf(rs.getInt("timeDay")));
/* 1674 */                   temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1675 */                   rawDataDay.add(temp);
/*      */                 }
/*      */               }
/*      */               
/* 1679 */               for (i = 0; i < rawDataDay.size(); i++)
/*      */               {
/* 1681 */                 execute = "SELECT timeHour,idHour from Hour where idday = " + ((Integer)((List)rawDataDay.get(i)).get(1)).intValue() + " order by timeHour";
/* 1682 */                 rs = stmt.executeQuery(execute);
/* 1683 */                 while (rs.next())
/*      */                 {
/* 1685 */                   temp = new ArrayList();
/* 1686 */                   temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 1687 */                   temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1688 */                   rawDataHour.add(temp);
/*      */                 }
/*      */               }
/*      */               
/* 1692 */               for (i = 0; i < rawDataHour.size(); i++)
/*      */               {
/* 1694 */                 execute = "SELECT * from Minute where idHour = " + ((Integer)((List)rawDataHour.get(i)).get(1)).intValue();
/* 1695 */                 if (generated)
/*      */                 {
/* 1697 */                   execute = execute + " and not Created";
/*      */                 }
/* 1699 */                 execute = execute + " order by timeMinute";
/* 1700 */                 rs = stmt.executeQuery(execute);
/* 1701 */                 while (rs.next())
/*      */                 {
/* 1703 */                   temp = new ArrayList();
/* 1704 */                   temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 1705 */                   temp.add(Double.valueOf(rs.getDouble("temp")));
/* 1706 */                   temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 1707 */                   temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1708 */                   temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1709 */                   temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1710 */                   temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1711 */                   rawDataMinute.add(temp);
/*      */                 }
/*      */               }
/*      */               
/*      */ 
/*      */ 
/*      */ 
/* 1718 */               execute = "SELECT timeMonth,idMonth from Month where timeMonth = " + startMonth + " and idYear = " + ((Integer)((List)rawDataYearStart.get(0)).get(1)).intValue();
/* 1719 */               rs = stmt.executeQuery(execute);
/* 1720 */               if (rs.next())
/*      */               {
/* 1722 */                 temp = new ArrayList();
/* 1723 */                 temp.add(Integer.valueOf(rs.getInt("timeMonth")));
/* 1724 */                 temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1725 */                 rawDataMonthStart.add(temp);
/*      */                 
/* 1727 */                 execute = "SELECT timeDay,idDay from Day where timeDay = " + startDay + " and idMonth = " + ((Integer)((List)rawDataMonthStart.get(0)).get(1)).intValue();
/* 1728 */                 rs = stmt.executeQuery(execute);
/* 1729 */                 if (rs.next())
/*      */                 {
/* 1731 */                   temp = new ArrayList();
/* 1732 */                   temp.add(Integer.valueOf(rs.getInt("timeDay")));
/* 1733 */                   temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1734 */                   rawDataDayStart.add(temp);
/*      */                   
/* 1736 */                   execute = "SELECT timeHour,idHour from Hour where timeHour = " + startHour + " and idDay = " + ((Integer)((List)rawDataDayStart.get(0)).get(1)).intValue();
/* 1737 */                   rs = stmt.executeQuery(execute);
/* 1738 */                   if (rs.next())
/*      */                   {
/* 1740 */                     temp = new ArrayList();
/* 1741 */                     temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 1742 */                     temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1743 */                     rawDataHourStart.add(temp);
/*      */                     
/* 1745 */                     execute = "SELECT * from Minute where timeMinute > " + startMinute + " and idHour = " + ((Integer)((List)rawDataHourStart.get(0)).get(1)).intValue();
/* 1746 */                     if (generated)
/*      */                     {
/* 1748 */                       execute = execute + " and not Created";
/*      */                     }
/* 1750 */                     execute = execute + " order by timeMinute";
/* 1751 */                     rs = stmt.executeQuery(execute);
/* 1752 */                     while (rs.next())
/*      */                     {
/* 1754 */                       temp = new ArrayList();
/* 1755 */                       temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 1756 */                       temp.add(Double.valueOf(rs.getDouble("temp")));
/* 1757 */                       temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 1758 */                       temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1759 */                       temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1760 */                       temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1761 */                       temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1762 */                       rawDataMinuteStart.add(temp);
/*      */                     }
/*      */                   }
/*      */                   
/* 1766 */                   execute = "SELECT timeHour,idHour from Hour where timeHour > " + startHour + " and idDay = " + ((Integer)((List)rawDataDayStart.get(0)).get(1)).intValue() + " order by timeHour";
/* 1767 */                   rs = stmt.executeQuery(execute);
/* 1768 */                   t[0] = rawDataHourStart.size();
/* 1769 */                   while (rs.next())
/*      */                   {
/* 1771 */                     temp = new ArrayList();
/* 1772 */                     temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 1773 */                     temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1774 */                     rawDataHourStart.add(temp);
/*      */                   }
/*      */                   
/* 1777 */                   for (i = t[0]; i < rawDataHourStart.size(); i++)
/*      */                   {
/* 1779 */                     execute = "SELECT * FROM Minute where idHour = " + ((Integer)((List)rawDataHourStart.get(i)).get(1)).intValue();
/* 1780 */                     if (generated)
/*      */                     {
/* 1782 */                       execute = execute + " and not Created";
/*      */                     }
/* 1784 */                     execute = execute + " order by timeMinute";
/* 1785 */                     rs = stmt.executeQuery(execute);
/* 1786 */                     while (rs.next())
/*      */                     {
/* 1788 */                       temp = new ArrayList();
/* 1789 */                       temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 1790 */                       temp.add(Double.valueOf(rs.getDouble("temp")));
/* 1791 */                       temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 1792 */                       temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1793 */                       temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1794 */                       temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1795 */                       temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1796 */                       rawDataMinuteStart.add(temp);
/*      */                     }
/*      */                   }
/*      */                 }
/*      */                 
/* 1801 */                 execute = "SELECT timeDay,idDay from Day where timeDay > " + startDay + " and idMonth = " + ((Integer)((List)rawDataMonthStart.get(0)).get(1)).intValue() + " order by timeDay";
/* 1802 */                 rs = stmt.executeQuery(execute);
/* 1803 */                 t[0] = rawDataDayStart.size();
/* 1804 */                 while (rs.next())
/*      */                 {
/* 1806 */                   temp = new ArrayList();
/* 1807 */                   temp.add(Integer.valueOf(rs.getInt("timeDay")));
/* 1808 */                   temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1809 */                   rawDataDayStart.add(temp);
/*      */                 }
/*      */                 
/* 1812 */                 t[1] = rawDataHourStart.size();
/* 1813 */                 for (i = t[0]; i < rawDataDayStart.size(); i++)
/*      */                 {
/*      */ 
/* 1816 */                   execute = "SELECT timeHour,idHour FROM hour where idDay = " + ((Integer)((List)rawDataDayStart.get(i)).get(1)).intValue() + " order by timeHour";
/* 1817 */                   rs = stmt.executeQuery(execute);
/* 1818 */                   while (rs.next())
/*      */                   {
/* 1820 */                     temp = new ArrayList();
/* 1821 */                     temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 1822 */                     temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1823 */                     rawDataHourStart.add(temp);
/*      */                   }
/*      */                 }
/*      */                 
/* 1827 */                 for (i = t[1]; i < rawDataHourStart.size(); i++)
/*      */                 {
/* 1829 */                   execute = "SELECT * FROM Minute where idHour = " + ((Integer)((List)rawDataHourStart.get(i)).get(1)).intValue() + " order by timeMinute";
/* 1830 */                   rs = stmt.executeQuery(execute);
/* 1831 */                   while (rs.next())
/*      */                   {
/* 1833 */                     temp = new ArrayList();
/* 1834 */                     temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 1835 */                     temp.add(Double.valueOf(rs.getDouble("temp")));
/* 1836 */                     temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 1837 */                     temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1838 */                     temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1839 */                     temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1840 */                     temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1841 */                     rawDataMinuteStart.add(temp);
/*      */                   }
/*      */                 }
/*      */               }
/*      */               
/*      */ 
/*      */ 
/* 1848 */               execute = "Select idMonth, timeMonth from month where idYear = " + ((Integer)((List)rawDataYearEnd.get(rawDataYearEnd.size() - 1)).get(1)).intValue() + " and timeMonth = " + endMonth;
/* 1849 */               rs = stmt.executeQuery(execute);
/* 1850 */               if (rs.next()) {
/* 1851 */                 temp = new ArrayList();
/* 1852 */                 temp.add(Integer.valueOf(rs.getInt("timeMonth")));
/* 1853 */                 temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1854 */                 rawDataMonthEnd.add(temp);
/*      */                 
/* 1856 */                 t[0] = rawDataDayEnd.size();
/* 1857 */                 execute = "select idDay, timeDay from day where idMonth = " + ((Integer)((List)rawDataMonthEnd.get(rawDataMonthEnd.size() - 1)).get(1)).intValue() + " and timeDay < " + endDay + " order by timeDay";
/* 1858 */                 rs = stmt.executeQuery(execute);
/* 1859 */                 while (rs.next())
/*      */                 {
/* 1861 */                   temp = new ArrayList();
/* 1862 */                   temp.add(Integer.valueOf(rs.getInt("timeDay")));
/* 1863 */                   temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1864 */                   rawDataDayEnd.add(temp);
/*      */                 }
/* 1866 */                 t[1] = rawDataHourEnd.size();
/* 1867 */                 for (i = t[0]; i < rawDataDayEnd.size(); i++)
/*      */                 {
/* 1869 */                   execute = "select idHour, timeHour from hour where idDay = " + ((Integer)((List)rawDataDayEnd.get(i)).get(1)).intValue() + " order by timeHour";
/* 1870 */                   rs = stmt.executeQuery(execute);
/* 1871 */                   while (rs.next())
/*      */                   {
/* 1873 */                     temp = new ArrayList();
/* 1874 */                     temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 1875 */                     temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1876 */                     rawDataHourEnd.add(temp);
/*      */                   }
/*      */                 }
/* 1879 */                 for (i = t[1]; i < rawDataHourEnd.size(); i++)
/*      */                 {
/* 1881 */                   execute = "select * from Minute where idHour = " + ((Integer)((List)rawDataHourEnd.get(i)).get(1)).intValue();
/* 1882 */                   if (generated)
/*      */                   {
/* 1884 */                     execute = execute + " and not Created";
/*      */                   }
/* 1886 */                   execute = execute + " order by timeMinute";
/* 1887 */                   rs = stmt.executeQuery(execute);
/* 1888 */                   while (rs.next())
/*      */                   {
/* 1890 */                     temp = new ArrayList();
/* 1891 */                     temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 1892 */                     temp.add(Double.valueOf(rs.getDouble("temp")));
/* 1893 */                     temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 1894 */                     temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1895 */                     temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1896 */                     temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1897 */                     temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1898 */                     rawDataMinuteEnd.add(temp);
/*      */                   }
/*      */                 }
/*      */                 
/* 1902 */                 execute = "Select idDay,timeDay from Day where idMonth = " + ((Integer)((List)rawDataMonthEnd.get(rawDataMonthEnd.size() - 1)).get(1)).intValue() + " and timeDay = " + endDay;
/* 1903 */                 rs = stmt.executeQuery(execute);
/* 1904 */                 if (rs.next())
/*      */                 {
/* 1906 */                   temp = new ArrayList();
/* 1907 */                   temp.add(Integer.valueOf(rs.getInt("timeDay")));
/* 1908 */                   temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1909 */                   rawDataDayEnd.add(temp);
/* 1910 */                   t[0] = rawDataHourEnd.size();
/* 1911 */                   execute = "Select idHour,timeHour from hour where idDay = " + ((Integer)((List)rawDataDayEnd.get(rawDataDayEnd.size() - 1)).get(1)).intValue() + " and timeHour < " + endHour + " order by timeHour";
/* 1912 */                   rs = stmt.executeQuery(execute);
/* 1913 */                   while (rs.next())
/*      */                   {
/* 1915 */                     temp = new ArrayList();
/* 1916 */                     temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 1917 */                     temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1918 */                     rawDataHourEnd.add(temp);
/*      */                   }
/* 1920 */                   for (i = t[0]; i < rawDataHourEnd.size(); i++) {
/* 1921 */                     execute = "select * from minute where idHour = " + ((Integer)((List)rawDataHourEnd.get(i)).get(1)).intValue();
/* 1922 */                     if (generated)
/*      */                     {
/* 1924 */                       execute = execute + " and not Created";
/*      */                     }
/* 1926 */                     execute = execute + " order by timeMinute";
/* 1927 */                     rs = stmt.executeQuery(execute);
/* 1928 */                     while (rs.next())
/*      */                     {
/* 1930 */                       temp = new ArrayList();
/* 1931 */                       temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 1932 */                       temp.add(Double.valueOf(rs.getDouble("temp")));
/* 1933 */                       temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 1934 */                       temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1935 */                       temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1936 */                       temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1937 */                       temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1938 */                       rawDataMinuteEnd.add(temp);
/*      */                     }
/*      */                   }
/* 1941 */                   execute = "select idHour, timeHour from hour where idDay = " + ((Integer)((List)rawDataDayEnd.get(rawDataDayEnd.size() - 1)).get(1)).intValue() + " and timeHour = " + endHour + " order by timeHour";
/* 1942 */                   rs = stmt.executeQuery(execute);
/* 1943 */                   if (rs.next())
/*      */                   {
/* 1945 */                     temp = new ArrayList();
/* 1946 */                     temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 1947 */                     temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1948 */                     rawDataHourEnd.add(temp);
/* 1949 */                     execute = "select * from minute where idHour = " + ((Integer)((List)rawDataHourEnd.get(rawDataHourEnd.size() - 1)).get(1)).intValue() + " and timeMinute < " + endMinute;
/* 1950 */                     if (generated)
/*      */                     {
/* 1952 */                       execute = execute + " and not Created";
/*      */                     }
/* 1954 */                     execute = execute + " order by timeMinute";
/* 1955 */                     rs = stmt.executeQuery(execute);
/* 1956 */                     while (rs.next())
/*      */                     {
/* 1958 */                       temp = new ArrayList();
/* 1959 */                       temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 1960 */                       temp.add(Double.valueOf(rs.getDouble("temp")));
/* 1961 */                       temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 1962 */                       temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 1963 */                       temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1964 */                       temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1965 */                       temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 1966 */                       rawDataMinuteEnd.add(temp);
/*      */                     }
/*      */                     
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/* 1973 */             else if (keyLength == 0)
/*      */             {
/* 1975 */               execute = "SELECT idMonth, timeMonth from Month where timeMonth = " + startMonth + " and idYear = " + ((Integer)((List)rawDataYear.get(0)).get(1)).intValue();
/* 1976 */               rs = stmt.executeQuery(execute);
/* 1977 */               if (rs.next())
/*      */               {
/* 1979 */                 temp = new ArrayList();
/* 1980 */                 temp.add(Integer.valueOf(rs.getInt("timeMonth")));
/* 1981 */                 temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 1982 */                 rawDataMonth.add(temp);
/* 1983 */                 rawDataMonthStart.add(temp);
/* 1984 */                 rawDataMonthEnd.add(temp);
/* 1985 */                 keyLength = endDay - startDay;
/* 1986 */                 if (keyLength > 0)
/*      */                 {
/*      */ 
/* 1989 */                   execute = "select timeDay,idDay from day where idMonth = " + ((Integer)((List)rawDataMonth.get(0)).get(1)).intValue() + " and timeDay < " + endDay + " and timeDay > " + startDay + " order by timeDay";
/* 1990 */                   rs = stmt.executeQuery(execute);
/* 1991 */                   while (rs.next())
/*      */                   {
/* 1993 */                     temp = new ArrayList();
/* 1994 */                     temp.add(Integer.valueOf(rs.getInt("timeDay")));
/* 1995 */                     temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 1996 */                     rawDataDay.add(temp);
/*      */                   }
/*      */                   
/* 1999 */                   for (int i = 0; i < rawDataDay.size(); i++)
/*      */                   {
/* 2001 */                     execute = "SELECT timeHour,idHour from Hour where idday = " + ((Integer)((List)rawDataDay.get(i)).get(1)).intValue() + " order by timeHour";
/* 2002 */                     rs = stmt.executeQuery(execute);
/* 2003 */                     while (rs.next()) {
/* 2004 */                       temp = new ArrayList();
/* 2005 */                       temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 2006 */                       temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 2007 */                       rawDataHour.add(temp);
/*      */                     }
/*      */                   }
/*      */                   
/* 2011 */                   for (i = 0; i < rawDataHour.size(); i++)
/*      */                   {
/* 2013 */                     execute = "SELECT * from Minute where idHour = " + ((Integer)((List)rawDataHour.get(i)).get(1)).intValue();
/* 2014 */                     if (generated) {
/* 2015 */                       execute = execute + " and not Created";
/*      */                     }
/* 2017 */                     execute = execute + " order by timeMinute";
/* 2018 */                     rs = stmt.executeQuery(execute);
/* 2019 */                     while (rs.next())
/*      */                     {
/* 2021 */                       temp = new ArrayList();
/* 2022 */                       temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 2023 */                       temp.add(Double.valueOf(rs.getDouble("temp")));
/* 2024 */                       temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 2025 */                       temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 2026 */                       temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 2027 */                       temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 2028 */                       temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 2029 */                       rawDataMinute.add(temp);
/*      */                     }
/*      */                   }
/*      */                   
/*      */ 
/*      */ 
/* 2035 */                   execute = "SELECT timeDay,idDay from Day where timeDay = " + startDay + " and idMonth = " + ((Integer)((List)rawDataMonthStart.get(0)).get(1)).intValue();
/* 2036 */                   rs = stmt.executeQuery(execute);
/* 2037 */                   if (rs.next())
/*      */                   {
/* 2039 */                     temp = new ArrayList();
/* 2040 */                     temp.add(Integer.valueOf(rs.getInt("timeDay")));
/* 2041 */                     temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 2042 */                     rawDataDayStart.add(temp);
/*      */                     
/* 2044 */                     execute = "SELECT timeHour,idHour from Hour where timeHour = " + startHour + " and idDay = " + ((Integer)((List)rawDataDayStart.get(0)).get(1)).intValue();
/* 2045 */                     rs = stmt.executeQuery(execute);
/* 2046 */                     if (rs.next())
/*      */                     {
/* 2048 */                       temp = new ArrayList();
/* 2049 */                       temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 2050 */                       temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 2051 */                       rawDataHourStart.add(temp);
/*      */                       
/* 2053 */                       execute = "SELECT * from Minute where timeMinute > " + startMinute + " and idHour = " + ((Integer)((List)rawDataHourStart.get(0)).get(1)).intValue();
/* 2054 */                       if (generated)
/*      */                       {
/* 2056 */                         execute = execute + " and not Created";
/*      */                       }
/* 2058 */                       execute = execute + " order by timeMinute";
/* 2059 */                       rs = stmt.executeQuery(execute);
/* 2060 */                       while (rs.next())
/*      */                       {
/* 2062 */                         temp = new ArrayList();
/* 2063 */                         temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 2064 */                         temp.add(Double.valueOf(rs.getDouble("temp")));
/* 2065 */                         temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 2066 */                         temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 2067 */                         temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 2068 */                         temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 2069 */                         temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 2070 */                         rawDataMinuteStart.add(temp);
/*      */                       }
/*      */                     }
/*      */                     
/* 2074 */                     execute = "SELECT timeHour,idHour from Hour where timeHour > " + startHour + " and idDay = " + ((Integer)((List)rawDataDayStart.get(0)).get(1)).intValue() + " order by timeHour";
/* 2075 */                     rs = stmt.executeQuery(execute);
/* 2076 */                     t[0] = rawDataHourStart.size();
/* 2077 */                     while (rs.next())
/*      */                     {
/* 2079 */                       temp = new ArrayList();
/* 2080 */                       temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 2081 */                       temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 2082 */                       rawDataHourStart.add(temp);
/*      */                     }
/*      */                     
/* 2085 */                     for (i = t[0]; i < rawDataHourStart.size(); i++)
/*      */                     {
/* 2087 */                       execute = "SELECT * FROM Minute where idHour = " + ((Integer)((List)rawDataHourStart.get(i)).get(1)).intValue();
/* 2088 */                       if (generated)
/*      */                       {
/* 2090 */                         execute = execute + " and not Created";
/*      */                       }
/* 2092 */                       execute = execute + " order by timeMinute";
/* 2093 */                       rs = stmt.executeQuery(execute);
/* 2094 */                       while (rs.next())
/*      */                       {
/* 2096 */                         temp = new ArrayList();
/* 2097 */                         temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 2098 */                         temp.add(Double.valueOf(rs.getDouble("temp")));
/* 2099 */                         temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 2100 */                         temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 2101 */                         temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 2102 */                         temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 2103 */                         temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 2104 */                         rawDataMinuteStart.add(temp);
/*      */                       }
/*      */                     }
/*      */                   }
/*      */                   
/*      */ 
/*      */ 
/* 2111 */                   execute = "Select idDay,timeDay from Day where idMonth = " + ((Integer)((List)rawDataMonthEnd.get(rawDataMonthEnd.size() - 1)).get(1)).intValue() + " and timeDay = " + endDay;
/* 2112 */                   rs = stmt.executeQuery(execute);
/* 2113 */                   if (rs.next())
/*      */                   {
/* 2115 */                     temp = new ArrayList();
/* 2116 */                     temp.add(Integer.valueOf(rs.getInt("timeDay")));
/* 2117 */                     temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 2118 */                     rawDataDayEnd.add(temp);
/* 2119 */                     t[0] = rawDataHourEnd.size();
/* 2120 */                     execute = "Select idHour,timeHour from hour where idDay = " + ((Integer)((List)rawDataDayEnd.get(rawDataDayEnd.size() - 1)).get(1)).intValue() + " and timeHour < " + endHour + " order by timeHour";
/* 2121 */                     rs = stmt.executeQuery(execute);
/* 2122 */                     while (rs.next())
/*      */                     {
/* 2124 */                       temp = new ArrayList();
/* 2125 */                       temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 2126 */                       temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 2127 */                       rawDataHourEnd.add(temp);
/*      */                     }
/* 2129 */                     for (i = t[0]; i < rawDataHourEnd.size(); i++)
/*      */                     {
/* 2131 */                       execute = "select * from minute where idHour = " + ((Integer)((List)rawDataHourEnd.get(i)).get(1)).intValue();
/* 2132 */                       if (generated)
/*      */                       {
/* 2134 */                         execute = execute + " and not Created";
/*      */                       }
/* 2136 */                       execute = execute + " order by timeMinute";
/* 2137 */                       rs = stmt.executeQuery(execute);
/* 2138 */                       while (rs.next()) {
/* 2139 */                         temp = new ArrayList();
/* 2140 */                         temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 2141 */                         temp.add(Double.valueOf(rs.getDouble("temp")));
/* 2142 */                         temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 2143 */                         temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 2144 */                         temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 2145 */                         temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 2146 */                         temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 2147 */                         rawDataMinuteEnd.add(temp);
/*      */                       }
/*      */                     }
/* 2150 */                     execute = "select idHour, timeHour from hour where idDay = " + ((Integer)((List)rawDataDayEnd.get(rawDataDayEnd.size() - 1)).get(1)).intValue() + " and timeHour = " + endHour + " order by timeHour";
/* 2151 */                     rs = stmt.executeQuery(execute);
/* 2152 */                     if (rs.next())
/*      */                     {
/* 2154 */                       temp = new ArrayList();
/* 2155 */                       temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 2156 */                       temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 2157 */                       rawDataHourEnd.add(temp);
/* 2158 */                       execute = "select * from minute where idHour = " + ((Integer)((List)rawDataHourEnd.get(rawDataHourEnd.size() - 1)).get(1)).intValue() + " and timeMinute < " + endMinute;
/* 2159 */                       if (generated)
/*      */                       {
/* 2161 */                         execute = execute + " and not Created";
/*      */                       }
/* 2163 */                       execute = execute + " order by timeMinute";
/* 2164 */                       rs = stmt.executeQuery(execute);
/* 2165 */                       while (rs.next())
/*      */                       {
/* 2167 */                         temp = new ArrayList();
/* 2168 */                         temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 2169 */                         temp.add(Double.valueOf(rs.getDouble("temp")));
/* 2170 */                         temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 2171 */                         temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 2172 */                         temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 2173 */                         temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 2174 */                         temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 2175 */                         rawDataMinuteEnd.add(temp);
/*      */                       }
/*      */                       
/*      */                     }
/*      */                   }
/*      */                 }
/* 2181 */                 else if (keyLength == 0)
/*      */                 {
/* 2183 */                   execute = "SELECT timeDay, idDay From Day where timeDay = " + startDay + " and idMonth = " + ((Integer)((List)rawDataMonth.get(0)).get(1)).intValue();
/* 2184 */                   rs = stmt.executeQuery(execute);
/* 2185 */                   if (rs.next())
/*      */                   {
/* 2187 */                     temp = new ArrayList();
/* 2188 */                     temp.add(Integer.valueOf(rs.getInt("timeDay")));
/* 2189 */                     temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 2190 */                     rawDataDay.add(temp);
/* 2191 */                     rawDataDayStart.add(temp);
/* 2192 */                     rawDataDayEnd.add(temp);
/* 2193 */                     keyLength = endHour - startHour;
/* 2194 */                     if (keyLength > 0)
/*      */                     {
/*      */ 
/*      */ 
/*      */ 
/* 2199 */                       execute = "select timeHour,idHour from Hour where idDay = " + ((Integer)((List)rawDataDay.get(0)).get(1)).intValue() + " and timeHour < " + endHour + " and timeHour > " + startHour + " order by timeHour";
/* 2200 */                       rs = stmt.executeQuery(execute);
/* 2201 */                       while (rs.next()) {
/* 2202 */                         temp = new ArrayList();
/* 2203 */                         temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 2204 */                         temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 2205 */                         rawDataHour.add(temp);
/*      */                       }
/*      */                       
/* 2208 */                       for (int i = 0; i < rawDataHour.size(); i++)
/*      */                       {
/* 2210 */                         execute = "SELECT * from Minute where idHour = " + ((Integer)((List)rawDataHour.get(i)).get(1)).intValue();
/* 2211 */                         if (generated)
/*      */                         {
/* 2213 */                           execute = execute + " and not Created";
/*      */                         }
/* 2215 */                         execute = execute + " order by timeMinute";
/* 2216 */                         rs = stmt.executeQuery(execute);
/* 2217 */                         while (rs.next()) {
/* 2218 */                           temp = new ArrayList();
/* 2219 */                           temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 2220 */                           temp.add(Double.valueOf(rs.getDouble("temp")));
/* 2221 */                           temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 2222 */                           temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 2223 */                           temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 2224 */                           temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 2225 */                           temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 2226 */                           rawDataMinute.add(temp);
/*      */                         }
/*      */                       }
/*      */                       
/*      */ 
/*      */ 
/* 2232 */                       execute = "SELECT timeHour,idHour from Hour where timeHour = " + startHour + " and idDay = " + ((Integer)((List)rawDataDayStart.get(0)).get(1)).intValue();
/* 2233 */                       rs = stmt.executeQuery(execute);
/* 2234 */                       if (rs.next())
/*      */                       {
/* 2236 */                         temp = new ArrayList();
/* 2237 */                         temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 2238 */                         temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 2239 */                         rawDataHourStart.add(temp);
/*      */                         
/* 2241 */                         execute = "SELECT * from Minute where timeMinute > " + startMinute + " and idHour = " + ((Integer)((List)rawDataHourStart.get(0)).get(1)).intValue();
/* 2242 */                         if (generated)
/*      */                         {
/* 2244 */                           execute = execute + " and not Created";
/*      */                         }
/* 2246 */                         execute = execute + " order by timeMinute";
/* 2247 */                         rs = stmt.executeQuery(execute);
/* 2248 */                         while (rs.next())
/*      */                         {
/* 2250 */                           temp = new ArrayList();
/* 2251 */                           temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 2252 */                           temp.add(Double.valueOf(rs.getDouble("temp")));
/* 2253 */                           temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 2254 */                           temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 2255 */                           temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 2256 */                           temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 2257 */                           temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 2258 */                           rawDataMinuteStart.add(temp);
/*      */                         }
/*      */                       }
/*      */                       
/*      */ 
/*      */ 
/* 2264 */                       execute = "Select idHour,timeHour from hour where idDay = " + ((Integer)((List)rawDataDayEnd.get(rawDataMonthEnd.size() - 1)).get(1)).intValue() + " and timeHour = " + endHour;
/* 2265 */                       rs = stmt.executeQuery(execute);
/* 2266 */                       if (rs.next())
/*      */                       {
/* 2268 */                         temp = new ArrayList();
/* 2269 */                         temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 2270 */                         temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 2271 */                         rawDataHourEnd.add(temp);
/* 2272 */                         execute = "select * from minute where idHour = " + ((Integer)((List)rawDataHourEnd.get(rawDataHourEnd.size() - 1)).get(1)).intValue() + " and timeMinute < " + endMinute;
/* 2273 */                         if (generated)
/*      */                         {
/* 2275 */                           execute = execute + " and not Created";
/*      */                         }
/* 2277 */                         execute = execute + " order by timeMinute";
/* 2278 */                         rs = stmt.executeQuery(execute);
/* 2279 */                         while (rs.next())
/*      */                         {
/* 2281 */                           temp = new ArrayList();
/* 2282 */                           temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 2283 */                           temp.add(Double.valueOf(rs.getDouble("temp")));
/* 2284 */                           temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 2285 */                           temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 2286 */                           temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 2287 */                           temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 2288 */                           temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 2289 */                           rawDataMinuteEnd.add(temp);
/*      */                         }
/*      */                       }
/*      */                     }
/*      */                     else {
/* 2294 */                       if (keyLength == 0)
/*      */                       {
/* 2296 */                         execute = "SELECT timeHour, idHour from Hour where timeHour = " + startHour + " and idDay = " + ((Integer)((List)rawDataDay.get(0)).get(1)).intValue();
/* 2297 */                         rs = stmt.executeQuery(execute);
/* 2298 */                         if (rs.next())
/*      */                         {
/* 2300 */                           temp = new ArrayList();
/* 2301 */                           temp.add(Integer.valueOf(rs.getInt("timeHour")));
/* 2302 */                           temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 2303 */                           rawDataHourStart.add(temp);
/* 2304 */                           keyLength = endMinute - startMinute;
/* 2305 */                           if (keyLength > 0)
/*      */                           {
/* 2307 */                             execute = "SELECT * from Minute where timeMinute > " + startMinute + " and timeMinute < " + endMinute + " and idHour = " + ((Integer)((List)rawDataHourStart.get(0)).get(1)).intValue();
/* 2308 */                             if (generated)
/*      */                             {
/* 2310 */                               execute = execute + " and not Created";
/*      */                             }
/* 2312 */                             rs = stmt.executeQuery(execute);
/* 2313 */                             while (rs.next())
/*      */                             {
/* 2315 */                               temp = new ArrayList();
/* 2316 */                               temp.add(Integer.valueOf(rs.getInt("timeMinute")));
/* 2317 */                               temp.add(Double.valueOf(rs.getDouble("temp")));
/* 2318 */                               temp.add(Integer.valueOf(rs.getInt("idMinute")));
/* 2319 */                               temp.add(Integer.valueOf(rs.getInt("idHour")));
/* 2320 */                               temp.add(Integer.valueOf(rs.getInt("idDay")));
/* 2321 */                               temp.add(Integer.valueOf(rs.getInt("idMonth")));
/* 2322 */                               temp.add(Integer.valueOf(rs.getInt("idYear")));
/* 2323 */                               rawDataMinuteStart.add(temp);
/*      */                             }
/*      */                           }
/*      */                           
/*      */ 
/* 2328 */                           stmt.close();
/* 2329 */                           conn.close();
/* 2330 */                           throw new UserError("Date format Exception", "end date must be before start date");
/*      */                         }
/*      */                         
/*      */ 
/*      */ 
/* 2335 */                         stmt.close();
/* 2336 */                         conn.close();
/* 2337 */                         Log.setInformed(true);
/* 2338 */                         Log.addMinorError(new SQLException("no data found"));
/* 2339 */                         throw new SQLException("no data found");
/*      */                       }
/*      */                       
/*      */ 
/*      */ 
/* 2344 */                       stmt.close();
/* 2345 */                       conn.close();
/* 2346 */                       throw new UserError("Date format Exception", "end date must be before start date");
/*      */                     }
/*      */                   }
/*      */                   else
/*      */                   {
/* 2351 */                     stmt.close();
/* 2352 */                     conn.close();
/* 2353 */                     Log.setInformed(true);
/* 2354 */                     Log.addMinorError(new SQLException("no data found"));
/* 2355 */                     throw new SQLException("no data found");
/*      */                   }
/*      */                 }
/*      */                 else
/*      */                 {
/* 2360 */                   stmt.close();
/* 2361 */                   conn.close();
/* 2362 */                   throw new UserError("Date format Exception", "end date must be before start date");
/*      */                 }
/*      */               }
/*      */               else
/*      */               {
/* 2367 */                 stmt.close();
/* 2368 */                 conn.close();
/* 2369 */                 Log.setInformed(true);
/* 2370 */                 Log.addMinorError(new SQLException("no data found"));
/* 2371 */                 throw new SQLException("no data found");
/*      */               }
/*      */             }
/*      */             else
/*      */             {
/* 2376 */               stmt.close();
/* 2377 */               conn.close();
/* 2378 */               throw new UserError("Date format Exception", "end date must be before start date");
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/* 2383 */             stmt.close();
/* 2384 */             conn.close();
/* 2385 */             Log.setInformed(true);
/* 2386 */             Log.addMinorError(new SQLException("no data found"));
/* 2387 */             throw new SQLException("no data found");
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/* 2392 */           stmt.close();
/* 2393 */           conn.close();
/* 2394 */           throw new UserError("Date format Exception", "end date must be before start date");
/*      */         }
/*      */       }
/*      */       catch (Throwable localThrowable1)
/*      */       {
/* 1159 */         localThrowable4 = localThrowable1;throw localThrowable1; } finally {} } catch (Throwable localThrowable2) { localThrowable3 = localThrowable2;throw localThrowable2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*      */     finally
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2396 */       if (conn != null) if (localThrowable3 != null) try { conn.close(); } catch (Throwable x2) { localThrowable3.addSuppressed(x2); } else conn.close(); }
/* 2397 */     if ((rawDataMinute.isEmpty()) && (rawDataMinuteEnd.isEmpty()) && (rawDataMinuteStart.isEmpty()))
/*      */     {
/* 2399 */       throw new SQLException("no data found");
/*      */     }
/*      */     
/* 2402 */     ret.add(rawDataYear);
/* 2403 */     ret.add(rawDataYearStart);
/* 2404 */     ret.add(rawDataYearEnd);
/* 2405 */     ret.add(rawDataMonth);
/* 2406 */     ret.add(rawDataMonthStart);
/* 2407 */     ret.add(rawDataMonthEnd);
/* 2408 */     ret.add(rawDataDay);
/* 2409 */     ret.add(rawDataDayStart);
/* 2410 */     ret.add(rawDataDayEnd);
/* 2411 */     ret.add(rawDataHour);
/* 2412 */     ret.add(rawDataHourStart);
/* 2413 */     ret.add(rawDataHourEnd);
/* 2414 */     ret.add(rawDataMinute);
/* 2415 */     ret.add(rawDataMinuteStart);
/* 2416 */     ret.add(rawDataMinuteEnd);
/* 2417 */     return ret;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object[][] searchDataSet(int startYear, int startMonth, int startDay, int startHour, int startMinute, int endYear, int endMonth, int endDay, int endHour, int endMinute, boolean generated)
/*      */     throws SQLException, NumberFormatException, UserError
/*      */   {
/* 2425 */     int j = 0;
/* 2426 */     List<List<List<Object>>> grab = collectData(startYear, startMonth, startDay, startHour, startMinute, endYear, endMonth, endDay, endHour, endMinute, generated);
/*      */     
/* 2428 */     List<List<Object>> rawDataYear = (List)grab.get(0);
/* 2429 */     List<List<Object>> rawDataYearStart = (List)grab.get(1);
/* 2430 */     List<List<Object>> rawDataYearEnd = (List)grab.get(2);
/* 2431 */     List<List<Object>> rawDataMonth = (List)grab.get(3);
/* 2432 */     List<List<Object>> rawDataMonthStart = (List)grab.get(4);
/* 2433 */     List<List<Object>> rawDataMonthEnd = (List)grab.get(5);
/* 2434 */     List<List<Object>> rawDataDay = (List)grab.get(6);
/* 2435 */     List<List<Object>> rawDataDayStart = (List)grab.get(7);
/* 2436 */     List<List<Object>> rawDataDayEnd = (List)grab.get(8);
/* 2437 */     List<List<Object>> rawDataHour = (List)grab.get(9);
/* 2438 */     List<List<Object>> rawDataHourStart = (List)grab.get(10);
/* 2439 */     List<List<Object>> rawDataHourEnd = (List)grab.get(11);
/* 2440 */     List<List<Object>> rawDataMinute = (List)grab.get(12);
/* 2441 */     List<List<Object>> rawDataMinuteStart = (List)grab.get(13);
/* 2442 */     List<List<Object>> rawDataMinuteEnd = (List)grab.get(14);
/* 2443 */     Object[][] hold = new Object[rawDataMinute.size() + rawDataMinuteStart.size() + rawDataMinuteEnd.size()][6];
/* 2444 */     Object[][] ret = new Object[rawDataMinute.size() + rawDataMinuteStart.size() + rawDataMinuteEnd.size()][3];
/* 2445 */     for (int i = 0; i < rawDataMinuteStart.size(); i++)
/*      */     {
/* 2447 */       hold[j][0] = ((List)rawDataMinuteStart.get(i)).get(1);
/* 2448 */       hold[j][1] = ((List)rawDataMinuteStart.get(i)).get(0);
/* 2449 */       for (int h = 0; h < rawDataHourStart.size(); h++)
/*      */       {
/* 2451 */         if (((Integer)((List)rawDataMinuteStart.get(i)).get(3)).intValue() == ((Integer)((List)rawDataHourStart.get(h)).get(1)).intValue())
/*      */         {
/* 2453 */           hold[j][2] = ((List)rawDataHourStart.get(h)).get(0);
/* 2454 */           break;
/*      */         }
/*      */       }
/* 2457 */       for (h = 0; h < rawDataDayStart.size(); h++)
/*      */       {
/* 2459 */         if (((Integer)((List)rawDataMinuteStart.get(i)).get(4)).intValue() == ((Integer)((List)rawDataDayStart.get(h)).get(1)).intValue())
/*      */         {
/* 2461 */           hold[j][3] = ((List)rawDataDayStart.get(h)).get(0);
/* 2462 */           break;
/*      */         }
/*      */       }
/* 2465 */       for (h = 0; h < rawDataMonthStart.size(); h++)
/*      */       {
/* 2467 */         if (((Integer)((List)rawDataMinuteStart.get(i)).get(5)).intValue() == ((Integer)((List)rawDataMonthStart.get(h)).get(1)).intValue())
/*      */         {
/* 2469 */           hold[j][4] = ((List)rawDataMonthStart.get(h)).get(0);
/* 2470 */           break;
/*      */         }
/*      */       }
/* 2473 */       for (h = 0; h < rawDataYearStart.size(); h++)
/*      */       {
/* 2475 */         if (((Integer)((List)rawDataMinuteStart.get(i)).get(6)).intValue() == ((Integer)((List)rawDataYearStart.get(h)).get(1)).intValue())
/*      */         {
/* 2477 */           hold[j][5] = ((List)rawDataYearStart.get(h)).get(0);
/* 2478 */           break;
/*      */         }
/*      */       }
/* 2481 */       ret[j][0] = ("" + ((Integer)hold[j][4]).intValue() + "/" + ((Integer)hold[j][3]).intValue() + "/" + ((Integer)hold[j][5]).intValue());
/* 2482 */       ret[j][1] = ("" + ((Integer)hold[j][2]).intValue() + ":" + ((Integer)hold[j][1]).intValue());
/* 2483 */       ret[j][2] = ("" + ((Double)hold[j][0]).doubleValue());
/* 2484 */       j++;
/*      */     }
/* 2486 */     for (i = 0; i < rawDataMinute.size(); i++)
/*      */     {
/* 2488 */       hold[j][0] = ((List)rawDataMinute.get(i)).get(1);
/* 2489 */       hold[j][1] = ((List)rawDataMinute.get(i)).get(0);
/* 2490 */       for (int h = 0; h < rawDataHour.size(); h++)
/*      */       {
/* 2492 */         if (((Integer)((List)rawDataMinute.get(i)).get(3)).intValue() == ((Integer)((List)rawDataHour.get(h)).get(1)).intValue())
/*      */         {
/* 2494 */           hold[j][2] = ((List)rawDataHour.get(h)).get(0);
/* 2495 */           break;
/*      */         }
/*      */       }
/* 2498 */       for (h = 0; h < rawDataDay.size(); h++)
/*      */       {
/* 2500 */         if (((Integer)((List)rawDataMinute.get(i)).get(4)).intValue() == ((Integer)((List)rawDataDay.get(h)).get(1)).intValue())
/*      */         {
/* 2502 */           hold[j][3] = ((List)rawDataDay.get(h)).get(0);
/* 2503 */           break;
/*      */         }
/*      */       }
/* 2506 */       for (h = 0; h < rawDataMonth.size(); h++)
/*      */       {
/* 2508 */         if (((Integer)((List)rawDataMinute.get(i)).get(5)).intValue() == ((Integer)((List)rawDataMonth.get(h)).get(1)).intValue())
/*      */         {
/* 2510 */           hold[j][4] = ((List)rawDataMonth.get(h)).get(0);
/* 2511 */           break;
/*      */         }
/*      */       }
/* 2514 */       for (h = 0; h < rawDataYear.size(); h++)
/*      */       {
/* 2516 */         if (((Integer)((List)rawDataMinute.get(i)).get(6)).intValue() == ((Integer)((List)rawDataYear.get(h)).get(1)).intValue())
/*      */         {
/* 2518 */           hold[j][5] = ((List)rawDataYear.get(h)).get(0);
/* 2519 */           break;
/*      */         }
/*      */       }
/* 2522 */       ret[j][0] = ("" + ((Integer)hold[j][4]).intValue() + "/" + ((Integer)hold[j][3]).intValue() + "/" + ((Integer)hold[j][5]).intValue());
/* 2523 */       ret[j][1] = ("" + ((Integer)hold[j][2]).intValue() + ":" + ((Integer)hold[j][1]).intValue());
/* 2524 */       ret[j][2] = ("" + ((Double)hold[j][0]).doubleValue());
/* 2525 */       j++;
/*      */     }
/* 2527 */     for (i = 0; i < rawDataMinuteEnd.size(); i++)
/*      */     {
/* 2529 */       hold[j][0] = ((List)rawDataMinuteEnd.get(i)).get(1);
/* 2530 */       hold[j][1] = ((List)rawDataMinuteEnd.get(i)).get(0);
/* 2531 */       for (int h = 0; h < rawDataHourEnd.size(); h++) {
/* 2532 */         if (((Integer)((List)rawDataMinuteEnd.get(i)).get(3)).intValue() == ((Integer)((List)rawDataHourEnd.get(h)).get(1)).intValue())
/*      */         {
/* 2534 */           hold[j][2] = ((List)rawDataHourEnd.get(h)).get(0);
/* 2535 */           break;
/*      */         }
/*      */       }
/* 2538 */       for (h = 0; h < rawDataDayEnd.size(); h++)
/*      */       {
/* 2540 */         if (((Integer)((List)rawDataMinuteEnd.get(i)).get(4)).intValue() == ((Integer)((List)rawDataDayEnd.get(h)).get(1)).intValue())
/*      */         {
/* 2542 */           hold[j][3] = ((List)rawDataDayEnd.get(h)).get(0);
/* 2543 */           break;
/*      */         }
/*      */       }
/* 2546 */       for (h = 0; h < rawDataMonthEnd.size(); h++)
/*      */       {
/* 2548 */         if (((Integer)((List)rawDataMinuteEnd.get(i)).get(5)).intValue() == ((Integer)((List)rawDataMonthEnd.get(h)).get(1)).intValue())
/*      */         {
/* 2550 */           hold[j][4] = ((List)rawDataMonthEnd.get(h)).get(0);
/* 2551 */           break;
/*      */         }
/*      */       }
/* 2554 */       for (h = 0; h < rawDataYearEnd.size(); h++)
/*      */       {
/* 2556 */         if (((Integer)((List)rawDataMinuteEnd.get(i)).get(6)).intValue() == ((Integer)((List)rawDataYearEnd.get(h)).get(1)).intValue())
/*      */         {
/* 2558 */           hold[j][5] = ((List)rawDataYearEnd.get(h)).get(0);
/* 2559 */           break;
/*      */         }
/*      */       }
/* 2562 */       ret[j][0] = ("" + ((Integer)hold[j][4]).intValue() + "/" + ((Integer)hold[j][3]).intValue() + "/" + ((Integer)hold[j][5]).intValue());
/* 2563 */       ret[j][1] = ("" + ((Integer)hold[j][2]).intValue() + ":" + ((Integer)hold[j][1]).intValue());
/* 2564 */       ret[j][2] = ("" + ((Double)hold[j][0]).doubleValue());
/* 2565 */       j++;
/*      */     }
/* 2567 */     return ret;
/*      */   }
/*      */ }


/* Location:              C:\Users\James\Desktop\jd-gui-windows-1.4.0\ForestTemperature.jar!\foresttemperature\DatabaseConnector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */