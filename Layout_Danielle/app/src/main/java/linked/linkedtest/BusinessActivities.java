package linked.linkedtest;

import linked.linkedtest.R.*;

import android.app.AlertDialog;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.content.DialogInterface;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import linked.linkedtest.MultiSelectionSpinner;
public class BusinessActivities extends AppCompatActivity implements AdapterView.OnItemSelectedListener, MultiSelectionSpinner.OnMultipleItemsSelectedListener{

    Spinner category_spinner;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_activities);

        adapter = ArrayAdapter.createFromResource(this, R.array.category_array, layout.spinner_layout_blue);
        category_spinner = (Spinner) findViewById(R.id.categorySpinner);
        category_spinner.setAdapter(adapter);
        category_spinner.setOnItemSelectedListener(BusinessActivities.this);
        adapter.setDropDownViewResource(layout.spinner_dialog_layout);

/*        MultiSelectionSpinner spinner = (MultiSelectionSpinner) findViewById(id.activitySpinner);

        String[] array = {"Biking", "Skating", "Running", "Basketball", "Baseball", "Football", "Soccer", "Video Games", "Dancing"};
        spinner.setItems(array);
        spinner.setListener(this);*/
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //TextView selectedText = (TextView) view;
        if(position == 0){
            // if nothing is selected and the second spinner is pressed, app crashes
        }
        if(position == 1){  // if Art selected

            MultiSelectionSpinner spinner = (MultiSelectionSpinner) findViewById(R.id.activitySpinner);
            String[] array = {"Choose an Activity", "Film", "Music", "Painting"};
            spinner.setItems(array);
            spinner.setListener(this);
        }

        if(position == 2){ // if Food selected
            MultiSelectionSpinner spinner = (MultiSelectionSpinner) findViewById(R.id.activitySpinner);
            String[] array = {"Choose an Activity", "Bars", "Dinner", "Eating Contests"};
            spinner.setItems(array);
            spinner.setListener(this);
        }

        if(position == 3){  // if Indoors is selected
            MultiSelectionSpinner spinner = (MultiSelectionSpinner) findViewById(R.id.activitySpinner);
            String[] array = {"Choose an Activity", "Board Games", "Video Games", "Yoga"};
            spinner.setItems(array);
            spinner.setListener(this);
        }

        if(position == 4){  // if Sports is selected
            MultiSelectionSpinner spinner = (MultiSelectionSpinner) findViewById(R.id.activitySpinner);
            String[] array = {"Choose an Activity", "Basketball", "Biking", "Dancing"};
            spinner.setItems(array);
            spinner.setListener(this);
        }

/*        TextView spinnerDialogText = (TextView) view;
        Toast.makeText(this, spinnerDialogText.getText(), Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> strings) {
        /*Toast.makeText(this, strings.toString(), Toast.LENGTH_LONG).show();

         */
    }
}
