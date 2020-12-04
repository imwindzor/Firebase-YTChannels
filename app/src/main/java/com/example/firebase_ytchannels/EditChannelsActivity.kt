package com.example.firebase_ytchannels

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.firebase_ytchannels.handlers.ChannelsHandler
import com.example.firebase_ytchannels.models.Channels

class EditChannelsActivity : AppCompatActivity() {
    lateinit var titleEditText: EditText
    lateinit var linkEditText: EditText
    lateinit var rankEditText: EditText
    lateinit var reasonEditText: EditText
    lateinit var confirmButton: Button
    lateinit var channelHandler: ChannelsHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var channels: Channels = intent.getSerializableExtra("channel") as Channels

        titleEditText = findViewById(R.id.titleET)
        linkEditText = findViewById(R.id.linkET)
        rankEditText = findViewById(R.id.rankET)
        reasonEditText = findViewById(R.id.reasonET)
        confirmButton = findViewById(R.id.channelEditBtn)
        channelHandler = ChannelsHandler()

        titleEditText.setText(channels.title)
        linkEditText.setText(channels.title)
        rankEditText.setText(channels.title.toString())
        reasonEditText.setText(channels.title)

        confirmButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val link = linkEditText.text.toString()
            val rank = rankEditText.text.toString().toInt()
            val reason = reasonEditText.text.toString()

            val channel = Channels(id = channels.id, title = title, link = link, rank = rank, reason = reason)//passing values to the channels model

            if(titleEditText.toString().isNotEmpty() && linkEditText.toString().isNotEmpty() && rankEditText.toString().isNotEmpty()
                && reasonEditText.toString().isNotEmpty())
            {
                channelHandler.update(channel)
                Toast.makeText(this, "Edited Successfully", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
