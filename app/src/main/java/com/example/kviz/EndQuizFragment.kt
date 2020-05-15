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
            for(userAnswer in answer.first){
                if(!answer.second.contains(userAnswer)){
                    netacnoOdg++
                }else{
                    tacnoOdg++
                }
            }
        }
        Log.d("baza",databaseAnswers.toString()+ "baza nakon zavrsetka kviza")

        tvRezultat.text = "Tacnoo ste odgovorili na ${tacnoOdg} pitanja"
    }

}
