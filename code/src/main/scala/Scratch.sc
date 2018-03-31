import com.sun.net.httpserver.Authenticator.Success

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object Scratch {
  val test: Option[Future[Int]] = Some(Future(10))
  val d: Future[Option[Int]] = Future.sequence(Option.option2Iterable(test)).map(_.headOption)

  val t = Await.result(d, 10 seconds)
    Predef
}