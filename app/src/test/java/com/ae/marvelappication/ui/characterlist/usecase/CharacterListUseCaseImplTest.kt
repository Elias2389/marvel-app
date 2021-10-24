package com.ae.marvelappication.ui.characterlist.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ae.marvelappication.ui.characterlist.repository.CharacterListRepository
import com.ae.marvelappication.util.characterMock
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class CharacterListUseCaseImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK(relaxed = true)
    private lateinit var mockRepository: CharacterListRepository

    private lateinit var useCase: CharacterListUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
        MockKAnnotations.init(this)

        useCase = CharacterListUseCaseImpl(mockRepository)
    }

    @Test
    fun `Get character list should be success`() = runBlockingTest {
        val expectedList = arrayListOf(characterMock.copy(id = 2222), characterMock.copy(id = 333))
        val mockOffset = 0
        val mockLimit = 10

        coEvery { mockRepository.getAllCharacters(mockOffset, mockLimit) } returns expectedList

        assertEquals(expectedList, useCase.invoke(mockOffset, mockLimit))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}