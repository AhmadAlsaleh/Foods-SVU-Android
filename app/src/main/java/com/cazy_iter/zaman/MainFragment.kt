package com.cazy_iter.zaman

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

@SuppressLint("ValidFragment")
class MainFragment(val item: ItemsModel) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val mainTV = view.findViewById<TextView>(R.id.mainTV)
        val mainIV = view.findViewById<ImageView>(R.id.mainIV)

        mainTV.text = item.name
        try {
            Picasso.get()
                    .load(item.image)
                    .into(mainIV)
        } catch (err: Exception) {}

        return view
    }


}
