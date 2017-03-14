package linked.linkedtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class LinkLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_location);
    }

    public void visitLinkInterests(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, LinkInterests.class);
        startActivity(intent);
    }
}