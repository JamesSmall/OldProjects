/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Base.UI;

import Graphics.Base.ScreenUI;
import static Graphics.Base.ScreenUI.CENTER;
import static Graphics.Base.ScreenUI.DOWN;
import static Graphics.Base.ScreenUI.DOWNLEFT;
import static Graphics.Base.ScreenUI.DOWNRIGHT;
import static Graphics.Base.ScreenUI.LEFT;
import static Graphics.Base.ScreenUI.RIGHT;
import static Graphics.Base.ScreenUI.UP;
import static Graphics.Base.ScreenUI.UPLEFT;
import static Graphics.Base.ScreenUI.UPRIGHT;
import Graphics.Base.UIManager;
import Holder.Texture;
import java.util.ArrayList;
import org.lwjgl.LWJGLException;

/**
 *
 * @author Allazham
 */
public class Panel extends ScreenUI{
    private boolean updated = false;
    private final ArrayList<ScreenUIHolder> list = new ArrayList();
    public Panel(boolean disabled){
        super(disabled);
    }
    public Panel(float x, float y, float width, float height, int orintation,boolean disabled){
        super(x,y,width,height,orintation,disabled);
    }
    public Panel(float x, float y, float width, float height, int orintation,Texture t,boolean disabled){
        super(x,y,width,height,orintation,t,disabled);
    }
    public void addRenderObject(ScreenUI ui){
        ScreenUIHolder uih = new ScreenUIHolder(ui);
        ui.setUIManager(uih);
        this.list.add(uih);
    }/*
    @Override
    public void setX(float x){
        super.setX(x);
        this.updated = true;
    }
    @Override
    public void setY(float y){
        super.setY(y);
        this.updated = true;
    }
    @Override
    public void setWidth(float width){
        super.setWidth(width);
        this.updated = true;
    }
    @Override
    public void setHeight(float height){
        super.setHeight(height);
        this.updated = true;
    }
    @Override
    public void setOrintation(int ort){
        super.setOrintation(ort);
        this.updated = true;
        super.ProcessChanges(null, UP, UP, ort, ort, ort);
    }*/
    @Override
    public void ProcessChanges(UIManager ui,float x, float y, float width, float height,int Orinentation){
        super.ProcessChanges(ui, x, y, width, height, Orinentation);
        super.setX(x);
        super.setY(y);
        super.setWidth(width);
        super.setHeight(height);
        super.setOrintation(Orinentation);
        this.updated = true;
    }
    @Override
    public void setUIManager(UIManager ui){
        super.setUIManager(ui);
        this.updated = true;
    }
    public boolean removeRenderObject(ScreenUI ui){
        ScreenUIHolder uih = this.getHolder(ui);
        if(uih == null){
            return false;
        }
        boolean remove = this.list.remove(uih);
        uih.SpitInstance();
        return remove;
    }
    private ScreenUIHolder getHolder(ScreenUI ui){
        int i;
        for(i = 0; i < this.list.size();i++){
            if(this.list.get(i).ui.equals(ui)){
                return this.list.get(i);
            }
        }
        return null;
    }
    @Override
    public void render() throws LWJGLException{
        super.render();
        int i;
        for(i = 0;i < this.list.size();i++){
            if(!this.list.get(i).hasRan||this.updated){
                HandleUI(this.list.get(i));
            }
            this.list.get(i).ui.render();
        }
        this.updated = false;
    }
    public void update(){
        this.updated = true;
    }
    private void HandleUI(ScreenUIHolder suih){
        suih.ui.ProcessChanges(suih, handleX(suih), handleY(suih), suih.width*super.getWidth(),suih.height*super.getHeight(), super.getOrintation());
        suih.hasRan = true;
    }
    private float handleX(ScreenUIHolder suih){
        switch(super.getOrintation()){
            case DOWNRIGHT:
            case UPRIGHT:
            case RIGHT:
                switch(suih.orientation){
                    case DOWNRIGHT:
                    case UPRIGHT:
                    case RIGHT:
                        return suih.getX()*super.getActualWidth()+super.getActualX();
                    case DOWN:
                    case UP:
                    case CENTER:
                        return super.getActualWidth()-(suih.getX()+suih.width+.5f)*super.getActualWidth()+super.getActualX();
                    case DOWNLEFT:
                    case UPLEFT:
                    case LEFT:
                        return super.getActualWidth()-(suih.getX()+suih.width)*super.getActualWidth()+super.getActualX();
                    default:
                        throw new UnsupportedOperationException("Orentation not supported");
                }
            case DOWN:
            case UP:
            case CENTER:
                switch(suih.orientation){
                    case DOWNRIGHT:
                    case UPRIGHT:
                    case RIGHT:
                       return (-suih.x-suih.getWidth()/2+.5f)*super.getActualWidth()+super.getActualX();
                    case DOWN:
                    case UP:
                    case CENTER:
                       return suih.x*super.getActualWidth()+super.getActualX();
                    case DOWNLEFT:
                    case UPLEFT:
                    case LEFT:
                        return (suih.x*super.getActualWidth()/2+suih.getWidth()/2-.5f)*super.getActualWidth()+super.getActualX();
                    default:
                        throw new UnsupportedOperationException("Orentation not supported");
                }
            case DOWNLEFT:
            case UPLEFT:
            case LEFT:
                switch(suih.orientation){
                    case DOWNRIGHT:
                    case UPRIGHT:
                    case RIGHT:
                        return super.getActualWidth()-(suih.getX()+suih.width)*super.getActualWidth()+super.getActualX();
                    case DOWN:
                    case UP:
                    case CENTER:
                        return (suih.getX()+.5f)*super.getActualWidth()+super.getActualX();
                    case DOWNLEFT:
                    case UPLEFT:
                    case LEFT:
                        return suih.getX()*super.getActualWidth()+super.getActualX();
                    default:
                        throw new UnsupportedOperationException("Orentation not supported");
                }
            default:
                throw new UnsupportedOperationException("Orentation not supported");
        }
    }
    private float handleY(ScreenUIHolder suih){
        switch(super.getOrintation()){
            case UPLEFT:
            case UPRIGHT:
            case UP:
                switch(suih.orientation){
                    case UPLEFT:
                    case UPRIGHT:
                    case UP:
                        return suih.getY()*super.getActualHeight()+super.getActualY();
                    case RIGHT:
                    case LEFT:
                    case CENTER:
                        return super.getActualHeight()-(suih.getY()+suih.height+.5f)*super.getActualHeight()+super.getActualY();
                    case DOWNLEFT:
                    case DOWNRIGHT:
                    case DOWN:
                        return super.getActualHeight()-(suih.getY()+suih.height)*super.getActualHeight()+super.getActualY();
                    default:
                        throw new UnsupportedOperationException("Orentation not supported");
                }
            case LEFT:
            case RIGHT:
            case CENTER:
                switch(suih.orientation){
                    case UPLEFT:
                    case UPRIGHT:
                    case UP:
                       return (-suih.y-suih.getHeight()/2+.5f)*super.getActualHeight()+super.getActualY();
                    case LEFT:
                    case RIGHT:
                    case CENTER:
                       return suih.y*super.getActualHeight()+super.getActualY();
                    case DOWNRIGHT:
                    case DOWNLEFT:
                    case DOWN:
                        return (suih.y*super.getActualHeight()/2+suih.getHeight()/2-.5f)*super.getActualHeight()+super.getActualY();
                    default:
                        throw new UnsupportedOperationException("Orentation not supported");
                }
            case DOWNLEFT:
            case DOWNRIGHT:
            case DOWN:
                switch(suih.orientation){
                    case UPRIGHT:
                    case UPLEFT:
                    case UP:
                        return super.getActualHeight()-(suih.getY()+suih.height)*super.getActualHeight()+super.getActualY();
                    case LEFT:
                    case RIGHT:
                    case CENTER:
                        return (suih.getY()+.5f)*super.getActualHeight()+super.getActualY();
                    case DOWNLEFT:
                    case DOWNRIGHT:
                    case DOWN:
                        return suih.getY()*super.getActualHeight()+super.getY();
                    default:
                        throw new UnsupportedOperationException("Orentation not supported");
                }
            default:
                throw new UnsupportedOperationException("Orentation not supported");
        }
    }
    private class ScreenUIHolder implements UIManager{
        private float x,y,width,height;
        private boolean hasRan = false;
        private int orientation;
        private ScreenUI ui;
        public ScreenUIHolder(ScreenUI ui){
            this.x = ui.getX();
            this.y = ui.getY();
            this.width = ui.getWidth();
            this.height = ui.getHeight();
            this.orientation = ui.getOrintation();
            this.ui = ui;
        }

        @Override
        public void setX(float x) {
            this.x = x;
            this.hasRan = false;
        }
        public void SpitInstance(){
            this.ui.ProcessChanges(this, x, y, width, height, orientation);
            this.ui.setUIManager(null);
        }
        @Override
        public void setY(float y) {
            this.y = y;
            this.hasRan = false;
        }

        @Override
        public void setWidth(float width) {
            this.width = width;
            this.hasRan = false;
        }

        @Override
        public void setHeight(float height) {
            this.height = height;
            this.hasRan = false;
        }

        @Override
        public float getX() {
            return this.x;
        }

        @Override
        public float getY() {
            return this.y;
        }

        @Override
        public float getWidth() {
            return this.width;
        }

        @Override
        public float getHeight() {
            return this.height;
        }

        @Override
        public void setOrientation(int ort) {
            this.orientation = ort;
            this.hasRan = false;
        }

        @Override
        public int getOrientation() {
            return this.orientation;
        }
        
    }
}
