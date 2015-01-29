/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snakeproject;

import java.awt.Point;
import java.util.Random;

/**
 *
 * @author Chopin
 * 
 * @param
 *          empty
 *          @brief \n Klasa ospowiada za jablka w grze.
 * 
 */
public class Apple {

    private Point position;
    
    // WIELKOSC MAPY:   // (0,0) lezy w lewym gornym rogu
    private int mapX;
    private int mapY;
    
    public Apple(int x, int y){
        //position = new Point(randPosition());
        mapX = x; x -=20;   // odejmujemy 20 zeby jablka nie wyladowaly na ceglach (20 pixeli)
        mapY = y; y -=20;
        changePosition();
    }
    
    private Point randPosition(){
        Random random = new Random();
        Point x = new Point(random.nextInt(mapX/10)+1, random.nextInt(mapY/10)+1);  // dzielenie przez 10, bo kazde jablko ma 10 pixeli. Kazde jablko musi wyladowac na sciezce weza.
        x = new Point(x.x*10, x.y*10);  // wracamy do normalnych wspolrzednych
        return x;
    }
    
    public void changePosition(){
        position = new Point(randPosition());
    }
    
    public Point getPosition(){
        return position;
    }
}
