// GENERATED CODE -- DO NOT EDIT!

'use strict';
var grpc = require('grpc');
var admin_pb = require('./admin_pb.js');

function serialize_AddUserRequest(arg) {
  if (!(arg instanceof admin_pb.AddUserRequest)) {
    throw new Error('Expected argument of type AddUserRequest');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_AddUserRequest(buffer_arg) {
  return admin_pb.AddUserRequest.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_AddUserResponse(arg) {
  if (!(arg instanceof admin_pb.AddUserResponse)) {
    throw new Error('Expected argument of type AddUserResponse');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_AddUserResponse(buffer_arg) {
  return admin_pb.AddUserResponse.deserializeBinary(new Uint8Array(buffer_arg));
}


var AdminService = exports.AdminService = {
  addUser: {
    path: '/Admin/AddUser',
    requestStream: false,
    responseStream: false,
    requestType: admin_pb.AddUserRequest,
    responseType: admin_pb.AddUserResponse,
    requestSerialize: serialize_AddUserRequest,
    requestDeserialize: deserialize_AddUserRequest,
    responseSerialize: serialize_AddUserResponse,
    responseDeserialize: deserialize_AddUserResponse,
  },
};

exports.AdminClient = grpc.makeGenericClientConstructor(AdminService);
