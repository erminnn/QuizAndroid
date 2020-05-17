# Kviz - Programiranje I

> Prirodno-matematički fakultet\
> Odsjek za matematiku\
> Razvoj mobilnih aplikacija

# Kratki opis

Kviz aplikacija sa primarnim fokusom na pitanja vezana za predmet Programiranje I.
Igrač u početku kviza bira jednu od tri težine, te u toku igre ima jedan "džoker" koji može iskoristiti
da upita prijatelja za pomoć preko poruke ili poziva.

# Sadržaj

  - [Kratki opis](#kratki-opis)
  - [Uvod](#uvod)
    - [Home fragment](#home-fragment)
    - [Start quiz fragment](#start-quiz-fragment)
    - [Main quiz fragment](#main-quiz-fragment)
      - [Multi-choice answer fragment](#multi-choice-answer-fragment)
      - [Radio answer fragment](#radio-answer-fragment)
      - [Text answer fragment](#text-answer-fragment)
    - [End quiz fragment](#end-quiz-fragment)
    - [About fragment](#about-fragment)


# Uvod 

Aplikacija sadrži samo jedan `activity` čiji je glavni layout *drawerLayout*, koji dalje sadrži *constraintLayout* sa *frameLayout*-om za držanje fragmenata, te *navigationView*.

Fragmenti su sljedeći:
  - [Home fragment](#home-fragment)
  - [Start quiz fragment](#start-quiz-fragment)
  - [Main quiz fragment](#main-quiz-fragment)
    - [Multi-choice answer fragment](#multi-choice-answer-fragment)
    - [Radio answer fragment](#radio-answer-fragment)
    - [Text answer fragment](#text-answer-fragment)
  - [End quiz fragment](#end-quiz-fragment)
  - [About fragment](#about-fragment)



## Home fragment

Sadrži tekst dobrodošlice, te dugme **Play** za početak kviza.

## Start quiz fragment

Sadrži tekst, te tri dugmeta za biranje težine.

## Main quiz fragment

Ovaj fragment služi kao *container* za 
[Multi-choice answer](#multi-choice-answer-fragment), 
[Radio answer](#radio-answer-fragment) i 
[Text answer](#text-answer-fragment) fragmente. Osim jednog od tri nabrojana fragmenta, `main_quiz` fragment također sadrži i dugme **Next**, te "džoker" dugme koje je tipa **FAB** (Floating Action Button), koje okida akciju dijeljenja.

### Multi-choice answer fragment

*Container* za checkbox-e. Multi-choice pitanja sa više mogućih tačnih odgovora.

### Radio answer fragment

*Container* za grupu radio dugmadi. Multi-choice pitanja sa jednim tačnim odgovorom.

### Text answer fragment

Sadrži jedan prazan *EditText* view koji služi za tekstualne odgovore.

## End quiz fragment

Fragment koji se prikaže nakon zadnjeg pitanja u kvizu. Prikazuje na koliko je pitanja igrač odgovorio tačno.

## About fragment

Sadrži minimalne podatke o aplikaciji.