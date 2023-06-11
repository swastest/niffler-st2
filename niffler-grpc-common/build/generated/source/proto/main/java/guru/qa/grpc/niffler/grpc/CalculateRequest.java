// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: niffler-currency.proto

package guru.qa.grpc.niffler.grpc;

/**
 * Protobuf type {@code guru.qa.grpc.niffler.CalculateRequest}
 */
public final class CalculateRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:guru.qa.grpc.niffler.CalculateRequest)
    CalculateRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CalculateRequest.newBuilder() to construct.
  private CalculateRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CalculateRequest() {
    spendCurrency_ = 0;
    desiredCurrency_ = 0;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new CalculateRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return guru.qa.grpc.niffler.grpc.NifflerCurrencyProto.internal_static_guru_qa_grpc_niffler_CalculateRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return guru.qa.grpc.niffler.grpc.NifflerCurrencyProto.internal_static_guru_qa_grpc_niffler_CalculateRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            guru.qa.grpc.niffler.grpc.CalculateRequest.class, guru.qa.grpc.niffler.grpc.CalculateRequest.Builder.class);
  }

  public static final int SPENDCURRENCY_FIELD_NUMBER = 1;
  private int spendCurrency_ = 0;
  /**
   * <code>.guru.qa.grpc.niffler.CurrencyValues spendCurrency = 1;</code>
   * @return The enum numeric value on the wire for spendCurrency.
   */
  @java.lang.Override public int getSpendCurrencyValue() {
    return spendCurrency_;
  }
  /**
   * <code>.guru.qa.grpc.niffler.CurrencyValues spendCurrency = 1;</code>
   * @return The spendCurrency.
   */
  @java.lang.Override public guru.qa.grpc.niffler.grpc.CurrencyValues getSpendCurrency() {
    guru.qa.grpc.niffler.grpc.CurrencyValues result = guru.qa.grpc.niffler.grpc.CurrencyValues.forNumber(spendCurrency_);
    return result == null ? guru.qa.grpc.niffler.grpc.CurrencyValues.UNRECOGNIZED : result;
  }

  public static final int DESIREDCURRENCY_FIELD_NUMBER = 2;
  private int desiredCurrency_ = 0;
  /**
   * <code>.guru.qa.grpc.niffler.CurrencyValues desiredCurrency = 2;</code>
   * @return The enum numeric value on the wire for desiredCurrency.
   */
  @java.lang.Override public int getDesiredCurrencyValue() {
    return desiredCurrency_;
  }
  /**
   * <code>.guru.qa.grpc.niffler.CurrencyValues desiredCurrency = 2;</code>
   * @return The desiredCurrency.
   */
  @java.lang.Override public guru.qa.grpc.niffler.grpc.CurrencyValues getDesiredCurrency() {
    guru.qa.grpc.niffler.grpc.CurrencyValues result = guru.qa.grpc.niffler.grpc.CurrencyValues.forNumber(desiredCurrency_);
    return result == null ? guru.qa.grpc.niffler.grpc.CurrencyValues.UNRECOGNIZED : result;
  }

  public static final int AMOUNT_FIELD_NUMBER = 3;
  private double amount_ = 0D;
  /**
   * <code>double amount = 3;</code>
   * @return The amount.
   */
  @java.lang.Override
  public double getAmount() {
    return amount_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (spendCurrency_ != guru.qa.grpc.niffler.grpc.CurrencyValues.UNDEFINED.getNumber()) {
      output.writeEnum(1, spendCurrency_);
    }
    if (desiredCurrency_ != guru.qa.grpc.niffler.grpc.CurrencyValues.UNDEFINED.getNumber()) {
      output.writeEnum(2, desiredCurrency_);
    }
    if (java.lang.Double.doubleToRawLongBits(amount_) != 0) {
      output.writeDouble(3, amount_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (spendCurrency_ != guru.qa.grpc.niffler.grpc.CurrencyValues.UNDEFINED.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(1, spendCurrency_);
    }
    if (desiredCurrency_ != guru.qa.grpc.niffler.grpc.CurrencyValues.UNDEFINED.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, desiredCurrency_);
    }
    if (java.lang.Double.doubleToRawLongBits(amount_) != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(3, amount_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof guru.qa.grpc.niffler.grpc.CalculateRequest)) {
      return super.equals(obj);
    }
    guru.qa.grpc.niffler.grpc.CalculateRequest other = (guru.qa.grpc.niffler.grpc.CalculateRequest) obj;

    if (spendCurrency_ != other.spendCurrency_) return false;
    if (desiredCurrency_ != other.desiredCurrency_) return false;
    if (java.lang.Double.doubleToLongBits(getAmount())
        != java.lang.Double.doubleToLongBits(
            other.getAmount())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + SPENDCURRENCY_FIELD_NUMBER;
    hash = (53 * hash) + spendCurrency_;
    hash = (37 * hash) + DESIREDCURRENCY_FIELD_NUMBER;
    hash = (53 * hash) + desiredCurrency_;
    hash = (37 * hash) + AMOUNT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getAmount()));
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static guru.qa.grpc.niffler.grpc.CalculateRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static guru.qa.grpc.niffler.grpc.CalculateRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static guru.qa.grpc.niffler.grpc.CalculateRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static guru.qa.grpc.niffler.grpc.CalculateRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static guru.qa.grpc.niffler.grpc.CalculateRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static guru.qa.grpc.niffler.grpc.CalculateRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static guru.qa.grpc.niffler.grpc.CalculateRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static guru.qa.grpc.niffler.grpc.CalculateRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static guru.qa.grpc.niffler.grpc.CalculateRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static guru.qa.grpc.niffler.grpc.CalculateRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static guru.qa.grpc.niffler.grpc.CalculateRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static guru.qa.grpc.niffler.grpc.CalculateRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(guru.qa.grpc.niffler.grpc.CalculateRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code guru.qa.grpc.niffler.CalculateRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:guru.qa.grpc.niffler.CalculateRequest)
      guru.qa.grpc.niffler.grpc.CalculateRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return guru.qa.grpc.niffler.grpc.NifflerCurrencyProto.internal_static_guru_qa_grpc_niffler_CalculateRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return guru.qa.grpc.niffler.grpc.NifflerCurrencyProto.internal_static_guru_qa_grpc_niffler_CalculateRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              guru.qa.grpc.niffler.grpc.CalculateRequest.class, guru.qa.grpc.niffler.grpc.CalculateRequest.Builder.class);
    }

    // Construct using guru.qa.grpc.niffler.grpc.CalculateRequest.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      spendCurrency_ = 0;
      desiredCurrency_ = 0;
      amount_ = 0D;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return guru.qa.grpc.niffler.grpc.NifflerCurrencyProto.internal_static_guru_qa_grpc_niffler_CalculateRequest_descriptor;
    }

    @java.lang.Override
    public guru.qa.grpc.niffler.grpc.CalculateRequest getDefaultInstanceForType() {
      return guru.qa.grpc.niffler.grpc.CalculateRequest.getDefaultInstance();
    }

    @java.lang.Override
    public guru.qa.grpc.niffler.grpc.CalculateRequest build() {
      guru.qa.grpc.niffler.grpc.CalculateRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public guru.qa.grpc.niffler.grpc.CalculateRequest buildPartial() {
      guru.qa.grpc.niffler.grpc.CalculateRequest result = new guru.qa.grpc.niffler.grpc.CalculateRequest(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(guru.qa.grpc.niffler.grpc.CalculateRequest result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.spendCurrency_ = spendCurrency_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.desiredCurrency_ = desiredCurrency_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.amount_ = amount_;
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof guru.qa.grpc.niffler.grpc.CalculateRequest) {
        return mergeFrom((guru.qa.grpc.niffler.grpc.CalculateRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(guru.qa.grpc.niffler.grpc.CalculateRequest other) {
      if (other == guru.qa.grpc.niffler.grpc.CalculateRequest.getDefaultInstance()) return this;
      if (other.spendCurrency_ != 0) {
        setSpendCurrencyValue(other.getSpendCurrencyValue());
      }
      if (other.desiredCurrency_ != 0) {
        setDesiredCurrencyValue(other.getDesiredCurrencyValue());
      }
      if (other.getAmount() != 0D) {
        setAmount(other.getAmount());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              spendCurrency_ = input.readEnum();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 16: {
              desiredCurrency_ = input.readEnum();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 25: {
              amount_ = input.readDouble();
              bitField0_ |= 0x00000004;
              break;
            } // case 25
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private int spendCurrency_ = 0;
    /**
     * <code>.guru.qa.grpc.niffler.CurrencyValues spendCurrency = 1;</code>
     * @return The enum numeric value on the wire for spendCurrency.
     */
    @java.lang.Override public int getSpendCurrencyValue() {
      return spendCurrency_;
    }
    /**
     * <code>.guru.qa.grpc.niffler.CurrencyValues spendCurrency = 1;</code>
     * @param value The enum numeric value on the wire for spendCurrency to set.
     * @return This builder for chaining.
     */
    public Builder setSpendCurrencyValue(int value) {
      spendCurrency_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.guru.qa.grpc.niffler.CurrencyValues spendCurrency = 1;</code>
     * @return The spendCurrency.
     */
    @java.lang.Override
    public guru.qa.grpc.niffler.grpc.CurrencyValues getSpendCurrency() {
      guru.qa.grpc.niffler.grpc.CurrencyValues result = guru.qa.grpc.niffler.grpc.CurrencyValues.forNumber(spendCurrency_);
      return result == null ? guru.qa.grpc.niffler.grpc.CurrencyValues.UNRECOGNIZED : result;
    }
    /**
     * <code>.guru.qa.grpc.niffler.CurrencyValues spendCurrency = 1;</code>
     * @param value The spendCurrency to set.
     * @return This builder for chaining.
     */
    public Builder setSpendCurrency(guru.qa.grpc.niffler.grpc.CurrencyValues value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000001;
      spendCurrency_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.guru.qa.grpc.niffler.CurrencyValues spendCurrency = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearSpendCurrency() {
      bitField0_ = (bitField0_ & ~0x00000001);
      spendCurrency_ = 0;
      onChanged();
      return this;
    }

    private int desiredCurrency_ = 0;
    /**
     * <code>.guru.qa.grpc.niffler.CurrencyValues desiredCurrency = 2;</code>
     * @return The enum numeric value on the wire for desiredCurrency.
     */
    @java.lang.Override public int getDesiredCurrencyValue() {
      return desiredCurrency_;
    }
    /**
     * <code>.guru.qa.grpc.niffler.CurrencyValues desiredCurrency = 2;</code>
     * @param value The enum numeric value on the wire for desiredCurrency to set.
     * @return This builder for chaining.
     */
    public Builder setDesiredCurrencyValue(int value) {
      desiredCurrency_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.guru.qa.grpc.niffler.CurrencyValues desiredCurrency = 2;</code>
     * @return The desiredCurrency.
     */
    @java.lang.Override
    public guru.qa.grpc.niffler.grpc.CurrencyValues getDesiredCurrency() {
      guru.qa.grpc.niffler.grpc.CurrencyValues result = guru.qa.grpc.niffler.grpc.CurrencyValues.forNumber(desiredCurrency_);
      return result == null ? guru.qa.grpc.niffler.grpc.CurrencyValues.UNRECOGNIZED : result;
    }
    /**
     * <code>.guru.qa.grpc.niffler.CurrencyValues desiredCurrency = 2;</code>
     * @param value The desiredCurrency to set.
     * @return This builder for chaining.
     */
    public Builder setDesiredCurrency(guru.qa.grpc.niffler.grpc.CurrencyValues value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000002;
      desiredCurrency_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.guru.qa.grpc.niffler.CurrencyValues desiredCurrency = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearDesiredCurrency() {
      bitField0_ = (bitField0_ & ~0x00000002);
      desiredCurrency_ = 0;
      onChanged();
      return this;
    }

    private double amount_ ;
    /**
     * <code>double amount = 3;</code>
     * @return The amount.
     */
    @java.lang.Override
    public double getAmount() {
      return amount_;
    }
    /**
     * <code>double amount = 3;</code>
     * @param value The amount to set.
     * @return This builder for chaining.
     */
    public Builder setAmount(double value) {
      
      amount_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>double amount = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearAmount() {
      bitField0_ = (bitField0_ & ~0x00000004);
      amount_ = 0D;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:guru.qa.grpc.niffler.CalculateRequest)
  }

  // @@protoc_insertion_point(class_scope:guru.qa.grpc.niffler.CalculateRequest)
  private static final guru.qa.grpc.niffler.grpc.CalculateRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new guru.qa.grpc.niffler.grpc.CalculateRequest();
  }

  public static guru.qa.grpc.niffler.grpc.CalculateRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CalculateRequest>
      PARSER = new com.google.protobuf.AbstractParser<CalculateRequest>() {
    @java.lang.Override
    public CalculateRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<CalculateRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CalculateRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public guru.qa.grpc.niffler.grpc.CalculateRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

