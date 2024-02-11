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

fun setStatusColor(textview: TextView) {
    when (textview.text.toString()){
        "המריאה" -> textview.setTextColor(Color.parseColor("#2A7A2D"))
        "DEPARTED" -> textview.setTextColor(Color.parseColor("#2A7A2D"))
        "בזמן" -> textview.setTextColor(Color.parseColor("#2A7A2D"))
        "ON TIME" -> textview.setTextColor(Color.parseColor("#2A7A2D"))
        "נחתה" -> textview.setTextColor(Color.parseColor("#2A7A2D"))
        "LANDED" -> textview.setTextColor(Color.parseColor("#2A7A2D"))
        "סופי" -> textview.setTextColor(Color.parseColor("#2A7A2D"))
        "FINAL" -> textview.setTextColor(Color.parseColor("#2A7A2D"))
        "לא סופי" -> textview.setTextColor(Color.parseColor("#FF5722"))
        "NOT FINAL" -> textview.setTextColor(Color.parseColor("#FF5722"))
        "בנחיתה" -> textview.setTextColor(Color.parseColor("#FF5722"))
        "LANDING" -> textview.setTextColor(Color.parseColor("#FF5722"))
        else -> textview.setTextColor(Color.parseColor("#D31010"))
    }
}


