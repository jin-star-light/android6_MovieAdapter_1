package com.example.studey01_day1_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_movie_list.*
import org.json.JSONException
/*
0. manifest에 Internet 퍼미션 적용 + Volley,GSON,Glide 라이브러리를 프로젝트에 추가하기. 완료
1. Volldy 라이브러리를 이용해서 TNDB로부터 현재 상영 중인 영화 정보를 받아오기. 완료
2. GSON 라이브러리를 이용해서 JSON->  Data Class로 변환하기. 완료
3. Glide 라이브러리를 이용해서 포스터 URL로부터 이미지를 받아서 ImageView에 적용하기.
 */
class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)




        //volley의 RequestsQueue 선언
        var requestQueue: RequestQueue = Volley.newRequestQueue(this)
        //GSON 객체 선언
        var gson: Gson = Gson()

        //API 주소 선언.
        val url="https://api.themoviedb.org/3/movie/now_playing?"+"api_key=ad0ef19d85d520853f9bc09e213b7e2d"+ "&language=ko-KR"+"&region=KR"

        //API를 호출한다.
        val request = JsonObjectRequest(Request.Method.GET,url,null,
        Response.Listener { //데이터가 정상적으로 호출됬을 때 처리되는 부분.

            response ->  try {
                //response(영화 JSON데이터)가 정상적으로 넘어온 경우 실행이된다.
            //받아온 Json 데이터를 Toast message로 출력할것이다.
            //Toast.makeText(this,response.toString(),Toast.LENGTH_SHORT).show()

                //response (영화 JSON 데이터) -> MovieList Data Class로 변형. (by GSON)
                val data: MovieList = gson.fromJson<MovieList>(response.toString(), MovieList::class.java)

                //adapter 선언한 함수들.
                val adapter =MovieAdapter(this, data.MovieList)
                movieRecycler.adapter = adapter//RecyclerView에 우리가 만든 MovieAdapter 셋팅함.

                val lm= LinearLayoutManager(this)// LinearLayoutManager을 선언하였다.
                movieRecycler.layoutManager =lm //RecyclerView에 LinearLayoutManager 셋팅하였다.

            } catch (e: JSONException){
                // response가 정상적으로 넘어오지 않은 경우 실행이 된다.
                Toast.makeText(this,e.localizedMessage,Toast.LENGTH_SHORT).show()
            }
        }, Response.ErrorListener { //데이터가 정상적으로 호출되지 않았을때 처리하는 부분.
                error ->  Toast.makeText(this,error.localizedMessage,Toast.LENGTH_SHORT).show()

            })

        requestQueue.add(request)
    }
}