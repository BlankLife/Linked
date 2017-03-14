package linked.linkedtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LinkInterests extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_interests);
    }

    public void visitLinkPeople(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, LinkPeople.class);
        startActivity(intent);
    }
}