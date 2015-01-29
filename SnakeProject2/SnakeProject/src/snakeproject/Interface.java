/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snakeproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 *
 * @author Chopin
 */
public class Interface extends JFrame{
    
    private int mapWidth = 380;
    private int mapHeight = 350;
    private Map map;
    static JLabel wynik;
    static JButton start;
    static JButton koniec;
    static JButton instrukcje;
    static JButton opcje;
    boolean PRZESZKODY;
    
    private Snake snake;
    private int score;
    private Gameplay gamePlay;
    private MapArea widok;
    private Player player;
    
    public Interface(){
        
        map = new Map(mapWidth, mapHeight);
        snake = map.getSnake();
        player = new Player(snake);
        PRZESZKODY = false;
        
        // PARAMETRY GLOWNEGO OKNNA:
        setTitle("Snake");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocation(new Point(240, 120));   
        Insets insets = new Insets(1,1,1,1);    // standardowy margines
        
        score = 0;
        
        start = new JButton("Start");
        start.setMargin(insets);
        start.setFocusable(false);
        start.setBounds(407, 90, 80, 30);
        start.addActionListener(new Start()); // akcja przy wcisnieciu przycisku
        
        koniec = new JButton("Zakończ");
        koniec.setMargin(insets);
        koniec.setFocusable(false);
        koniec.setBounds(407, 330, 80, 30);
        koniec.addActionListener(new Zakoncz()); // akcja przy wcisnieciu przycisku
        
        instrukcje = new JButton("Instrukcje");
        instrukcje.setMargin(insets);
        instrukcje.setFocusable(false);
        instrukcje.setBounds(407, 250, 80, 30);
        instrukcje.addActionListener(new Instrukcje());
        
        opcje = new JButton("Opcje");
        opcje.setMargin(insets);
        opcje.setFocusable(false);
        opcje.setBounds(407, 170, 80, 30);
        opcje.addActionListener(new Opcje());
        
        wynik = new JLabel("0");
        wynik.setBounds(450, 30, 42, 30);
        
        //MapArea widok = new MapArea();
        widok = new MapArea();
        widok.setBounds(0, 0, 400, 372);
        
        add(start);
        add(koniec);  
        add(instrukcje);
        add(opcje);
        add(wynik);
        add(widok);


        setVisible(true);
    }
    
    private class MapArea extends JPanel implements ActionListener{
        
        private Apple apple;
        private Image brickImage;
        private Image appleImage;
        private Image headImage;
        private Image bodyImage;
        
        public Timer timer;
        private int timeDelay = 50;
        
        private ArrayList<Integer> bodyPosX, bodyPosY;  // przechowuje wspolrzedne kazdej czesci tlowia
        
    public MapArea(){
        
        addKeyListener(player.getKAdapter());
        
        gamePlay = new Gameplay(mapWidth, mapHeight, snake);
        apple = map.getApple();
        
        ImageIcon ib = new ImageIcon(this.getClass().getResource("cegla.jpg")); // grafiki musza byc 10x10pix
        brickImage = ib.getImage();
        
        ImageIcon ia = new ImageIcon(this.getClass().getResource("apple.jpg"));
        appleImage = ia.getImage();
        
        ImageIcon ih = new ImageIcon(this.getClass().getResource("head.jpg"));
        headImage = ih.getImage();

        ImageIcon ibo = new ImageIcon(this.getClass().getResource("body.jpg"));
        bodyImage = ibo.getImage();

        setBackground(Color.black);
        setFocusable(true);
        
        initializeGame();
        }
        
    public void initializeGame(){
            
        bodyPosX = new ArrayList<Integer>();
        bodyPosY = new ArrayList<Integer>();
        
        for (int i = 0; i < snake.getSize(); i++) {
            bodyPosX.add(180 + i*10);
            bodyPosY.add(180);
            }
            
            locateApple();
 
        timer = new Timer(timeDelay, this);
        }
        
    private void locateApple() {   // sprawdza czy jablko nie lezy na wezu
            boolean condition = true;
            
            while (condition) {
                apple.changePosition();
            if ((bodyPosX.contains(apple.getPosition().x)) && (bodyPosY.contains(apple.getPosition().y)))
                continue;
            else
                condition = false;
            }
        }
        
    private void checkApple() {
        if ((bodyPosX.get(0) == apple.getPosition().x) && (bodyPosY.get(0) == apple.getPosition().y)) {
            snake.growUp();
            score += 10;
            wynik.setText(Integer.toString(score));
            
            bodyPosX.add(bodyPosX.get(1));
            bodyPosY.add(bodyPosY.get(1));

            locateApple();
            }
        }

