
object ObjectAlegbra {

  import cats.kernel.Semigroup
  import cats.instances.all._
  import cats.syntax.semigroup._

  // Initial State
  // Interface
  trait Eval[A] {
    def go(): A
  }

  // Data
  trait Literal[T, A] {
    def Literal(x: T): A
  }


  //Behavior
  class LiteralEval[T] extends Literal[T, Eval[T]] {
    override def Literal(x: T) =
      new Eval[T] {
        override def go() = x
      }
  }

  // Addtional Data
  trait BinaryAddition[A, Eval[_]] {
    def BinaryAddition(x1: Eval[A], x2: Eval[A])(implicit ev: Semigroup[A]): Eval[A]
  }

  //Original Behavior
  class BinaryAdditionEval[A]
    extends LiteralEval[A]
      with BinaryAddition[A, Eval] {
    override def BinaryAddition(x1: Eval[A], x2: Eval[A])(implicit ev: Semigroup[A]) =
      new Eval[A] {
        override def go() = x1.go() |+| x2.go()
      }
  }

  def add[T: Semigroup](a: T, b: T)(ev: BinaryAddition[T, Eval]) = {
    val lit = new LiteralEval[T]()
    ev.BinaryAddition(lit.Literal(a), lit.Literal(b))
  }

  add(3, 6)(new BinaryAdditionEval[Int]()).go()
  add("3", "6")(new BinaryAdditionEval[String]()).go()


  // Interface for new Behavior
  trait Show[T] extends Eval[String]

  // Adding Behavior
  class ShowEval[T]
      extends BinaryAddition[T, Show] {
    def Literal(x: T) =
      new Show[T] {
        override def go(): String = x.toString
      }

    override def BinaryAddition(x1: Show[T], x2: Show[T])(implicit ev: Semigroup[T]) =
      new Show[T] {
        override def go() = "(" |+| x1.go() |+| " + " |+| x2.go() |+| ")"
      }
  }

  def show[T: Semigroup](a: T)(implicit ev: BinaryAddition[T, Show]) = {
    val lit = new ShowEval[T]()
    lit.Literal(a)
  }

  val lit = new LiteralEval[Double]()
  val q = lit.Literal(10.0)
  val r = lit.Literal(30)
  q.go() |+| r.go()

  val show = new ShowEval[Double]()
  val a = show.Literal(10.0)
  val b = show.Literal(20.0)
  show.BinaryAddition(a, b).go()

  def expressionize[T](value: T, algebra: LiteralEval[T]) = {
    algebra.Literal(value)
  }



}