package com.example.projectweek8.Model

open class Data {
    var name :String = ""
    var description :String  = ""

    fun setname(names :String) {
            this.name = names
    }
    fun getname():String{
            return name
    }
        fun setdesc(desc :String) {
                this.description = desc
        }
        fun getdesc():String{
                return description
        }


}


