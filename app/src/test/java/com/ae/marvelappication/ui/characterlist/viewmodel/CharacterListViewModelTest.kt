package com.ae.marvelappication.ui.characterlist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ae.marvelappication.common.reponse.Resource
import com.ae.marvelappication.common.reponse.ResponseHandler
import com.ae.marvelappication.dto.ResultsItem
import com.ae.marvelappication.ui.characterlist.usecase.CharacterListUseCase
import com.ae.marvelappication.util.characterMock
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
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
class CharacterListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK(relaxed = true)
    private lateinit var mockUseCase: CharacterListUseCase

    @MockK(relaxed = true)
    private lateinit var mockResponseHandler: ResponseHandler

    @MockK(relaxed = true)
    private lateinit var mockEventObserver: Observer<Resource<List<ResultsItem>>>

    private lateinit var viewModel: CharacterListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
        MockKAnnotations.init(this)
        setupViewModel()
    }

    @Test
    fun `Get character list should be success`() = runBlockingTest {
        val expectedList = arrayListOf(characterMock.copy(id = 2222), characterMock.copy(id = 333))
        val expectedResponse = mockResponseHandler.handleSuccess(expectedList)

        coEvery { mockUseCase.invoke(any(), any()) } returns expectedList

        viewModel.getAllCharactersByPaging()

        coVerify { mockEventObserver.onChanged(eq(expectedResponse)) }
    }

    @Test
    fun `Get character list should be success and return list`() = runBlockingTest {
        val expectedList = arrayListOf(characterMock.copy(id = 2222), characterMock.copy(id = 333))
        val expectedResponse = mockResponseHandler.handleSuccess(expectedList)

        coEvery { mockUseCase.invoke(any(), any()) } returns expectedList

        viewModel.getAllCharactersByPaging()
        val response = viewModel.getEvents.value

        assertEquals(expectedResponse, response)
    }

    @Test
    fun `Get character list should be fail`() = runBlockingTest {
        val expectedException = Exception("")
        val expectedResponse = mockResponseHandler.handleException<List<ResultsItem>>(expectedException)

        coEvery { mockUseCase.invoke(any(), any()) } throws  expectedException

        viewModel.getAllCharactersByPaging()

        coVerify { mockEventObserver.onChanged(eq(expectedResponse)) }
    }

    @Test
    fun `Get character list should be fail and return error`() = runBlockingTest {
        val expectedException = Exception("")
        val expectedResponse = mockResponseHandler.handleException<List<ResultsItem>>(expectedException)

        coEvery { mockUseCase.invoke(any(), any()) } throws  expectedException

        viewModel.getAllCharactersByPaging()
        val errorResponse = viewModel.getEvents.value

        assertEquals(expectedResponse, errorResponse)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    private fun setupViewModel() {
        viewModel = CharacterListViewModel(
            mockUseCase,
            mockResponseHandler
        ).apply { getEvents.observeForever(mockEventObserver) }
    }
}