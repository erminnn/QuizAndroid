package com.example.kviz.database

import com.example.kviz.models.*
import java.util.*
import kotlin.random.Random

object InMemoryDatabase {


    val answers = mutableListOf<Pair<List<String>,List<String>>>()
    val easyQuestions = listOf(
        TextQuestion("EP1T","EO1"),
        TextQuestion("EP1T","EO1"),
        TextQuestion("EP1T","EO1"),
        TextQuestion("EP1T","EO1"),
        TextQuestion("EP1T","EO1"),
        RadioQuestion("EPR", listOf("DA","NE"),"DA"),
        RadioQuestion("EPR", listOf("DA","NE"),"DA"),
        RadioQuestion("EPR", listOf("DA","NE"),"DA"),
        RadioQuestion("EPR", listOf("DA","NE"),"DA"),
        RadioQuestion("EPR", listOf("DA","NE"),"DA"),
        MultiChoiceQuestion("EPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("EPM", listOf(MultiChoiceItem("Prvi"),MultiChoiceItem("Drugi"),MultiChoiceItem("Treci"),MultiChoiceItem("Cetvrti")), listOf(MultiChoiceItem("Prvi"),MultiChoiceItem("Drugi")))
                /*
        MultiChoiceQuestion("EPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("EPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("EPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("EPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA")))

                 */
    )

    val mediumQuestions = listOf(
        TextQuestion("MP1T","EO1"),
        TextQuestion("MP1T","EO1"),
        TextQuestion("MP1T","EO1"),
        TextQuestion("MP1T","EO1"),
        TextQuestion("MP1T","EO1"),
        RadioQuestion("MPR", listOf("DA","NE"),"DA"),
        RadioQuestion("MPR", listOf("DA","NE"),"DA"),
        RadioQuestion("MPR", listOf("DA","NE"),"DA"),
        RadioQuestion("MPR", listOf("DA","NE"),"DA"),
        RadioQuestion("MPR", listOf("DA","NE"),"DA"),
        MultiChoiceQuestion("MPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("MPM", listOf(MultiChoiceItem("YES"),MultiChoiceItem("NO"),MultiChoiceItem("MYB")), listOf(MultiChoiceItem("YEs"))),
        MultiChoiceQuestion("MPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("MPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("MPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("MPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA")))
    )
    val hardQuestions = listOf(
        TextQuestion("HP1T","EO1"),
        TextQuestion("HP1T","EO1"),
        TextQuestion("HP1T","EO1"),
        TextQuestion("HP1T","EO1"),
        TextQuestion("HP1T","EO1"),
        RadioQuestion("HPR", listOf("DA","NE"),"DA"),
        RadioQuestion("HPR", listOf("DA","NE"),"DA"),
        RadioQuestion("HPR", listOf("DA","NE"),"DA"),
        RadioQuestion("HPR", listOf("DA","NE"),"DA"),
        RadioQuestion("HPR", listOf("DA","NE"),"DA"),
        MultiChoiceQuestion("HPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("HPM", listOf(MultiChoiceItem("YES"),MultiChoiceItem("NO"),MultiChoiceItem("MYB")), listOf(MultiChoiceItem("YEs"))),
        MultiChoiceQuestion("HPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("HPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("HPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("HPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA")))
    )

// komentar
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