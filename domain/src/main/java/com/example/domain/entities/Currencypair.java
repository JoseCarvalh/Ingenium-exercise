package com.example.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Currencypair {

    private String id;
    private Long entryID;
    private Long key;
    private Integer quoteType;
    private Long time;
    private Integer bid;
    private Integer ask;
    private Integer bidVolume;
    private Integer askVolume;
    private Integer depth;
    private Integer positionNumber;
    private String compID;
    private Long validTime;
}
