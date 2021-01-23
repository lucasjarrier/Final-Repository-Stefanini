const { response } = require('express')
const neDB = require('../configurations/database')
const api = {}

/**
 * Returns all elements present in NeDB
 * @param {*} request 
 * @param {*} response 
 */

api.findAll = (request, response) => {
    neDB.find({}).sort({ name: 1 }).exec((exception, cards) => {
        if (exception) {
            const setence = 'Couldnt Listed!!'
            console.log(setence, exception)
            response.status(exception.status | 400)
            response.json({ 'mensagem': setence })
        }
        response.json(cards)

    })
}

/**
 * Save elements to NeDB
 * @param {*} request 
 * @param {*} response 
 */

api.save = (request, response) => {
    const canonical = request.body

    neDB.insert(canonical, (exception, card) => {
        if(exception){
            console.log('Couldnt Save!', exception)
        }

        response.json(card)
        response.status(201)
    })
}

/**
 * Returns an element by its ID
 * @param {*} request 
 * @param {*} response 
 */
api.findById = (request, response) => {
    const params = request.params.id
    console.log('PARAMS: ', params)
    neDB.findOne({ _id : params }, function (error, cards) {
        if (error) {
            console.log('Error: ', error)
        }
        response.json(cards)
        response.status(201)
        console.log(cards)
    })
}

/**
 * Removes elements by ID
 * @param {*} request 
 * @param {*} response 
 */

api.remove = (request, response) =>{
    let remove = request.body._id
    neDB.remove({_id: remove}, {}, (exception, numRemoved) =>{
        if (exception) {
            const setence = 'Couldnt remove!'
            console.log(setence, exception)
            response.status(exception.status | 400)
            response.json({ 'mensagem': setence })
        }
        response.status(201)
        response.json(numRemoved)
    })
}

/**
 * Changes the element by ID
 * @param {*} request 
 * @param {*} response 
 */
api.updateById = (request, response) => {
    const params = request.params.id
    const requestBody = request.body
    neDB.update({_id : params}, { $set : {'cardNumber' : requestBody.cardNumber, 'embossName': requestBody.embossName, 'customerName': requestBody.customerName, 
    'documentNumber': requestBody.documentNumber, 'motherName': requestBody.motherName, 'address': requestBody.address, 'city': requestBody.city}},
        { multi: true }, (error, cards) => {
            if (error) {
                console.log('Error: ', error)
            }
            response.json(cards)
            response.status(201)
            console.log(cards)
        }
    )
}

module.exports = api