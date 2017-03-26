package bsru.thongmook.chinnawat.floatingmarket;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by Hello on 27/3/2560.
 */

public class AddValueToUser extends AsyncTask<String, Void, String> {
    //exp
    private Context context;
    private String nameString, userString, passString, emailString;
    private ProgressDialog progressDialog;

    public AddValueToUser(Context context,
                          String nameString,
                          String userString,
                          String emailString,
                          String passString) {
        this.context = context;
        this.nameString = nameString;
        this.userString = userString;
        this.emailString = emailString;
        this.passString = passString;
    }   //constructor

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Upload Value",
                "Please Wait");
    }


    @Override
    protected String doInBackground(String... params) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("user_name", userString)
                    .add("user_pass", passString)
                    .add("name", nameString)
                    .add("email", emailString)
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(params[0]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            Log.d("addvalue", "e doin" + e.toString());


            return null;
        }
    }
}
