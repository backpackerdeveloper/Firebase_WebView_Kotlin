package com.shubhamtripz.bijlibillnewtest

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.wvWebsite)
        webView.settings.javaScriptEnabled = true
        // Initialize Firebase and retrieve URL from Firebase
        FirebaseApp.initializeApp(this)
        val database = FirebaseDatabase.getInstance().reference.child("url")

        // Set up web view client
        webView.webViewClient = WebViewClient()

        // Load URL from Firebase in web view
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val url = dataSnapshot.value.toString()
                webView.loadUrl(url)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
            }
        })
    }

}

