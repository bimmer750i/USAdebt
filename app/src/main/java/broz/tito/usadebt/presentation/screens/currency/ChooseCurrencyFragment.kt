package broz.tito.usadebt.presentation.screens.currency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import broz.tito.usadebt.App
import broz.tito.usadebt.R.string
import broz.tito.usadebt.data.currencydata.currency_v2.CurrencyEntityRoom
import broz.tito.usadebt.databinding.FragmentChooseCurrencyBinding
import broz.tito.usadebt.presentation.viewmodels.ChooseCurrencyFragmentViewModel
import broz.tito.usadebt.presentation.viewmodels.factories.ChooseCurrencyFragmentViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ChooseCurrencyFragment : Fragment() {

    private lateinit var binding: FragmentChooseCurrencyBinding
    lateinit var viewModel: ChooseCurrencyFragmentViewModel
    @Inject
    lateinit var factory: ChooseCurrencyFragmentViewModelFactory
    val TAG = "ChooseCurrencyFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this,factory).get(ChooseCurrencyFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChooseCurrencyBinding.inflate(layoutInflater,container,false)
        binding.chooseCurrencyRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ChooseCurrencyAdapter()
        binding.chooseCurrencyRecyclerview.adapter = adapter
        binding.button2.setOnClickListener {
            lifecycleScope.launch {

                viewModel.setSelectedCurrencies(requireContext(), adapter.getSelectedItemsCodes().map {
                       CurrencyEntityRoom(it.id,it.currencyCode,true)
                } as ArrayList<CurrencyEntityRoom>)

                withContext(Dispatchers.Main) {
                    if (adapter.getSelectedItemsCodes().size > 0) {
                        parentFragmentManager.setFragmentResult(UPDATE, bundleOf(VALUE to true))
                    }
                    findNavController().navigateUp()
                }
            }
        }
        viewModel.unSelectedCurrenciesLiveData.observe(viewLifecycleOwner) {
            adapter.list = it
        }
        lifecycleScope.launch {
            viewModel.getUnselectedCurrencies(requireContext())
        }
        lifecycleScope.launchWhenResumed {
            adapter.state.collectLatest {
                if (it == 0) {
                    binding.button2.text = getString(string.go_back)
                }
                else {
                    binding.button2.text = getString(string.add) + " ($it)"
                }
            }
        }
        return binding.root
    }



    companion object {
        const val UPDATE = "UPDATE"
        const val VALUE = "VALUE"
    }
}