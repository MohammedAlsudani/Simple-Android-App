package com.simplemvvm.app.viewModel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.simplemvvm.app.model.Bet
import com.simplemvvm.app.util.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * Test class for [MainViewModel] which contains unit tests for various functionalities.
 * This class utilizes [InstantTaskExecutorRule] for testing LiveData objects, and [StandardTestDispatcher]
 * for managing coroutines in a testing environment.
 *
 * @see MainViewModel
 * @see InstantTaskExecutorRule
 * @see StandardTestDispatcher
 */
@ExperimentalCoroutinesApi
class MainViewModelTest {

    /**
     * Rule to force LiveData to execute synchronously for testing purposes.
     */
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    /**
     * Dispatcher for testing coroutines in a controlled environment.
     */
    @ExperimentalCoroutinesApi
    private val testCoroutineDispatcher = StandardTestDispatcher()

    /**
     * Mock instance of [MainViewModel] to be used in tests.
     */
    private lateinit var mockViewModel: MainViewModel

    /**
     * Setup method executed before each test case.
     * It sets up the [Dispatchers.Main] to the test [StandardTestDispatcher] and initializes the [mockViewModel].
     */
    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
        mockViewModel = MainViewModel()
    }

    /**
     * Test method to verify the behavior of fetching data in [MainViewModel].
     * It verifies that the fetched bet matches the expected bet.
     */
    @Test
    fun onFetch() = runTest {
        val bet = Bet("Winning team", 10, 20, "https://i.imgur.com/mx66SBD.jpeg")
        val response = mockViewModel.betList.getOrAwaitValue(1000).last()
        Assert.assertEquals(bet.type, response.type)
    }

    /**
     * Test method to verify the behavior of updating odds in [MainViewModel].
     * It verifies that the updated odds match the expected odds for the first bet in the list.
     */
    @Test
    fun onUpdateOdds() = runTest {
        val bet = Bet("First goal scorer", 0, 80, "https://i.imgur.com/Wy94Tt7.jpeg")
        val response = mockViewModel.betList.getOrAwaitValue(1000).first()
        Assert.assertEquals(bet.type, response.type)
    }

    /**
     * Teardown method executed after each test case.
     * It resets the [Dispatchers.Main] to its original state.
     */
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}