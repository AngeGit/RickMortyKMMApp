package com.angiedev.rickmortyapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.angiedev.rickmortyapp.data.remote.ApiService
import com.angiedev.rickmortyapp.data.remote.responses.CharactersWrapperResponse
import com.angiedev.rickmortyapp.domain.model.CharacterModel

class CharactersPagingSource(
    private val api: ApiService
) : PagingSource<Int, CharacterModel>() {

    // Responsible for knowing when we need to load more data
    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> =
        try {
            val page = params.key ?: 1
            val response: CharactersWrapperResponse = api.getAllCharacters(page)
            val characters = response.results
            val prev = if (page > 0) -1 else null
            val nextPage = if (response.info.next != null) page + 1 else null

            LoadResult.Page(
                data = characters.map { it.toDomain() },
                prevKey = prev,
                nextKey = nextPage
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}
