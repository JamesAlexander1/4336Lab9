package a4336.a0.lab4.james.lab9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SPActivity extends AppCompatActivity {

    private String fileName = "Lab9File";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);

        final Button SPBackBtn = (Button) findViewById(R.id.SPBackbutton);
        SPBackBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
