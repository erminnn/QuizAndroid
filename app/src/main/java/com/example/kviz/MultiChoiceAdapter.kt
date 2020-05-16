package com.example.kviz

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kviz.database.InMemoryDatabase
import com.example.kviz.models.MultiChoiceItem
import kotlinx.android.synthetic.main.fragment_main_quiz.view.*
import kotlinx.android.synthetic.main.multichoice_item.view.*


class MultiChoiceAdapter(private val multiChoiceAnswers : List<MultiChoiceItem>) : RecyclerView.Adapter<MultiChoiceAdapter.MultiChoiceViewHolder>() {
    val databaseAnswers = InMemoryDatabase.answers
    var checkedAnswers = mutableListOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiChoiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.multichoice_item,parent,false)
        return MultiChoiceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return multiChoiceAnswers.size
    }

    override fun onBindViewHolder(holder: MultiChoiceViewHolder, position: Int) {
        val currentItem = multiChoiceAnswers[position]
        holder.multiChoiceItemTextView.text = currentItem.answer
        holder.multiChoiceItem.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                if(holder.multiChoiceItem.isChecked){
                    checkedAnswers.add(currentItem.answer)
                }else{
                    checkedAnswers.remove(currentItem.answer)
                }


            }})
    }

    class MultiChoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val multiChoiceItemTextView : TextView = itemView.tvMultiChoiceAnswer
        val multiChoiceItem : CheckBox = itemView.cbAnswer
    }

    fun saveAns(correctAns : List<String>){
        databaseAnswers.add(Pair(checkedAnswers,correctAns))
    }

    fun getCheckedAnswersSizeFromAdapter() : Int {
        return checkedAnswers.size
    }


}


