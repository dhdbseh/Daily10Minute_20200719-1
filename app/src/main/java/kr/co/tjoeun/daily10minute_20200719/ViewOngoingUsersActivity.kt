package kr.co.tjoeun.daily10minute_20200719

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_ongoing_users.*
import kr.co.tjoeun.daily10minute_20200719.datas.Project
import kr.co.tjoeun.daily10minute_20200719.utils.ServerUtil
import org.json.JSONObject


class ViewOngoingUsersActivity : BaseActivity() {

    var mProjectId = 0

//    서버에서 받아온 프로젝ㅇ트ㅡ 정보
    lateinit var mProject : Project

//    사용자 정보를 저장할 목록
//     val mOngoingUserList = ArrayList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_ongoing_users)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mProjectId = intent.getIntExtra("projectId", 0)

        getOngoingUsersFromServer()

    }

    //    진행중인 사람 명단 + 상세정보 물러오기
    fun getOngoingUsersFromServer() {

        ServerUtil.getRequestProjectDetailWithUser(
            mContext,
            mProjectId,
            object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {

                    val data = json.getJSONObject("data")
                    val projectObj = data.getJSONObject("project")

                    mProject = Project.getProjectFromJson(projectObj)

//            projectObj 내부에 ongoing_users 배열을 활용해서 전달

//            프로젝트 정보 UI에 반영
                    runOnUiThread {
                        titleTxt.text = mProject.title
                        userCountTxt.text = "참여중 인원 : ${mProject.ongoingUserCount}명"

                    }

                }

            })

    }

}