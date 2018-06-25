/*    */ package foresttemperature;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserError
/*    */   extends Exception
/*    */ {
/*    */   private String fullExplain;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public UserError(String baseError, String fullExplain)
/*    */   {
/* 16 */     super(baseError);
/* 17 */     this.fullExplain = fullExplain;
/* 18 */     Log.addUserError(this);
/*    */   }
/*    */   
/* 21 */   public UserError(String baseError) { super(baseError); }
/*    */   
/*    */   public String getFullExplantion() {
/* 24 */     return this.fullExplain;
/*    */   }
/*    */ }


/* Location:              C:\Users\James\Desktop\jd-gui-windows-1.4.0\ForestTemperature.jar!\foresttemperature\UserError.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */