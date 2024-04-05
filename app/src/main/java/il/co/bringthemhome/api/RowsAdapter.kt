package il.co.bringthemhome.api

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import il.co.bringthemhome.R
import il.co.bringthemhome.data.models.Row
import il.co.bringthemhome.databinding.ItemLayoutBinding
import il.co.bringthemhome.utils.imgGlideCaster
import il.co.bringthemhome.utils.showToast


class RowsAdapter(private val rows: List<Row>, val callBack: ItemListener) :
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
            val clipboard = itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("kidnappedClipboard", "${rows[adapterPosition].b} ${rows[adapterPosition].c}, ${rows[adapterPosition].i} - ${rows[adapterPosition].l}")
            clipboard.setPrimaryClip(clip)

            showToast(itemView.context, "${ContextCompat.getString(itemView.context, R.string.copied)}")
            return true
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
        imgGlideCaster(holder.itemView.context, rows[position].j, holder.itemView.findViewById(R.id.ivKidnappedImg))
    }

    override fun getItemCount() =
        rows.size
}