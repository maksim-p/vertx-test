package common.messages.bank;

import common.messages.Message;
import io.vertx.core.eventbus.EventBus;

import java.sql.Timestamp;

public abstract class BankMessage extends Message {

  public static final String CHANNEL_NANE = "bank_message_channel";

  BankMessage(Long id, Timestamp createDate) {
    super(id, createDate);
  }

  public void send(EventBus eventBus){
    sendWithCodec(eventBus, CHANNEL_NANE);
  }

}
