package djl.gymbuddy;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    String WORKOUT, DATE;
    EditText REPS, SETS, WEIGHT, WORKOUT1, DATE1;
    String reps_str, sets_str, weight_str, workout1, date1;
    Integer reps, sets, weight;
    Button COMPLETE;
    Context ctx = this;
    //********************************************************
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    String formattedDate = sdf.format(c.getTime());
    String beforeDate = "09-28-2016";
    //********************************************************
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    public void onStart(){
        super.onStart();
        DatabaseOperations DOP = new DatabaseOperations(ctx);
        String test = DOP.getInformation(DOP);
        String delims = "[ ,\n]+";
        String[] tokens = test.split(delims);
        Log.v("reps", tokens[3]);
        Log.v("sets", tokens[5]);
        Log.v("weight", tokens[7]);

        TextView textView1 = (TextView)findViewById(R.id.ex1_reps_data);
        textView1.setText(tokens[3], TextView.BufferType.EDITABLE);
        TextView textView2 = (TextView)findViewById(R.id.ex1_sets_data);
        textView2.setText(tokens[5], TextView.BufferType.EDITABLE);
        TextView textView3 = (TextView)findViewById(R.id.ex1_wght_data);
        textView3.setText(tokens[7], TextView.BufferType.EDITABLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RETRIVE DATA FROM DATABASE
        //DatabaseOperations DOP = new DatabaseOperations(ctx);
        //String test = DOP.getInformation(DOP);
        //EditText editText = (EditText)findViewById(R.id.past_data);
        //editText.setText("ho", TextView.BufferType.EDITABLE);
        /*CR.moveToFirst();
        boolean loginStatus = false;
        String NAME = "";
        do {

        }while(CR.moveToNext());*/

        WORKOUT = "UpperChest";
        DATE = "9/29/2016";
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
                //Toast.makeText(getBaseContext(), "Successfully Inserted Data", Toast.LENGTH_LONG).show();
                /*
                if (beforeDate.compareTo(formattedDate) < 0) {
                    Log.d("formattedDate is", "latest");
                }
                else {
                    Log.d("beforeDate is", "latest");
                }*/
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

