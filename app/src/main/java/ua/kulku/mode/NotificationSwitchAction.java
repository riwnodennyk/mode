package ua.kulku.mode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import de.greenrobot.event.EventBus;

public class NotificationSwitchAction extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ModeManager.switchToNext(context);
        ModeNotification.show(context);
        EventBus.getDefault().post(new OnNotificationSwitchedMode());
        //todo integrate into
        // a single BroadcastReceiver
    }

}