package visitor.impl.expressions;


import visitor.interfaces.Visitable;
import visitor.interfaces.Visitor;

public class BinaryMultiplication implements Visitable {
    private Visitable lhs;
    private Visitable rhs;

    public BinaryMultiplication(Visitable l, Visitable r) {
        this.lhs = l;
        this.rhs = r;
    }

    public Visitable getLhs() {
        return lhs;
    }

    public Visitable getRhs() {
        return rhs;
    }

    @Override
    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }
}
