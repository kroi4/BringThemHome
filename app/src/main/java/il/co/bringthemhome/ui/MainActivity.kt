package il.co.bringthemhome.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import il.co.bringthemhome.databinding.ActivityMainBinding
import il.co.bringthemhome.MyApplication

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rowViewModel: RowViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = (application as MyApplication).rowRepository
        rowViewModel = ViewModelProvider(
            this,
            RowViewModelFactory(repository)
        ).get(RowViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}