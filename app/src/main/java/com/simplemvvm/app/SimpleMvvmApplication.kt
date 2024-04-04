package com.simplemvvm.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * This class represents the application class for the Scanner app.
 * Extends [Application] and is annotated with [HiltAndroidApp] for Dagger Hilt integration.
 */
@HiltAndroidApp
class SimpleMvvmApplication : Application()