object App {
  /******* implicit conversion ********/

   class Result(v: Integer) {
     def get: Integer = v
     //explicitly using java.lang.Integer
   }

  implicit class ResultOps(r: Result) {
    def getOpt: Option[Integer] = Option(r.get)
  }

  def consume(bad: Result) = {
    val better = bad.getOpt
    better
  }

  consume(new Result(null)).fold(ifEmpty = 2)(_ + 4) // 2
  consume(new Result(10)).fold(ifEmpty = 2)(_ + 4) // 14

  /******* implicit parameter ********/

  implicit val nullConverter: Result => Integer =
    x => if (x.get == null) 2 else x.get


  def consumeRaw(x: Result)(implicit converter: Result => Integer): Integer = {
    converter(x)
  }

  consumeRaw(new Result(null)) // 2
  consumeRaw(new Result(10)) // 10
}


