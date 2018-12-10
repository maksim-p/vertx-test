package ATM;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class ATMStart extends AbstractVerticle {

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from ATM");
    }).listen(8082, http -> {
      if (http.succeeded()) {
        startFuture.complete();
        System.out.println("HTTP server started on http://localhost:8082");
      } else {
        startFuture.fail(http.cause());
      }
    });
  }

}
