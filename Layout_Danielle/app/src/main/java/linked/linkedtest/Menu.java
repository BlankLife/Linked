package linked.linkedtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void visitStart(View view) {
        Intent intent = new Intent(this, Start.class);
        startActivity(intent);
    }

    public void visitLinkLocation(View view) {
        Intent intent = new Intent(this, LinkLocation.class);
        startActivity(intent);
    }
}