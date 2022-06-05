package dimas_ok.shoppinglist.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dimas_ok.shoppinglist.R
import dimas_ok.shoppinglist.databinding.ListNameItemBinding
import dimas_ok.shoppinglist.entities.ShopListNameItem

class ShopListNameAdapter(private val listener: Listener) :
    ListAdapter<ShopListNameItem, ShopListNameAdapter.ItemHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListNameItemBinding.bind(view)

        fun setData(shopListNameItemItem: ShopListNameItem, listener: Listener) = with(binding) {
            tvListName.text = shopListNameItemItem.name
            tvTime.text = shopListNameItemItem.time
            itemView.setOnClickListener {

            }
            imDelete.setOnClickListener {
                listener.deleteItem(shopListNameItemItem.id!!)
            }
            imEdit.setOnClickListener {
                listener.editItem(shopListNameItemItem)
            }
        }

        companion object {
            fun create(parent: ViewGroup): ItemHolder {
                return ItemHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_name_item, parent, false))
            }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<ShopListNameItem>() {
        override fun areItemsTheSame(
            oldItem: ShopListNameItem,
            newItem: ShopListNameItem,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ShopListNameItem,
            newItem: ShopListNameItem,
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface Listener {
        fun deleteItem(id: Int)
        fun editItem(shopListNameItem: ShopListNameItem)
        fun onClickItem(shopListNameItem: ShopListNameItem)
    }

}