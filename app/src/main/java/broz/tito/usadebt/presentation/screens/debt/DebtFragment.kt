package broz.tito.usadebt.presentation.screens.debt

import android.os.Bundle
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import broz.tito.usadebt.App
import broz.tito.usadebt.R
import broz.tito.usadebt.databinding.FragmentDebtBinding
import broz.tito.usadebt.model.FailureDebtResult
import broz.tito.usadebt.model.PendingDebtResult
import broz.tito.usadebt.model.SuccessDebtResult
import broz.tito.usadebt.presentation.viewmodels.DebtFragmentViewModel
import broz.tito.usadebt.presentation.viewmodels.factories.DebtFragmentViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject


class DebtFragment : Fragment() {

    @Inject
    lateinit var debtFragmentFactory: DebtFragmentViewModelFactory

    private lateinit var viewModel: DebtFragmentViewModel
    private lateinit var binding: FragmentDebtBinding
    private var isExtended = false
    private var isLoaded = false
    private var text : String = ""
    private var extendedText : String = ""
    private var res: Int = R.drawable.ic_baseline_add_24



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this,debtFragmentFactory)[DebtFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDebtBinding.inflate(inflater,container,false)
        binding.button.setOnClickListener {
            if (!isExtended) {
                isExtended = true
                TransitionManager.beginDelayedTransition(binding.cardView)
                if (!extendedText.equals("")) {
                    binding.debtText.text = extendedText
                }
                res = R.drawable.ic_baseline_remove_24
                binding.button.setImageResource(res)
            }
            else {
                isExtended = false
                TransitionManager.beginDelayedTransition(binding.cardView)
                if (!text.equals("")) {
                    binding.debtText.text = text
                }
                res = R.drawable.ic_baseline_add_24
                binding.button.setImageResource(res)
            }
        }
        viewModel.currentDebt.observe(viewLifecycleOwner, Observer {
            when(it) {
                is PendingDebtResult -> {
                    showProgressBar()
                    binding.button.isClickable = false}
                is FailureDebtResult -> {
                    hideProgressBar()
                    binding.button.isClickable = false}
                is SuccessDebtResult -> {
                    hideProgressBar()
                    binding.button.isClickable = true
                    extendedText = it.debt.extended_text
                    text = it.debt.text
                    TransitionManager.beginDelayedTransition(binding.cardView)
                    if (isExtended) {
                        binding.debtText.text = it.debt.extended_text
                    }
                    else {
                        binding.debtText.text = it.debt.text
                    }
                }
            }
        })

        binding.swiperefresh.setOnRefreshListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getDebt()
            }
            binding.swiperefresh.isRefreshing = false
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isLoaded) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getDebt()
            }
            isLoaded = true
        }
        else {
            binding.button.setImageResource(res)
        }
    }

    private fun showProgressBar() {
        binding.progressBar2.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar2.visibility = View.GONE
    }

}