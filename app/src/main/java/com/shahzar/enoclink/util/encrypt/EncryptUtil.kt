package com.shahzar.enoclink.util.encrypt

import android.util.Base64
import com.shahzar.enoclink.util.Constants
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object EncryptUtil{
    @JvmStatic fun aesEncrypt(v:String, secretKey:String = Constants.ENCRYPTION_SECRET) = AES256.encrypt(v, secretKey)
    @JvmStatic fun aesDecrypt(v:String, secretKey:String = Constants.ENCRYPTION_SECRET) = AES256.decrypt(v, secretKey)
}

private object AES256{
    private fun cipher(opmode:Int, secretKey:String): Cipher {
        if(secretKey.length != 32) throw RuntimeException("SecretKey length is not 32 chars")
        val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val sk = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), "AES")
        val iv = IvParameterSpec(secretKey.substring(0, 16).toByteArray(Charsets.UTF_8))
        c.init(opmode, sk, iv)
        return c
    }
    fun encrypt(str:String, secretKey:String):String{
        val encrypted = cipher(Cipher.ENCRYPT_MODE, secretKey).doFinal(str.toByteArray(Charsets.UTF_8))
        return String(Base64.encode(encrypted, Base64.DEFAULT))
    }
    fun decrypt(str:String, secretKey:String):String{
        val byteStr = Base64.decode(str.toByteArray(Charsets.UTF_8), Base64.DEFAULT)
        return String(cipher(Cipher.DECRYPT_MODE, secretKey).doFinal(byteStr))
    }
}