package com.issuemoa.board.application;

import com.issuemoa.board.domain.board.favorite.BoardFavorites;
import com.issuemoa.board.domain.board.favorite.BoardFavoritesRepository;
import com.issuemoa.board.presentation.dto.BoardFavoritesResponse;
import com.issuemoa.board.presentation.dto.BoardFavoritesSave;
import com.issuemoa.common.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardFavoritesService {
    private final BoardFavoritesRepository boardFavoritesRepository;
    private final TokenProvider tokenProvider;

    public BoardFavoritesResponse save(HttpServletRequest request, BoardFavoritesSave boardFavoritesSave){
        Long userId = tokenProvider.getUserId(request);
        BoardFavorites favorites = boardFavoritesRepository.save(boardFavoritesSave.toEntity(userId));
        return BoardFavoritesResponse.toDto(favorites);
    }

    public List<BoardFavoritesResponse> findByUserId(HttpServletRequest request){
        Long userId = tokenProvider.getUserId(request);
        return boardFavoritesRepository.findByUserId(userId, Sort.by(Sort.Direction.DESC, "registerDateTime"));
    }
}
