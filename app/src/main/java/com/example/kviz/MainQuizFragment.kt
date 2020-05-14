package com.example.kviz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.Toast
import com.example.kviz.answerfragments.MultiChoiceAnswerFragment
import com.example.kviz.answerfragments.RadioAnswerFragment
import com.example.kviz.answerfragments.TextAnswerFragment
import com.example.kviz.database.InMemoryDatabase
import com.example.kviz.models.MultiChoiceItem
import com.example.kviz.models.MultiChoiceQuestion
import com.example.kviz.models.RadioQuestion
import com.example.kviz.models.TextQuestion
import kotlinx.android.synthetic.main.fragment_main_quiz.*
import kotlinx.android.synthetic.main.fragment_radio_answer.*
import kotlinx.android.synthetic.main.fragment_text_answer.*
import kotlin.math.log

class MainQuizFragment : Fragment(){

    val questions = InMemoryDatabase.getQuestions(8,1)
    var index = 0
    val databaseAnswers = InMemoryDatabase.answers



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var question = questions.get(index)
        setQuestion(question)

        btnSubmitAnswer.setOnClickListener {
            Log.d("answr",databaseAnswers.toString())
            val current_question = questions.get(index)
            if (current_question is TextQuestion) {
                Log.i("TG","${current_question.correctAnswer.toString()}")
                Log.i("TG","${etTextAnswer.text.toString()}")
            } else if (current_question is RadioQuestion) {
                val checkedRadioAnswerId = rgRadioAnswer.checkedRadioButtonId
                val ans = activity!!.findViewById<RadioButton>(checkedRadioAnswerId)
                Log.i("EO","${ans.text}")

            } else if (current_question is MultiChoiceQuestion) {
            }

            if(index <= questions.size-2) {
                index += 1
                question = questions.get(index)
                setQuestion(question)
            }else{
                val fragmentManager = activity!!.supportFragmentManager
                val endQuizFragment = EndQuizFragment()
                fragmentManager.beginTransaction().apply {
                    replace(R.id.fragmentHolder,endQuizFragment)
                    commit()
                }
            }
        }
    }

    fun setQuestion(question : Any){
        if(question is TextQuestion){
            val textAnswerFragment = TextAnswerFragment()
            val fragmentManager = childFragmentManager
            tvQuestion.text = question.question
            fragmentManager.beginTransaction().apply {
                replace(R.id.answerFragmentHolder,textAnswerFragment)
                commit()
            }
        }else if(question is RadioQuestion){
            val radioAnswerFragment = RadioAnswerFragment()
            tvQuestion.text = question.question
            val fragmentManager = childFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.answerFragmentHolder,radioAnswerFragment)
                commit()
            }
        }else if(question is MultiChoiceQuestion){
            tvQuestion.text = question.question
            val multiChoiceAnswerFragment = MultiChoiceAnswerFragment(question.answerChoices)
            val fragmentManager = childFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.answerFragmentHolder,multiChoiceAnswerFragment)
                commit()
            }
        }
    }




}
