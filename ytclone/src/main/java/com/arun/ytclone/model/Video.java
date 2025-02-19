package com.arun.ytclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document(value = "Video")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    private String id;

    private String title;

    private String url;

    private String description;

    private String userID;

    private String thumbnailUrl;

    private Integer views;

    private Integer likes;

    private Integer disLikes;

    private Set<String> tags;

    private List<Comment> comments;

    private VideoStatus videoStatus;
}
