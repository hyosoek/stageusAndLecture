package com.example.stageus_android_lecuture

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*

object RetrofitClient { //class로 만들어도 됨
    //기본설정
    var instance : Retrofit? = null
    fun initRetrofit():Retrofit {
        if (instance == null) {
            instance = Retrofit
                .Builder()
                .baseUrl("http://3.39.66.6:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            //IP나 도메인 입력하고, 뒤에는 json 통신을 하겠다고 명시
        }
        return instance!!
    }
}

data class AccountLoginData(
    var message: String,
    var success: Boolean
)
data class AccountData(
    var message: String,
    var success: Boolean
)
interface RetrofitService{//모든 파일에서 전역변수임
    @GET("/account/login") fun getAccountLogin(
        @Query("id") id: String,
        @Query("pw") pw: String
    ) : Call<AccountLoginData>//안에 데이터 클래스

    @POST("/account")
    fun postAccount(//하나밖에 못 보냄
        @Body body: HashMap<String, String>//key value로 이루어진 자료형, hashmap의 발전형이 json
    ):Call<AccountData>
    //밑에 추가해주면 됨

}