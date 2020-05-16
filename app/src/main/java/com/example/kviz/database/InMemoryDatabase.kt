package com.example.kviz.database

import com.example.kviz.models.*
import java.lang.Math.round
import java.util.*
import kotlin.math.roundToInt
import kotlin.random.Random

object InMemoryDatabase {


    val answers = mutableListOf<Pair<List<String>,List<String>>>()
    val easyQuestions = listOf(
        TextQuestion("EP1T","EO1"),
        TextQuestion("EP2T","EO1"),
        TextQuestion("EP3T","EO1"),
        TextQuestion("EP4T","EO1"),
        TextQuestion("EP5T","EO1"),
        RadioQuestion("EPR1", listOf("DA","NE"),"DA"),
        RadioQuestion("EPR2", listOf("DA","NE"),"DA"),
        RadioQuestion("EPR3", listOf("DA","NE"),"DA"),
        RadioQuestion("EPR4", listOf("DA","NE"),"DA"),
        RadioQuestion("EPR5", listOf("DA","NE"),"DA"),
        MultiChoiceQuestion("EPM1", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("EPM2", listOf(MultiChoiceItem("Prvi"),MultiChoiceItem("Drugi"),MultiChoiceItem("Treci"),MultiChoiceItem("Cetvrti")), listOf(MultiChoiceItem("Prvi"),MultiChoiceItem("Drugi")))
                /*
        MultiChoiceQuestion("EPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("EPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("EPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("EPM", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA")))

                 */
    )

    val mediumQuestions = listOf(
        TextQuestion("MP1T","EO1"),
        TextQuestion("MP2T","EO1"),
        TextQuestion("MP3T","EO1"),
        TextQuestion("MP4T","EO1"),
        TextQuestion("MP5T","EO1"),
        RadioQuestion("MPR1", listOf("DA","NE"),"DA"),
        RadioQuestion("MPR2", listOf("DA","NE"),"DA"),
        RadioQuestion("MPR3", listOf("DA","NE"),"DA"),
        RadioQuestion("MPR4", listOf("DA","NE"),"DA"),
        RadioQuestion("MPR5", listOf("DA","NE"),"DA"),
        MultiChoiceQuestion("MPM1", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("MPM2", listOf(MultiChoiceItem("YES"),MultiChoiceItem("NO"),MultiChoiceItem("MYB")), listOf(MultiChoiceItem("YES"))),
        MultiChoiceQuestion("MPM3", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("MPM4", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("MPM5", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("MPM6", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA")))
    )
    val hardQuestions = listOf(
        TextQuestion("HP1T","EO1"),
        TextQuestion("HP2T","EO1"),
        TextQuestion("HP3T","EO1"),
        TextQuestion("HP4T","EO1"),
        TextQuestion("HP5T","EO1"),
        RadioQuestion("HPR1", listOf("DA","NE"),"DA"),
        RadioQuestion("HPR2", listOf("DA","NE"),"DA"),
        RadioQuestion("HPR3", listOf("DA","NE"),"DA"),
        RadioQuestion("HPR4", listOf("DA","NE"),"DA"),
        RadioQuestion("HPR5", listOf("DA","NE"),"DA"),
        MultiChoiceQuestion("HPM1", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("HPM2", listOf(MultiChoiceItem("YES"),MultiChoiceItem("NO"),MultiChoiceItem("MYB")), listOf(MultiChoiceItem("YES"))),
        MultiChoiceQuestion("HPM3", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("HPM4", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("HPM5", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA"))),
        MultiChoiceQuestion("HPM6", listOf(MultiChoiceItem("DA"),MultiChoiceItem("Ne"),MultiChoiceItem("MOZDA")), listOf(MultiChoiceItem("DA")))
    )


    fun getQuestions(numberOfQuestions : Int,level : Int): List<Any> {
        var number = numberOfQuestions.toFloat()
        var num_of_easy: Int
        var num_of_medium: Int
        var num_of_hard: Int

        when (level) {           // easy
            1 -> {
                num_of_easy = (number / 2).roundToInt()
                num_of_medium = (number / 4).roundToInt()
                num_of_hard = (number / 4).roundToInt()
            }
            2 -> {
                num_of_easy = (number / 4).roundToInt()
                num_of_medium = (number / 2).roundToInt()
                num_of_hard = (number / 4).roundToInt()
            }
            3 -> {
                num_of_easy = (number / 4).roundToInt()
                num_of_medium = (number / 4).roundToInt()
                num_of_hard = (number / 2).roundToInt()
            }
            else -> {
                num_of_easy = 0
                num_of_medium = 0
                num_of_hard = number.toInt()
            }
        }

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