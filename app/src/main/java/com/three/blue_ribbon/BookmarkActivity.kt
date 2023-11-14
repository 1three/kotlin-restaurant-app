package com.three.blue_ribbon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BookmarkActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val contentModels = mutableListOf<ContentModel>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        auth = Firebase.auth

        // DB에서 데이터 가져오기
        val database = Firebase.database
        val bookmarkRef = database.getReference("bookmarkRef")

        val recyclerView = findViewById<RecyclerView>(R.id.bookmarkRV)
        val rvAdapter = RVAapter(baseContext, contentModels)
        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        bookmarkRef.child(auth.currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // 데이터 가져오기 성공 시
                    for (dataModel in dataSnapshot.children) {
                        contentModels.add(dataModel.getValue(ContentModel::class.java)!!)
                    }
                    // 어댑터 동기화
                    rvAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    // 데이터 가져오기 실패 시
                    Log.e("Bookmark Error", "DB 에러")
                }
            })

    }
}