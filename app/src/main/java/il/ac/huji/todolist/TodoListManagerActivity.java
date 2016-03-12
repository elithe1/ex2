package il.ac.huji.todolist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TodoListManagerActivity extends AppCompatActivity {
    private ArrayList<String> todosArray = new ArrayList<>();
    private EditText edtNewItem;
    private ListView lstTodoItems;
    private CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);
        edtNewItem = (EditText) findViewById(R.id.edtNewItem);
        lstTodoItems = (ListView) findViewById(R.id.lstTodoItems);
        adapter = new CustomAdapter(this, todosArray);
        lstTodoItems.setAdapter(adapter);
        final Context ctx = this;
        lstTodoItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
                alertDialogBuilder.setTitle(todosArray.get(position));
                alertDialogBuilder.setPositiveButton("Delete Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        todosArray.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.addItem:

                String newTodo = edtNewItem.getText().toString();
                if(newTodo.equals(""))
                    return true;
                todosArray.add(newTodo);
                adapter.notifyDataSetChanged();
                edtNewItem.setText("");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
