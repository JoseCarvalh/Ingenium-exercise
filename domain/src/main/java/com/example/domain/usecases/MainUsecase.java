package com.example.domain.usecases;

import com.example.domain.dataInterface.MainFileReaderImpl;
import com.example.domain.entities.Currencypair;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MainUsecase {

	private final MainFileReaderImpl mainFileReader;

	public String execute() throws IOException {
		List<Currencypair> fromFile = mainFileReader.getListClass();

		// TODO divide per dates groups
			// TODO create List of Lists
			// TODO getFirstDate
			// TODO get all between firstDate and fistDate + 30 min
			// TODO put List(i++)
			// TODO repeat

		// TODO create get bigger bid
		// TODO create get bigger ask
		// TODO create get smaller bid
		// TODO create get smaller ask

		// TODO create get average
		// TODO create get median

		// TODO in the forEach increment the volume (cumulative bid and ask)
		return fromFile.get(0).toString();
	}

}
