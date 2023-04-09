package broz.tito.usadebt.presentation.screens.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import broz.tito.usadebt.databinding.ChoosecurrencylayoutBinding
import broz.tito.usadebt.domain.currencydomain.currency_v2.local.ChooseCurrencyEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChooseCurrencyAdapter: RecyclerView.Adapter<ChooseCurrencyAdapter.ViewHolder>(),CompoundButton.OnCheckedChangeListener {

    private val _state = MutableStateFlow(0)
    val state: StateFlow<Int> = _state

    var list = ArrayList<ChooseCurrencyEntity>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    private val selectedCurrenciesList = ArrayList<ChooseCurrencyEntity>()

    fun getSelectedItemsCodes(): ArrayList<ChooseCurrencyEntity> {
        return selectedCurrenciesList
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        val currencyCode = p0?.tag as ChooseCurrencyEntity
        if (p0.isChecked) {
            selectedCurrenciesList.add(currencyCode)
            _state.value = selectedCurrenciesList.size
        }
        else {
            selectedCurrenciesList.remove(currencyCode)
            _state.value = selectedCurrenciesList.size
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChoosecurrencylayoutBinding.inflate(inflater,parent,false)
        binding.checkBox.isChecked = false
        binding.checkBox.setOnCheckedChangeListener(this)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currency = list.get(position)
        with(holder.binding) {
            checkBox.tag = currency
            textView7.text = currency.currencyText
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(val binding: ChoosecurrencylayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }


}