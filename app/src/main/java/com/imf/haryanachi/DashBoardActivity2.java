package com.imf.haryanachi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;

import com.imf.haryanachi.adapter.Padapter;
import com.imf.haryanachi.adapter.SearchAdapter;
import com.imf.haryanachi.databinding.ActivityDashBoard2Binding;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.addMember.AddMemberRequest;
import com.imf.haryanachi.networkModel.addMember.AddMemberResponse;
import com.imf.haryanachi.networkModel.paitentReg.PaintentRequest;
import com.imf.haryanachi.networkModel.paitentReg.RegiResponse;

import com.google.zxing.Result;
import com.imf.haryanachi.networkModel.search.DataItem;
import com.imf.haryanachi.networkModel.search.SearchRequest;
import com.imf.haryanachi.networkModel.search.SearchResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardActivity2 extends AppCompatActivity {
    private CodeScanner mCodeScanner;
    private CodeScannerView scannerView;
    private TextView textView, nametxt;
    private String dname, id, visitId, chc_id, phc_id, bed_number;
    String currentDateTimeString;
    public static final String TAG = "DashBoard2";
    private Button dashButton, statusButton, regButton;
    EditText simpleSearchView, searchFamilyview;

    RecyclerView recyclerView;
    List<DataItem> items1;
    ActivityDashBoard2Binding binding;
    LinearLayout linearLayout1, linearLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board2);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dash_board2);
        simpleSearchView = findViewById(R.id.searchview);
        searchFamilyview =  findViewById(R.id.searchview2);
        linearLayout1 = findViewById(R.id.linaer1);
        linearLayout2 = findViewById(R.id.linaer2);
        recyclerView = findViewById(R.id.recycerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        SharedPreferences sharedPref2 = getSharedPreferences("update", Context.MODE_PRIVATE);
        if (sharedPref2.getString("btn", "").equals("ok")) {
            binding.linar1.setVisibility(View.GONE);
            binding.scannerView2.setVisibility(View.VISIBLE);
            binding.patient.setVisibility(View.VISIBLE);
        } else {
            binding.linar1.setVisibility(View.VISIBLE);
            binding.scannerView2.setVisibility(View.GONE);
            binding.patient.setVisibility(View.GONE);
        }



      /*  searchFamilyview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                if (s.length()<10||s.length()>10) {
                    Toast.makeText(DashBoardActivity2.this, "Please enter 10 digit Mobile number", Toast.LENGTH_SHORT).show();
                    linearLayout1.setVisibility(View.VISIBLE);

                } else getFamilySearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length()<10||s.length()>10){
                    Toast.makeText(DashBoardActivity2.this, "Mobile number should be 10 digits", Toast.LENGTH_SHORT).show();
                }
                linearLayout1.setVisibility(View.GONE);

                return false;
            }
        });*/
        searchFamilyview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (searchFamilyview.getText().toString().length()<10){
                    searchFamilyview.setError("Mobile no minimum 10 digits");
                }else  getFamilySearch(searchFamilyview.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {
                linearLayout1.setVisibility(View.GONE);
            }
        });

    /*    simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.length()<10||s.length()>10) {
                    linearLayout2.setVisibility(View.VISIBLE);

                } else getSearch(s);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length()<10||s.length()>10){
                }
                linearLayout2.setVisibility(View.GONE);
                return false;
            }
        });*/
        simpleSearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (simpleSearchView.getText().toString().length()<10){
                    simpleSearchView.setError("Registartion no minimum 10 digits");
                }else getSearch(simpleSearchView.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {
                linearLayout2.setVisibility(View.GONE);
            }
        });


        Intent intent = getIntent();

        try {
          /*  if (intent == null) {

            } else {
                dname = intent.getStringExtra("name");
                id = intent.getStringExtra("id");
                chc_id = intent.getStringExtra("chc_id");
                phc_id = intent.getStringExtra("phc_id");
                bed_number = intent.getStringExtra("bed_number");

            }*/
            SharedPreferences sharedPref1 = getSharedPreferences("PGI", Context.MODE_PRIVATE);
            dname = sharedPref1.getString("name", "");
            id = sharedPref1.getString("id", "");
            chc_id = sharedPref1.getString("chc_id", "");
            phc_id = sharedPref1.getString("phc_id", "");
            bed_number = sharedPref1.getString("bed_number", "");

            SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);

            if (sharedPref.getString("session", "").equals("ok")) {
                dname = sharedPref.getString("name", "");
                id = sharedPref.getString("id", "");//doctorId
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        binding.homeiamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardActivity2.this, EmployeeProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        dashButton = findViewById(R.id.dash);
        statusButton = findViewById(R.id.update);
        regButton = findViewById(R.id.reg);

        dashButton.setOnClickListener((View v) -> {

        });

        regButton.setOnClickListener((View v) -> {
            Intent intent1 = new Intent(DashBoardActivity2.this, RegiActivity.class);
            intent.putExtra("name", dname);
            intent.putExtra("doctorId", id);//doctorId
            startActivity(intent1);

        });
        nametxt = findViewById(R.id.name);
        nametxt.setText(dname);
        scannerView = findViewById(R.id.scanner_view2);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.startPreview();
        try {

            mCodeScanner.setDecodeCallback(new DecodeCallback() {
                @Override
                public void onDecoded(@NonNull final Result result) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //
                                Log.d("registration", result.getText());
                                String[] temp;
                                String strMain = result.getText();
                                Log.d("myDataaaa", strMain);
                                String delimiter = ",";
                                temp = strMain.split(delimiter);
                                String cr_no = temp[0];
                                String mobile = temp[1];
                                registration(cr_no, mobile);

                            } catch (Exception e) {

                            }

                        }
                    });
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getFamilySearch(String s) {
        AddMemberRequest request = new AddMemberRequest();
        request.setText(s);
        Call<AddMemberResponse> responceCall = ApiClient.getApiClient().search_family_number_api(request);
        responceCall.enqueue(new Callback<AddMemberResponse>() {
            @Override
            public void onResponse(Call<AddMemberResponse> call, Response<AddMemberResponse> response) {
                if (response.body().getStatus().equals("true")) {

                    List<com.imf.haryanachi.networkModel.addMember.DataItem> items = response.body().getData();
                    Padapter padapter = new Padapter(DashBoardActivity2.this, items);
                    recyclerView.setAdapter(padapter);
                }

            }

            @Override
            public void onFailure(Call<AddMemberResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, EmployeeProfileActivity.class));
        finish();
    }

    //search_family_number_api
    private void getSearch(String s) {
        SearchRequest request = new SearchRequest();
        request.setText(s);
        Call<SearchResponse> responceCall = ApiClient.getApiClient().search_mobile_api(request);
        responceCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.body().getStatus().equals("true")) {
                    items1 = response.body().getData();
                    SearchAdapter adapter = new SearchAdapter(items1, DashBoardActivity2.this);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });


    }


    private void registration(String crNo, String mobile) {
        try {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

            try {
                currentDateTimeString = sdf.format(d);
                Log.d(TAG, currentDateTimeString);

            } catch (Exception e) {
            }

            PaintentRequest request = new PaintentRequest();
            request.setCr_no(crNo);
            request.setHoldermobile(mobile);
            request.setVisit_time(currentDateTimeString + "");
            request.setDoctor_id(id);
            request.setChc_id(chc_id);
            request.setPhc_id(phc_id);
            Log.d(TAG, id + " //" + currentDateTimeString + "// " + crNo);


            Call<RegiResponse> responceCall = ApiClient.getApiClient().regi(request);
            responceCall.enqueue(new Callback<RegiResponse>() {
                @Override
                public void onResponse(Call<RegiResponse> call, Response<RegiResponse> response) {
                    Log.d(TAG, response.body().getMessage());


                    visitId = response.body().getData().getVisitId();
                    Intent intent = new Intent(DashBoardActivity2.this, StatusActivity.class);
                    intent.putExtra("name", dname);
                    intent.putExtra("doctorId", id);//doctorId
                    intent.putExtra("visitId", response.body().getData().getVisitId());
                    intent.putExtra("paitentId", response.body().getData().getPatientsId());
                    intent.putExtra("pname", response.body().getData().getPname());
                    intent.putExtra("chc_id", chc_id);//doctorId
                    intent.putExtra("phc_id", phc_id);//doctorId
                    intent.putExtra("bed_number", bed_number);//doctorId
                    startActivity(intent);

                    Log.d("printlogg", "bjgjgbhj    " + response.body().getData().getPgender());

                    SharedPreferences sharedPref1 = getSharedPreferences("PGI", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref1.edit();
                    editor.putString("visitId", response.body().getData().getVisitId());
                    editor.putString("paitentId", response.body().getData().getPatientsId());
                    editor.putString("pname", response.body().getData().getPname());
                    editor.putString("page", response.body().getData().getPgae());
                    editor.putString("gender", response.body().getData().getPgender());
                    editor.apply();

                    mCodeScanner.stopPreview();
                    mCodeScanner.releaseResources();

                }

                @Override
                public void onFailure(Call<RegiResponse> call, Throwable t) {
                    Log.d(TAG, call.toString() + "  jghhjgbhjghjgbhjkgu");
                    mCodeScanner.stopPreview();
                    mCodeScanner.startPreview();
                    Toast.makeText(DashBoardActivity2.this, "Invalid Data", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {

        }

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
                SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("session", "cancel");
                editor.putString("name", "");
                editor.putString("id", "");
                editor.apply();
                Intent intent = new Intent(this, AllLognActivity.class);
                startActivity(intent);
                finish();
                return true;

        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}