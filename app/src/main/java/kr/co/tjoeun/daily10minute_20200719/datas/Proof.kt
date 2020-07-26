package kr.co.tjoeun.daily10minute_20200719.datas

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Proof {

    var id = 0
    var content = ""
    val proofTime = Calendar.getInstance() //인증 일시를 분석해서 세팅 예정

    val imageUrlList = ArrayList<String>() //이미지의 주소만 따서 목록으로 저장

    lateinit var user : User // 인증을 올린 사람에 대한 정보

    companion object {

//        적당한 json을 넣으면 -> proof로 변환해주는 기능

        fun getProofFromJson(json: JSONObject) : Proof{
            val p = Proof()

//            재료로 들어오는 json을 가지고 -> p의 멤버 변수들의 내용을 채워주자.
            p.id = json.getInt("id")
            p.content = json.getString("content")

//            서버에서는 인증일시를 String으로 알려줌.
//            앱에서는 인증일시를 Calendar로 저장해야함.
//            String -> Calendar 변환. SimpleDataString

            val proofTimeString = json.getString("proof_time")

//            서버에서 내려주는 양식을 읽어올 SimpleDateFormat 생성
//            2020-06-09 02:53:34 양식 분석
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

//            sdf를 이용하서 -> 캘린더 변수에 시간 대입
            p.proofTime.time = sdf.parse(proofTimeString)

            return p
        }

    }

}