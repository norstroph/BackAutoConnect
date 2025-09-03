package com.AutoConnect.AutoConnect.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDTO {

    private String url;
    private Long count;
    private Long pages;
    private Long total;
    private String next;
    private String prev;
    private String first;
    private String last;
}


