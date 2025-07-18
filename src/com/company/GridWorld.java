package com.company;

import javafx.util.Pair;

import java.util.Vector;

public class GridWorld extends World{
    public GridWorld(int width, int height)
    {
        super(height,width);
    }
    public GridWorld(int width, int height, int turn)
    {
        super(height,width,turn);
    }
    @Override
    public boolean checkBoundaries(int x, int y)
    {
        if(x > width || y > height || x< 1 || y < 1 || (x%2 == 1 && y%2 == 1))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    @Override
    public Vector<Organism> getNeighbours(Organism organism)
    {
        Vector<Organism> neighbours = new Vector<>();
        if (organism.getX() - 1 > 1 && organism.getX() % 2 == 0)
        {
            if (!isEmpty(organism.getX() - 1, organism.getY()))
            {
                neighbours.add(getOrganism(organism.getX() - 1, organism.getY()));
            }
        }
        if (organism.getX() + 1 <= width && organism.getX() % 2 == 0)
        {
            if (!isEmpty(organism.getX() + 1, organism.getY()))
            {
                neighbours.add(getOrganism(organism.getX() + 1, organism.getY()));
            }
        }
        if (organism.getY() - 1 > 1 && organism.getY() % 2 == 0)
        {
            if (!isEmpty(organism.getX(), organism.getY() - 1))
            {
                neighbours.add(getOrganism(organism.getX(), organism.getY() - 1));
            }
        }
        if (organism.getY() + 1 <= height && organism.getY() % 2 == 0)
        {
            if (!isEmpty(organism.getX(), organism.getY() + 1))
            {
                neighbours.add(getOrganism(organism.getX(), organism.getY() + 1));
            }
        }
        return neighbours;
    }
    @Override
     public Pair<Integer,Integer> findFreeArea(Organism first)
    {
        for (int k = 0; k < (Math.max(height, width)); k++)
        {
            for (int i = (Math.max((first.getY() - k), 1)); i < (Math.min((first.getY() + k), this.getHeight())); i++)
            {
                for (int j = (Math.max((first.getX() - k), 1)); j < (Math.min((first.getX() + k), this.getWidth())); j++)
                {
                    if (isEmpty(i, j) == true && (i % 2 == 0 || j%2 == 0))
                    {
                        return new Pair<>(i,j);
                    }
                }
            }
        }
        return new Pair<>(-1,-1);
    }
    @Override
    public Pair<Integer,Integer> findFreePosition() {
        Vector<Organism> temp = getOrganisms();
        if (temp.size() >= width * height)
        {
            return new Pair<>(-1,-1);
        }
        else
        {
            int k = 0;
            do{
                int x = (int)(Math.random() * width + 1);
                int y = (int)(Math.random() * height + 1);
                if(getOrganism(x,y) == null && (x % 2 == 0 || y%2 == 0))
                {
                    return new Pair<>(x,y);
                }
                k++;
            }while(k < 100);
            return new Pair<>(-1,-1);
        }
    }
    @Override
    public int freeSidesOrganism(Organism organism)
    {
        int freeSides = 0;
        if (organism.getX() - 1 >= 1 && organism.getX() % 2 == 0)
        {
            freeSides++;
        }
        if (organism.getX() + 1 <= width && organism.getX() % 2 == 0)
        {
            freeSides++;
        }
        if (organism.getY() - 1 >= 1 && organism.getY() % 2 == 0)
        {
            freeSides++;
        }
        if (organism.getY() + 1 <= height && organism.getY() % 2 == 0)
        {
            freeSides++;
        }
        return freeSides;
    }

}
