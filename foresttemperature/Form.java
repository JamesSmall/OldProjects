/*      */ package foresttemperature;
/*      */ 
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.io.IOException;
/*      */ import java.sql.SQLException;
/*      */ import javax.swing.GroupLayout;
/*      */ import javax.swing.GroupLayout.Alignment;
/*      */ import javax.swing.GroupLayout.ParallelGroup;
/*      */ import javax.swing.GroupLayout.SequentialGroup;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBoxMenuItem;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JRadioButton;
/*      */ import javax.swing.JTable;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.LayoutStyle.ComponentPlacement;
/*      */ 
/*      */ public class Form extends javax.swing.JFrame
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   
/*      */   public Form()
/*      */   {
/*   27 */     initComponents();
/*   28 */     addWindowListener(new java.awt.event.WindowAdapter()
/*      */     {
/*      */       public void windowClosing(java.awt.event.WindowEvent we) {
/*   31 */         if ((Form.this.addBTN.isEnabled()) && (Form.this.addToDataBaseBTN.isEnabled()) && (Form.this.toExcelBTN.isEnabled()) && (Form.this.selectKOJCData.isEnabled()))
/*      */         {
/*   33 */           System.exit(0);
/*      */         }
/*      */         else
/*      */         {
/*   37 */           int select = JOptionPane.showConfirmDialog(null, "A Process is currently Running, Are You sure you want to exit?", "Warning", 2);
/*   38 */           if (select == 0) {
/*   39 */             System.exit(0);
/*      */           }
/*      */         }
/*      */       }
/*      */     });
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void initComponents()
/*      */   {
/*   55 */     this.jButton1 = new JButton();
/*   56 */     this.ConnectPanel = new javax.swing.JPanel();
/*   57 */     this.usernameLBL = new JLabel();
/*   58 */     this.userNameTXT = new JTextField();
/*   59 */     this.passwordPWF = new javax.swing.JPasswordField();
/*   60 */     this.connectBTN = new JButton();
/*   61 */     this.passwordLBL = new JLabel();
/*   62 */     this.CreateNewUserB = new JButton();
/*   63 */     this.StudentUserR = new JRadioButton();
/*   64 */     this.TrustedStudentR = new JRadioButton();
/*   65 */     this.AdminR = new JRadioButton();
/*   66 */     this.StudentWarningLBL = new JLabel();
/*   67 */     this.studentwarning2LBL = new JLabel();
/*   68 */     this.DeleteUserB = new JButton();
/*   69 */     this.switchTP = new javax.swing.JTabbedPane();
/*   70 */     this.inputP = new javax.swing.JPanel();
/*   71 */     this.outputSP = new javax.swing.JScrollPane();
/*   72 */     this.outputTBL = new JTable();
/*   73 */     this.searchP = new javax.swing.JPanel();
/*   74 */     this.SearchCreatedRAD = new JRadioButton();
/*   75 */     this.SearchBTN = new JButton();
/*   76 */     this.DeleteBTN = new JButton();
/*   77 */     this.GraphBTN = new JButton();
/*   78 */     this.outputStartTimeTXT = new JTextField();
/*   79 */     this.outputEndTimeTXT = new JTextField();
/*   80 */     this.InputTimeTXT = new JLabel();
/*   81 */     this.outputStartDateTXT = new JTextField();
/*   82 */     this.outputEndDateTXT = new JTextField();
/*   83 */     this.InputDateTXT = new JLabel();
/*   84 */     this.StartDateLBL = new JLabel();
/*   85 */     this.endDateLBL = new JLabel();
/*   86 */     this.outputP = new javax.swing.JPanel();
/*   87 */     this.inputScrollP = new javax.swing.JScrollPane();
/*   88 */     this.InputTBL = new JTable();
/*   89 */     this.addBTN = new JButton();
/*   90 */     this.editBTN = new JButton();
/*   91 */     this.sizeT = new JTextField();
/*   92 */     this.sizeL = new JLabel();
/*   93 */     this.ExcelP = new javax.swing.JPanel();
/*   94 */     this.toExcelBTN = new JButton();
/*   95 */     this.excelStartDateTXT = new JTextField();
/*   96 */     this.excelStartTime = new JLabel();
/*   97 */     this.excelStartTimeTXT = new JTextField();
/*   98 */     this.excelEndDateTXT = new JTextField();
/*   99 */     this.excelEndTimeTXT = new JTextField();
/*  100 */     this.excelEndTime = new JLabel();
/*  101 */     this.excelStartDateLBL = new JLabel();
/*  102 */     this.excelEndDateLBL = new JLabel();
/*  103 */     this.outputSP1 = new javax.swing.JScrollPane();
/*  104 */     this.excelMoveDataTable = new JTable();
/*  105 */     this.DataMovedLBL = new JLabel();
/*  106 */     this.addToDataBaseBTN = new JButton();
/*  107 */     this.selectKOJCData = new JButton();
/*  108 */     this.KOJCLBL = new JLabel();
/*  109 */     this.ExcelLBL = new JLabel();
/*  110 */     this.ExcelDoNotMoveCreatedData = new JRadioButton();
/*  111 */     this.editToDataBaseBTN = new JButton();
/*  112 */     this.choicesMNU = new javax.swing.JMenuBar();
/*  113 */     this.FileMNU = new javax.swing.JMenu();
/*  114 */     this.CreditMNU = new JMenuItem();
/*  115 */     this.SetDefaultUserB = new JMenuItem();
/*  116 */     this.editMNU = new javax.swing.JMenu();
/*  117 */     this.CleanDataBaseBTN = new JMenuItem();
/*  118 */     this.deleteCreatedDataMNUB = new JMenuItem();
/*  119 */     this.AllowButtonEditMNUR = new JCheckBoxMenuItem();
/*  120 */     this.LinesCBMI = new JCheckBoxMenuItem();
/*  121 */     this.GiveDuplicatesCBMI = new JCheckBoxMenuItem();
/*  122 */     this.errorLogMNU = new javax.swing.JMenu();
/*  123 */     this.writeAllErrorBM = new JMenuItem();
/*  124 */     this.SeriousErrorMNUB = new JMenuItem();
/*  125 */     this.GeneralErrorMNUB = new JMenuItem();
/*  126 */     this.MinorErrorMNUB = new JMenuItem();
/*  127 */     this.NonErrorMNUB = new JMenuItem();
/*  128 */     this.UnknownErrorMNUB = new JMenuItem();
/*  129 */     this.UserErrorMNUB = new JMenuItem();
/*      */     
/*  131 */     this.jButton1.setText("jButton1");
/*      */     
/*  133 */     setDefaultCloseOperation(0);
/*  134 */     setTitle("Forest Temperature");
/*  135 */     setName("ForestFrame");
/*      */     
/*  137 */     this.ConnectPanel.setAlignmentX(0.0F);
/*  138 */     this.ConnectPanel.setAlignmentY(0.0F);
/*  139 */     this.ConnectPanel.setAutoscrolls(true);
/*  140 */     this.ConnectPanel.setCursor(new java.awt.Cursor(0));
/*  141 */     this.ConnectPanel.setMaximumSize(new java.awt.Dimension(163, 110));
/*  142 */     this.ConnectPanel.setMinimumSize(new java.awt.Dimension(163, 110));
/*  143 */     this.ConnectPanel.setName("");
/*      */     
/*  145 */     this.usernameLBL.setText("Username");
/*      */     
/*  147 */     this.connectBTN.setText("connect");
/*  148 */     this.connectBTN.setToolTipText("");
/*  149 */     this.connectBTN.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  151 */         Form.this.connectBTNActionPerformed(evt);
/*      */       }
/*      */       
/*  154 */     });
/*  155 */     this.passwordLBL.setText("Password");
/*      */     
/*  157 */     this.CreateNewUserB.setText("create new User");
/*  158 */     this.CreateNewUserB.setEnabled(false);
/*  159 */     this.CreateNewUserB.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  161 */         Form.this.CreateNewUserBActionPerformed(evt);
/*      */       }
/*      */       
/*  164 */     });
/*  165 */     this.StudentUserR.setText("create Student");
/*  166 */     this.StudentUserR.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  168 */         Form.this.StudentUserRActionPerformed(evt);
/*      */       }
/*      */       
/*  171 */     });
/*  172 */     this.TrustedStudentR.setText("create Trusted Student");
/*  173 */     this.TrustedStudentR.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  175 */         Form.this.TrustedStudentRActionPerformed(evt);
/*      */       }
/*      */       
/*  178 */     });
/*  179 */     this.AdminR.setText("create Admin");
/*  180 */     this.AdminR.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  182 */         Form.this.AdminRActionPerformed(evt);
/*      */       }
/*      */       
/*  185 */     });
/*  186 */     this.StudentWarningLBL.setText("you must have admin to create");
/*      */     
/*  188 */     this.studentwarning2LBL.setText("or edit accounts");
/*      */     
/*  190 */     this.DeleteUserB.setText("Delete User");
/*  191 */     this.DeleteUserB.setEnabled(false);
/*  192 */     this.DeleteUserB.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  194 */         Form.this.DeleteUserBActionPerformed(evt);
/*      */       }
/*      */       
/*  197 */     });
/*  198 */     GroupLayout ConnectPanelLayout = new GroupLayout(this.ConnectPanel);
/*  199 */     this.ConnectPanel.setLayout(ConnectPanelLayout);
/*  200 */     ConnectPanelLayout.setHorizontalGroup(ConnectPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(ConnectPanelLayout.createSequentialGroup().addContainerGap().addGroup(ConnectPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.userNameTXT).addComponent(this.passwordPWF, GroupLayout.Alignment.TRAILING).addComponent(this.connectBTN, -1, -1, 32767).addComponent(this.CreateNewUserB, -1, -1, 32767).addGroup(ConnectPanelLayout.createSequentialGroup().addGroup(ConnectPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.passwordLBL).addComponent(this.usernameLBL)).addContainerGap()).addComponent(this.DeleteUserB, -1, -1, 32767).addGroup(ConnectPanelLayout.createSequentialGroup().addGroup(ConnectPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.studentwarning2LBL).addComponent(this.StudentWarningLBL).addGroup(ConnectPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.StudentUserR, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.AdminR, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.TrustedStudentR, GroupLayout.Alignment.LEADING, -1, -1, 32767))).addGap(0, 10, 32767)))));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  225 */     ConnectPanelLayout.setVerticalGroup(ConnectPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(ConnectPanelLayout.createSequentialGroup().addComponent(this.usernameLBL).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.userNameTXT, -1, -1, -2).addGap(1, 1, 1).addComponent(this.passwordLBL).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.passwordPWF, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.connectBTN).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.CreateNewUserB).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.DeleteUserB).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.StudentUserR).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.TrustedStudentR).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.AdminR).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.StudentWarningLBL).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.studentwarning2LBL).addContainerGap(18, 32767)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  254 */     this.switchTP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
/*      */     
/*  256 */     this.outputTBL.setBackground(new java.awt.Color(204, 204, 204));
/*  257 */     this.outputTBL.setModel(new javax.swing.table.DefaultTableModel(new Object[0][], new String[0]));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  265 */     this.outputTBL.setEnabled(false);
/*  266 */     this.outputTBL.setRowSelectionAllowed(false);
/*  267 */     this.outputSP.setViewportView(this.outputTBL);
/*      */     
/*  269 */     this.SearchCreatedRAD.setText("Do Not Search Created Data");
/*      */     
/*  271 */     this.SearchBTN.setText("Search");
/*  272 */     this.SearchBTN.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  274 */         Form.this.SearchBTNActionPerformed(evt);
/*      */       }
/*      */       
/*  277 */     });
/*  278 */     this.DeleteBTN.setText("Delete");
/*  279 */     this.DeleteBTN.setEnabled(false);
/*  280 */     this.DeleteBTN.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  282 */         Form.this.DeleteBTNActionPerformed(evt);
/*      */       }
/*      */       
/*  285 */     });
/*  286 */     this.GraphBTN.setText("Graph");
/*  287 */     this.GraphBTN.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  289 */         Form.this.GraphBTNActionPerformed(evt);
/*      */       }
/*      */       
/*  292 */     });
/*  293 */     this.InputTimeTXT.setText("(HH:MM)");
/*      */     
/*  295 */     this.InputDateTXT.setText("(MM/DD/YYYY)");
/*      */     
/*  297 */     this.StartDateLBL.setText("Start Date");
/*      */     
/*  299 */     this.endDateLBL.setText("End Date");
/*      */     
/*  301 */     GroupLayout searchPLayout = new GroupLayout(this.searchP);
/*  302 */     this.searchP.setLayout(searchPLayout);
/*  303 */     searchPLayout.setHorizontalGroup(searchPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, searchPLayout.createSequentialGroup().addContainerGap().addGroup(searchPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.StartDateLBL).addComponent(this.endDateLBL)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(searchPLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.InputDateTXT).addComponent(this.outputStartDateTXT, -1, 112, 32767).addComponent(this.outputEndDateTXT)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(searchPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.InputTimeTXT).addGroup(searchPLayout.createSequentialGroup().addGroup(searchPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.outputEndTimeTXT, -2, 106, -2).addComponent(this.outputStartTimeTXT, -2, 106, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(searchPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(searchPLayout.createSequentialGroup().addGroup(searchPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.SearchBTN, -2, 76, -2).addComponent(this.GraphBTN, -2, 76, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.SearchCreatedRAD)).addComponent(this.DeleteBTN, -2, 76, -2)))).addContainerGap(-1, 32767)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  333 */     searchPLayout.setVerticalGroup(searchPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(searchPLayout.createSequentialGroup().addGroup(searchPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(searchPLayout.createSequentialGroup().addContainerGap().addComponent(this.SearchCreatedRAD)).addComponent(this.SearchBTN).addGroup(searchPLayout.createSequentialGroup().addGap(6, 6, 6).addGroup(searchPLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.InputDateTXT).addComponent(this.InputTimeTXT)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(searchPLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.outputStartDateTXT, -2, -1, -2).addComponent(this.outputStartTimeTXT, -2, -1, -2).addComponent(this.GraphBTN).addComponent(this.StartDateLBL)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(searchPLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.outputEndDateTXT, -2, -1, -2).addComponent(this.outputEndTimeTXT, -2, -1, -2).addComponent(this.DeleteBTN).addComponent(this.endDateLBL)))).addContainerGap(17, 32767)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  361 */     GroupLayout inputPLayout = new GroupLayout(this.inputP);
/*  362 */     this.inputP.setLayout(inputPLayout);
/*  363 */     inputPLayout.setHorizontalGroup(inputPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.outputSP, -1, 579, 32767).addGroup(inputPLayout.createSequentialGroup().addComponent(this.searchP, -1, -1, -2).addGap(0, 0, 32767)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  370 */     inputPLayout.setVerticalGroup(inputPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(inputPLayout.createSequentialGroup().addComponent(this.outputSP, -1, 230, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.searchP, -2, -1, -2)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  378 */     this.switchTP.addTab("OutPut", this.inputP);
/*      */     
/*  380 */     this.InputTBL.setBackground(new java.awt.Color(204, 204, 204));
/*  381 */     this.InputTBL.setBorder(new javax.swing.border.SoftBevelBorder(0));
/*  382 */     this.InputTBL.setModel(new javax.swing.table.DefaultTableModel(new Object[0][], new String[0]));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  390 */     this.InputTBL.setToolTipText("");
/*  391 */     this.InputTBL.setAutoscrolls(false);
/*  392 */     this.InputTBL.setCursor(new java.awt.Cursor(0));
/*  393 */     this.InputTBL.setGridColor(new java.awt.Color(0, 0, 0));
/*  394 */     this.InputTBL.setOpaque(false);
/*  395 */     this.InputTBL.setRowSelectionAllowed(false);
/*  396 */     this.InputTBL.setSelectionBackground(new java.awt.Color(0, 102, 204));
/*  397 */     this.InputTBL.setSelectionForeground(new java.awt.Color(153, 255, 255));
/*  398 */     this.InputTBL.getTableHeader().setReorderingAllowed(false);
/*  399 */     this.inputScrollP.setViewportView(this.InputTBL);
/*      */     
/*  401 */     this.addBTN.setText("Add Set");
/*  402 */     this.addBTN.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  404 */         Form.this.addBTNActionPerformed(evt);
/*      */       }
/*      */       
/*  407 */     });
/*  408 */     this.editBTN.setText("Edit Set");
/*  409 */     this.editBTN.setEnabled(false);
/*  410 */     this.editBTN.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  412 */         Form.this.editBTNActionPerformed(evt);
/*      */       }
/*      */       
/*  415 */     });
/*  416 */     this.sizeT.setText("0");
/*  417 */     this.sizeT.addCaretListener(new javax.swing.event.CaretListener() {
/*      */       public void caretUpdate(javax.swing.event.CaretEvent evt) {
/*  419 */         Form.this.sizeTCaretUpdate(evt);
/*      */       }
/*  421 */     });
/*  422 */     this.sizeT.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  424 */         Form.this.sizeTActionPerformed(evt);
/*      */       }
/*      */       
/*  427 */     });
/*  428 */     this.sizeL.setText("size of Table");
/*      */     
/*  430 */     GroupLayout outputPLayout = new GroupLayout(this.outputP);
/*  431 */     this.outputP.setLayout(outputPLayout);
/*  432 */     outputPLayout.setHorizontalGroup(outputPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, outputPLayout.createSequentialGroup().addContainerGap().addComponent(this.addBTN).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.editBTN).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 259, 32767).addComponent(this.sizeL).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.sizeT, -2, 90, -2).addContainerGap()).addComponent(this.inputScrollP, GroupLayout.Alignment.TRAILING));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  446 */     outputPLayout.setVerticalGroup(outputPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, outputPLayout.createSequentialGroup().addComponent(this.inputScrollP, -1, 291, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(outputPLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.addBTN).addComponent(this.editBTN).addComponent(this.sizeT, -2, -1, -2).addComponent(this.sizeL)).addContainerGap()));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  459 */     this.switchTP.addTab("Input", this.outputP);
/*      */     
/*  461 */     this.toExcelBTN.setText("To Excel");
/*  462 */     this.toExcelBTN.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  464 */         Form.this.toExcelBTNActionPerformed(evt);
/*      */       }
/*      */       
/*  467 */     });
/*  468 */     this.excelStartTime.setText("Start Time");
/*      */     
/*  470 */     this.excelEndTime.setText("End Time");
/*      */     
/*  472 */     this.excelStartDateLBL.setText("Start Date");
/*      */     
/*  474 */     this.excelEndDateLBL.setText("End Date");
/*      */     
/*  476 */     this.excelMoveDataTable.setBackground(new java.awt.Color(204, 204, 204));
/*  477 */     this.excelMoveDataTable.setModel(new javax.swing.table.DefaultTableModel(new Object[0][], new String[0]));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  485 */     this.excelMoveDataTable.setEnabled(false);
/*  486 */     this.outputSP1.setViewportView(this.excelMoveDataTable);
/*      */     
/*  488 */     this.DataMovedLBL.setText("Data Moved");
/*      */     
/*  490 */     this.addToDataBaseBTN.setText("Add To Database");
/*  491 */     this.addToDataBaseBTN.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  493 */         Form.this.addToDataBaseBTNActionPerformed(evt);
/*      */       }
/*      */       
/*  496 */     });
/*  497 */     this.selectKOJCData.setText("Get KOJC Data");
/*  498 */     this.selectKOJCData.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  500 */         Form.this.selectKOJCDataActionPerformed(evt);
/*      */       }
/*      */       
/*  503 */     });
/*  504 */     this.KOJCLBL.setText("KOJC data management");
/*      */     
/*  506 */     this.ExcelLBL.setText("Excel Data Management");
/*      */     
/*  508 */     this.ExcelDoNotMoveCreatedData.setText("Do not copy Created Data into excel");
/*      */     
/*  510 */     this.editToDataBaseBTN.setText("Edit To Database");
/*  511 */     this.editToDataBaseBTN.setEnabled(false);
/*  512 */     this.editToDataBaseBTN.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  514 */         Form.this.editToDataBaseBTNActionPerformed(evt);
/*      */       }
/*      */       
/*  517 */     });
/*  518 */     GroupLayout ExcelPLayout = new GroupLayout(this.ExcelP);
/*  519 */     this.ExcelP.setLayout(ExcelPLayout);
/*  520 */     ExcelPLayout.setHorizontalGroup(ExcelPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(ExcelPLayout.createSequentialGroup().addContainerGap().addGroup(ExcelPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(ExcelPLayout.createSequentialGroup().addGroup(ExcelPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.excelStartDateLBL).addComponent(this.excelEndDateLBL).addComponent(this.excelStartTime)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(ExcelPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.excelStartDateTXT, -2, 132, -2).addComponent(this.excelStartTimeTXT, -2, 132, -2).addGroup(GroupLayout.Alignment.TRAILING, ExcelPLayout.createSequentialGroup().addGroup(ExcelPLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.excelEndTimeTXT, -2, 132, -2).addComponent(this.excelEndDateTXT, -2, 132, -2)).addGap(27, 27, 27))).addComponent(this.DataMovedLBL)).addGroup(ExcelPLayout.createSequentialGroup().addGroup(ExcelPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(ExcelPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(ExcelPLayout.createSequentialGroup().addGroup(ExcelPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.KOJCLBL).addComponent(this.ExcelLBL).addGroup(ExcelPLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.editToDataBaseBTN, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.toExcelBTN, GroupLayout.Alignment.LEADING, -1, 192, 32767).addComponent(this.addToDataBaseBTN, GroupLayout.Alignment.LEADING, -1, 192, 32767).addComponent(this.selectKOJCData, GroupLayout.Alignment.LEADING, -1, -1, 32767))).addGap(15, 15, 15)).addComponent(this.ExcelDoNotMoveCreatedData, GroupLayout.Alignment.TRAILING)).addComponent(this.excelEndTime)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.outputSP1, -1, 350, 32767))).addContainerGap()));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  559 */     ExcelPLayout.setVerticalGroup(ExcelPLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, ExcelPLayout.createSequentialGroup().addContainerGap().addGroup(ExcelPLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.excelStartDateTXT, -2, -1, -2).addComponent(this.excelStartDateLBL)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(ExcelPLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.excelStartTimeTXT, -2, -1, -2).addComponent(this.excelStartTime, -2, 14, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(ExcelPLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.excelEndDateLBL).addComponent(this.excelEndDateTXT, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(ExcelPLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.excelEndTime).addComponent(this.excelEndTimeTXT, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.ExcelLBL).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.toExcelBTN).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.addToDataBaseBTN).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.editToDataBaseBTN).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.ExcelDoNotMoveCreatedData).addGap(5, 5, 5).addComponent(this.KOJCLBL).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.selectKOJCData).addContainerGap(42, 32767)).addGroup(ExcelPLayout.createSequentialGroup().addComponent(this.DataMovedLBL).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.outputSP1, -2, 0, 32767).addContainerGap()));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  600 */     this.switchTP.addTab("Excel & KOJC data Management", this.ExcelP);
/*      */     
/*  602 */     this.FileMNU.setText("File");
/*      */     
/*  604 */     this.CreditMNU.setText("Credit");
/*  605 */     this.CreditMNU.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  607 */         Form.this.CreditMNUActionPerformed(evt);
/*      */       }
/*  609 */     });
/*  610 */     this.FileMNU.add(this.CreditMNU);
/*      */     
/*  612 */     this.SetDefaultUserB.setText("Set Default User");
/*  613 */     this.SetDefaultUserB.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  615 */         Form.this.SetDefaultUserBActionPerformed(evt);
/*      */       }
/*  617 */     });
/*  618 */     this.FileMNU.add(this.SetDefaultUserB);
/*      */     
/*  620 */     this.choicesMNU.add(this.FileMNU);
/*      */     
/*  622 */     this.editMNU.setText("Edit");
/*      */     
/*  624 */     this.CleanDataBaseBTN.setText("Clean DataBase");
/*  625 */     this.CleanDataBaseBTN.setEnabled(false);
/*  626 */     this.CleanDataBaseBTN.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  628 */         Form.this.CleanDataBaseBTNActionPerformed(evt);
/*      */       }
/*  630 */     });
/*  631 */     this.editMNU.add(this.CleanDataBaseBTN);
/*      */     
/*  633 */     this.deleteCreatedDataMNUB.setText("Delete All Created Data");
/*  634 */     this.deleteCreatedDataMNUB.setEnabled(false);
/*  635 */     this.deleteCreatedDataMNUB.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  637 */         Form.this.deleteCreatedDataMNUBActionPerformed(evt);
/*      */       }
/*  639 */     });
/*  640 */     this.editMNU.add(this.deleteCreatedDataMNUB);
/*      */     
/*  642 */     this.AllowButtonEditMNUR.setText("Enable Edit And Delete");
/*  643 */     this.AllowButtonEditMNUR.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  645 */         Form.this.AllowButtonEditMNURActionPerformed(evt);
/*      */       }
/*  647 */     });
/*  648 */     this.editMNU.add(this.AllowButtonEditMNUR);
/*      */     
/*  650 */     this.LinesCBMI.setSelected(true);
/*  651 */     this.LinesCBMI.setText("Show Lines");
/*  652 */     this.LinesCBMI.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  654 */         Form.this.LinesCBMIActionPerformed(evt);
/*      */       }
/*  656 */     });
/*  657 */     this.editMNU.add(this.LinesCBMI);
/*      */     
/*  659 */     this.GiveDuplicatesCBMI.setSelected(true);
/*  660 */     this.GiveDuplicatesCBMI.setText("Duplicate Warning");
/*  661 */     this.editMNU.add(this.GiveDuplicatesCBMI);
/*      */     
/*  663 */     this.choicesMNU.add(this.editMNU);
/*      */     
/*  665 */     this.errorLogMNU.setText("Error Log");
/*      */     
/*  667 */     this.writeAllErrorBM.setText("Write All Errors");
/*  668 */     this.writeAllErrorBM.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  670 */         Form.this.writeAllErrorBMActionPerformed(evt);
/*      */       }
/*  672 */     });
/*  673 */     this.errorLogMNU.add(this.writeAllErrorBM);
/*      */     
/*  675 */     this.SeriousErrorMNUB.setText("Write Serious Errors");
/*  676 */     this.SeriousErrorMNUB.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  678 */         Form.this.SeriousErrorMNUBActionPerformed(evt);
/*      */       }
/*  680 */     });
/*  681 */     this.errorLogMNU.add(this.SeriousErrorMNUB);
/*      */     
/*  683 */     this.GeneralErrorMNUB.setText("Write General Errors");
/*  684 */     this.GeneralErrorMNUB.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  686 */         Form.this.GeneralErrorMNUBActionPerformed(evt);
/*      */       }
/*  688 */     });
/*  689 */     this.errorLogMNU.add(this.GeneralErrorMNUB);
/*      */     
/*  691 */     this.MinorErrorMNUB.setText("Write Minor Errors");
/*  692 */     this.MinorErrorMNUB.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  694 */         Form.this.MinorErrorMNUBActionPerformed(evt);
/*      */       }
/*  696 */     });
/*  697 */     this.errorLogMNU.add(this.MinorErrorMNUB);
/*      */     
/*  699 */     this.NonErrorMNUB.setText("Write Non Errors");
/*  700 */     this.NonErrorMNUB.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  702 */         Form.this.NonErrorMNUBActionPerformed(evt);
/*      */       }
/*  704 */     });
/*  705 */     this.errorLogMNU.add(this.NonErrorMNUB);
/*      */     
/*  707 */     this.UnknownErrorMNUB.setText("Write Unknown Errors");
/*  708 */     this.UnknownErrorMNUB.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  710 */         Form.this.UnknownErrorMNUBActionPerformed(evt);
/*      */       }
/*  712 */     });
/*  713 */     this.errorLogMNU.add(this.UnknownErrorMNUB);
/*      */     
/*  715 */     this.UserErrorMNUB.setText("Write User Error");
/*  716 */     this.UserErrorMNUB.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent evt) {
/*  718 */         Form.this.UserErrorMNUBActionPerformed(evt);
/*      */       }
/*  720 */     });
/*  721 */     this.errorLogMNU.add(this.UserErrorMNUB);
/*      */     
/*  723 */     this.choicesMNU.add(this.errorLogMNU);
/*      */     
/*  725 */     setJMenuBar(this.choicesMNU);
/*      */     
/*  727 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  728 */     getContentPane().setLayout(layout);
/*  729 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.ConnectPanel, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.switchTP)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  737 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.switchTP).addGroup(layout.createSequentialGroup().addComponent(this.ConnectPanel, -2, -1, -2).addGap(0, 0, 32767)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  745 */     pack();
/*      */   }
/*      */   
/*      */   private void connectBTNActionPerformed(ActionEvent evt)
/*      */   {
/*  750 */     new Thread(new connect(null)).start();
/*      */   }
/*      */   
/*      */ 
/*      */   private void sizeTActionPerformed(ActionEvent evt) {}
/*      */   
/*      */ 
/*      */   private void sizeTCaretUpdate(javax.swing.event.CaretEvent evt)
/*      */   {
/*  759 */     String temp = this.sizeT.getText();
/*      */     
/*      */ 
/*      */     try
/*      */     {
/*  764 */       this.InputTBLSize = Integer.parseInt(temp);
/*  765 */       if ((this.InputTBLSize == 0) || ((this.InputTBLSize > 0) && (this.InputTBLSize + 1 < this.MaxInPutTBLSize)))
/*      */       {
/*  767 */         Object[][] tbl = new Object[this.InputTBLSize][3];
/*  768 */         javax.swing.table.DefaultTableModel Tf = new javax.swing.table.DefaultTableModel(tbl, INPUTLBLS);
/*  769 */         this.InputTBL.setModel(Tf);
/*  770 */         this.InputTBL.setShowHorizontalLines(this.LinesCBMI.isSelected());
/*  771 */         this.InputTBL.setShowVerticalLines(this.LinesCBMI.isSelected());
/*  772 */         this.editToDataBaseBTN.setEnabled(false);
/*      */       }
/*      */     }
/*      */     catch (NumberFormatException ex) {
/*  776 */       Log.addNonSeriousError(ex);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private void editBTNActionPerformed(ActionEvent evt)
/*      */   {
/*  783 */     setSearching(true);
/*  784 */     setAdding(true);
/*  785 */     new Thread(new NormalEdit(null)).start();
/*      */   }
/*      */   
/*      */   private void addBTNActionPerformed(ActionEvent evt)
/*      */   {
/*  790 */     setAdding(true);
/*  791 */     new Thread(new NormalAdd(null)).start();
/*      */   }
/*      */   
/*      */   private void DeleteBTNActionPerformed(ActionEvent evt)
/*      */   {
/*  796 */     setSearching(true);
/*  797 */     setAdding(true);
/*  798 */     new Thread(new NormalDelete()).start();
/*      */   }
/*      */   
/*      */   private void SearchBTNActionPerformed(ActionEvent evt) {
/*  802 */     setSearching(true);
/*  803 */     new Thread(new NormalSearch(null)).start();
/*      */   }
/*      */   
/*      */ 
/*      */   private void CreditMNUActionPerformed(ActionEvent evt) {}
/*      */   
/*      */ 
/*      */   private void CleanDataBaseBTNActionPerformed(ActionEvent evt)
/*      */   {
/*  812 */     setSearching(true);
/*  813 */     setAdding(true);
/*  814 */     new Thread(new CleanDataBase(null)).start();
/*      */   }
/*      */   
/*      */   private void addToDataBaseBTNActionPerformed(ActionEvent evt)
/*      */   {
/*  819 */     setAdding(true);
/*  820 */     new Thread(new addToDatabase(null)).start();
/*      */   }
/*      */   
/*      */   private void LinesCBMIActionPerformed(ActionEvent evt)
/*      */   {
/*  825 */     this.outputTBL.setShowHorizontalLines(this.LinesCBMI.isSelected());
/*  826 */     this.outputTBL.setShowVerticalLines(this.LinesCBMI.isSelected());
/*  827 */     this.InputTBL.setShowHorizontalLines(this.LinesCBMI.isSelected());
/*  828 */     this.InputTBL.setShowVerticalLines(this.LinesCBMI.isSelected());
/*  829 */     this.excelMoveDataTable.setShowHorizontalLines(this.LinesCBMI.isSelected());
/*  830 */     this.excelMoveDataTable.setShowVerticalLines(this.LinesCBMI.isSelected());
/*      */   }
/*      */   
/*      */   private void toExcelBTNActionPerformed(ActionEvent evt)
/*      */   {
/*  835 */     setSearching(true);
/*  836 */     new Thread(new toExcel(null)).start();
/*      */   }
/*      */   
/*      */ 
/*      */   private void SetDefaultUserBActionPerformed(ActionEvent evt) {}
/*      */   
/*      */ 
/*      */   private void selectKOJCDataActionPerformed(ActionEvent evt)
/*      */   {
/*  845 */     setAdding(true);
/*  846 */     new Thread(new SelectNewCenteryData(null)).start();
/*      */   }
/*      */   
/*      */   private void writeAllErrorBMActionPerformed(ActionEvent evt) {
/*  850 */     new Thread(new Runnable()
/*      */     {
/*      */       public void run()
/*      */       {
/*      */         try
/*      */         {
/*      */           
/*      */         }
/*      */         catch (IOException ex) {
/*  859 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/*      */     }).start();
/*      */   }
/*      */   
/*      */   private void SeriousErrorMNUBActionPerformed(ActionEvent evt) {
/*  866 */     new Thread(new Runnable()
/*      */     {
/*      */       public void run()
/*      */       {
/*      */         try
/*      */         {
/*      */           
/*      */         }
/*      */         catch (IOException ex) {
/*  875 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/*      */     }).start();
/*      */   }
/*      */   
/*      */   private void GeneralErrorMNUBActionPerformed(ActionEvent evt) {
/*  882 */     new Thread(new Runnable()
/*      */     {
/*      */       public void run()
/*      */       {
/*      */         try
/*      */         {
/*      */           
/*      */         }
/*      */         catch (IOException ex) {
/*  891 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/*      */     }).start();
/*      */   }
/*      */   
/*      */   private void MinorErrorMNUBActionPerformed(ActionEvent evt) {
/*  898 */     new Thread(new Runnable()
/*      */     {
/*      */       public void run()
/*      */       {
/*      */         try
/*      */         {
/*      */           
/*      */         }
/*      */         catch (IOException ex) {
/*  907 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/*      */     }).start();
/*      */   }
/*      */   
/*      */   private void NonErrorMNUBActionPerformed(ActionEvent evt) {
/*  914 */     new Thread(new Runnable()
/*      */     {
/*      */       public void run()
/*      */       {
/*      */         try
/*      */         {
/*      */           
/*      */         }
/*      */         catch (IOException ex) {
/*  923 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/*      */     }).start();
/*      */   }
/*      */   
/*      */   private void UnknownErrorMNUBActionPerformed(ActionEvent evt) {
/*  930 */     new Thread(new Runnable()
/*      */     {
/*      */       public void run()
/*      */       {
/*      */         try
/*      */         {
/*      */           
/*      */         }
/*      */         catch (IOException ex) {
/*  939 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/*      */     }).start();
/*      */   }
/*      */   
/*      */   private void UserErrorMNUBActionPerformed(ActionEvent evt) {
/*  946 */     new Thread(new Runnable()
/*      */     {
/*      */       public void run()
/*      */       {
/*      */         try
/*      */         {
/*      */           
/*      */         }
/*      */         catch (IOException ex) {
/*  955 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/*      */     }).start();
/*      */   }
/*      */   
/*      */   private void AllowButtonEditMNURActionPerformed(ActionEvent evt)
/*      */   {
/*  963 */     this.DeleteBTN.setEnabled(this.AllowButtonEditMNUR.isSelected());
/*  964 */     this.editBTN.setEnabled(this.AllowButtonEditMNUR.isSelected());
/*  965 */     this.editToDataBaseBTN.setEnabled(this.AllowButtonEditMNUR.isSelected());
/*  966 */     this.CleanDataBaseBTN.setEnabled(this.AllowButtonEditMNUR.isSelected());
/*  967 */     this.deleteCreatedDataMNUB.setEnabled(this.AllowButtonEditMNUR.isSelected());
/*  968 */     this.CreateNewUserB.setEnabled(this.AllowButtonEditMNUR.isSelected());
/*  969 */     this.DeleteUserB.setEnabled(this.AllowButtonEditMNUR.isEnabled());
/*  970 */     if (this.AllowButtonEditMNUR.isSelected())
/*      */     {
/*  972 */       JOptionPane.showMessageDialog(null, "Although you may have reenabled the buttons, You still need to be logged in a proper account in order to edit or delete items");
/*      */     }
/*      */   }
/*      */   
/*      */   private void deleteCreatedDataMNUBActionPerformed(ActionEvent evt)
/*      */   {
/*  978 */     setSearching(true);
/*  979 */     setAdding(true);
/*  980 */     new Thread(new deleteCreatedData(null)).start();
/*      */   }
/*      */   
/*      */   private void editToDataBaseBTNActionPerformed(ActionEvent evt)
/*      */   {
/*  985 */     setSearching(true);
/*  986 */     setAdding(true);
/*  987 */     new Thread(new updateToDatabase(null)).start();
/*      */   }
/*      */   
/*      */   private void GraphBTNActionPerformed(ActionEvent evt)
/*      */   {
/*  992 */     new Thread(new Runnable()
/*      */     {
/*      */       public void run() {
/*      */         try {
/*  996 */           if (Form.this.outputTBL.getRowCount() == 0)
/*      */           {
/*  998 */             throw new UserError("User Error", "You must search data before you can graph it");
/*      */           }
/* 1000 */           g = new Graph(Form.this.outputTBL);
/*      */         } catch (NumberFormatException ex) { Graph g;
/* 1002 */           JOptionPane.showMessageDialog(null, "Numbers Format Exception", ex.getMessage(), 2);
/* 1003 */           if (!Log.isInformed())
/*      */           {
/* 1005 */             Log.addGeneralError(ex);
/*      */           }
/*      */         } catch (UserError ex) {
/* 1008 */           JOptionPane.showMessageDialog(null, ex.getFullExplantion(), ex.getMessage(), 2);
/*      */         }
/*      */       }
/*      */     }).start();
/*      */   }
/*      */   
/*      */   private void StudentUserRActionPerformed(ActionEvent evt)
/*      */   {
/* 1016 */     this.TrustedStudentR.setSelected(false);
/* 1017 */     this.AdminR.setSelected(false);
/*      */   }
/*      */   
/*      */   private void TrustedStudentRActionPerformed(ActionEvent evt)
/*      */   {
/* 1022 */     this.StudentUserR.setSelected(false);
/* 1023 */     this.AdminR.setSelected(false);
/*      */   }
/*      */   
/*      */   private void AdminRActionPerformed(ActionEvent evt)
/*      */   {
/* 1028 */     this.TrustedStudentR.setSelected(false);
/* 1029 */     this.StudentUserR.setSelected(false);
/*      */   }
/*      */   
/*      */   private void CreateNewUserBActionPerformed(ActionEvent evt)
/*      */   {
/* 1034 */     new Thread(new Runnable()
/*      */     {
/*      */ 
/*      */       public void run()
/*      */       {
/* 1039 */         String pass = "";
/*      */         
/* 1041 */         char[] passHold = Form.this.passwordPWF.getPassword();
/* 1042 */         for (int i = 0; i < passHold.length; i++)
/*      */         {
/* 1044 */           pass = pass + passHold[i];
/*      */         }
/*      */         try {
/* 1047 */           if (Form.this.StudentUserR.isSelected())
/*      */           {
/* 1049 */             Form.this.conn.createNewUser(Form.this.userNameTXT.getText(), pass, 0);
/*      */           }
/* 1051 */           else if (Form.this.TrustedStudentR.isSelected())
/*      */           {
/* 1053 */             Form.this.conn.createNewUser(Form.this.userNameTXT.getText(), pass, 1);
/*      */           }
/* 1055 */           else if (Form.this.AdminR.isSelected())
/*      */           {
/* 1057 */             Form.this.conn.createNewUser(Form.this.userNameTXT.getText(), pass, 2);
/*      */           }
/*      */           else {
/* 1060 */             JOptionPane.showMessageDialog(null, "Please choose a ", "UserError", 0);
/*      */           }
/*      */         }
/*      */         catch (SQLException ex) {
/* 1064 */           if (Log.isInformed())
/*      */           {
/* 1066 */             Log.addSeriousError(ex);
/*      */           }
/* 1068 */           JOptionPane.showMessageDialog(null, ex.getMessage(), "SQL Error", 0);
/*      */         }
/*      */         catch (UserError ex)
/*      */         {
/* 1072 */           JOptionPane.showMessageDialog(null, ex.getFullExplantion(), ex.getMessage(), 0);
/*      */         }
/*      */       }
/*      */     }).start();
/*      */   }
/*      */   
/*      */   private void DeleteUserBActionPerformed(ActionEvent evt)
/*      */   {
/* 1080 */     new Thread(new Runnable()
/*      */     {
/*      */       public void run() {
/*      */         try {
/* 1084 */           Form.this.conn.deleteUser(Form.this.userNameTXT.getText());
/*      */         }
/*      */         catch (SQLException ex) {
/* 1087 */           if (Log.isInformed())
/*      */           {
/* 1089 */             Log.addSeriousError(ex);
/*      */           }
/* 1091 */           JOptionPane.showMessageDialog(null, ex.getMessage(), "SQL Error", 0);
/*      */         }
/*      */         catch (UserError ex) {
/* 1094 */           JOptionPane.showMessageDialog(null, ex.getFullExplantion(), ex.getMessage(), 0);
/*      */         }
/*      */       }
/*      */     }).start();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void main(String[] args)
/*      */   {
/*      */     try
/*      */     {
/* 1110 */       for (javax.swing.UIManager.LookAndFeelInfo info : ) {
/* 1111 */         if ("Nimbus".equals(info.getName())) {
/* 1112 */           javax.swing.UIManager.setLookAndFeel(info.getClassName());
/* 1113 */           break;
/*      */         }
/*      */       }
/*      */     } catch (ClassNotFoundException|InstantiationException|IllegalAccessException|javax.swing.UnsupportedLookAndFeelException ex) {
/* 1117 */       JOptionPane.showMessageDialog(null, ex.getMessage(), "error", 1);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1123 */     java.awt.EventQueue.invokeLater(new Runnable()
/*      */     {
/*      */ 
/*      */       public void run()
/*      */       {
/* 1128 */         Form frm = new Form();
/* 1129 */         frm.setVisible(true);
/*      */         try {
/* 1131 */           frm.setAlwaysOnTop(false);
/* 1132 */           frm.conn = new DatabaseConnector();
/* 1133 */           frm.CDG = new KOJCConnector();
/* 1134 */           frm.EC = new ExcelConverter();
/*      */         }
/*      */         catch (InstantiationException|ClassNotFoundException|IllegalAccessException ex) {
/* 1137 */           java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
/*      */         }
/*      */       }
/*      */     });
/*      */   }
/*      */   
/*      */ 
/*      */   private int[][] getDateFromInputTbl()
/*      */     throws NumberFormatException, NullPointerException
/*      */   {
/* 1147 */     int[][] ret = new int[3][this.InputTBLSize];
/* 1148 */     String[][] data = new String[3][this.InputTBLSize];
/*      */     
/* 1150 */     this.lastSetSize = this.InputTBLSize;
/* 1151 */     for (int i = 0; i < this.InputTBLSize; i++)
/*      */     {
/* 1153 */       String temp = (String)this.InputTBL.getValueAt(i, 0);
/*      */       
/* 1155 */       if (((temp == null) || (temp.isEmpty())) && ((this.InputTBL.getValueAt(i, 2) == null) || (((String)this.InputTBL.getValueAt(i, 2)).isEmpty())))
/*      */       {
/* 1157 */         if (i == 0)
/*      */         {
/* 1159 */           throw new NumberFormatException("Date Format Must Be (mm/dd/yyyy)");
/*      */         }
/* 1161 */         this.lastSetSize = i;
/* 1162 */         break;
/*      */       }
/* 1164 */       if (((temp == null) || (temp.isEmpty())) && ((this.InputTBL.getValueAt(i, 2) != null) || (!((String)this.InputTBL.getValueAt(i, 2)).isEmpty())))
/*      */       {
/* 1166 */         if (i == 0)
/*      */         {
/* 1168 */           throw new NumberFormatException("Date Format Must Be (mm/dd/yyyy)");
/*      */         }
/* 1170 */         ret[0][i] = ret[0][(i - 1)];
/* 1171 */         ret[1][i] = ret[1][(i - 1)];
/* 1172 */         ret[2][i] = ret[2][(i - 1)];
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1177 */         data[0][i] = "";
/* 1178 */         data[1][i] = "";
/* 1179 */         data[2][i] = "";
/* 1180 */         int h = 0;
/* 1181 */         for (int j = 0; j < temp.length(); j++)
/*      */         {
/* 1183 */           if (temp.charAt(j) == '/')
/*      */           {
/* 1185 */             h++;
/* 1186 */             while (temp.charAt(j) == '/')
/*      */             {
/* 1188 */               j++;
/*      */             }
/*      */           }
/*      */           
/* 1192 */           int tmp309_307 = i; String[] tmp309_306 = data[h];tmp309_306[tmp309_307] = (tmp309_306[tmp309_307] + temp.charAt(j));
/*      */         }
/* 1194 */         if (h != 2)
/*      */         {
/* 1196 */           throw new NumberFormatException("Date Format Must Be (mm/dd/yyyy)");
/*      */         }
/* 1198 */         for (j = 0; j < 3; j++)
/*      */         {
/* 1200 */           ret[j][i] = Integer.parseInt(data[j][i]);
/*      */         }
/* 1202 */         if ((ret[0][i] <= -1) || (ret[0][i] >= 12) || (ret[1][i] <= -1) || (ret[1][i] >= 32) || (ret[2][i] <= 1970) || (ret[0][i] >= 2112))
/*      */         {
/* 1204 */           throw new NumberFormatException("Date Format Must Be (mm/dd/yyyy)");
/*      */         }
/*      */       }
/*      */     }
/* 1208 */     return ret;
/*      */   }
/*      */   
/*      */   private int[][] getTimeOfDayFromInputTBL() throws NumberFormatException, NullPointerException
/*      */   {
/* 1213 */     int[][] ret = new int[2][this.lastSetSize];
/*      */     
/* 1215 */     String[][] data = new String[2][this.lastSetSize];
/* 1216 */     for (int i = 0; i < this.lastSetSize; i++) {
/* 1217 */       String temp = (String)this.InputTBL.getValueAt(i, 1);
/*      */       
/* 1219 */       if (temp == null)
/*      */       {
/* 1221 */         throw new NumberFormatException("Date Format Must Be (mm/dd/yyyy)");
/*      */       }
/*      */       
/*      */ 
/* 1225 */       data[0][i] = "";
/* 1226 */       data[1][i] = "";
/* 1227 */       int h = 0;
/* 1228 */       for (int j = 0; j < temp.length(); j++)
/*      */       {
/* 1230 */         if (temp.charAt(j) == ':')
/*      */         {
/* 1232 */           h++;
/* 1233 */           while (temp.charAt(j) == ':')
/*      */           {
/* 1235 */             j++;
/*      */           }
/*      */         }
/*      */         
/* 1239 */         int tmp135_134 = i; String[] tmp135_133 = data[h];tmp135_133[tmp135_134] = (tmp135_133[tmp135_134] + temp.charAt(j));
/*      */       }
/* 1241 */       if (h != 1)
/*      */       {
/* 1243 */         throw new NumberFormatException("Date Format Must Be (mm/dd/yyyy)");
/*      */       }
/* 1245 */       for (j = 0; j < 2; j++)
/*      */       {
/* 1247 */         ret[j][i] = Integer.parseInt(data[j][i]);
/*      */       }
/* 1249 */       if ((ret[0][i] >= 24) || (ret[0][i] <= -1) || (ret[1][i] >= 60) || (ret[1][i] <= -1))
/*      */       {
/* 1251 */         throw new NumberFormatException("Date Format Must Be (mm/dd/yyyy)");
/*      */       }
/*      */     }
/*      */     
/* 1255 */     return ret;
/*      */   }
/*      */   
/*      */   private double[] getTempFromInputTBL() throws NumberFormatException, NullPointerException {
/* 1259 */     String[] temp = new String[this.lastSetSize];
/*      */     
/* 1261 */     double[] ret = new double[this.lastSetSize];
/* 1262 */     for (int i = 0; i < this.lastSetSize; i++)
/*      */     {
/* 1264 */       temp[i] = ((String)this.InputTBL.getValueAt(i, 2));
/* 1265 */       ret[i] = Double.parseDouble(temp[i]);
/*      */     }
/* 1267 */     return ret;
/*      */   }
/*      */   
/*      */   private int[] getDateRange() throws NumberFormatException {
/* 1271 */     int[] date = new int[10];
/* 1272 */     String[] data = new String[10];
/* 1273 */     int h = 0;
/* 1274 */     String temp = this.outputStartDateTXT.getText();
/* 1275 */     for (int j = 0; j < 10; j++)
/*      */     {
/* 1277 */       data[j] = "";
/*      */     }
/* 1279 */     for (j = 0; j < temp.length(); j++)
/*      */     {
/* 1281 */       if (temp.charAt(j) == '/')
/*      */       {
/* 1283 */         h++;
/* 1284 */         while (temp.charAt(j) == '/')
/*      */         {
/* 1286 */           j++;
/*      */         }
/*      */       }
/* 1289 */       int tmp97_95 = h; String[] tmp97_94 = data;tmp97_94[tmp97_95] = (tmp97_94[tmp97_95] + temp.charAt(j));
/*      */     }
/* 1291 */     h++;
/* 1292 */     temp = this.outputStartTimeTXT.getText();
/* 1293 */     for (j = 0; j < temp.length(); j++)
/*      */     {
/* 1295 */       if (temp.charAt(j) == ':')
/*      */       {
/* 1297 */         h++;
/* 1298 */         while (temp.charAt(j) == ':')
/*      */         {
/* 1300 */           j++;
/*      */         }
/*      */       }
/* 1303 */       int tmp185_183 = h; String[] tmp185_182 = data;tmp185_182[tmp185_183] = (tmp185_182[tmp185_183] + temp.charAt(j));
/*      */     }
/* 1305 */     h++;
/* 1306 */     temp = this.outputEndDateTXT.getText();
/* 1307 */     for (j = 0; j < temp.length(); j++)
/*      */     {
/* 1309 */       if (temp.charAt(j) == '/')
/*      */       {
/* 1311 */         h++;
/* 1312 */         while (temp.charAt(j) == '/')
/*      */         {
/* 1314 */           j++;
/*      */         }
/*      */       }
/* 1317 */       int tmp273_271 = h; String[] tmp273_270 = data;tmp273_270[tmp273_271] = (tmp273_270[tmp273_271] + temp.charAt(j));
/*      */     }
/* 1319 */     h++;
/* 1320 */     temp = this.outputEndTimeTXT.getText();
/* 1321 */     for (j = 0; j < temp.length(); j++)
/*      */     {
/* 1323 */       if (temp.charAt(j) == ':')
/*      */       {
/* 1325 */         h++;
/* 1326 */         while (temp.charAt(j) == ':')
/*      */         {
/* 1328 */           j++;
/*      */         }
/*      */       }
/* 1331 */       int tmp361_359 = h; String[] tmp361_358 = data;tmp361_358[tmp361_359] = (tmp361_358[tmp361_359] + temp.charAt(j));
/*      */     }
/* 1333 */     if (h != 9)
/*      */     {
/* 1335 */       throw new NumberFormatException("Data Must Be In MM/DD/YYYY & HH:MM Format");
/*      */     }
/* 1337 */     for (j = 0; j < 10; j++)
/*      */     {
/* 1339 */       date[j] = Integer.parseInt(data[j]);
/*      */     }
/* 1341 */     return date;
/*      */   }
/*      */   
/*      */   public int[] getDateRangeExcel() throws NumberFormatException {
/* 1345 */     int[] date = new int[10];
/* 1346 */     String[] data = new String[10];
/* 1347 */     int h = 0;
/* 1348 */     String temp = this.excelStartDateTXT.getText();
/* 1349 */     for (int j = 0; j < 10; j++)
/*      */     {
/* 1351 */       data[j] = "";
/*      */     }
/* 1353 */     for (j = 0; j < temp.length(); j++)
/*      */     {
/* 1355 */       if (temp.charAt(j) == '/')
/*      */       {
/* 1357 */         h++;
/* 1358 */         while (temp.charAt(j) == '/')
/*      */         {
/* 1360 */           j++;
/*      */         }
/*      */       }
/* 1363 */       int tmp97_95 = h; String[] tmp97_94 = data;tmp97_94[tmp97_95] = (tmp97_94[tmp97_95] + temp.charAt(j));
/*      */     }
/* 1365 */     h++;
/* 1366 */     temp = this.excelStartTimeTXT.getText();
/* 1367 */     for (j = 0; j < temp.length(); j++)
/*      */     {
/* 1369 */       if (temp.charAt(j) == ':')
/*      */       {
/* 1371 */         h++;
/* 1372 */         while (temp.charAt(j) == ':')
/*      */         {
/* 1374 */           j++;
/*      */         }
/*      */       }
/* 1377 */       int tmp185_183 = h; String[] tmp185_182 = data;tmp185_182[tmp185_183] = (tmp185_182[tmp185_183] + temp.charAt(j));
/*      */     }
/* 1379 */     h++;
/* 1380 */     temp = this.excelEndDateTXT.getText();
/* 1381 */     for (j = 0; j < temp.length(); j++)
/*      */     {
/* 1383 */       if (temp.charAt(j) == '/')
/*      */       {
/* 1385 */         h++;
/* 1386 */         while (temp.charAt(j) == '/')
/*      */         {
/* 1388 */           j++;
/*      */         }
/*      */       }
/* 1391 */       int tmp273_271 = h; String[] tmp273_270 = data;tmp273_270[tmp273_271] = (tmp273_270[tmp273_271] + temp.charAt(j));
/*      */     }
/* 1393 */     h++;
/* 1394 */     temp = this.excelEndTimeTXT.getText();
/* 1395 */     for (j = 0; j < temp.length(); j++)
/*      */     {
/* 1397 */       if (temp.charAt(j) == ':')
/*      */       {
/* 1399 */         h++;
/* 1400 */         while (temp.charAt(j) == ':')
/*      */         {
/* 1402 */           j++;
/*      */         }
/*      */       }
/* 1405 */       int tmp361_359 = h; String[] tmp361_358 = data;tmp361_358[tmp361_359] = (tmp361_358[tmp361_359] + temp.charAt(j));
/*      */     }
/* 1407 */     if (h != 9) {
/* 1408 */       throw new NumberFormatException("Data Must Be In MM/DD/YYYY & HH:MM format");
/*      */     }
/* 1410 */     for (j = 0; j < 10; j++)
/*      */     {
/* 1412 */       date[j] = Integer.parseInt(data[j]);
/*      */     }
/* 1414 */     return date;
/*      */   }
/*      */   
/*      */ 
/*      */   private class NormalSearch
/*      */     implements Runnable
/*      */   {
/*      */     private NormalSearch() {}
/*      */     
/*      */ 
/*      */     public void run()
/*      */     {
/*      */       try
/*      */       {
/* 1428 */         boolean created = Form.this.SearchCreatedRAD.isSelected();
/* 1429 */         int[] data = Form.this.getDateRange();
/* 1430 */         Object[][] tbl = Form.this.conn.searchDataSet(data[2], data[0], data[1], data[3], data[4], data[7], data[5], data[6], data[8], data[9], created);
/* 1431 */         javax.swing.table.DefaultTableModel Tf = new javax.swing.table.DefaultTableModel(tbl, Form.INPUTLBLS);
/* 1432 */         Form.this.outputTBL.setModel(Tf);
/* 1433 */         Form.this.outputTBL.setShowHorizontalLines(Form.this.LinesCBMI.isSelected());
/* 1434 */         Form.this.outputTBL.setShowVerticalLines(Form.this.LinesCBMI.isSelected());
/*      */       }
/*      */       catch (SQLException ex) {
/* 1437 */         JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Error", 0);
/*      */         
/* 1439 */         if (!Log.isInformed()) {
/* 1440 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/*      */       catch (NumberFormatException ex) {
/* 1444 */         JOptionPane.showMessageDialog(null, ex.getMessage(), "Numbers Format", 0);
/*      */         
/* 1446 */         if (!Log.isInformed()) {
/* 1447 */           Log.addMinorError(ex);
/*      */         }
/*      */       }
/*      */       catch (RuntimeException ex) {
/* 1451 */         JOptionPane.showMessageDialog(null, ex.getMessage(), "Run Time Exception", 1);
/*      */         
/* 1453 */         if (!Log.isInformed()) {
/* 1454 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/*      */       catch (UserError ex) {
/* 1458 */         JOptionPane.showMessageDialog(null, ex.getFullExplantion(), ex.getMessage(), 0);
/*      */       }
/*      */       catch (OutOfMemoryError ex)
/*      */       {
/* 1462 */         JOptionPane.showMessageDialog(null, "Ran Out Of Memory Error, Use Smaller Sets");
/*      */       }
/*      */       catch (Exception ex)
/*      */       {
/* 1466 */         JOptionPane.showMessageDialog(null, "an unknown error has occured");
/* 1467 */         if (!Log.isInformed())
/*      */         {
/* 1469 */           Log.addUnknownError(ex);
/*      */         }
/*      */       }
/* 1472 */       Form.this.setSearching(false);
/*      */     }
/*      */   }
/*      */   
/*      */   public class NormalDelete implements Runnable
/*      */   {
/*      */     public NormalDelete() {}
/*      */     
/*      */     public void run() {
/*      */       try {
/* 1482 */         int[] data = Form.this.getDateRange();
/* 1483 */         Form.this.conn.deleteDataSet(data[2], data[0], data[1], data[3], data[4], data[7], data[5], data[6], data[8], data[9]);
/*      */       }
/*      */       catch (SQLException ex) {
/* 1486 */         JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Error", 0);
/* 1487 */         if (!Log.isInformed()) {
/* 1488 */           Log.addSeriousError(ex);
/*      */         }
/* 1490 */         java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
/*      */       }
/*      */       catch (RuntimeException ex) {
/* 1493 */         JOptionPane.showMessageDialog(null, ex.getMessage(), "Numbers Format", 0);
/* 1494 */         if (!Log.isInformed())
/*      */         {
/* 1496 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/*      */       catch (UserError ex)
/*      */       {
/* 1501 */         JOptionPane.showMessageDialog(null, ex.getFullExplantion(), ex.getMessage(), 0);
/*      */       }
/*      */       catch (OutOfMemoryError ex)
/*      */       {
/* 1505 */         JOptionPane.showMessageDialog(null, "Ran Out Of Memory Error, Use Smaller Sets");
/*      */       }
/*      */       catch (Exception ex)
/*      */       {
/* 1509 */         JOptionPane.showMessageDialog(null, "An Unknown Error Has Occured");
/* 1510 */         if (!Log.isInformed())
/*      */         {
/* 1512 */           Log.addUnknownError(ex);
/*      */         }
/*      */       }
/* 1515 */       Form.this.setSearching(false);
/* 1516 */       Form.this.setAdding(false);
/*      */     }
/*      */   }
/*      */   
/*      */   private class NormalEdit implements Runnable {
/*      */     private NormalEdit() {}
/*      */     
/* 1523 */     public void run() { int i = 0;int j = 0;
/*      */       
/*      */ 
/*      */       try
/*      */       {
/* 1528 */         int[][] date = Form.this.getDateFromInputTbl();
/* 1529 */         int[][] TOD = Form.this.getTimeOfDayFromInputTBL();
/* 1530 */         double[] temp = Form.this.getTempFromInputTBL();
/* 1531 */         Form.this.conn.updateDataSet(date[2], date[0], date[1], TOD[0], TOD[1], temp);
/*      */       }
/*      */       catch (SQLException ex) {
/* 1534 */         JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Error", 0);
/* 1535 */         if (!Log.isInformed()) {
/* 1536 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/*      */       catch (NumberFormatException ex) {
/* 1540 */         JOptionPane.showMessageDialog(null, ex.getMessage(), "Numbers Format", 0);
/* 1541 */         if (!Log.isInformed()) {
/* 1542 */           Log.addMinorError(ex);
/*      */         }
/*      */       }
/*      */       catch (NullPointerException ex) {
/* 1546 */         JOptionPane.showMessageDialog(null, "Data Missing or not found, please insert data", "Null pointer ", 0);
/* 1547 */         if (!Log.isInformed()) {
/* 1548 */           Log.addGeneralError(ex);
/*      */         }
/*      */       }
/*      */       catch (UserError ex) {
/* 1552 */         JOptionPane.showMessageDialog(null, ex.getFullExplantion(), ex.getMessage(), 0);
/*      */       }
/*      */       catch (OutOfMemoryError ex)
/*      */       {
/* 1556 */         JOptionPane.showMessageDialog(null, "Ran Out Of Memory Error, Use Smaller Sets");
/*      */       }
/*      */       catch (Exception ex) {
/* 1559 */         JOptionPane.showMessageDialog(null, "An unknown Error has Occured");
/* 1560 */         if (!Log.isInformed())
/*      */         {
/* 1562 */           Log.addUnknownError(ex);
/*      */         }
/*      */       }
/* 1565 */       Form.this.setSearching(false);
/* 1566 */       Form.this.setAdding(false);
/*      */     }
/*      */   }
/*      */   
/*      */   private class NormalAdd implements Runnable
/*      */   {
/*      */     private NormalAdd() {}
/*      */     
/*      */     public void run()
/*      */     {
/*      */       try {
/* 1577 */         if (Form.this.InputTBL.getCellEditor() != null)
/*      */         {
/* 1579 */           Form.this.InputTBL.getCellEditor().stopCellEditing();
/*      */         }
/* 1581 */         int[][] date = Form.this.getDateFromInputTbl();
/* 1582 */         int[][] TOD = Form.this.getTimeOfDayFromInputTBL();
/* 1583 */         double[] temp = Form.this.getTempFromInputTBL();
/* 1584 */         Form.this.conn.addDataSet(date[2], date[0], date[1], TOD[0], TOD[1], temp, false, !Form.this.GiveDuplicatesCBMI.isSelected());
/*      */       }
/*      */       catch (SQLException ex) {
/* 1587 */         JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Error", 0);
/* 1588 */         if (!Log.isInformed()) {
/* 1589 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/*      */       catch (NumberFormatException ex)
/*      */       {
/* 1594 */         JOptionPane.showMessageDialog(null, ex.getMessage(), "Numbers Format", 0);
/* 1595 */         if (!Log.isInformed())
/*      */         {
/* 1597 */           Log.addMinorError(ex);
/*      */         }
/*      */       }
/*      */       catch (NullPointerException ex) {
/* 1601 */         JOptionPane.showMessageDialog(null, "Please Input", "DataMissing", 0);
/* 1602 */         if (!Log.isInformed())
/*      */         {
/* 1604 */           Log.addGeneralError(ex);
/*      */         }
/*      */       }
/*      */       catch (RuntimeException ex) {
/* 1608 */         JOptionPane.showMessageDialog(null, ex.getMessage(), "Runtime Exception", 1);
/* 1609 */         if (!Log.isInformed())
/*      */         {
/* 1611 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/*      */       catch (UserError ex)
/*      */       {
/* 1616 */         JOptionPane.showMessageDialog(null, ex.getFullExplantion(), ex.getMessage(), 0);
/*      */       }
/*      */       catch (OutOfMemoryError ex)
/*      */       {
/* 1620 */         JOptionPane.showMessageDialog(null, "Ran Out Of Memory Error, Use Smaller Sets");
/*      */       }
/*      */       catch (Exception ex)
/*      */       {
/* 1624 */         JOptionPane.showMessageDialog(null, "An unknown Error has Occured");
/* 1625 */         if (!Log.isInformed())
/*      */         {
/* 1627 */           Log.addUnknownError(ex);
/*      */         }
/*      */       }
/* 1630 */       Form.this.setAdding(false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private class toExcel
/*      */     implements Runnable
/*      */   {
/*      */     private toExcel() {}
/*      */     
/*      */ 
/*      */     public void run()
/*      */     {
/* 1644 */       javax.swing.JFileChooser jfc = new javax.swing.JFileChooser("directoryChanged");
/*      */       try
/*      */       {
/* 1647 */         int choose = jfc.showSaveDialog(null);
/* 1648 */         if (choose != 0)
/*      */         {
/* 1650 */           throw new UserError("closed", "Canceled By User");
/*      */         }
/* 1652 */         String save = jfc.getSelectedFile().getPath();
/* 1653 */         boolean created = Form.this.ExcelDoNotMoveCreatedData.isSelected();
/* 1654 */         int[] data = Form.this.getDateRangeExcel();
/* 1655 */         Object[][] tbl = Form.this.conn.searchDataSet(data[2], data[0], data[1], data[3], data[4], data[7], data[5], data[6], data[8], data[9], created);
/* 1656 */         javax.swing.table.DefaultTableModel Tf = new javax.swing.table.DefaultTableModel(tbl, Form.INPUTLBLS);
/* 1657 */         Form.this.excelMoveDataTable.setModel(Tf);
/* 1658 */         Form.this.excelMoveDataTable.setShowHorizontalLines(Form.this.LinesCBMI.isSelected());
/* 1659 */         Form.this.excelMoveDataTable.setShowVerticalLines(Form.this.LinesCBMI.isSelected());
/* 1660 */         Form.this.EC.createDoc(tbl, save);
/*      */       }
/*      */       catch (SQLException ex)
/*      */       {
/* 1664 */         JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Error", 0);
/* 1665 */         if (!Log.isInformed()) {
/* 1666 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/*      */       catch (NumberFormatException ex) {
/* 1670 */         JOptionPane.showMessageDialog(null, ex.getMessage(), "Numbers Format", 0);
/* 1671 */         if (!Log.isInformed()) {
/* 1672 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/*      */       catch (UserError ex) {
/* 1676 */         JOptionPane.showMessageDialog(null, ex.getFullExplantion(), ex.getMessage(), 0);
/*      */       }
/*      */       catch (IOException ex)
/*      */       {
/* 1680 */         JOptionPane.showMessageDialog(null, ex.getMessage(), "File Exception", 0);
/* 1681 */         if (!Log.isInformed()) {
/* 1682 */           Log.addSeriousError(ex);
/*      */         }
/*      */         
/*      */       }
/*      */       catch (OutOfMemoryError ex)
/*      */       {
/* 1688 */         JOptionPane.showMessageDialog(null, "Ran Out Of Memory Error, Use Smaller Sets");
/*      */       }
/*      */       catch (Exception ex)
/*      */       {
/* 1692 */         JOptionPane.showMessageDialog(null, "An unknown Error has Occured");
/* 1693 */         if (!Log.isInformed())
/*      */         {
/* 1695 */           Log.addUnknownError(ex);
/*      */         }
/*      */       }
/* 1698 */       Form.this.setSearching(false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private class addToDatabase
/*      */     implements Runnable
/*      */   {
/*      */     private addToDatabase() {}
/*      */     
/*      */ 
/*      */     public void run()
/*      */     {
/* 1712 */       javax.swing.JFileChooser JFC = new javax.swing.JFileChooser();
/* 1713 */       int choose = JFC.showOpenDialog(null);
/* 1714 */       if (choose == JFC.getApproveButtonMnemonic())
/*      */       {
/*      */         try
/*      */         {
/* 1718 */           Object[][] item = Form.this.EC.getDoc(JFC.getSelectedFile());
/* 1719 */           int[][] date = new int[5][item[0].length];
/* 1720 */           double[] temp = new double[item[5].length];
/* 1721 */           Object[][] Tbl = new Object[item[5].length][3];
/* 1722 */           for (int i = 0; i < item[0].length; i++)
/*      */           {
/* 1724 */             for (int j = 0; j < 5; j++)
/*      */             {
/* 1726 */               date[j][i] = ((Integer)item[j][i]).intValue();
/*      */             }
/* 1728 */             temp[i] = ((Double)item[5][i]).doubleValue();
/* 1729 */             Tbl[i][2] = Double.valueOf(temp[i]);
/*      */           }
/* 1731 */           Form.this.conn.addDataSet(date[0], date[1], date[2], date[3], date[4], temp, false, !Form.this.GiveDuplicatesCBMI.isSelected());
/* 1732 */           for (i = 0; i < Tbl.length; i++)
/*      */           {
/* 1734 */             Tbl[i][0] = ("" + date[1][i] + "/" + date[2][i] + "/" + date[0][i]);
/* 1735 */             Tbl[i][1] = ("" + date[3][i] + ":" + date[4][i]);
/*      */           }
/* 1737 */           javax.swing.table.DefaultTableModel Tf = new javax.swing.table.DefaultTableModel(Tbl, Form.INPUTLBLS);
/* 1738 */           Form.this.excelMoveDataTable.setModel(Tf);
/* 1739 */           Form.this.excelMoveDataTable.setShowHorizontalLines(Form.this.LinesCBMI.isSelected());
/* 1740 */           Form.this.excelMoveDataTable.setShowVerticalLines(Form.this.LinesCBMI.isSelected());
/*      */         }
/*      */         catch (IOException ex)
/*      */         {
/* 1744 */           JOptionPane.showMessageDialog(null, ex.getMessage(), "File In/out Exception", 0);
/* 1745 */           if (!Log.isInformed()) {
/* 1746 */             Log.addSeriousError(ex);
/*      */           }
/*      */         }
/*      */         catch (NumberFormatException ex)
/*      */         {
/* 1751 */           JOptionPane.showMessageDialog(null, ex.getMessage(), "Numbers Format Exception", 0);
/* 1752 */           if (!Log.isInformed()) {
/* 1753 */             Log.addMinorError(ex);
/*      */           }
/*      */         }
/*      */         catch (SQLException ex)
/*      */         {
/* 1758 */           JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Error", 0);
/* 1759 */           if (!Log.isInformed()) {
/* 1760 */             Log.addSeriousError(ex);
/*      */           }
/*      */         }
/*      */         catch (OutOfMemoryError ex)
/*      */         {
/* 1765 */           JOptionPane.showMessageDialog(null, "Ran Out Of Memory Error, Use Smaller Sets");
/*      */         }
/*      */         catch (UserError ex) {
/* 1768 */           JOptionPane.showMessageDialog(null, ex.getFullExplantion(), ex.getMessage(), 0);
/*      */         }
/*      */         catch (org.apache.poi.poifs.filesystem.OfficeXmlFileException ex) {
/* 1771 */           JOptionPane.showMessageDialog(null, ex.getMessage(), "excel converter error", 0);
/* 1772 */           Log.addGeneralError(ex);
/*      */         }
/*      */         catch (Exception ex)
/*      */         {
/* 1776 */           JOptionPane.showMessageDialog(null, "An unknown Error Has Occured");
/* 1777 */           if (!Log.isInformed())
/*      */           {
/* 1779 */             Log.addUnknownError(ex);
/*      */           }
/*      */         }
/*      */       }
/* 1783 */       Form.this.setAdding(false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private class updateToDatabase
/*      */     implements Runnable
/*      */   {
/*      */     private updateToDatabase() {}
/*      */     
/*      */ 
/*      */     public void run()
/*      */     {
/* 1797 */       javax.swing.JFileChooser JFC = new javax.swing.JFileChooser();
/* 1798 */       int choose = JFC.showOpenDialog(null);
/* 1799 */       if (choose == JFC.getApproveButtonMnemonic())
/*      */       {
/*      */         try
/*      */         {
/* 1803 */           Object[][] item = Form.this.EC.getDoc(JFC.getSelectedFile());
/* 1804 */           int[][] date = new int[5][item[0].length];
/* 1805 */           double[] temp = new double[item[5].length];
/* 1806 */           Object[][] Tbl = new Object[item[5].length][3];
/* 1807 */           for (int i = 0; i < item[0].length; i++)
/*      */           {
/* 1809 */             for (int j = 0; j < 5; j++)
/*      */             {
/* 1811 */               date[j][i] = ((Integer)item[j][i]).intValue();
/*      */             }
/* 1813 */             temp[i] = ((Double)item[5][i]).doubleValue();
/* 1814 */             Tbl[i][2] = Double.valueOf(temp[i]);
/*      */           }
/* 1816 */           Form.this.conn.updateDataSet(date[0], date[1], date[2], date[3], date[4], temp);
/* 1817 */           for (i = 0; i < Tbl.length; i++)
/*      */           {
/* 1819 */             Tbl[i][0] = ("" + date[1][i] + "/" + date[2][i] + "/" + date[0][i]);
/* 1820 */             Tbl[i][1] = ("" + date[3][i] + ":" + date[4][i]);
/*      */           }
/* 1822 */           javax.swing.table.DefaultTableModel Tf = new javax.swing.table.DefaultTableModel(Tbl, Form.INPUTLBLS);
/* 1823 */           Form.this.excelMoveDataTable.setModel(Tf);
/* 1824 */           Form.this.excelMoveDataTable.setShowHorizontalLines(Form.this.LinesCBMI.isSelected());
/* 1825 */           Form.this.excelMoveDataTable.setShowVerticalLines(Form.this.LinesCBMI.isSelected());
/*      */         }
/*      */         catch (IOException ex)
/*      */         {
/* 1829 */           JOptionPane.showMessageDialog(null, ex.getMessage(), "File In/out Exception", 0);
/* 1830 */           if (!Log.isInformed()) {
/* 1831 */             Log.addSeriousError(ex);
/*      */           }
/*      */         }
/*      */         catch (NumberFormatException ex)
/*      */         {
/* 1836 */           JOptionPane.showMessageDialog(null, ex.getMessage(), "Numbers Format Exception", 0);
/* 1837 */           if (!Log.isInformed()) {
/* 1838 */             Log.addMinorError(ex);
/*      */           }
/*      */         }
/*      */         catch (SQLException ex)
/*      */         {
/* 1843 */           JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Error", 0);
/* 1844 */           if (!Log.isInformed()) {
/* 1845 */             Log.addSeriousError(ex);
/*      */           }
/*      */         }
/*      */         catch (UserError ex) {
/* 1849 */           JOptionPane.showMessageDialog(null, ex.getFullExplantion(), ex.getMessage(), 0);
/*      */         }
/*      */         catch (OutOfMemoryError ex)
/*      */         {
/* 1853 */           JOptionPane.showMessageDialog(null, "Ran Out Of Memory Error, Use Smaller Sets");
/*      */         }
/*      */         catch (Exception ex)
/*      */         {
/* 1857 */           JOptionPane.showMessageDialog(null, "An Unknown Error has Occured");
/* 1858 */           if (!Log.isInformed())
/*      */           {
/* 1860 */             Log.addUnknownError(ex);
/*      */           }
/*      */         }
/*      */       }
/* 1864 */       Form.this.setSearching(false);
/* 1865 */       Form.this.setAdding(false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private class SelectNewCenteryData
/*      */     implements Runnable
/*      */   {
/*      */     private SelectNewCenteryData() {}
/*      */     
/*      */ 
/*      */     public void run()
/*      */     {
/*      */       try
/*      */       {
/* 1881 */         int[] data = Form.this.getDateRangeExcel();
/* 1882 */         Object[][] temp = Form.this.CDG.getData(data[2], data[0], data[1], data[3], data[4], data[7], data[5], data[6], data[8], data[9]);
/* 1883 */         int[][] toDataBase = new int[5][temp.length];
/* 1884 */         double[] tempToDataBase = new double[temp.length];
/* 1885 */         Object[][] tbl = new Object[temp.length][3];
/* 1886 */         for (int i = 0; i < temp.length; i++)
/*      */         {
/* 1888 */           toDataBase[0][i] = ((Integer)temp[i][0]).intValue();
/* 1889 */           toDataBase[1][i] = ((Integer)temp[i][1]).intValue();
/* 1890 */           toDataBase[2][i] = ((Integer)temp[i][2]).intValue();
/* 1891 */           toDataBase[3][i] = ((Integer)temp[i][3]).intValue();
/* 1892 */           toDataBase[4][i] = ((Integer)temp[i][4]).intValue();
/* 1893 */           tempToDataBase[i] = ((Double)temp[i][5]).doubleValue();
/* 1894 */           tbl[i][0] = ("" + ((Integer)temp[i][1]).intValue() + "/" + ((Integer)temp[i][2]).intValue() + "/" + ((Integer)temp[i][0]).intValue());
/* 1895 */           tbl[i][1] = ("" + ((Integer)temp[i][3]).intValue() + ":" + ((Integer)temp[i][4]).intValue());
/* 1896 */           tbl[i][2] = temp[i][5];
/*      */         }
/* 1898 */         Form.this.conn.addDataSet(toDataBase[0], toDataBase[1], toDataBase[2], toDataBase[3], toDataBase[4], tempToDataBase, true, !Form.this.GiveDuplicatesCBMI.isSelected());
/* 1899 */         javax.swing.table.DefaultTableModel Tf = new javax.swing.table.DefaultTableModel(tbl, Form.INPUTLBLS);
/* 1900 */         Form.this.excelMoveDataTable.setModel(Tf);
/* 1901 */         Form.this.excelMoveDataTable.setShowHorizontalLines(Form.this.LinesCBMI.isSelected());
/* 1902 */         Form.this.excelMoveDataTable.setShowVerticalLines(Form.this.LinesCBMI.isSelected());
/*      */ 
/*      */       }
/*      */       catch (IOException ex)
/*      */       {
/* 1907 */         JOptionPane.showMessageDialog(null, ex.getMessage(), "IOException", 0);
/* 1908 */         if (!Log.isInformed()) {
/* 1909 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/*      */       catch (NumberFormatException ex)
/*      */       {
/* 1914 */         JOptionPane.showMessageDialog(null, ex.getMessage(), "Numbers Format Exception", 0);
/* 1915 */         if (!Log.isInformed()) {
/* 1916 */           Log.addMinorError(ex);
/*      */         }
/*      */       }
/*      */       catch (SQLException ex)
/*      */       {
/* 1921 */         JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection", 0);
/* 1922 */         if (!Log.isInformed()) {
/* 1923 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/*      */       catch (UserError ex)
/*      */       {
/* 1928 */         JOptionPane.showMessageDialog(null, ex.getFullExplantion(), ex.getMessage(), 0);
/*      */       }
/*      */       catch (OutOfMemoryError ex) {
/* 1931 */         JOptionPane.showMessageDialog(null, "Ran Out Of Memory Error, Use Smaller Sets");
/*      */       }
/*      */       catch (Exception ex)
/*      */       {
/* 1935 */         JOptionPane.showMessageDialog(null, ex.getMessage());
/* 1936 */         if (!Log.isInformed())
/*      */         {
/* 1938 */           Log.addUnknownError(ex);
/*      */         }
/*      */       }
/* 1941 */       Form.this.setAdding(false);
/*      */     }
/*      */   }
/*      */   
/*      */   private class deleteCreatedData implements Runnable {
/*      */     private deleteCreatedData() {}
/*      */     
/*      */     public void run() {
/* 1949 */       try { Form.this.conn.deleteCreatedData();
/*      */       } catch (SQLException ex) {
/* 1951 */         JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection", 0);
/* 1952 */         if (!Log.isInformed()) {
/* 1953 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/* 1956 */       Form.this.setSearching(false);
/* 1957 */       Form.this.setAdding(false);
/*      */     }
/*      */   }
/*      */   
/*      */   private class CleanDataBase implements Runnable {
/*      */     private CleanDataBase() {}
/*      */     
/*      */     public void run() {
/* 1965 */       try { Form.this.conn.cleanDataBase();
/*      */       } catch (SQLException ex) {
/* 1967 */         JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection", 0);
/* 1968 */         if (!Log.isInformed()) {
/* 1969 */           Log.addSeriousError(ex);
/*      */         }
/*      */       }
/* 1972 */       Form.this.setAdding(false);
/* 1973 */       Form.this.setSearching(false);
/*      */     }
/*      */   }
/*      */   
/*      */   private class connect implements Runnable {
/*      */     private connect() {}
/*      */     
/* 1980 */     public void run() { String pass = "";
/*      */       
/*      */ 
/* 1983 */       String user = Form.this.userNameTXT.getText();
/* 1984 */       char[] intoPass = Form.this.passwordPWF.getPassword();
/* 1985 */       for (int i = 0; i < intoPass.length; i++)
/*      */       {
/* 1987 */         pass = pass + intoPass[i];
/*      */       }
/*      */       try
/*      */       {
/* 1991 */         Form.this.conn.setUser(user, pass);
/*      */       } catch (SQLException ex) {
/* 1993 */         JOptionPane.showMessageDialog(null, ex.getMessage());
/* 1994 */         if (!Log.isInformed()) {
/* 1995 */           Log.addMinorError(ex);
/*      */         }
/*      */       }
/*      */       catch (Exception ex)
/*      */       {
/* 2000 */         JOptionPane.showMessageDialog(null, "An Unknown Error Has Occured");
/* 2001 */         if (!Log.isInformed())
/*      */         {
/* 2003 */           Log.addUnknownError(ex);
/*      */         }
/* 2005 */         DatabaseConnector.SetDefaultUser();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void setAdding(boolean add) {
/* 2011 */     add = !add;
/* 2012 */     this.adding = add;
/* 2013 */     if (add)
/*      */     {
/* 2015 */       if (this.searching)
/*      */       {
/* 2017 */         if (this.AllowButtonEditMNUR.isSelected())
/*      */         {
/* 2019 */           this.DeleteBTN.setEnabled(true);
/* 2020 */           this.editBTN.setEnabled(true);
/* 2021 */           this.editToDataBaseBTN.setEnabled(true);
/* 2022 */           this.CleanDataBaseBTN.setEnabled(true);
/* 2023 */           this.deleteCreatedDataMNUB.setEnabled(true);
/* 2024 */           this.CreateNewUserB.setEnabled(true);
/* 2025 */           this.DeleteUserB.setEnabled(false);
/*      */         }
/* 2027 */         this.connectBTN.setEnabled(true);
/* 2028 */         this.SetDefaultUserB.setEnabled(true);
/* 2029 */         this.AllowButtonEditMNUR.setEnabled(true);
/*      */       }
/* 2031 */       this.addBTN.setEnabled(true);
/* 2032 */       this.addToDataBaseBTN.setEnabled(true);
/* 2033 */       this.selectKOJCData.setEnabled(true);
/*      */     }
/*      */     else
/*      */     {
/* 2037 */       this.DeleteBTN.setEnabled(false);
/* 2038 */       this.editBTN.setEnabled(false);
/* 2039 */       this.editToDataBaseBTN.setEnabled(false);
/* 2040 */       this.CleanDataBaseBTN.setEnabled(false);
/* 2041 */       this.deleteCreatedDataMNUB.setEnabled(false);
/* 2042 */       this.connectBTN.setEnabled(false);
/* 2043 */       this.SetDefaultUserB.setEnabled(false);
/* 2044 */       this.AllowButtonEditMNUR.setEnabled(false);
/* 2045 */       this.addBTN.setEnabled(false);
/* 2046 */       this.addToDataBaseBTN.setEnabled(false);
/* 2047 */       this.selectKOJCData.setEnabled(false);
/* 2048 */       this.CreateNewUserB.setEnabled(false);
/* 2049 */       this.DeleteUserB.setEnabled(false);
/*      */     }
/*      */   }
/*      */   
/*      */   private void setSearching(boolean search) {
/* 2054 */     search = !search;
/* 2055 */     this.searching = search;
/* 2056 */     if (search)
/*      */     {
/* 2058 */       if (this.adding)
/*      */       {
/* 2060 */         if (this.AllowButtonEditMNUR.isSelected())
/*      */         {
/* 2062 */           this.DeleteBTN.setEnabled(true);
/* 2063 */           this.editBTN.setEnabled(true);
/* 2064 */           this.editToDataBaseBTN.setEnabled(true);
/* 2065 */           this.CleanDataBaseBTN.setEnabled(true);
/* 2066 */           this.deleteCreatedDataMNUB.setEnabled(true);
/* 2067 */           this.CreateNewUserB.setEnabled(true);
/* 2068 */           this.DeleteUserB.setEnabled(true);
/*      */         }
/* 2070 */         this.connectBTN.setEnabled(true);
/* 2071 */         this.SetDefaultUserB.setEnabled(true);
/* 2072 */         this.AllowButtonEditMNUR.setEnabled(true);
/*      */       }
/* 2074 */       this.SearchBTN.setEnabled(true);
/* 2075 */       this.toExcelBTN.setEnabled(true);
/*      */     }
/*      */     else
/*      */     {
/* 2079 */       this.DeleteBTN.setEnabled(false);
/* 2080 */       this.editBTN.setEnabled(false);
/* 2081 */       this.editToDataBaseBTN.setEnabled(false);
/* 2082 */       this.CleanDataBaseBTN.setEnabled(false);
/* 2083 */       this.deleteCreatedDataMNUB.setEnabled(false);
/* 2084 */       this.connectBTN.setEnabled(false);
/* 2085 */       this.SetDefaultUserB.setEnabled(false);
/* 2086 */       this.AllowButtonEditMNUR.setEnabled(false);
/* 2087 */       this.SearchBTN.setEnabled(false);
/* 2088 */       this.toExcelBTN.setEnabled(false);
/* 2089 */       this.CreateNewUserB.setEnabled(false);
/* 2090 */       this.DeleteUserB.setEnabled(false);
/*      */     } }
/*      */   
/* 2093 */   private int MaxInPutTBLSize = 5000;
/*      */   private static final int minDate = 1970;
/*      */   private static final int maxDate = 2112;
/* 2096 */   private static final String[] INPUTLBLS = { "Date(mm/dd/yyyy)", "Time of Day(HH:MM)", "Forest Temperature" };
/*      */   private DatabaseConnector conn;
/*      */   private ExcelConverter EC;
/*      */   private KOJCConnector CDG;
/* 2100 */   private int InputTBLSize = 0; private int lastSetSize = 0;
/* 2101 */   private boolean searching = true; private boolean adding = true;
/*      */   private JRadioButton AdminR;
/*      */   private JCheckBoxMenuItem AllowButtonEditMNUR;
/*      */   private JMenuItem CleanDataBaseBTN;
/*      */   private javax.swing.JPanel ConnectPanel;
/*      */   private JButton CreateNewUserB;
/*      */   private JMenuItem CreditMNU;
/*      */   private JLabel DataMovedLBL;
/*      */   private JButton DeleteBTN;
/*      */   private JButton DeleteUserB;
/*      */   private JRadioButton ExcelDoNotMoveCreatedData;
/*      */   private JLabel ExcelLBL;
/*      */   private javax.swing.JPanel ExcelP;
/*      */   private javax.swing.JMenu FileMNU;
/*      */   private JMenuItem GeneralErrorMNUB;
/*      */   private JCheckBoxMenuItem GiveDuplicatesCBMI;
/*      */   private JButton GraphBTN;
/*      */   private JLabel InputDateTXT;
/*      */   private JTable InputTBL;
/*      */   private JLabel InputTimeTXT;
/*      */   private JLabel KOJCLBL;
/*      */   private JCheckBoxMenuItem LinesCBMI;
/*      */   private JMenuItem MinorErrorMNUB;
/*      */   private JMenuItem NonErrorMNUB;
/*      */   private JButton SearchBTN;
/*      */   private JRadioButton SearchCreatedRAD;
/*      */   private JMenuItem SeriousErrorMNUB;
/*      */   private JMenuItem SetDefaultUserB;
/*      */   private JLabel StartDateLBL;
/*      */   private JRadioButton StudentUserR;
/*      */   private JLabel StudentWarningLBL;
/*      */   private JRadioButton TrustedStudentR;
/*      */   private JMenuItem UnknownErrorMNUB;
/*      */   private JMenuItem UserErrorMNUB;
/*      */   private JButton addBTN;
/*      */   private JButton addToDataBaseBTN;
/*      */   private javax.swing.JMenuBar choicesMNU;
/*      */   private JButton connectBTN;
/*      */   private JMenuItem deleteCreatedDataMNUB;
/*      */   private JButton editBTN;
/*      */   private javax.swing.JMenu editMNU;
/*      */   private JButton editToDataBaseBTN;
/*      */   private JLabel endDateLBL;
/*      */   private javax.swing.JMenu errorLogMNU;
/*      */   private JLabel excelEndDateLBL;
/*      */   private JTextField excelEndDateTXT;
/*      */   private JLabel excelEndTime;
/*      */   private JTextField excelEndTimeTXT;
/*      */   private JTable excelMoveDataTable;
/*      */   private JLabel excelStartDateLBL;
/*      */   private JTextField excelStartDateTXT;
/*      */   private JLabel excelStartTime;
/*      */   private JTextField excelStartTimeTXT;
/*      */   private javax.swing.JPanel inputP;
/*      */   private javax.swing.JScrollPane inputScrollP;
/*      */   private JButton jButton1;
/*      */   private JTextField outputEndDateTXT;
/*      */   private JTextField outputEndTimeTXT;
/*      */   private javax.swing.JPanel outputP;
/*      */   private javax.swing.JScrollPane outputSP;
/*      */   private javax.swing.JScrollPane outputSP1;
/*      */   private JTextField outputStartDateTXT;
/*      */   private JTextField outputStartTimeTXT;
/*      */   private JTable outputTBL;
/*      */   private JLabel passwordLBL;
/*      */   private javax.swing.JPasswordField passwordPWF;
/*      */   private javax.swing.JPanel searchP;
/*      */   private JButton selectKOJCData;
/*      */   private JLabel sizeL;
/*      */   private JTextField sizeT;
/*      */   private JLabel studentwarning2LBL;
/*      */   private javax.swing.JTabbedPane switchTP;
/*      */   private JButton toExcelBTN;
/*      */   private JTextField userNameTXT;
/*      */   private JLabel usernameLBL;
/*      */   private JMenuItem writeAllErrorBM;
/*      */ }


/* Location:              C:\Users\James\Desktop\jd-gui-windows-1.4.0\ForestTemperature.jar!\foresttemperature\Form.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */