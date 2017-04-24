package bsru.thongmook.chinnawat.floatingmarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //explicit
    private Button signInButton, signUpButton, skipButton;
    private EditText userEditText, passEditText;
    private String  userString, passString;
    private String[] loginStrings = new String[6]; // ข้อมูลใน db
    private static final String urlPHP = "http://swiftcodingthai.com/bsru/get_user.php";
    private boolean aBoolean = true;


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

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
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
                    // no space
                    checkUserPass();

                }

            }
        });

    } // main method

    private void checkUserPass() {

        try {

            GetUser getUser = new GetUser(MainActivity.this);
            getUser.execute(urlPHP);
            String strJSON = getUser.get();
            Log.d("2404v1", "strJSON==>" + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);//ตัดคำ
            for (int i = 0; i < jsonArray.length(); i += 1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (userString.equals(jsonObject.getString("user_name"))) {
                    loginStrings[0] = jsonObject.getString("id");
                    loginStrings[1] = jsonObject.getString("user_name");
                    loginStrings[2] = jsonObject.getString("user_pass");
                    loginStrings[3] = jsonObject.getString("name");
                    loginStrings[4] = jsonObject.getString("email");
                    loginStrings[5] = jsonObject.getString("Lat");
                    loginStrings[6] = jsonObject.getString("Lng");

                    aBoolean = false;

                } // if

            } // for loop

            if (aBoolean) {
                //user false
                MyAlert myAlert = new MyAlert(MainActivity.this);
                myAlert.myDialog("ไม่พบ User", "ไม่มี " + userString + " ในระบบ ");

            } else if (!passString.equals(loginStrings[2])) {
                // password false
                MyAlert myAlert = new MyAlert(MainActivity.this);
                myAlert.myDialog("Wrong Password", "Please Try Agian Wrong Password");
            } else {
                //  password pass
                Toast.makeText(MainActivity.this, " Welcome " + loginStrings[3],
                        Toast.LENGTH_SHORT).show();

                //  intent to menu
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                intent.putExtra("Login", loginStrings);
                startActivity(intent);
                finish();
            }

        } catch (Exception e) {
            Log.d("2404V1", "e checkUserPass ==> " + e.toString());
        }

    }   //check user pass



}   //main class
