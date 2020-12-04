package com.example.firebase_ytchannels.models

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties

class Channels (var id: String? = "" , var title: String? = "", var link: String? = "", var rank: Int? = 0, var reason: String? = "")
    : Serializable {
    override fun toString(): String {
        return  "Youtube Channel Name: $title \n\n"+
                "Channel: $link \n\n"+
                "Rank: $rank \n\n"+
                "Reason: $reason\n\n"
    }
}