package com.company;

import javafx.util.Pair;

import java.util.Vector;

public class HexWorld extends World{
    public HexWorld(int width, int height)
    {
        super(height,width);
    }
    public HexWorld(int width, int height, int turn)
    {
        super(height,width,turn);
    }

    @Override
    public int freeSidesOrganism(Organism organism)
    {
        int freeSides = 0;
        if (organism.getX() - 1 >= 1)
        {
            freeSides++;
        }
        if (organism.getX() + 1 <= width)
        {
            freeSides++;
        }
        if (organism.getY() - 1 >= 1 && organism.getX() - 1 >= 1)
        {
            freeSides++;
        }
        if(organism.getY() - 1 >= 1 && organism.getX() + 1 <= width)
        {
            freeSides++;
        }
        if (organism.getY() + 1 <= height && organism.getX() - 1 >= 1)
        {
            freeSides++;
        }
        if(organism.getY() + 1 <= height && organism.getX() + 1 <= width)
        {
            freeSides++;
        }
        return freeSides;
    }
    @Override
    public Pair<Integer,Integer> findPosition(int x, int y, int range)
    {
        switch((int)(Math.random() * 6))
        {
            case 0:
                if(checkBoundaries(x+range,y))
                {
                    return new Pair<>(-1,-1);
                }
                return new Pair<>(x+range,y);
            case 1:
                if(checkBoundaries(x-range,y))
                {
                    return new Pair<>(-1,-1);
                }
                return new Pair<>(x-range,y);
            case 2:
                if(checkBoundaries(x-range,y+range))
                {
                    return new Pair<>(-1,-1);
                }
                return new Pair<>(x-range,y+range);
            case 3:
                if(checkBoundaries(x+range,y+range))
                {
                    return new Pair<>(-1,-1);
                }
                return new Pair<>(x+range,y+range);
            case 4:
                if(checkBoundaries(x+range,y-range))
                {
                    return new Pair<>(-1,-1);
                }
                return new Pair<>(x+range,y-range);
            case 5:
                if(checkBoundaries(x-range,y-range))
                {
                    return new Pair<>(-1,-1);
                }
                return new Pair<>(x-range,y-range);
        }
        return new Pair<>(-1,-1);
    }
    @Override
    public Vector<Organism> getNeighbours(Organism organism)
    {
        Vector<Organism> neighbours = new Vector<>();
        if (organism.getX() - 1 >= 1)
        {
            if (!isEmpty(organism.getX() - 1, organism.getY()))
            {
                neighbours.add(getOrganism(organism.getX() - 1, organism.getY()));
            }
        }
        if (organism.getX() + 1 <= width)
        {
            if (!isEmpty(organism.getX() + 1, organism.getY()))
            {
                neighbours.add(getOrganism(organism.getX() + 1, organism.getY()));
            }
        }
        if (organism.getY() - 1 >= 1  && organism.getX() - 1 >= 1)
        {
            if (!isEmpty(organism.getX() - 1, organism.getY() - 1))
            {
                neighbours.add(getOrganism(organism.getX() - 1, organism.getY() - 1));
            }
        }
        if (organism.getY() - 1 >= 1 && organism.getX() + 1 <= width)
        {
            if (!isEmpty(organism.getX() + 1, organism.getY() - 1))
            {
                neighbours.add(getOrganism(organism.getX() + 1, organism.getY() - 1));
            }
        }
        if (organism.getY() + 1 <= height && organism.getX() - 1 >= 1)
        {
            if (!isEmpty(organism.getX() - 1, organism.getY() + 1))
            {
                neighbours.add(getOrganism(organism.getX() - 1, organism.getY() + 1));
            }
        }
        if (organism.getY() + 1 <= height && organism.getX() + 1 <= width)
        {
            if (!isEmpty(organism.getX() + 1, organism.getY() + 1))
            {
                neighbours.add(getOrganism(organism.getX() + 1, organism.getY() + 1));
            }
        }
        return neighbours;
    }
}
