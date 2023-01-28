package com.example.springboot;

import com.example.domain.usecases.MainUsecase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class MainController {

	private final MainUsecase mainUseCase;

	@GetMapping("/")
	public String index() throws IOException {
		return mainUseCase.execute();
	}

	@GetMapping("/test")
	public String test() {
		return "test!";
	}

}
