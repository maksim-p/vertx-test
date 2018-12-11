package common.messages;

import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class Message implements Serializable{


  private final Long id;

  private final Timestamp createDate;
  private static final DeliveryOptions simpleDeliveryOptions =
    new DeliveryOptions().setCodecName(SimpleMessageCodec.commonMessageCodec);

  public Message(Long id, Timestamp createDate) {
    this.id = id;
    this.createDate = createDate;
  }

  protected Long getId() {
    return id;
  }

  protected Timestamp getCreateDate() {
    return createDate;
  }

  protected void sendWithCodec(EventBus eventBus, String channel) {
    eventBus.send(channel, this, simpleDeliveryOptions);
  }

  @Override
  public String toString() {
    return "Message{" +
      "id=" + id +
      ", createDate=" + createDate +
      '}';
  }
}

