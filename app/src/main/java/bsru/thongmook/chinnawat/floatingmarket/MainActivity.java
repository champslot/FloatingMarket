package bsru.thongmook.chinnawat.floatingmarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //explicit
    private Button signInButton, signUpButton, skipButton;
    private EditText userEditText, passEditText;
    private String  userString, passString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind widget
        signInButton = (Button) findViewById(R.id.button);
        signUpButton = (Button) findViewById(R.id.button2);
        skipButton = (Button) findViewById(R.id.button3);
        userEditText = (EditText) findViewById(R.id.editText);
        passEditText = (EditText) findViewById(R.id.editText2);

        //button controller
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check ที่ว่าง edittext
                userString = userEditText.getText().toString().trim();
                passString = passEditText.getText().toString().trim();
                if (userString.equals("") || passString.equals("")) {
                    MyAlert myAlert = new MyAlert(MainActivity.this);
                    myAlert.myDialog("มีช่องว่าง", "กรุณากรอกช่องว่าง");
                } else {

                }

            }
        });



    } // main method



}   //main class
