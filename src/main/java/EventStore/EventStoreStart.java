package Bank;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class EventStoreStart extends AbstractVerticle {

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from EventStore");
    }).listen(8083, http -> {
      if (http.succeeded()) {
        startFuture.complete();
        System.out.println("HTTP server started on http://localhost:8083");
      } else {
        startFuture.fail(http.cause());
      }
    });
  }

}
