var mongoose = require("mongoose");

mongoose.connect('mongodb://localhost:27017/catalog_BD', null, function(err) {
    if (err) throw err;
    console.log('Successfully connected');
});


var userSchema = mongoose.Schema({
    email: String,
    created: Date
});


var User = mongoose.model('User', userSchema);


exports.User = User;
