/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snakeproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author Chopin
 */
public class Map{
    private Apple apple;
    private Snake snake;
    private int mapSizeX;
    private int mapSizeY;
    
    public Map(int mapX, int mapY){
        mapSizeX = mapX;
        mapSizeY = mapY;
        apple = new Apple(mapSizeX, mapSizeY);
        snake = new Snake();
    }
    
    public Snake getSnake(){
        return snake;
    }

    public Apple getApple(){
        return apple;
    }
}
