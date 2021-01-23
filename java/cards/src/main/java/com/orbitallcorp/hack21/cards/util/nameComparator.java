package com.orbitallcorp.hack21.cards.util;

import com.orbitallcorp.hack21.cards.domains.Card;

import java.util.Comparator;

/**
 * @author Lucas Jarrier
 * This classe is Comparator By Name.
 */

public class nameComparator implements Comparator<Card> {

    public nameComparator(){

    }
    @Override
    public int compare(Card card1, Card card2) {
        return card1.getCustomerName().compareTo(card2.getCustomerName());
    }
}
