package bsru.thongmook.chinnawat.floatingmarket;

import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jibble.simpleftp.SimpleFTP;

public class SignUpActivity extends AppCompatActivity {

    // exp
    private Button button;
    private EditText nameEditText, userEditText , passEditText, emailEditText;
    private String nameString, userString, passString, emailString;
    private Uri uri;
    private boolean aBoolean = true;
    private int anInt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //bindwidget
        bindWidget();

        //buttoncontroller
        buttonController();


    }   // main method


    private void buttonController() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get value form edittext
                emailString = emailEditText.getText().toString().trim();
                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passString = passEditText.getText().toString().trim();

                //check space
                if (emailString.equals("") || nameString.equals("") || userString.equals("") || passString.equals("")) {
                    //true have space
                    MyAlert myAlert = new MyAlert(SignUpActivity.this);
                    myAlert.myDialog("กรูณากรอก", "กรุณากรอกช่องว่าให้ครบ");
                }else {
                    // false not have spave
                    uploadValueToServer();
                }

            }   //onclick
        });
    }   //buttoncontroller

    private void uploadValueToServer() {

        try {
            //upload
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                    .Builder()
                    .permitAll()
                    .build();
            StrictMode.setThreadPolicy(policy);
            SimpleFTP simpleFTP = new SimpleFTP();
            simpleFTP.connect("ftp.champslot.hol.es", 21,
                    "u424745988", "0844127718");
            simpleFTP.bin();
            simpleFTP.disconnect();

            //upload text
            String tag = "chkupload";
            Log.d(tag, "Name ==> " + nameString);
            Log.d(tag, "user ==> " + userString);
            Log.d(tag, "pass ==> " + passString);
            Log.d(tag, "email ==> " + emailString);


            AddValueToUser addValueToUser = new AddValueToUser(SignUpActivity.this,
                    nameString, userString, passString, emailString,
                    Integer.toString(anInt));
            addValueToUser.execute()



        } catch (Exception e) {
            Log.d("upload", "e upload ==> " + e.toString());
        }

    }

    private void bindWidget() {

        emailEditText = (EditText) findViewById(R.id.editText7);
        nameEditText = (EditText) findViewById(R.id.editText3);
        userEditText = (EditText) findViewById(R.id.editText4);
        passEditText = (EditText) findViewById(R.id.editText5);
        button = (Button) findViewById(R.id.button4);

    }
}   // main class
