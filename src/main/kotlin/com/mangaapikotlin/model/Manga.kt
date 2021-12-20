package com.mangaapikotlin.model

import javax.persistence.*

@Entity
@Table(name = "Mangas")
data class Manga(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var nome: String,
    var capituloatual: Long,
    var estado: EstadoManga
)

