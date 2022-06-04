package dimas_ok.shoppinglist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dimas_ok.shoppinglist.activities.MainApp
import dimas_ok.shoppinglist.databinding.FragmentShopListNamesBinding
import dimas_ok.shoppinglist.db.MainViewModel
import dimas_ok.shoppinglist.db.ShopListNameAdapter
import dimas_ok.shoppinglist.dialogs.NewListDialog
import dimas_ok.shoppinglist.entities.ShoppingListName
import dimas_ok.shoppinglist.utils.TimeManager

class ShopListNamesFragment : BaseFragment() {
    private lateinit var binding: FragmentShopListNamesBinding
    private lateinit var adapter: ShopListNameAdapter

    private val mainViewModel: MainViewModel by activityViewModels {
        MainViewModel.MainViewModelFactory((context?.applicationContext as MainApp).database)
    }

    override fun onClickNew() {
        NewListDialog.showDialog(activity as AppCompatActivity, object : NewListDialog.Listener {
            override fun onClick(name: String) {
                val shopListName = ShoppingListName(
                    null,
                    name,
                    TimeManager.getCurrentTime(),
                    0,
                    0,
                    ""
                )
                mainViewModel.insertShopListName(shopListName)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentShopListNamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        observer()
    }

    private fun initRcView() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = ShopListNameAdapter()
        rcView.adapter = adapter
    }

    private fun observer() {
       mainViewModel.allShopListNames.observe(viewLifecycleOwner, adapter::submitList
//            {
//            adapter.submitList(it)
//      }
       )
    }



    companion object {


        @JvmStatic
        fun newInstance() = ShopListNamesFragment()

    }
}