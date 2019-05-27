var express = require('express');
var router = express.Router();

var mongoose = require("mongoose");

var userSchema = mongoose.Schema({
  email: String,
  created: Date
});


var User = mongoose.model('User', userSchema);

/* GET users listing. */
router.post('/', function(req, res, next) {
  var user = new User({
    email: req.body.email,
    created: new Date()
  });

  user.save(function(err) {
    if(err) throw err;
    res.send(user);
    console.log(user);
  });
  // res.send('respond with a resource');
});


router.get('/', function(req, res, next) {
  User.find(function(err, users) {
    if(err) throw err;
    res.send(users);
  })
});

module.exports = router;


