package es.developer.achambi.cabifychallenge.core.ui;

public class DataState<T> {
    public enum Value {
        SUCCESS,
        ERROR,
    }

    private T data;
    private Value value;
    private Exception exception;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
