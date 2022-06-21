package com.example.stageus_android_lecuture

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.Menu
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class ContentActivity : AppCompatActivity() {
    private lateinit var mService: LocalService
    private var mBound: Boolean = false

    /** Defines callbacks for service binding, passed to bindService()  */
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val binder = service as LocalService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        Log.d("result_message","아이디 :  / 비밀번호 : ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_layout)//레이아웃 지정 명령어
        val idValue = intent.getStringExtra("id_value")
        val pwValue = intent.getStringExtra("pw_value")
        Log.d("result_message","아이디 : ${idValue} / 비밀번호 : ${pwValue}")

        //val ide = argument랑 헷갈리지 말것 - 이건 프래그먼트


        val linearLayout = findViewById<LinearLayout>(R.id.parent_layout)//동적 뷰


        val dataList = arrayOf(arrayOf("최민석","01085827643"),arrayOf("최효석","01077042672"),arrayOf("김재걸","01088887777"))

        //custom view cnrk
        //custom view는 스타일 속성들을 xml 파일에서 해주도록 하는 장치
        //스타일 속성을 코틀린 코드 내에서 개발하는 것은 추천안 하고, 코드가 매우 복잡하고 통일성 없음
        // 코틀린 내에서는 xml 가져와서 data만 바꿔즌 ㄴ형태로 개발

        for (index in 0 until dataList.size){
            val customView = layoutInflater.inflate(R.layout.customview,linearLayout,false)
            customView.findViewById<TextView>(R.id.name_text).text = dataList[index][0]
            customView.findViewById<TextView>(R.id.second_text).text = dataList[index][1]
            linearLayout.addView(customView)
        }
        Intent(this, LocalService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        Log.d("result_message","아이디 : ${mBound}  / 비밀번호 : 1")
    }

    override fun onStart() {
        super.onStart() //부모의 것을 일단 실행하고 이어 붙이는 것
        Log.d("result_message","onstart가 실행됨")
    }

    override fun onResume() {
        super.onResume()
        Log.d("result_message","onResume가 실행됨")
    }

    override fun onPause() {
        super.onPause()
        Log.d("result_message","onPause가 실행됨")
    }

    override fun onStop() {
        super.onStop()
        Log.d("result_message","onStop가 실행됨")

        //messsagebox 같은거
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("일시정지")
        dialog.setMessage("앱이 일시 정지 상태에 들어갔습니다.")
        dialog.create()
        dialog.show()

        var intent = Intent(this, BgmService::class.java)
        stopService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("result_message","onDestroy가 실행됨")
    }


}