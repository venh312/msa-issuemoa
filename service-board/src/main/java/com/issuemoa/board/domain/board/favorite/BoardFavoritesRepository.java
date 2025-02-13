package com.issuemoa.board.domain.board.favorite;

import com.issuemoa.board.presentation.dto.BoardFavoritesResponse;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BoardFavoritesRepository extends MongoRepository<BoardFavorites, String> {
    List<BoardFavoritesResponse> findByUserId(Long userId, Sort sort);
}
