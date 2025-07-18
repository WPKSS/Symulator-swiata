package com.company;

import javafx.util.Pair;

import java.util.Vector;

public class Fox extends Animal{
    public Fox(int x, int y, World world)
    {
        super('F',3,7,x,y,world);
    }
    @Override
    public Fox child()
    {
        return new Fox(this.getX(),this.getY(),world);
    }
    @Override
    public void action()
    {
        Vector<Organism> neighbours = world.getNeighbours(this);
        int availableToAttack = 0;
        if (neighbours.size() == world.freeSidesOrganism(this))
        {
            for (Organism organism : neighbours)
            {
                if (organism.getStrength() <= this.getStrength())
                {
                    availableToAttack++;
                }
            }
            if (availableToAttack == 0)
            {
                return;
            }
        }

        Pair<Integer, Integer> coordinates = null;
        int k = 0;
        do {
            if(k>30)
            {
                return;
            }
            do {
                coordinates = world.findPosition(this.getX(), this.getY(), 1);
            } while (coordinates.getKey() == -1 && coordinates.getValue() == -1);
            if(world.isEmpty(coordinates.getKey(), coordinates.getValue()) == true)
            {
                break;
            }
            k++;
        }while(world.getOrganism(coordinates.getKey(), coordinates.getValue()).getStrength() > this.getStrength());

        move(coordinates.getKey(),coordinates.getValue());

        return;
    }
    @Override
    public String print()
    {
        String toPrint = "Fox [" + this.getX() + "," + this.getY() + "]";
        return toPrint;
    }
}
