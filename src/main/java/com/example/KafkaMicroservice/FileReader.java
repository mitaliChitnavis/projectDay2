package com.example.KafkaMicroservice;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.example.KafkaMicroservice.CSVRecord;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
@Component
public class FileReader {
	private static final String SAMPLE_CSV_FILE_PATH = "/home/edyoda/CSVToKafka/testInputData.csv";
	CsvToBean<CSVRecord> csvToBean;

	public List<CSVRecord> loadFileIntoBean()
	{
		Reader reader; 
		try { 
		    reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));

		    csvToBean = new CsvToBeanBuilder(reader)
					.withType(CSVRecord.class)
					.withIgnoreLeadingWhiteSpace(true)
					.build();
		

		List<CSVRecord> recordList = csvToBean.parse();
		// print details of Bean object 
		/*for (CSVRecord e : recordList) { 
			System.out.println("in for loop");
		    System.out.println(e.getEmpId()); 
		    System.out.println(e.getEmpName());
		}*/ 
			//runProducer(recordList);
		return recordList;
		//runConsumer();
		} 
		catch (IOException e) { 

		    // TODO Auto-generated catch block 
		    e.printStackTrace(); 
		} 
return null;
	}
}
