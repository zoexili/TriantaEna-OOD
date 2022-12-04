package Player;

import java.util.ArrayList;

public class teTeam extends Team{
    public int Score;
    public int money;


    protected static int[] win_times;

    public teTeam() {
        super();
        this.name = "";
        this.Score = 0;
        this.money = money;
    }

    public teTeam(String name) {
        super();
        this.name = name;
        this.Score = 0;
    }

    public int getMoney() {
        return money;
    }

    public void win() {
        this.Score++;
    }

    public void lose() {
        this.Score--;
    }

    @Override
    public void addPlayer(Player p) {
        team.add(p);
    }

    @Override
    public void removePlayer(Player p) {
        team.remove(p);
    }

    public tePlayer get(int index)
    {
        return (tePlayer)team.get(index);
    }

}
