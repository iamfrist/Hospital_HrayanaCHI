package com.imf.haryanachi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
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
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.imf.haryanachi.databinding.ActivityRegiBinding;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.distric.DataItem;
import com.imf.haryanachi.networkModel.distric.DistricResponse;
import com.imf.haryanachi.networkModel.fetch.ComoList;
import com.imf.haryanachi.networkModel.fetch_mobile_hcf.HcfRequest;
import com.imf.haryanachi.networkModel.fetch_mobile_hcf.HcfResponse;
import com.imf.haryanachi.networkModel.fetch_mobile_phc.PhcRequest;
import com.imf.haryanachi.networkModel.fetch_mobile_phc.PhcResponse;
import com.imf.haryanachi.networkModel.pReg.Data;
import com.imf.haryanachi.networkModel.pReg.RegRequest;
import com.imf.haryanachi.networkModel.pReg.RegResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegiActivity extends AppCompatActivity implements View.OnClickListener {

    List<ComoList> comoList;
    CheckBox chk1, chk2, chk3, chk4, chk5, chk6, chk7, chk8, chk9;
    SharedPreferences sharedPreferences;
    EditText pnameEditText, lnameEditText, mnoEditText, ageEditText, paddressEditText, icmrEditText;
    TextView duartionTextView;
    LinearLayout lin1, lin2;
    RecyclerView recyclerView;
    List<Data> regList;
    Button subButton, cancelButton;
    RadioButton maleradioButton, femaleradioButton;
    ActivityRegiBinding binding;
    RadioGroup radioGroup, vaccynRdaiogroup;
    Spinner disspinner, phcspinner, chcspinner;
    TextView dose1, dose2;
    CalendarView calendarView;
    String genderstr, comoStr;
    boolean regTxtVisible;
    int clickCount = 0;
    ArrayAdapter<DataItem> adapter;
    String districStr, phcStr, hfcStr;
    boolean isGender;
    boolean checked;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener dateListener;
    String vaccynated;
    boolean isVaccynated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regi);
       /* regList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RegiActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
       */

        binding = DataBindingUtil.setContentView(this, R.layout.activity_regi);
        disspinner = findViewById(R.id.disspinner);
        chcspinner = findViewById(R.id.chcspinner);
        phcspinner = findViewById(R.id.phcspinner);
        getDistric();

        comoList = new ArrayList<>();
        binding.addbtn.setVisibility(View.GONE);
        //  binding.home.setVisibility(View.GONE);


        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.name.getText().toString().isEmpty()) {
                    binding.name.setError("Add your name");
                } else if (binding.fno.getText().toString().isEmpty()) {
                    binding.fno.setError("Add your family member number");
                } else if (binding.nocomo.getText().toString().isEmpty()) {
                    binding.nocomo.setError("Add number of COMORBIDITIES ");
                } else if (Integer.parseInt(binding.nocomo.getText().toString()) > Integer.parseInt(binding.fno.getText().toString())) {
                    //Toast.makeText(RegiActivity.this, "Comorbidities can not be more than family members", Toast.LENGTH_LONG).show();
                    binding.nocomo.setError("Comorbidities can not be more than family members");
                } else if (binding.pno.getText().toString().isEmpty()) {
                    binding.pno.setError("Add positive family member number");
                } else if (binding.pno.getText().toString().equals("0")) {
                    binding.pno.setError("positive member number should not be zero");
                } else if (Integer.parseInt(binding.pno.getText().toString()) > Integer.parseInt(binding.fno.getText().toString())) {
                    // Toast.makeText(RegiActivity.this, "Positive Patient can not be more than family members", Toast.LENGTH_LONG).show();
                    binding.pno.setError("Positive Patient can not be more than family members");
                } else if (binding.address.getText().toString().isEmpty()) {
                    binding.address.setError("Add your address");
                } else if (binding.city.getText().toString().isEmpty()) {
                    binding.city.setError("Add your city");
                } else if (binding.no.getText().toString().isEmpty()) {
                    binding.no.setError("Add your mobile no");
                } else if (binding.no.length() < 10) {
                    binding.no.setError("Add your mobile no should be 10 digits");
                } else if (binding.password.getText().toString().isEmpty()) {
                    binding.password.setError("please write password ");
                } else if (binding.state.getText().toString().isEmpty()) {
                    binding.state.setError("Add your state");
                } else {
                    // binding.regTxt.setVisibility(View.VISIBLE);
                    regTxtVisible = true;
                    //binding.addbtn.setVisibility(View.VISIBLE);
                    binding.pno.setEnabled(false);
                    comoList.clear();
                    getPopup();


                }
            }
        });
        binding.dprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegiActivity.this, EmployeeProfileActivity.class));
                finish();
            }
        });

        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegiActivity.this, AllLognActivity.class));
                finish();
            }
        });

        SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);


        binding.homeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPref.getString("session", "").equals("ok")) {
                    startActivity(new Intent(RegiActivity.this, EmployeeProfileActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(RegiActivity.this, AllLognActivity.class));
                    finish();
                }
            }
        });

    }

    private void getPopup() {

        comoList.clear();
        if (!regTxtVisible) {
        } else {

            Dialog dialog = new Dialog(RegiActivity.this);
            dialog.setContentView(R.layout.dialog_layout);
            dialog.setCancelable(false);
            dialog.show();

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER;
            dialog.getWindow().setAttributes(lp);

            pnameEditText = dialog.findViewById(R.id.pname);
            lnameEditText = dialog.findViewById(R.id.lname);
            mnoEditText = dialog.findViewById(R.id.pmno);
            ageEditText = dialog.findViewById(R.id.age);
            duartionTextView = dialog.findViewById(R.id.duration);
            icmrEditText = dialog.findViewById(R.id.icmrid);
            paddressEditText = dialog.findViewById(R.id.paddress);


            subButton = dialog.findViewById(R.id.psub);
            cancelButton = dialog.findViewById(R.id.cancel);
            maleradioButton = dialog.findViewById(R.id.male);
            femaleradioButton = dialog.findViewById(R.id.female);
            radioGroup = (RadioGroup) dialog.findViewById(R.id.yourRadioGroup);
            vaccynRdaiogroup = (RadioGroup) dialog.findViewById(R.id.vaccynRadioGroup);
            paddressEditText.setText(binding.address.getText().toString());
            mnoEditText.setText(binding.no.getText().toString());
            lin1 = dialog.findViewById(R.id.lin1);
            lin2 = dialog.findViewById(R.id.lin2);
            dose1 = dialog.findViewById(R.id.dose1);
            dose2 = dialog.findViewById(R.id.dose2);


            chk1 = (CheckBox) dialog.findViewById(R.id.checkBox1);
            chk2 = (CheckBox) dialog.findViewById(R.id.checkBox2);
            chk3 = (CheckBox) dialog.findViewById(R.id.checkBox3);
            chk4 = (CheckBox) dialog.findViewById(R.id.checkBox4);
            chk5 = (CheckBox) dialog.findViewById(R.id.checkBox5);
            chk6 = (CheckBox) dialog.findViewById(R.id.checkBox6);
            chk7 = (CheckBox) dialog.findViewById(R.id.checkBox7);
            chk8 = (CheckBox) dialog.findViewById(R.id.checkBox8);

            chk1.setOnClickListener(this);
            chk2.setOnClickListener(this);
            chk3.setOnClickListener(this);
            chk4.setOnClickListener(this);
            chk5.setOnClickListener(this);
            chk6.setOnClickListener(this);
            chk7.setOnClickListener(this);
            chk8.setOnClickListener(this);
            vaccynRdaiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton radioButton = (RadioButton) dialog.findViewById(checkedId);
                    Toast.makeText(RegiActivity.this, radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                    vaccynated = radioButton.getText().toString();
                    if (vaccynated.equals("Yes")) {
                        isVaccynated = true;
                        lin1.setVisibility(View.VISIBLE);
                        lin2.setVisibility(View.VISIBLE);
                    } else {
                        isVaccynated = false;
                        lin1.setVisibility(View.GONE);
                        lin2.setVisibility(View.GONE);
                    }
                }
            });
            dose1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    android.text.format.DateFormat df = new android.text.format.DateFormat();
                    df.format("yyyy-M-dd hh:mm:ss a", new java.util.Date());
                    final Calendar newCalendar = Calendar.getInstance();
                    final DatePickerDialog startTime = new DatePickerDialog(RegiActivity.this, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            Calendar newDate = Calendar.getInstance();
                            newDate.set(year, monthOfYear, dayOfMonth);
                            dose1.setText(df.format("yyyy-M-dd", newDate));
                        }

                    }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                    startTime.show();
                }
            });
            dose2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    android.text.format.DateFormat df = new android.text.format.DateFormat();
                    df.format("yyyy-MM-dd hh:mm:ss a", new java.util.Date());

                    final Calendar newCalendar = Calendar.getInstance();
                    final DatePickerDialog startTime = new DatePickerDialog(RegiActivity.this, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            Calendar newDate = Calendar.getInstance();
                            newDate.set(year, monthOfYear, dayOfMonth);
                            dose2.setText(df.format("yyyy-M-dd", newDate));
                        }

                    }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                    startTime.show();
                }
            });


            myCalendar = Calendar.getInstance();
            dateListener = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {

                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    duartionTextView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                }

            };

            duartionTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getCalender();

                }
            });
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                      @Override
                                                      public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                          RadioButton radioButton = (RadioButton) dialog.findViewById(checkedId);
                                                          genderstr = radioButton.getText().toString();
                                                          isGender = true;
                                                      }
                                                  }
            );


            subButton.setOnClickListener((View dv) -> {

                if (pnameEditText.getText().toString().isEmpty()) {
                    pnameEditText.setError("enter patient name");
                } else if (lnameEditText.getText().toString().isEmpty()) {
                    lnameEditText.setError("enter patient last name");
                } else if (mnoEditText.getText().toString().isEmpty()) {
                    mnoEditText.setError("enter correct patient mobile no");
                } else if (mnoEditText.getText().length() < 10) {
                    mnoEditText.setError("Add your mobile no should be 10 digits");
                } else if (paddressEditText.getText().toString().isEmpty()) {
                    paddressEditText.setError("please enter your address");
                } else if (ageEditText.getText().toString().isEmpty()) {
                    ageEditText.setError("enter patient age");
                } else if (ageEditText.getText().toString().equals("0")) {
                    ageEditText.setError("patient age should not be zero");
                } /*else if (icmrEditText.getText().toString().isEmpty()) {
                    icmrEditText.setError("enter correct ICMR id");
                } */ else if (duartionTextView.getText().toString().isEmpty() || (duartionTextView.getText().toString().equals("YYYY-MM-DD"))) {
                    duartionTextView.setError("please select date from calender");
                } else if (!isGender) {
                    Toast toast = Toast.makeText(RegiActivity.this, "Please select your gender", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    isGender = false;
                } else if (comoList.isEmpty()) {
                    Toast toast = Toast.makeText(RegiActivity.this, "please select Comorbidities", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    checked = false;
                } else if (vaccynated.isEmpty()) {
                    Toast.makeText(RegiActivity.this, "Select Vaccynated Option", Toast.LENGTH_SHORT).show();
                } else if (vaccynated.isEmpty()) {
                    Toast.makeText(RegiActivity.this, "Select Vaccynated option", Toast.LENGTH_SHORT).show();
                }/*else if (dose1.getText().toString().equals("SELECT DATE")){
                    Toast.makeText(RegiActivity.this, "Select date", Toast.LENGTH_SHORT).show();
                } else if (dose2.getText().toString().equals("SELECT DATE")){
                    Toast.makeText(RegiActivity.this, "Select date", Toast.LENGTH_SHORT).show();
                } */ else {

                    binding.progressBar.setVisibility(View.VISIBLE);
                    SharedPreferences sharedPref1 = getSharedPreferences("PGI", Context.MODE_PRIVATE);
                    RegRequest regRequest = new RegRequest();

                    regRequest.setFamilyholder(binding.name.getText().toString());
                    // regRequest.setFamilylastname(binding.lastname.getText().toString());
                    regRequest.setFamilymember(binding.fno.getText().toString());
                    regRequest.setHoldermobile(binding.no.getText().toString());
                    regRequest.setPositivemember(binding.pno.getText().toString());
                    regRequest.setFamilyaddress(binding.address.getText().toString());
                    regRequest.setFamilycity(binding.city.getText().toString());
                    regRequest.setNo_como(comoList);

                    if (sharedPref1.getString("id", "").isEmpty()){
                        regRequest.setDoctor_id("");
                    }else regRequest.setDoctor_id(sharedPref1.getString("id", ""));

                    regRequest.setFamilystate(binding.state.getText().toString());
                    regRequest.setPname(pnameEditText.getText().toString());
                    regRequest.setLname(lnameEditText.getText().toString());
                    regRequest.setP_age(ageEditText.getText().toString());
                    regRequest.setPgender(genderstr);
                    regRequest.setPnumber(mnoEditText.getText().toString());
                    regRequest.setComorbidities(binding.nocomo.getText().toString());
                    regRequest.setPassword(binding.password.getText().toString());
                    regRequest.setDistric_id(districStr);
                    regRequest.setChc_id(hfcStr);//phcStr
                    regRequest.setPhc_id(phcStr);
                    regRequest.setDate(duartionTextView.getText().toString());
                    regRequest.setPaddress(paddressEditText.getText().toString());
                    regRequest.setIcmr_id(icmrEditText.getText().toString());
                    if (dose1.getText().toString().equals("SELECT DATE")) {
                        regRequest.setDose2_date("");
                    } else regRequest.setDose1_date(dose1.getText().toString());
                    if (dose2.getText().toString().equals("SELECT DATE")) {
                        regRequest.setDose2_date("");
                    } else regRequest.setDose2_date(dose2.getText().toString());
                    sendvalue(regRequest, dialog);
                    //Toast.makeText(RegiActivity.this, "" + binding.state.getText().toString(), Toast.LENGTH_SHORT).show();
                }

            });


        }

    }

    private void getCalender() {
        /*Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.calender_layout);
        dialog.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);

        calendarView = dialog.findViewById(R.id.calenderview);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //   String Date = dayOfMonth + "-" + (month + 1) + "-" + year;
                String Date = year + "-" + (month + 1) + "-" + dayOfMonth;
                //  dateView.setText(Date);
                duartionTextView.setText(Date);
                Log.d("dateeeee", Date);
                dialog.dismiss();
            }
        });*/


        DatePickerDialog datePickerDialog = new DatePickerDialog(RegiActivity.this, dateListener, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        Log.d("datee", datePickerDialog.getDatePicker().getYear() + "-" + datePickerDialog.getDatePicker().getMonth() + "-" + datePickerDialog.getDatePicker().getDayOfMonth());
        //following line to restrict future date selection

        String date = datePickerDialog.getDatePicker().getYear() + "-" + datePickerDialog.getDatePicker().getMonth() + "-" + datePickerDialog.getDatePicker().getDayOfMonth();

        // duartionTextView.setText(date);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();


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

        chk1 = (CheckBox) dialog.findViewById(R.id.checkBox);
        chk2 = (CheckBox) dialog.findViewById(R.id.checkBox2);
        chk3 = (CheckBox) dialog.findViewById(R.id.checkBox3);
        chk4 = (CheckBox) dialog.findViewById(R.id.checkBox4);
        chk5 = (CheckBox) dialog.findViewById(R.id.checkBox5);
        chk6 = (CheckBox) dialog.findViewById(R.id.checkBox6);
        chk7 = (CheckBox) dialog.findViewById(R.id.checkBox7);

        Button submitbtn = (Button) dialog.findViewById(R.id.addbtn);
       /* Button cancel = (Button) dialog.findViewById(R.id.cancelbtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });*/


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkBox1:
                ComoList c = new ComoList(chk1.getText().toString());
                comoList.add(c);


                break;
            case R.id.checkBox2:

                ComoList c2 = new ComoList(chk2.getText().toString());
                comoList.add(c2);

                break;

            case R.id.checkBox3:
                ComoList c3 = new ComoList(chk3.getText().toString());
                comoList.add(c3);

                break;
            case R.id.checkBox4:
                ComoList c4 = new ComoList(chk4.getText().toString());
                comoList.add(c4);

                break;
            case R.id.checkBox5:
                ComoList c5 = new ComoList(chk5.getText().toString());
                comoList.add(c5);

                break;
            case R.id.checkBox6:
                ComoList c6 = new ComoList(chk6.getText().toString());
                comoList.add(c6);

                break;
            case R.id.checkBox7:
                ComoList c7 = new ComoList(chk7.getText().toString());
                comoList.add(c7);

                break;
            case R.id.checkBox8:
                ComoList c8 = new ComoList(chk8.getText().toString());
                comoList.add(c8);
                checked = true;
                break;
        }
    }

    private void sendvalue(RegRequest regRequest, Dialog dialog) {
        Call<RegResponse> responceCall = ApiClient.getApiClient().reg(regRequest);

        responceCall.enqueue(new Callback<RegResponse>() {
            @Override
            public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {
                if (response.body().getMessage().equals("Patient inserted")) {
                    dialog.dismiss();
                    clickCount = clickCount + 1;
                    binding.progressBar.setVisibility(View.GONE);
                    isGender = false;
                    int i = Integer.parseInt(binding.pno.getText().toString());
                    if (clickCount == i) {
                        binding.addbtn.setEnabled(false);
                        sharedPreferences = getSharedPreferences("session", MODE_PRIVATE);
                        binding.register.setEnabled(false);
                        getRegPopup(response.body().getData().getRegno(), "0");

                       /* if (sharedPreferences.getString("session", "").equals("ok")) {
                            binding.dprofile.setVisibility(View.VISIBLE);
                        } else binding.home.setVisibility(View.VISIBLE);
                  */
                        //    getRegPopup(response.body().getData().getRegno());

                    } else {
                        binding.addbtn.setEnabled(true);
                        getRegPopup(response.body().getData().getRegno(), "1");

                    }
                   /* startActivity(new Intent(RegiActivity.this,PatientSuccessfulActivity.class));
                    finish();*/

                }
            }

            @Override
            public void onFailure(Call<RegResponse> call, Throwable t) {
                Log.d("failed", "failed" + t.getMessage());
                binding.progressBar.setVisibility(View.GONE);

            }
        });


    }

    private void getRegPopup(String regno, String s) {
        new AlertDialog.Builder(RegiActivity.this)
                .setTitle("REGISTRATION SUCCESSFUL")
                .setMessage("Your Registration No. is : " + regno)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation

                        if (s.equals("0")) {

                            SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);

                            if (sharedPref.getString("session", "").equals("ok")) {
                                startActivity(new Intent(RegiActivity.this, EmployeeProfileActivity.class));
                                finish();
                            } else {

                                SharedPreferences sharedPref1 = getSharedPreferences("logindata", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor1 = sharedPref1.edit();
                                editor1.putString("username", binding.no.getText().toString());
                                editor1.putString("password", binding.password.getText().toString());
                                editor1.apply();
                                Intent intent = new Intent(RegiActivity.this, ProfileActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else getPopup();


                        dialog.dismiss();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();


    }


    private void getDistric() {
        binding.progressBar.setVisibility(View.VISIBLE);

        Call<DistricResponse> responceCall = ApiClient.getApiClient().fetch_mobile_district();
        responceCall.enqueue(new Callback<DistricResponse>() {
            @Override
            public void onResponse(Call<DistricResponse> call, Response<DistricResponse> response) {
                if (response.body().isStatus()) {
                    List<DataItem> items = response.body().getData();
                    binding.progressBar.setVisibility(View.GONE);

                    ArrayAdapter<DataItem> adapter = new ArrayAdapter<DataItem>
                            (RegiActivity.this, android.R.layout.simple_spinner_item, items);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.disspinner.setAdapter(adapter);
                    binding.disspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            DataItem user = (DataItem) parent.getSelectedItem();
                            displayDistric(user);
                            districStr = user.getDistrictId();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<DistricResponse> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);

            }
        });


    }

    private void displayDistric(DataItem user) {
        binding.progressBar.setVisibility(View.VISIBLE);

        String chcid = user.getDistrictId();
        HcfRequest request = new HcfRequest();
        request.setDistrict_id(chcid);
        Call<HcfResponse> responceCall = ApiClient.getApiClient().fetch_mobile_hcf(request);
        responceCall.enqueue(new Callback<HcfResponse>() {
            @Override
            public void onResponse(Call<HcfResponse> call, Response<HcfResponse> response) {
                if (response.body().isStatus()) {
                    List<com.imf.haryanachi.networkModel.fetch_mobile_hcf.DataItem> items = response.body().getData();
                    binding.progressBar.setVisibility(View.GONE);

                    ArrayAdapter<com.imf.haryanachi.networkModel.fetch_mobile_hcf.DataItem> adapter = new ArrayAdapter<com.imf.haryanachi.networkModel.fetch_mobile_hcf.DataItem>
                            (RegiActivity.this, android.R.layout.simple_spinner_item, items);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    chcspinner.setAdapter(adapter);

                    chcspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            com.imf.haryanachi.networkModel.fetch_mobile_hcf.DataItem user = (com.imf.haryanachi.networkModel.fetch_mobile_hcf.DataItem) parent.getSelectedItem();
                            displayphcf(user);
                            Log.d("nameeee", user.getHcfname());
                            hfcStr = user.getChcId();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });


                }
            }

            @Override
            public void onFailure(Call<HcfResponse> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);

            }
        });
    }

    private void displayphcf(com.imf.haryanachi.networkModel.fetch_mobile_hcf.DataItem user) {
        binding.progressBar.setVisibility(View.VISIBLE);

        String hcf = user.getChcId();
        PhcRequest request = new PhcRequest();
        request.setChc_id(hcf);
        Call<PhcResponse> responceCall = ApiClient.getApiClient().fetch_mobile_phc(request);
        responceCall.enqueue(new Callback<PhcResponse>() {
            @Override
            public void onResponse(Call<PhcResponse> call, Response<PhcResponse> response) {
                if (response.body().isStatus()) {
                    binding.progressBar.setVisibility(View.GONE);

                    List<com.imf.haryanachi.networkModel.fetch_mobile_phc.DataItem> items = response.body().getData();
                    Log.d("logmeeee1", items.size() + "");
                    ArrayAdapter<com.imf.haryanachi.networkModel.fetch_mobile_phc.DataItem> adapter =
                            new ArrayAdapter<com.imf.haryanachi.networkModel.fetch_mobile_phc.DataItem>
                                    (RegiActivity.this, android.R.layout.simple_spinner_item, items);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    phcspinner.setAdapter(adapter);

                    phcspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            com.imf.haryanachi.networkModel.fetch_mobile_phc.DataItem user = (com.imf.haryanachi.networkModel.fetch_mobile_phc.DataItem) parent.getSelectedItem();
                            //displayphcf(user);
                            phcStr = user.getPhcId();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });


                }
            }

            @Override
            public void onFailure(Call<PhcResponse> call, Throwable t) {
                Log.d("logmeeee1", t.getMessage() + "");
                binding.progressBar.setVisibility(View.GONE);

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        SharedPreferences preferences = getSharedPreferences("session", MODE_PRIVATE);
        if (preferences.getString("session", "").equals("ok")) {
            startActivity(new Intent(this, EmployeeProfileActivity.class));
            finish();
        }
    }


}