package il.ac.huji.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by elithe on 3/30/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "todo_db";

    private SQLiteDatabase db;

    public static String MAIN_TABLE_NAME = "todo";
    public static String KEY_TODO_STR= "todoStr";
    public static String KEY_DATE= "todoDate";

    private static final String CREATE_TABLE_MAIN = "CREATE TABLE "
            + MAIN_TABLE_NAME + "(" + KEY_TODO_STR + " TEXT," + KEY_DATE
            + " TEXT)";

    private ArrayList<TodoItem> allTodos;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
         db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MAIN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertTodo(TodoItem todo)
    {
        ContentValues newTodo = new ContentValues();
        newTodo.put(KEY_TODO_STR,todo.getTodo());
        newTodo.put(KEY_DATE,todo.getDateStr());
        Long ret = db.insertOrThrow(MAIN_TABLE_NAME, null, newTodo);
        Log.i("todo","insertTodo returned with: " + ret);
    }

    public void deleteTodo(TodoItem todo) {

        int ret = db.delete(MAIN_TABLE_NAME, KEY_TODO_STR + " = '" + todo.getTodo()+"'", null);
        Log.i("todo","deleteTodo returned with: " + ret);

    }

    public ArrayList<TodoItem> getAllTodos() {
        allTodos = new ArrayList<>();

        String selectAllTodos = "SELECT * FROM " + MAIN_TABLE_NAME;
        Cursor crs = db.rawQuery(selectAllTodos, null);
        if(crs.getCount() == 0)
        {
            Log.i("todo", "getAllTodos: No stories in db.");
            crs.close();
            return allTodos;
        }
        crs.moveToFirst();
        for(int i = 0; i <  crs.getCount(); i++) {
            String todoStr = crs.getString(0);
            String datestr = crs.getString(1);
            Date date = parseDate(datestr);
            TodoItem newTodo = new TodoItem(date, todoStr);
            allTodos.add(newTodo);
            crs.moveToNext();
        }
        crs.close();
        return allTodos;
    }

    private Date parseDate(String datestr) {
        String[] dateSplitted = datestr.split("/");
        String dayStr = dateSplitted[1];
        String monthStr = dateSplitted[0];
        String yearStr =  dateSplitted[2];

        int day = Integer.parseInt(dayStr);
        int month = Integer.parseInt(monthStr);
        int year = Integer.parseInt(yearStr);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
