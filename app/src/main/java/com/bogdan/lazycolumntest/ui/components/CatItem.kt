package com.bogdan.lazycolumntest.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import com.bogdan.lazycolumntest.model.Cat
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CatItem(cat: Cat) {
    Column(Modifier.fillMaxWidth()) {
        Card(Modifier.padding(4.dp)){
            Text(text = cat.name)
            Text(text = cat.age.toString())
        }
    }
}