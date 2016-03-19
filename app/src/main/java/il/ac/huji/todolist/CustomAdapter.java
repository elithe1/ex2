package il.ac.huji.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by elithe on 3/8/2016.
 */
public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<TodoItem> mTodos;

    public CustomAdapter(Context context, ArrayList<TodoItem> todos) {
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
        TextView dueTextView = (TextView) convertView.findViewById(R.id.DueDate);

        TodoItem curItem = mTodos.get(position);
        itemTextView.setText(curItem.getTodo());
        dueTextView.setText(curItem.getDateStr());

        if(!curItem.passedDueDate())
        {
            itemTextView.setTextColor(Color.BLACK);
            dueTextView.setTextColor(Color.BLACK);
        }
        else {
            itemTextView.setTextColor(Color.RED);
            dueTextView.setTextColor(Color.RED);
        }
        return convertView;
    }
}
