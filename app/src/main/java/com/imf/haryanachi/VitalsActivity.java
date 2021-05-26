package com.imf.haryanachi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.imf.haryanachi.databinding.ActivityVitalsBinding;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.vitals.VitalsRequest;
import com.imf.haryanachi.networkModel.vitals.VitalsResponse;
import com.imf.haryanachi.utils.GpsTracker;
import com.imf.haryanachi.utils.InputFilterMinMax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VitalsActivity extends AppCompatActivity {


    private static final long LOCATION_REFRESH_TIME = 0;
    private static final float LOCATION_REFRESH_DISTANCE = 0;
    ActivityVitalsBinding binding;
    private String dname, id, chc_id, phc_id, bed_number, paitentId, visitId, pname, centername, age, gender, wardname;
    LocationManager mLocationManager;
    Geocoder geocoder;
    List<Address> addresses;
    String address;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private GpsTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vitals);
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            checkAndRequestPermissions();
            return;
        } else {
          /*  mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                    LOCATION_REFRESH_DISTANCE, mLocationListener);
            binding.progressBar.setVisibility(View.VISIBLE);

*/ getLocation();
        }

        SharedPreferences sharedPref1 = getSharedPreferences("PGI", Context.MODE_PRIVATE);
        dname = sharedPref1.getString("name", "");
        id = sharedPref1.getString("id", "");
        chc_id = sharedPref1.getString("chc_id", "");
        phc_id = sharedPref1.getString("phc_id", "");
        paitentId = sharedPref1.getString("paitentId", "");
        visitId = sharedPref1.getString("visitId", "");
        pname = sharedPref1.getString("pname", "");
        bed_number = sharedPref1.getString("team_member", "");
        centername = sharedPref1.getString("centername", "");
        wardname = sharedPref1.getString("wardname", "");
        age = sharedPref1.getString("page", "");
        gender = sharedPref1.getString("gender", "");


        binding.pAge.setText(age);
        binding.pgender.setText(gender);
        binding.pname.setText(pname);

        binding.homeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VitalsActivity.this, EmployeeProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //    binding.tempeedt.setFilters(new InputFilter[]{new InputFilterMinMax("0", "110")});
        binding.pluseedt.setFilters(new InputFilter[]{new InputFilterMinMax("0", "300")});
        binding.spo2edt.setFilters(new InputFilter[]{new InputFilterMinMax("0", "100")});
        binding.sbpedt.setFilters(new InputFilter[]{new InputFilterMinMax("0", "300")});
        binding.dbpedt.setFilters(new InputFilter[]{new InputFilterMinMax("0", "200")});
        binding.repo.setFilters(new InputFilter[]{new InputFilterMinMax("0", "60")});

          binding.vitalsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.tempeedt.getText().toString().isEmpty()) {
                    binding.tempeedt.setError("Add Temp");
                } else if (binding.pluseedt.getText().toString().isEmpty()) {
                    binding.pluseedt.setError("Add pluse");
                } else if (binding.spo2edt.getText().toString().isEmpty()) {
                    binding.spo2edt.setError("Add spo2");
                } else if (binding.sbpedt.getText().toString().isEmpty()) {
                    binding.sbpedt.setError("Add Systolic Bp");
                } else if (binding.dbpedt.getText().toString().isEmpty()) {
                    binding.dbpedt.setError("Add DIASTOLIC BP");
                } else if (binding.repo.getText().toString().isEmpty()) {
                    binding.repo.setError("Add Resp. Rate");
                } else {

                    // visit_id,temperature,pulse,spo2,rate,systolic_bp,
                    // diastolic_bp,patients_id,chc_id,phc_id,bed_number;
                    binding.progressBar.setVisibility(View.VISIBLE);
                    VitalsRequest request = new VitalsRequest();
                    request.setVisit_id(visitId);
                    request.setTemperature(binding.tempeedt.getText().toString());
                    request.setPulse(binding.pluseedt.getText().toString());
                    request.setSpo2(binding.spo2edt.getText().toString());
                    request.setRate(binding.repo.getText().toString());
                    request.setSystolic_bp(binding.sbpedt.getText().toString());
                    request.setDiastolic_bp(binding.dbpedt.getText().toString());
                    request.setPatients_id(paitentId);
                    request.setChc_id(chc_id);
                    request.setPhc_id(phc_id);
                    request.setBed_number(bed_number);
                    request.setWardname(wardname);
                    request.setCentername(centername);
                    request.setLocation(address);
//2108745234
                    //
                    Log.d("printlogg", "bjgjgbhj    " + bed_number);
                    Log.d("printlogg", "bjgjgbhj    " + centername);
                    Log.d("printlogg", "bjgjgbhj    " + chc_id);
                    Log.d("printlogg", "bjgjgbhj    " + phc_id);
                    Log.d("printlogg", "bjgjgbhj    " + paitentId);
                    Log.d("printlogg", "bjgjgbhj    " + address);

                    //dname, id,chc_id,phc_id,bed_number;

                    sendData(request);


                }
            }
        });


    }
    public void getLocation(){
        binding.progressBar.setVisibility(View.VISIBLE);
        gpsTracker = new GpsTracker(VitalsActivity.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();

            geocoder = new Geocoder(VitalsActivity.this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(latitude,longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (IOException e) {
                e.printStackTrace();
            }
            address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            //Toast.makeText(VitalsActivity.this, ""+address, Toast.LENGTH_SHORT).show();

            Log.d("latlongggg", address + "   //   ");
            binding.progressBar.setVisibility(View.GONE);
            Log.d("lotaaaa",gpsTracker.getLatitude()+"     /////");
            Log.d("lotaaaa",gpsTracker.getLongitude()+"     /////");
            Log.d("lotaaaa",address+"     /////");
        }else{
            gpsTracker.showSettingsAlert();
        }
    }
    private boolean checkAndRequestPermissions() {
        int readlocation = ContextCompat.checkSelfPermission(VitalsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        List listPermissionsNeeded = new ArrayList<>();
        if (readlocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);

        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(VitalsActivity.this,
                    (String[]) listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);

            return false;
        }
        return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_ID_MULTIPLE_PERMISSIONS) {

        }

        if (grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
         /*   mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                    LOCATION_REFRESH_DISTANCE, mLocationListener);*/
            getLocation();
        } else {
            Toast.makeText(VitalsActivity.this, "Please allow to Access your location ", Toast.LENGTH_SHORT).show();
            checkAndRequestPermissions();
        }

    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {

            geocoder = new Geocoder(VitalsActivity.this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (IOException e) {
                e.printStackTrace();
            }
            address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            //Toast.makeText(VitalsActivity.this, ""+address, Toast.LENGTH_SHORT).show();

            Log.d("latlongggg", address + "   //   ");
            binding.progressBar.setVisibility(View.GONE);
        }
    };

    private void sendData(VitalsRequest request) {


        Call<VitalsResponse> responceCall = ApiClient.getApiClient().vitals(request);
        responceCall.enqueue(new Callback<VitalsResponse>() {
            @Override
            public void onResponse(Call<VitalsResponse> call, Response<VitalsResponse> response) {
                if (response.body().isStatus()) {
                    binding.progressBar.setVisibility(View.GONE);
                    SharedPreferences sharedPref1 = getSharedPreferences("show", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = sharedPref1.edit();
                    editor1.putString("pname", response.body().getData().getPname());
                    editor1.putString("p_age", response.body().getData().getPAge());
                    editor1.putString("pgender", response.body().getData().getPgender());
                    editor1.putString("patients_status", response.body().getData().getPatientsStatus());
                    editor1.putString("dayscount", response.body().getData().getDayscount());
                    editor1.putString("visitId", visitId);
                    editor1.putString("paitentId", paitentId);
                    editor1.apply();


                    binding.tempeedt.setText("");
                    binding.pluseedt.setText("");
                    binding.spo2edt.setText("");
                    binding.sbpedt.setText("");
                    binding.dbpedt.setText("");
                    binding.repo.setText("");
                    Intent intent = new Intent(VitalsActivity.this, ShowPatientActivty.class);
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<VitalsResponse> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });

    }
}