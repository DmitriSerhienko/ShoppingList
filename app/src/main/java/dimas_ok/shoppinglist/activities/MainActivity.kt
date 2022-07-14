package dimas_ok.shoppinglist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dimas_ok.shoppinglist.R
import dimas_ok.shoppinglist.databinding.ActivityMainBinding
import dimas_ok.shoppinglist.dialogs.NewListDialog
import dimas_ok.shoppinglist.fragments.FragmentManager
import dimas_ok.shoppinglist.fragments.NoteFragment
import dimas_ok.shoppinglist.fragments.ShopListNamesFragment
import dimas_ok.shoppinglist.settings.SettingsActivity

class MainActivity : AppCompatActivity(),  NewListDialog.Listener {
    lateinit var binding: ActivityMainBinding
    private var currentMenuItemId = R.id.shop_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FragmentManager.setFragment(ShopListNamesFragment.newInstance(), this)
        setBottomNavListener()
    }

    private fun setBottomNavListener(){
        binding.bNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                }
                R.id.notes -> {
                    currentMenuItemId = R.id.notes
                    FragmentManager.setFragment(NoteFragment.newInstance(), this)
                }
                R.id.shop_list -> {
                    currentMenuItemId = R.id.shop_list
                    FragmentManager.setFragment(ShopListNamesFragment.newInstance(), this)
                }
                R.id.new_item -> {
                    //NewListDialog.showDialog(this, this))  - создать диалог с выбором что создаем
                    FragmentManager.currentFrag?.onClickNew()

                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        binding.bNav.selectedItemId = currentMenuItemId
    }

    override fun onClick(name: String) {
        Log.d("MyLog", "Name: $name")
    }
}