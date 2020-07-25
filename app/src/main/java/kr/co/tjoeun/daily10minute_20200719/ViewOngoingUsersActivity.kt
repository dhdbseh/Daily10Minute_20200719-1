package kr.co.tjoeun.daily10minute_20200719

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.tjoeun.daily10minute_20200719.utils.ServerUtil


var mProjectId

class ViewOngoingUsersActivity : BaseActivity() {

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
            object : ServerUtil.JsonResponseHandler)
    }

}