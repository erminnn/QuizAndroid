package com.example.kviz.answerfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.kviz.R
import kotlinx.android.synthetic.main.fragment_radio_answer.*

/**
 * A simple [Fragment] subclass.
 */
class RadioAnswerFragment : Fragment() {
    lateinit var radioGroup: RadioGroup
    lateinit var ANS : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_radio_answer, container, false)
        radioGroup = view.findViewById<RadioGroup>(R.id.rgRadioAnswer)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.rbTrue){
                ANS = rbTrue.text.toString()
            }else if(checkedId == R.id.rbFalse){
                ANS = rbFalse.text.toString()
            }
        }
    }

}
