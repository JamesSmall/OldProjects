/*     */ package foresttemperature;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.ConnectException;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KOJCConnector
/*     */ {
/*     */   private static final String URLADDRESS = "http://www.wunderground.com/history/airport/KOJC::DailyHistory.html?format=1";
/*     */   
/*     */   public KOJCConnector()
/*     */   {
/*  35 */     HttpURLConnection.setFollowRedirects(false);
/*     */   }
/*     */   
/*     */ 
/*     */   public int getEndDay(int year, int month)
/*     */   {
/*  41 */     Calendar cal = Calendar.getInstance();
/*  42 */     cal.clear();
/*  43 */     cal.set(year, month, 5);
/*  44 */     return cal.getActualMaximum(5);
/*     */   }
/*     */   
/*     */   public Object[][] getData(int startYear, int startMonth, int startDay, int startHour, int startMinute, int endYear, int endMonth, int endDay, int endHour, int endMinute)
/*     */     throws UnknownHostException, IOException
/*     */   {
/*  50 */     List<Object[]> prepData = getUnconfiguredData(startYear, startMonth, startDay, startHour, startMinute, endYear, endMonth, endDay, endHour, endMinute);
/*     */     
/*  52 */     if (prepData.isEmpty())
/*     */     {
/*     */ 
/*  55 */       IOException t = new IOException("data not found");
/*  56 */       Log.setInformed(true);
/*  57 */       Log.addMinorError(t);
/*  58 */       throw t;
/*     */     }
/*  60 */     Object[][] ret = new Object[prepData.size()][6];
/*  61 */     for (int i = 0; i < prepData.size(); i++) {
/*  62 */       ret[i] = ((Object[])prepData.get(i));
/*     */     }
/*  64 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private List<Object[]> getUnconfiguredData(int startYear, int startMonth, int startDay, int startHour, int startMinute, int endYear, int endMonth, int endDay, int endHour, int endMinute)
/*     */     throws UnknownHostException, IOException
/*     */   {
/*  73 */     List<Object[]> data = new ArrayList();
/*     */     
/*     */ 
/*  76 */     if ((startYear == endYear) && (endMonth == startMonth) && (startDay == endDay))
/*     */     {
/*     */       List<Object[]> temp;
/*  79 */       if ((temp = getEndFileData(startYear, startMonth, startDay, startHour, startMinute, endHour, endMinute)) != null)
/*     */       {
/*  81 */         data.addAll(temp);
/*     */       }
/*     */       else
/*     */       {
/*  85 */         IOException t = new IOException("data not found");
/*  86 */         Log.setInformed(true);
/*  87 */         Log.addMinorError(t);
/*  88 */         throw t;
/*     */       }
/*     */       
/*     */     }
/*  92 */     else if ((startYear == endYear) && (endMonth == startMonth))
/*     */     {
/*     */       List<Object[]> temp;
/*  95 */       if ((temp = getEndFileData(startYear, startMonth, startDay, startHour, startMinute, 1000, 1000)) != null)
/*     */       {
/*  97 */         data.addAll(temp);
/*     */       }
/*     */       
/* 100 */       for (int x = startDay + 1; x < endDay + 1; x++) {
/* 101 */         if ((temp = getFileData(startYear, startMonth, x)) != null)
/*     */         {
/* 103 */           data.addAll(temp);
/*     */         }
/*     */       }
/*     */       
/* 107 */       if ((temp = getEndFileData(startYear, startMonth, endDay, startHour, startMinute, endHour, endMinute)) != null)
/*     */       {
/* 109 */         data.addAll(temp);
/*     */       }
/*     */       
/*     */     }
/* 113 */     else if (startYear == endYear)
/*     */     {
/*     */       List<Object[]> temp;
/* 116 */       if ((temp = getEndFileData(startYear, startMonth, startDay, startHour, startMinute, 1000, 1000)) != null)
/*     */       {
/* 118 */         data.addAll(temp);
/*     */       }
/*     */       
/*     */ 
/* 122 */       for (int x = startMonth + 1; x < endMonth + 1; x++)
/*     */       {
/* 124 */         int dayMax = getEndDay(startYear, x) + 1;
/*     */         
/* 126 */         if (x == startMonth)
/*     */         {
/*     */ 
/* 129 */           for (int y = startDay + 1; y < dayMax + 1; y++)
/*     */           {
/* 131 */             if ((temp = getFileData(startYear, x, y)) != null)
/*     */             {
/* 133 */               data.addAll(temp);
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 138 */         if (x != endMonth)
/*     */         {
/*     */ 
/* 141 */           for (int y = 1; y < dayMax; y++)
/*     */           {
/* 143 */             if ((temp = getFileData(startYear, x, y)) != null)
/*     */             {
/* 145 */               data.addAll(temp);
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 150 */         if (x == endMonth)
/*     */         {
/*     */ 
/* 153 */           for (int y = 1; y < endDay + 1; y++)
/*     */           {
/* 155 */             if ((temp = getFileData(startYear, x, y)) != null)
/*     */             {
/* 157 */               data.addAll(temp);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 163 */       if ((temp = getEndFileData(startYear, endMonth, endDay, -1, -1, endHour, endMinute)) != null)
/*     */       {
/* 165 */         data.addAll(temp);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*     */       List<Object[]> temp;
/* 171 */       if ((temp = getEndFileData(startYear, startMonth, startDay, startHour, startMinute, 1000, 1000)) != null)
/*     */       {
/* 173 */         data.addAll(temp);
/*     */       }
/*     */       
/* 176 */       for (int x = startYear; x < endYear + 1; x++)
/*     */       {
/*     */ 
/* 179 */         if (x == startYear)
/*     */         {
/* 181 */           for (int y = startMonth + 1; y < 13; y++)
/*     */           {
/* 183 */             int dayMax = getEndDay(x, y) + 1;
/*     */             
/* 185 */             if (y == startMonth)
/*     */             {
/*     */ 
/* 188 */               for (int z = startDay + 1; z < dayMax + 1; z++)
/*     */               {
/* 190 */                 if ((temp = getFileData(x, y, z)) != null)
/*     */                 {
/* 192 */                   data.addAll(temp);
/*     */                 }
/*     */               }
/*     */             }
/*     */             
/*     */ 
/*     */ 
/* 199 */             for (int z = 1; z < dayMax + 1; z++)
/*     */             {
/* 201 */               if ((temp = getFileData(x, y, z)) != null)
/*     */               {
/* 203 */                 data.addAll(temp);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 210 */         if (x == endYear)
/*     */         {
/*     */ 
/* 213 */           for (int y = 1; y < endMonth + 1; y++)
/*     */           {
/* 215 */             int dayMax = getEndDay(x, y) + 1;
/*     */             
/* 217 */             if (y == endMonth)
/*     */             {
/*     */ 
/* 220 */               for (int z = 1; z < endDay + 1; z++)
/*     */               {
/* 222 */                 if ((temp = getFileData(x, y, z)) != null)
/*     */                 {
/* 224 */                   data.addAll(temp);
/*     */                 }
/*     */               }
/*     */             }
/*     */             
/*     */ 
/*     */ 
/* 231 */             for (int z = 1; z < dayMax + 1; z++)
/*     */             {
/* 233 */               if ((temp = getFileData(x, y, z)) != null)
/*     */               {
/* 235 */                 data.addAll(temp);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 244 */         for (int y = 0; y < 12; y++)
/*     */         {
/* 246 */           int dayMax = getEndDay(x, y) + 1;
/* 247 */           for (int z = 0; z < dayMax; z++)
/*     */           {
/* 249 */             if ((temp = getFileData(x, y, z)) != null)
/*     */             {
/* 251 */               data.addAll(temp);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 258 */       if ((temp = getEndFileData(endYear, endMonth, endDay, -1, -1, endHour, endMinute)) != null) {
/* 259 */         data.addAll(temp);
/*     */       }
/*     */     }
/*     */     
/* 263 */     if (data.isEmpty())
/*     */     {
/* 265 */       IOException t = new IOException("data not found");
/* 266 */       Log.setInformed(true);
/* 267 */       Log.addMinorError(t);
/* 268 */       throw t;
/*     */     }
/* 270 */     return data;
/*     */   }
/*     */   
/*     */ 
/*     */   private List<Object[]> getEndFileData(int year, int month, int day, int minHour, int minMinute, int maxHour, int maxMinute)
/*     */     throws IOException
/*     */   {
/* 277 */     List<Object[]> data = getFileData(year, month, day);
/* 278 */     List<Object[]> ret = new ArrayList();
/*     */     
/* 280 */     if (data == null) {
/* 281 */       return null;
/*     */     }
/*     */     
/* 284 */     for (int i = 0; i < data.size(); i++) {
/* 285 */       Object[] temp = (Object[])data.get(i);
/*     */       
/* 287 */       if (((((Integer)temp[3]).intValue() > minHour) && (maxHour > ((Integer)temp[3]).intValue())) || ((((Integer)temp[3]).intValue() == maxHour) && (((Integer)temp[4]).intValue() < maxMinute)) || ((((Integer)temp[3]).intValue() == minHour) && (((Integer)temp[4]).intValue() > minMinute))) {
/* 288 */         ret.add(temp);
/*     */       }
/*     */     }
/* 291 */     return ret;
/*     */   }
/*     */   
/*     */   private List<Object[]> getFileData(int year, int month, int day) throws IOException
/*     */   {
/* 296 */     URL url = new URL("http://www.wunderground.com/history/airport/KOJC::DailyHistory.html?format=1".replace("::", "/" + year + "/" + month + "/" + day + "/"));
/*     */     
/* 298 */     File f = File.createTempFile("KOJC", "" + year + "S" + month + "S" + day);
/* 299 */     List<Object[]> data = new ArrayList();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 306 */     f.deleteOnExit();
/* 307 */     BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));Throwable localThrowable2 = null;
/*     */     InputStream in;
/*     */     try {
/* 310 */       try { URLConnection conn = url.openConnection();
/* 311 */         in = conn.getInputStream();
/* 312 */         byte[] buffer = new byte['Ѐ'];
/*     */         
/* 314 */         while ((numRead = in.read(buffer)) != -1)
/*     */         {
/* 316 */           out.write(buffer, 0, numRead);
/*     */         }
/* 318 */         out.flush();
/* 319 */         out.close();
/*     */         
/* 321 */         BufferedReader b = new BufferedReader(new FileReader(f));
/*     */         String line;
/* 323 */         while ((line = b.readLine()) != null)
/*     */         {
/*     */ 
/* 326 */           while (line.isEmpty()) {
/* 327 */             line = b.readLine();
/*     */           }
/* 329 */           int firstPoint = 0;
/*     */           
/* 331 */           while (line.charAt(firstPoint) != ':')
/*     */           {
/* 333 */             firstPoint++;
/* 334 */             if (firstPoint == line.length())
/*     */             {
/* 336 */               firstPoint = 0;
/* 337 */               line = b.readLine();
/* 338 */               if (line == null)
/*     */               {
/* 340 */                 return data;
/*     */               }
/*     */             }
/*     */           }
/* 344 */           int secondPoint = firstPoint;
/*     */           
/* 346 */           while (line.charAt(secondPoint) != ' ') {
/* 347 */             secondPoint++;
/*     */           }
/* 349 */           int thirdPoint = secondPoint;
/*     */           
/* 351 */           while (line.charAt(thirdPoint) != ',')
/*     */           {
/* 353 */             thirdPoint++;
/*     */           }
/* 355 */           int forthPoint = thirdPoint + 1;
/*     */           
/* 357 */           while (line.charAt(forthPoint) != ',')
/*     */           {
/* 359 */             forthPoint++;
/*     */           }
/* 361 */           Object[] temp = new Object[6];
/* 362 */           temp[0] = Integer.valueOf(year);
/* 363 */           temp[1] = Integer.valueOf(month);
/* 364 */           temp[2] = Integer.valueOf(day);
/*     */           
/* 366 */           temp[3] = Integer.valueOf(Integer.parseInt(line.substring(0, firstPoint)));
/* 367 */           temp[4] = Integer.valueOf(Integer.parseInt(line.substring(firstPoint + 1, secondPoint)) + 7);
/* 368 */           if (((Integer)temp[4]).intValue() >= 60)
/*     */           {
/* 370 */             temp[4] = Integer.valueOf(((Integer)temp[4]).intValue() - 60);
/* 371 */             temp[3] = Integer.valueOf(((Integer)temp[3]).intValue() + 1);
/*     */           }
/* 373 */           if (((Integer)temp[3]).intValue() > 12) {
/* 374 */             temp[3] = Integer.valueOf(((Integer)temp[3]).intValue() - 12);
/*     */           }
/*     */           
/* 377 */           if (line.substring(secondPoint + 1, thirdPoint).equalsIgnoreCase("PM"))
/*     */           {
/* 379 */             temp[3] = Integer.valueOf(((Integer)temp[3]).intValue() + 11);
/*     */           }
/*     */           else {
/* 382 */             temp[3] = Integer.valueOf(((Integer)temp[3]).intValue() - 1);
/*     */           }
/* 384 */           temp[5] = Double.valueOf((Double.parseDouble(line.substring(thirdPoint + 1, forthPoint)) - 32.0D) / 1.8D);
/* 385 */           data.add(temp);
/*     */         }
/*     */       }
/*     */       catch (ConnectException ex) {
/*     */         int numRead;
/* 390 */         Log.addMinorError(ex);
/* 391 */         return null;
/*     */       }
/*     */     }
/*     */     catch (Throwable localThrowable1)
/*     */     {
/* 307 */       localThrowable2 = localThrowable1;throw localThrowable1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*     */     finally
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 393 */       if (out != null) if (localThrowable2 != null) try { out.close(); } catch (Throwable x2) { localThrowable2.addSuppressed(x2); } else { out.close();
/*     */         }
/*     */     }
/* 396 */     in.close();
/* 397 */     f.delete();
/* 398 */     return data;
/*     */   }
/*     */ }


/* Location:              C:\Users\James\Desktop\jd-gui-windows-1.4.0\ForestTemperature.jar!\foresttemperature\KOJCConnector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */