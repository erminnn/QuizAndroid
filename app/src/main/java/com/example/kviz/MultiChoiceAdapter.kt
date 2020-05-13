package com.example.kviz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.kviz.models.MultiChoiceItem
import kotlinx.android.synthetic.main.multichoice_item.view.*


class MultiChoiceAdapter(private val multiChoiceAnswers : List<MultiChoiceItem>,private val clickListener: (MultiChoiceItem) -> Unit) : RecyclerView.Adapter<MultiChoiceAdapter.MultiChoiceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiChoiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.multichoice_item,parent,false)
        return MultiChoiceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return multiChoiceAnswers.size
    }

    override fun onBindViewHolder(holder: MultiChoiceViewHolder, position: Int) {
        //val currentItem = multiChoiceAnswers[position]
       // holder.multiChoiceItem.text = currentItem.answer
        holder.bind(multiChoiceAnswers[position], clickListener)
    }

    class MultiChoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item : MultiChoiceItem, clickListener: (MultiChoiceItem) -> Unit) {
            itemView.cbAnswer.text = item.answer
            itemView.setOnClickListener { clickListener(item)}
        }

    }

}