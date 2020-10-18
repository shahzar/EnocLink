package com.shahzar.enoclink.util.hash

import org.junit.Assert.*
import org.junit.Test

class HashUtilsTest {

    @Test
    fun md5_validHashGenerated() {
        val hash = HashUtils.md5("MyEmailAddress@example.com".toLowerCase())
        assertEquals("0bc83cb571cd1c50ba6f3e8a78ef1346", hash)
    }


}