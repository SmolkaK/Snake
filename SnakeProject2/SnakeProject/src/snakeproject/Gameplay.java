/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snakeproject;

import java.util.ArrayList;

/**
 *
 * @author Chopin
 */
public class Gameplay {
    
    private boolean isAlive = false;    // czy waz zyje
    private int mapWidth; 
    private int mapHeight;
    private Snake snake;
    
    public Gameplay(int mWidth, int mHeight, Snake s){
        isAlive = true;
        mapHeight = mHeight;
        mapWidth = mWidth;
        snake = s;
    }
    
    public boolean getIsAlive(){
        return isAlive;
    }
    
    public void makeSnakeAlive(){
        isAlive = true;
    }
    
    public void checkCollision(ArrayList<Integer> x, ArrayList<Integer> y) {

    for(int i=1; i<snake.getSize(); i++){
        if((x.get(i).equals(x.get(0))) && (y.get(i).equals(y.get(0)))) {
            isAlive = false;
            break;
            }
        }

    if (x.get(0) > mapWidth)
        isAlive = false;

    if (x.get(0) < 10)
        isAlive = false;

    if (y.get(0) > mapHeight)
        isAlive = false;

    if (y.get(0) < 10)
        isAlive = false;
    }
}
