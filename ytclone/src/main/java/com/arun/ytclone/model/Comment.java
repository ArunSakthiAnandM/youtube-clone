package com.arun.ytclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    private String id;

    private String text;

    private String authorID;

    private Integer likes;

    private Integer disLikes;
}
