package il.ac.huji.todolist;

import android.util.Log;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by elithe on 3/15/2016.
 */
public class TodoItem implements Serializable {

    public Date date;
    public String todo;
    public boolean call;

    public TodoItem(Date date,String todo)
    {
       this.date = date;
        if(todo.contains("Call ")){
            this.todo = todo.substring("Call ".length());
            call = true;
        }
        else{
            this.todo = todo;
            call = false;
        }
    }

    public String getTodo() {
        if(call)
            return "Call " + todo;
        return todo;
    }
    public Date getDate() {
        return date;
    }
    public String getDateStr() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int mon = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        return "" + mon + "/" + day + "/" + year;
    }

    public boolean passedDueDate() {
        Calendar todaycal = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);
        todaycal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.AM_PM, 0);
        todaycal.set(Calendar.AM_PM, 0);
        cal.set(Calendar.HOUR, 0);
        todaycal.set(Calendar.HOUR, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        todaycal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        todaycal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        todaycal.set(Calendar.MINUTE, 0);

        int comp = todaycal.compareTo(cal);
        if(comp == 1)
            return true;
        else
            return false;
    }

    public String getCellNumber()
    {
        return todo;
    }
}
