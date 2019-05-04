package es.developer.achambi.cabifychallenge.core.ui;

import android.content.Context;

import es.developer.achambi.cabifychallenge.R;

public class DataStatePresentationBuilder {

    public static DataStatePresentation buildPresentation(Context context, DataState dataState) {
        return new DataStatePresentation(
                formatState(dataState),
                formatErrorMessage( context, dataState.getException() ));
    }

    private static boolean formatState(DataState productDataState) {
        switch (productDataState.getValue()){
            case SUCCESS:
                return true;
            case ERROR:
                return false;
        }
        return false;
    }

    private static String formatErrorMessage(Context context, Exception e) {
        if( e != null ) {
            return context.getString(R.string.network_error_message);
        } else {
            return "";
        }
    }
}
