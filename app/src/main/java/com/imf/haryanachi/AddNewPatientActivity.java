package com.imf.haryanachi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.fetch.ComoList;
import com.imf.haryanachi.networkModel.pReg.RegRequest;
import com.imf.haryanachi.networkModel.pReg.RegResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewPatientActivity extends AppCompatActivity implements View.OnClickListener {
    CheckBox chk1, chk2, chk3, chk4, chk5, chk6, chk7, chk8, chk9;
    List<ComoList> comoList;
    CalendarView calendarView;
    EditText pnameEditText, lnameEditText, mnoEditText, ageEditText, paddressEditText, icmrEditText;
    TextView duartionTextView;
    Button subButton, cancelButton;
    RadioButton maleradioButton, femaleradioButton;
    RadioGroup radioGroup;
    String genderstr;
    boolean isGender;
    SharedPreferences sharedPref1;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener dateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_patient);

        pnameEditText = findViewById(R.id.pname);
        lnameEditText = findViewById(R.id.lname);
        mnoEditText = findViewById(R.id.pmno);
        ageEditText = findViewById(R.id.age);
        duartionTextView = findViewById(R.id.duration);
        icmrEditText = findViewById(R.id.icmrid);
        paddressEditText = findViewById(R.id.paddress);

        comoList = new ArrayList<>();
        subButton = findViewById(R.id.psub);
        cancelButton = findViewById(R.id.cancel);
        maleradioButton = findViewById(R.id.male);
        femaleradioButton = findViewById(R.id.female);
        radioGroup = (RadioGroup) findViewById(R.id.yourRadioGroup);
        // comospinner = findViewById(R.id.comospinner);
        //  como();
        chk1 = (CheckBox) findViewById(R.id.checkBox);
        chk2 = (CheckBox) findViewById(R.id.checkBox2);
        chk3 = (CheckBox) findViewById(R.id.checkBox3);
        chk4 = (CheckBox) findViewById(R.id.checkBox4);
        chk5 = (CheckBox) findViewById(R.id.checkBox5);
        chk6 = (CheckBox) findViewById(R.id.checkBox6);
        chk7 = (CheckBox) findViewById(R.id.checkBox7);
        chk8 = (CheckBox) findViewById(R.id.checkBox8);

        chk1.setOnClickListener(this);
        chk2.setOnClickListener(this);
        chk3.setOnClickListener(this);
        chk4.setOnClickListener(this);
        chk5.setOnClickListener(this);
        chk6.setOnClickListener(this);
        chk7.setOnClickListener(this);
        chk8.setOnClickListener(this);
        myCalendar = Calendar.getInstance();
        dateListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                duartionTextView.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
            }

        };

        duartionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getCalender();
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewPatientActivity.this, dateListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                Log.d("datee", datePickerDialog.getDatePicker().getYear() + "-" + datePickerDialog.getDatePicker().getMonth() + "-" + datePickerDialog.getDatePicker().getDayOfMonth());
                //following line to restrict future date selection

                String date = datePickerDialog.getDatePicker().getYear() + "-" + datePickerDialog.getDatePicker().getMonth() + "-" + datePickerDialog.getDatePicker().getDayOfMonth();

                // duartionTextView.setText(date);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();



            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                  @Override
                                                  public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                      RadioButton radioButton = (RadioButton) findViewById(checkedId);
                                                      Toast.makeText(getBaseContext(), radioButton.getText(), Toast.LENGTH_SHORT).show();
                                                      genderstr = radioButton.getText().toString();
                                                      isGender = true;
                                                  }
                                              }
        );
        sharedPref1 = getSharedPreferences("addnewP", Context.MODE_PRIVATE);

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
            }  else if (icmrEditText.getText().toString().isEmpty()) {
                icmrEditText.setError("enter correct ICMR id");
            } else if (duartionTextView.getText().toString().isEmpty() || (duartionTextView.getText().toString().equals("YYYY-MM-DD"))) {
                duartionTextView.setError("please select date from calender");
            }else if (!isGender) {
                Toast toast = Toast.makeText(AddNewPatientActivity.this, "Please select your gender", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                isGender = false;
            } else if (comoList.isEmpty()) {
                Toast toast = Toast.makeText(AddNewPatientActivity.this, "please select Comorbidities", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            } else {
                String positiveStr = sharedPref1.getString("positivemember", "");
                RegRequest regRequest = new RegRequest();

                regRequest.setFamilyholder(sharedPref1.getString("familyholder", ""));
                regRequest.setFamilymember(sharedPref1.getString("familymember", ""));
                regRequest.setHoldermobile(sharedPref1.getString("holdermobile", ""));
                regRequest.setPositivemember(Integer.parseInt(positiveStr) + 1 + "");
                regRequest.setFamilyaddress(sharedPref1.getString("familyaddress", ""));
                regRequest.setFamilycity(sharedPref1.getString("familycity", ""));
                regRequest.setNo_como(comoList);
                regRequest.setFamilystate(sharedPref1.getString("familystate", ""));
                regRequest.setPname(pnameEditText.getText().toString());
                regRequest.setLname(lnameEditText.getText().toString());
                regRequest.setP_age(ageEditText.getText().toString());
                regRequest.setPgender(genderstr);
                regRequest.setPnumber(mnoEditText.getText().toString());
                regRequest.setComorbidities(sharedPref1.getString("comorbidities", ""));
                regRequest.setPassword(sharedPref1.getString("password", ""));
                regRequest.setDistric_id(sharedPref1.getString("district_id", ""));
                regRequest.setChc_id(sharedPref1.getString("chc_id", ""));//phcStr
                regRequest.setPhc_id(sharedPref1.getString("phc_id", ""));
                regRequest.setDate(duartionTextView.getText().toString());
                regRequest.setPaddress(paddressEditText.getText().toString());
                regRequest.setIcmr_id(icmrEditText.getText().toString());
                sendvalue(regRequest);
                //Toast.makeText(RegiActivity.this, "" + binding.state.getText().toString(), Toast.LENGTH_SHORT).show();
            }
   /*    "familyholder": "PARIHAR ",
            "familymember": "6",
            "positivemember": "3",
            "comorbidities": "2",
            "familyaddress": "NAGPUR ",
            "familycity": "NAGPUR ",
            "familystate": "",
            "holdermobile": "9422591515",
            "password": "",
            "district_id": "5f795f2caa36e77b8ab6e3fe",
            "chc_id": "5f7960f0aa36e77b8ab6e412",
            "phc_id": "5f796e50aa36e77b8ab6e4ac"*/
        });


    }

    private void sendvalue(RegRequest regRequest) {
        Call<RegResponse> responceCall = ApiClient.getApiClient().reg(regRequest);

        responceCall.enqueue(new Callback<RegResponse>() {
            @Override
            public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {
                if (response.body().getMessage().equals("Patient inserted")) {

                    new AlertDialog.Builder(AddNewPatientActivity.this)
                            .setTitle("REGISTRATION SUCCESSFUL")
                            .setMessage("Your Registration No. is : "+response.body().getData().getRegno())
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(AddNewPatientActivity.this, EmployeeProfileActivity.class));
                                    finish();
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();


                }
            }

            @Override
            public void onFailure(Call<RegResponse> call, Throwable t) {
                Toast.makeText(AddNewPatientActivity.this, "failed" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("failed", "failed" + t.getMessage());


            }
        });


    }

    private void getCalender() {
        Dialog dialog = new Dialog(this);
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
                String Date = dayOfMonth + "-" + (month + 1) + "-" + year;
                //  dateView.setText(Date);
                duartionTextView.setText(Date);
                Log.d("dateeeee", Date);
                dialog.dismiss();
            }
        });
    }

    /*public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkBox:
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

        }
    }*/

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkBox:
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
                break;
        }
    }
}