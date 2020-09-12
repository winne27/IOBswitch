package de.fehngarten.iobswitch.modul;

import android.content.Context;

import de.fehngarten.iobswitch.R;

public class SendDialogMessage extends SendMessage {

    public SendDialogMessage(Context context, String msg) {
        super(context);
        header = context.getString(R.string.hint);
        dialog.setPositiveButton(context.getString(R.string.ok), null);
        dialog.setNegativeButton(context.getString(R.string.cancel), null);
        doSendMessage(msg);
    }
}
