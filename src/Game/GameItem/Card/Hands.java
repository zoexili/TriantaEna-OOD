package Game.GameItem.Card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hands extends CardsPool{
    int AceNumber;
    int valExpAce;
    public Hands()
    {
        AceNumber=0;
        valExpAce=0;
    }
    public Card getOneCard()
    {
        if (cards.isEmpty()) return null;
        Card c=cards.get(0);
        cards.remove(0);
        if (c.getValue()==1) AceNumber--;
        else valExpAce-=c.getValue();
        return c;
    }
    @Override public void addCard(Card c)
    {
        cards.add(c);
        if (c.getValue()==1) AceNumber++;
        else valExpAce+=c.getValue();
    }

    public int getMinVal()
    {
        if (AceNumber==0) return valExpAce;
        else return valExpAce+AceNumber*11-10;
    }

    public int getFinalVal()
    {
        if (AceNumber==0) return valExpAce;
        if (AceNumber>1) return valExpAce+AceNumber*11-10;
        int val1=valExpAce+1;
        int val11=valExpAce+11;
        if (val11<=31) return val11;
        else return val1;
    }
}
