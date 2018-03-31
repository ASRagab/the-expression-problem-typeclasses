package visitor.impl.visitors;

import visitor.impl.expressions.BinaryAddition;
import visitor.impl.expressions.BinaryMultiplication;
import visitor.impl.expressions.Literal;
import visitor.interfaces.Visitor;

public class ShowVisitor implements Visitor<Void> {
    @Override
    public Void visit(Literal l) {
        System.out.print(l.getValue());
        return null;
    }

    @Override
    public Void visit(BinaryAddition b) {
        System.out.print("(");

        b.getLhs().accept(this);
        System.out.print(" + ");
        b.getRhs().accept(this);

        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(BinaryMultiplication m) {
        System.out.print("(");

        m.getLhs().accept(this);
        System.out.print(" * ");
        m.getRhs().accept(this);

        System.out.print(")");

        return null;
    }
}
