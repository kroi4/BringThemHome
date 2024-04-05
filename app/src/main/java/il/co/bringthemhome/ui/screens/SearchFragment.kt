package il.co.bringthemhome.ui.screens

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import android.text.TextWatcher
import android.view.Gravity
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import il.co.bringthemhome.R
import il.co.bringthemhome.api.RowsAdapter
import il.co.bringthemhome.data.models.Row
import il.co.bringthemhome.databinding.DetailsLayoutBinding
import il.co.bringthemhome.databinding.DialogSliderBinding
import il.co.bringthemhome.databinding.ReleasedLayoutBinding
import il.co.bringthemhome.databinding.SearchLayoutBinding
import il.co.bringthemhome.ui.RowViewModel
import il.co.bringthemhome.utils.Loading
import il.co.bringthemhome.utils.Resource
import il.co.bringthemhome.utils.Success
import il.co.bringthemhome.utils.autoCleared
import il.co.bringthemhome.utils.imgGlideCaster
import il.co.bringthemhome.utils.showToast


class SearchFragment : Fragment() {

    private var binding: SearchLayoutBinding by autoCleared()
    private var itemBinding: DetailsLayoutBinding by autoCleared()
    private var ageBinding: DialogSliderBinding by autoCleared()
    private val viewModel: RowViewModel by activityViewModels()

    private var currentNameInput: String? = null
    private var currentGenderSelection: String? = null
    private var currentCityInput: String? = null
    private var currentStatusInput: String? = null
    private var currentMinAgeInput: String? = null
    private var currentMaxAgeInput: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchLayoutBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        performSearch()

        val genders = resources.getStringArray(R.array.genders)
        val genderAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_menu_popup_item, genders)
        binding.gender.setAdapter(genderAdapter)

        binding.ivButtonStatus.setOnClickListener { imageView ->
            showStatusMenu(imageView)
        }

         binding.apply {
            gender.setOnClickListener {
                binding.gender.setText("", false)
                currentGenderSelection = ""
                performSearch()
            }

            binding.gender.setOnItemClickListener { adapterView, view, position, id ->
                currentGenderSelection = when(position) {
                    0 -> "גבר"
                    1 -> "אישה"
                    else -> ""
                }
                performSearch()
            }

            etName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        currentNameInput = s?.toString()
                        performSearch()
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })

            etCity.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        currentCityInput = s?.toString()
                        performSearch()
                }

                override fun afterTextChanged(s: Editable?) {
                    currentCityInput = s?.toString()
                    performSearch()
                }
            })

            btnAge.setOnClickListener {
                ageBinding = DialogSliderBinding.inflate(layoutInflater)

                AlertDialog.Builder(context)
                    .setTitle(getString(R.string.age_range))
                    .setView(ageBinding.root)
                    .setPositiveButton(getString(R.string.ok)) { dialog, which ->
                        // Here you can get the selected values and update the UI accordingly
                        val selectedValues = ageBinding.rangeSlider.values
                        btnAge.text = "${getString(R.string.age)}: ${selectedValues[0].toInt()} - ${selectedValues[1].toInt()}"
                        Toast.makeText(requireContext(), "${getString(R.string.selected_age_range)}: ${selectedValues[0].toInt()} - ${selectedValues[1].toInt()}", Toast.LENGTH_SHORT).show()

                        currentMinAgeInput = selectedValues[0].toInt().toString()
                        currentMaxAgeInput = selectedValues[1].toInt().toString()
                        performSearch()
                    }
                    .setNegativeButton(getString(R.string.cancel), null)
                    .show()
            }
        }
    }

    private fun performSearch() {
        val nameInput = currentNameInput ?: ""
        val cityInput = currentCityInput ?: ""
        val genderSelection = currentGenderSelection ?: ""
        val status = currentStatusInput ?: ""
        val minAge = currentMinAgeInput ?: ""
        val maxAge = currentMaxAgeInput ?: ""

        val results = viewModel.getFilteredRows(nameInput, minAge, maxAge, genderSelection, cityInput, status)
        binding.rvKidnapped.adapter = setRecyclerViewer(results)

        val displayMetrics = resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        val columnWidthDp = 180
        val numOfColumns = (screenWidthDp / columnWidthDp).toInt()
        binding.rvKidnapped.layoutManager = GridLayoutManager(requireContext(), numOfColumns)
    }


    private fun setRecyclerViewer(it: List<Row>): RowsAdapter {
        return RowsAdapter(it, object : RowsAdapter.ItemListener {

            override fun onItemClicked(index: Int) {
                itemBinding = DetailsLayoutBinding.inflate(layoutInflater)

                val builder = AlertDialog.Builder(requireContext())
                builder.setView(itemBinding.root)
                val dialog = builder.create()

                itemBinding.apply {
                    tvDetailKidnappedName.text = "${it[index].b} ${it[index].c}"
                    tvDetailKidnappedAge.text = "${it[index].i}"
                    tvDetailKidnappedInfo.text = "${it[index].l}"
                    imgGlideCaster(requireContext(),it[index].j,ivDetailKidnappedImg)
                    dialog.show()
                }
            }

            override fun onItemLongClicked(index: Int) {}
        })
    }


    private fun showStatusMenu(view: View) {
        val popup = PopupMenu(requireContext(), view)
        popup.menuInflater.inflate(R.menu.status_menu, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            currentStatusInput = when (menuItem.itemId) {
                R.id.action_none -> {
                    binding.ivButtonStatus.setImageResource(R.drawable.grey)
                    "" // אם ללא בחירה מייצג כולם, נניח שהערך הוא ריק
                }
                R.id.action_released -> {
                    binding.ivButtonStatus.setImageResource(R.drawable.green)
                    "2" // ערך המייצג "משוחררים"
                }
                R.id.action_active -> {
                    binding.ivButtonStatus.setImageResource(R.drawable.red)
                    "1" // ערך המייצג "פעילים"
                }
                else -> null
            }

            performSearch() // קריאה מחדש לפונקציה עם הערך החדש של הסטטוס
            true
        }

        popup.show()
    }



}