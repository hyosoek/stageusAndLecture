package com.example.stageus_android_lecuture

import android.accounts.Account
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.function.LongFunction

//메인 액티비티에 들어가는 프래그먼트
class SignUpFragment:Fragment() {
    lateinit var retrofit: Retrofit //connection
    lateinit var retrofitHttp: RetrofitService //cursor 역할
    fun initRetrofit(){
        retrofit = RetrofitClient.initRetrofit() //class면 client 뒤에 ()
        retrofitHttp =  retrofit!!.create(RetrofitService::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.signup_fragment, container, false)
        //initData(view
        initRetrofit()
        initEvent(view)
        return view
    }
    fun initData(view:View){
        Glide.with(view)
            .load(R.drawable.test_image)
            //.placeholder(R.drawable.red)//대기 이미지
            //.thumbnail(0.1f)//같은 이미지 작게
            //.override(300,200)//해상도가 적고 원본 크기를 바꿔줌
            .circleCrop()
           .into(view.findViewById(R.id.signup_image))
        //여기서 실행하면 수행 못함.
        val gson = GsonBuilder()
         //   .setPrettyPrinting()//그저 옵션
            .create()//초기화입니다 GLIDE처럼 옵션을 넣어줄 수 있음.
        val gson2 = Gson()//위와 동일한 초기화 하지만, 추가 옵션 불가능



        //data class는 구조체입니다.
        //일반 클래스에서 함수 없이 매개변수만 따온 형태 - 최적화 된 것
        data class Account( //코틀린의 자체 용법이고 gson이 아님
            var id: String,
            var pw: String
            )
        //클래스를 객체로 만든 형태
        val myAccount = Account("스테이지어스","1234")
        //여기부터 gson
        val myAccountData = gson2.toJson(myAccount)
        //이렇게 하면 myAccountData는 json의 형태를 가짐
        Log.d("data",myAccountData)
//여기까진 gson을 바탕으로 json 생성



        //        var data = {
//            "id":"stageus",
//            "pw":"1234"
//        } //위를  아래에 표현

//        val myAccountData2 = gson.fromJson<Account>(data,Account::class.java) //이건 가져온 json을 보기 쉽게 바꿔주는거
    }

    fun initEvent(view : View){
        //회원가입 버튼 이벤트 ->  부모한테 보내주는 기능 fragment to activity 데이터 전송 과정
        val signUpButton = view.findViewById<Button>(R.id.signUpBtn1)
//        signUpButton.setOnClickListener(){
//            val dataInterface = context as DataFromFragment //이 부분 열심히 합니다.
//            dataInterface.sendData("Save Data",
//                arrayOf(view.findViewById<EditText>(R.id.id_text).text.toString(),
//                    view.findViewById<EditText>(R.id.pw_text).text.toString())
//            )
        signUpButton.setOnClickListener(){
            var requestData: HashMap<String,String> = HashMap() //자료형 고정
            requestData["id"] = view.findViewById<EditText>(R.id.id_text).text.toString()
            requestData["pw"] = view.findViewById<EditText>(R.id.pw_text).text.toString()
            requestData["name"] = "stageus"
            requestData["contact"] = "01099119598"

            retrofitHttp.postAccount(requestData)//제이슨으로 받아오기에 담을 객체 바로 생성 가능
                .enqueue(object: Callback<AccountData> {//enqueue가 비동기함수 + 비동기처리 됨 자동으로
                override fun onFailure(call: Call<AccountData>, t: Throwable) {
                    Log.d("result","Request Fail ${t}")//실패사유
                }override fun onResponse(call: Call<AccountData>, response: Response<AccountData>) {
                    if(response.body()!!.success) {
                        Log.d("result","Request Success")//실패사유
                    }
                    else{
                        Log.d("result","Request Fail : ${response.body()!!.success}")//실패사유
                    }
                }
                })
        }
    }

}