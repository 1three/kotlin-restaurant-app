package com.three.blue_ribbon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ViewActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        auth = Firebase.auth

        val webView = findViewById<WebView>(R.id.webView)
        val url = intent.getStringExtra("url").toString()
        val imgUrl = intent.getStringExtra("imgUrl").toString()
        val title = intent.getStringExtra("title").toString()

        // 웹 뷰 보이기
        webView.loadUrl(url)

        // DB에 저장하기 (장소에 대한 저장 버튼 클릭 시)
        val database = Firebase.database
        val bookmarkRef = database.getReference("bookmarkRef")

        val saveBtn: Button = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener {
            bookmarkRef
                .child(auth.currentUser!!.uid)
                .push()
                .setValue(ContentModel(url, imgUrl, title))

            Toast.makeText(baseContext, "${title}를 북마크에 저장하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}