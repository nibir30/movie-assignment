package com.example.movie_backend.data.ReqData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddFavoriteMovieReqData {
    private String email;
    private Long movieId;
    private Boolean addFavorite;
}
