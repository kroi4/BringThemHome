package il.co.bringthemhome.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import il.co.bringthemhome.R

fun imgGlideCaster(context: Context, urlImg: String, intoImg: ImageView) {
    Glide.with(context)
        .load(urlImg)
        .into(intoImg)
}

fun showToast(context: Context, text: String) {
    val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
    toast.show()
}
