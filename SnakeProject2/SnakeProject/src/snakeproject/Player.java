/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snakeproject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Chopin
 */
public class Player {
    private Snake snake;
    private KAdapter kAdapter;
    
    public Player(Snake s){
        snake = s;
        kAdapter = new KAdapter();
    }
    
    public KAdapter getKAdapter(){
        return kAdapter;
    }
    
private class KAdapter extends KeyAdapter {     // obsluga sterowania
public void keyPressed(KeyEvent e) {
 
int key = e.getKeyCode();
 
    if ((key == KeyEvent.VK_UP) && (snake.getDirection() != 2)) {       // kazdy warunek sprawdza czy waz nie idzie w przeciwna strone (nie mozna zawracac)
        snake.setDirection(0);
        }

    if ((key == KeyEvent.VK_RIGHT) && (snake.getDirection() != 3)) {
        snake.setDirection(1);
        }

    if ((key == KeyEvent.VK_DOWN) && (snake.getDirection() != 0)) {
        snake.setDirection(2);
        }

    if ((key == KeyEvent.VK_LEFT) && (snake.getDirection() != 1)) {     
        snake.setDirection(3);
        }
    }
}
}
