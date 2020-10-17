package com.shahzar.enoclink.util

import android.graphics.Bitmap

import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream


object ImageUtils {

    fun toBase64(imagePath: String?): String? {

        if (imagePath == null) {
            return null
        }

        val bm = BitmapFactory.decodeFile(imagePath)
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos) // bm is the bitmap object

        val byteArrayImage: ByteArray = baos.toByteArray()

        return Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
    }

}