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
 * 
 *  @param
 *          empty
 * 
 *          @brief \n Klasa odpowiada za pozycje, kierunek ruchu i rozmiar weza.
 */
public class Snake {

    private Point position;
    private int size;       // dlugosc weza bez glowy
    private int direction; // 0 = gora, 1 = prawo, 2 = dol, 3 = lewo
    
    /**
     * @brief \n Konstruktor Snake(), nadaje pozycje poczatkowa (0,0), kierunek weza do gory, oraz dlugosc rowna 3.
     */
    public Snake(){
        position = new Point(0,0);
        direction = 0;
        size = 3;
    }
    
    /**
     * @brief \n Funkcja growUp() dodaje 1 do dlugosci weza
     */
    public void growUp(){
        size++;
    }
    
    /**
     * @brief \n Waz powraca do stanu poczatkowego.
     */
    public void snakeReset(){
        position = new Point(0,0);
        direction = 0;
        size = 3;
    }
    
    /**
     * @return \n Zwraca dlugosc weza.
     */
    public int getSize(){
        return size;
    }
    
    /**
     * @return \n Funkcja zwraca pozycje weza. 
     */
    public Point getPosition(){
        return position;
    }
    
    /**
     * @return \n Funkcja zwraca kierunek weza.  0 = gora, 1 = prawo, 2 = dol, 3 = lewo
     */
    public int getDirection(){
        return direction;
    }
    
    /**
     * @param
     * Jako argument przyjmuje liczbe od 0 do 4, odpowiadajaca pozadanemu kierunkowi ruchu.  0 = gora, 1 = prawo, 2 = dol, 3 = lewo
     * @brief \n Funkcja pozwala na zmiane kierunku ruchu weza. 
     */
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
