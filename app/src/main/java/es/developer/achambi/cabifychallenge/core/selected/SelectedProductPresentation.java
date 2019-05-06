package es.developer.achambi.cabifychallenge.core.selected;

public class SelectedProductPresentation {
    public final String code;
    public final String name;
    public final String quantity;

    SelectedProductPresentation(String code, String name, String quantity) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }
}
