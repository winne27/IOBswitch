package de.fehngarten.iobswitch.widget.listviews;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import java.util.ArrayList;
import de.fehngarten.iobswitch.R;

import static de.fehngarten.iobswitch.global.Consts.*;
import static de.fehngarten.iobswitch.global.Settings.settingActiveShapes;
import static de.fehngarten.iobswitch.global.Settings.settingDefaultShapes;

import de.fehngarten.iobswitch.data.ConfigWorkBasket;
import de.fehngarten.iobswitch.data.ConfigWorkInstance;
import de.fehngarten.iobswitch.data.RowCommand;

class CommandsFactory implements RemoteViewsFactory {
    //private static final String TAG = "CommandsFactory.";
    private Context mContext = null;
    private int colnum;
    private int instSerial;
    private int widgetId;
    private ConfigWorkInstance curInstance;

    CommandsFactory(Context context, Intent intent, int colnum) {
        //if (BuildConfig.DEBUG) Log.d(TAG, "started");
        mContext = context;
        this.colnum = colnum;
        instSerial = intent.getIntExtra(INSTSERIAL, -1);
        curInstance = ConfigWorkBasket.data.get(instSerial);
        widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
    }

    public void initData() {
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public int getCount() {
        int size = 0;
        try {
            if (curInstance.commandsCols.size() > 0) {
                ArrayList<RowCommand> myCommandsCols = curInstance.commandsCols.get(colnum);
                size = myCommandsCols.size();
            }
        } catch (Exception e) {
            size = 0;
        }
        return size;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        //Log.i("values Position: " + position + " of " + values.size(),values.get(position).name);
        RemoteViews mView = null;
        try {
            int count = getCount();
            if (position < count && count > 0) {
                mView = new RemoteViews(mContext.getPackageName(), R.layout.widget_row_command);
                ArrayList<RowCommand> myCommandsCols = curInstance.commandsCols.get(colnum);
                RowCommand curCommand = myCommandsCols.get(position);
                String type = ConfigWorkBasket.data.get(instSerial).myRoundedCorners.getType(COMMANDS, colnum, position);
                mView.setTextViewText(R.id.command_name, curCommand.name);

                if (curCommand.activ) {
                    mView.setInt(R.id.command_row, "setBackgroundResource", settingActiveShapes.get(type));
                } else {
                    mView.setInt(R.id.command_row, "setBackgroundResource", settingDefaultShapes.get(type));
                }

                Bundle bundle = new Bundle();
                bundle.putString(FHEM_COMMAND, curCommand.command);
                //bundle.putString(FHEM_COMMAND, ConfigWorkBasket.data.get(instSerial).commandsCols.get(colnum).get(position).command);
                bundle.putString(FHEM_TYPE, "command");
                bundle.putString(POS, Integer.toString(position));
                bundle.putString(COL, Integer.toString(colnum));
                bundle.putInt(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                bundle.putInt(INSTSERIAL, instSerial);

                final Intent fillInIntent = new Intent();
                fillInIntent.setAction(SEND_FHEM_COMMAND);
                fillInIntent.putExtras(bundle);
                mView.setOnClickFillInIntent(R.id.command_name, fillInIntent);
            }
        } catch (Exception e) {
            Log.e("getViewAt", e.getMessage());
        }
        return mView;
    }

    @Override
    public RemoteViews getLoadingView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getViewTypeCount() {
        // TODO Auto-generated method stub
        return 1;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }
}
