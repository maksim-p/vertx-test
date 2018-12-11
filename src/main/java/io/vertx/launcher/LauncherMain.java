package io.vertx.launcher;

import ATM.ATMStart;
import Bank.BankStart;
import Bank.EventStoreStart;
import io.vertx.core.Vertx;

public class LauncherMain {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(new ATMStart());
    vertx.deployVerticle(new BankStart());
    vertx.deployVerticle(new EventStoreStart());
  }
}
