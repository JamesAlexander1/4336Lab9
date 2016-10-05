package a4336.a0.lab4.james.lab9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button statusButton = (Button) findViewById(R.id.SPbutton);

        statusButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SPActivity.class);
                startActivity(intent);
            }
        });

        final Button eSButton = (Button) findViewById(R.id.goToESbutton);
        eSButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ESActivity.class);
                startActivity(intent);
            }
        });
    }
}
