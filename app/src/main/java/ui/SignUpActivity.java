package ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appbuildersworld.lenders.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import connectivity.RetrofitClient;
import interfaces.RetrofitInterface;
import model.MUser;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private MUser newUser;
    private EditText etUserName, etNrc, etPhoneNumber, etPassword, etEmail;
    private Button bSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUserName = (EditText) findViewById(R.id.names);
        etNrc = (EditText) findViewById(R.id.nrc);
        etEmail = (EditText) findViewById(R.id.email);
        etPhoneNumber = (EditText) findViewById(R.id.phonenumber);
        etPassword = (EditText) findViewById(R.id.password);
        bSignUp = (Button)findViewById(R.id.bSignUp);

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    }

    private void signUp() {

        final ProcessDialog registerDialog = new ProcessDialog(this);
        registerDialog.showProcessingDialog("Creating Account.. Please wait..");
        registerDialog.setOnActionListener(new ProcessDialog.ActionClickListener() {
            @Override
            public void onActionClick() {

            }
        });
        registerDialog.setOnCloseDialogListener(new ProcessDialog.CloseDialogClickListener() {
            @Override
            public void onCloseDialogClick() {
                Toast.makeText(SignUpActivity.this, "Please wait for this process to complete...", Toast.LENGTH_SHORT).show();
            }
        });
        registerDialog.setOnBackPressedListener(new ProcessDialog.BackPressedListener() {
            @Override
            public void onBackPressedClick() {
                Toast.makeText(SignUpActivity.this, "Please wait for this process to complete...", Toast.LENGTH_SHORT).show();
            }
        });

        RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
        String userName = etUserName.getText().toString();
        String nrc = etNrc.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhoneNumber.getText().toString();
        String password = etPassword.getText().toString();


        retrofitInterface.addUser(userName, nrc, phone, password, email, "addUser").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> netResponse) {

                try {



                    final String response = netResponse.body().string();
                    Log.d("NNN", "Response" + response);
                    JSONObject responseJObject = new JSONObject(response);



                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Log.e(TAG, "Unable to submit post to API.");

                Log.d("NNN", "Failed:" + t.getLocalizedMessage());
            }
        });
    }

    }

}