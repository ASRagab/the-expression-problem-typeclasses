package visitor.impl.expressions;

import visitor.interfaces.Visitable;
import visitor.interfaces.Visitor;

public class BinaryAddition implements Visitable {
    private Visitable lhs;
    private Visitable rhs;

    public BinaryAddition(Visitable l, Visitable r) {
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
    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }


}
