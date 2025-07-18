package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoiceOrganism {
    JFrame screen;
    Game game;
    World world;
    ChoiceOrganism(World world, int x, int y)
    {
        screen = new JFrame();
        this.screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel choices = new JPanel(new GridLayout(7,2));
        Button[] buttons = new Button[10];
        buttons[0] = new Button("Antelope");
        buttons[0].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Antelope organism = new Antelope(x,y,world);
                world.addOrganism(organism);
                screen.setVisible(false);
                screen.dispose();
            }
        });
        buttons[1] = new Button("Fox");
        buttons[1].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Fox organism = new Fox(x,y,world);
                world.addOrganism(organism);
                screen.setVisible(false);
                screen.dispose();
            }
        });
        buttons[2] = new Button("Grass");
        buttons[2].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Grass organism = new Grass(x,y,world);
                world.addOrganism(organism);
                screen.setVisible(false);
                screen.dispose();
            }
        });
        buttons[3] = new Button("Guarana");
        buttons[3].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Guarana organism = new Guarana(x,y,world);
                world.addOrganism(organism);
                screen.setVisible(false);
                screen.dispose();
            }
        });
        buttons[4] = new Button("Keen Berry");
        buttons[4].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                KeenBerry organism = new KeenBerry(x,y,world);
                world.addOrganism(organism);
                screen.setVisible(false);
                screen.dispose();
            }
        });
        buttons[5] = new Button("Milt");
        buttons[5].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Milt organism = new Milt(x,y,world);
                world.addOrganism(organism);
                screen.setVisible(false);
                screen.dispose();
            }
        });
        buttons[6] = new Button("Sheep");
        buttons[6].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Sheep organism = new Sheep(x,y,world);
                world.addOrganism(organism);
                screen.setVisible(false);
                screen.dispose();
            }
        });
        buttons[7] = new Button("Sosnowsky Hogwood");
        buttons[7].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                SosnowskyHogwood organism = new SosnowskyHogwood(x,y,world);
                world.addOrganism(organism);
                screen.setVisible(false);
                screen.dispose();
            }
        });
        buttons[8] = new Button("Turtle");
        buttons[8].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Turtle organism = new Turtle(x,y,world);
                world.addOrganism(organism);
                screen.setVisible(false);
                screen.dispose();
            }
        });
        buttons[9] = new Button("Wolf");
        buttons[9].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Wolf organism = new Wolf(x,y,world);
                world.addOrganism(organism);
                screen.setVisible(false);
                screen.dispose();
            }
        });
        for(int i = 0; i<10; i++)
        {
            choices.add(buttons[i]);
        }
        screen.add(choices);
        screen.setSize(900,500);
        screen.setVisible(true);
    };
}
