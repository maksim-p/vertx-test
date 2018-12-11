package io.vertx.launcher;

import ATM.ATMStart;
import Bank.BankStart;
import Bank.EventStoreStart;
import common.messages.SimpleMessageCodec;
import common.messages.bank.ExampleMessage;
import io.vertx.core.Vertx;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class LauncherMain {

  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();

    vertx.eventBus().registerCodec(new SimpleMessageCodec());

    vertx.deployVerticle(new ATMStart());
    vertx.deployVerticle(new BankStart());
    vertx.deployVerticle(new EventStoreStart());

    vertx.setPeriodic(TimeUnit.SECONDS.toMillis(3), id -> {
      final ExampleMessage message = new ExampleMessage(id, now(), "Periodic message from LauncherMain");
      message.send(vertx.eventBus());
    });
  }

  private static Timestamp now() {
    return new Timestamp(System.currentTimeMillis());
  }


}
