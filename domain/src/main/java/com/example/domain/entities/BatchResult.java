package com.example.domain.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BatchResult {

    private final static Gson PRETTY_GSON = new GsonBuilder().create();

    public LocalDateTime from;
    public LocalDateTime to;
    public Double maxBid;
    public Double minBid;
    public Double averageBid;
    public Double medianBid;
    public Integer bidVolume;
    public Double maxAsk;
    public Double minAsk;
    public Double averageAsk;
    public Double medianAsk;
    public Integer askVolume;
    public Integer batchVolume;

    public String toJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("from", from.toString());
        map.put("to", to.toString());
        map.put("maxBid", maxBid);
        map.put("minBid", minBid);
        map.put("averageBid", averageBid);
        map.put("medianBid", medianBid);
        map.put("bidVolume", bidVolume);
        map.put("maxAsk", maxAsk);
        map.put("minAsk", minAsk);
        map.put("averageAsk", averageAsk);
        map.put("medianAsk", medianAsk);
        map.put("askVolume", askVolume);
        map.put("batchVolume", batchVolume);

        return PRETTY_GSON.toJson(map);
    }

}
