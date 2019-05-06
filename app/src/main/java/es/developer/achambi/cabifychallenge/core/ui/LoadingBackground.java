package es.developer.achambi.cabifychallenge.core.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import es.developer.achambi.cabifychallenge.R;

/**
 * On an network error, an error message will be displayed, but due to time constraints a retry
 * option has not been added
 */
public class LoadingBackground extends ConstraintLayout {
    private TextView errorMessage;
    private ProgressBar loading;

    public LoadingBackground(Context context) {
        super(context);
        init(context);
    }

    public LoadingBackground(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadingBackground(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.loading_background_layout, this);
        loading = findViewById(R.id.progressBar);
        errorMessage = findViewById(R.id.error_message_text);
    }

    public void startLoading() {
        this.setVisibility(VISIBLE);
        loading.setVisibility(VISIBLE);
    }

    public void displayState(DataStatePresentation dataStatePresentation) {
        loading.setVisibility(GONE);
        if(dataStatePresentation.success) {
            setVisibility(GONE);
        } else {
            errorMessage.setText(dataStatePresentation.errorMessage);
        }
    }
}
