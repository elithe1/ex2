package il.ac.huji.todolist;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class TodoListManagerActivity extends AppCompatActivity {
    private ArrayList<TodoItem> todosArray = new ArrayList<>();
    private ListView lstTodoItems;
    private CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);

        lstTodoItems = (ListView) findViewById(R.id.lstTodoItems);
        registerForContextMenu(lstTodoItems);
        adapter = new CustomAdapter(this, todosArray);
        lstTodoItems.setAdapter(adapter);

    }
    //delete call menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_menu, menu);

        // Get the info on which item was selected
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        // Retrieve the item that was clicked on
        TodoItem curItem = (TodoItem) adapter.getItem(info.position);

        String title = curItem.getTodo();
        menu.setHeaderTitle(title);
        if(curItem.call)
            menu.add(curItem.getTodo());

    }


    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        TodoItem curItem = (TodoItem) adapter.getItem(info.position);

        switch (item.getItemId())
        {
            case R.id.menuItemDelete:
                todosArray.remove(curItem);
                adapter.notifyDataSetChanged();
                break;
            case 0://call
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + curItem.getCellNumber()));
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.addItem:
                Intent i = new Intent(this, AddNewTodoItemActivity.class);
                startActivityForResult(i, 1);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Date d = (Date) data.getExtras().getSerializable("dueDate");
        String t = data.getExtras().getString("title");
        TodoItem newToDoItem = new TodoItem(d,t);
        todosArray.add(newToDoItem);
        adapter.notifyDataSetChanged();
    }
}
