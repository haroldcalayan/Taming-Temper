package com.haroldcalayan.tamingtemper.common

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.plus

/**
 * This scope is used until app class installs a proper scope with proper error handling etc. Theoretically this scope should not even be used, but our start up
 * sequence is so weird, that it might who knows. I'm wrapping the exception to add some context to the cause.
 */
@DelicateCoroutinesApi
private val preStartupAppScope = GlobalScope + CoroutineExceptionHandler { _, th ->
    Thread.currentThread().uncaughtExceptionHandler?.uncaughtException(Thread.currentThread(), RuntimeException("error very early in app startup", th))
}

/**
 * A replacement for [GlobalScope] that comes with exception handler (see app's startup code which the initializer's value) and uses a SupervisorJob to prevent
 * errors in one usage of this scope from cancelling others.
 */
@OptIn(DelicateCoroutinesApi::class)
var appScope: CoroutineScope = preStartupAppScope + SupervisorJob()