package ua.kulku.mode.app;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.greenrobot.event.EventBus;
import ua.kulku.mode.Mode;
import ua.kulku.mode.ModeManager;
import ua.kulku.mode.OnNotificationSwitchedMode;
import ua.kulku.mode.R;

public class MainFragment extends Fragment {

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        bindViews();
        EventBus.getDefault().register(this);
    }

    public void onEvent(OnNotificationSwitchedMode onNotificationSwitchedMode) {
        bindViews();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void bindViews() {
        Mode current = ModeManager.getCurrent(getActivity());
        int bgColor = getResources().getColor(current.getColorResId());
        boolean isBright = isBright(bgColor);
        int textColor = getResources().getColor(
                isBright ? android.R.color.primary_text_light
                        : android.R.color.primary_text_dark);
        Drawable divider = getResources().getDrawable(
                isBright ? android.R.drawable.divider_horizontal_dark
                        : android.R.drawable.divider_horizontal_bright);

        View colorIndicator = getView().findViewById(R.id.color_indicator);
        colorIndicator.setBackgroundColor(bgColor);

        LinearLayout dividingLayout = (LinearLayout) getView().findViewById(R.id.dividing_layout);
        dividingLayout.setDividerDrawable(divider);

        TextView descriptionView = (TextView) getView().findViewById(R.id.description);
        descriptionView.setTextColor(textColor);
        descriptionView.setText(current.getDescription());

        TextView numberView = (TextView) getView().findViewById(R.id.number);
        numberView.setTextColor(textColor);
        numberView.setText(String.valueOf(current.getNumber()));

        TextView switchButton = (TextView) getView().findViewById(R.id.switch_button);
        switchButton.setTextColor(textColor);
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getActivity();
                ModeManager.switchToNext(context);
                bindViews();
            }
        });
    }

    private boolean isBright(int bgColor) {
        int blue = bgColor & 0xFF;
        int green = bgColor >> 8 & 0xFF;
        int red = bgColor >> 16 & 0xFF;
        float brightness = 0.2126f * red + 0.7152f * green + 0.0722f * blue;
        return brightness > 160f;
    }
}
