var express = require('express');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');

var app = express();

var port = process.env.PORT || 8080;// seteamos el puerto

var mongoose = require("mongoose");

mongoose.connect('mongodb://localhost:27017/catalog_BD', null, function(err) {
  if (err) throw err;
  console.log('Successfully connected');
});


app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());


app.use('/', indexRouter);
app.use('/users', usersRouter);
app.listen(port);

module.exports = app;
