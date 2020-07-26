package kr.co.tjoeun.daily10minute_20200719.adapters

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import kr.co.tjoeun.daily10minute_20200719.R
import kr.co.tjoeun.daily10minute_20200719.datas.Project
import kr.co.tjoeun.daily10minute_20200719.datas.Proof
import kr.co.tjoeun.daily10minute_20200719.utils.ServerUtil
import org.json.JSONObject
import java.text.SimpleDateFormat

class ProofAdapter(
    val mContext:Context,
    resId:Int,
    val mList:List<Proof>) : ArrayAdapter<Proof>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.proof_list_item, null)
        }

        val row = tempRow!!

        val userProfileImg = row.findViewById<ImageView>(R.id.userProfileImg)
        val userNickNameTxt = row.findViewById<TextView>(R.id.userNickNameTxt)
        val proofTimeTxt = row.findViewById<TextView>(R.id.proofTimeTxt)
        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)
        val proofImg = row.findViewById<ImageView>(R.id.proofImg)
        val likeBtn = row.findViewById<Button>(R.id.likeBtn)
        val rePlyBtn = row.findViewById<Button>(R.id.replyBtn)

        val data = mList[position]

        contentTxt.text = data.content

        userNickNameTxt.text = data.user.nickName
        Glide.with(mContext).load(data.user.profileImageList[0].imageUrl).into(userProfileImg)

//        인증 일시 : 2020년 6월 9일 오전 2시 8분 양식으로 출력
//        data의 proofTime 변수를 활용하자
//        String으로 변환 -> SimpleDateFormat의 format 기능 활용

        val sdf = SimpleDateFormat("yyyy년 M월 d일 a h시 m분")
        proofTimeTxt.text = sdf.format(data.proofTime.time)

//        그림이 있느냐 없느냐 구별 해야함 -> How? data의 이미지주소목록의 크기값 확인
        if (data.imageUrlList.size == 0) {
//            그림이 첨부가 안된 경우 -> 이미지뷰 숨김
            proofImg.visibility = View.GONE
        }
        else{
//            한장 이상의 그림이 첨부된 경우 -> 이미지뷰 표시
            proofImg.visibility = View.VISIBLE

//            맨 앞에 첨부된 그림을 실제로 표시
            Glide.with(mContext).load(data.imageUrlList[0]).into(proofImg)

        }

//        좋아요 / 답글 버튼의 문구 수정
        likeBtn.text = "좋아요 ${data.likeCount}개"
        rePlyBtn.text = "답글 ${data.replyCount}개"

//        좋아요 버튼 눌리는 이벤트
        likeBtn.setOnClickListener {

//            data.id 를 이용하면, 몇 번 인증글인지 알아낼 수 있다.

            ServerUtil.postRequestLikeProof(mContext, data.id, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {

                    val message = json.getString("message")

//                    어댑터에는 runOnUiThread 기능이 없다.
                    val myHandler = Handler(Looper.getMainLooper())

                    myHandler.post {
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                    }

                }


            })

        }

        return row
    }


}