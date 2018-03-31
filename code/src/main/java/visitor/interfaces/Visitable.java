package visitor.interfaces;

public interface Visitable {
    <R> R accept(Visitor<R> v);
}
