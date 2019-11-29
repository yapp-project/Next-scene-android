package com.android.yapp.scenetalker;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

public class Chattingroom_Notify_Dialog {
    private Context context;

    public Chattingroom_Notify_Dialog(Context context) {
        this.context = context;
    }

    public void callFunction(){
        final Dialog dlg = new Dialog(context);
        dlg.setContentView(R.layout.chattingroom_notify_dialog);
        dlg.show();

        final Button notify_exit_button = dlg.findViewById(R.id.notify_exit_button);
        notify_exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.dismiss();
            }
        });
    }
}
