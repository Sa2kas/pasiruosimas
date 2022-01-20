package com.example.egzaminui.person

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.egzaminui.R
import kotlinx.android.synthetic.main.person_item.view.*

class ListAdapter() : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var personList = emptyList<Person>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent, false))
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = personList[position]
        holder.itemView.textId.text = currentItem.id.toString()
        holder.itemView.textName.text = currentItem.name
        holder.itemView.textSurname.text = currentItem.surname
        holder.itemView.textNumber.text = currentItem.phoneNumber


    }

    fun setData(person: List<Person>){
        this.personList = person
        notifyDataSetChanged()
    }
}