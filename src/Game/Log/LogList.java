package Game.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class LogList {
    private ArrayList<GameLog> loglist;
    private int stalemate;
    public LogList()
    {
        stalemate=0;
        loglist=new ArrayList<GameLog>();
    }
    public void addlog(GameLog log)
    {
        loglist.add(log);
    }
    public void printlog()
    {
        for (int i=0;i<loglist.size();i++)
            System.out.println(loglist.get(i));
    }
    public void clear()
    {
        stalemate=0;
        loglist.clear();
    }
}
