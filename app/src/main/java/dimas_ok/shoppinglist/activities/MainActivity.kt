package dimas_ok.shoppinglist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dimas_ok.shoppinglist.R
import dimas_ok.shoppinglist.databinding.ActivityMainBinding
import dimas_ok.shoppinglist.dialogs.NewListDialog
import dimas_ok.shoppinglist.fragments.FragmentManager
import dimas_ok.shoppinglist.fragments.NoteFragment
import dimas_ok.shoppinglist.fragments.ShopListNamesFragment

class MainActivity : AppCompatActivity(),  NewListDialog.Listener {
    lateinit var binding: ActivityMainBinding

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
                    Log.d("MyLog", "Settings")
                }
                R.id.notes -> {
                    FragmentManager.setFragment(NoteFragment.newInstance(), this)
                }
                R.id.shop_list -> {
                    FragmentManager.setFragment(ShopListNamesFragment.newInstance(), this)
                }
                R.id.new_item -> {
                    //NewListDialog.showDialog(this, this)
                    FragmentManager.currentFrag?.onClickNew()

                }
            }
            true
        }
    }

    override fun onClick(name: String) {
        Log.d("MyLog", "Name: $name")
    }
}