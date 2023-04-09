package broz.tito.usadebt.presentation.screens.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import broz.tito.usadebt.databinding.HistoryelementBinding

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    var elements = arrayListOf<String>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HistoryelementBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val text = elements.get(position)
        holder.binding.textView3.text = text
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    class ViewHolder(val binding: HistoryelementBinding): RecyclerView.ViewHolder(binding.root) {

    }
}