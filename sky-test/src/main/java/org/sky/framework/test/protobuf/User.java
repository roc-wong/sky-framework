package org.sky.framework.test.protobuf;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: UserInfo.proto

public final class User {
  private User() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * Protobuf enum {@code UserStatus}
   */
  public enum UserStatus
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <pre>
     *表示处于离线状态的用户
     * </pre>
     *
     * <code>OFFLINE = 0;</code>
     */
    OFFLINE(0),
    /**
     * <pre>
     *表示处于在线状态的用户
     * </pre>
     *
     * <code>ONLINE = 1;</code>
     */
    ONLINE(1),
    ;

    /**
     * <pre>
     *表示处于离线状态的用户
     * </pre>
     *
     * <code>OFFLINE = 0;</code>
     */
    public static final int OFFLINE_VALUE = 0;
    /**
     * <pre>
     *表示处于在线状态的用户
     * </pre>
     *
     * <code>ONLINE = 1;</code>
     */
    public static final int ONLINE_VALUE = 1;


    public final int getNumber() {
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @Deprecated
    public static UserStatus valueOf(int value) {
      return forNumber(value);
    }

    public static UserStatus forNumber(int value) {
      switch (value) {
        case 0: return OFFLINE;
        case 1: return ONLINE;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<UserStatus>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        UserStatus> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<UserStatus>() {
            public UserStatus findValueByNumber(int number) {
              return UserStatus.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return User.getDescriptor().getEnumTypes().get(0);
    }

    private static final UserStatus[] VALUES = values();

    public static UserStatus valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private UserStatus(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:UserStatus)
  }

  public interface LogonReqMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:LogonReqMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int64 acctID = 1;</code>
     */
    boolean hasAcctID();
    /**
     * <code>required int64 acctID = 1;</code>
     */
    long getAcctID();

    /**
     * <code>required string passwd = 2;</code>
     */
    boolean hasPasswd();
    /**
     * <code>required string passwd = 2;</code>
     */
    String getPasswd();
    /**
     * <code>required string passwd = 2;</code>
     */
    com.google.protobuf.ByteString
        getPasswdBytes();

    /**
     * <code>required .UserStatus status = 3;</code>
     */
    boolean hasStatus();
    /**
     * <code>required .UserStatus status = 3;</code>
     */
    UserStatus getStatus();
  }
  /**
   * Protobuf type {@code LogonReqMessage}
   */
  public  static final class LogonReqMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:LogonReqMessage)
      LogonReqMessageOrBuilder {
    // Use LogonReqMessage.newBuilder() to construct.
    private LogonReqMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private LogonReqMessage() {
      acctID_ = 0L;
      passwd_ = "";
      status_ = 0;
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private LogonReqMessage(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              acctID_ = input.readInt64();
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              passwd_ = bs;
              break;
            }
            case 24: {
              int rawValue = input.readEnum();
              UserStatus value = UserStatus.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(3, rawValue);
              } else {
                bitField0_ |= 0x00000004;
                status_ = rawValue;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return User.internal_static_LogonReqMessage_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return User.internal_static_LogonReqMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              LogonReqMessage.class, Builder.class);
    }

    private int bitField0_;
    public static final int ACCTID_FIELD_NUMBER = 1;
    private long acctID_;
    /**
     * <code>required int64 acctID = 1;</code>
     */
    public boolean hasAcctID() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 acctID = 1;</code>
     */
    public long getAcctID() {
      return acctID_;
    }

    public static final int PASSWD_FIELD_NUMBER = 2;
    private volatile Object passwd_;
    /**
     * <code>required string passwd = 2;</code>
     */
    public boolean hasPasswd() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string passwd = 2;</code>
     */
    public String getPasswd() {
      Object ref = passwd_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          passwd_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string passwd = 2;</code>
     */
    public com.google.protobuf.ByteString
        getPasswdBytes() {
      Object ref = passwd_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        passwd_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int STATUS_FIELD_NUMBER = 3;
    private int status_;
    /**
     * <code>required .UserStatus status = 3;</code>
     */
    public boolean hasStatus() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required .UserStatus status = 3;</code>
     */
    public UserStatus getStatus() {
      UserStatus result = UserStatus.valueOf(status_);
      return result == null ? UserStatus.OFFLINE : result;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasAcctID()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasPasswd()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasStatus()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt64(1, acctID_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, passwd_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeEnum(3, status_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(1, acctID_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, passwd_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(3, status_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof LogonReqMessage)) {
        return super.equals(obj);
      }
      LogonReqMessage other = (LogonReqMessage) obj;

      boolean result = true;
      result = result && (hasAcctID() == other.hasAcctID());
      if (hasAcctID()) {
        result = result && (getAcctID()
            == other.getAcctID());
      }
      result = result && (hasPasswd() == other.hasPasswd());
      if (hasPasswd()) {
        result = result && getPasswd()
            .equals(other.getPasswd());
      }
      result = result && (hasStatus() == other.hasStatus());
      if (hasStatus()) {
        result = result && status_ == other.status_;
      }
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasAcctID()) {
        hash = (37 * hash) + ACCTID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
            getAcctID());
      }
      if (hasPasswd()) {
        hash = (37 * hash) + PASSWD_FIELD_NUMBER;
        hash = (53 * hash) + getPasswd().hashCode();
      }
      if (hasStatus()) {
        hash = (37 * hash) + STATUS_FIELD_NUMBER;
        hash = (53 * hash) + status_;
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static LogonReqMessage parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static LogonReqMessage parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static LogonReqMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static LogonReqMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static LogonReqMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static LogonReqMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static LogonReqMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static LogonReqMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static LogonReqMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static LogonReqMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static LogonReqMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static LogonReqMessage parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(LogonReqMessage prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code LogonReqMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:LogonReqMessage)
        LogonReqMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return User.internal_static_LogonReqMessage_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return User.internal_static_LogonReqMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                LogonReqMessage.class, Builder.class);
      }

      // Construct using User.LogonReqMessage.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        acctID_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        passwd_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        status_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return User.internal_static_LogonReqMessage_descriptor;
      }

      public LogonReqMessage getDefaultInstanceForType() {
        return LogonReqMessage.getDefaultInstance();
      }

      public LogonReqMessage build() {
        LogonReqMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public LogonReqMessage buildPartial() {
        LogonReqMessage result = new LogonReqMessage(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.acctID_ = acctID_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.passwd_ = passwd_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.status_ = status_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof LogonReqMessage) {
          return mergeFrom((LogonReqMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(LogonReqMessage other) {
        if (other == LogonReqMessage.getDefaultInstance()) return this;
        if (other.hasAcctID()) {
          setAcctID(other.getAcctID());
        }
        if (other.hasPasswd()) {
          bitField0_ |= 0x00000002;
          passwd_ = other.passwd_;
          onChanged();
        }
        if (other.hasStatus()) {
          setStatus(other.getStatus());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        if (!hasAcctID()) {
          return false;
        }
        if (!hasPasswd()) {
          return false;
        }
        if (!hasStatus()) {
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        LogonReqMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (LogonReqMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long acctID_ ;
      /**
       * <code>required int64 acctID = 1;</code>
       */
      public boolean hasAcctID() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int64 acctID = 1;</code>
       */
      public long getAcctID() {
        return acctID_;
      }
      /**
       * <code>required int64 acctID = 1;</code>
       */
      public Builder setAcctID(long value) {
        bitField0_ |= 0x00000001;
        acctID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 acctID = 1;</code>
       */
      public Builder clearAcctID() {
        bitField0_ = (bitField0_ & ~0x00000001);
        acctID_ = 0L;
        onChanged();
        return this;
      }

      private Object passwd_ = "";
      /**
       * <code>required string passwd = 2;</code>
       */
      public boolean hasPasswd() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string passwd = 2;</code>
       */
      public String getPasswd() {
        Object ref = passwd_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            passwd_ = s;
          }
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>required string passwd = 2;</code>
       */
      public com.google.protobuf.ByteString
          getPasswdBytes() {
        Object ref = passwd_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          passwd_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string passwd = 2;</code>
       */
      public Builder setPasswd(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        passwd_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string passwd = 2;</code>
       */
      public Builder clearPasswd() {
        bitField0_ = (bitField0_ & ~0x00000002);
        passwd_ = getDefaultInstance().getPasswd();
        onChanged();
        return this;
      }
      /**
       * <code>required string passwd = 2;</code>
       */
      public Builder setPasswdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        passwd_ = value;
        onChanged();
        return this;
      }

      private int status_ = 0;
      /**
       * <code>required .UserStatus status = 3;</code>
       */
      public boolean hasStatus() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required .UserStatus status = 3;</code>
       */
      public UserStatus getStatus() {
        UserStatus result = UserStatus.valueOf(status_);
        return result == null ? UserStatus.OFFLINE : result;
      }
      /**
       * <code>required .UserStatus status = 3;</code>
       */
      public Builder setStatus(UserStatus value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000004;
        status_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <code>required .UserStatus status = 3;</code>
       */
      public Builder clearStatus() {
        bitField0_ = (bitField0_ & ~0x00000004);
        status_ = 0;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:LogonReqMessage)
    }

    // @@protoc_insertion_point(class_scope:LogonReqMessage)
    private static final LogonReqMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new LogonReqMessage();
    }

    public static LogonReqMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @Deprecated public static final com.google.protobuf.Parser<LogonReqMessage>
        PARSER = new com.google.protobuf.AbstractParser<LogonReqMessage>() {
      public LogonReqMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new LogonReqMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<LogonReqMessage> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<LogonReqMessage> getParserForType() {
      return PARSER;
    }

    public LogonReqMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_LogonReqMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_LogonReqMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\016UserInfo.proto\"N\n\017LogonReqMessage\022\016\n\006a" +
      "cctID\030\001 \002(\003\022\016\n\006passwd\030\002 \002(\t\022\033\n\006status\030\003 " +
      "\002(\0162\013.UserStatus*%\n\nUserStatus\022\013\n\007OFFLIN" +
      "E\020\000\022\n\n\006ONLINE\020\001B\006B\004User"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_LogonReqMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_LogonReqMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_LogonReqMessage_descriptor,
        new String[] { "AcctID", "Passwd", "Status", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
