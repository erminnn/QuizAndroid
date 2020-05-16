package com.example.kviz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kviz.database.InMemoryDatabase
import kotlinx.android.synthetic.main.fragment_end_quiz.*


class EndQuizFragment : Fragment() {
    val databaseAnswers = InMemoryDatabase.answers
    var tacnoOdg = 0
    var netacnoOdg = 0
    var tacan = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_end_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for(answer in databaseAnswers){
            tacan = true
            for(userAnswer in answer.first){
                if(!answer.second.contains(userAnswer)){
                    tacan = false
                }
            }
            if(tacan == true){
                tacnoOdg++
            }else{
                netacnoOdg++
            }

        }
        Log.d("baza",databaseAnswers.toString()+ "baza nakon zavrsetka kviza")

        tvRezultat.text = "Od ${databaseAnswers.size} pitanja, tacno ste odgovorili na ${tacnoOdg} pitanja, a netacno na ${netacnoOdg}"


    }

}
