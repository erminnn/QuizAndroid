package com.example.kviz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kviz.database.InMemoryDatabase
import kotlinx.android.synthetic.main.fragment_end_quiz.*


class EndQuizFragment : Fragment() {
    val database = InMemoryDatabase
    val databaseAnswers = InMemoryDatabase.answers
    var correctAnswers = 0
    var incorrectAnswers = 0
    var answerCorrect = false

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
            answerCorrect = true
            for(userAnswer in answer.first){
                if(!answer.second.contains(userAnswer)){
                    answerCorrect = false
                }
            }
            if(answerCorrect == true){
                correctAnswers++
            }else{
                incorrectAnswers++
            }

        }
        Log.d("baza",databaseAnswers.toString()+ "baza nakon zavrsetka kviza")
        if(databaseAnswers.size == correctAnswers){
            tvResult.text = "Cestitamo odgovorili ste tacno na sve odgovore"
        }else if(databaseAnswers.size == incorrectAnswers){
            tvResult.text = "Zao nam je niste odgovorili tacno na postavljena pitanja"
        }else{
            tvResult.text = "Od ${databaseAnswers.size} pitanja, tačno ste odgovorili na ${correctAnswers}"
        }

        btnShareResult.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Od ${databaseAnswers.size} pitanja, tačno ste odgovorili na ${correctAnswers}"
            )
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share to : "))
        }

        btnPlayAgain.setOnClickListener {
            val fragmentManager = activity!!.supportFragmentManager
            val homeFragment = HomeFragment()
            database.deleteAnswers()
            fragmentManager.beginTransaction().apply {
                replace(R.id.fragmentHolder,homeFragment)
                commit()
            }
        }




    }

}
