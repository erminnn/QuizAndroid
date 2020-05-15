package com.example.kviz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.example.kviz.answerfragments.MultiChoiceAnswerFragment
import com.example.kviz.answerfragments.RadioAnswerFragment
import com.example.kviz.answerfragments.TextAnswerFragment
import com.example.kviz.database.InMemoryDatabase
import com.example.kviz.models.MultiChoiceQuestion
import com.example.kviz.models.RadioQuestion
import com.example.kviz.models.TextQuestion
import kotlinx.android.synthetic.*
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
    }

    fun setQuestion(question : Any){
        Log.d("baza",databaseAnswers.toString())
        if(index != 0){
            for (fragment in childFragmentManager.fragments) {
                Log.i("IMAIH","BROJ")
                childFragmentManager.beginTransaction().remove(fragment).commit()
            }
        }
        if(question is TextQuestion){


            val textAnswerFragment = TextAnswerFragment()
            val fragmentManager = childFragmentManager


            tvQuestion.text = question.question

            fragmentManager.beginTransaction().apply {
                replace(R.id.answerFragmentHolder,textAnswerFragment)
                commit()
            }


            btnSubmitAnswer.setOnClickListener {


                val userAnswer = listOf(textAnswerFragment.editText.text.toString())
                val correctAnswer = listOf(question.correctAnswer)

                databaseAnswers.add(Pair(userAnswer,correctAnswer))


                if(index <= questions.size-2) {
                    index += 1
                    val question = questions.get(index)
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


        }else if(question is RadioQuestion){
            val radioAnswerFragment = RadioAnswerFragment()
            val fragmentManager = childFragmentManager

            tvQuestion.text = question.question

            fragmentManager.beginTransaction().apply {
                replace(R.id.answerFragmentHolder,radioAnswerFragment)
                commit()
            }

            btnSubmitAnswer.setOnClickListener {
                val userAnswer = listOf(radioAnswerFragment.ANS)
                val correctAnswer = listOf(question.correctAnswer)

                databaseAnswers.add(Pair(userAnswer,correctAnswer))

                if(index <= questions.size-2) {
                    index += 1
                    val question = questions.get(index)
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


        }else if(question is MultiChoiceQuestion){
            val correctAns = mutableListOf<String>()
            for(ans in question.correctAnswers){
                correctAns.add(ans.answer)
            }

            val multiChoiceAnswerFragment = MultiChoiceAnswerFragment(question.answerChoices)
            val fragmentManager = childFragmentManager

            tvQuestion.text = question.question
            fragmentManager.beginTransaction().apply {
                replace(R.id.answerFragmentHolder,multiChoiceAnswerFragment)
                commit()
            }

            btnSubmitAnswer.setOnClickListener {
                multiChoiceAnswerFragment.save(correctAns)
                if(index <= questions.size-2) {
                    index += 1
                    val  question = questions.get(index)
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
    }

    /*
    fun getVisibleFragment() {
        val fragmentManager = activity!!.supportFragmentManager
        val fragments: List<Fragment> = fragmentManager.fragments
        if (fragments != null) {
            for (fragment in fragments) {
                Log.i("frag" , "eo")
            }
        }
    }

     */




}
