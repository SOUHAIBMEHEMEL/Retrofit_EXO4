package com.example.android.retrofit_exo4

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class listAdapter ( private var activity: Activity, private var items: ArrayList<Tache>): BaseAdapter() {



    private class ViewHolder(row: View?) {
        var engTxt: TextView? = null
        var frTxt: TextView? = null

        init {
            this.engTxt= row?.findViewById(R.id.title)
            this.frTxt= row?.findViewById(R.id.body)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.list_item, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var userDto = items[position]
        viewHolder.engTxt?.text = userDto.title
        viewHolder.frTxt?.text = userDto.body

        return view as View
    }

    override fun getItem(i: Int): Tache {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

}