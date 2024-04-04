package com.simplemvvm.app.viewBinding


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.simplemvvm.app.adapter.ItemBetAdapter
import com.simplemvvm.app.model.Bet

// Binding adapter to set the list of bets to the RecyclerView
@BindingAdapter("setBetList")
fun setBetList(recyclerView: RecyclerView, list: MutableList<Bet>?) {
    // Update the data in the RecyclerView adapter with the provided list of bets
    (recyclerView.adapter as ItemBetAdapter?)?.updateData(list ?: emptyList())
}

// Binding adapter to load an image URL into an ImageView using Coil library
@BindingAdapter("loadImageUrl")
fun loadImageUrl(image: ImageView, url: String?) {
    // Load the image from the provided URL into the ImageView using Coil library
    image.load(url)
}