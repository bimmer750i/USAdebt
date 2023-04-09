package broz.tito.usadebt.presentation.screens.currency

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import broz.tito.usadebt.App
import broz.tito.usadebt.R
import broz.tito.usadebt.databinding.FragmentSelectedCurrenciesBinding
import broz.tito.usadebt.domain.currencydomain.currency_v2.remote.CurrencyV2Entity
import broz.tito.usadebt.model.PendingCurrencyV2Result
import broz.tito.usadebt.model.SuccessCurrencyV2Result
import broz.tito.usadebt.presentation.screens.getItemTouchHelper
import broz.tito.usadebt.presentation.viewmodels.SelectedCurrenciesViewModel
import broz.tito.usadebt.presentation.viewmodels.factories.SelectedCurrenciesViewModelFactory
import kotlinx.coroutines.*
import java.io.Serializable
import javax.inject.Inject

private const val ARG_LIST = "currencylist"

class SelectedCurrenciesFragment : Fragment(), FragmentController {

    @Inject
    lateinit var factory: SelectedCurrenciesViewModelFactory

    private val TAG = "SelectedCurrencies"
    private lateinit var binding: FragmentSelectedCurrenciesBinding
    private lateinit var viewModel: SelectedCurrenciesViewModel
    private lateinit var adapter: SelectedCurrenciesAdapter
    private var isLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as App).appComponent.inject(this)
        adapter = SelectedCurrenciesAdapter()
        if (savedInstanceState != null) {
            isLoaded = true
            adapter.list = savedInstanceState.getSerializable(ARG_LIST) as ArrayList<CurrencyV2Entity>
        }
        viewModel = ViewModelProvider(this,factory)[SelectedCurrenciesViewModel::class.java]
        lifecycleScope.launch {
            if (!isLoaded) {
                viewModel.loadCurrencies(requireContext())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectedCurrenciesBinding.inflate(layoutInflater,container,false)
        binding.selectCurrencies.setOnClickListener {
            lifecycleScope.launch {
                viewModel.setUnSelectedCurrencies(requireContext(),adapter.unselectedItemCodeList)
                adapter.unselectedItemCodeList.clear()
                withContext(Dispatchers.Main) {
                    findNavController().navigate(R.id.action_selectedCurrenciesFragment_to_chooseCurrencyFragment)
                }
            }

        }
        val manager = LinearLayoutManager(activity?.applicationContext)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = manager
        getItemTouchHelper(adapter).attachToRecyclerView(binding.recyclerView)
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            lifecycleScope.launch {
                viewModel.setUnSelectedCurrencies(
                        requireContext(),
                        adapter.unselectedItemCodeList)
                adapter.unselectedItemCodeList.clear()
                viewModel.loadCurrencies(requireContext())
            }
        }
        viewModel.currencyList.observe(viewLifecycleOwner) {
            Log.d(TAG, it.javaClass.name.toString())
            if (it is SuccessCurrencyV2Result) {
                hideAll()
                showResult()
                adapter.list = it.currency.currencyList
                binding.date.text = it.currency.date
            }
            else if (it is PendingCurrencyV2Result) {
                hideAll()
                showProgressBar()
            }
            else {
                hideAll()
                showError()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.setFragmentResultListener(ChooseCurrencyFragment.UPDATE,viewLifecycleOwner) { requestKey: String, data: Bundle ->
            val reload = data.getBoolean(ChooseCurrencyFragment.VALUE)
            if (reload) {
                lifecycleScope.launch {
                    viewModel.loadCurrencies(requireContext())
                    Log.d(TAG, "Updated selected currency list with new currencies !!!")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        isLoaded = false
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (this::adapter.isInitialized) {
            outState.putSerializable(ARG_LIST,adapter.list as Serializable)
        }
    }

    override fun showProgressBar() {
        binding.progressBar5.visibility = View.VISIBLE
    }

    override fun showResult() {
        binding.recyclerView.visibility = View.VISIBLE
        binding.date.visibility = View.VISIBLE
    }

    override fun hideAll() {
        binding.date.visibility = View.GONE
        binding.progressBar5.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.currencyerrortext.visibility  = View.GONE
    }

    override fun showError() {
        binding.currencyerrortext.visibility = View.VISIBLE
    }

}