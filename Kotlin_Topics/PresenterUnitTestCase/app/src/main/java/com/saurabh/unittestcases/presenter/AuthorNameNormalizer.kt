package com.saurabh.unittestcases.presenter

class AuthorNameNormalizer {

    init {
        print("INIT")
    }

    fun normalize(name: String): String {
        val splitname = name.trim().split(" ")
        if (splitname.count() == 1) {
            return name
        }
        var middlename = ""
        if (splitname.count() > 2) {
            middlename = splitname[1]
        }

        var middleInitial = ""
        if (!middlename.isEmpty()) {
            middleInitial = "" + middlename.first() + "."
        }
        return "${splitname.last()} , ${splitname.first()} ${middleInitial}"
    }


}