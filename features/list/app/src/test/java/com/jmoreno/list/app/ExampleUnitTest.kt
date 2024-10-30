package com.jmoreno.list.app

import org.junit.Test

import org.junit.Assert.*
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.test.verify.verify
import org.koin.core.module.dsl.viewModelOf
import com.jmoreno.list.ui.ListViewModel

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    val niaAppModule = module {
        includes(
            AppModule,
        )
        viewModelOf(::ListViewModel)
    }

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun checkKoinModule() {

        // Verify Koin configuration
        niaAppModule.verify()
    }
}