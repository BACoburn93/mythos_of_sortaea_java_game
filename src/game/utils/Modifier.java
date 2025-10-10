package utils;

public interface Modifier<T> {
    void apply(T item);
    String getName();
}
