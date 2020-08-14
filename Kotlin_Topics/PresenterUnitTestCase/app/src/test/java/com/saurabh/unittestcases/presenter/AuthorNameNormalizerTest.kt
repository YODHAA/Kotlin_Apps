package com.saurabh.unittestcases.presenter

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class AuthorNameNormalizerTest {

    private lateinit var normalizer: AuthorNameNormalizer

    @Before
    fun setup() {
        normalizer = AuthorNameNormalizer()
    }


    @Test
    fun `return empty string when empty`() {
        assertThat(
            normalizer.normalize("")
            , equalTo("")
        )
    }

    @Test
    fun `return single word name `() {
        assertThat(
            normalizer.normalize("Saurabh"),
            equalTo("Saurabh")
        )
    }

    @Test
    fun `return revert word name `() {
        assertThat(
            normalizer.normalize("Saurabh Singh"),
            equalTo("Singh , Saurabh")
        )
    }

    @Test
    fun `remove leading and trailing whitespaces`() {
        assertThat(
            normalizer.normalize(" Saurabh Singh "),
            equalTo("Singh , Saurabh")
        )
    }

    @Test
    fun `initializes middle name`() {
        assertThat(
            normalizer.normalize("Saurabh Master Singh"),
            equalTo("Singh , Saurabh M.")
        )
    }

}