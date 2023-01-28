package com.example.domain.dataInterface;

import com.example.domain.entities.Currencypair;

import java.io.IOException;
import java.util.List;

public interface MainFileReaderImpl {

    List<Currencypair> getListClass() throws IOException;
}
