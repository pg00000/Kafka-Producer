package com.example.demo.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.example.demo.vo.SlotForecastDetails;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class ReadSlotForecastFromCsv {

    public static List<SlotForecastDetails> readObjectsFromCsv(File file) throws IOException {
        CsvSchema bootstrap = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();

        MappingIterator<SlotForecastDetails> mappingItr = csvMapper.readerFor(SlotForecastDetails.class).with(bootstrap)
                .readValues(file);

        return mappingItr.readAll();
    }

    public static void writeAsJson(List<Map<?, ?>> data, File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, data);
    }
}

