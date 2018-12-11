package common.messages;


import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class SimpleMessageCodec implements MessageCodec<Message, Message> {

  public static final String commonMessageCodec = SimpleMessageCodec.class.getSimpleName() + "_MessageCodec";

  @Override
  public void encodeToWire(Buffer buffer, Message message) {
    //serialize code here
  }

  @Override
  public Message decodeFromWire(int pos, Buffer buffer) {
    //serialize code here
    return null;
  }

  @Override
  public Message transform(Message message) {
    return message;
  }

  @Override
  public String name() {
    return commonMessageCodec;
  }

  @Override
  public byte systemCodecID() {
    return -1;
  }
}
