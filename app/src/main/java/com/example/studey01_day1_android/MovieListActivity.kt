package com.example.studey01_day1_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {

    //영화 정보를 담고 있는 ArryList를 만들것이다.
    val movieList : ArrayList<Movie> = arrayListOf(
        Movie("테넷", 10.234, "시간의 흐름","2020/08/26", R.drawable.m2),
        Movie("소년 시절의 너",50.222, "세상을 지켜줘","2020/08/27",R.drawable.m4),
        Movie("덩케르크 이스케이프",30.234,"역사에 기록되지 않은 숨은이야기","2020/08/28",R.drawable.m3),
        Movie("짱구는 못말려",60.455,"짱구의 이야기","2020/08/29",R.drawable.m1)
   )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val adapter =MovieAdapter(this, movieList)
        movieRecycler.adapter = adapter//RecyclerView에 우리가 만든 MovieAdapter 셋팅함.

        val lm= LinearLayoutManager(this)// LinearLayoutManager을 선언하였다.
        movieRecycler.layoutManager =lm //RecyclerView에 LinearLayoutManager 셋팅하였다.
    }
}