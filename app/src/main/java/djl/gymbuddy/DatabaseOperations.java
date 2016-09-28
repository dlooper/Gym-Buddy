package djl.gymbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Doran on 9/26/2016.
 */

public class DatabaseOperations extends SQLiteOpenHelper {
    public static final int database_version = 3;
    public String CREATE_QUERY = "CREATE TABLE " + WorkoutTable.WorkoutInfo.TABLE_NAME + "(" + WorkoutTable.WorkoutInfo.WORKOUT + " TEXT," + WorkoutTable.WorkoutInfo.DATE + " TEXT," + WorkoutTable.WorkoutInfo.REPS + " TEXT," + WorkoutTable.WorkoutInfo.SETS + " TEXT," + WorkoutTable.WorkoutInfo.WEIGHT + " TEXT);";

    public DatabaseOperations(Context context) {
        super(context, WorkoutTable.WorkoutInfo.DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

    public void insertValues(DatabaseOperations dop, String workout, String date, Integer reps, Integer weight, Integer sets)
    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WorkoutTable.WorkoutInfo.WORKOUT, workout);
        cv.put(WorkoutTable.WorkoutInfo.DATE, date);
        cv.put(WorkoutTable.WorkoutInfo.REPS, reps);
        cv.put(WorkoutTable.WorkoutInfo.SETS, sets);
        cv.put(WorkoutTable.WorkoutInfo.WEIGHT, weight);

        long k = SQ.insert(WorkoutTable.WorkoutInfo.TABLE_NAME, null, cv);
        Log.d("Database operations", "One row inserted");
    }
}
