package Game.GameItem.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardsPool {
    protected List<Card> cards;
    public CardsPool()
    {
        cards=new ArrayList<Card>();
    }

    public void addCard(Card c)
    {
        cards.add(c);
    }

    public void reshuffle()
    {
        Collections.shuffle(cards);
    }

    public Card get(int index)
    {
        return cards.get(index);
    }
    public Card getOneCard()
    {
        if (cards.isEmpty()) return null;
        Card c=cards.get(0);
        cards.remove(0);
        return c;
    }
    public int size()
    {
        return cards.size();
    }

    public boolean isEmpty()
    {
        return cards.isEmpty();
    }


}
