package com.dfl.cleanarchmovieapp.notImplemented

import com.dfl.datamodule.MoviesRepository
import com.dfl.datamodule.dbsqlite.RoomDBDataSource
import com.dfl.datamodule.remote.ServerDataSource
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
    fun testeo() {
      /*  coEvery { providerServer.getMovies(1) } returns DataResult.Error(Exception(""))
        repo.getMovies()
        coVerify { providerDb.getMovies(1) }*/
        coEvery {  providerServer.getMovies(1) } returns com.dfl.sharedmodule.DataResult.Error(Exception(""))

        coVerify { providerServer.getMovies(1)}
    }
}