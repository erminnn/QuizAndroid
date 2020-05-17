package com.example.kviz.database

import com.example.kviz.models.*
import java.lang.Math.round
import java.util.*
import kotlin.math.roundToInt
import kotlin.random.Random

object InMemoryDatabase {


    val answers = mutableListOf<Pair<List<String>,List<String>>>()
    val easyQuestions = listOf(
        TextQuestion("Napišite indeks koji vraća predzadnji član neke liste:","-2"),
        TextQuestion("Kako se zove funkcija koja vraća dužinu list-e, string-a, array-a, itd.?","len"),
        TextQuestion("Napišite operator koji obrće vrijednost boolean varijable:","not"),
        TextQuestion("Koji se specijalni karakter koristi za jednolinijske komentare u pythonu?", "#"),
        RadioQuestion("Da li je python case-sensitive (tj. da li razlikuje velika i mala slova)?", listOf("DA","NE"),"DA"),
        MultiChoiceQuestion("Kada će se else dio try-except-else koda biti izvršen?", listOf(MultiChoiceItem("uvijek"),MultiChoiceItem("kada se desi izuzetak"),MultiChoiceItem("kada se ne desi ni jedan izuzetak"), MultiChoiceItem("kada se desi izuzetak unutar except bloka")), listOf(MultiChoiceItem("kada se ne desi ni jedan izuzetak"))),
        MultiChoiceQuestion("Šta je rezultat sljedećeg koda?\nf = None\n\nfor i in range(5):\n\twith open(\"data.txt\", \"w\") as f:\n\t\tif i > 2:\n\t\t\tbreak", listOf(MultiChoiceItem("Prvi"),MultiChoiceItem("Drugi"),MultiChoiceItem("Treci"),MultiChoiceItem("Cetvrti")), listOf(MultiChoiceItem("Prvi"),MultiChoiceItem("Drugi"))),
        MultiChoiceQuestion("Koji od sljedećih izraza kreira riječnik?", listOf(MultiChoiceItem("d = {}"),MultiChoiceItem("d = {\"john\":40, \"peter\":45}"),MultiChoiceItem("d = {40:\"john\", 45:\"peter\"}"), MultiChoiceItem("d = (40:\"john\", 45:\"50\")")), listOf(MultiChoiceItem("d = {\"john\":40, \"peter\":45}"),MultiChoiceItem("d = {40:\"john\", 45:\"peter\"}"), MultiChoiceItem("d = (40:\"john\", 45:\"50\")")))
    )

    val mediumQuestions = listOf(
        TextQuestion("Koja se ključna riječ koristi za prekid petlje?", "break"),
        TextQuestion("Koja se ključna riječ koristi za prekid trenutne iteracije petlje?", "continue"),
        TextQuestion("Koja je ključna riječ sa kojom se definiše funkcija u pythonu?", "def"),
        RadioQuestion("Da li je moguće izvršiti eksplicitnu konverziju u python-u?", listOf("Da","Ne"),"Da"),
        MultiChoiceQuestion("Da bi otvorili fajl \"c:scores.txt\" za zapisivanje, koristimo:", listOf(MultiChoiceItem("outfile = open(\"c:scores.txt\", \"r\")"),MultiChoiceItem("outfile = open(\"c:scores.txt\", \"w\")"),MultiChoiceItem("outfile = open(file=\"c:scores.txt\", \"r\")"),MultiChoiceItem("outfile = open(file=\"c:scores.txt\", \"o\")")), listOf(MultiChoiceItem("outfile = open(\"c:scores.txt\", \"w\")"))),
        MultiChoiceQuestion("Neka je lista1 = [2, 33, 222, 14, 25]. Šta nam daje sljedeći izraz list1a[-1]?", listOf(MultiChoiceItem("Error"),MultiChoiceItem("None"),MultiChoiceItem("25"), MultiChoiceItem("2")), listOf(MultiChoiceItem("25"))),
        MultiChoiceQuestion("Koja je najveća moguća dužina identifikatora? (naziv varijable, klase, ...)", listOf(MultiChoiceItem("31 karakter"),MultiChoiceItem("63 karaktera"), MultiChoiceItem("Ni jedno od ponuđenog")), listOf(MultiChoiceItem("Ni jedno od ponuđenog"))),
        MultiChoiceQuestion("Koji od sljedećih operatora je cjelobrojno dijeljenje?", listOf(MultiChoiceItem("/"),MultiChoiceItem("//"),MultiChoiceItem("%"), MultiChoiceItem("Ni jedno od ponuđenog")), listOf(MultiChoiceItem("//")))
    )
    val hardQuestions = listOf(
        TextQuestion("Napišite izraz koji će razdvojiti sljedeći string na listu od 3 stringa i spasiti je u varijablu \"l\":\ns = \"a b c\"","l = s.split()"),
        TextQuestion("Napišite sintaksu za prazan višelinijski komentar u pythonu:","\"\"\"\"\"\""),
        RadioQuestion("Da li je uvlačenje neophodno u pythonu?", listOf("DA","NE"),"DA"),
        MultiChoiceQuestion("Šta je rezultat sljedećeg koda?\nf = None\n\nfor i in range(5):\n\twith open(\"data.txt\", \"w\") as f:\n\t\tif i > 2:\n\t\t\tbreak", listOf(MultiChoiceItem("True"),MultiChoiceItem("False"),MultiChoiceItem("None"),MultiChoiceItem("Error")), listOf(MultiChoiceItem("True"))),
        MultiChoiceQuestion("Šta je rezultat sljedećeg koda?\ntry:\n\tif '1' != 1:\n\t\traise \"nekaGreska\"\n\telse:\n\t\tprint(\"nekaGreska se nije desila\")\nexcept \"nekaGreska\":\n\tprint(\"nekaGreska se desila\")", listOf(MultiChoiceItem("nekaGreska se desila"),MultiChoiceItem("nekaGreska se nije desila"),MultiChoiceItem("invalid code"), MultiChoiceItem("ni jedno od ponuđenog")), listOf(MultiChoiceItem("invalid code"))),
        MultiChoiceQuestion("Koji od sljedećih izraza je neispravan?", listOf(MultiChoiceItem("abc = 1,000,000"),MultiChoiceItem("a b c = 1000 2000 3000"),MultiChoiceItem("a,b,c = 1000, 2000, 3000"), MultiChoiceItem("a_b_c = 1,000,000")), listOf(MultiChoiceItem("a b c = 1000 2000 3000"))),
        MultiChoiceQuestion("Zašto nazivi lokalnih varijabla koji počinju sa donjom crtom (\"_\") nisu dobra praksa?", listOf(MultiChoiceItem("jer se koriste za privatne varijable u klasi"),MultiChoiceItem("jer zbune interpreter"),MultiChoiceItem("jer se koriste za globalne varijable"), MultiChoiceItem("jer usporavaju izvršavanje programa")), listOf(MultiChoiceItem("jer se koriste za privatne varijable u klasi")))
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