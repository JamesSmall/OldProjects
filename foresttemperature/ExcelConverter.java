/*     */ package foresttemperature;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.apache.poi.hssf.usermodel.HSSFCell;
/*     */ import org.apache.poi.hssf.usermodel.HSSFRow;
/*     */ import org.apache.poi.hssf.usermodel.HSSFSheet;
/*     */ import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*     */ import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
/*     */ import org.apache.poi.ss.usermodel.CellStyle;
/*     */ import org.apache.poi.ss.usermodel.CreationHelper;
/*     */ import org.apache.poi.ss.usermodel.DataFormat;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExcelConverter
/*     */ {
/*     */   public void createDoc(Object[][] data, String Name)
/*     */     throws IOException, OfficeXmlFileException, IndexOutOfBoundsException
/*     */   {
/*  31 */     Name = Name.replace("\\", "/");
/*  32 */     FileOutputStream fileOut = new FileOutputStream(Name + ".xls");Throwable localThrowable2 = null;
/*     */     try
/*     */     {
/*  35 */       HSSFWorkbook doc = new HSSFWorkbook();
/*     */       
/*  37 */       HSSFSheet sheet = doc.createSheet(((String)data[0][0]).replace("/", " ") + " to " + ((String)data[(data.length - 1)][0]).replace("/", " "));
/*     */       
/*     */ 
/*  40 */       CreationHelper createHelper = doc.getCreationHelper();
/*  41 */       CellStyle cellStyle = doc.createCellStyle();
/*  42 */       Calendar cal = Calendar.getInstance();
/*  43 */       Date[] date = new Date[data.length];
/*  44 */       String[] SHold = new String[5];
/*     */       
/*  46 */       double[] temp = new double[data.length];
/*     */       
/*     */ 
/*  49 */       cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
/*     */       
/*  51 */       for (int i = 0; i < data.length; i++)
/*     */       {
/*  53 */         int h = 0;
/*  54 */         String HoldString = (String)data[i][0];
/*  55 */         for (int j = 0; j < 5; j++)
/*     */         {
/*  57 */           SHold[j] = "";
/*     */         }
/*  59 */         for (j = 0; j < HoldString.length(); j++)
/*     */         {
/*  61 */           if (HoldString.charAt(j) == '/')
/*     */           {
/*  63 */             h++;
/*  64 */             while (HoldString.charAt(j) == '/')
/*     */             {
/*  66 */               j++;
/*     */             }
/*     */           }
/*  69 */           int tmp271_269 = h; String[] tmp271_267 = SHold;tmp271_267[tmp271_269] = (tmp271_267[tmp271_269] + HoldString.charAt(j));
/*     */         }
/*  71 */         h++;
/*  72 */         HoldString = (String)data[i][1];
/*  73 */         for (j = 0; j < HoldString.length(); j++)
/*     */         {
/*  75 */           if (HoldString.charAt(j) == ':') {
/*  76 */             h++;
/*  77 */             while (HoldString.charAt(j) == ':')
/*     */             {
/*  79 */               j++;
/*     */             }
/*     */           }
/*  82 */           int tmp367_365 = h; String[] tmp367_363 = SHold;tmp367_363[tmp367_365] = (tmp367_363[tmp367_365] + HoldString.charAt(j));
/*     */         }
/*  84 */         int[] hold = new int[5];
/*  85 */         for (j = 0; j < 5; j++) {
/*  86 */           hold[j] = Integer.parseInt(SHold[j]);
/*     */         }
/*  88 */         cal.set(hold[2], hold[0] - 1, hold[1], hold[3], hold[4]);
/*  89 */         date[i] = cal.getTime();
/*  90 */         temp[i] = Double.parseDouble((String)data[i][2]);
/*     */       }
/*     */       
/*  93 */       sheet.setColumnWidth(0, 5000);
/*  94 */       for (i = 0; i < data.length; i++) {
/*  95 */         HSSFRow row = sheet.createRow(i);
/*  96 */         HSSFCell cell = row.createCell(0);
/*  97 */         cell.setCellStyle(cellStyle);
/*  98 */         cell.setCellValue(date[i]);
/*  99 */         cell = row.createCell(1);
/* 100 */         cell.setCellValue(temp[i]);
/*     */       }
/* 102 */       doc.write(fileOut);
/* 103 */       fileOut.flush();
/*     */     }
/*     */     catch (Throwable localThrowable1)
/*     */     {
/*  32 */       localThrowable2 = localThrowable1;throw localThrowable1;
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
/* 104 */       if (fileOut != null) if (localThrowable2 != null) try { fileOut.close(); } catch (Throwable x2) { localThrowable2.addSuppressed(x2); } else fileOut.close();
/*     */     }
/*     */   }
/*     */   
/*     */   public Object[][] getDoc(File f)
/*     */     throws IOException, OfficeXmlFileException
/*     */   {
/* 111 */     int rowMax = 0;
/* 112 */     List<int[]> date = new ArrayList();
/* 113 */     List<Double> temp = new ArrayList();
/*     */     
/*     */ 
/* 116 */     FileInputStream input = new FileInputStream(f);
/* 117 */     HSSFWorkbook doc = new HSSFWorkbook(input);
/* 118 */     HSSFSheet sheet = doc.getSheetAt(0);
/*     */     
/* 120 */     Calendar cal = Calendar.getInstance();
/*     */     
/* 122 */     rowMax = sheet.getLastRowNum();
/*     */     
/* 124 */     for (int i = 0; i < rowMax; i++)
/*     */     {
/*     */       try
/*     */       {
/* 128 */         HSSFRow row = sheet.getRow(i);
/* 129 */         if ((row.getPhysicalNumberOfCells() > 1) && ((row.getCell(0).getCellType() == 2) || (row.getCell(0).getCellType() == 0)) && (row.getCell(1).getCellType() == 0))
/*     */         {
/* 131 */           Date dateHold = row.getCell(0).getDateCellValue();
/* 132 */           double tempHold = row.getCell(1).getNumericCellValue();
/* 133 */           int[] timeHold = new int[5];
/* 134 */           cal.setTime(dateHold);
/* 135 */           timeHold[0] = cal.get(1);
/* 136 */           timeHold[1] = (cal.get(2) + 1);
/* 137 */           timeHold[2] = cal.get(5);
/* 138 */           timeHold[3] = cal.get(11);
/* 139 */           timeHold[4] = cal.get(12);
/* 140 */           date.add(timeHold);
/* 141 */           temp.add(Double.valueOf(tempHold));
/*     */         }
/*     */       }
/*     */       catch (NullPointerException|ArrayIndexOutOfBoundsException ex)
/*     */       {
/* 146 */         Log.addNonSeriousError(ex);
/*     */       }
/*     */       catch (Exception ex)
/*     */       {
/* 150 */         Log.addUnknownError(ex);
/*     */       }
/*     */     }
/*     */     
/* 154 */     Object[][] ret = new Object[6][date.size()];
/* 155 */     for (i = 0; i < date.size(); i++)
/*     */     {
/* 157 */       ret[0][i] = Integer.valueOf(((int[])date.get(i))[0]);
/* 158 */       ret[1][i] = Integer.valueOf(((int[])date.get(i))[1]);
/* 159 */       ret[2][i] = Integer.valueOf(((int[])date.get(i))[2]);
/* 160 */       ret[3][i] = Integer.valueOf(((int[])date.get(i))[3]);
/* 161 */       ret[4][i] = Integer.valueOf(((int[])date.get(i))[4]);
/* 162 */       ret[5][i] = temp.get(i);
/*     */     }
/* 164 */     return ret;
/*     */   }
/*     */ }


/* Location:              C:\Users\James\Desktop\jd-gui-windows-1.4.0\ForestTemperature.jar!\foresttemperature\ExcelConverter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */