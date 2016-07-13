import com.theironyard.javawithclojure.jhporter.Card;
import com.theironyard.javawithclojure.jhporter.Main;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertTrue;

/**
 * Created by jeffryporter on 7/13/16.
 */
public class MainTest
{
    @Test
    public void testIsStraightFlush()
    {
        HashSet<Card> h1 = new HashSet<>();
        HashSet<Card> h2 = new HashSet<>();
        HashSet<Card> h3 = new HashSet<>();
        Card c1 = new Card(Card.Suit.SPADES,Card.Rank.ACE);
        Card c2 = new Card(Card.Suit.SPADES,Card.Rank.KING);
        Card c3 = new Card(Card.Suit.SPADES,Card.Rank.QUEEN);
        Card c4 = new Card(Card.Suit.SPADES,Card.Rank.JACK);
        Card c5 = new Card(Card.Suit.SPADES,Card.Rank.THREE);
        Card c6 = new Card(Card.Suit.HEARTS,Card.Rank.JACK);
        h1.add(c1);
        h1.add(c2);
        h1.add(c3);
        h1.add(c4);
        h2.add(c1);
        h2.add(c2);
        h2.add(c3);
        h2.add(c5);
        h3.add(c1);
        h3.add(c2);
        h3.add(c3);
        h3.add(c6);

        assertTrue(Main.isStraightFlush(h1));
        assertTrue(!Main.isStraightFlush(h2));
        assertTrue(!Main.isStraightFlush(h3));
    }

    @Test
    public void testStraight()
    {
        HashSet<Card> h1 = new HashSet<>();
        HashSet<Card> h2 = new HashSet<>();
        Card c1 = new Card(Card.Suit.SPADES,Card.Rank.ACE);
        Card c2 = new Card(Card.Suit.HEARTS,Card.Rank.KING);
        Card c3 = new Card(Card.Suit.DIAMONDS,Card.Rank.QUEEN);
        Card c4 = new Card(Card.Suit.CLUBS,Card.Rank.JACK);
        Card c5 = new Card(Card.Suit.CLUBS,Card.Rank.THREE);
        h1.add(c1);
        h1.add(c2);
        h1.add(c3);
        h1.add(c4);
        h2.add(c1);
        h2.add(c2);
        h2.add(c3);
        h2.add(c5);

        assertTrue(Main.isStraight(h1));
        assertTrue(!Main.isStraight(h2));


    }

    @Test
    public void testFourOfAKind()
    {
        HashSet<Card> h1 = new HashSet<>();
        HashSet<Card> h2 = new HashSet<>();
        Card c1 = new Card(Card.Suit.SPADES,Card.Rank.ACE);
        Card c2 = new Card(Card.Suit.HEARTS,Card.Rank.ACE);
        Card c3 = new Card(Card.Suit.DIAMONDS,Card.Rank.ACE);
        Card c4 = new Card(Card.Suit.CLUBS,Card.Rank.ACE);
        Card c5 = new Card(Card.Suit.CLUBS,Card.Rank.THREE);
        h1.add(c1);
        h1.add(c2);
        h1.add(c3);
        h1.add(c4);
        h2.add(c1);
        h2.add(c2);
        h2.add(c3);
        h2.add(c5);

        assertTrue(Main.isFourOfAKind(h1));
        assertTrue(!Main.isFourOfAKind(h2));
    }

    @Test
    public void testThreeOfAKind()
    {
        HashSet<Card> h1 = new HashSet<>();
        HashSet<Card> h2 = new HashSet<>();
        Card c1 = new Card(Card.Suit.SPADES,Card.Rank.ACE);
        Card c2 = new Card(Card.Suit.HEARTS,Card.Rank.ACE);
        Card c3 = new Card(Card.Suit.DIAMONDS,Card.Rank.FIVE);
        Card c4 = new Card(Card.Suit.CLUBS,Card.Rank.ACE);
        Card c5 = new Card(Card.Suit.CLUBS,Card.Rank.THREE);
        h1.add(c1);
        h1.add(c2);
        h1.add(c3);
        h1.add(c4);
        h2.add(c1);
        h2.add(c2);
        h2.add(c3);
        h2.add(c5);

        assertTrue(Main.isThreeOfAKind(h1));
        assertTrue(!Main.isThreeOfAKind(h2));
    }

    @Test
    public void testTowPair()
    {
        HashSet<Card> h1 = new HashSet<>();
        HashSet<Card> h2 = new HashSet<>();
        Card c1 = new Card(Card.Suit.SPADES,Card.Rank.ACE);
        Card c2 = new Card(Card.Suit.HEARTS,Card.Rank.ACE);
        Card c3 = new Card(Card.Suit.DIAMONDS,Card.Rank.FIVE);
        Card c4 = new Card(Card.Suit.CLUBS,Card.Rank.FIVE);
        Card c5 = new Card(Card.Suit.CLUBS,Card.Rank.THREE);
        h1.add(c1);
        h1.add(c2);
        h1.add(c3);
        h1.add(c4);
        h2.add(c1);
        h2.add(c2);
        h2.add(c3);
        h2.add(c5);

        assertTrue(Main.isTwoPair(h1));
        assertTrue(!Main.isTwoPair(h2));
    }



}
