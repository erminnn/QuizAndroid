package com.example.kviz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main_quiz.*

/**
 * A simple [Fragment] subclass.
 */
class MainQuizFragment : Fragment() {

    val radioAnswerFragment = RadioAnswerFragment()
    val textAnswerFragment = TextAnswerFragment()
    var multiAns = mutableListOf(MultiChoiceItem("DA"),
        MultiChoiceItem("NE"),MultiChoiceItem
            ("MOZDA"))
    val multiChoiceAnswerFragment = MultiChoiceAnswerFragment(multiAns);
    var choice = 0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSubmitAnswer.setOnClickListener {

            val fragmentManager = activity!!.supportFragmentManager
            if(choice == 0){
                fragmentManager.beginTransaction().apply {
                    replace(R.id.answerFragmentHolder,radioAnswerFragment)
                    commit()
                }
                choice = 1
            }else if(choice == 1){
                fragmentManager.beginTransaction().apply {
                    replace(R.id.answerFragmentHolder,textAnswerFragment)
                    commit()
                }
                choice = 2
            }else{
                fragmentManager.beginTransaction().apply {
                    replace(R.id.answerFragmentHolder,multiChoiceAnswerFragment)
                    commit()
                }
                choice = 0
            }

        }
    }

}
