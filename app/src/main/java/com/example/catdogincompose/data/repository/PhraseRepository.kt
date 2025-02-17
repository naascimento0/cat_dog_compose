package com.example.catdogincompose.data.repository

import com.example.catdogincompose.domain.model.Phrase
import javax.inject.Inject

/**
 * Repository for phrases.
 *
 * This repository is used to manage the phrases.
 */
class PhraseRepository @Inject constructor() {
    private val catPhrases = listOf(
        "Os gatos têm um órgão extra que lhes permite saborear cheiros.",
        "Um gato pode fazer mais de 100 sons diferentes.",
        "Os gatos têm 32 músculos em cada orelha, permitindo-lhes girar suas orelhas para ouvir melhor.",
        "Os bigodes dos gatos são altamente sensíveis e podem detectar mudanças mínimas no ambiente."
    )

    private val dogPhrases = listOf(
        "Os cães têm um olfato que é até 100.000 vezes mais sensível que o dos humanos.",
        "Os cães conseguem entender cerca de 165 palavras e sinais diferentes.",
        "Os cães possuem glândulas sudoríparas apenas nas patas, não pelo corpo.",
        "Raças como o Basenji não latem, mas emitem um som peculiar conhecido como \"yodel\"."
    )

    fun getRandomCatPhrase(): Phrase {
        return Phrase(catPhrases.random())
    }

    fun getRandomDogPhrase(): Phrase {
        return Phrase(dogPhrases.random())
    }
}