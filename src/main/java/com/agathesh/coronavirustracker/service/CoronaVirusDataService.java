package com.agathesh.coronavirustracker.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.agathesh.coronavirustracker.model.LocationStats;

//Spring boot service - creates an instance for @Service - Singleton 
//One instance for the whole application
//used by injecting. @Autowired (accessed by pvt variable)
//used in the place of business logics
//
@Service 
public class CoronaVirusDataService {

	public String VIRUS_DATA_URL="https://github.com/CSSEGISandData/COVID-19/blob/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv"; 
	
	private List<LocationStats> allStats= new ArrayList<>();
	public List<LocationStats> getAllStats() {
		return allStats;
	}
	
	

	@PostConstruct
	@Scheduled(cron="* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		/*
		 * HttpClient client=HttpClient.newHttpClient(); HttpRequest
		 * request=HttpRequest.newBuilder() .uri(URI.create(VIRUS_DATA_URL)) .build();
		 * HttpResponse<String> response=client.send(request,
		 * HttpResponse.BodyHandlers.ofString()); System.out.println(response.body());
		 */
		List<LocationStats> newStats= new ArrayList<>();
		Reader in = new FileReader("/coronavirus-tracker/src/main/java/com/agathesh/coronavirustracker/service/corona.csv");
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		for (CSVRecord record : records) {
			
			LocationStats locationstat=new LocationStats();
			
			locationstat.setState(record.get("Province/State"));
			locationstat.setCountry(record.get("Country/Region"));
			locationstat.setLatestStats(Integer.parseInt((record.get(record.size()-1))));
			locationstat.setDiff((Integer.parseInt((record.get(record.size()-1))))-(Integer.parseInt((record.get(record.size()-2)))));
			
			//adding to the list 
			newStats.add(locationstat);
		}
		//moving to allStats from new stats due to concorrrency issue 
		this.allStats=newStats;
	}
	
}
