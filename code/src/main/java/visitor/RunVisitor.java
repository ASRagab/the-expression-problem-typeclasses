package visitor;

import visitor.impl.expressions.BinaryAddition;
import visitor.impl.expressions.BinaryMultiplication;
import visitor.impl.expressions.Literal;
import visitor.impl.visitors.EvaluateVisitor;
import visitor.impl.visitors.ShowVisitor;

public class RunVisitor {
    public static void main(String[] args) {
        Literal l = new Literal(5);
        Literal r = new Literal(10);

        BinaryAddition b = new BinaryAddition(l, r);
        b.accept(new ShowVisitor());
        System.out.println(" = " + b.accept(new EvaluateVisitor()));

        BinaryMultiplication m = new BinaryMultiplication(b, r);
        m.accept(new ShowVisitor());
        System.out.println(" = " + m.accept(new EvaluateVisitor()));

    }
}
