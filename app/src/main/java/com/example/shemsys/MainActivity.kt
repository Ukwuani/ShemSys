package com.example.shemsys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.nkzawa.socketio.client.IO

class MainActivity : AppCompatActivity() {

    companion object {
        // Connect to the DB
        private val url = "https://shemsys.herokuapp.com"
        private val socket = IO.socket(url)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val status = socket.connect()
        Log.i("MainActivity:SocketStat", "${socket.connected()}")



    }

    public override fun onDestroy() {
        super.onDestroy()

        socket.disconnect()
//        socket.off("new message", onNewMessage)
    }

}
