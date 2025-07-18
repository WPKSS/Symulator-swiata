package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameHex extends  Game{
    class HexagonButton extends JButton {
        private long serialVersionUID = 1L;
        private int side = 50;
        public int length = 95;
        public int width = 105;

        public HexagonButton() {
            setContentAreaFilled(false);
            setFocusPainted(true);
            setBorderPainted(false);
            setPreferredSize(new Dimension(width, length));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Polygon hex = new Polygon();
            for (int i = 0; i < 6; i++) {
                hex.addPoint((int) (2 + ((width)) * Math.cos(i * 2 * Math.PI / 6)), //calculation for side
                        (int) (2 + side * Math.sin(i * 2 * Math.PI / 6)));   //calculation for side
            }
            g.drawPolygon(hex);
        }
       /* public void addLetter(char letter)
        {
            JLabel text = new JLabel();
            text.setLabel
        }*/
    }

    public GameHex()
    {
    }
    public GameHex(World world)
    {
        this.world = world;
        world.nextTurn();
    }

    @Override
    public void gameWindow()
    {
        this.window = new JFrame();
        this.window.setSize(700,700);
        this.window.addKeyListener(this);
        window.setFocusable(true);
        board = new JPanel(new GridLayout(world.getWidth(), world.getHeight()));
        HexagonButton[][] buttons = new HexagonButton[world.getWidth()][world.getHeight()];
        for(int i  = 0; i < world.getWidth(); i++)
        {
            for (int j = 0; j < world.getHeight(); j++) {
                buttons[i][j] = new HexagonButton();
                int finalI = i;
                int finalJ = j;
                if(world.getOrganism(finalI +1, finalJ +1) != null) {
                    buttons[finalI][finalJ].setText(" " + world.getOrganism(finalI + 1, finalJ + 1).getLetter());
                    if(world.getOrganism(finalI+1,finalJ+1).isHuman() == true)
                    {
                        buttons[i][j].setBackground(Color.GRAY);
                    }
                }
                board.add(buttons[i][j]);
                buttons[i][j].addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if(world.getOrganism(finalI +1, finalJ +1) == null && ((finalI+1)%2 == 0 || (finalJ+1) %2 == 0)) {
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
        toolBar.add(new JLabel("Use numpad to move, \n Q to use special ability"));
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
        HexagonButton[][] buttons = new HexagonButton[world.getWidth()][world.getHeight()];
        for (int i = 0; i < world.getWidth(); i++) {
            for (int j = 0; j < world.getHeight(); j++) {
                buttons[i][j] = new HexagonButton();
                int finalI = i;
                int finalJ = j;
                if((i +1) % 2 == 1 && (j+1) % 2 == 1)
                {
                    buttons[i][j].setBackground(Color.BLACK);
                }
                if (world.getOrganism(finalI + 1, finalJ + 1) != null) {
                    buttons[finalI][finalJ].setText(" " + world.getOrganism(finalI + 1, finalJ + 1).getLetter());
                    if(world.getOrganism(finalI+1,finalJ+1).isHuman() == true)
                    {
                        buttons[i][j].setBackground(Color.GRAY);
                    }
                }
                buttons[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (world.getOrganism(finalI + 1, finalJ + 1) == null && ((finalI+1)%2 == 0 || (finalJ+1) %2 == 0)) {
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
}
