package user

object UserServiceGrpc {
  val METHOD_ADD_ITEM: _root_.io.grpc.MethodDescriptor[user.AddItemRequest, user.User] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("UserService", "AddItem"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[user.AddItemRequest])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[user.User])
      .build()
  
  val METHOD_ADD_USER: _root_.io.grpc.MethodDescriptor[user.User, user.UserId] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("UserService", "AddUser"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[user.User])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[user.UserId])
      .build()
  
  val METHOD_GET_USER_BY_ID: _root_.io.grpc.MethodDescriptor[user.UserId, user.User] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("UserService", "GetUserById"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[user.UserId])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[user.User])
      .build()
  
  val METHOD_GET_ALL_USERS: _root_.io.grpc.MethodDescriptor[product.GetAllRequest, user.GetAllUsersResponse] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("UserService", "GetAllUsers"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[product.GetAllRequest])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[user.GetAllUsersResponse])
      .build()
  
  val METHOD_GET_WISH_LIST: _root_.io.grpc.MethodDescriptor[user.UserId, product.ProductIds] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("UserService", "GetWishList"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[user.UserId])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[product.ProductIds])
      .build()
  
  val METHOD_GET_WISH_LIST_WITH_DESCRIPTION: _root_.io.grpc.MethodDescriptor[user.UserId, product.ProductsList] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("UserService", "GetWishListWithDescription"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[user.UserId])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[product.ProductsList])
      .build()
  
  val METHOD_GET_PING: _root_.io.grpc.MethodDescriptor[product.Ping, product.Pong] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("UserService", "GetPing"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[product.Ping])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[product.Pong])
      .build()
  
  val SERVICE: _root_.io.grpc.ServiceDescriptor =
    _root_.io.grpc.ServiceDescriptor.newBuilder("UserService")
      .setSchemaDescriptor(new _root_.scalapb.grpc.ConcreteProtoFileDescriptorSupplier(user.UserProto.javaDescriptor))
      .addMethod(METHOD_ADD_ITEM)
      .addMethod(METHOD_ADD_USER)
      .addMethod(METHOD_GET_USER_BY_ID)
      .addMethod(METHOD_GET_ALL_USERS)
      .addMethod(METHOD_GET_WISH_LIST)
      .addMethod(METHOD_GET_WISH_LIST_WITH_DESCRIPTION)
      .addMethod(METHOD_GET_PING)
      .build()
  
  trait UserService extends _root_.scalapb.grpc.AbstractService {
    override def serviceCompanion = UserService
    def addItem(request: user.AddItemRequest): scala.concurrent.Future[user.User]
    def addUser(request: user.User): scala.concurrent.Future[user.UserId]
    def getUserById(request: user.UserId): scala.concurrent.Future[user.User]
    def getAllUsers(request: product.GetAllRequest): scala.concurrent.Future[user.GetAllUsersResponse]
    def getWishList(request: user.UserId): scala.concurrent.Future[product.ProductIds]
    def getWishListWithDescription(request: user.UserId): scala.concurrent.Future[product.ProductsList]
    def getPing(request: product.Ping): scala.concurrent.Future[product.Pong]
  }
  
  object UserService extends _root_.scalapb.grpc.ServiceCompanion[UserService] {
    implicit def serviceCompanion: _root_.scalapb.grpc.ServiceCompanion[UserService] = this
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = user.UserProto.javaDescriptor.getServices().get(0)
  }
  
  trait UserServiceBlockingClient {
    def serviceCompanion = UserService
    def addItem(request: user.AddItemRequest): user.User
    def addUser(request: user.User): user.UserId
    def getUserById(request: user.UserId): user.User
    def getAllUsers(request: product.GetAllRequest): user.GetAllUsersResponse
    def getWishList(request: user.UserId): product.ProductIds
    def getWishListWithDescription(request: user.UserId): product.ProductsList
    def getPing(request: product.Ping): product.Pong
  }
  
  class UserServiceBlockingStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[UserServiceBlockingStub](channel, options) with UserServiceBlockingClient {
    override def addItem(request: user.AddItemRequest): user.User = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_ADD_ITEM, options, request)
    }
    
    override def addUser(request: user.User): user.UserId = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_ADD_USER, options, request)
    }
    
    override def getUserById(request: user.UserId): user.User = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_GET_USER_BY_ID, options, request)
    }
    
    override def getAllUsers(request: product.GetAllRequest): user.GetAllUsersResponse = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_GET_ALL_USERS, options, request)
    }
    
    override def getWishList(request: user.UserId): product.ProductIds = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_GET_WISH_LIST, options, request)
    }
    
    override def getWishListWithDescription(request: user.UserId): product.ProductsList = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_GET_WISH_LIST_WITH_DESCRIPTION, options, request)
    }
    
    override def getPing(request: product.Ping): product.Pong = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_GET_PING, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): UserServiceBlockingStub = new UserServiceBlockingStub(channel, options)
  }
  
  class UserServiceStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[UserServiceStub](channel, options) with UserService {
    override def addItem(request: user.AddItemRequest): scala.concurrent.Future[user.User] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_ADD_ITEM, options, request)
    }
    
    override def addUser(request: user.User): scala.concurrent.Future[user.UserId] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_ADD_USER, options, request)
    }
    
    override def getUserById(request: user.UserId): scala.concurrent.Future[user.User] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_GET_USER_BY_ID, options, request)
    }
    
    override def getAllUsers(request: product.GetAllRequest): scala.concurrent.Future[user.GetAllUsersResponse] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_GET_ALL_USERS, options, request)
    }
    
    override def getWishList(request: user.UserId): scala.concurrent.Future[product.ProductIds] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_GET_WISH_LIST, options, request)
    }
    
    override def getWishListWithDescription(request: user.UserId): scala.concurrent.Future[product.ProductsList] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_GET_WISH_LIST_WITH_DESCRIPTION, options, request)
    }
    
    override def getPing(request: product.Ping): scala.concurrent.Future[product.Pong] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_GET_PING, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): UserServiceStub = new UserServiceStub(channel, options)
  }
  
  def bindService(serviceImpl: UserService, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition =
    _root_.io.grpc.ServerServiceDefinition.builder(SERVICE)
    .addMethod(
      METHOD_ADD_ITEM,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[user.AddItemRequest, user.User] {
        override def invoke(request: user.AddItemRequest, observer: _root_.io.grpc.stub.StreamObserver[user.User]): Unit =
          serviceImpl.addItem(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_ADD_USER,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[user.User, user.UserId] {
        override def invoke(request: user.User, observer: _root_.io.grpc.stub.StreamObserver[user.UserId]): Unit =
          serviceImpl.addUser(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_GET_USER_BY_ID,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[user.UserId, user.User] {
        override def invoke(request: user.UserId, observer: _root_.io.grpc.stub.StreamObserver[user.User]): Unit =
          serviceImpl.getUserById(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_GET_ALL_USERS,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[product.GetAllRequest, user.GetAllUsersResponse] {
        override def invoke(request: product.GetAllRequest, observer: _root_.io.grpc.stub.StreamObserver[user.GetAllUsersResponse]): Unit =
          serviceImpl.getAllUsers(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_GET_WISH_LIST,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[user.UserId, product.ProductIds] {
        override def invoke(request: user.UserId, observer: _root_.io.grpc.stub.StreamObserver[product.ProductIds]): Unit =
          serviceImpl.getWishList(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_GET_WISH_LIST_WITH_DESCRIPTION,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[user.UserId, product.ProductsList] {
        override def invoke(request: user.UserId, observer: _root_.io.grpc.stub.StreamObserver[product.ProductsList]): Unit =
          serviceImpl.getWishListWithDescription(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_GET_PING,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[product.Ping, product.Pong] {
        override def invoke(request: product.Ping, observer: _root_.io.grpc.stub.StreamObserver[product.Pong]): Unit =
          serviceImpl.getPing(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .build()
  
  def blockingStub(channel: _root_.io.grpc.Channel): UserServiceBlockingStub = new UserServiceBlockingStub(channel)
  
  def stub(channel: _root_.io.grpc.Channel): UserServiceStub = new UserServiceStub(channel)
  
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = user.UserProto.javaDescriptor.getServices().get(0)
  
}