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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import il.co.bringthemhome.R
import il.co.bringthemhome.api.RowsAdapter
import il.co.bringthemhome.data.models.Row
import il.co.bringthemhome.databinding.DetailsLayoutBinding
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
    private val viewModel: RowViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchLayoutBinding.inflate(layoutInflater)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) = makeFlag(
                ItemTouchHelper.ACTION_STATE_SWIPE,
                ItemTouchHelper.ACTION_STATE_IDLE or ItemTouchHelper.ACTION_STATE_IDLE
            )

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val item =
//                    (binding.recycler.adapter as ItemAdapter).itemAt(viewHolder.adapterPosition)
//                viewModel.deleteItem(item)
            }
        }).attachToRecyclerView(binding.rvKidnapped)

        binding.apply {
            etName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    // ניתן להשתמש כאן אם יש צורך לבצע פעולה לפני שהטקסט משתנה
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // זה המקום להגיב לשינוי בטקסט, לדוגמה להציג Toast
                    if (s != null && s.isNotEmpty()) {
                        val nameInput = s.toString()

                        val results = viewModel.getFilteredNameRows(nameInput)
                        binding.rvKidnapped.adapter = setRecyclerViewer(results)

                        val displayMetrics = resources.displayMetrics
                        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
                        val columnWidthDp = 180
                        val numOfColumns = (screenWidthDp / columnWidthDp).toInt()
                        binding.rvKidnapped.layoutManager = GridLayoutManager(requireContext(), numOfColumns)
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    // ניתן להשתמש כאן אם יש צורך לבצע פעולה אחרי שהטקסט שונה
                }
            })
        }

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


}