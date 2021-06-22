package com.malikali.kotlinrssfeeed

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.malikali.kotlinrssfeeed.Common.HttpDataHandler
import com.malikali.kotlinrssfeeed.adapter.FeedAdapter
import com.malikali.kotlinrssfeeed.data.RSSObject
import com.malikali.kotlinrssfeeed.databinding.ActivityMainBinding
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private val RSS_link = "https://rss.nytimes.com/services/xml/rss/nyt/Science.xml"
    private val RSS_api = "https://api.rss2json.com/v1/api.json?rss_url="
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolBar.title = "NEWS"
        setSupportActionBar(binding.toolBar)

        val linearLayoutManager = LinearLayoutManager(baseContext,LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.layoutManager = linearLayoutManager

        loadRSS()
    }

    private fun loadRSS() {

        val loadRSSAsync = object: AsyncTask<String, String, String>(){

            internal var mDialog = ProgressDialog(this@MainActivity)

            override fun onPreExecute() {
                mDialog.setMessage("Please wait")
                mDialog.show()
            }

            override fun onPostExecute(result: String?) {
                mDialog.dismiss()
                var rssObject:RSSObject
                rssObject = Gson().fromJson<RSSObject>(result,RSSObject::class.java)
                val adapter = FeedAdapter(baseContext,rssObject)
                binding.recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun doInBackground(vararg params: String?): String? {
                val result:String
                val http = HttpDataHandler()
                result = http.GetHttpDataHandler(params[0])
                return result
            }

        }

        val url_get_data = StringBuilder(RSS_api)
        url_get_data.append(RSS_link)
        loadRSSAsync.execute(url_get_data.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_refresh)
            loadRSS()
        return true

    }


}