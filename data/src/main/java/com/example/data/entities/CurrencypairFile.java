package com.example.data.entities;

import com.example.domain.entities.Currencypair;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CurrencypairFile {

    private final static int MILL_SECONDS_DIVIDER = 1_000_000;
    private final static int AMOUNT_TO_BE_DIVIDED = 100_000;

    public static class Id {
        public String $oid;
    }
    public static class NumberLong {
        public Long $numberLong;
    }
    public static class NumberInt {
        public Integer $numberInt;
    }

    public Id _id;
    public Long entryID;
    public NumberLong key;
    public NumberInt quoteType;
    public NumberLong time;
    public NumberInt bid;
    public NumberInt ask;
    public NumberInt bidVolume;
    public NumberInt askVolume;
    public NumberInt depth;
    public NumberInt positionNumber;
    public String compID;
    public NumberLong validTime;

    public Currencypair toDomain() {
        return Currencypair.builder()
                .id(_id.$oid)
                .entryID(entryID)
                .key(key.$numberLong)
                .quoteType(quoteType.$numberInt)
                .time(LocalDateTime.ofInstant(Instant.ofEpochMilli(time.$numberLong / MILL_SECONDS_DIVIDER), ZoneOffset.UTC))
                .bid((double) bid.$numberInt / AMOUNT_TO_BE_DIVIDED)
                .ask((double) ask.$numberInt / AMOUNT_TO_BE_DIVIDED)
                .bidVolume(bidVolume.$numberInt / AMOUNT_TO_BE_DIVIDED)
                .askVolume(askVolume.$numberInt / AMOUNT_TO_BE_DIVIDED)
                .depth(depth.$numberInt)
                .positionNumber(positionNumber.$numberInt)
                .compID(compID)
                .validTime(validTime.$numberLong)
                .build();
    }
}
