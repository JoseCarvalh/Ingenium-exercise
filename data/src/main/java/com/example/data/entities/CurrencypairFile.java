package com.example.data.entities;

import com.example.domain.entities.Currencypair;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;

import static org.springframework.util.ReflectionUtils.findField;
import static org.springframework.util.ReflectionUtils.makeAccessible;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CurrencypairFile {

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
                .time(time.$numberLong)
                .bid(bid.$numberInt) // TODO dive by 100000 and convert to long
                .ask(ask.$numberInt) // TODO dive by 100000 and convert to long
                .bidVolume(bidVolume.$numberInt)
                .askVolume(askVolume.$numberInt)
                .depth(depth.$numberInt)
                .positionNumber(positionNumber.$numberInt)
                .compID(compID)
                .validTime(validTime.$numberLong)
                .build();
    }

    public static Currencypair cast(Object o) throws IllegalAccessException, NoSuchFieldException {
        Field field = findField(CurrencypairFile.class, "entryID");
        makeAccessible(field);
        return Currencypair.builder()
                .entryID((Long) field.get(o))
                .build();
    }
}
