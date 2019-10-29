package com.example.shemsys

import com.github.nkzawa.socketio.client.IO
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun socketThingy () {
        val url = "https://shemsys.herokuapp.com/shemsys"
        val socket = IO.socket(url)
        socket.connect()
        assertEquals(true, socket.connected())

    }

}
