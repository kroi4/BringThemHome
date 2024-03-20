package il.co.bringthemhome.ui.screens

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import il.co.bringthemhome.R
import il.co.bringthemhome.api.RowsAdapter
import il.co.bringthemhome.data.models.Row
import il.co.bringthemhome.databinding.ActivitiesLayoutBinding
import il.co.bringthemhome.databinding.DetailsLayoutBinding
import il.co.bringthemhome.ui.RowViewModel
import il.co.bringthemhome.utils.Loading
import il.co.bringthemhome.utils.Resource
import il.co.bringthemhome.utils.Success
import il.co.bringthemhome.utils.autoCleared
import il.co.bringthemhome.utils.imgGlideCaster

class ActivitiesFragment : Fragment() {

    private var binding: ActivitiesLayoutBinding by autoCleared()
    private var itemBinding: DetailsLayoutBinding by autoCleared()
    private val viewModel: RowViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivitiesLayoutBinding.inflate(layoutInflater)


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetch()
        val results = viewModel.getActivitiesCountRows()
        binding.tvActivity.text = "${getString(R.string.activities)} (${results.toString()})"


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
    }

    fun setRecyclerViewer(it: Resource<List<Row>>): RowsAdapter {
        return RowsAdapter(it.status.data!!, object : RowsAdapter.ItemListener {

            override fun onItemClicked(index: Int) {

                itemBinding = DetailsLayoutBinding.inflate(layoutInflater)

                val builder = AlertDialog.Builder(requireContext())
                builder.setView(itemBinding.root)
                val dialog = builder.create()

                itemBinding.apply {
                    tvDetailKidnappedName.text = "${it.status.data[index].b} ${it.status.data[index].c}"
                    tvDetailKidnappedAge.text = "${it.status.data[index].i}"
                    tvDetailKidnappedInfo.text = "${it.status.data[index].l}"
                    imgGlideCaster(requireContext(),it.status.data[index].j,ivDetailKidnappedImg)

                    dialog.show()
                }
            }

            override fun onItemLongClicked(index: Int) {}
        })
    }

    private fun fetch() {
        viewModel.getActivitiesRows()
        viewModel.activitiesKidnapped.observe(viewLifecycleOwner) {
            when (it.status) {
                is Loading -> {
                    binding.rvKidnapped.isVisible = false
                    binding.progressBar.isVisible = true
                }
                is Success -> {
                    if (!it.status.data.isNullOrEmpty()) {
                        binding.progressBar.isVisible = false
                        binding.rvKidnapped.isVisible = true
                        binding.tvStatus.isVisible = false
                        binding.rvKidnapped.adapter = setRecyclerViewer(it)

                        val displayMetrics = resources.displayMetrics
                        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
                        val columnWidthDp = 180
                        val numOfColumns = (screenWidthDp / columnWidthDp).toInt()
                        binding.rvKidnapped.layoutManager = GridLayoutManager(requireContext(), numOfColumns)

                    }
                }
                is Error -> {
                    binding.progressBar.isVisible = false
                    binding.rvKidnapped.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.couldnt_connect_to_server),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    binding.tvStatus.text = getString(R.string.couldnt_connect_to_server)
                    binding.tvStatus.isVisible = true
                }
                else -> {
                    binding.progressBar.isVisible = false
                    binding.rvKidnapped.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.couldnt_connect_to_server),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    binding.tvStatus.text = getString(R.string.couldnt_connect_to_server)
                    binding.tvStatus.isVisible = true
                }
            }
        }
    }



}