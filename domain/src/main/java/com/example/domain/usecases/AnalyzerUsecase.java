package com.example.domain.usecases;

import com.example.domain.entities.BatchResult;
import com.example.domain.entities.Currencypair;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AnalyzerUsecase {

	private final static int FIRST_ITERATION = 0;
	private final static int LOW_INITIATOR_INT = 0;
	private final static double LOW_INITIATOR = 0;
	private final static double HIGH_INITIATOR = 999999999;
	private final static int MINUTES_BATCH_INTERVAL = 30;

	private static ArrayList<Double> bidBatchValues = new ArrayList<>();
	private static ArrayList<Double> askBatchValues = new ArrayList<>();

	public List<BatchResult> execute(List<Currencypair> currencyPairs) {
		List<BatchResult> batchResults = new ArrayList<>();
		final BatchResult[] batchResult = {initializeNewBatch(currencyPairs.get(FIRST_ITERATION).time)};

		currencyPairs.forEach(currencypair -> {
			if (isNewBatch(batchResult[FIRST_ITERATION].getFrom(), currencypair.time)) {

				batchResults.add(calculateMedian(batchResult[FIRST_ITERATION]));
				batchResult[FIRST_ITERATION] = initializeNewBatch(currencypair.time);
			}

			batchResult[FIRST_ITERATION] = updateBatchResult(batchResult[FIRST_ITERATION], currencypair);
		});

		batchResults.add(calculateMedian(batchResult[FIRST_ITERATION]));

		return batchResults;
	}

	private BatchResult calculateMedian(BatchResult batchResult) {
		return batchResult.toBuilder()
				.medianBid(calcMedian(bidBatchValues))
				.medianAsk(calcMedian(askBatchValues))
				.build();
	}

	private BatchResult initializeNewBatch(LocalDateTime from) {
		bidBatchValues = new ArrayList<>();
		askBatchValues = new ArrayList<>();

		return new BatchResult().toBuilder()
				.from(from)
				.maxBid(LOW_INITIATOR)
				.minBid(HIGH_INITIATOR)
				.averageBid(LOW_INITIATOR)
				.medianBid(LOW_INITIATOR)
				.bidVolume(LOW_INITIATOR_INT)
				.maxAsk(LOW_INITIATOR)
				.minAsk(HIGH_INITIATOR)
				.averageAsk(LOW_INITIATOR)
				.medianAsk(LOW_INITIATOR)
				.askVolume(LOW_INITIATOR_INT)
				.batchVolume(LOW_INITIATOR_INT)
				.build();
	}

	private boolean isNewBatch(LocalDateTime fromDateTime, LocalDateTime itemDateTime) {
		return itemDateTime.isAfter(fromDateTime.plusMinutes(MINUTES_BATCH_INTERVAL));
	}

	private BatchResult updateBatchResult(BatchResult batchResult, Currencypair currencypair) {

		if (currencypair.getAsk() == 0) {
			bidBatchValues.add(currencypair.getBid());
			return updateBid(batchResult, currencypair);
		} else {
			askBatchValues.add(currencypair.getAsk());
			return updateAsk(batchResult, currencypair);
		}
	}

	private BatchResult updateBid(BatchResult batchResult, Currencypair currencypair) {

		return batchResult.toBuilder()
				.to(currencypair.getTime())
				.maxBid(currencypair.getBid() > batchResult.getMaxBid() ? currencypair.getBid() : batchResult.getMaxBid())
				.minBid(currencypair.getBid() < batchResult.getMinBid() ? currencypair.getBid() : batchResult.getMinBid())
				.averageBid(calcAverage(batchResult.getAverageBid(), batchResult.getBidVolume(), currencypair.getBid()))
				.bidVolume(batchResult.getBidVolume() + 1)
				.batchVolume(batchResult.getBatchVolume() + 1)
				.build();
	}

	private BatchResult updateAsk(BatchResult batchResult, Currencypair currencypair) {

		return batchResult.toBuilder()
				.to(currencypair.getTime())
				.maxAsk(currencypair.getAsk() > batchResult.getMaxAsk() ? currencypair.getAsk() : batchResult.getMaxAsk())
				.minAsk(currencypair.getAsk() < batchResult.getMinAsk() ? currencypair.getAsk() : batchResult.getMinAsk())
				.averageAsk(calcAverage(batchResult.getAverageAsk(), batchResult.getAskVolume(), currencypair.getAsk()))
				.askVolume(batchResult.getAskVolume() + 1)
				.batchVolume(batchResult.getBatchVolume() + 1)
				.build();
	}

	private double calcAverage(double currentAverage, int volume, double valueToAdd) {
		return (currentAverage * volume + valueToAdd) / (volume + 1);
	}

	private double calcMedian(ArrayList<Double> numArrayList) {
		Collections.sort(numArrayList);

		if (numArrayList.size() % 2 == 0) {
			return (numArrayList.get(numArrayList.size() / 2) +
					numArrayList.get(numArrayList.size() / 2 - 1)) / 2;
		}
		else {
			return numArrayList.get(numArrayList.size() / 2);
		}
	}

}
