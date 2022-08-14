package com.example.myassignment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myassignment.adapter.CustomAdapter
import com.example.myassignment.model.ImageModel

class MainActivity : AppCompatActivity() {
    private val cat: MutableList<ImageModel> = ArrayList()
    private var dogImg: Uri? = null
    var listSize: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn)
        val userSize = findViewById<EditText>(R.id.edit_user_list_size)
        btn.setOnClickListener {
            if (userSize.text.isEmpty()){
                userSize.error ="Please enter size"
            }else{
                listSize = userSize.text.toString()
                openGalleryForImages()
            }

        }

    }


    private fun openGalleryForImages() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        resultForActivity.launch(intent)
    }


    private val resultForActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                if (it.data?.clipData != null) {
                    val count = it.data?.clipData?.itemCount

                    for (i in 0 until count!!) {
                        val catImg: Uri = it.data?.clipData?.getItemAt(0)!!.uri
                        dogImg = it.data?.clipData?.getItemAt(1)!!.uri
                        cat.clear()
                        for (j in 0 until listSize!!.toInt()) {
                            cat.add(ImageModel(j, catImg))
                        }
                    }
                }

                for (k in 1 until cat.size) {
                    val sequences = k * (k + 1) / 2
                    cat.map { imageModel ->
                        if (imageModel.id == sequences) {
                            cat[imageModel.id] = ImageModel(imageModel.id, dogImg!!)
                        }
                    }
                }
            }

            val recyclerView = findViewById<RecyclerView>(R.id.rv_image)
            recyclerView.layoutManager = GridLayoutManager(this, 5)
            val adapter = CustomAdapter()
            recyclerView.adapter = adapter
            adapter.setData(cat)
        }
}