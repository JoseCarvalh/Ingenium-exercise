package com.example.data;

import com.example.data.entities.CurrencypairFile;
import com.example.domain.dataInterface.MainFileReaderImpl;
import com.example.domain.entities.Currencypair;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MainFileReader implements MainFileReaderImpl {

	private final static String FILE = "./resource/euraud.json";

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public List<Currencypair> getListClass() throws IOException {

		File file = new File(FILE);
		String content = formatContent(Files.asCharSource(file, Charsets.UTF_8).read());

		return objectMapper.readValue(content, new TypeReference<List<CurrencypairFile>>(){})
				.stream().map(CurrencypairFile::toDomain).collect(Collectors.toList());
	}

	private String formatContent(String content) {
		content = "[" + content; // start array
		content = content.replaceAll("\n", ","); // separate each iteration with ","
		content = content.replaceAll(",$", "]"); // replace last "," with "]"
		return content;
	}

}
