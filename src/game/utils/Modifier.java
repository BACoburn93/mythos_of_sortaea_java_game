package utils;

public interface Modifier<T> {
    void apply(T target);
    String getName();
}
