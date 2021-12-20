package com.mangaapikotlin.model.response

import com.mangaapikotlin.model.Manga

data class MangaListResponse(
    var Dados: List<Manga>,
    var Error: String?
)
