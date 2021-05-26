package com.imf.haryanachi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.imf.haryanachi.databinding.ActivityEmployeeProfileBinding;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.overall_state_count.Data;
import com.imf.haryanachi.networkModel.overall_state_count.OveralStateResponse;
import com.imf.haryanachi.networkModel.todays_state_count.TodaysStateResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeProfileActivity extends AppCompatActivity {

    ActivityEmployeeProfileBinding binding;
    private String dname, id, visitId, chc_id, phc_id, team_number, distric, centername, wardname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_profile);

        SharedPreferences sharedPref1 = getSharedPreferences("PGI", Context.MODE_PRIVATE);
        dname = sharedPref1.getString("name", "");
        id = sharedPref1.getString("id", "");
        chc_id = sharedPref1.getString("chc_id", "");
        phc_id = sharedPref1.getString("phc_id", "");
        team_number = sharedPref1.getString("team_member", "");
        distric = sharedPref1.getString("distric", "");
        centername = sharedPref1.getString("centername", "");
        wardname = sharedPref1.getString("wardname", "");

        binding.DISTRICT.setText(distric);
        binding.chc.setText(centername);
        binding.PHC.setText(wardname);
        binding.TEAMNO.setText(team_number);

        binding.callimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeProfileActivity.this, CallActivity.class);
                startActivity(intent);

            }
        });
        binding.logimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(EmployeeProfileActivity.this)
                        .setTitle("LOGOUT")
                        .setMessage("Do You Want To Logout ?")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                SharedPreferences sharedPref1 = getSharedPreferences("PGI", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor1 = sharedPref1.edit();
                                editor1.putString("name", "");
                                editor1.putString("id", "");
                                editor1.putString("chc_id", "");
                                editor1.putString("phc_id","");
                                editor1.putString("team_member", "");
                                editor1.putString("wardname", "");
                                editor1.putString("centername","");
                                editor1.putString("distric", "");
                                editor1.apply();

                                SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("session", "cancel");
                                editor.putString("name", "");
                                editor.putString("id", "");
                                editor.apply();





                                Intent intent = new Intent(EmployeeProfileActivity.this, AllLognActivity.class);
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

        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getSharedPreferences("update", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("btn", "ok");
                editor.apply();
                Intent intent = new Intent(EmployeeProfileActivity.this, DashBoardActivity2.class);
                startActivity(intent);
                finish();
            }
        });

        binding.name.setText("WELCOME " + dname);
        binding.regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeProfileActivity.this, RegiActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeProfileActivity.this, EmployeeDashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DashBoardActivity2
                Intent intent = new Intent(EmployeeProfileActivity.this, DashBoardActivity2.class);
                startActivity(intent);
                finish();
                SharedPreferences sharedPref = getSharedPreferences("update", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("btn", "cancel");
                editor.apply();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logout:


                new AlertDialog.Builder(EmployeeProfileActivity.this)
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
                                Intent intent = new Intent(EmployeeProfileActivity.this, AllLognActivity.class);
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



                return true;

        }
        return false;
    }
    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(EmployeeProfileActivity.this)
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