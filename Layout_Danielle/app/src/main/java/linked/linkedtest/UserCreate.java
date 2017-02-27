package linked.linkedtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;


public class UserCreate extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner month_spinner, year_spinner, gender_spinner;
    ArrayAdapter adapter;
    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create);

        adapter = ArrayAdapter.createFromResource(this, R.array.month_array, android.R.layout.simple_spinner_item);
        month_spinner = (Spinner) findViewById(R.id.monthSpinner);
        month_spinner.setAdapter(adapter);
        month_spinner.setOnItemSelectedListener(UserCreate.this);

        adapter = ArrayAdapter.createFromResource(this, R.array.year_array, android.R.layout.simple_spinner_item);
        year_spinner = (Spinner) findViewById(R.id.yearSpinner);
        year_spinner.setAdapter(adapter);
        year_spinner.setOnItemSelectedListener(UserCreate.this);

        adapter = ArrayAdapter.createFromResource(this, R.array.gender_array, android.R.layout.simple_spinner_item);
        gender_spinner = (Spinner) findViewById(R.id.genderSpinner);
        gender_spinner.setAdapter(adapter);
        gender_spinner.setOnItemSelectedListener(UserCreate.this);

        ImageButton button = (ImageButton) findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
/*        TextView spinnerDialogText = (TextView) view;
        Toast.makeText(this, spinnerDialogText.getText(), Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
