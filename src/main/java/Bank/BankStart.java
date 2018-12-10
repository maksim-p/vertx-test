package Bank;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class BankStart extends AbstractVerticle {

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
  }

}
