package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Chapter implements Serializable {

    private String id;
    private String title;
    private String size;
    private String duration;
    private String audio;
    private String album_id;
}