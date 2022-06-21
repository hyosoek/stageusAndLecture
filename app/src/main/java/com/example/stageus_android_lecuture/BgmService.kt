package com.example.stageus_android_lecuture

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import java.security.Provider

class BgmService: Service() {
    lateinit var media: MediaPlayer

    //기존 방식은 생성없이 intent로 만들고 시작하는 방식입니다.
    // Activity에서 Service를 생성할 때 값을 보내줬다면 이 함수로 옴 intent로 옵니다.Intent는 액티비티는 4대 컴포넌트들이 서로 통신할 때 씁니다.
    //서비스 생성부
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    //가장 처음 실행되는 함수
    override fun onCreate() {
        super.onCreate()
        media = MediaPlayer.create(this,R.raw.beep)
    }

    //서비스 실행 명령이 왔을 때 호출되는 함수
    // 액티비티에서 서비스를 만들 때 넣는 거랑, 실행할 때 전달하는게 다르다.
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        media.start()
        return super.onStartCommand(intent, flags, startId)
    }


    // Service 중지 명령어 왔을 때 호출되는 함수
    override fun onDestroy() {
        super.onDestroy()
        media.stop()
    }
}