package com.simplemvvm.app


import com.simplemvvm.app.adapter.ItemBetAdapter
import com.simplemvvm.app.base.BaseActivity
import com.simplemvvm.app.databinding.ActivityMainBinding
import com.simplemvvm.app.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main activity of the application, responsible for displaying the main screen UI.
 * This activity utilizes the AndroidEntryPoint annotation for dependency injection using Hilt.
 */
@AndroidEntryPoint
class MainActivity(override val layoutId: Int = R.layout.activity_main) :
    BaseActivity<ActivityMainBinding, MainViewModel>() {

    /**
     * Returns the class of the ViewModel associated with this activity.
     * @return The class of the ViewModel.
     */
    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    /**
     * Initializes the view by setting up data binding and adapters.
     */
    override fun initView() {
        super.initView()
        binding.viewModel = viewModel
        binding.adapter = ItemBetAdapter()
    }

    /**
     * Observes changes in the ViewModel.
     * Currently, there are no observations specified in this method.
     */
    override fun observeViewModel() {
        // Observations will be added here if needed.
    }
}