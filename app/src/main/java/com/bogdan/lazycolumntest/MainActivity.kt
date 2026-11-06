package com.bogdan.lazycolumntest

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bogdan.lazycolumntest.model.Cat
import com.bogdan.lazycolumntest.ui.components.CatItem
import com.bogdan.lazycolumntest.ui.theme.LazyColumnTestTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyColumnTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val db = Firebase.firestore
    var cats: MutableList<Cat> = mutableListOf()
    var whiskey : Cat by remember {mutableStateOf(Cat(
        id = "",
        name = "Whiskey",
        age = 7,
        breed = "Bengal",
        color = "Bengal pattern",
        sex = "male",
        weight = 5.0
    ))}
    db.collection("cats").add(whiskey).addOnSuccessListener {
        Log.i(TAG, "Successfully added cat to database with ID: ${it.id}")
    }.addOnFailureListener {
        Log.e(TAG, "Failed to add cat to database")
    }



    db.collection("cats").get().addOnSuccessListener {result ->
        for (tmpCat in result) {
            var cat: Cat = tmpCat.toObject(Cat::class.java)
            cats.add(cat)
            Log.i(TAG, "Cat: ${tmpCat.data}")
        }
    }
    Text(
        text = "Hello $name!",
        modifier = modifier
    )

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(cats.size) { index ->
            CatItem(cat = cats[index])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LazyColumnTestTheme {
        Greeting("Android")
    }
}