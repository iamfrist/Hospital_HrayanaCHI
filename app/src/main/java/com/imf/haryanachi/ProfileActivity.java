package com.imf.haryanachi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
//com.example.ambalachi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imf.haryanachi.adapter.UserAdapter;
import com.imf.haryanachi.databinding.ActivityProfileBinding;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.patientLogin.PatientsdataItem;
import com.imf.haryanachi.networkModel.patientLogin.PloginRequest;
import com.imf.haryanachi.networkModel.patientLogin.PloginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    String pname, lname, p_age, pgender, covid_status, comorbidities, pnumber, password, patientsaddress, registered_date, qrcode, session;
    ActivityProfileBinding binding;
    String usernameStr, passwordStr;
    RecyclerView recyclerView;
    ImageView callImageView,logoutImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
       //     binding.progressBar.setVisibility(View.VISIBLE);
        SharedPreferences sharedPref1 = getSharedPreferences("logindata", Context.MODE_PRIVATE);
        usernameStr = sharedPref1.getString("username", "");
        passwordStr = sharedPref1.getString("password", "");
        recyclerView=findViewById(R.id.recyc);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
        session = sharedPref.getString("session", "");
            binding.call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ProfileActivity.this, CallActivity.class));
                }
            });
            binding.log.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new AlertDialog.Builder(ProfileActivity.this)
                            .setTitle("LOGOUT")
                            .setMessage("Do You Want To Logout ?")

                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Continue with delete operation

                                    SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPref.edit();
                                    editor.putString("session", "cancel");
                                    editor.putString("name", "");
                                    editor.putString("id", "");
                                    editor.apply();
                                    Intent intent = new Intent(ProfileActivity.this, AllLognActivity.class);
                                    startActivity(intent);
                                    finish();
                                    dialog.dismiss();
                                }
                            })

                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();


                }
            });
        if (sharedPref.getString("session", "").equals("profile")) {
            if (!usernameStr.isEmpty() && !passwordStr.isEmpty()) {
                getlogin(usernameStr, passwordStr);
            }
        } else {
            if (!usernameStr.isEmpty() && !passwordStr.isEmpty()) {
                getlogin(usernameStr, passwordStr);
            }
        }





    }

    private void getlogin(String username, String password) {

        PloginRequest ploginRequest = new PloginRequest();
        ploginRequest.setUsername(username);
        ploginRequest.setPassword(password);

        Call<PloginResponse> responceCall = ApiClient.getApiClient().patients_login(ploginRequest);

        responceCall.enqueue(new Callback<PloginResponse>() {
            @Override
            public void onResponse(Call<PloginResponse> call, Response<PloginResponse> response) {
                if (response.body().getStatus().equals("Success")) {

                    binding.fname.setText(response.body().getData().getFamilyname());
                    binding.comobility.setText(response.body().getData().getComorbidities());
                    binding.address.setText(response.body().getData().getPatientsaddress());
                    binding.city.setText(response.body().getData().getFamilycity());
                    binding.state.setText(response.body().getData().getFamilystate());
                    binding.postivep.setText(response.body().getData().getPositivemember());
                    binding.mobile.setText(response.body().getData().getPnumber());
                    binding.member.setText(response.body().getData().getFamilymember());
                    Log.d("stateeee",response.body().getData().getFamilystate()+"      jhknjknjk");



                    List<PatientsdataItem> list=response.body().getPatientsdata();

                    UserAdapter adapter=new UserAdapter(list,ProfileActivity.this);
                    recyclerView.setAdapter(adapter);

                    SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("session", "profile");
                    editor.apply();

                } else {
                    new AlertDialog.Builder(ProfileActivity.this)
                            .setMessage("Something went wrong. Please try again")
                            .setCancelable(false)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(ProfileActivity.this, AllLognActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<PloginResponse> call, Throwable t) {

                Log.d("acscascsasd", t.getMessage());
            }
        });


    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(ProfileActivity.this)
                .setTitle("Exit")
                .setMessage("Do You Want To Exit App ?")

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        finish();
                        dialog.dismiss();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}