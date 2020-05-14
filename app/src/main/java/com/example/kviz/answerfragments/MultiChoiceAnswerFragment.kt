package com.example.kviz.answerfragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kviz.MultiChoiceAdapter
import com.example.kviz.R
import com.example.kviz.models.MultiChoiceItem
import kotlinx.android.synthetic.main.fragment_multi_choice_answer.*

/**
 * A simple [Fragment] subclass.
 */
class MultiChoiceAnswerFragment(private val multiChoiceAnswers : List<MultiChoiceItem>) : Fragment(){
    var l = mutableListOf<MultiChoiceItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multi_choice_answer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var multiAns = multiChoiceAnswers
        val adapter = MultiChoiceAdapter(multiAns)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }


}
