package de.fehngarten.iobswitch.modul;

import android.content.Context;
import android.content.Intent;

public interface MyReceiveListener {
    void run(Context context, Intent intent);
}
