package com.company;

import javafx.util.Pair;

import java.util.Vector;

public abstract class World {
    protected int height, width, turn;
    private boolean humanState;
    private Vector<Organism> organisms;
    private Vector<String> logs;
    public World()
    {
        width = 20;
        height = 20;
        turn = 0;
        organisms = new Vector<>();
        logs = new Vector<>();
    }
    public World(int height, int width, int turn) {
        this.height = height;
        this.width = width;
        this.turn = turn;
        organisms = new Vector<>();
        logs = new Vector<>();
    }
    public World(int height, int width) {
        this.height = height;
        this.width = width;
        turn = 0;
        organisms = new Vector<>();
        logs = new Vector<>();
    }

    final public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    final public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    final public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    final public boolean isHumanState() {
        return humanState;
    }

    public void setHumanState(boolean humanState) {
        this.humanState = humanState;
    }

    final public Organism getOrganism(int x, int y)
    {
        for(Organism organism : organisms)
        {
            if(organism.getX() == x && organism.getY() == y)
            {
                return organism;
            }
        }
        return null;
    }

    public boolean isEmpty(int x, int y)
    {
        if(getOrganism(x,y) != null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

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
        if (organism.getY() - 1 >= 1)
        {
            freeSides++;
        }
        if (organism.getY() + 1 <= height)
        {
            freeSides++;
        }
        return freeSides;
    }

    public boolean checkBoundaries(int x, int y)
    {
        if(x > width || y > height || x< 1 || y < 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Pair<Integer,Integer> findPosition(int x, int y, int range)
    {
        switch((int)(Math.random() * 4))
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
                if(checkBoundaries(x,y+range))
                {
                    return new Pair<>(-1,-1);
                }
                return new Pair<>(x,y+range);
            case 3:
                if(checkBoundaries(x,y-range))
                {
                    return new Pair<>(-1,-1);
                }
                return new Pair<>(x,y-range);
        }
        return new Pair<>(-1,-1);
    }

    public Pair<Integer,Integer> findFreePosition() {
        if (organisms.size() >= width * height)
        {
            return new Pair<>(-1,-1);
        }
        else
        {
            int k = 0;
            do{
                int x = (int)(Math.random() * width + 1);
                int y = (int)(Math.random() * height + 1);
                if(getOrganism(x,y) == null)
                {
                    return new Pair<>(x,y);
                }
                k++;
            }while(k < 100);
            return new Pair<>(-1,-1);
        }
    }
    public void addOrganism(Organism organism)
    {
        organisms.add(organism);
    }

    public void removeOrganism(Organism organism)
    {
        organisms.remove(organism);
    }

    final public Vector<Organism> getOrganisms()
    {
        return organisms;
    }

    public Vector<Organism> getNeighbours(Organism organism)
    {
        Vector<Organism> neighbours = new Vector<>();
        if (organism.getX() - 1 > 1)
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
        if (organism.getY() - 1 > 1)
        {
            if (!isEmpty(organism.getX(), organism.getY() - 1))
            {
                neighbours.add(getOrganism(organism.getX(), organism.getY() - 1));
            }
        }
        if (organism.getY() + 1 <= height)
        {
            if (!isEmpty(organism.getX(), organism.getY() + 1))
            {
                neighbours.add(getOrganism(organism.getX(), organism.getY() + 1));
            }
        }
        return neighbours;
    }

    public Pair<Integer,Integer> findFreeArea(Organism first)
    {
        for (int k = 0; k < (Math.max(height, width)); k++)
        {
            for (int i = (Math.max((first.getY() - k), 1)); i < (Math.min((first.getY() + k), this.getHeight())); i++)
            {
                for (int j = (Math.max((first.getX() - k), 1)); j < (Math.min((first.getX() + k), this.getWidth())); j++)
                {
                    if (isEmpty(i, j) == true)
                    {
                        return new Pair<>(i,j);
                    }
                }
            }
        }
        return new Pair<>(-1,-1);
    }

    public void eraseDead(Vector<Organism> organisms)
    {
        for(int i = 0; i<organisms.size(); i++)
        {
            if(organisms.get(i).getAlive() == false)
            {
                removeOrganism(organisms.get(i));
            }
        }
    }

    final public Human getHuman()
    {
        for(Organism organism : organisms)
        {
            if(organism.isHuman() == true)
            {
                return (Human)organism;
            }
        }
        return null;
    }


    final public int getOrganismsAmount()
    {
        return organisms.size();
    }

    public void addLog(String log)
    {
        logs.add(log);
        return;
    }

    private void addHuman()
    {
        Pair<Integer, Integer> coordinates = findFreePosition();
        Human human = new Human(coordinates.getKey(), coordinates.getValue(), this);
        addOrganism(human);
        this.setHumanState(true);
    }

    private void addPopulation()
    {
        float factor =  5; // sqrt(width * height) + 0.5;
        Pair<Integer, Integer> coordinates = findFreePosition();
        for (int i = 0; i < 1 + (int)factor; i++)
        {
            Antelope antelope = new Antelope(coordinates.getKey(), coordinates.getValue(), this);
            coordinates = findFreePosition();
            if (coordinates.getKey() == -1 && coordinates.getValue() == -1)
            {
                break;
            }
            addOrganism(antelope);

            Wolf wolf = new Wolf(coordinates.getKey(), coordinates.getValue(), this);
            addOrganism(wolf);
            coordinates = findFreePosition();

            Sheep sheep = new Sheep(coordinates.getKey(), coordinates.getValue(), this);
            addOrganism(sheep);
            coordinates = findFreePosition();

            Turtle turtle = new Turtle(coordinates.getKey(), coordinates.getValue(), this);
            addOrganism(turtle);
            coordinates = findFreePosition();

            Fox fox = new Fox(coordinates.getKey(), coordinates.getValue(), this);
            addOrganism(fox);
            coordinates = findFreePosition();

            Milt milt = new Milt(coordinates.getKey(), coordinates.getValue(), this);
            addOrganism(milt);
            coordinates = findFreePosition();

            SosnowskyHogwood sosnowskyHogwood = new SosnowskyHogwood(coordinates.getKey(), coordinates.getValue(), this);
            addOrganism(sosnowskyHogwood);
            coordinates = findFreePosition();

            Grass grass = new Grass(coordinates.getKey(), coordinates.getValue(), this);
            addOrganism(grass);
            coordinates = findFreePosition();

            Guarana guarana = new Guarana(coordinates.getKey(), coordinates.getValue(), this);
            addOrganism(guarana);
            coordinates = findFreePosition();

            KeenBerry keenBerry = new KeenBerry(coordinates.getKey(), coordinates.getValue(), this);
            addOrganism(keenBerry);
            coordinates = findFreePosition();
        }
    }

    private void manageGameSystem()
    {
        organisms.sort((a,b) -> {
           if(a.getInitiative() == b.getInitiative())
           {
               return Integer.signum(a.getAge() - b.getAge());
           }
           return Integer.signum(a.getInitiative() - b.getInitiative());
        });
        int sizeBefore = organisms.size();
        for (int i = 0; i < sizeBefore; i++) {
            if(organisms.get(i).getAlive() == true && organisms.get(i).getAge() > 0)
            {
                organisms.get(i).action();
            }
            organisms.get(i).incrementAge();
        }
        eraseDead(organisms);
    }

    private void writeLogs()
    {
        for (int i = 0; i < logs.size(); i++) {
            System.out.println(logs.get(i));
        }
        logs.clear();
    }

    public void nextTurn()
    {
        if(turn == 0)
        {
            addPopulation();
            addHuman();
            turn++;
            return;
        }
        manageGameSystem();
        writeLogs();
        turn++;
    }
}
