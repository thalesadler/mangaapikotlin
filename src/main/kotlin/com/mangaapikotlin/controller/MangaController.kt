package com.mangaapikotlin.controller

import com.mangaapikotlin.model.Manga
import com.mangaapikotlin.model.response.MangaListResponse
import com.mangaapikotlin.model.response.MangaResponse
import com.mangaapikotlin.service.MangaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/api/manga")
class MangaController(private val mangaService: MangaService) {

    @GetMapping
    @Transactional
    fun List(): ResponseEntity<MangaListResponse> {

        var result: MangaListResponse = MangaListResponse(listOf(), null)

        try {
            result.Dados = mangaService.List()
            return ResponseEntity.ok(result)
        } catch (e: Exception) {
            result.Error = e.message
            return ResponseEntity.badRequest().body(result)
        }
    }

    @GetMapping("/{status}")
    @Transactional
    fun ListByStatus(@PathVariable status: String): ResponseEntity<MangaListResponse> {

        var result: MangaListResponse = MangaListResponse(listOf(), null)

        try {
            result.Dados = mangaService.List(status)
            return ResponseEntity.ok(result);
        } catch (e: Exception) {
            result.Error = e.message
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping
    @Transactional
    fun Post(
        @RequestBody @Valid form: Manga,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<MangaResponse> {

        var result: MangaResponse = MangaResponse(null, null)

        try {
            var manga: Manga = mangaService.Post(form)
            result.Dados = manga
            var id: Long? = manga.id
            val uri = uriBuilder.path("/manga/${id}").build().toUri()
            return ResponseEntity.created(uri).body(result)
        } catch (e: Exception) {
            result.Error = e.message
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PutMapping
    @Transactional
    fun Put(@RequestBody @Valid form: Manga): ResponseEntity<MangaResponse> {
        var result: MangaResponse = MangaResponse(null, null)

        try {
            result.Dados = mangaService.Put(form)
            return ResponseEntity.ok(result);
        } catch (e: Exception) {
            result.Error = e.message
            return ResponseEntity.badRequest().body(result);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long) {
        mangaService.Delete(id)
    }

}