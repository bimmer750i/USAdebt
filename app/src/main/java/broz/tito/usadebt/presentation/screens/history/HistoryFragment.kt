package broz.tito.usadebt.presentation.screens.history


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import broz.tito.usadebt.App
import broz.tito.usadebt.databinding.FragmentHistoryBinding
import broz.tito.usadebt.model.FailureHistoryResult
import broz.tito.usadebt.model.PendingHistoryResult
import broz.tito.usadebt.model.SuccessHistoryResult
import broz.tito.usadebt.presentation.screens.*
import broz.tito.usadebt.presentation.viewmodels.HistoryFragmentViewModel
import broz.tito.usadebt.presentation.viewmodels.factories.HistoryFragmentViewModelFactory
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoryFragment : Fragment() {

    @Inject
    lateinit var historyFragmentViewModelFactory: HistoryFragmentViewModelFactory

    private lateinit var viewModel: HistoryFragmentViewModel
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var adapter: HistoryAdapter

    private var isLoaded = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = HistoryAdapter()
        if (savedInstanceState != null) {
            val list = savedInstanceState?.getStringArrayList("loaded_arraylist")
            if (list != null) {
                isLoaded = true
                adapter.elements = list
            }
        }
        (requireActivity().application as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this,historyFragmentViewModelFactory)[HistoryFragmentViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        binding.chipsPrograms.setOnCheckedStateChangeListener(object:
            ChipGroup.OnCheckedStateChangeListener {
            override fun onCheckedChanged(group: ChipGroup, checkedIds: MutableList<Int>) {
                if (checkedIds.size > 0) {
                    when(checkedIds.first()) {
                        binding.chip0.id -> {
                            if (isLoaded) return
                            viewModel.getHistory("7")
                            return
                        }
                        binding.chip1.id -> {
                            if (isLoaded) return
                            viewModel.getHistory("14")
                            return
                        }
                        binding.chip2.id -> {
                            if (isLoaded) return
                            viewModel.getHistory("30")
                            return
                        }
                        binding.chip3.id -> {
                            if (isLoaded) return
                            viewModel.getHistory("60")
                            return
                        }
                        binding.chip4.id -> {
                            if (isLoaded) return
                            viewModel.getHistory("180")
                            return
                        }
                    }
                }
            }
        })

        viewModel.historyResult.observe(viewLifecycleOwner, Observer {
            when(it) {
                is SuccessHistoryResult -> {
                    hideAll()
                    adapter.elements = it.debtarray
                    binding.HistoryRecyclerView.visibility = View.VISIBLE
                }
                is FailureHistoryResult -> {
                    hideAll()
                    binding.textView2.visibility = View.VISIBLE
                }
                is PendingHistoryResult -> {
                    hideAll()
                    binding.progressBar.visibility = View.VISIBLE

                }
            }
        })
        binding.HistoryRecyclerView.adapter = adapter
        binding.HistoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        isLoaded = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putStringArrayList("loaded_arraylist", adapter.elements)
        super.onSaveInstanceState(outState)
    }





    private fun hideAll() {
        binding.HistoryRecyclerView.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.textView2.visibility = View.GONE
    }

}