package com.example.kviz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
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
    var index = 0
    val databaseAnswers = InMemoryDatabase.answers
    var jokerUsed = false
    var level = 0
    lateinit var questions : List<Any>
    val database = InMemoryDatabase


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_quiz, container, false)
        val bundle : Bundle = this.arguments as Bundle
        level = bundle.getInt("level")
        questions = database.getQuestions(8,level)
        Log.d("baza",questions.toString())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var question = questions.get(index)
        setQuestion(question)
            btnJoker.setOnClickListener {
                if(jokerUsed == false) {
                jokerUsed = true
                val intent = Intent()
                val question = questions.get(index)
                if (question is TextQuestion) {
                    intent.action = Intent.ACTION_SEND
                    intent.putExtra(
                        Intent.EXTRA_TEXT,
                        "Hello can u help me with this question : ${question.question}"
                    )
                    intent.type = "text/plain"
                    startActivity(Intent.createChooser(intent, "Share to : "))
                } else if (question is RadioQuestion) {
                    intent.action = Intent.ACTION_SEND
                    intent.putExtra(
                        Intent.EXTRA_TEXT,
                        "Hello can u help me with this question : ${question.question} , i have options true or false"
                    )
                    intent.type = "text/plain"
                    startActivity(Intent.createChooser(intent, "Share to : "))

                } else if (question is MultiChoiceQuestion) {
                    intent.action = Intent.ACTION_SEND
                    var choices = ""
                    for (choice in question.answerChoices) {
                        choices = choices + choice.answer + "\n"
                    }
                    intent.putExtra(
                        Intent.EXTRA_TEXT,
                        "Hello can u help me with this question : ${question.question} i have this choices \n ${choices}"
                    )
                    intent.type = "text/plain"
                    startActivity(Intent.createChooser(intent, "Share to : "))
                }
            }else{
                    Toast.makeText(context,"U Used joker",Toast.LENGTH_LONG).show()
                }
        }
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
                if(textAnswerFragment.editText.text.toString() != ""){
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
                }else{
                    Toast.makeText(context,"Text field empty",Toast.LENGTH_LONG).show()
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
                if(radioAnswerFragment.answer != ""){
                    val userAnswer = listOf(radioAnswerFragment.answer)
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
                }else{
                    Toast.makeText(context,"Radio answer not checked empty",Toast.LENGTH_LONG).show()
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
                if(multiChoiceAnswerFragment.getCheckedAnswersSizeFromFragment() != 0){
                    multiChoiceAnswerFragment.saveAnswersInAdapter(correctAns)
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
                }else{
                    Toast.makeText(context,"MultiChoice not checked",Toast.LENGTH_LONG).show()
                }
            }
        }


    }

}
