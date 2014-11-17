package lola.giovannini.lola.activite_main.fragments;

import android.content.Context;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lola.giovannini.lola.BuildConfig;
import lola.giovannini.lola.R;

/**
 * Created by giovannini on 11/6/14.
 */
public class MySpinnerAdapter extends BaseAdapter {

    private List<String> frag_names = new ArrayList<String>();

    private final Context context;

    // the context is needed to inflate views in getView()
    public MySpinnerAdapter(Context context) {
        this.context = context;
    }

    public void update_frag_names(List<String> frag_names) {
        ThreadPreconditions.checkOnMainThread();
        this.frag_names = frag_names;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return frag_names.size();
    }

    // getItem(int) in Adapter returns Object but we can override
    // it to BananaPhone thanks to Java return type covariance
    @Override
    public String getItem(int position) {

        return frag_names.get(position);
    }

    // getItemId() is often useless, I think this should be the default
    // implementation in BaseAdapter
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.spinner_element, parent, false);
        }

        TextView nameView = (TextView) convertView.findViewById(R.id.spinnerElementTV);

        String frag_name = getItem(position);
        nameView.setText(frag_name);

        return convertView;
    }

}

class ThreadPreconditions {
    public static void checkOnMainThread() {
        if (BuildConfig.DEBUG) {
            if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                throw new IllegalStateException("This method should be called from the Main Thread");
            }
        }
    }
}




