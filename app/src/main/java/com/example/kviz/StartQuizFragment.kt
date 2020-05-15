package com.example.kviz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_start_quiz.*
import kotlin.math.log


class StartQuizFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_start_quiz, container, false)
        return  v;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainQuizFragment = MainQuizFragment();
        val fragmentManager = activity!!.supportFragmentManager
        val bundle : Bundle = Bundle()

        btnEasyLevel.setOnClickListener {
            bundle.putInt("level",1)
            mainQuizFragment.arguments = bundle
            fragmentManager.beginTransaction().apply {
                replace(R.id.fragmentHolder,mainQuizFragment)
                commit()
            }
        }

        btnMediumLevel.setOnClickListener {
            bundle.putInt("level",2)
            mainQuizFragment.arguments = bundle
            fragmentManager.beginTransaction().apply {
                replace(R.id.fragmentHolder,mainQuizFragment)
                commit()
            }
        }

        btnHardLevel.setOnClickListener {
            bundle.putInt("level",3)
            mainQuizFragment.arguments = bundle
            fragmentManager.beginTransaction().apply {
                replace(R.id.fragmentHolder,mainQuizFragment)
                commit()
            }
        }


    }

}

