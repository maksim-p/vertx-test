package common.messages.bank;

import java.sql.Timestamp;

public class ExampleMessage extends BankMessage {

  private final String text;

  public ExampleMessage(Long id, Timestamp createDate, String text) {
    super(id, createDate);
    this.text = text;
  }

  public String getText() {
    return text;
  }

  @Override
  public String toString() {
    return "ExampleMessage{" +
      "id=" + getId() +
      ", createDate=" + getCreateDate() +
      ", text=" + text +
      '}';
  }
}
