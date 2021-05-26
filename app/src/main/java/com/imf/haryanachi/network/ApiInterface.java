package com.imf.haryanachi.network;

import com.imf.haryanachi.networkModel.addMember.AddMemberRequest;
import com.imf.haryanachi.networkModel.addMember.AddMemberResponse;
import com.imf.haryanachi.networkModel.all_center_details_api_statewise.CenterDetailResponse;
import com.imf.haryanachi.networkModel.all_state_count_api.AllStateResponse;
import com.imf.haryanachi.networkModel.dashActiveCount.DashActiveCountResponse;
import com.imf.haryanachi.networkModel.detailsPatient.DetailsRequest;
import com.imf.haryanachi.networkModel.detailsPatient.DetaislResponse;
import com.imf.haryanachi.networkModel.dischaerge.DischargeRequest;
import com.imf.haryanachi.networkModel.dischaerge.DischargeResponse;
import com.imf.haryanachi.networkModel.distric.DistricResponse;
import com.imf.haryanachi.networkModel.familyList.FamilyListResponse;
import com.imf.haryanachi.networkModel.fetch.ChcResponse;
import com.imf.haryanachi.networkModel.fetch.fetch_phc.PhpRequest;
import com.imf.haryanachi.networkModel.fetch.fetch_phc.PhpResponse;
import com.imf.haryanachi.networkModel.fetch_mobile_hcf.HcfRequest;
import com.imf.haryanachi.networkModel.fetch_mobile_hcf.HcfResponse;
import com.imf.haryanachi.networkModel.fetch_mobile_phc.PhcRequest;
import com.imf.haryanachi.networkModel.fetch_mobile_phc.PhcResponse;
import com.imf.haryanachi.networkModel.higherAuthoriytyLogin.HigherAuthorityResponse;
import com.imf.haryanachi.networkModel.history.HistoryRequest;
import com.imf.haryanachi.networkModel.history.HistoryResponse;
import com.imf.haryanachi.networkModel.hopiList.HopListResponse;
import com.imf.haryanachi.networkModel.hopiList.HopisListRequest;
import com.imf.haryanachi.networkModel.login.LoginRequest;
import com.imf.haryanachi.networkModel.login.LoginResponse;
import com.imf.haryanachi.networkModel.overall_state_count.OveralStateResponse;
import com.imf.haryanachi.networkModel.pReg.RegRequest;
import com.imf.haryanachi.networkModel.pReg.RegResponse;
import com.imf.haryanachi.networkModel.paitentReg.PaintentRequest;
import com.imf.haryanachi.networkModel.paitentReg.RegiResponse;
import com.imf.haryanachi.networkModel.patientLogin.PloginRequest;
import com.imf.haryanachi.networkModel.patientLogin.PloginResponse;
import com.imf.haryanachi.networkModel.phcDashCount.PhcDashCountRequest;
import com.imf.haryanachi.networkModel.phcDashCount.PhcDashCountResponse;
import com.imf.haryanachi.networkModel.positiveList.PositiveListResponse;
import com.imf.haryanachi.networkModel.search.SearchRequest;
import com.imf.haryanachi.networkModel.search.SearchResponse;
import com.imf.haryanachi.networkModel.selfRegi.SelfRegRequest;
import com.imf.haryanachi.networkModel.selfRegi.SelfRegResponse;
import com.imf.haryanachi.networkModel.status.StatusRequest;
import com.imf.haryanachi.networkModel.status.StatusResponse;
import com.imf.haryanachi.networkModel.todays_state_count.TodaysStateResponse;
import com.imf.haryanachi.networkModel.updatefinal.UpdateFinalRequest;
import com.imf.haryanachi.networkModel.updatefinal.UpdatefinalResponse;
import com.imf.haryanachi.networkModel.vitals.VitalsRequest;
import com.imf.haryanachi.networkModel.vitals.VitalsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {


    @POST(AppConstants.login)
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST(AppConstants.paitent_reg)
    Call<RegiResponse> regi(@Body PaintentRequest paintentRequest);

    @POST(AppConstants.paitent_status)
    Call<StatusResponse> status(@Body StatusRequest request);

    @POST(AppConstants.mobile_patients_register)
    Call<RegResponse> reg(@Body RegRequest request);


    @POST(AppConstants.add_vitals_form)
    Call<VitalsResponse> vitals(@Body VitalsRequest request);

    @POST(AppConstants.self_patients_registrations)
    Call<SelfRegResponse> selfReg(@Body SelfRegRequest request);

    @POST(AppConstants.fetch_chc)
    Call<ChcResponse> fetch_chc();

    @POST(AppConstants.fetch_phc)
    Call<PhpResponse> fetch_phc(@Body PhpRequest request);

    @POST(AppConstants.patients_login)
    Call<PloginResponse> patients_login(@Body PloginRequest request);

    @POST(AppConstants.view_patients_profile)
    Call<DetaislResponse> view_patients_profile(@Body DetailsRequest request);

    @POST(AppConstants.update_final_status)
    Call<UpdatefinalResponse> update_final_status(@Body UpdateFinalRequest request);

    @POST(AppConstants.search_mobile_api)
    Call<SearchResponse> search_mobile_api(@Body SearchRequest request);

    @POST(AppConstants.fetch_mobile_district)
    Call<DistricResponse> fetch_mobile_district();

    @POST(AppConstants.fetch_mobile_hcf)
    Call<HcfResponse> fetch_mobile_hcf(@Body HcfRequest request);

    @POST(AppConstants.fetch_mobile_phc)
    Call<PhcResponse> fetch_mobile_phc(@Body PhcRequest request);

    @POST(AppConstants.phc_dashboard_count)
    Call<PhcDashCountResponse> phc_dashboard_count(@Body PhcDashCountRequest request);

    @POST(AppConstants.dashboard_active_case_count)
    Call<DashActiveCountResponse> dashboard_active_case_count(@Body PhcDashCountRequest request);

    @POST(AppConstants.show_family_list)
    Call<FamilyListResponse> show_family_list(@Body PhcDashCountRequest request);

    @POST(AppConstants.mobile_positive_list)
    Call<PositiveListResponse> mobile_positive_list(@Body PhcDashCountRequest request);

    @POST(AppConstants.mobile_discharged_list)
    Call<DischargeResponse> mobile_discharged_list(@Body DischargeRequest request);

    @POST(AppConstants.mobile_patients_history)
    Call<HistoryResponse> mobile_patients_history(@Body HistoryRequest request);

    @POST(AppConstants.search_family_number_api)
    Call<AddMemberResponse> search_family_number_api(@Body AddMemberRequest request);

    @POST(AppConstants.hospital_list)
    Call<HopListResponse> hospital_list(@Body HopisListRequest request);

    @POST(AppConstants.todays_state_count)
    Call<TodaysStateResponse> todays_state_count();

    @POST(AppConstants.overall_state_count)
    Call<OveralStateResponse> overall_state_count();

    @POST(AppConstants.all_state_count_api)
    Call<AllStateResponse> all_state_count_api();

    @FormUrlEncoded
    @POST(AppConstants.all_center_details_api_statewise)
    Call<CenterDetailResponse> all_center_details_api_statewise(@Field("district_id") String district_id);

    @FormUrlEncoded
    @POST(AppConstants.others_login)
    Call<HigherAuthorityResponse> others_login(@Field("username") String username,@Field("password") String password);

}
