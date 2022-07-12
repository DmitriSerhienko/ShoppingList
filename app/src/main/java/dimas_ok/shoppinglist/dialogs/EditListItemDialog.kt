package dimas_ok.shoppinglist.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import dimas_ok.shoppinglist.R
import dimas_ok.shoppinglist.databinding.EditListItemDialogBinding
import dimas_ok.shoppinglist.databinding.NewListDialogBinding
import dimas_ok.shoppinglist.entities.ShopListItem

object EditListItemDialog {
    fun showDialog(context: Context, item: ShopListItem, listener: Listener){
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = EditListItemDialogBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)
        binding.apply {
                edName.setText(item.name)
                edInfo.setText(item.itemInfo)
                bUpdate.setOnClickListener {
                    if(edName.text.toString().isNotEmpty()){
                        listener.onClick(item.copy(name = edName.text.toString(), itemInfo = edInfo.text.toString()))
                    }

                    dialog?.dismiss()
                }

            }

        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(null)
        dialog.show()
    }
    interface Listener{
        fun onClick(item: ShopListItem)
    }
}