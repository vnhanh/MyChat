package vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.firebase

open class AccountRemote {
    var uid:String=""
    var fullName:String=""
    var iconUrl:String=""
    var email:String=""
    var birthDate:Long=0
    var gender:Boolean=true
    var connections:String=""

    constructor()

    constructor(uid:String, fullName:String, iconUrl:String, email:String, birthDate:Long, gender:Boolean, connections:String){
        this.uid = uid
        this.fullName = fullName
        this.iconUrl = iconUrl
        this.email = email
        this.birthDate = birthDate
        this.gender = gender
        this.connections = connections
    }

    fun toMap() : Map<String,Any>{
        val map = HashMap<String, Any>()

        map.put("uid", uid)
        map.put("full_name", fullName)
        map.put("icon_url", iconUrl)
        map.put("email", email)
        map.put("birth_date", birthDate)
        map.put("gender", gender)
        map.put("connections", connections)

        return map
    }
}