package com.angiedev.rickmortyapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.angiedev.rickmortyapp.data.remote.ApiService
import com.angiedev.rickmortyapp.data.remote.responses.characters.CharactersWrapperResponse
import com.angiedev.rickmortyapp.domain.model.CharacterModel

class CharactersPagingSource(
    private val api: ApiService
) : PagingSource<Int, CharacterModel>() {

    // Page to load again when the data is invalidated: the one holding the item the user was looking at
    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.let { page ->
                page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
            }
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> =
        try {
            val page = params.key ?: 1
            val response: CharactersWrapperResponse = api.getAllCharacters(page)
            val characters = response.results
            val prev = if (page > 1) page - 1 else null
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
