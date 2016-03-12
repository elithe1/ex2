package il.ac.huji.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by elithe on 3/8/2016.
 */
public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mTodos;

    public CustomAdapter(Context context, ArrayList<String> todos) {
        mContext = context;
        mTodos = todos;
    }

    @Override
    public int getCount() {
        return mTodos.size();
    }

    @Override
    public Object getItem(int position) {
        return mTodos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.row,parent,false);
        TextView itemTextView = (TextView) convertView.findViewById(R.id.Item);
        itemTextView.setText(mTodos.get(position));
        if((position % 2) != 0)
            itemTextView.setTextColor(Color.BLUE);
        else
            itemTextView.setTextColor(Color.RED);
        return convertView;
    }
}
