package Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class GameSet
{
    private List<Game> gamelist;
    public GameSet()
    {
        gamelist=new ArrayList<Game>();
    }
    public void addGame(Game g)
    {
        gamelist.add(g);
    }
    public Game getGame(int index)
    {
        return gamelist.get(index);
    }
    public void showlist()
    {
        for (int i=0;i<gamelist.size();i++)
            System.out.println(i+":"+gamelist.get(i).getName());
    }
    public int size()
    {
        return gamelist.size();
    }

    public void run(int index) throws IOException, InterruptedException {
        gamelist.get(index).run();
    }
}
