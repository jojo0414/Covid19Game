package covid19.game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Hero extends Character{

    String[][] Src = {{"hero_1.png", "hero_2.png", "hero_3.png", "hero_4.png"}, {"hero_5.png", "hero_6.png", "hero_7.png", "hero_8.png"}};  //角色圖片
    String[] Blood = {"12-12.png","11-12.png","10-12.png","9-12.png","8-12.png","7-12.png","6-12.png","5-12.png","4-12.png","3-12.png","2-12.png","1-12.png","0-12.png"};   //血量圖片
    
    public Hero(int sX, int sY, int rX, int rY){
        super(sX, sY, rX, rY);
        this.width = 200;
        this.height = 200;
        srcFlag = 0;
        moveFlag = -1;
        bloodFlag = 0;
        img.add(new ImageIcon("hero_1.png").getImage());
        blood.add(new ImageIcon("12-12.png").getImage());
    }

    public Hero(int sX, int sY){
        super(sX, sY);
        this.width = 200;
        this.height = 200;
        srcFlag = 0;
        bloodFlag = 0;
        moveFlag = -1;
        img.add(new ImageIcon("hero_1.png").getImage());
        blood.add(new ImageIcon("12-12.png").getImage());
    }

    @Override
    public void changeImg(){                //跑動畫
        if(moveFlag>=0){           
            // System.out.println("in 33 line in Hero"); 
            String src = Src[moveFlag][srcFlag];
            img.set(0, new ImageIcon(src).getImage());
            if(srcFlag==3)
                srcFlag = 0;
            else
                srcFlag++;
        }
    }

    public void setHp(Enemy enemy)
    {
        if(bloodFlag < 13)
        {
            String hp = Blood[bloodFlag];
            blood.set(0, new ImageIcon(hp).getImage());
            if(this.hit("Left",enemy) || this.hit("Right",enemy) || this.hit("Up",enemy) || this.hit("Down",enemy))
            {
                System.out.println("hit!!");
                bloodFlag++;
            }
        }
        
        else
        {
            img.remove(0);
            blood.remove(0);
        }
    }

    public boolean hit(String dir,Enemy enemy){
            Rectangle myrect = new Rectangle(posX,posY,30,30);
            Rectangle rect =null;
            //for (int i = 0; i < eneryList.size(); i++) {
             //   Enery enery = eneryList.get(i);
                if(dir.equals("Left")){
                    rect = new Rectangle(enemy.posX+20,enemy.posY,30,30);
                }
                else if(dir.equals("Right")){
                    rect = new Rectangle(enemy.posX-2,enemy.posY,30,30);
                }
                else if(dir.equals("Up")){
                    rect = new Rectangle(enemy.posX,enemy.posY+1,30,30);
                }else if(dir.equals("Down")){
                    rect = new Rectangle(enemy.posX,enemy.posY-20,30,30);
                }
                //碰撞檢測
                if(myrect.intersects(rect)){
                    return true;
                }
            //}
            return false;
    }

    public void check(){
        System.out.println("speedX is "+speedX+" while speedY is "+speedY);
        System.out.println("rectX is "+rectX+" while rectY is "+rectY);
        System.out.println("posX is "+posX+" while posY is "+posY);
    }
}