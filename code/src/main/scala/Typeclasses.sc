object Typeclasses {

  //Data
  trait Exp

  case class Literal(value: Int) extends Exp

  case class BinaryAddition[A <: Exp, B <: Exp](left: A, right: B) extends Exp

  //Typeclass Behavior Trait
  trait Eval[A] {
    def eval(a: A): Int
  }

  object Evaluator {
    implicit def LiteralEval = new Eval[Literal] {
      override def eval(a: Literal) = a.value
    }

    implicit def BinaryAdditionEval[A <: Exp, B <: Exp](implicit evA: Eval[A], evB: Eval[B]) =
      new Eval[BinaryAddition[A, B]] {

        override def eval(expr: BinaryAddition[A, B]) =
          evA.eval(expr.left) + evB.eval(expr.right)
      }
  }

  import Evaluator._

  val l1 = Literal(5)
  val l2 = Literal(10)
  val add = BinaryAddition(l1, l2)

  LiteralEval.eval(l1) // okay
  BinaryAdditionEval[Literal, Literal].eval(add) // eh....not great!

  //New Data
  case class Negative[A <: Exp](expr: A) extends Exp

  object NegativeEvaluator {
    implicit def NegativeEval[A <: Exp](implicit ev: Eval[A]) = new Eval[Negative[A]] {
      override def eval(a: Negative[A]): Int = -ev.eval(a.expr)
    }
  }


  import NegativeEvaluator._
  val neg = Negative(add)
  NegativeEval[BinaryAddition[Literal, Literal]].eval(neg) // this is getting out of hand

  //New Behavior
  trait Show[A] {
    def show(a: A): String
  }

  object Shower {
    implicit def LiteralShower = new Show[Literal] {
      override def show(a: Literal): String = s"lit ${a.value}"
    }

    implicit def BinaryAdditionShower[A <: Exp, B <: Exp](implicit evA: Show[A], evB: Show[B]) =
      new Show[BinaryAddition[A, B]] {
        override def show(a: BinaryAddition[A, B]) =
          "(" + evA.show(a.left) + " + " + evB.show(a.right) + ")"
      }

    implicit def NegativeShower[A <: Exp](implicit ev: Show[A]) =
      new Show[Negative[A]] {
        override def show(a: Negative[A]) = "-" + "(" + ev.show(a.expr) + ")"
      }
  }

  import Shower._
  LiteralShower.show(l1)
  NegativeShower[BinaryAddition[Literal, Literal]].show(neg)

  object LowerPriorityImplicits {

    implicit class LiteralSyntax(lit: Literal) {
      def evaluate(implicit ev: Eval[Literal]) = {
        ev.eval(lit)
      }

      def show(implicit ev: Show[Literal]) = {
        ev.show(lit)
      }
    }

    implicit class BinaryAdditionSyntax[A <: Exp, B <: Exp](ba: BinaryAddition[A, B]) {
      def evaluate(implicit ev: Eval[BinaryAddition[A, B]]) = {
        ev.eval(ba)
      }

      def show(implicit ev: Show[BinaryAddition[A, B]]) = {
        ev.show(ba)
      }
    }

    implicit class NegativeSyntax[A <: Exp](neg: Negative[A]) {
      def evaluate(implicit ev: Eval[Negative[A]]) = {
        ev.eval(neg)
      }

      def show(implicit ev: Show[Negative[A]]) = {
        ev.show(neg)
      }
    }
  }

  import LowerPriorityImplicits._
  l1.evaluate
  add.evaluate

  add.show
  neg.show

}