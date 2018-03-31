package visitor.impl.expressions;

import visitor.interfaces.Visitable;
import visitor.interfaces.Visitor;

public class Literal implements Visitable {
    private int value;

    public int getValue() {
        return value;
    }

    public Literal(int v) {
        this.value = v;
    }

    @Override
    public <T> T accept(Visitor<T> v) {
         return v.visit(this);
    }
}
