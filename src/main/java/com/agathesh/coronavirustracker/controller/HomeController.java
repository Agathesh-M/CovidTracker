package com.agathesh.coronavirustracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agathesh.coronavirustracker.model.LocationStats;
import com.agathesh.coronavirustracker.service.CoronaVirusDataService;

@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusDataService coronaVirusDataService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats=coronaVirusDataService.getAllStats();
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestStats()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiff()).sum();
		model.addAttribute("locationStats",allStats );
		model.addAttribute("totalReportedCases",totalReportedCases );
		model.addAttribute("totalNewCases", totalNewCases);
		
		return "home";
		
	}
}
