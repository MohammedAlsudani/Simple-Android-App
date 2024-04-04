package com.simplemvvm.app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simplemvvm.app.model.Bet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for the main screen.
 * This ViewModel manages the data related to bets displayed on the main screen.
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    // LiveData to hold the list of bets
    private val _betList = MutableLiveData<List<Bet>>(emptyList())
    val betList: LiveData<List<Bet>> get() = _betList

    /**
     * Initializes the ViewModel by fetching the list of bets data.
     */
    init {
        fetchBetsData()
    }

    /**
     * Fetches the initial list of bets data.
     */
   private fun fetchBetsData() {
        fetchDataAndUpdateList { getItemsFromNetwork() }
    }

    /**
     * Updates the list of bets with recalculated odds.
     */
    fun onUpdateOdds() {
        fetchDataAndUpdateList { calculateOdds() }
    }

    /**
     * Fetches data from the network and updates the list of bets.
     */
    fun fetchDataAndUpdateList(action: suspend () -> List<Bet>) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = action().sortedBy { it.sellIn }
            _betList.postValue(data)
        }
    }

    // for the scope of this exercise, we will just return a hardcoded list of items,
    // but imagine they are coming from a network call
    fun getItemsFromNetwork(): ArrayList<Bet> {
        val bets = arrayListOf<Bet>()
        bets.add(Bet("Winning team", 10, 20, "https://i.imgur.com/mx66SBD.jpeg"))
        bets.add(Bet("Total score", 2, 0, "https://i.imgur.com/VnPRqcv.jpeg"))
        bets.add(Bet("Player performance", 5, 7, "https://i.imgur.com/Urpc00H.jpeg"))
        bets.add(Bet("First goal scorer", 0, 80, "https://i.imgur.com/Wy94Tt7.jpeg"))
        bets.add(Bet("Number of fouls", 5, 49, "https://i.imgur.com/NMLpcKj.jpeg"))
        bets.add(Bet("Corner kicks", 3, 6, "https://i.imgur.com/TiJ8y5l.jpeg"))
        return bets
    }

    private fun calculateOdds(): List<Bet> {
        val bets = getItemsFromNetwork()
        for (i in bets.indices) {
            if (bets[i].type != "Total score" && bets[i].type != "Number of fouls") {
                if (bets[i].odds > 0) {
                    if (bets[i].type != "First goal scorer") {
                        bets[i].odds = bets[i].odds - 1
                    }
                }
            } else {
                if (bets[i].odds < 50) {
                    bets[i].odds = bets[i].odds + 1

                    if (bets[i].type == "Number of fouls") {
                        if (bets[i].sellIn < 11) {
                            if (bets[i].odds < 50) {
                                bets[i].odds = bets[i].odds + 1
                            }
                        }

                        if (bets[i].sellIn < 6) {
                            if (bets[i].odds < 50) {
                                bets[i].odds = bets[i].odds + 1
                            }
                        }
                    }
                }
            }

            if (bets[i].type != "First goal scorer") {
                bets[i].sellIn = bets[i].sellIn - 1
            }

            if (bets[i].sellIn < 0) {
                if (bets[i].type != "Total score") {
                    if (bets[i].type != "Number of fouls") {
                        if (bets[i].odds > 0) {
                            if (bets[i].type != "First goal scorer") {
                                bets[i].odds = bets[i].odds - 1
                            }
                        }
                    } else {
                        bets[i].odds = bets[i].odds - bets[i].odds
                    }
                } else {
                    if (bets[i].odds < 50) {
                        bets[i].odds = bets[i].odds + 1
                    }
                }
            }
        }
        return bets
    }
}