package il.co.bringthemhome.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

fun imgGlideCaster(context: Context, kidnappedImg: String, flag: ImageView) {
    Glide.with(context)
        .load(kidnappedImg)
        .override(200, 200)
        .into(flag)
}

fun showToast(context: Context, text: String) {
    val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
    toast.show()
}


fun showAlertDialog(context: Context, title:String, message: String, button: String) {
    val alertDialog = AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setNeutralButton(button, DialogInterface.OnClickListener { dialog, _ ->
            dialog.cancel()
        })
    alertDialog.show()
}

fun isEmpty(textview: TextView) : Boolean {
    return textview.text.toString() == ""
}

