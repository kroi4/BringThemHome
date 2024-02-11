package il.co.bringthemhome.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import il.co.bringthemhome.R
import il.co.bringthemhome.data.models.Row
import il.co.bringthemhome.databinding.ItemLayoutBinding
import il.co.bringthemhome.utils.imgGlideCaster

class RowsAdapter(val rows: List<Row>, val callBack: ItemListener) :
    RecyclerView.Adapter<RowsAdapter.ItemViewHolder>() {

    interface ItemListener {
        fun onItemClicked(index: Int)
        fun onItemLongClicked(index: Int)
    }

    inner class ItemViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener,
        View.OnLongClickListener {

        init {
            binding.root.setOnClickListener(this)
            binding.root.setOnLongClickListener(this)
        }

        override fun onClick(p0: View?) {
            callBack.onItemClicked(adapterPosition)
        }

        override fun onLongClick(p0: View?): Boolean {
            callBack.onItemLongClicked(adapterPosition)
            return false
        }

        fun bind(row: Row) {
            binding.tvKidnappedDetails.text = "${row.b} ${row.c}"
            binding.tvKidnappedSecondDetails.text = "${row.i}"

            when (row.status){
                "1" -> binding.llItem.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.dark_red))
                "2" -> binding.llItem.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.dark_green))
            }
        }
    }


    fun itemAt(position: Int) = rows[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(rows[position])
        Glide.with(holder.itemView)
            .load(rows[position].j)
            .override(200, 200)
            .into(holder.itemView.findViewById(R.id.ivKidnappedImg))
    }

    override fun getItemCount() =
        rows.size
}