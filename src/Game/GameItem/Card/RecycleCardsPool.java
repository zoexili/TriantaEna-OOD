package Game.GameItem.Card;


public class RecycleCardsPool {
    CardsPool usedCards;
    CardsPool unusedCards;
    public RecycleCardsPool()
    {
        usedCards=new CardsPool();
        unusedCards=new CardsPool();
    }

    public void addCard(Card c)
    {
        unusedCards.addCard(c);
    }

    public void reshuffle()
    {
        unusedCards.reshuffle();
    }

    public Card get(int index)
    {
        return unusedCards.get(index);
    }

    public Card getOneCard()
    {
        if (unusedCards.isEmpty())
        {
            while (usedCards.isEmpty())
            {
                Card c=usedCards.getOneCard();
                unusedCards.addCard(c);
            }
            unusedCards.reshuffle();
        }
        return unusedCards.getOneCard();
    }

    public void dropCard(Card c)
    {
        usedCards.addCard(c);
    }
}
