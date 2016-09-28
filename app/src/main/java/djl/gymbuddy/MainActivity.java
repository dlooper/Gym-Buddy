package djl.gymbuddy;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String WORKOUT, DATE;
    EditText REPS, SETS, WEIGHT;
    String reps_str, sets_str, weight_str;
    Integer reps, sets, weight;
    Button COMPLETE;
    Context ctx = this;

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WORKOUT = "UpperChest";
        DATE = "9/28/2016";
        REPS = (EditText) findViewById(R.id.ex1_reps);
        SETS = (EditText) findViewById(R.id.ex1_sets);
        WEIGHT = (EditText) findViewById(R.id.ex1_wght);
        COMPLETE = (Button) findViewById(R.id.ex1_button);
        COMPLETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reps_str = REPS.getText().toString();
                sets_str = SETS.getText().toString();
                weight_str = WEIGHT.getText().toString();
                reps = Integer.parseInt(reps_str);
                sets = Integer.parseInt(sets_str);
                weight = Integer.parseInt(weight_str);

                DatabaseOperations DB = new DatabaseOperations(ctx);
                DB.insertValues(DB, WORKOUT, DATE, reps, weight, sets);
                Toast.makeText(getBaseContext(), "Successfully Inserted Data", Toast.LENGTH_LONG).show();
            }
        });
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {;
        Intent intent = new Intent(this, Day1_Ex2.class);
        EditText editText = (EditText) findViewById(R.id.ex1_reps);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}

