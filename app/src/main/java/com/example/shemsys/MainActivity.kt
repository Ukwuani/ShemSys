package com.example.shemsys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    companion object {
        // Connect to the DB

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val url = "https://shemsys.herokuapp.com/shemsys"
         val socket = IO.socket(url)
         val motionState = "SHEMSYS_MOTION_STATE"
         val powerState = "SHEMSYS_POWER"
        socket.connect()

        socket.on("connect", Emitter.Listener {
            connectionStatus.isChecked
        })
        socket.connect()
        socket.on(powerState, powerStateHandler())
        socket.on(motionState, motionStateHandler())

    }

    private fun powerStateHandler(): Emitter.Listener {
        return Emitter.Listener {
            this@MainActivity.runOnUiThread {
                powerRate.text = (it[0] as JSONObject).get("value").toString()
            }
        }
    }

    private fun motionStateHandler(): Emitter.Listener {
        return Emitter.Listener {
            this@MainActivity.runOnUiThread {
                when ((it[0] as JSONObject).getBoolean("state")) {
                    true ->  imageView.setImageDrawable(resources.getDrawable(R.drawable.dome_camera_green))
                    false -> imageView.setImageDrawable(resources.getDrawable(R.drawable.dome_camera))
                }
            }
        }
    }



    public override fun onDestroy() {
        super.onDestroy()

//        socket.disconnect()
//        socket.off("new message", onNewMessage)
    }

}
