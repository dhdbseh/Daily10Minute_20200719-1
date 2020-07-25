package kr.co.tjoeun.daily10minute_20200719.datas

import org.json.JSONObject

class Project {

    var id = 0
    var title = ""
    var imageUrl = ""
    var description = ""

    var proofMethod = ""
    var ongoingUserCount = 0

    companion object{

        fun getProjectFromJson(json: JSONObject) : Project {

            val p = Project()

//            json에 들어있는 데이터들을 이용해서 -> p의 데이터로 대입
            p.id = json.getInt("id")
            p.title = json.getString("title")
            p.imageUrl = json.getString("img_url")
            p.description = json.getString("description")

            p.proofMethod = json.getString("proof_method")
            p.ongoingUserCount = json.getInt("ongoing_users_count")

//            완성된 p를 리턴
            return p

        }
    }

}