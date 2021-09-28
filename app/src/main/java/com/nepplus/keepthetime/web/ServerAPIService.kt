package com.nepplus.keepthetime.web

import com.nepplus.keepthetime.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.*

interface ServerAPIService {

    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp(
        @Field("email") email: String,
        @Field("password") pw: String,
        @Field("nick_name") nickname: String) : Call<BasicResponse>

    @FormUrlEncoded
    @POST("/user")
    fun postRequestLogin(
        @Field("email") email : String,
        @Field("password") pw : String) : Call<BasicResponse>

    @FormUrlEncoded
    @POST("/user/social")
    fun postRequestSocialLogin(
        @Field("provider") provider: String,
        @Field("uid") id: String,
        @Field("nick_name") name: String  ) : Call<BasicResponse>

    @FormUrlEncoded
    @POST("/appointment")
    fun postRequestAppointment(
        @Field("title")  title: String,
        @Field("datetime") datetime:String,
        @Field("place") placeName: String,
        @Field("latitude") lat: Double,
        @Field("longitude") lng: Double,


    ) : Call<BasicResponse>

    //    GET - 약속 목록 가져오기

    @GET("/appointment")
    fun getRequestAppointmentList() : Call<BasicResponse>

    @GET("/user")
    fun getRequestMyInfo() : Call<BasicResponse>

    //    POST - PUT - PATCH : FormData 활용
    //    회원정보 수정 API
    @FormUrlEncoded
    @PATCH("/user")
    fun patchRequestMyInfo(
        @Field("field") field:String,
        @Field("value") value:String) : Call<BasicResponse>

    @FormUrlEncoded
    @POST("/user/place")
    fun postRequestAddMyPlace(
        @Field("name") name: String,
        @Field("latitude") lat: Double,
        @Field("longitude") lng: Double,
        @Field("is_primary") isPrimary: Boolean) : Call<BasicResponse>



}