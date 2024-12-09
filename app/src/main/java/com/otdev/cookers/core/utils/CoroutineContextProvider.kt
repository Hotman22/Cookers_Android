package com.otdev.cookers.core.utils

import kotlin.coroutines.CoroutineContext

/**
 * Provide [CoroutineContext]
 * Used in order to inject dispatcher
 * Easy to use for testing purpose
 */
interface CoroutineContextProvider {

    /**
     * Main thread on Android, interact with the UI and perform light work:
     *  - Call UI functions
     *  - Updating LiveData
     */
    val ui: CoroutineContext

    /**
     * Optimized for disk and network IO off the main thread:
     *  - Database
     *  - Reading/writing files
     *  - Networking
     */
    val io: CoroutineContext

    /**
     * Optimized for CPU intensive work off the main thread:
     *  - Sorting a list
     *  - Parsing JSON
     *  - DiffUtils
     */
    val default: CoroutineContext
}