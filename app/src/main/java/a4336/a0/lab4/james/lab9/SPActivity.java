package a4336.a0.lab4.james.lab9;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SPActivity extends AppCompatActivity {

    private String fileName = "Lab9File";
    private String key = "Test1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);

        final SharedPreferences settings = getSharedPreferences(fileName, 0);
        final SharedPreferences.Editor editor = settings.edit();
       // String process = settings.getString(key, "0");

        final Button SPBackBtn = (Button) findViewById(R.id.SPBackbutton);
        SPBackBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final Button RetrieveButton = (Button) findViewById(R.id.SPRetbutton);
        RetrieveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                RetrieveKeyValue(settings);
            }
        });

        final EditText editText = (EditText) findViewById(R.id.editTextForStore);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                boolean success = false;
                if(i == EditorInfo.IME_ACTION_DONE){

                    String input = editText.getText().toString();
                    storeKeyValue(editor, input);

                    RetrieveKeyValue(settings);
                    success = true;
                }
                return success;
            }
        });

        final Button deleteSPButton = (Button) findViewById(R.id.SPDeletebutton);
        deleteSPButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                deleteKeyValue(editor);
            }
        });
    }
    private void RetrieveKeyValue(SharedPreferences pref){

        String process = pref.getString(key, "0");

        if(process.equals("0")){
            process = "No Value";
        }

        TextView retValue = (TextView) findViewById(R.id.retValueView);
        retValue.setText(process);
    }

    private void storeKeyValue(SharedPreferences.Editor editor, String stringInput){
        editor.putString(key, stringInput);
        editor.commit();
    }
    private void deleteKeyValue(SharedPreferences.Editor editor){
        editor.remove(key).commit();
    }
}
