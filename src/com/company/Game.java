package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Vector;

public class Game implements KeyListener {
    protected World world;
    protected JFrame window;
    protected JPanel board;
    protected JPanel specialAbility;
    protected JPanel temp;

    public Game()
    {
    }
    public Game(World world)
    {
        this.world = world;
        world.nextTurn();
    }

    public void gameWindow()
    {
        this.window = new JFrame();
        this.window.setSize(700,700);
        this.window.addKeyListener(this);
        window.setFocusable(true);
        board = new JPanel(new GridLayout(world.getWidth(), world.getHeight()));
        Button[][] buttons = new Button[world.getWidth()][world.getHeight()];
        for(int i  = 0; i < world.getWidth(); i++)
        {
            for (int j = 0; j < world.getHeight(); j++) {
                buttons[i][j] = new Button();
                int finalI = i;
                int finalJ = j;
                if(world.getOrganism(finalI +1, finalJ +1) != null) {
                    buttons[finalI][finalJ].setLabel(" " + world.getOrganism(finalI + 1, finalJ + 1).getLetter());
                    if(world.getOrganism(finalI+1,finalJ+1).isHuman() == true)
                    {
                        buttons[i][j].setBackground(Color.GRAY);
                    }
                }
                board.add(buttons[i][j]);
                buttons[i][j].addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if(world.getOrganism(finalI +1, finalJ +1) == null) {
                            ChoiceOrganism choiceOrganism = new ChoiceOrganism(world, finalI + 1, finalJ + 1);
                            //reDraw();
                        }
                    }
                });
            }
        }

        JToolBar toolBar = new JToolBar();
        JButton save = new JButton("SAVE");
        save.setFocusable(false);
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    saveGame();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        JButton nextTurn = new JButton("NEXT TURN");
        nextTurn.setFocusable(false);
        nextTurn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextTurn();
            }
        });

        JPanel temp = new JPanel();
        toolBar.add(new JLabel("Use arrows to move, \n Q to use special ability"));
        toolBar.add(nextTurn); toolBar.add(save);
        temp.add(toolBar);
        specialAbility = new JPanel(new GridLayout(1,3));
        Human human = (Human)world.getHuman();
        specialAbility.add(new JLabel("Special ability cooldown: " + human.getSpecialAbilityCooldown()));
        specialAbility.add(new JLabel("Special ability duration: " + human.getSpecialAbilityTurns()));
        specialAbility.add(new JLabel("Special ability active: " + (human.isSpecialAbilityActive() == true ? "YES" : "NO")));
        window.add(temp, BorderLayout.NORTH); window.add(board,BorderLayout.CENTER); window.add(specialAbility, BorderLayout.PAGE_END);
        this.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.window.setVisible(true);
    }

    private void nextTurn()
    {
        world.nextTurn();
        //board.revalidate();
        //window.revalidate();
        reDraw();
        //window.
    }

    public void reDraw() {
        board.removeAll();
       // board = new JPanel(new GridLayout(world.getWidth(), world.getHeight()));
        Button[][] buttons = new Button[world.getWidth()][world.getHeight()];
        for (int i = 0; i < world.getWidth(); i++) {
            for (int j = 0; j < world.getHeight(); j++) {
                buttons[i][j] = new Button();
                int finalI = i;
                int finalJ = j;
                if (world.getOrganism(finalI + 1, finalJ + 1) != null) {
                    buttons[finalI][finalJ].setLabel(" " + world.getOrganism(finalI + 1, finalJ + 1).getLetter());
                    if(world.getOrganism(finalI+1,finalJ+1).isHuman() == true)
                    {
                        buttons[i][j].setBackground(Color.GRAY);
                    }
                }
                buttons[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (world.getOrganism(finalI + 1, finalJ + 1) == null) {
                            ChoiceOrganism choiceOrganism = new ChoiceOrganism(world, finalI + 1, finalJ + 1);
                            //reDraw();
                        }
                    }
                });
                board.add(buttons[i][j]);
            }
        }
        specialAbility.removeAll();
        if(world.isHumanState() == true) {
            specialAbility.removeAll();
            Human human = (Human) world.getHuman();
            specialAbility.add(new JLabel("Special ability cooldown: " + human.getSpecialAbilityCooldown()));
            specialAbility.add(new JLabel("Special ability duration: " + human.getSpecialAbilityTurns()));
            specialAbility.add(new JLabel("Special ability active: " + (human.isSpecialAbilityActive() == true ? "YES" : "NO")));
            window.add(specialAbility, BorderLayout.PAGE_END);
        }
        board.revalidate();
        window.setFocusable(true);
        //JPanel temp = new JPanel(); temp.add(toolBar);
        //window.add(temp, BorderLayout.NORTH);
        //window.add(specialAbility, BorderLayout.PAGE_END);
        //SwingUtilities.updateComponentTreeUI(window);
    }

    public void saveGame() throws IOException
    {
        FileOutputStream file = null;

        try
        {
            file = new FileOutputStream("data.bin");
            if(this.world instanceof HexWorld)
            {
                file.write(71);
            }
            else if(this.world instanceof GridWorld)
            {
                file.write(50);
            }
            file.write(world.getTurn());
            file.write(world.getHeight());
            file.write(world.getWidth());

            Vector<Organism> organisms = world.getOrganisms();

            for(Organism organism : organisms)
            {
                file.write(organism.getLetter());
                file.write(organism.getStrength());
                file.write(organism.getX());
                file.write(organism.getY());
                file.write(organism.getAge());

                if(organism.isHuman() == true)
                {
                    Human human = (Human) organism;
                    file.write(human.getSpecialAbilityCooldown());
                    file.write(human.isSpecialAbilityActive() ? 1 : 0);
                    file.write(human.getSpecialAbilityTurns());
                }
            }
            System.out.println("SAVED");
        }finally
        {
            if(file != null)
            {
                file.close();
            }
        }
    }

    public void loadGame() throws IOException
    {
        FileInputStream file = null;
        try
        {
            file = new FileInputStream("data.bin");
            int type, turn, heigth, width;
            type = file.read(); turn = file.read(); heigth = file.read(); width = file.read();
            if(type == 71) {
                world = new HexWorld(width, heigth, turn);
            }
            else
            {
                world = new GridWorld(width, heigth, turn);
            }

            while(file.available() != 0)
            {
                char letter;
                int strength, x, y, age;
                letter = (char)file.read(); strength = file.read(); x = file.read(); y = file.read(); age = file.read();
                if(letter == 'X')
                {
                    int specialAbilityCooldown, specialAbilityTurns; boolean isSpecialAbilityActive;
                    Human human = new Human(x, y, world);
                    human.setStrength(strength); human.setAge(age); human.setHumanDirection(0,0);
                    specialAbilityCooldown = file.read(); isSpecialAbilityActive = (file.read() == 1);  specialAbilityTurns = file.read();
                    human.setSpecialAbilityActive(isSpecialAbilityActive); human.setSpecialAbilityCooldown(specialAbilityCooldown); human.setSpecialAbilityTurns(specialAbilityTurns);
                    this.world.setHumanState(true);
                    human.setAlive(true);
                    this.world.addOrganism(human);
                }
                else if(letter == 'A')
                {
                    Antelope organism = new Antelope(x,y,world);
                    organism.setAge(age);
                    organism.setStrength(strength);
                    this.world.addOrganism(organism);
                }
                else if(letter == 'F')
                {
                    Fox organism = new Fox(x,y,world);
                    organism.setAge(age);
                    organism.setStrength(strength);
                    this.world.addOrganism(organism);
                }
                else if(letter == 'G')
                {
                    Grass organism = new Grass(x,y,world);
                    organism.setAge(age);
                    organism.setStrength(strength);
                    this.world.addOrganism(organism);
                }
                else if(letter == 'U')
                {
                    Guarana organism = new Guarana(x,y,world);
                    organism.setAge(age);
                    organism.setStrength(strength);
                    this.world.addOrganism(organism);
                }
                else if(letter == 'M')
                {
                    Milt organism = new Milt(x,y,world);
                    organism.setAge(age);
                    organism.setStrength(strength);
                    this.world.addOrganism(organism);
                }
                else if(letter == 'S')
                {
                    Sheep organism = new Sheep(x,y,world);
                    organism.setAge(age);
                    organism.setStrength(strength);
                    this.world.addOrganism(organism);
                }
                else if(letter == 'H')
                {
                    SosnowskyHogwood organism = new SosnowskyHogwood(x,y,world);
                    organism.setAge(age);
                    organism.setStrength(strength);
                    this.world.addOrganism(organism);
                }
                else if(letter == 'T')
                {
                    Turtle organism = new Turtle(x,y,world);
                    organism.setAge(age);
                    organism.setStrength(strength);
                    this.world.addOrganism(organism);
                }
                else if(letter == 'W')
                {
                    Wolf organism = new Wolf(x,y,world);
                    organism.setAge(age);
                    organism.setStrength(strength);
                    this.world.addOrganism(organism);
                }
            }
            System.out.println("LOADED");
        }finally
        {
            if(file != null)
            {
                file.close();
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(world.isHumanState() == true) {
            if (key == KeyEvent.VK_LEFT) {
                world.getHuman().setHumanDirection(0, -1);
            } else if (key == KeyEvent.VK_UP) {
                world.getHuman().setHumanDirection(-1, 0);
            } else if (key == KeyEvent.VK_RIGHT) {
                world.getHuman().setHumanDirection(0, 1);
            } else if (key == KeyEvent.VK_DOWN) {
                world.getHuman().setHumanDirection(1, 0);
            } else if (key == KeyEvent.VK_Q) {
                world.getHuman().setHumanSpecialAbility();
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
