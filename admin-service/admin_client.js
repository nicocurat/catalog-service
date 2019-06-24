const messages = require('./generated-pbs/admin_pb');
const services = require('./generated-pbs/admin_grpc_pb');

const grpc = require('grpc');

function main() {
    const client = new services.AdminClient('localhost:50052',
        grpc.credentials.createInsecure());
    const request = new messages.AddUserRequest();
    let email;
    if (process.argv.length >= 3) {
        email = process.argv[2];
    } else {
        email = 'world';
    }
    request.setEmail(email);
    client.addUser(request, function(err, response) {
        if(err) console.log(err);
        else console.log('Added user:', response);
    });
}

main();
