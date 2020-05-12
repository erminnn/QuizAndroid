package com.example.kviz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.multichoice_item.view.*


class MultiChoiceAdapter(private val multiChoiceAnswers : List<MultiChoiceItem>) : RecyclerView.Adapter<MultiChoiceAdapter.MultiChoiceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiChoiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.multichoice_item,parent,false)
        return MultiChoiceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return multiChoiceAnswers.size
    }

    override fun onBindViewHolder(holder: MultiChoiceViewHolder, position: Int) {
        val currentItem = multiChoiceAnswers[position]
        holder.multiChoiceItem.text = currentItem.answer
    }

    class MultiChoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val multiChoiceItem : CheckBox = itemView.cbAnswer
    }
}