package visitor.interfaces;

import visitor.impl.expressions.BinaryAddition;
import visitor.impl.expressions.BinaryMultiplication;
import visitor.impl.expressions.Literal;

public interface Visitor<T> {
    T visit(Literal l);
    T visit(BinaryAddition b);
    T visit(BinaryMultiplication m);
}
