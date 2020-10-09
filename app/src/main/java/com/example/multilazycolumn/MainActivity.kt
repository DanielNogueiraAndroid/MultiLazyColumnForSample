package com.example.multilazycolumn

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.example.multilazycolumn.ui.MultiLazyColumnForSampleTheme
import com.example.multilazycolumn.ui.BaseSample.MultiTypeLazyItem
import com.example.multilazycolumn.ui.BaseSample.ComplexIdImp
import com.example.multilazycolumn.ui.MultiSample

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiLazyColumnForSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                }
            }
        }
    }
}

private val list = listOf<MultiTypeLazyItem>(
    MultiSample.ExampleItemType1(
        ComplexIdImp("1")
    ),
    MultiSample.ExampleItemType2(
        ComplexIdImp("2")
    )
)

@Composable
fun LazyColumnForMultiDemo() {
    LazyColumnFor(items = list,
        modifier = Modifier,
        itemContent = { item ->
            Log.d("Compose", "This get rendered $item")
            item.getCompose()
        })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MultiLazyColumnForSampleTheme {
        LazyColumnForMultiDemo()
    }
}

