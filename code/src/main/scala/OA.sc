import cats.kernel.Semigroup
import cats.instances.all._
import cats.syntax.semigroup._

object OA {

  // Initial object algebra interface for expressions: integers and addition
  trait ExpAlg[T, E[_]] {
    def lit(x: T): E[T]

    def add[R: Semigroup](e1: E[R], e2: E[R]): E[R]
  }

  // An object algebra implementing that interface (evaluation)

  // The evaluation interface
  trait Eval[T] {
    def eval(): T
  }

  // The object algebra
  class EvalExpAlg[R] extends ExpAlg[R, Eval] {
    override def lit(x: R) = new Eval[R] {
      override def eval() = x
    }

    def add[R: Semigroup](e1: Eval[R], e2: Eval[R]) =
      new Eval[R]() {
        def eval() = e1.eval() |+| e2.eval()
      }
  }

  // Evolution 1: Adding subtraction
  trait NegExpAlg[T, E[_]] extends ExpAlg[T, E] {
    def neg[R: Numeric](e1: E[R]): E[R]
  }

  // Updating evaluation:
  class EvalNegExpAlg[T] extends EvalExpAlg[T] with NegExpAlg[T, Eval] {
    override def neg[A: Numeric](e1: Eval[A]) = new Eval[A]() {
      def eval() = implicitly[Numeric[A]].negate(e1.eval())
    }
  }

  // Evolution 2: Adding pretty printing
  trait Show[T] extends Eval[String] {
    def print(): String

    override def eval() = ""
  }

  class ShowExpAlg[T] extends ExpAlg[T, Show] {
    def add(e1: Show[T], e2: Show[T]) =
      new Show[T]() {
        def print() = e1.print() + " + " + e2.print()
      }

    def neg[R: Numeric](e1: Show[T]) =
      new Show[T]() {
        override def print() = "-" + e1.print()
      }

    override def lit(x: T) = new Show[T]() {
      override def print() = x.toString
    }

    override def add[R: Semigroup](e1: Show[R], e2: Show[R]) =
      new Show[R] {
        override def print() = e1.print() + " + " + e2.print()
      }
  }

  // An alternative object algebra for pretty printing:

  // Testing

  //An expression using the basic ExpAlg
  def exp1[R: Numeric](a: R, alg: EvalNegExpAlg[R]) = {
    import alg._
    neg(lit(a))
  }

  def exp2[R: Semigroup](a: R, b: R,  alg: EvalExpAlg[R]) = {
    import alg._
    add(lit(a), lit(b))
  }

  //

  val e2 = exp2(3, 5, new EvalExpAlg[Int]())
  exp1(10, new EvalNegExpAlg[Int]()).eval()
  //exp3(e2, new ShowExpAlg[Int]())
  //
  //  // An expression using subtraction too
  //  def exp2[E](exp1: E)(alg: NegExpAlg[Int, E]) = {
  //    import alg._
  //    neg(exp1(alg))
  //  }
  //
  //  def test(): Unit = {
  //    // Some object algebras:
  //    val ea = new EvalExpAlg[Int]() {}
  //    val esa = new EvalNegExpAlg() {}
  //    val pa = new ShowExpAlg[String]() {}
  //
  //    // We can call esa with exp1
  //    val ev = exp1(4, 5, esa)
  //
  //    // But calling ea with exp2 is an error
  //    // val ev_bad = exp2(ea)
  //
  //    // Testing the actual algebras
  //    println("Evaluation of exp1 \"" + exp1(pa).print() + "\" is: " + ev.eval())
  //    println("Evaluation of exp2 \"" + exp2(pa).print() + "\" is: " + exp2(esa).eval())
  //  }
  //
  //  test()
}