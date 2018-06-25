/*     */ package foresttemperature;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.WindowEvent;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ import javax.swing.UIManager.LookAndFeelInfo;
/*     */ 
/*     */ public class Credit extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JLabel CASELBL;
/*     */   private JLabel CourseLBL;
/*     */   private JLabel DesignerLBL;
/*     */   private JButton okB;
/*     */   private JLabel teacherLBL;
/*     */   
/*     */   public Credit(java.awt.Frame parent, boolean modal)
/*     */   {
/*  26 */     super(parent, modal);
/*  27 */     initComponents();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  38 */     this.okB = new JButton();
/*  39 */     this.DesignerLBL = new JLabel();
/*  40 */     this.CourseLBL = new JLabel();
/*  41 */     this.teacherLBL = new JLabel();
/*  42 */     this.CASELBL = new JLabel();
/*     */     
/*  44 */     setTitle("Credit");
/*  45 */     addWindowListener(new java.awt.event.WindowAdapter() {
/*     */       public void windowClosing(WindowEvent evt) {
/*  47 */         Credit.this.closeDialog(evt);
/*     */       }
/*     */       
/*  50 */     });
/*  51 */     this.okB.setText("OK");
/*  52 */     this.okB.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/*  54 */         Credit.this.okBActionPerformed(evt);
/*     */       }
/*     */       
/*  57 */     });
/*  58 */     this.DesignerLBL.setText("Designer & Code: James Small");
/*     */     
/*  60 */     this.CourseLBL.setText("Course Created in: Case Senior Project");
/*     */     
/*  62 */     this.teacherLBL.setText("Teacher: Terry Yoast");
/*     */     
/*  64 */     this.CASELBL.setText("CASE facilitator: terry yoast");
/*     */     
/*  66 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  67 */     getContentPane().setLayout(layout);
/*  68 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.CourseLBL, -1, -1, 32767).addComponent(this.DesignerLBL).addComponent(this.okB, -1, -1, 32767)).addComponent(this.teacherLBL).addComponent(this.CASELBL)).addContainerGap(-1, 32767)));
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
/*  81 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(this.CourseLBL).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.CASELBL).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.teacherLBL).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.DesignerLBL).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.okB).addContainerGap()));
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
/*  97 */     getRootPane().setDefaultButton(this.okB);
/*     */     
/*  99 */     pack();
/*     */   }
/*     */   
/*     */   private void okBActionPerformed(ActionEvent evt) {
/* 103 */     doClose();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void closeDialog(WindowEvent evt)
/*     */   {
/* 110 */     doClose();
/*     */   }
/*     */   
/*     */   private void doClose()
/*     */   {
/* 115 */     setVisible(false);
/* 116 */     dispose();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void Creditable()
/*     */   {
/*     */     try
/*     */     {
/* 129 */       for (UIManager.LookAndFeelInfo info : ) {
/* 130 */         if ("Nimbus".equals(info.getName())) {
/* 131 */           javax.swing.UIManager.setLookAndFeel(info.getClassName());
/* 132 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (ClassNotFoundException|InstantiationException|IllegalAccessException|javax.swing.UnsupportedLookAndFeelException ex) {
/* 137 */       javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage(), "Runtime Exception", 1);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 143 */     java.awt.EventQueue.invokeLater(new Runnable()
/*     */     {
/*     */ 
/*     */       public void run()
/*     */       {
/* 148 */         Credit dialog = new Credit(new javax.swing.JFrame(), true);
/* 149 */         dialog.addWindowListener(new java.awt.event.WindowAdapter()
/*     */         {
/*     */ 
/*     */           public void windowClosing(WindowEvent e) {}
/*     */ 
/*     */ 
/* 155 */         });
/* 156 */         dialog.setVisible(true);
/*     */       }
/*     */     });
/*     */   }
/*     */ }


/* Location:              C:\Users\James\Desktop\jd-gui-windows-1.4.0\ForestTemperature.jar!\foresttemperature\Credit.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */