package es.developer.achambi.cabifychallenge.core.ui;

public class DataStatePresentation {
    public final boolean success;
    public final String errorMessage;

    public DataStatePresentation(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }
}
