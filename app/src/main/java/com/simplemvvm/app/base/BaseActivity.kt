package com.simplemvvm.app.base

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Base class for activities that use data binding and ViewModel.
 * @param B The type of ViewDataBinding.
 * @param VM The type of ViewModel.
 */
abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

    /** The ViewDataBinding instance associated with this activity. */
    lateinit var binding: B

    /** The ViewModel instance associated with this activity. */
    lateinit var viewModel: VM

    /** Lazy-initialized ViewModelProvider.Factory instance for creating ViewModels. */
    private val viewModelFactory by lazy { ViewModelProvider.AndroidViewModelFactory.getInstance(application) }

    /** The layout resource ID for the activity's layout. */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Returns the ViewModel class associated with this activity.
     * This method must be implemented by subclasses to provide the ViewModel class.
     * @return The ViewModel class.
     */
    abstract fun getViewModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize ViewModel
        viewModel = ViewModelProvider(this, viewModelFactory)[getViewModelClass()]
        // Inflate layout and bind data
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        // Initialize view components
        initView()
        // Observe ViewModel changes
        observeViewModel()
        // Handle back button press
        onBackPressedDispatcher.addCallback(
            this /* lifecycle owner */,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Back is pressed... Finishing the activity
                    finish()
                }
            })
    }

    /**
     * Initializes the view components.
     * Subclasses can override this method to perform additional initialization.
     */
    open fun initView() {}

    /**
     * Observes changes in the ViewModel.
     * Subclasses must override this method to observe ViewModel changes.
     */
    abstract fun observeViewModel()
}
