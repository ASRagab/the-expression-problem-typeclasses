package visitor.impl.visitors;

import visitor.impl.expressions.BinaryAddition;
import visitor.impl.expressions.BinaryMultiplication;
import visitor.impl.expressions.Literal;
import visitor.interfaces.Visitor;
import java.lang.Integer;

public class EvaluateVisitor implements Visitor<Integer> {
    @Override
    public Integer visit(Literal l) {
        return l.getValue();
    }

    @Override
    public Integer visit(BinaryAddition b) {
        return b.getLhs().accept(this) + b.getRhs().accept(this);
    }

    @Override
    public Integer visit(BinaryMultiplication m) {
        return m.getLhs().accept(this) * m.getRhs().accept(this);
    }
}
