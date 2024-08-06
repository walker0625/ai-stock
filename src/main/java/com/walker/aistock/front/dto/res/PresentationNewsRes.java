package com.walker.aistock.front.dto.res;

import com.walker.aistock.backend.data.entity.News;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PresentationNewsRes {

    private String headline;
    private String summary;

    private String image;
    private String url;

    public PresentationNewsRes(News news) {
        this.headline = news.getHeadline();
        this.summary = news.getSummary();
        this.image = news.getImage();
        this.url = news.getUrl();
    }

}