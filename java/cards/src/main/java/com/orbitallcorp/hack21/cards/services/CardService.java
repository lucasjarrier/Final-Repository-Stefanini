package com.orbitallcorp.hack21.cards.services;

import com.orbitallcorp.hack21.cards.domains.Card;
import com.orbitallcorp.hack21.cards.repositories.CardRepository;
import com.orbitallcorp.hack21.cards.util.nameComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Lucas Jarrier
 * class responsible for executing application operations
 */

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Card save(Card card) {
        return cardRepository.save((card));
    }


    /**
     *
     * @return all Cards.
     */

    public List<Card> findAll() {
        List<Card> cards = new ArrayList<>();
        cardRepository.findAll().forEach(cards :: add);
        return cards;
    }


    /**
     *
     * @param id PrimaryKey
     * @return Card Deleted!
     * @throws Exception Cause not found card.
     */
    public Card deleteByID(long id) throws Exception {
        Card card = cardRepository.findById(id).get();
        cardRepository.delete(card);

        if(!((card) == null)) {
            return card;
        }

        throw new Exception("User not found!");
    }

    /**
     *
     * @param id PrimaryKey
     * @return Card Founded
     * @throws Exception Cause not found card.
     */
    public Card findByID(long id) throws Exception{
        Card card = cardRepository.findById(id).get();
        if(!((card) == null)) {
            return card;
        }
        throw new Exception("User not found!");
    }

    /**
     *
     * @return cards sorted by name.
     */
    public List<Card> orderByName() {
        List<Card> cardsSorted = this.findAll();
        Collections.sort(cardsSorted,new nameComparator());
        return cardsSorted;
    }
}
