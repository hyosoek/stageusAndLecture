package com.example.stageus_android_lecuture

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlin.math.log

class LogInFragment:Fragment() {
    lateinit var preference: SharedPreferences


    var idValue =""
    var pwValue =""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        //초기지정
        preference = requireContext().getSharedPreferences("Login_Data",MODE_PRIVATE)

        //내부의 특정값 가져오기
        idValue = preference.getString("id", "")!!
        pwValue = preference.getString("pw", "")!!
        //Login 시도
        Log.d("result_message","아이디 : ${idValue} / 비밀번호 : ${pwValue}")
        if ((idValue == "stageus")&&(pwValue == "1234")) {
            val intent = Intent(context, ContentActivity::class.java)
            intent.putExtra("id_value", idValue)
            intent.putExtra("pw_value", pwValue)
            startActivity(intent)
        }
        idValue = arguments?.getString("id_value").toString()
        pwValue = arguments?.getString("pw_value").toString()


        val view = inflater.inflate(R.layout.login_fragment, container, false)
        initEvent(view)
        return view
    }

    fun initEvent(myView: View) {
        val loginButton = myView.findViewById<Button>(R.id.login_btn)
        loginButton.setOnClickListener {
            idValue = view?.findViewById<EditText>(R.id.id_text)!!.text.toString()
            pwValue = view?.findViewById<EditText>(R.id.pw_text)!!.text.toString()

            if ((idValue == "stageus")&&(pwValue == "1234")) {
                //preference에 값 저장
                preference.edit().putString("id", "stageus").apply()
                preference.edit().putString("pw", "1234").apply()

                val intent = Intent(context, ContentActivity::class.java)
                intent.putExtra("id_value", idValue)
                intent.putExtra("pw_value", pwValue)
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

