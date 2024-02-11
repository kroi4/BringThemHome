package il.co.bringthemhome.api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import il.co.bringthemhome.data.models.Row
import il.co.bringthemhome.databinding.ItemLayoutBinding

class KidnappedAdapter : RecyclerView.Adapter<KidnappedAdapter.KidnappedViewHolder>() {

    inner class KidnappedViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Row>() {
        override fun areItemsTheSame(oldItem: Row, newItem: Row): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Row, newItem: Row): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var rows: List<Row>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


    override fun getItemCount() = rows.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KidnappedViewHolder {
        return KidnappedViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: KidnappedViewHolder, position: Int) {
        holder.binding.apply {
            val row = rows[position]
            tvKidnappedDetails.text = "${row.b} ${row.c}"
            tvKidnappedSecondDetails.text = "${row.i}"

        }
    }

}