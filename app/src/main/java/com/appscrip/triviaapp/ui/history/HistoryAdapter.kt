package com.appscrip.triviaapp.ui.history

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.appscrip.triviaapp.R
import com.appscrip.triviaapp.databinding.ItemHistoryBinding
import com.appscrip.triviaapp.models.History

/**
 * Created by Sagar Chavda on 27/04/20.
 * It's create list of history with assigned history details
 */
class HistoryAdapter internal constructor(context: Context): RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var list = emptyList<History>()

    /**
     * It's handle view and interact with it
     */
    class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History?) {
            binding.history = history
            binding.executePendingBindings()
        }
    }

    /**
     * Create view holder from view layout file
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemBinding: ItemHistoryBinding = DataBindingUtil.inflate(inflater, R.layout.item_history, parent, false)
        return HistoryViewHolder(itemBinding)
    }

    /**
     * It's get data and bind it with view holder for displaying
     */
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history: History = list[position]
        holder.bind(history)
    }

    /**
     * It's set data in list of adapter
     */
    internal fun setHistories(histories: List<History>) {
        this.list = histories
        notifyDataSetChanged()
    }

    /**
     * Return total count of list items
     */
    override fun getItemCount(): Int {
        return list.size
    }

}