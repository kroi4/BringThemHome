package il.co.bringthemhome.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import il.co.bringthemhome.R
import il.co.bringthemhome.databinding.HomePageLayoutBinding
import il.co.bringthemhome.ui.ScreenSlidePagerAdapter
import il.co.bringthemhome.utils.autoCleared
import java.util.Calendar
import java.util.Timer
import kotlin.concurrent.timerTask

class HomePageFragment : Fragment(R.layout.home_page_layout) {

    private var binding: HomePageLayoutBinding by autoCleared()
    private var timer: Timer? = null // הגדר את הטיימר כמשתנה חבר של ה-Fragment


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomePageLayoutBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        startTimer()
    }


    private fun startTimer() {
        val targetCalendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2023)
            set(Calendar.MONTH, Calendar.OCTOBER)
            set(Calendar.DAY_OF_MONTH, 7)
            set(Calendar.HOUR_OF_DAY, 6)
            set(Calendar.MINUTE, 20)
            set(Calendar.SECOND, 0)
        }

        timer = Timer().apply {
            scheduleAtFixedRate(timerTask {
                val currentTime = Calendar.getInstance()
                val diff = currentTime.timeInMillis - targetCalendar.timeInMillis

                if (diff > 0) {
                    val days = (diff / (24 * 60 * 60 * 1000)).toInt()
                    val hours = ((diff / (60 * 60 * 1000)) % 24).toInt()
                    val minutes = ((diff / (60 * 1000)) % 60).toInt()
                    val seconds = ((diff / 1000) % 60).toInt()

                    activity?.runOnUiThread {
                        if (isAdded) { // בדוק אם הפרגמנט עדיין מחובר ונמצא בשימוש
                            binding.tvDays.text = String.format("%03d", days)
                            binding.tvHrs.text = String.format("%02d", hours)
                            binding.tvMin.text = String.format("%02d", minutes)
                            binding.tvSec.text = String.format("%02d", seconds)
                        }
                    }
                } else {
                    cancel()
                    activity?.runOnUiThread {
                        if (isAdded) {
                            // פעולות שיתבצעו כאשר הספירה קדימה מסתיימת
                        }
                    }
                }
            }, 0, 1000)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter = ScreenSlidePagerAdapter(requireActivity())
        binding.viewPager.adapter = adapter
    }

}