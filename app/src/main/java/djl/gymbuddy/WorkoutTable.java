package djl.gymbuddy;

import android.provider.BaseColumns;

/**
 * Created by Doran on 9/26/2016.
 */

public class WorkoutTable {

    public WorkoutTable() {

    }

    public static abstract class WorkoutInfo implements BaseColumns {
        public static final String WORKOUT = "workout";
        public static final String DATE = "date";
        public static final String REPS = "reps";
        public static final String SETS = "sets";
        public static final String WEIGHT = "weight";
        public static final String DATABASE_NAME = "workouts_db";
        public static final String TABLE_NAME = "workouts_table";
    }
}
