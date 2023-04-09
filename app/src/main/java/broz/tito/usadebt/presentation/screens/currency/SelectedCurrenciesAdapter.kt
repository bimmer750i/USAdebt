package broz.tito.usadebt.presentation.screens.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import broz.tito.usadebt.databinding.SelectedcurrencylayoutBinding
import broz.tito.usadebt.domain.currencydomain.currency_v2.remote.CurrencyV2Entity

class SelectedCurrenciesAdapter: RecyclerView.Adapter<SelectedCurrenciesAdapter.ViewHolder>() {

    var list = ArrayList<CurrencyV2Entity>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    val unselectedItemCodeList = ArrayList<String>()

    fun setUnselectedElements(index: Int) {
        val code = list.get(index).currencyCode
        unselectedItemCodeList.add(code)
        list.removeAt(index)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SelectedcurrencylayoutBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currencyV2Entity = list.get(position)
        holder.binding.selectedcurrencytext.text = currencyV2Entity.currencyText
        holder.binding.selectedcurrencyvalue.text = currencyV2Entity.currencyValue
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(val binding: SelectedcurrencylayoutBinding): RecyclerView.ViewHolder(binding.root)
}