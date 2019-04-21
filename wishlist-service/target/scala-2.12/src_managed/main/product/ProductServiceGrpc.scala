package product

object ProductServiceGrpc {
  val METHOD_GET_PRODUCT: _root_.io.grpc.MethodDescriptor[product.ProductIdRequest, product.ProductResponse] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("ProductService", "GetProduct"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[product.ProductIdRequest])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[product.ProductResponse])
      .build()
  
  val METHOD_GET_ALL_PRODUCTS: _root_.io.grpc.MethodDescriptor[product.GetAllRequest, product.ProductsList] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("ProductService", "GetAllProducts"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[product.GetAllRequest])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[product.ProductsList])
      .build()
  
  val METHOD_GET_PRODUCTS: _root_.io.grpc.MethodDescriptor[product.ProductIds, product.ProductsList] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("ProductService", "GetProducts"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[product.ProductIds])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[product.ProductsList])
      .build()
  
  val METHOD_GET_PING: _root_.io.grpc.MethodDescriptor[product.Ping, product.Pong] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("ProductService", "GetPing"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[product.Ping])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[product.Pong])
      .build()
  
  val METHOD_ADD_PRODUCT: _root_.io.grpc.MethodDescriptor[product.Product, product.AddProductResponse] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("ProductService", "AddProduct"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[product.Product])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[product.AddProductResponse])
      .build()
  
  val SERVICE: _root_.io.grpc.ServiceDescriptor =
    _root_.io.grpc.ServiceDescriptor.newBuilder("ProductService")
      .setSchemaDescriptor(new _root_.scalapb.grpc.ConcreteProtoFileDescriptorSupplier(product.ProductProto.javaDescriptor))
      .addMethod(METHOD_GET_PRODUCT)
      .addMethod(METHOD_GET_ALL_PRODUCTS)
      .addMethod(METHOD_GET_PRODUCTS)
      .addMethod(METHOD_GET_PING)
      .addMethod(METHOD_ADD_PRODUCT)
      .build()
  
  trait ProductService extends _root_.scalapb.grpc.AbstractService {
    override def serviceCompanion = ProductService
    def getProduct(request: product.ProductIdRequest): scala.concurrent.Future[product.ProductResponse]
    def getAllProducts(request: product.GetAllRequest): scala.concurrent.Future[product.ProductsList]
    def getProducts(request: product.ProductIds): scala.concurrent.Future[product.ProductsList]
    def getPing(request: product.Ping): scala.concurrent.Future[product.Pong]
    def addProduct(request: product.Product): scala.concurrent.Future[product.AddProductResponse]
  }
  
  object ProductService extends _root_.scalapb.grpc.ServiceCompanion[ProductService] {
    implicit def serviceCompanion: _root_.scalapb.grpc.ServiceCompanion[ProductService] = this
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = product.ProductProto.javaDescriptor.getServices().get(0)
  }
  
  trait ProductServiceBlockingClient {
    def serviceCompanion = ProductService
    def getProduct(request: product.ProductIdRequest): product.ProductResponse
    def getAllProducts(request: product.GetAllRequest): product.ProductsList
    def getProducts(request: product.ProductIds): product.ProductsList
    def getPing(request: product.Ping): product.Pong
    def addProduct(request: product.Product): product.AddProductResponse
  }
  
  class ProductServiceBlockingStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[ProductServiceBlockingStub](channel, options) with ProductServiceBlockingClient {
    override def getProduct(request: product.ProductIdRequest): product.ProductResponse = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_GET_PRODUCT, options, request)
    }
    
    override def getAllProducts(request: product.GetAllRequest): product.ProductsList = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_GET_ALL_PRODUCTS, options, request)
    }
    
    override def getProducts(request: product.ProductIds): product.ProductsList = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_GET_PRODUCTS, options, request)
    }
    
    override def getPing(request: product.Ping): product.Pong = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_GET_PING, options, request)
    }
    
    override def addProduct(request: product.Product): product.AddProductResponse = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_ADD_PRODUCT, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): ProductServiceBlockingStub = new ProductServiceBlockingStub(channel, options)
  }
  
  class ProductServiceStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[ProductServiceStub](channel, options) with ProductService {
    override def getProduct(request: product.ProductIdRequest): scala.concurrent.Future[product.ProductResponse] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_GET_PRODUCT, options, request)
    }
    
    override def getAllProducts(request: product.GetAllRequest): scala.concurrent.Future[product.ProductsList] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_GET_ALL_PRODUCTS, options, request)
    }
    
    override def getProducts(request: product.ProductIds): scala.concurrent.Future[product.ProductsList] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_GET_PRODUCTS, options, request)
    }
    
    override def getPing(request: product.Ping): scala.concurrent.Future[product.Pong] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_GET_PING, options, request)
    }
    
    override def addProduct(request: product.Product): scala.concurrent.Future[product.AddProductResponse] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_ADD_PRODUCT, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): ProductServiceStub = new ProductServiceStub(channel, options)
  }
  
  def bindService(serviceImpl: ProductService, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition =
    _root_.io.grpc.ServerServiceDefinition.builder(SERVICE)
    .addMethod(
      METHOD_GET_PRODUCT,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[product.ProductIdRequest, product.ProductResponse] {
        override def invoke(request: product.ProductIdRequest, observer: _root_.io.grpc.stub.StreamObserver[product.ProductResponse]): Unit =
          serviceImpl.getProduct(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_GET_ALL_PRODUCTS,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[product.GetAllRequest, product.ProductsList] {
        override def invoke(request: product.GetAllRequest, observer: _root_.io.grpc.stub.StreamObserver[product.ProductsList]): Unit =
          serviceImpl.getAllProducts(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_GET_PRODUCTS,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[product.ProductIds, product.ProductsList] {
        override def invoke(request: product.ProductIds, observer: _root_.io.grpc.stub.StreamObserver[product.ProductsList]): Unit =
          serviceImpl.getProducts(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_GET_PING,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[product.Ping, product.Pong] {
        override def invoke(request: product.Ping, observer: _root_.io.grpc.stub.StreamObserver[product.Pong]): Unit =
          serviceImpl.getPing(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_ADD_PRODUCT,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[product.Product, product.AddProductResponse] {
        override def invoke(request: product.Product, observer: _root_.io.grpc.stub.StreamObserver[product.AddProductResponse]): Unit =
          serviceImpl.addProduct(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .build()
  
  def blockingStub(channel: _root_.io.grpc.Channel): ProductServiceBlockingStub = new ProductServiceBlockingStub(channel)
  
  def stub(channel: _root_.io.grpc.Channel): ProductServiceStub = new ProductServiceStub(channel)
  
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = product.ProductProto.javaDescriptor.getServices().get(0)
  
}