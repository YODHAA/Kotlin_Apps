package com.saurabh.yourworld.Model

class Results {
    var icon:String?=null
    var photos:Array<Photos>?=null
    var id:String?=null
    var place_id:String?=null
    var price_level:Int=0
    var rating:Double=0.0
    var reference:String?=null
    var scope:String?=null
    var type:Array<String>?=null
    var vicinity:String?=null
    var userRatingTotal:Long=0
    var name:String?=null
    var pluscode: PlusCode?=null
    var openingHours: OpeningHours?=null
    var bussinessStatus:String?=null
    var geometry:Geometry?=null

    var address_components:Array<AddessComponent>?=null
    var adr_address:String?=null
    var formatted_address:String?=null
    var formatted_phone_number:String?=null
    var international_phone_number:String?=null
    var url:String?=null
    var reviews:Array<Review>?=null
    var utc_offset:Int=0
    var website:String?=null

}