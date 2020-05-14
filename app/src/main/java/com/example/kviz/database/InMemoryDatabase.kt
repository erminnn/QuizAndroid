package com.example.kviz.database

import com.example.kviz.models.*
import java.util.*
import kotlin.random.Random

object InMemoryDatabase {
    val answers = mutableListOf<Pair<List<String>,List<String>?>>()
 val easyQuestions = listOf(
     TextQuestion(1,"EP1T","EO1"),
     TextQuestion(1,"EP1T","EO1"),
     TextQuestion(1,"EP1T","EO1"),
     TextQuestion(1,"EP1T","EO1"),
     TextQuestion(1,"EP1T","EO1"),
     RadioQuestion(2,"EPR", listOf("DA","NE"),"DA"),
     RadioQuestion(2,"EPR", listOf("DA","NE"),"DA"),
     RadioQuestion(2,"EPR", listOf("DA","NE"),"DA"),
     RadioQuestion(2,"EPR", listOf("DA","NE"),"DA"),
     RadioQuestion(2,"EPR", listOf("DA","NE"),"DA"),
     MultiChoiceQuestion(3,"EPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
     MultiChoiceQuestion(3,"EPM", listOf(MultiChoiceItem("YES"),MultiChoiceItem("NO"),MultiChoiceItem("MYB")), listOf(MultiChoiceItem("YEs"))),
     MultiChoiceQuestion(3,"EPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
     MultiChoiceQuestion(3,"EPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
     MultiChoiceQuestion(3,"EPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
     MultiChoiceQuestion(3,"EPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA")))
 )

    val mediumQuestions = listOf(
        TextQuestion(1,"MP1T","EO1"),
        TextQuestion(1,"MP1T","EO1"),
        TextQuestion(1,"MP1T","EO1"),
        TextQuestion(1,"MP1T","EO1"),
        TextQuestion(1,"MP1T","EO1"),
        RadioQuestion(2,"MPR", listOf("DA","NE"),"DA"),
        RadioQuestion(2,"MPR", listOf("DA","NE"),"DA"),
        RadioQuestion(2,"MPR", listOf("DA","NE"),"DA"),
        RadioQuestion(2,"MPR", listOf("DA","NE"),"DA"),
        RadioQuestion(2,"MPR", listOf("DA","NE"),"DA"),
        MultiChoiceQuestion(3,"MPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion(3,"MPM", listOf(MultiChoiceItem("YES"),MultiChoiceItem("NO"),MultiChoiceItem("MYB")), listOf(MultiChoiceItem("YEs"))),
        MultiChoiceQuestion(3,"MPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion(3,"MPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion(3,"MPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion(3,"MPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA")))
    )
    val hardQuestions = listOf(
        TextQuestion(1,"HP1T","EO1"),
        TextQuestion(1,"HP1T","EO1"),
        TextQuestion(1,"HP1T","EO1"),
        TextQuestion(1,"HP1T","EO1"),
        TextQuestion(1,"HP1T","EO1"),
        RadioQuestion(2,"HPR", listOf("DA","NE"),"DA"),
        RadioQuestion(2,"HPR", listOf("DA","NE"),"DA"),
        RadioQuestion(2,"HPR", listOf("DA","NE"),"DA"),
        RadioQuestion(2,"HPR", listOf("DA","NE"),"DA"),
        RadioQuestion(2,"HPR", listOf("DA","NE"),"DA"),
        MultiChoiceQuestion(3,"HPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion(3,"HPM", listOf(MultiChoiceItem("YES"),MultiChoiceItem("NO"),MultiChoiceItem("MYB")), listOf(MultiChoiceItem("YEs"))),
        MultiChoiceQuestion(3,"HPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion(3,"HPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion(3,"HPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion(3,"HPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA")))
    )


    fun getQuestions(number : Int,level : Int): List<Any> {
            val num_of_easy = (number/2).toInt()
            val num_of_medium = (num_of_easy/2).toInt()
            val num_of_hard = num_of_medium
            val easy_indexes = mutableListOf<Int>()
            val medium_indexes = mutableListOf<Int>()
            val hard_indexes = mutableListOf<Int>()
            val questions = mutableListOf<Any>()

            for(i in 1..num_of_easy){
                var random_index = Random.nextInt(easyQuestions.size)
                while (easy_indexes.contains(random_index)){
                    random_index = Random.nextInt(easyQuestions.size)
                }
                questions.add(easyQuestions.get(random_index))
                easy_indexes.add(random_index)
            }

            for(i in 1..num_of_medium){
                var random_index = Random.nextInt(mediumQuestions.size)
                while (medium_indexes.contains(random_index)){
                    random_index = Random.nextInt(mediumQuestions.size)
                }
                questions.add(mediumQuestions.get(random_index))
                medium_indexes.add(random_index)
            }


            for(i in 1..num_of_hard){
                var random_index = Random.nextInt(hardQuestions.size)
                while (hard_indexes.contains(random_index)){
                    random_index = Random.nextInt(hardQuestions.size)
                }
                questions.add(hardQuestions.get(random_index))
                hard_indexes.add(random_index)
            }

            return questions
    }

}