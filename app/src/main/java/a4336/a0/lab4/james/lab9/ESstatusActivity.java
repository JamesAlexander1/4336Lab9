package a4336.a0.lab4.james.lab9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ESstatusActivity extends AppCompatActivity {

    ExternalStorageUse external_storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esstatus);

        external_storage = new ExternalStorageUse();

        final Button backButton = (Button) findViewById(R.id.BackToESbutton);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final TextView status = (TextView) findViewById(R.id.ESstatusView);
        if(external_storage.getExternalDirectoryStatus()){
            status.setText("External Storage Found");
        }else{
            status.setText("External Storage Not Found");
        }
        final TextView size = (TextView) findViewById(R.id.sizeTextView);
        size.setText(Long.toString(external_storage.getExternalDirectoryStorageSize()));
    }
}
