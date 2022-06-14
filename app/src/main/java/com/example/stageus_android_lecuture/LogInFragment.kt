package com.example.stageus_android_lecuture

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

//메인 액티비티에 들어가는 프래그먼트
class LogInFragment:Fragment() {
    var idValue =""
    var pwValue =""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        idValue = arguments?.getString("id_value").toString()
        pwValue = arguments?.getString("pw_value").toString()
        Log.d("result_message","받아온 정보 ${idValue}${pwValue}")

        val view = inflater.inflate(R.layout.login_fragment, container, false)
        initEvent(view)//1.레이아웃을 코틀린 파일로 만들고,

        return view
    }

    //2.아래로 보내줍니다.
    fun initEvent(myView: View) {
        val loginButton = myView.findViewById<Button>(R.id.login_btn)
        loginButton.setOnClickListener {
            val userId = myView.findViewById<EditText>(R.id.id_text).text.toString()
            var userPw = myView.findViewById<EditText>(R.id.pw_text).text.toString()
            Log.d("result_message","입력한 정보 ${userId}${userPw}")
                if ((userId == idValue)&&(userPw == pwValue)) {
                    val intent = Intent(context, ContentActivity::class.java)//매개변수, 첫번째는 통신주체(액티비티는 this,얘는 없으니 context는 자동으로 가져옴), 두번째는 어떤 액티비티 보낼 지
                    //다른 액티비티 레이아웃으로의 통신
                    intent.putExtra("id_value", myView.findViewById<EditText>(R.id.id_text).text.toString()) //에딧텍스트에 들어가는 값은 스트링이 아닙니다. 뮤터블 데이터로 되어있습니다. 그래서 변환해줘야합니다
                    intent.putExtra("pw_value", myView.findViewById<EditText>(R.id.pw_text).text.toString()) //받는 사람 기준 이름, 보내는 사람 기준으로의 이름으로 찾은 것
                    startActivity(intent)
                }
            }
        val signUpButton = myView.findViewById<Button>(R.id.signup_btn)
        signUpButton.setOnClickListener{
            val dataInterface = context as DataFromFragment
            dataInterface.sendData("Call Signup Fragment", arrayOf())
        }
    }

}

//    }
//    fun initEvent() {
//        val loginButton = view?.findViewById<Button>(R.id.loginBtn1)
//    }
        //로 해도 됩니다.

