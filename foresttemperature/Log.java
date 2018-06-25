/*     */ package foresttemperature;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Log
/*     */ {
/*  21 */   private static boolean wasInformed = false;
/*  22 */   private static List<Throwable> nonError = new ArrayList();
/*  23 */   private static List<UserError> userError = new ArrayList();
/*  24 */   private static List<Throwable> minorError = new ArrayList();
/*  25 */   private static List<Throwable> generalError = new ArrayList();
/*  26 */   private static List<Throwable> seriousError = new ArrayList();
/*  27 */   private static List<Throwable> unknownError = new ArrayList();
/*     */   
/*     */   public static boolean isInformed()
/*     */   {
/*  31 */     if (wasInformed)
/*     */     {
/*  33 */       wasInformed = false;
/*  34 */       return true;
/*     */     }
/*  36 */     return false;
/*     */   }
/*     */   
/*     */   public static void setInformed(boolean info)
/*     */   {
/*  41 */     wasInformed = info;
/*     */   }
/*     */   
/*     */   public static void addUserError(UserError e)
/*     */   {
/*  46 */     userError.add(e);
/*     */   }
/*     */   
/*     */   public static void addNonSeriousError(Throwable t)
/*     */   {
/*  51 */     nonError.add(t);
/*     */   }
/*     */   
/*     */   public static void addMinorError(Throwable t)
/*     */   {
/*  56 */     minorError.add(t);
/*     */   }
/*     */   
/*     */   public static void addGeneralError(Throwable t)
/*     */   {
/*  61 */     generalError.add(t);
/*     */   }
/*     */   
/*     */   public static void addSeriousError(Throwable t)
/*     */   {
/*  66 */     seriousError.add(t);
/*     */   }
/*     */   
/*     */   public static void addUnknownError(Throwable t)
/*     */   {
/*  71 */     unknownError.add(t);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void writeAllErrorsToFile()
/*     */     throws IOException
/*     */   {
/*  82 */     if ((generalError.isEmpty()) && (seriousError.isEmpty()) && (minorError.isEmpty()) && (nonError.isEmpty()) && (unknownError.isEmpty()) && (userError.isEmpty()))
/*     */     {
/*  84 */       JOptionPane.showMessageDialog(null, "No Errors Found");
/*  85 */       return;
/*     */     }
/*  87 */     JFileChooser JFC = new JFileChooser("ApproveButtonMnemonicChangedProperty");
/*  88 */     int choose = JFC.showSaveDialog(null);
/*     */     
/*  90 */     if (choose == JFC.getApproveButtonMnemonic())
/*     */     {
/*  92 */       String FileName = JFC.getSelectedFile().getAbsolutePath() + ".txt";
/*  93 */       File f = new File(FileName);
/*  94 */       PrintWriter fw = new PrintWriter(f);
/*  95 */       fw.println("[UnknownError]");
/*  96 */       fw.println("These errors were not caught by normal means, these could be fatal or minor");
/*  97 */       for (int i = 0; i < unknownError.size(); i++)
/*     */       {
/*  99 */         Throwable t = (Throwable)unknownError.get(i);
/* 100 */         fw.println(t.getMessage());
/* 101 */         t.printStackTrace(fw);
/*     */       }
/* 103 */       fw.println();
/* 104 */       fw.println("[Serious Errors]");
/* 105 */       fw.println("All errors here may not be fatal, a serious error can include a lack of internet connection");
/* 106 */       for (i = 0; i < seriousError.size(); i++)
/*     */       {
/* 108 */         Throwable t = (Throwable)seriousError.get(i);
/* 109 */         fw.println(t.getMessage());
/* 110 */         t.printStackTrace(fw);
/*     */       }
/* 112 */       fw.println();
/* 113 */       fw.println("[General Errors]");
/* 114 */       fw.println("These errors may affect performance or functions, however they are not risking conenctions with database or file system");
/* 115 */       for (i = 0; i < generalError.size(); i++)
/*     */       {
/* 117 */         Throwable t = (Throwable)generalError.get(i);
/* 118 */         fw.println(t.getMessage());
/* 119 */         t.printStackTrace(fw);
/*     */       }
/* 121 */       fw.println();
/* 122 */       fw.println("[Minor Errors]");
/* 123 */       fw.println("Errors that do not affect the main system but do affect performance slightly.");
/* 124 */       for (i = 0; i < minorError.size(); i++)
/*     */       {
/* 126 */         Throwable t = (Throwable)minorError.get(i);
/* 127 */         fw.println(t.getMessage());
/* 128 */         t.printStackTrace(fw);
/*     */       }
/* 130 */       fw.println();
/* 131 */       fw.println("[non error]");
/* 132 */       fw.println("this error was an expected error ignored and/or dismissed.");
/* 133 */       for (i = 0; i < nonError.size(); i++)
/*     */       {
/* 135 */         Throwable t = (Throwable)nonError.get(i);
/* 136 */         fw.println(t.getMessage());
/* 137 */         t.printStackTrace(fw);
/*     */       }
/* 139 */       fw.println();
/* 140 */       fw.println("[User Errors]");
/* 141 */       fw.println("These errors are Directly caused by the user");
/* 142 */       for (i = 0; i < userError.size(); i++)
/*     */       {
/* 144 */         Throwable t = (Throwable)userError.get(i);
/* 145 */         fw.println(t.getMessage());
/* 146 */         t.printStackTrace(fw);
/*     */       }
/* 148 */       fw.println();
/* 149 */       fw.flush();
/* 150 */       fw.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void writeSeriousErrorsToFile()
/*     */     throws IOException
/*     */   {
/* 163 */     if (seriousError.isEmpty())
/*     */     {
/* 165 */       JOptionPane.showMessageDialog(null, "No Errors Found");
/* 166 */       return;
/*     */     }
/* 168 */     JFileChooser JFC = new JFileChooser("ApproveButtonMnemonicChangedProperty");
/* 169 */     int choose = JFC.showSaveDialog(null);
/*     */     
/* 171 */     if (choose == JFC.getApproveButtonMnemonic())
/*     */     {
/* 173 */       String FileName = JFC.getSelectedFile().getAbsolutePath() + ".txt";
/* 174 */       File f = new File(FileName);
/* 175 */       PrintWriter fw = new PrintWriter(f);
/* 176 */       fw.println("[Serious Errors]");
/* 177 */       fw.println("all errors here may not be fatal, a serious error can include a lack of internet connection");
/* 178 */       for (int i = 0; i < seriousError.size(); i++)
/*     */       {
/* 180 */         Throwable t = (Throwable)seriousError.get(i);
/* 181 */         fw.println(t.getMessage());
/* 182 */         t.printStackTrace(fw);
/*     */       }
/* 184 */       fw.flush();
/* 185 */       fw.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void writeUnknownErrorFile()
/*     */     throws IOException
/*     */   {
/* 197 */     if (unknownError.isEmpty())
/*     */     {
/* 199 */       JOptionPane.showMessageDialog(null, "No errors found");
/* 200 */       return;
/*     */     }
/* 202 */     JFileChooser JFC = new JFileChooser("ApproveButtonMnemonicChangedProperty");
/* 203 */     int choose = JFC.showSaveDialog(null);
/*     */     
/* 205 */     if (choose == JFC.getApproveButtonMnemonic())
/*     */     {
/* 207 */       String FileName = JFC.getSelectedFile().getAbsolutePath() + ".txt";
/* 208 */       File f = new File(FileName);
/* 209 */       PrintWriter fw = new PrintWriter(f);
/* 210 */       fw.println("[UnknownError]");
/* 211 */       fw.println("These errors were not caught by normal means, these could be fatal or minor");
/* 212 */       for (int i = 0; i < unknownError.size(); i++)
/*     */       {
/* 214 */         Throwable t = (Throwable)unknownError.get(i);
/* 215 */         fw.println(t.getMessage());
/* 216 */         t.printStackTrace(fw);
/*     */       }
/* 218 */       fw.flush();
/* 219 */       fw.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void writeGeneralErrorToFile()
/*     */     throws IOException
/*     */   {
/* 231 */     if (generalError.isEmpty())
/*     */     {
/* 233 */       JOptionPane.showMessageDialog(null, "No errors found");
/* 234 */       return;
/*     */     }
/* 236 */     JFileChooser JFC = new JFileChooser("ApproveButtonMnemonicChangedProperty");
/* 237 */     int choose = JFC.showSaveDialog(null);
/*     */     
/* 239 */     if (choose == JFC.getApproveButtonMnemonic())
/*     */     {
/* 241 */       String FileName = JFC.getSelectedFile().getAbsolutePath() + ".txt";
/* 242 */       File f = new File(FileName);
/* 243 */       PrintWriter fw = new PrintWriter(f);
/* 244 */       fw.println("[General Errors]");
/* 245 */       fw.println("These errors may affect performance or functions, however they are not risking conenctions with database or file system");
/* 246 */       for (int i = 0; i < generalError.size(); i++)
/*     */       {
/* 248 */         Throwable t = (Throwable)generalError.get(i);
/* 249 */         fw.println(t.getMessage());
/* 250 */         t.printStackTrace(fw);
/*     */       }
/* 252 */       fw.flush();
/* 253 */       fw.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void writeMinorErrorsToFile()
/*     */     throws IOException
/*     */   {
/* 265 */     if (minorError.isEmpty())
/*     */     {
/* 267 */       JOptionPane.showMessageDialog(null, "No errors found");
/* 268 */       return;
/*     */     }
/* 270 */     JFileChooser JFC = new JFileChooser("ApproveButtonMnemonicChangedProperty");
/* 271 */     int choose = JFC.showSaveDialog(null);
/*     */     
/* 273 */     if (choose == JFC.getApproveButtonMnemonic())
/*     */     {
/* 275 */       String FileName = JFC.getSelectedFile().getAbsolutePath() + ".txt";
/* 276 */       File f = new File(FileName);
/* 277 */       PrintWriter fw = new PrintWriter(f);
/* 278 */       fw.println("[Minor Errors]");
/* 279 */       fw.println("Errors that do not affect the main system but do affect performance slightly.");
/* 280 */       for (int i = 0; i < minorError.size(); i++)
/*     */       {
/* 282 */         Throwable t = (Throwable)minorError.get(i);
/* 283 */         fw.println(t.getMessage());
/* 284 */         t.printStackTrace(fw);
/*     */       }
/* 286 */       fw.flush();
/* 287 */       fw.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void writeNonErrorsToFile()
/*     */     throws IOException
/*     */   {
/* 299 */     if (nonError.isEmpty())
/*     */     {
/* 301 */       JOptionPane.showMessageDialog(null, "No error's found");
/* 302 */       return;
/*     */     }
/* 304 */     JFileChooser JFC = new JFileChooser("ApproveButtonMnemonicChangedProperty");
/* 305 */     int choose = JFC.showSaveDialog(null);
/*     */     
/* 307 */     if (choose == JFC.getApproveButtonMnemonic())
/*     */     {
/* 309 */       String FileName = JFC.getSelectedFile().getAbsolutePath() + ".txt";
/* 310 */       File f = new File(FileName);
/* 311 */       PrintWriter fw = new PrintWriter(f);
/* 312 */       fw.println("[non error]");
/* 313 */       fw.println("this error was an expected error ignored and/or dismissed.");
/* 314 */       for (int i = 0; i < nonError.size(); i++) {
/* 315 */         Throwable t = (Throwable)nonError.get(i);
/* 316 */         fw.println(t.getMessage());
/* 317 */         t.printStackTrace(fw);
/*     */       }
/* 319 */       fw.flush();
/* 320 */       fw.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void writeUserErrorsToFile()
/*     */     throws IOException
/*     */   {
/* 332 */     if (userError.isEmpty())
/*     */     {
/* 334 */       JOptionPane.showMessageDialog(null, "No errors found");
/* 335 */       return;
/*     */     }
/* 337 */     JFileChooser JFC = new JFileChooser("ApproveButtonMnemonicChangedProperty");
/* 338 */     int choose = JFC.showSaveDialog(null);
/*     */     
/* 340 */     if (choose == JFC.getApproveButtonMnemonic())
/*     */     {
/* 342 */       String FileName = JFC.getSelectedFile().getAbsolutePath() + ".txt";
/* 343 */       File f = new File(FileName);
/* 344 */       PrintWriter fw = new PrintWriter(f);
/* 345 */       fw.println("[User Errors]");
/* 346 */       fw.println("These errors are Directly caused by the user");
/* 347 */       for (int i = 0; i < userError.size(); i++) {
/* 348 */         Throwable t = (Throwable)userError.get(i);
/* 349 */         fw.println(t.getMessage());
/* 350 */         t.printStackTrace(fw);
/*     */       }
/* 352 */       fw.flush();
/* 353 */       fw.close();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\James\Desktop\jd-gui-windows-1.4.0\ForestTemperature.jar!\foresttemperature\Log.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */