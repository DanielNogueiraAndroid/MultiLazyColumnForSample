package com.example.multilazycolumn.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.viewModel
import androidx.lifecycle.LiveData
import com.example.multilazycolumn.ui.BaseSample.ComplexId
import com.example.multilazycolumn.ui.BaseSample.MultiTypeLazyItem
import com.example.multilazycolumn.ui.BaseSample.MultiViewModelFactory
import com.example.multilazycolumn.ui.BaseSample.BaseMultiViewModel

import androidx.lifecycle.MutableLiveData
object MultiSample {

    class ExampleItemType1(override val complexId: ComplexId) :
        MultiTypeLazyItem {
        @Composable
        override fun getCompose() {
            composeLayoutString(complexId, getViewModelFactoryNew())
        }
    }

    @Composable
    fun composeLayoutString(
        complexId: ComplexId,
        factory: MultiViewModelFactory
    ) {
        val viewModel: ExampleMultiViewModel1 = viewModel(complexId.getKey(), factory)
        val state = viewModel.viewState.observeAsState("10")
        Divider()
        Text(
            text = "type task03", fontSize = 24.sp,
            color = Color.Green
        )
        Text(
            text = state.value.toString(), fontSize = 20.sp,
            color = Color.Green,
            modifier = Modifier.clickable(onClick = {
                viewModel.updateState()
            })
        )
    }

    // ComplexViewModel that requires an initial state parameter
    class ExampleMultiViewModel1(complexId: ComplexId) :
        BaseMultiViewModel<String>() {
        override val _viewState = MutableLiveData<String>()

        // Watch this LiveData within the @Composable function
        override val viewState = _viewState as LiveData<String>

        // set the current state to the initial state passed from the Factory
        private var currentState = 0
        private var complexId = complexId

        // Call this function within the @Composable function to update the state
        override fun updateState() {
            currentState += 1
            _viewState.value = "Current id ${complexId.getKey()} state: $currentState"
        }
    }

    class ExampleItemType2(override val complexId: ComplexId) :
        MultiTypeLazyItem {
        @Composable
        override fun getCompose() {
            composeLayoutInt(complexId, getViewModelFactoryNew())
        }
    }

    @Composable
    fun composeLayoutInt(
        complexId: ComplexId,
        factory: MultiViewModelFactory
    ) {
        val viewModel: MultiViewModelTwo = viewModel(complexId.getKey(), factory)
        val state = viewModel.viewState.observeAsState(10)
        Divider()
        Text(
            text = "type task04", fontSize = 24.sp,
            color = Color.Green
        )
        Text(
            text = state.value.toString(), fontSize = 20.sp,
            color = Color.Green,
            modifier = Modifier.clickable(onClick = {
                viewModel.updateState()
            })
        )
    }

    class MultiViewModelTwo(complexId: ComplexId) :
        BaseMultiViewModel<Int>() {
        override val _viewState = MutableLiveData<Int>()

        // Watch this LiveData within the @Composable function
        override val viewState = _viewState as LiveData<Int>

        // set the current state to the initial state passed from the Factory
        private var currentState = 0
        private var complexId = complexId

        // Call this function within the @Composable function to update the state
        override fun updateState() {
            currentState += 2
            _viewState.value = currentState
        }
    }
}