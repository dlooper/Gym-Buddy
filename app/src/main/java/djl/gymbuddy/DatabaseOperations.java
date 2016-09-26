package djl.gymbuddy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Doran on 9/26/2016.
 */

public class DatabaseOperations extends SQLiteOpenHelper {
    public static final int database_version = 1;
    public String CREATE_QUERY = "CREATE TABLE " + WorkoutTable.WorkoutInfo.TABLE_NAME + "(" + WorkoutTable.WorkoutInfo.WORKOUT + " TEXT," + WorkoutTable.WorkoutInfo.DATE + " TEXT," + WorkoutTable.WorkoutInfo.REPS + " TEXT," + WorkoutTable.WorkoutInfo.SETS + " TEXT," + WorkoutTable.WorkoutInfo.WEIGHT + "TEXT );";

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
}
