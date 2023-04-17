package broz.tito.usadebt.presentation.screens.currency.v1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import broz.tito.usadebt.App
import broz.tito.usadebt.databinding.FragmentCurrencyBinding
import broz.tito.usadebt.model.*
import broz.tito.usadebt.presentation.viewmodels.CurrencyFragmentViewModel
import kotlinx.coroutines.launch


class CurrencyFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var isLoaded = false
    private lateinit var viewModel: CurrencyFragmentViewModel
    private lateinit var app: App
    private lateinit var binding: FragmentCurrencyBinding
    private var currencies: HashMap<String,Currency> = HashMap<String,Currency>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = requireActivity().application as App
        viewModel = ViewModelProvider(requireActivity())[CurrencyFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyBinding.inflate(inflater,container,false)
        if (savedInstanceState != null) {
            setCurrencyFields(savedInstanceState.get("currencies") as HashMap<String, Currency>)
            isLoaded = true
        }
        binding.currencyswiperefresh.setOnRefreshListener {
            binding.currencyswiperefresh.isRefreshing = false
            viewLifecycleOwner.lifecycleScope.launch {
                //viewModel.getCurrency()
            }
        }
       /* viewModel.currencyResult.observe(viewLifecycleOwner, Observer {
            when(it) {
                is SuccessCurrencyResult -> {
                    hideAll()
                    binding.currencyscrollview.visibility = View.VISIBLE
                    currencies = it.currencies
                    setCurrencyFields(currencies)
                }
                is FailureCurrencyResult -> {
                    hideAll()
                    binding.currencyError.visibility = View.VISIBLE
                }
                is PendingCurrencyResult -> {
                    hideAll()
                    binding.currencyProgressBar.visibility = View.VISIBLE
                }
            }
        })*/



        return binding.root
    }

    override fun onStart() {
        if (!isLoaded) {
            viewLifecycleOwner.lifecycleScope.launch {
                //viewModel.getCurrency()
            }
        }
        super.onStart()
    }

    override fun onResume() {
        isLoaded = false
        super.onResume()
    }

    private fun hideAll() {
        binding.currencyscrollview.visibility = View.GONE
        binding.currencyError.visibility = View.GONE
        binding.currencyProgressBar.visibility = View.GONE
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("currencies",currencies)
        super.onSaveInstanceState(outState)
    }

    private fun setCurrencyFields(currencies: HashMap<String,Currency>) {
        currencies.forEach {
            when(it.key) {
                "China" -> {
                    binding.usdCnyValue.text = it.value.value
                    binding.usdCnyDate.text = it.value.date
                }
                "Euro Zone" -> {
                    binding.usdEurValue.text = it.value.value
                    binding.usdEurDate.text = it.value.date
                }
                "Japan" -> {
                    binding.usdJpyValue.text = it.value.value
                    binding.usdJpyDate.text = it.value.date
                }
                "Canada" -> {
                    binding.usdCadValue.text = it.value.value
                    binding.usdCadDate.text = it.value.date
                }
                "United Kingdom" -> {
                    binding.usdGbpValue.text = it.value.value
                    binding.usdGbpDate.text = it.value.date
                }
                "Switzerland" -> {
                    binding.usdChfValue.text = it.value.value
                    binding.usdChfDate.text = it.value.date
                }
            }
        }
    }
}