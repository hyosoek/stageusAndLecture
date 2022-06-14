package com.example.stageus_android_lecuture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

//메인 액티비티에 들어가는 프래그먼트
class SignUpFragment:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.signup_fragment, container, false)
        initEvent(view)
        return view
    }

    fun initEvent(view : View){
        //회원가입 버튼 이벤트 ->  부모한테 보내주는 기능 fragment to activity 데이터 전송 과정
        val signUpButton = view.findViewById<Button>(R.id.signUpBtn1)
        signUpButton.setOnClickListener(){
            val dataInterface = context as DataFromFragment //이 부분 열심히 합니다.
            dataInterface.sendData("Save Data",
                arrayOf(view.findViewById<EditText>(R.id.id_text).text.toString(),
                    view.findViewById<EditText>(R.id.pw_text).text.toString())
            )
        }
    }
}