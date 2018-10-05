package vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.firebase

open class AccountRemote {
    var uid:String=""
    var fullName:String=""
    var email:String=""
    var birthDate:Long=0
    var gender:Boolean=true

    constructor()

    constructor(uid:String, fullName:String, email:String, birthDate:Long, gender:Boolean){
        this.uid = uid
        this.fullName = fullName
        this.email = email
        this.birthDate = birthDate
        this.gender = gender
    }

    fun toMap() : Map<String,Any>{
        val map = HashMap<String, Any>()

        map.put("uid", uid)
        map.put("fullName", fullName)
        map.put("email", email)
        map.put("birthDate", birthDate)
        map.put("gender", gender)

        return map
    }
}