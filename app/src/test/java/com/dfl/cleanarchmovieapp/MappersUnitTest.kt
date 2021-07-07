package com.dfl.cleanarchmovieapp

import com.dfl.datamodule.dbsqlite.MovieEntity
import com.dfl.datamodule.mapper.MovieMap
import com.dfl.datamodule.remote.MovieRemote
import com.dfl.model.Movie
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 *pruebas unitarias sobre mapeadores
 */
class MappersUnitTest {
    private lateinit var remote: MovieRemote
    private lateinit var model: Movie
    private lateinit var entity: MovieEntity
    private var page = 2

    @Before
    fun setup() {
        remote = MovieRemote(1, "nemo", true, "historia", listOf(1), "image")
        model = Movie(1, "nemo", "historia", "image", true, 2)
        entity = MovieEntity(1, "nemo", "historia", "image", true, 2)
    }

    @Test
    fun testMapRemoteToModel() {
        val sut = MovieMap.getItems(listOf(remote), page)
        assertEquals(model, sut.first())
    }

    @Test
    fun testMapToDB() {
        val sut = MovieMap.getMoviesEntity(listOf(model), page)
        assertEquals(entity, sut.first())
    }

    @Test
    fun testMapFromDBToModel() {
        val sut = MovieMap.getMovieFromEntity(entity)
        assertEquals(model, sut)
    }

}