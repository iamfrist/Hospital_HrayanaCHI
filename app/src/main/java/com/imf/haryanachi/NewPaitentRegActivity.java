package com.imf.haryanachi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import com.imf.haryanachi.databinding.ActivityNewPaitentRegBinding;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.fetch.ChcResponse;
import com.imf.haryanachi.networkModel.fetch.ComoList;
import com.imf.haryanachi.networkModel.fetch.DataItem;
import com.imf.haryanachi.networkModel.fetch.fetch_phc.PhpRequest;
import com.imf.haryanachi.networkModel.fetch.fetch_phc.PhpResponse;
import com.imf.haryanachi.networkModel.selfRegi.SelfRegRequest;
import com.imf.haryanachi.networkModel.selfRegi.SelfRegResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPaitentRegActivity extends AppCompatActivity {


    ActivityNewPaitentRegBinding binding;
    List<com.imf.haryanachi.networkModel.fetch.fetch_phc.DataItem> phcDataItems;
    List<DataItem> dataItems;
    List<ComoList> comoList;
    Spinner chc_spinner, phc_spinner;
    String chc_id_Str, phc_id_Str, comoStr, genderstr, covide;
    ProgressBar progressBar;
    RadioGroup radioGroup, covideRadioGroup;
    CheckBox chk1,chk2,chk3,chk4,chk5,chk6,chk7,chk8,chk9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_paitent_reg);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_paitent_reg);

        chc_spinner = findViewById(R.id.chcspinner);
        phc_spinner = findViewById(R.id.phpspinner);

        progressBar = findViewById(R.id.progressBar);
        radioGroup = findViewById(R.id.yourRadioGroup);
        covideRadioGroup = findViewById(R.id.covidRadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);

                genderstr = radioButton.getText().toString();
            }
        });
        covideRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);

                covide = radioButton.getText().toString();
            }
        });


        progressBar.setVisibility(View.GONE);
        binding.regsubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.name.getText().toString().isEmpty()) {
                    binding.name.setError("Add your name");
                } else if (binding.lastname.getText().toString().isEmpty()) {
                    binding.lastname.setError("Add your lastname");
                } else if (binding.mobileno.getText().toString().isEmpty()) {
                    binding.mobileno.setError("Add your mobile no");
                } else if (binding.mobileno.getText().length() < 10) {
                    binding.mobileno.setError("Add your mobile no");
                } else if (binding.age.getText().toString().isEmpty()) {
                    binding.age.setError("Add your age");
                } else if (binding.password1.getText().toString().isEmpty()) {
                    binding.password1.setError("Add password");
                } else if (binding.address.getText().toString().isEmpty()) {
                    binding.address.setError("Add your address");
                }  else if (chc_id_Str.isEmpty()) {
                    Toast.makeText(NewPaitentRegActivity.this, "select chc_id", Toast.LENGTH_SHORT).show();
                } else if (phc_id_Str.isEmpty()) {
                    Toast.makeText(NewPaitentRegActivity.this, "select phc_id", Toast.LENGTH_SHORT).show();
                } else {
                    //pname,lname,p_age,pgender,covid_status,comorbidities,pnumber,password,patientsaddress,chc_id,phc_id;
                    SelfRegRequest request = new SelfRegRequest();
                    request.setPname(binding.name.getText().toString());
                    request.setLname(binding.lastname.getText().toString());
                    request.setP_age(binding.age.getText().toString());
                    request.setPgender(genderstr);
                    request.setCovid_status(covide);
                    request.setData(comoList);
                    request.setPnumber(binding.mobileno.getText().toString());
                    request.setPassword(binding.password1.getText().toString());
                    request.setPatientsaddress(binding.address.getText().toString());
                    request.setChc_id(chc_id_Str);
                    request.setPhc_id(phc_id_Str);

                    RegPatient(request);

                }
            }
        });

        //getChc();
        binding.comotxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectComo();

            }

        });

        comoList = new ArrayList<>();

      /*  ArrayAdapter<ComoList> adapter = new ArrayAdapter<ComoList>(this,
                android.R.layout.simple_spinner_item, comoList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comospinner.setAdapter(adapter);

        comospinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ComoList user = (ComoList) parent.getSelectedItem();
                displayComo(user);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/
    }

    private void selectComo() {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.comorbidities_layout);
        dialog.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);

        chk1=(CheckBox) dialog.findViewById(R.id.checkBox);
        chk2=(CheckBox) dialog.findViewById(R.id.checkBox2);
        chk3=(CheckBox) dialog.findViewById(R.id.checkBox3);
        chk4=(CheckBox) dialog.findViewById(R.id.checkBox4);
        chk5=(CheckBox) dialog.findViewById(R.id.checkBox5);
        chk6=(CheckBox) dialog.findViewById(R.id.checkBox6);
        chk7=(CheckBox) dialog.findViewById(R.id.checkBox7);

       Button submitbtn =(Button)dialog.findViewById(R.id.addbtn);
     /* Button cancel =(Button)dialog.findViewById(R.id.cancelbtn);
       cancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialog.dismiss();
           }
       });
*/
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                binding.comotxt.setText("Comorbidities Selected");
                for (ComoList str : comoList) {

                }
            }
        });

    }
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.checkBox:
                ComoList  c=new ComoList(chk1.getText().toString());
                comoList.add(c);


                break;
            case R.id.checkBox2:

                ComoList  c2=new ComoList(chk2.getText().toString());
                comoList.add(c2);

                break;

            case R.id.checkBox3:
                ComoList  c3=new ComoList(chk3.getText().toString());
                comoList.add(c3);

                break;
            case R.id.checkBox4:
                ComoList  c4=new ComoList(chk4.getText().toString());
                comoList.add(c4);

                break;
            case R.id.checkBox5:
                ComoList  c5=new ComoList(chk5.getText().toString());
                comoList.add(c5);

                break;
            case R.id.checkBox6:
                ComoList  c6=new ComoList(chk6.getText().toString());
                comoList.add(c6);

                break;
            case R.id.checkBox7:
                ComoList  c7=new ComoList(chk7.getText().toString());
                comoList.add(c7);

                break;

        }
    }



    private void RegPatient(SelfRegRequest request) {
        Call<SelfRegResponse> responceCall = ApiClient.getApiClient().selfReg(request);

        responceCall.enqueue(new Callback<SelfRegResponse>() {
            @Override
            public void onResponse(Call<SelfRegResponse> call, Response<SelfRegResponse> response) {
                if (response.body().getStatus().equals("Success")) {


                    SharedPreferences sharedPref1 = getSharedPreferences("profile", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = sharedPref1.edit();
                    editor1.putString("pname", response.body().getData().getPname());
                    editor1.putString("lname", response.body().getData().getLname());
                    editor1.putString("p_age", response.body().getData().getPAge());
                    editor1.putString("pgender", response.body().getData().getPgender());
                    editor1.putString("covid_status", response.body().getData().getCovidStatus());
                    editor1.putString("comorbidities", response.body().getData().getComorbidities());
                    editor1.putString("pnumber", response.body().getData().getPnumber());
                    editor1.putString("patientsaddress", response.body().getData().getPatientsaddress());
                    editor1.putString("qrcode", response.body().getData().getQrcode());
                    editor1.putString("registered_date", response.body().getData().getRegisteredDate());
                    editor1.apply();


                    SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("session", "profile");
                    editor.apply();

                /*    Intent intent=new Intent(NewPaitentRegActivity.this,ProfileActivity.class);
                    startActivity(intent);
                    finish();
*/
                    new AlertDialog.Builder(NewPaitentRegActivity.this)
                            .setTitle("New Patient")
                            .setMessage("Your Registration Successfully Done!")

                             .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Continue with delete operation

                                    Intent intent=new Intent(NewPaitentRegActivity.this,ProfileActivity.class);
                                    startActivity(intent);
                                    finish();

                                }
                            })

                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }
            }

            @Override
            public void onFailure(Call<SelfRegResponse> call, Throwable t) {

            }
        });


    }

    private void displayComo(ComoList user) {
      //  comoStr = user.getName();
    }


    private void getChc() {
        Call<ChcResponse> responceCall = ApiClient.getApiClient().fetch_chc();
        responceCall.enqueue(new Callback<ChcResponse>() {
            @Override
            public void onResponse(Call<ChcResponse> call, Response<ChcResponse> response) {

                Log.d("responcesss",(response.body().getMessage()));
             /*   if (response.body().getStatus().equals("Success")) {


                    dataItems = response.body().getData();
                    ArrayAdapter<DataItem> adapter = new ArrayAdapter<DataItem>(NewPaitentRegActivity.this,
                            android.R.layout.simple_spinner_item, dataItems);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    chc_spinner.setAdapter(adapter);
                }*/
            }

            @Override
            public void onFailure(Call<ChcResponse> call, Throwable t) {

            }
        });

        chc_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DataItem user = (DataItem) parent.getSelectedItem();
                getdisplaychc(user);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        phc_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                com.imf.haryanachi.networkModel.fetch.fetch_phc.DataItem user = (com.imf.haryanachi.networkModel.fetch.fetch_phc.DataItem) parent.getSelectedItem();
                getphcDisplay(user);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void getphcDisplay(com.imf.haryanachi.networkModel.fetch.fetch_phc.DataItem user) {
        String name = user.getPhcName();
        phc_id_Str = user.getPhcId();
        String userData = "Name: " + name;
    }

    public void getSelectedUser(View v) {
        DataItem user = (DataItem) chc_spinner.getSelectedItem();
        getdisplaychc(user);
    }

    private void getdisplaychc(DataItem user) {
        String name = user.getChcName();
        chc_id_Str = user.getChcId();
        String userData = "Name: " + name;

        getPhp(chc_id_Str);
    }

    private void getPhp(String id) {

        PhpRequest request = new PhpRequest();
        request.setChc_id(id);
        Call<PhpResponse> responceCall = ApiClient.getApiClient().fetch_phc(request);

        responceCall.enqueue(new Callback<PhpResponse>() {
            @Override
            public void onResponse(Call<PhpResponse> call, Response<PhpResponse> response) {
                if (response.body().getStatus().equals("Success")) {

                    phcDataItems = response.body().getData();
                    // adapter = new ChcAdapter(NewPaitentRegActivity.this, dataItems);
                    ArrayAdapter<com.imf.haryanachi.networkModel.fetch.fetch_phc.DataItem> adapter = new ArrayAdapter<com.imf.haryanachi.networkModel.fetch.fetch_phc.DataItem>(NewPaitentRegActivity.this,
                            android.R.layout.simple_spinner_item, phcDataItems);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    phc_spinner.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<PhpResponse> call, Throwable t) {

            }
        });

    }


}