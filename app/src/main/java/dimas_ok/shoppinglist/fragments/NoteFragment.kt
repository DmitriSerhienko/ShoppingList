package dimas_ok.shoppinglist.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dimas_ok.shoppinglist.R
import dimas_ok.shoppinglist.activities.MainApp
import dimas_ok.shoppinglist.activities.NewNoteActivity
import dimas_ok.shoppinglist.databinding.FragmentNoteBinding
import dimas_ok.shoppinglist.db.MainViewModel
import dimas_ok.shoppinglist.db.NoteAdapter
import dimas_ok.shoppinglist.entities.NoteItem

class NoteFragment : BaseFragment() , NoteAdapter.Listener {
    private lateinit var binding: FragmentNoteBinding
    private lateinit var editLauncher: ActivityResultLauncher<Intent>
    private lateinit var adapter: NoteAdapter

    private val mainViewModel: MainViewModel by activityViewModels {
        MainViewModel.MainViewModelFactory((context?.applicationContext as MainApp).database)
    }
    override fun onClickNew() {
        editLauncher.launch(Intent(activity, NewNoteActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onEditResult()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        observer()
    }
    private fun initRcView () = with(binding){
        rcViewNote.layoutManager = LinearLayoutManager(activity)
        adapter = NoteAdapter(this@NoteFragment)
        rcViewNote.adapter = adapter
    }
    private fun observer(){
        mainViewModel.allNotes.observe(viewLifecycleOwner, adapter::submitList
//            {
//            adapter.submitList(it)
//        }
        )
    }

    private fun onEditResult(){
        editLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
                if(it.resultCode == Activity.RESULT_OK){
                    mainViewModel.insertNote(it.data?.getSerializableExtra(NEW_NOTE_KEY) as NoteItem)
                }
            }

    }

    companion object {
        const val NEW_NOTE_KEY = "new_note_key"
        @JvmStatic
        fun newInstance() = NoteFragment()

    }

    override fun deleteItem(id: Int) {
        mainViewModel.deleteNote(id)
    }
}