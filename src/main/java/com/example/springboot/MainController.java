package com.example.springboot;

import com.example.domain.usecases.MainUsecase;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class MainController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	private final MainUsecase mainUseCase;

	@GetMapping("/")
	public String index() throws IOException {

		String result = mainUseCase.execute();
		LOGGER.info(result);
		return result;
	}

}
