package com.company;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;

public class MainMenu {
    public JFrame screen;
    public Game game;
    MainMenu()
    {
        screen = new JFrame();
        game = new Game();
    };
    public void menuScreen()
    {
        JButton start=new JButton("START");
        start.setBounds(200,100,200,50);
        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               chooseWorld();
            }
        });
        JButton load=new JButton("LOAD");
        load.setBounds(200,200,200,50);
        load.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    loadGame();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        JButton quit=new JButton("QUIT");
        quit.setBounds(200,300,200,50);
        quit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                quitGame();
            }
        });
        this.screen.add(start, BorderLayout.EAST);
        this.screen.add(load, BorderLayout.EAST);
        this.screen.add(quit, BorderLayout.EAST);
        this.screen.setLayout(null);
        this.screen.setSize(600,500);
        this.screen.setVisible(true);
        this.screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void chooseWorld()
    {
        screen.dispose();
        screen = new JFrame();
        JLabel info = new JLabel("CHOOSE WORLD TYPE");
        Button grid = new Button("GRID WORLD");
        Button hex = new Button("HEX WORLD");
        info.setBounds(10,10,300,50);
        grid.setBounds(10,75,300,75);
        hex.setBounds(10, 200, 300, 75);
        grid.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                readSize(1);
            }
        });
        hex.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                readSize(2);
            }
        });
        screen.add(info, BorderLayout.EAST);
        screen.add(grid, BorderLayout.EAST);
        screen.add(hex, BorderLayout.EAST);
        screen.setLayout(null);
        screen.setSize(600,500);
        screen.setVisible(true);
        SwingUtilities.updateComponentTreeUI(screen);
    }

    public void readSize(int type)
    {
        screen.dispose();
        JFrame screen2 = new JFrame();
        JLabel sizeX = new JLabel("Write size X");
        JLabel sizeY = new JLabel("write size Y");
        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        Button button = new Button("CONTINUE");
        tf1.setBounds(50,30,150,20);
        tf2.setBounds(50,90,150,20);
        sizeX.setBounds(50,0,150,20);
        sizeY.setBounds(50,60,150,20);
        button.setBounds(50,120,150,20);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(tf1.getText().length() != 0 && tf2.getText().length() != 0)
                {
                    if(type == 1)
                    {
                        World world = new GridWorld(Integer.parseInt(tf1.getText()), Integer.parseInt(tf2.getText()));
                        game = new GameGrid(world);
                    }
                    else if(type == 2) {
                        World world = new HexWorld(Integer.parseInt(tf1.getText()), Integer.parseInt(tf2.getText()));
                        game = new Game(world);
                    }
                    screen2.dispose();
                    game.gameWindow();
                }
            }
        });
        screen2.add(sizeX); screen2.add(tf1); screen2.add(sizeY); screen2.add(tf2); screen2.add(button);
        screen2.setLayout(null);
        screen2.setSize(600,500);
        screen2.setVisible(true);
    }



    void loadGame() throws IOException {
        game.loadGame();
        Game game2 = new Game();
        if(game.world instanceof GridWorld)
        {
            game2 = new GameGrid(game.world);
        }
        else
        {
            game2 = new Game(game.world);
        }
        game2.gameWindow();
    }
    void quitGame()
    {
        this.screen.setVisible(false);
        this.screen.dispose();
    }
}
