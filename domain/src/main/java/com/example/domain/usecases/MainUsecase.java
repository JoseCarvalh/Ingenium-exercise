package com.example.domain.usecases;

import com.example.domain.dataInterface.MainFileReaderImpl;
import com.example.domain.entities.BatchResult;
import com.example.domain.entities.Currencypair;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MainUsecase {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainUsecase.class);

	private final MainFileReaderImpl mainFileReader;
	private final AnalyzerUsecase analyzerUseCase;

	public String execute() throws IOException {

		List<Currencypair> fromFile = getFromFile();
		List<BatchResult> batchResults = getAnalysedData(fromFile);

		return batchResults.stream().map(BatchResult::toJson).collect(Collectors.toList()).toString();
	}

	private List<Currencypair> getFromFile() throws IOException {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		List<Currencypair> fromFile = mainFileReader.getListClass();
		stopWatch.stop();

		LOGGER.info("Successfully read from file, took: [{}] ms", stopWatch.getTotalTimeMillis());

		return fromFile;
	}

	private List<BatchResult> getAnalysedData(List<Currencypair> fromFile) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		List<BatchResult> batchResults = analyzerUseCase.execute(fromFile);
		stopWatch.stop();

		LOGGER.info("Successfully analysed the data, took: [{}] ms", stopWatch.getTotalTimeMillis());

		return batchResults;
	}


}
