package com.simplemvvm.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.simplemvvm.app.databinding.BetListItemBinding
import com.simplemvvm.app.model.Bet

/**
 * Adapter for displaying a list of bets in a RecyclerView.
 * This adapter efficiently handles updates to the list of bets using DiffUtil.
 */
class ItemBetAdapter : RecyclerView.Adapter<ItemBetAdapter.BetViewHolder>() {

    // List to hold the current set of bets
    private var bets: ArrayList<Bet> = ArrayList()

    /**
     * Updates the adapter's data with a new list of bets.
     * @param newData The new list of bets to update the adapter with.
     */
    fun updateData(newData: List<Bet>) {
        // Calculate the difference between the old and new data sets
        val diffResult = DiffUtil.calculateDiff(BetDiffCallback(bets, newData))
        bets.clear()
        bets.addAll(newData)
        // Dispatch updates to the adapter based on the calculated differences
        diffResult.dispatchUpdatesTo(this)
    }

    /**
     * Inflates the layout for each item in the RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BetViewHolder {
        return BetViewHolder.from(parent)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     */
    override fun getItemCount(): Int {
        return bets.size
    }

    /**
     * Binds the data to the views in the item layout.
     */
    override fun onBindViewHolder(holder: BetViewHolder, position: Int) {
        val item = bets[position]
        holder.bindingData(item)
    }

    /**
     * ViewHolder class for holding the views of each item in the RecyclerView.
     */
    class BetViewHolder(private val binding: BetListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the bet data to the item layout.
         */
        fun bindingData(bet: Bet) {
            binding.bet = bet
        }

        companion object {
            /**
             * Inflates the layout for the ViewHolder from the specified parent ViewGroup.
             */
            fun from(parent: ViewGroup): BetViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BetListItemBinding.inflate(layoutInflater, parent, false)
                return BetViewHolder(binding)
            }
        }
    }

    /**
     * Callback for calculating the difference between two lists of bets.
     */
    internal class BetDiffCallback(
        private val oldBets: List<Bet>,
        private val newBets: List<Bet>,
    ) : DiffUtil.Callback() {

        /**
         * Returns the size of the old list.
         */
        override fun getOldListSize(): Int {
            return oldBets.size
        }

        /**
         * Returns the size of the new list.
         */
        override fun getNewListSize(): Int {
            return newBets.size
        }

        /**
         * Checks if two items in the old and new lists represent the same item.
         */
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldBets[oldItemPosition] == newBets[newItemPosition]
        }

        /**
         * Checks if the contents of two items in the old and new lists are the same.
         */
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldBets[oldItemPosition] == newBets[newItemPosition]
        }
    }
}
