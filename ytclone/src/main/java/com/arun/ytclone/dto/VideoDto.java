package com.arun.ytclone.dto;

import com.arun.ytclone.model.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDto {
    private String id;

    private String title;

    private String url;

    private String description;

    private String thumbnailUrl;

    private Set<String> tags;

    private VideoStatus videoStatus;
}
