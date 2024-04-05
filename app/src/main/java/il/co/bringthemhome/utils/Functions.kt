package il.co.bringthemhome.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun imgGlideCaster(context: Context, kidnappedImg: String, flag: ImageView) {
    Glide.with(context)
        .load(kidnappedImg)
        .into(flag)
}

fun showToast(context: Context, text: String) {
    val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
    toast.show()
}


