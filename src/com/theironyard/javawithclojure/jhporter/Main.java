package com.theironyard.javawithclojure.jhporter;

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
        if (hand.contains(Card.Rank.ACE) && hand.contains(Card.Rank.TWO) && hand.contains(Card.Rank.THREE) && hand.contains(Card.Rank.FOUR))
        {
            isAStraight = true;
        }
        if (hand.contains(Card.Rank.FIVE) && hand.contains(Card.Rank.TWO) && hand.contains(Card.Rank.THREE) && hand.contains(Card.Rank.FOUR))
        {
            isAStraight = true;
        }
        if (hand.contains(Card.Rank.FIVE) && hand.contains(Card.Rank.SIX) && hand.contains(Card.Rank.THREE) && hand.contains(Card.Rank.FOUR))
        {
            isAStraight = true;
        }
        if (hand.contains(Card.Rank.FIVE) && hand.contains(Card.Rank.SIX) && hand.contains(Card.Rank.SEVEN) && hand.contains(Card.Rank.FOUR))
        {
            isAStraight = true;
        }
        if (hand.contains(Card.Rank.FIVE) && hand.contains(Card.Rank.SIX) && hand.contains(Card.Rank.SEVEN) && hand.contains(Card.Rank.EIGHT))
        {
            isAStraight = true;
        }
        if (hand.contains(Card.Rank.NINE) && hand.contains(Card.Rank.SIX) && hand.contains(Card.Rank.SEVEN) && hand.contains(Card.Rank.EIGHT))
        {
            isAStraight = true;
        }
        if (hand.contains(Card.Rank.NINE) && hand.contains(Card.Rank.TEN) && hand.contains(Card.Rank.SEVEN) && hand.contains(Card.Rank.EIGHT))
        {
            isAStraight = true;
        }
        if (hand.contains(Card.Rank.NINE) && hand.contains(Card.Rank.TEN) && hand.contains(Card.Rank.JACK) && hand.contains(Card.Rank.EIGHT))
        {
            isAStraight = true;
        }
        if (hand.contains(Card.Rank.NINE) && hand.contains(Card.Rank.TEN) && hand.contains(Card.Rank.JACK) && hand.contains(Card.Rank.QUEEN))
        {
            isAStraight = true;
        }
        if (hand.contains(Card.Rank.KING) && hand.contains(Card.Rank.TEN) && hand.contains(Card.Rank.JACK) && hand.contains(Card.Rank.QUEEN))
        {
            isAStraight = true;
        }
        if (hand.contains(Card.Rank.KING) && hand.contains(Card.Rank.ACE) && hand.contains(Card.Rank.JACK) && hand.contains(Card.Rank.QUEEN))
        {
            isAStraight = true;
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

        return isAFlush && isAStraight;
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
