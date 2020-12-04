package com.example.firebase_ytchannels.handlers

import com.example.firebase_ytchannels.models.Channels
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ChannelsHandler {
    var database: FirebaseDatabase
    var channelsRef: DatabaseReference

    init {
        database = FirebaseDatabase.getInstance()
        channelsRef = database.getReference("channels")
    }

    fun create(channel: Channels): Boolean{
        val id = channelsRef.push().key
        channel.id = id

        channelsRef.child(id!!).setValue(channel)//update firebase
        return true
    }

    fun update(channel: Channels): Boolean{
        channelsRef.child(channel.id!!).setValue(channel)
        return true
    }

    fun delete(channel: Channels): Boolean{
        channelsRef.child(channel.id!!).removeValue()
        return true
    }
}