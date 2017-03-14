package linked.linkedtest;

import linked.linkedtest.R.*;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.view.Menu;
import android.view.MenuItem;
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

        adapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
        category_spinner = (Spinner) findViewById(R.id.categorySpinner);
        category_spinner.setAdapter(adapter);
        category_spinner.setOnItemSelectedListener(BusinessActivities.this);

        MultiSelectionSpinner spinner = (MultiSelectionSpinner) findViewById(id.activitySpinner);

        String[] array = {"Biking", "Skating", "Running", "Basketball", "Baseball", "Football", "Soccer", "Video Games", "Dancing"};
        spinner.setItems(array);
        spinner.setListener(this);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
