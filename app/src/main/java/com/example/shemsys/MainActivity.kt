package com.example.shemsys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.*
import java.lang.ref.Reference
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        // Connect to the DB
        private val database = FirebaseDatabase.getInstance()
        val refMotion = database.getReference("motion")
        val refPower = database.getReference("power_usage")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        detector(refMotion, powerDetectorValueListener())
//        detector(refPower, motionDetectorValueListener())



    }



    private fun detector(ref: DatabaseReference, detectorListener: ValueEventListener ) {
        ref.addValueEventListener(detectorListener)
    }

    private fun powerDetectorValueListener(): ValueEventListener {
        return object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                 Log.i("Main:onCancelled", "$p0")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(String::class.java)
                Log.i("Main:onDataChange", "$value")
            }

        }
    }


    private fun motionDetectorValueListener(): ValueEventListener {
        return object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.i("Main:onCancelled", "$p0")

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(String::class.java)
                Log.i("Main:onDataChange", "$value")

            }

        }
    }

}
