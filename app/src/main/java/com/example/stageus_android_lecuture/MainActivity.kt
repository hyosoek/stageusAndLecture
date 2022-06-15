package com.example.stageus_android_lecuture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
//import kotlin.String as String1

//액티비티 옆에 붙은게 상속임. 아래는 activity의 형식

interface DataFromFragment{
    fun sendData(requestType : String, requestData :Array<Any>)
}

class MainActivity : AppCompatActivity(), DataFromFragment {

    var idValue = ""
    var pwValue = ""

    override fun sendData(requestType :String, requestData :Array<Any>) {
        if(requestType == "Save Data") {
            idValue = requestData[0].toString()
            pwValue = requestData[1].toString()
        }
        else if(requestType == "Call Signup Fragment"){
            val fragmentTemp2 = SignUpFragment()//파일명을 가져와야함.
            supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp2).commit()//가져온 프래그먼트를 붙여줍니다. 첫번째는 위치, 두번째는 물건
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)//여기서 사용할 레이아웃을 확정해줬기 때문에, 레아아웃간의 뷰 이름은 겹쳐도 됩니다.

//        val subLogo =findViewById<TextView>(R.id.sublogo)
//        subLogo.text = "바뀐값"

        //fragment 가져오기
        val fragmentTemp = LogInFragment()//파일명을 가져와야함.
        supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp).commit()//가져온 프래그먼트를 붙여줍니다. 첫번째는 위치, 두번째는 물건

        initEvent()






        //서비스 실행*********************
        var intent = Intent(this, BgmService::class.java)
        startService(intent)
        //서비스 실행*********************



    }
//레이아웃의 뷰를 가져오는 방법

    fun initEvent(){
        //로그인 누르면 이벤트 처리
        val logInButton = findViewById<Button>(R.id.loginBtn1)
        logInButton.setOnClickListener {
            val fragment = LogInFragment()
            val bundle = Bundle()
            bundle.putString("id_value",idValue)
            bundle.putString("pw_value",pwValue)
            fragment.arguments = bundle //이 프래그먼트의 아규먼트로 보내는 겁니다.

            val fragmentTemp1 = fragment//파일명을 가져와야함.
            supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp1).commit()//가져온 프래그먼트를 붙여줍니다. 첫번째는 위치, 두번째는 물건
        }
//
        val signUpButton = findViewById<Button>(R.id.signUpBtn1)
        signUpButton.setOnClickListener {
            val fragmentTemp2 = SignUpFragment()//파일명을 가져와야함.
            supportFragmentManager.beginTransaction().replace(R.id.fragmentBox, fragmentTemp2).commit()//가져온 프래그먼트를 붙여줍니다. 첫번째는 위치, 두번째는 물건
        }
    }

//    override fun onStop() {
//        super.onStop()
//        var intent = Intent(this, BgmService::class.java)
//        stopService(intent)
//    }



}