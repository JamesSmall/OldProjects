/*     */ package foresttemperature;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import javax.swing.JCheckBoxMenuItem;
/*     */ import javax.swing.JColorChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Graph
/*     */   extends JFrame
/*     */ {
/*     */   private static final double MAXTIME = 4.32E8D;
/*     */   private static final double MINTIME = 3600000.0D;
/*  35 */   private boolean showText = true;
/*     */   private int[][] date;
/*     */   private int[][] timeOfDay;
/*     */   private double[] temp;
/*     */   private static final int PAD = 60;
/*  40 */   private double maxValue = -100.0D; private double minValue = 100.0D;
/*  41 */   private double scale; private List<String> drawDate = new ArrayList();
/*  42 */   private List<Point> points = new LinkedList();
/*  43 */   private int LastLocation = 63536;
/*     */   private JMenuBar settingMNU;
/*     */   private JMenu optionsMNUB;
/*     */   private JMenu colorMNUB;
/*     */   private JCheckBoxMenuItem showTextCMNU;
/*  48 */   private JMenuItem ChooseBackGroundColorMNUB; private JMenuItem dotColorMNUB; private JMenuItem linesMNUB; private JMenuItem wordColorMNUB; private Color background = Color.BLACK; private Color dotColor = Color.RED; private Color lines = Color.WHITE; private Color wordColor = Color.RED;
/*     */   private JGraphPanel Graph;
/*     */   
/*     */   public Graph(JTable t) throws NumberFormatException, UserError
/*     */   {
/*  53 */     int size = t.getRowCount();
/*     */     
/*  55 */     this.date = getDateFromOutputTbl(t, size);
/*  56 */     this.timeOfDay = getTimeOfDayFromOutputTBL(t, size);
/*  57 */     this.temp = getTempFromOutputTBL(t, size);
/*  58 */     PointConfigure();
/*     */     
/*     */ 
/*  61 */     this.settingMNU = new JMenuBar();
/*     */     
/*  63 */     this.optionsMNUB = new JMenu("Edit");
/*  64 */     this.optionsMNUB.setIgnoreRepaint(false);
/*  65 */     this.showTextCMNU = new JCheckBoxMenuItem("Show Date & Temperature");
/*  66 */     this.showTextCMNU.setSelected(true);
/*  67 */     this.showTextCMNU.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/*  70 */         Graph.this.showText = Graph.this.showTextCMNU.isSelected();
/*  71 */         Graph.this.repaint();
/*     */       }
/*     */       
/*     */ 
/*  75 */     });
/*  76 */     this.optionsMNUB.add(this.showTextCMNU);
/*     */     
/*     */ 
/*  79 */     this.colorMNUB = new JMenu("Color Chooser");
/*  80 */     this.colorMNUB.setIgnoreRepaint(true);
/*  81 */     this.ChooseBackGroundColorMNUB = new JMenuItem("Choose Background Color");
/*  82 */     this.ChooseBackGroundColorMNUB.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/*  85 */         Graph.this.background = JColorChooser.showDialog(null, "Choose Background Color", Graph.this.background);
/*  86 */         Graph.this.repaint();
/*     */       }
/*  88 */     });
/*  89 */     this.dotColorMNUB = new JMenuItem("choose dot color");
/*  90 */     this.dotColorMNUB.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/*  93 */         Graph.this.dotColor = JColorChooser.showDialog(null, "Choose Background Color", Graph.this.dotColor);
/*  94 */         Graph.this.repaint();
/*     */       }
/*  96 */     });
/*  97 */     this.linesMNUB = new JMenuItem("choose line color");
/*  98 */     this.linesMNUB.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 101 */         Graph.this.lines = JColorChooser.showDialog(null, "Choose Line Color", Graph.this.lines);
/* 102 */         Graph.this.repaint();
/*     */       }
/* 104 */     });
/* 105 */     this.wordColorMNUB = new JMenuItem("Choose Word Color");
/* 106 */     this.wordColorMNUB.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 109 */         Graph.this.wordColor = JColorChooser.showDialog(null, "Choose background Color", Graph.this.wordColor);
/* 110 */         Graph.this.repaint();
/*     */       }
/*     */       
/* 113 */     });
/* 114 */     this.colorMNUB.add(this.ChooseBackGroundColorMNUB);
/* 115 */     this.colorMNUB.add(this.dotColorMNUB);
/* 116 */     this.colorMNUB.add(this.linesMNUB);
/* 117 */     this.colorMNUB.add(this.wordColorMNUB);
/*     */     
/* 119 */     this.settingMNU.add(this.colorMNUB);
/* 120 */     this.settingMNU.add(this.optionsMNUB);
/* 121 */     this.settingMNU.setVisible(true);
/*     */     
/*     */ 
/* 124 */     add(this.settingMNU);
/* 125 */     setJMenuBar(this.settingMNU);
/*     */     
/* 127 */     setLayout(new GridLayout(1, 1));
/*     */     
/*     */ 
/* 130 */     this.Graph = new JGraphPanel(null);
/* 131 */     add(this.Graph);
/*     */     
/* 133 */     setDefaultCloseOperation(2);
/* 134 */     setTitle("Temperature V. Time Graph");
/* 135 */     setSize(1000, 600);
/*     */     
/* 137 */     setVisible(true);
/*     */   }
/*     */   
/*     */   private double[] getTempFromOutputTBL(JTable OutputTBL, int SetSize)
/*     */     throws NumberFormatException, NullPointerException
/*     */   {
/* 143 */     String[] TempString = new String[SetSize];
/*     */     
/* 145 */     double[] ret = new double[SetSize];
/*     */     
/* 147 */     for (int i = 0; i < SetSize; i++)
/*     */     {
/* 149 */       TempString[i] = ((String)OutputTBL.getValueAt(i, 2));
/* 150 */       ret[i] = Double.parseDouble(TempString[i]);
/*     */     }
/* 152 */     return ret;
/*     */   }
/*     */   
/*     */   private int[][] getDateFromOutputTbl(JTable OutputTBL, int TBLSize)
/*     */     throws NumberFormatException, NullPointerException
/*     */   {
/* 158 */     int[][] ret = new int[TBLSize][3];
/* 159 */     String[][] data = new String[3][TBLSize];
/*     */     
/*     */ 
/* 162 */     for (int i = 0; i < TBLSize; i++)
/*     */     {
/* 164 */       String TempString = (String)OutputTBL.getValueAt(i, 0);
/* 165 */       data[0][i] = "";
/* 166 */       data[1][i] = "";
/* 167 */       data[2][i] = "";
/* 168 */       int h = 0;
/* 169 */       for (int j = 0; j < TempString.length(); j++)
/*     */       {
/* 171 */         if (TempString.charAt(j) == '/')
/*     */         {
/* 173 */           h++;
/* 174 */           while (TempString.charAt(j) == '/')
/*     */           {
/* 176 */             j++;
/*     */           }
/*     */         }
/* 179 */         int tmp126_124 = i; String[] tmp126_123 = data[h];tmp126_123[tmp126_124] = (tmp126_123[tmp126_124] + TempString.charAt(j));
/*     */       }
/* 181 */       for (j = 0; j < 3; j++)
/*     */       {
/* 183 */         ret[i][j] = Integer.parseInt(data[j][i]);
/*     */       }
/*     */     }
/* 186 */     return ret;
/*     */   }
/*     */   
/*     */   private int[][] getTimeOfDayFromOutputTBL(JTable OutputTBL, int SetSize) throws NumberFormatException, NullPointerException
/*     */   {
/* 191 */     int[][] ret = new int[SetSize][2];
/*     */     
/* 193 */     String[][] data = new String[2][SetSize];
/* 194 */     for (int i = 0; i < SetSize; i++) {
/* 195 */       String tempString = (String)OutputTBL.getValueAt(i, 1);
/*     */       
/* 197 */       if (tempString == null)
/*     */       {
/* 199 */         throw new NumberFormatException("Date Format Must Be (mm/dd/yyyy)");
/*     */       }
/*     */       
/*     */ 
/* 203 */       data[0][i] = "";
/* 204 */       data[1][i] = "";
/* 205 */       int h = 0;
/* 206 */       for (int j = 0; j < tempString.length(); j++)
/*     */       {
/* 208 */         if (tempString.charAt(j) == ':')
/*     */         {
/* 210 */           h++;
/* 211 */           while (tempString.charAt(j) == ':')
/*     */           {
/* 213 */             j++;
/*     */           }
/*     */         }
/*     */         
/* 217 */         int tmp132_130 = i; String[] tmp132_129 = data[h];tmp132_129[tmp132_130] = (tmp132_129[tmp132_130] + tempString.charAt(j));
/*     */       }
/* 219 */       for (j = 0; j < 2; j++)
/*     */       {
/* 221 */         ret[i][j] = Integer.parseInt(data[j][i]);
/*     */       }
/*     */     }
/*     */     
/* 225 */     return ret;
/*     */   }
/*     */   
/*     */   public long GetTime(int[] time)
/*     */   {
/* 230 */     Calendar cal = Calendar.getInstance();
/* 231 */     cal.clear();
/* 232 */     cal.set(time[2], time[0], time[1], time[3], time[4]);
/* 233 */     return cal.getTimeInMillis();
/*     */   }
/*     */   
/*     */   public long GetTime(int[] time, int[] timeofday) {
/* 237 */     Calendar cal = Calendar.getInstance();
/* 238 */     cal.clear();
/* 239 */     cal.set(time[2], time[0], time[1], timeofday[0], timeofday[1]);
/* 240 */     return cal.getTimeInMillis();
/*     */   }
/*     */   
/*     */   private void PointConfigure()
/*     */     throws UserError
/*     */   {
/* 246 */     int[] StartDate = new int[5];int[] EndDate = new int[5];
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 251 */     for (int i = 0; i < 3; i++)
/*     */     {
/* 253 */       StartDate[i] = this.date[0][i];
/* 254 */       EndDate[i] = this.date[(this.date.length - 1)][i];
/*     */     }
/* 256 */     for (i = 3; i < 5; i++)
/*     */     {
/* 258 */       StartDate[i] = this.timeOfDay[0][(i - 3)];
/* 259 */       EndDate[i] = this.timeOfDay[(this.timeOfDay.length - 1)][(i - 3)];
/*     */     }
/*     */     
/* 262 */     double minTime = GetTime(StartDate);
/* 263 */     double timeSpace = GetTime(EndDate) - minTime;
/* 264 */     if ((timeSpace > 3600000.0D) && (4.32E8D > timeSpace))
/*     */     {
/*     */ 
/* 267 */       for (i = 0; i < this.temp.length; i++)
/*     */       {
/* 269 */         this.maxValue = Math.max(this.maxValue, this.temp[i]);
/* 270 */         this.minValue = Math.min(this.minValue, this.temp[i]);
/*     */       }
/* 272 */       this.scale = (this.maxValue - this.minValue);
/* 273 */       for (i = 0; i < this.temp.length;)
/*     */       {
/* 275 */         double x = (GetTime(this.date[i], this.timeOfDay[i]) - minTime) / timeSpace;
/* 276 */         double y = (this.temp[i] - this.minValue) / this.scale;
/* 277 */         String tempHold = "" + this.temp[i];
/* 278 */         String dateHold = "" + this.date[i][0] + "/" + this.date[i][1] + "/" + this.date[i][2] + " " + this.timeOfDay[i][0] + ":" + this.timeOfDay[i][1];
/* 279 */         this.points.add(new Point(x, y, tempHold));
/* 280 */         this.drawDate.add(dateHold);i++; continue;
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 283 */         if (timeSpace < 3600000.0D)
/*     */         {
/*     */ 
/* 286 */           throw new UserError("Date format", "the date choosen was too small");
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 291 */         throw new UserError("Date format", "the date chosen was too large, use excels API to create the graph.");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void checkword(Graphics2D g, int posX, int y, int h, int i) {
/* 297 */     if (posX > this.LastLocation + 100)
/*     */     {
/* 299 */       this.LastLocation = posX;
/* 300 */       g.drawString((String)this.drawDate.get(i), posX, h - y / 2);
/* 301 */       g.setColor(this.lines);
/* 302 */       g.drawLine(posX, h - (y + 5), posX, h - (y - 5));
/*     */     }
/*     */   }
/*     */   
/*     */   private class Point
/*     */   {
/*     */     private double x;
/*     */     private double y;
/*     */     private String temp;
/*     */     
/*     */     public Point(double x, double y, String temp) {
/* 313 */       this.temp = temp;
/* 314 */       this.x = x;
/* 315 */       this.y = y;
/*     */     }
/*     */     
/*     */     protected void Render(Graphics2D g, int ScreenX, int ScreenY, int ScreenW, int ScreenH, int index)
/*     */     {
/* 320 */       int w = ScreenW - ScreenX - 60;int h = ScreenH - ScreenY - 60;
/* 321 */       g.fillOval(ScreenX + ((int)(this.x * w) - 2), ScreenH - (ScreenY + (int)(this.y * h) - 2), 3, 3);
/* 322 */       g.drawLine(ScreenX + 5, ScreenH - (ScreenY + (int)(this.y * h) - 1), ScreenX - 5, ScreenH - (ScreenY + (int)(this.y * h) - 1));
/* 323 */       g.drawLine(ScreenX + ((int)(this.x * w) - 1), ScreenH - 60 + 5, ScreenX + ((int)(this.x * w) - 1), ScreenH - 60 - 5);
/* 324 */       if (Graph.this.showText)
/*     */       {
/* 326 */         g.setColor(Graph.this.wordColor);
/* 327 */         g.drawString(this.temp, ScreenX / 3, ScreenH - (ScreenY + (int)(this.y * h) - 1));
/* 328 */         Graph.this.checkword(g, ScreenX + ((int)(this.x * w) - 1), ScreenX, ScreenH, index);
/* 329 */         g.setColor(Graph.this.dotColor);
/*     */       }
/*     */     }
/*     */     
/*     */     public void setX(long x) {
/* 334 */       this.x = x;
/*     */     }
/*     */     
/*     */     public void setY(long y) {
/* 338 */       this.y = y;
/*     */     }
/*     */     
/*     */     public void setTempMessage(String message) {
/* 342 */       this.temp = message;
/*     */     }
/*     */     
/*     */     public double getX() {
/* 346 */       return this.x;
/*     */     }
/*     */     
/*     */     public double getY() {
/* 350 */       return this.y;
/*     */     }
/*     */     
/*     */     public String getTempMessage() {
/* 354 */       return this.temp;
/*     */     }
/*     */   }
/*     */   
/*     */   private class JGraphPanel extends JPanel
/*     */   {
/*     */     private JGraphPanel() {}
/*     */     
/*     */     public void paint(Graphics g)
/*     */     {
/* 364 */       Graphics2D g2 = (Graphics2D)g;
/*     */       
/* 366 */       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */       
/* 368 */       int w = getWidth();int h = getHeight();
/*     */       
/* 370 */       g2.setColor(Graph.this.background);
/* 371 */       g2.fillRect(0, 0, w, h);
/*     */       
/* 373 */       g2.setColor(Graph.this.lines);
/*     */       
/* 375 */       g2.drawLine(60, h - 60, w - 60, h - 60);
/* 376 */       g2.drawLine(60, h - 60, 60, 60);
/* 377 */       g2.setPaint(Graph.this.dotColor);
/*     */       
/* 379 */       for (int i = 0; i < Graph.this.points.size(); i++) {
/* 380 */         ((Graph.Point)Graph.this.points.get(i)).Render(g2, 62, 62, w, h, i);
/*     */       }
/* 382 */       Graph.this.LastLocation = 63536;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\James\Desktop\jd-gui-windows-1.4.0\ForestTemperature.jar!\foresttemperature\Graph.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */