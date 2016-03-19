package il.ac.huji.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

public class AddNewTodoItemActivity extends AppCompatActivity {
    private EditText edtNewItem;
    private DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_todo_item_activity);
        edtNewItem = (EditText) findViewById(R.id.edtNewItem);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
    }
    public void cancelPressed(View v)
    {
        this.finish();
    }

    public void okPressed(View v)
    {
        Date date = getDateFromDatePicker();
        String todo = edtNewItem.getText().toString();
        TodoItem newTodo = new TodoItem(date,todo);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("title", newTodo.getTodo());
        resultIntent.putExtra("dueDate", newTodo.getDate());

        setResult(1, resultIntent);
        finish();
    }

    public Date getDateFromDatePicker(){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

}
