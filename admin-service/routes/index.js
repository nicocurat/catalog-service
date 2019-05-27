var express = require('express');
var grpc = require('grpc');
var router = express.Router();
var mongoose = require('mongoose');


/* GET home page. */
router.get('/', function(req, res, next) {
  res.send('Admin Service');
});

module.exports = router;
