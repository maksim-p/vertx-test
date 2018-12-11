package Bank;

import common.messages.bank.BankMessage;
import common.messages.bank.ExampleMessage;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class BankStart extends AbstractVerticle {

  private static final Map<Class<? extends BankMessage>, Consumer<? extends BankMessage>> handlers = new HashMap();
  static {
    registerHandler(ExampleMessage.class, new ExampleMessageHandler());
  }

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from BankStart");
    }).listen(8081, http -> {
      if (http.succeeded()) {
        startFuture.complete();
        System.out.println("HTTP server started on http://localhost:8081");
      } else {
        startFuture.fail(http.cause());
      }
    });

    vertx.eventBus().<BankMessage>consumer(BankMessage.CHANNEL_NANE, event -> {
      BankMessage message = event.body();
      Class<? extends BankMessage> aClass = message.getClass();

      //noinspection unchecked
      Consumer<BankMessage> messageHandler = (Consumer<BankMessage>) handlers.
        getOrDefault(aClass, bankMessage -> System.out.println("There is no handler for " + bankMessage.getClass()));
      messageHandler.accept(message);
    });
  }

  private static <T extends BankMessage, C extends Class<T>> void registerHandler(C clazz, Consumer<T> message){
    handlers.put(clazz, message);
  }

  private static final class ExampleMessageHandler implements Consumer<ExampleMessage>{

    @Override
    public void accept(ExampleMessage exampleMessage) {
      System.out.println("Test message received: " + exampleMessage.toString());
    }
  }
}
