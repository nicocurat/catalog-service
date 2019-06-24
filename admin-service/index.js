var express = require('express');
var grpc = require('grpc');
var router = express.Router();
var mongoose = require('mongoose');
const {getServer} = require("./admin_server");


/* GET home page. */
router.get('/', function(req, res, next) {
  res.send('Admin Service');
});

module.exports = router;

const server = getServer();
