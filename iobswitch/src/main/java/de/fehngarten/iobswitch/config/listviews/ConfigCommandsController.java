package de.fehngarten.iobswitch.config.listviews;

import android.content.Context;
import android.view.MotionEvent;
import androidx.core.content.ContextCompat;
import android.view.View;

import com.mobeta.android.dslv.DragSortController;
import com.mobeta.android.dslv.DragSortListView;

import de.fehngarten.iobswitch.R;

public class ConfigCommandsController extends DragSortController {

    private ConfigCommandsAdapter mAdapter;
    Context mContext;
    private DragSortListView mDslv;

    ConfigCommandsController(DragSortListView dslv, ConfigCommandsAdapter adapter, Context context) {
        super(dslv, R.id.config_command_drag, DragSortController.ON_DOWN, 0);
        mAdapter = adapter;
        mContext = context;
        mDslv = dslv;
        DragSortListView.DropListener onDrop = (from, to) -> mAdapter.changeItems(from, to);
        mDslv.setDropListener(onDrop);
        setRemoveEnabled(false);
    }

    @Override
    public int startDragPosition(MotionEvent ev) {
        int res = super.dragHandleHitPosition(ev);
        if (res == 0) {
            return DragSortController.MISS;
        } else {
            return res;
        }
    }

    @Override
    public View onCreateFloatView(int position) {
        View v = mAdapter.getView(position, null, mDslv);
        v.setBackgroundColor(ContextCompat.getColor(mContext, R.color.conf_bg_handle_pressed));
        return v;
    }

    @Override
    public void onDestroyFloatView(View floatView) {
        //do nothing; block super from crashing
    }

}