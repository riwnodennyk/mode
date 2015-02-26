package ua.kulku.mode;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.List;

/**
 * Created by andrii.lavrinenko on 29.11.2014.
 */
public class ModeManager {

    public static final String CURRENT_INDEX = "Current Index";

    public static Mode getCurrent(Context context) {
        List<Mode> all = getAll();
        return all.get(getCurrentIndex(context));
    }

    public static int getCurrentIndex(Context context) {
        int savedIndex = getSharedPreferences(context).getInt(CURRENT_INDEX, 0);
        return savedIndex < getAll().size() ? savedIndex : 0;
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("Mode Manager", Context.MODE_PRIVATE);
    }

    private static List<Mode> getAll() {
        return Arrays.asList(
                new Mode(1, "Програмування, створюю корисне", R.color.red),
                new Mode(2, "Стаю кращим виховником", R.color.green),
                new Mode(3, "Слава української мови", R.color.yellow),
                new Mode(4, "Людська наука", R.color.purple),
                new Mode(5, "Технічна наука", R.color.black)
        );
    }

    public static void switchToNext(Context context) {
        getSharedPreferences(context).edit()
                .putInt(CURRENT_INDEX, getCurrentIndex(context) + 1)
                .commit();
    }
}
