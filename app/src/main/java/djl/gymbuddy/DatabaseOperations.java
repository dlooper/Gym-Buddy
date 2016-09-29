package djl.gymbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public Cursor getInformation(DatabaseOperations dop) {
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String test = getTableAsString(SQ);
        Log.v("test", test);

        String[] columns = {WorkoutTable.WorkoutInfo.WORKOUT, WorkoutTable.WorkoutInfo.DATE};
        Cursor CR = SQ.query(WorkoutTable.WorkoutInfo.TABLE_NAME, columns, null, null, null, null, null);
        return CR;
    }

    public String getTableAsString(SQLiteDatabase db) {
        String tableName = WorkoutTable.WorkoutInfo.TABLE_NAME;
        Log.d("DbHelper", "getTableAsString called");
        String tableString = String.format("Table %s:\n", tableName);
        Cursor allRows  = db.rawQuery("SELECT * FROM " + tableName, null);
        if (allRows.moveToFirst() ){
            String[] columnNames = allRows.getColumnNames();
            do {
                for (String name: columnNames) {
                    tableString += String.format("%s: %s\n", name,
                            allRows.getString(allRows.getColumnIndex(name)));
                }
                tableString += "\n";

            } while (allRows.moveToNext());
        }

        return tableString;
    }
}
