package de.fehngarten.iobswitch.modul;

import android.content.Context;
import de.fehngarten.iobswitch.R;

public class SendAlertMessage extends SendMessage {

    public SendAlertMessage(Context context, String msg) {
        super(context);
        header = context.getString(R.string.error_header);
        dialog.setNeutralButton(context.getString(R.string.ok), null);
        doSendMessage(msg);
    }
}
