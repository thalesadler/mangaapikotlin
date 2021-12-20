package com.mangaapikotlin.model.response

import com.mangaapikotlin.model.Manga

data class MangaResponse(
    var Dados: Manga?,
    var Error: String?
)
