package com.example.kviz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_start_quiz.*
import kotlin.math.log


class StartQuizFragment : Fragment() {
    lateinit var poruka : String
    var index = 0
    val pitanja = listOf<String>("P1","P2","P3","P4")
    val odg = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_start_quiz, container, false)
        val tv : TextView = v.findViewById(R.id.tvChooseLevel) as TextView
        tv.text = pitanja.get(index)
        if(savedInstanceState != null){
            tv.text = poruka
        }
        return  v;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if((pitanja.count())== index){
            tvChooseLevel.text = "ZAVRSENO"
        }else {
            btnPromjeni.setOnClickListener {
                tvChooseLevel.text = pitanja.get(index)
                    poruka = tvChooseLevel.text.toString()
                index++
                odg.add(etOdg.text.toString())
            }
        }
    }



}
