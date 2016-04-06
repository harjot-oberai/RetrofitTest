package com.example.harjot.retrofittest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.harjot.retrofittest.api.GitApiInterface;
import com.example.harjot.retrofittest.models.GitResult;
import com.example.harjot.retrofittest.models.Item;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
public class MainActivity extends AppCompatActivity {

    private String baseUrl = "https://api.github.com" ;
    private UserAdapter adapter;
    List<Item> users;
    EditText name;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = (Button) findViewById(R.id.searchBtn);
        name = (EditText) findViewById(R.id.searchName);

        final ListView listView = (ListView) findViewById(R.id.userList);
        users = new ArrayList<Item>();

        search.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "", "loading...");
                        RestClient.GitApiInterface service = RestClient.getClient();
                        Call<GitResult> call = service.getUsersNamedTom(name.getText().toString());
                        call.enqueue(new Callback<GitResult>() {
                            @Override
                            public void onResponse(Response<GitResult> response) {
                                dialog.dismiss();
                                if (response.isSuccess()) {
                                    // request successful (status code 200, 201)
                                    Log.d("RETRO",response.body()+"");
                                    GitResult result = response.body();
                                    users = result.getItems();
                                    adapter = new UserAdapter(MainActivity.this, users);
                                    listView.setAdapter(adapter);
                                } else {
                                    //request not successful (like 400,401,403 etc)
                                    //Handle errors
                                }
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                dialog.dismiss();
                                Log.d("RETRO", t.getMessage());
                            }
                        });
                    }
                }
        );
    }
}
