package guru.qa.grpc.niffler.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 *контракт сервиса
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: niffler-spend.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SpendServiceGrpc {

  private SpendServiceGrpc() {}

  public static final String SERVICE_NAME = "guru.qa.grpc.niffler.SpendService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<guru.qa.grpc.niffler.grpc.SpendRequest,
      guru.qa.grpc.niffler.grpc.SpendResponse> getSaveSpendForUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SaveSpendForUser",
      requestType = guru.qa.grpc.niffler.grpc.SpendRequest.class,
      responseType = guru.qa.grpc.niffler.grpc.SpendResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<guru.qa.grpc.niffler.grpc.SpendRequest,
      guru.qa.grpc.niffler.grpc.SpendResponse> getSaveSpendForUserMethod() {
    io.grpc.MethodDescriptor<guru.qa.grpc.niffler.grpc.SpendRequest, guru.qa.grpc.niffler.grpc.SpendResponse> getSaveSpendForUserMethod;
    if ((getSaveSpendForUserMethod = SpendServiceGrpc.getSaveSpendForUserMethod) == null) {
      synchronized (SpendServiceGrpc.class) {
        if ((getSaveSpendForUserMethod = SpendServiceGrpc.getSaveSpendForUserMethod) == null) {
          SpendServiceGrpc.getSaveSpendForUserMethod = getSaveSpendForUserMethod =
              io.grpc.MethodDescriptor.<guru.qa.grpc.niffler.grpc.SpendRequest, guru.qa.grpc.niffler.grpc.SpendResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SaveSpendForUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  guru.qa.grpc.niffler.grpc.SpendRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  guru.qa.grpc.niffler.grpc.SpendResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SpendServiceMethodDescriptorSupplier("SaveSpendForUser"))
              .build();
        }
      }
    }
    return getSaveSpendForUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<guru.qa.grpc.niffler.grpc.SpendRequest,
      guru.qa.grpc.niffler.grpc.SpendResponse> getEditSpendForUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EditSpendForUser",
      requestType = guru.qa.grpc.niffler.grpc.SpendRequest.class,
      responseType = guru.qa.grpc.niffler.grpc.SpendResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<guru.qa.grpc.niffler.grpc.SpendRequest,
      guru.qa.grpc.niffler.grpc.SpendResponse> getEditSpendForUserMethod() {
    io.grpc.MethodDescriptor<guru.qa.grpc.niffler.grpc.SpendRequest, guru.qa.grpc.niffler.grpc.SpendResponse> getEditSpendForUserMethod;
    if ((getEditSpendForUserMethod = SpendServiceGrpc.getEditSpendForUserMethod) == null) {
      synchronized (SpendServiceGrpc.class) {
        if ((getEditSpendForUserMethod = SpendServiceGrpc.getEditSpendForUserMethod) == null) {
          SpendServiceGrpc.getEditSpendForUserMethod = getEditSpendForUserMethod =
              io.grpc.MethodDescriptor.<guru.qa.grpc.niffler.grpc.SpendRequest, guru.qa.grpc.niffler.grpc.SpendResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EditSpendForUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  guru.qa.grpc.niffler.grpc.SpendRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  guru.qa.grpc.niffler.grpc.SpendResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SpendServiceMethodDescriptorSupplier("EditSpendForUser"))
              .build();
        }
      }
    }
    return getEditSpendForUserMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SpendServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SpendServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SpendServiceStub>() {
        @java.lang.Override
        public SpendServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SpendServiceStub(channel, callOptions);
        }
      };
    return SpendServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SpendServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SpendServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SpendServiceBlockingStub>() {
        @java.lang.Override
        public SpendServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SpendServiceBlockingStub(channel, callOptions);
        }
      };
    return SpendServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SpendServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SpendServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SpendServiceFutureStub>() {
        @java.lang.Override
        public SpendServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SpendServiceFutureStub(channel, callOptions);
        }
      };
    return SpendServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   *контракт сервиса
   * </pre>
   */
  public static abstract class SpendServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void saveSpendForUser(guru.qa.grpc.niffler.grpc.SpendRequest request,
        io.grpc.stub.StreamObserver<guru.qa.grpc.niffler.grpc.SpendResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSaveSpendForUserMethod(), responseObserver);
    }

    /**
     */
    public void editSpendForUser(guru.qa.grpc.niffler.grpc.SpendRequest request,
        io.grpc.stub.StreamObserver<guru.qa.grpc.niffler.grpc.SpendResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEditSpendForUserMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSaveSpendForUserMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                guru.qa.grpc.niffler.grpc.SpendRequest,
                guru.qa.grpc.niffler.grpc.SpendResponse>(
                  this, METHODID_SAVE_SPEND_FOR_USER)))
          .addMethod(
            getEditSpendForUserMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                guru.qa.grpc.niffler.grpc.SpendRequest,
                guru.qa.grpc.niffler.grpc.SpendResponse>(
                  this, METHODID_EDIT_SPEND_FOR_USER)))
          .build();
    }
  }

  /**
   * <pre>
   *контракт сервиса
   * </pre>
   */
  public static final class SpendServiceStub extends io.grpc.stub.AbstractAsyncStub<SpendServiceStub> {
    private SpendServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SpendServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SpendServiceStub(channel, callOptions);
    }

    /**
     */
    public void saveSpendForUser(guru.qa.grpc.niffler.grpc.SpendRequest request,
        io.grpc.stub.StreamObserver<guru.qa.grpc.niffler.grpc.SpendResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSaveSpendForUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void editSpendForUser(guru.qa.grpc.niffler.grpc.SpendRequest request,
        io.grpc.stub.StreamObserver<guru.qa.grpc.niffler.grpc.SpendResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEditSpendForUserMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *контракт сервиса
   * </pre>
   */
  public static final class SpendServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<SpendServiceBlockingStub> {
    private SpendServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SpendServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SpendServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public guru.qa.grpc.niffler.grpc.SpendResponse saveSpendForUser(guru.qa.grpc.niffler.grpc.SpendRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSaveSpendForUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public guru.qa.grpc.niffler.grpc.SpendResponse editSpendForUser(guru.qa.grpc.niffler.grpc.SpendRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEditSpendForUserMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *контракт сервиса
   * </pre>
   */
  public static final class SpendServiceFutureStub extends io.grpc.stub.AbstractFutureStub<SpendServiceFutureStub> {
    private SpendServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SpendServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SpendServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<guru.qa.grpc.niffler.grpc.SpendResponse> saveSpendForUser(
        guru.qa.grpc.niffler.grpc.SpendRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSaveSpendForUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<guru.qa.grpc.niffler.grpc.SpendResponse> editSpendForUser(
        guru.qa.grpc.niffler.grpc.SpendRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEditSpendForUserMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAVE_SPEND_FOR_USER = 0;
  private static final int METHODID_EDIT_SPEND_FOR_USER = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SpendServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SpendServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAVE_SPEND_FOR_USER:
          serviceImpl.saveSpendForUser((guru.qa.grpc.niffler.grpc.SpendRequest) request,
              (io.grpc.stub.StreamObserver<guru.qa.grpc.niffler.grpc.SpendResponse>) responseObserver);
          break;
        case METHODID_EDIT_SPEND_FOR_USER:
          serviceImpl.editSpendForUser((guru.qa.grpc.niffler.grpc.SpendRequest) request,
              (io.grpc.stub.StreamObserver<guru.qa.grpc.niffler.grpc.SpendResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SpendServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SpendServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return guru.qa.grpc.niffler.grpc.NifflerSpendProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SpendService");
    }
  }

  private static final class SpendServiceFileDescriptorSupplier
      extends SpendServiceBaseDescriptorSupplier {
    SpendServiceFileDescriptorSupplier() {}
  }

  private static final class SpendServiceMethodDescriptorSupplier
      extends SpendServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SpendServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SpendServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SpendServiceFileDescriptorSupplier())
              .addMethod(getSaveSpendForUserMethod())
              .addMethod(getEditSpendForUserMethod())
              .build();
        }
      }
    }
    return result;
  }
}
