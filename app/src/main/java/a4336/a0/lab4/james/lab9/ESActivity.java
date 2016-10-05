package a4336.a0.lab4.james.lab9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ESActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_es);

        final Button backButton = (Button) findViewById(R.id.ESBackbutton);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final Button goToESFileButton = (Button) findViewById(R.id.goToESFilebutton);
        goToESFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //Replace with ES code.
            }
        });

        final Button showESButton = (Button) findViewById(R.id.gotoCheckSbutton);
        showESButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }
}
