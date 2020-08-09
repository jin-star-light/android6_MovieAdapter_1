package com.example.studey01_day1_android

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

/*
내가 지금부터 여기 MovieAdapter 파일에 만들것들 .
1. Movie Data Class
2. RecyclerView Adapter
 */
data class Movie(
    //영화 정보를 담고 있는 데이터클레스에 들아갈 항목들
    //이것이 바로 1. Movie Data Class 이다.
   val title : String, //1. 영화제목
   val popularity : Double, //2. 인기도
   val description : String, //3. 설명
   val opendate : String, //4. 개봉일
   val posterurl : Int // 5. 포스터 url


)

//RecyclerView Adapter
class MovieAdapter(val context: Context, val movieList: ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        //쌜 레이아웃을 불러오는 역할
        val view = LayoutInflater.from(context).inflate(R.layout.cell,parent, false)
        return Holder(view)
    }


    override fun getItemCount(): Int {
        //샐 갯수를 설정하는 역할, 샐 갯수를 반환하는 함수.
        return movieList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
         //각 셀에 맞는 정보를 넣는 역할을 한다.
        holder.bind(movieList[position])
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //셀에 해당하는 부분을 홀더라고 적는다. 하지만 이 것은 셀이다. ㅣ
        //셀의 구성요소를 불러오는 역할을 한다.
        val imgPoster = itemView.findViewById<ImageView>(R.id.imgPoster)
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvPopularity = itemView.findViewById<TextView>(R.id.tvPopularity)
        val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        val tvOpenDate = itemView.findViewById<TextView>(R.id.tvOpenDate)
        val container = itemView.findViewById<ConstraintLayout>(R.id.container)// 
        //Holder의 역할 : ArrayList의 데이터를 셀의 레이아웃에 알맞게 넣어주는 역할.


        fun bind(movie: Movie){
            imgPoster.setImageResource(movie.posterurl)
            tvTitle.text = movie.title
            tvPopularity.text = "인기도 : " + movie.popularity
            tvDescription.text = "설명 : " + movie.description
            tvOpenDate.text = "개봉일 : " + movie.opendate

            container.setOnClickListener {//셀을 클릭했을 때
                Toast.makeText(context,movie.title, Toast.LENGTH_LONG).show() //영화 제목을 토스트 메시지로 띄운다.
            }
        }

    }
}