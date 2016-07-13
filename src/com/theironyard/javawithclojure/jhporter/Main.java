package com.theironyard.javawithclojure.jhporter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Main {

    static HashSet<Card> createDeck()
    {
        HashSet<Card> deck = new HashSet<>();
        for (Card.Suit suit : Card.Suit.values())
        {
            for (Card.Rank rank : Card.Rank.values())
            {
                Card c = new Card(suit, rank);
                deck.add(c);
            }
        }
        return deck;
    }

    static HashSet<HashSet<Card>> createHands(HashSet<Card> deck)
    {
        HashSet<HashSet<Card>> hands = new HashSet<>();

        for(Card c1 : deck)
        {
            HashSet<Card> deck2 = (HashSet<Card>)deck.clone();
            deck2.remove(c1);
            for(Card c2 : deck2)
            {
                HashSet<Card> deck3 = (HashSet<Card>)deck2.clone();
                deck3.remove(c2);
                for(Card c3 : deck3)
                {
                    HashSet<Card> deck4 = (HashSet<Card>) deck3.clone();
                    deck4.remove(c3);
                    for (Card c4 : deck4)
                    {
                        HashSet<Card> hand = new HashSet<>();
                        hand.add(c1);
                        hand.add(c2);
                        hand.add(c3);
                        hand.add(c4);
                        hands.add(hand);
                    }
                }
            }
        }
        return hands;
    }

    public static boolean isFlush(HashSet<Card> hand)
    {
        HashSet<Card.Suit> suits = hand.stream()
                .map(card -> card.suit)
                .collect(Collectors.toCollection(HashSet<Card.Suit>::new));
        return suits.size() == 1;
    }

    public static boolean isStraight(HashSet<Card> hand)
    {
        boolean isAStraight = false;

        int c1=0, c2=0, c3=0, c4=0, i=1;
        for(Card card : hand)
        {

            switch (i)
            {
                case 1:
                    c1 = card.rank.ordinal();
                    i++;
                    break;
                case 2:
                    c2 = card.rank.ordinal();
                    i++;
                    break;
                case 3:
                    c3 = card.rank.ordinal();
                    i++;
                    break;
                case 4:
                    c4 = card.rank.ordinal();
                    break;
            }
        }

        //order them.
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        for(i = 0; i<cards.size();i++)
        {
            for (int j = 0; j<cards.size();j++)
            {
                int tmp = 0;
                if (cards.get(i) < cards.get(j))
                {
                    tmp = cards.get(i);
                    cards.set(i, cards.get(j));
                    cards.set(j, tmp);
                }
            }
        }
        if ((cards.get(3)-cards.get(2)==1) && (cards.get(3)-cards.get(2)==1) && cards.get(2)-cards.get(1)==1)
        {
            if ((cards.get(1)-cards.get(0)==1) || (cards.get(1)-cards.get(0)==10))
            {
                isAStraight = true;
            }
        }

        return isAStraight;
    }

    public static boolean isFourOfAKind(HashSet<Card> hand)
    {
        HashSet<Card.Rank> ranks = hand.stream()
                .map(card -> card.rank)
                .collect(Collectors.toCollection(HashSet<Card.Rank>::new));
        return ranks.size() == 1;
    }

    public static boolean isThreeOfAKind(HashSet<Card> hand)
    {
        return isXOfAKind(hand, 3);
    }

    public static boolean isTwoPair(HashSet<Card> hand)
    {
        HashSet<Card.Rank> ranks = hand.stream()
                .map(card -> card.rank)
                .collect(Collectors.toCollection(HashSet<Card.Rank>::new));
        if (ranks.size() == 2)
        {
            return isXOfAKind(hand, 2);
        }
        return false;
    }

    public static boolean isXOfAKind(HashSet<Card> hand, int size)
    {
        int ac=0,tw=0 ,th=0 ,fo=0 ,fi=0 ,si=0 ,se=0,ei=0,ni=0,te=0,ja=0,qu=0,ki=0;
        boolean isBlankOfAKind = false;
        for (Card card: hand)
        {
            switch (card.rank)
            {
                case ACE:
                    ac++;
                    break;
                case TWO:
                    tw++;
                    break;
                case THREE:
                    th++;
                    break;
                case FOUR:
                    fo++;
                    break;
                case FIVE:
                    fi++;
                    break;
                case SIX:
                    si++;
                    break;
                case SEVEN:
                    se++;
                    break;
                case EIGHT:
                    ei++;
                    break;
                case NINE:
                    ni++;
                    break;
                case TEN:
                    te++;
                    break;
                case JACK:
                    ja++;
                    break;
                case QUEEN:
                    qu++;
                    break;
                case KING:
                    ki++;
                    break;
            }
        }
        if (ac==size||tw==size||th==size||fo==size||fi==size||si==size||se==size||ei==size||ni==size||te==size||ja==size||qu==size||ki==size)
        {
            isBlankOfAKind = true;
        }
        return isBlankOfAKind;
    }

    public static boolean isStraightFlush(HashSet<Card> hand)
    {
        boolean isAFlush = isFlush(hand);
        boolean isAStraight = isStraight(hand);

        if(isAFlush && isAStraight)
        {
            return true;
        }
        return false;
    }


    public static void main(String[] args)
    {
        HashSet<Card> deck = createDeck();
        HashSet<HashSet<Card>> hands = createHands(deck);
        HashSet<HashSet<Card>> flushes = hands.stream()
                .filter(Main::isFlush)
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));
        System.out.println(flushes.size());
    }
}
