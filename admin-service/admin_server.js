var messages = require('./generated-pbs/admin_pb');
var services = require('./generated-pbs/admin_grpc_pb');

var grpc = require('grpc');
var mongo = require('./mongo-db');


/**
 * Implements the AddUser RPC method.
 */
function addUser(call, callback) {
    const reply = new messages.AddUserResponse();
    console.log(callback);

    const user = new mongo.User({
        email: call.request.getEmail(),
        created: new Date()
    });

    user.save(function (err) {
        if (err) {
            console.log(err);
            reply.setStatus(messages.FAILED);
        }
        reply.setStatus(messages.OK);
        console.log(user);
    });
    callback(null, reply);
}


/**
 * Get a new server with the handler functions in this file bound to the methods
 * it serves.
 * @return {Server} The new server object
 */
function getServer() {
    const server = new grpc.Server();
    server.addService(services.AdminService, {addUser: addUser});
    const port = '50051';
    server.bind('localhost:' + port, grpc.ServerCredentials.createInsecure());
    console.log('Listening on port ', port);
    server.start();
}

exports.getServer = getServer;
