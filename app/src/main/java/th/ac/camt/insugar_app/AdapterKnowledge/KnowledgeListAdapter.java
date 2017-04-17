package th.ac.camt.insugar_app.AdapterKnowledge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import th.ac.camt.insugar_app.R;

/**
 * Created by MARK on 13/2/2560.
 */

public class KnowledgeListAdapter extends BaseAdapter {

    Context mContext;
    String[] strName;
    int[] resId;

    int lastPosition = -1;

    public KnowledgeListAdapter(Context context, String[] strName, int[] resId) {
        this.mContext= context;
        this.strName = strName;
        this.resId = resId;
    }

    public int getCount() {
        return strName.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater mInflater =
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = mInflater.inflate(R.layout.knowledge_list_item, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.textView1);
        textView.setText(strName[position]);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
        imageView.setBackgroundResource(resId[position]);

        if (position > lastPosition) {
            Animation anim = AnimationUtils.loadAnimation(parent.getContext(),
                    R.anim.up_from_bottom);
            view.startAnimation(anim);
            lastPosition = position;
        }

            return view;

        }
    }