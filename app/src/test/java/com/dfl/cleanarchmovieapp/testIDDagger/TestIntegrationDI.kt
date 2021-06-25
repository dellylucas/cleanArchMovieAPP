package com.dfl.cleanarchmovieapp.testIDDagger

import com.dfl.cleanarchmovieapp.data.IDataSource
import com.dfl.cleanarchmovieapp.di.components.AppComponent
import com.dfl.cleanarchmovieapp.di.modules.AppModule
import com.dfl.cleanarchmovieapp.domain.model.Movie
import com.dfl.cleanarchmovieapp.utils.DataResult
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModuleTest::class,
        AppModule::class,
    ]
)
interface TestComponent : AppComponent {

    @Named("local")
    val localDataSource: IDataSource

    @Named("remote")
    val remoteDataSource: IDataSource

    @Component.Factory
    interface FactoryTest {
        fun create(): TestComponent
    }
}

@Module
class DataModuleTest {

    @Provides
    @Singleton
    @Named("local")
    fun localDataSourceProvider(): IDataSource = FakeLocalDataSource()

    @Provides
    @Singleton
    @Named("remote")
    fun remoteDataSourceProvider(): IDataSource = FakeRemoteDataSource()

}


class FakeLocalDataSource : IDataSource {

    var movies: List<Movie> = emptyList()

    override suspend fun saveMovies(movies: List<Movie>, page: Int) {
        this.movies = movies
    }

    override suspend fun getMovies(page: Int): DataResult<List<Movie>> =
        DataResult.Success(movies)

    override suspend fun getMovieById(id: Int): DataResult<Movie> =
        DataResult.Success(movies.first { it.id == id })

}

class FakeRemoteDataSource : IDataSource {

    var movies = defaultFakeMovies

    override suspend fun getMovies(page: Int): DataResult<List<Movie>> =
        DataResult.Success(movies)

    override suspend fun getMovieById(id: Int): DataResult<Movie> =
        DataResult.Success(movies.first { it.id == id })
}


val mockModelMovie = Movie(
    1,
    "nemo",
    "historia",
    "image",
    true,
    2
)

val defaultFakeMovies = listOf(
    mockModelMovie.copy(1),
    mockModelMovie.copy(2),
    mockModelMovie.copy(3),
    mockModelMovie.copy(4)
)
