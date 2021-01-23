const api = require('../controllers/card-controller')

module.exports = (app) => {
    app.route('/cards')
        .get(api.findAll)
        .post(api.save);
        
    app.route('/cards/:id')
       .get(api.findById)
       .delete(api.remove)
       .put(api.updateById)
    
        
}