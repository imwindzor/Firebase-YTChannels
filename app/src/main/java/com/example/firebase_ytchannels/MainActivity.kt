package com.example.firebase_ytchannels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.firebase_ytchannels.handlers.ChannelsHandler
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.example.firebase_ytchannels.models.Channels as Channels

class MainActivity : AppCompatActivity() {

    lateinit var channelList: ArrayList<Channels>
    lateinit var channelHandler: ChannelsHandler
    lateinit var channelListView: ListView
    lateinit var channel: Channels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        channelList = ArrayList()
        channelHandler = ChannelsHandler()
        channelListView = findViewById(R.id.ytChannelLists)
        registerForContextMenu(channelListView)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when(item.itemId) {
            R.id.edit_channel -> {
                channel = channelList[info.position] // position of array
                var intent = Intent(this,EditChannelsActivity::class.java)
                intent.putExtra("channel", channel)
                startActivity(intent)
                true
            }
            R.id.delete_channel -> {
                if(channelHandler.delete(channelList[info.position])){
                    Toast.makeText(applicationContext, "Channel deleted successfully", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()

        channelHandler.channelsRef.orderByChild("rank").addValueEventListener(object:
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                channelList.clear()
                snapshot.children.forEach{
                    it -> val channel = it.getValue(Channels::class.java)
                    channelList.add(channel!!)
                }
                val adapter = ArrayAdapter<Channels>(applicationContext, android.R.layout.simple_list_item_1, channelList)
                channelListView.adapter = adapter
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_channel -> {
                startActivity(Intent(this, AddChannelsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