    public void move() {
            int tmp;
            
        for (int i = snake.getSize()-1; i > 0; i--) {
            bodyPosX.set(i, bodyPosX.get(i-1));
            bodyPosY.set(i, bodyPosY.get(i-1));
            }

        if (snake.getDirection() == 3) {
            tmp = bodyPosX.get(0);
            bodyPosX.set(0, tmp-10);
            }
        if (snake.getDirection() == 1) {
            tmp = bodyPosX.get(0);
            bodyPosX.set(0, tmp+10);
            }
        if (snake.getDirection() == 0) {
            tmp = bodyPosY.get(0);
            bodyPosY.set(0, tmp-10);
            }
        if (snake.getDirection() == 2) {
            tmp = bodyPosY.get(0);
            bodyPosY.set(0, tmp+10);
            }
        }
        
    public void paint(Graphics g) { // renderuje wszystko co jest wewnatrz MapArea. Nie trzeba jej wywolywac.
        super.paint(g);
        
        if (gamePlay.getIsAlive()) {
            g.drawImage(appleImage, apple.getPosition().x, apple.getPosition().y, this);

        for (int i = 0; i < snake.getSize(); i++) {
            if (i == 0)
                g.drawImage(headImage, bodyPosX.get(i), bodyPosY.get(i), this);
            else
                g.drawImage(bodyImage, bodyPosX.get(i), bodyPosY.get(i), this);
            }
        }
        
        for (int i = 0; i <= 390; i += 10) {
            g.drawImage(brickImage, i, 0, this);
            g.drawImage(brickImage, i, 360, this);
            }

        for (int i = 10; i < 360; i += 10) {
            g.drawImage(brickImage, 0, i, this);
            g.drawImage(brickImage, 390, i, this);
            }
        }
        
    public void actionPerformed(ActionEvent e) {
        if (gamePlay.getIsAlive()) {
            checkApple();
            gamePlay.checkCollision(bodyPosX, bodyPosY);
            move();
            repaint();
        }
        else {
            timer.stop();
            bodyPosX.clear();
            bodyPosY.clear();
            snake.snakeReset();

            for (int i = 0; i < snake.getSize(); i++) {
                bodyPosX.add(180 + i*10);
                bodyPosY.add(180);
        }

        snake.setDirection(0);
        gamePlay.makeSnakeAlive();
        
        start.setEnabled(true);
        //Snake.wyniki.setEnabled(true);

        repaint();

        //KoniecGry koniec = new KoniecGry();
        System.out.println("KONIEC GRY");
        System.out.println(score);
        score=0;
        wynik.setText(Integer.toString(score));
        }
    }
}
    
private class Start implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        start.setEnabled(false);
        wynik.setEnabled(false);
        widok.timer.start();
        
    }
}

private class Zakoncz implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        widok.timer.stop();
        dispose();
        }
    }
private class Opcje implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        new OknoOpcje();
        }
    }
private class OknoOpcje extends JFrame{
        
    private JButton zamknij;
    private JCheckBox przeszkody;
    public OknoOpcje(){
        setTitle("Opcje");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocation(new Point(240, 120));   
        Insets insets = new Insets(1,1,1,1);

        zamknij = new JButton("Zamknij");
        zamknij.setMargin(insets);
        zamknij.setFocusable(false);
        zamknij.setBounds(100, 120, 80, 30);
        zamknij.addActionListener(new Zamknij()); // akcja przy wcisnieciu przycisku
        
        przeszkody = new JCheckBox("Przeszkody");
        przeszkody.setBounds(20, 20, 100, 20);
        przeszkody.addActionListener(new Przeszkody());
        
        add(przeszkody);
        add(zamknij);
        setVisible(true);
    }
    
    private class Zamknij implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        }
    }
    private class Przeszkody implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if(przeszkody.isSelected()){
            PRZESZKODY = true;
            System.out.println("PRZESZKODY SA TRUE");
        }else
            PRZESZKODY = false;
        }
    }
}
private class OknoInstrukcje extends JFrame{
        
    private JButton zamknij;
    private JTextArea info;
    public OknoInstrukcje(){
        setTitle("Instrukcje");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocation(new Point(240, 120));   
        Insets insets = new Insets(1,1,1,1);

        zamknij = new JButton("Zamknij");
        zamknij.setMargin(insets);
        zamknij.setFocusable(false);
        zamknij.setBounds(100, 120, 80, 30);
        zamknij.addActionListener(new Zamknij()); // akcja przy wcisnieciu przycisku
        
        info = new JTextArea();
        info.setFocusable(false);
        info.setBounds(10, 10, 200, 100);
        info.setSize(280, 80);
        info.setText("Celem gracza jest zdobycie jak najwyzszego\n wyniku, poprzez zjadanie jablek.\n Uderzenie w przeszkode lub samego weza\n powoduje koniec gry.");
        
        add(zamknij);
        add(info);
        setVisible(true);
    }
    
    private class Zamknij implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        }
    }
    

}
private class Instrukcje implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        new OknoInstrukcje();
        }
    }
}
