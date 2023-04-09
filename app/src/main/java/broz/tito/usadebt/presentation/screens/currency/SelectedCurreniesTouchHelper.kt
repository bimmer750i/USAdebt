package broz.tito.usadebt.presentation.screens

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import broz.tito.usadebt.presentation.screens.currency.SelectedCurrenciesAdapter

fun getItemTouchHelper(adapter: SelectedCurrenciesAdapter): ItemTouchHelper {
    val callback = object: ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT,ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            adapter.setUnselectedElements(viewHolder.adapterPosition)
        }

    }
    return ItemTouchHelper(callback)
}
