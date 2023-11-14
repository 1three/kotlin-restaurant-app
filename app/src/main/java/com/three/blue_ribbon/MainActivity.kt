package com.three.blue_ribbon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val items = mutableListOf<ContentModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 북마크 버튼 클릭 시, BookmarkActivity로 이동
        val bookmarkBtn: Button = findViewById(R.id.bookmarkBtn);
        bookmarkBtn.setOnClickListener {
            val intent = Intent(this, BookmarkActivity::class.java)
            startActivity(intent)
        }

        // 데이터 넣기
        items.add(
            ContentModel(
                "https://www.siksinhot.com/P/315233",
                "https://img.siksinhot.com/place/1459317297288889.jpg?w=540&h=436&c=Y",
                "R고기"
            )
        )
        items.add(
            ContentModel(
                "https://www.siksinhot.com/P/1206606",
                "https://img.siksinhot.com/place/1582875186782460.jpg?w=508&h=412&c=Y",
                "스시스미레"
            )
        )
        items.add(
            ContentModel(
                "https://www.siksinhot.com/P/365171",
                "https://img.siksinhot.com/place/1452426217726720.jpg?w=508&h=412&c=Y",
                "코지마"
            )
        )
        items.add(
            ContentModel(
                "https://www.siksinhot.com/P/366545",
                "https://img.siksinhot.com/place/1455126246233468.jpg?w=508&h=412&c=Y",
                "보트르메종"
            )
        )
        items.add(
            ContentModel(
                "https://www.siksinhot.com/P/1067913",
                "https://img.siksinhot.com/place/1676004161489392.jpg?w=508&h=412&c=Y",
                "덕후선생"
            )
        )
        items.add(
            ContentModel(
                "https://www.siksinhot.com/P/320351",
                "https://img.siksinhot.com/place/1554870952814204.jpg?w=508&h=412&c=Y",
                "삼성원조양곱창"
            )
        )
        items.add(
            ContentModel(
                "https://www.siksinhot.com/P/266878",
                "https://img.siksinhot.com/place/1539251025173097.JPG?w=560&h=448&c=Y",
                "삼거리먼지막 순대국"
            )
        )
        items.add(
            ContentModel(
                "https://www.siksinhot.com/P/1078708",
                "https://img.siksinhot.com/place/1676004432741396.jpg?w=560&h=448&c=Y",
                "월래순교자관"
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.mainRV)
        val rvAdapter = RVAapter(baseContext, items)
        recyclerView.adapter = rvAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        // RV 개별 아이템 클릭 이벤트 처리 (3)
        rvAdapter.itemClick = object :RVAapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(baseContext, ViewActivity::class.java)
                intent.putExtra("url", items[position].url)
                intent.putExtra("imgUrl", items[position].imgUrl)
                intent.putExtra("title", items[position].title)
                startActivity(intent)
            }
        }
    }
}