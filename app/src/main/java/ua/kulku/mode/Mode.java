package ua.kulku.mode;

/**
 * Created by andrii.lavrinenko on 29.11.2014.
 */
public class Mode {
    private final int mNumber;
    private final String mDescription;
    private int mColorResId;

    public Mode(int number, String description, int colorResId) {
        mNumber = number;
        mDescription = description;
        mColorResId = colorResId;
    }

    public int getNumber() {
        return mNumber;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getColorResId() {
        return mColorResId;
    }

}
