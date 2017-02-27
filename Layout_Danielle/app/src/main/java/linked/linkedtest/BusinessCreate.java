package linked.linkedtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class BusinessCreate extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner business_spinner;
    ArrayAdapter adapter;
    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_create);

        adapter = ArrayAdapter.createFromResource(this, R.array.business_type_array, android.R.layout.simple_spinner_item);
        business_spinner = (Spinner) findViewById(R.id.businessSpinner);
        business_spinner.setAdapter(adapter);
        business_spinner.setOnItemSelectedListener(BusinessCreate.this);

        ImageButton button = (ImageButton) findViewById(R.id.imageButton2);
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

        //        Submit button from Create Business Account screen
        Button submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(BusinessCreate.this, BusinessActivities.class);
                startActivity(i);
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
