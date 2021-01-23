package com.orbitallcorp.hack21.cards.controllers;


import com.orbitallcorp.hack21.cards.domains.Card;
import com.orbitallcorp.hack21.cards.repositories.CardRepository;
import com.orbitallcorp.hack21.cards.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Lucas Jarrier
 * @Text: First of all I apologize for not having followed the correct way in some methods, but I created the way I built in some of my applications.
 * Enjoy this aplication!!
 */

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @Autowired
    private CardRepository cardRepository;

    /**
     * This method is responsible for saving cards.
     * @param card
     * @return A card created.
     */

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody Card card) {
        Card savedCard = cardService.save((card));
        return new ResponseEntity(savedCard, HttpStatus.CREATED);
    }

    /**
     * This method is responsible for returning all cards present in our database.
     * @return returns the list with all cards.
     */

    @GetMapping
    public ResponseEntity<List<Card>> findAll(){
        List<Card> cards = cardService.findAll();
        return ResponseEntity.ok(cards);
    }

    /**
     * This method is responsible for searching for an element by ID.
     * @param id PrimaryKey in BD
     * @return returns the element found with this id. Or if not found, returns that did not find the element
     *
     * How to use in Postman: http://localhost:8080/cards/deleteByID
     * body -> form-data
     * Key: id / Value: Id to be found (Exp: 1, 2...)
     */

    @RequestMapping(value = "/findByID", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Card> findByID(@RequestParam long id) {
        try {
            return new ResponseEntity(cardService.findByID(id), HttpStatus.FOUND);
        }
         catch (Exception e) {
            e.printStackTrace();
         }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    /**
     * Free-Time: Alternative Delete
     *
     * This method is responsible for deleting an element by ID.
     * @param id PrimaryKey in DB
     * @return returns the deleted element. Or if not found, returns that did not find the element
     *
     *
     * How to use in Postman: http://localhost:8080/cards/findByID
     * Body -> form-data
     * Key: id / Value: Id to be deleted (Exp: 1,2...)
     */

    @RequestMapping(value = "/deleteByID", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public  ResponseEntity<Card> deleteByID(@RequestParam long id) {
        try {
            return new ResponseEntity(cardService.deleteByID(id), HttpStatus.OK);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    /**
     * This method is responsible for changing data.
     * @param id
     * @param newCard
     * @return Returns the modified card
     * @throws Exception If you cannot find the Card
     *
     * Funcional!
     */

    @RequestMapping(value = "/card/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Card> put(@PathVariable(value = "id") long id, @RequestBody Card newCard) throws Exception {
        Optional<Card> oldCard = cardRepository.findById(id);
        if(oldCard.isPresent()) {
            Card card = oldCard.get();
            card.setCardNumber(newCard.getCardNumber());
            card.setEmbossName(newCard.getEmbossName());
            card.setCustomerName(newCard.getCustomerName());
            card.setDocumentNumber(newCard.getDocumentNumber());
            card.setMotherName(newCard.getMotherName());
            card.setAddress(newCard.getAddress());
            card.setCity(newCard.getCity());
            cardService.save(card);
            return new ResponseEntity<Card>(card,HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method returns all cards sorted by name.
     * @return ordered list by Name.
     */

    @ResponseBody
    @RequestMapping(value = "/listByName", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Card> orderByName(){
        return this.cardService.orderByName();

    }

    /**
     * This method is responsible for deleting an element by ID.
     * @param id PrimaryKey in DB
     * @return returns the deleted element. Or if not found, returns that did not find the element
     */

    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Card> Delete(@PathVariable(value = "id") long id) {
        Optional<Card> card = cardRepository.findById(id);
        if(card.isPresent()){
            cardRepository.delete(card.get());
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * testing the getById method according to the REST API, but I didn't feel confident and preferred to go for my implementation. Sorry!
     */


    //@GetMapping(path = {"/{id}"})
    //public ResponseEntity findById(@PathVariable long id) {
    //    try {
    //        return new ResponseEntity(cardService.findByID(id), HttpStatus.FOUND);
    //    }

    //    catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //    return new ResponseEntity(HttpStatus.NOT_FOUND);
    //}


}
