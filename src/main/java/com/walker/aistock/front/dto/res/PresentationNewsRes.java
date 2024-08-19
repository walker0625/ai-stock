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

    String headline;
    String summary;

    String image;
    String url;

    public PresentationNewsRes(News news) {
        this.headline = news.getHeadline();
        this.summary = news.getSummary();
        this.image = news.getImage();
        this.url = news.getUrl();
    }

}