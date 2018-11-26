package vn.com.tma.vo_ngoc_hanh.mychat.base.db.surface_account

/*
* This class can be useless currently
* */

open class RemoteSurfaceAccount{
    var uid:String=""
    var fullName:String=""
    var iconUrl:String=""
    var email:String=""
    var gender:Boolean=true

    constructor()

    constructor(uid:String, fullName:String, iconUrl:String, email:String, gender:Boolean){
        this.uid = uid
        this.fullName = fullName
        this.iconUrl = iconUrl
        this.email = email
        this.gender = gender
    }

    fun toMap() : Map<String,Any>{
        val map = HashMap<String, Any>()

        map.put("uid", uid)
        map.put("full_name", fullName)
        map.put("icon_url", iconUrl)
        map.put("email", email)
        map.put("gender", gender)

        return map
    }
}