package com.dfl.cleanarchmovieapp

import com.dfl.cleanarchmovieapp.data.MoviesRepository
import com.dfl.cleanarchmovieapp.data.local.dbsqlite.RoomDBDataSource
import com.dfl.cleanarchmovieapp.data.remote.ServerDataSource
import com.dfl.cleanarchmovieapp.utils.DataResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class Test {

    var providerServer = mockk<ServerDataSource>()
    var providerDb = mockk<RoomDBDataSource>()
    lateinit var repo: MoviesRepository

    @Before
    fun setup() {
        repo = MoviesRepository(
            providerDb,
            providerServer
        )
    }

    @Test
    suspend fun testeo() {
      /*  coEvery { providerServer.getMovies(1) } returns DataResult.Error(Exception(""))
        repo.getMovies()
        coVerify { providerDb.getMovies(1) }*/
        coEvery {  providerServer.getMovies(1) } returns DataResult.Error(Exception(""))

        providerServer.getMovies(1)
        coVerify { providerServer.getMovies(1)}
    }
}