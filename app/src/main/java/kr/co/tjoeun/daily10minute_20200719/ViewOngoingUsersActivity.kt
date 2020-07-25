package kr.co.tjoeun.daily10minute_20200719

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ViewOngoingUsersActivity : BaseActivity() {
    override fun setupEvents() {

    }

    override fun setValues() {

        mProjectId = intent.getIntExtra("projectId", 0)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_ongoing_users)
        setupEvents()
        setValues()
    }
}