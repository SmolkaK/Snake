/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snakeproject;

import java.awt.Point;

/**
 *
 * @author Chopin
 */
public class Snake {

    private Point position;
    private int size;       // dlugosc weza bez glowy
    private int direction; // 0 = gora, 1 = prawo, 2 = dol, 3 = lewo
    
    public Snake(){
        position = new Point(0,0);
        direction = 0;
        size = 3;
    }
    
    public void growUp(){
        size++;
    }
    
    public void snakeReset(){
        position = new Point(0,0);
        direction = 0;
        size = 3;
    }
    
    public int getSize(){
        return size;
    }
    
    public Point getPosition(){
        return position;
    }
    
    public int getDirection(){
        return direction;
    }
    
    public void setDirection(int newDir){
        try{
            if(newDir == 0 || newDir == 1 || newDir == 2 || newDir == 3 )
                direction = newDir;
            else{
                throw new Exception();
            }
        }catch(Exception ex){
            System.out.println("Blad przy okreslaniu kierunku ruchu! (klasa Snake)");
        }
    }
    
}
