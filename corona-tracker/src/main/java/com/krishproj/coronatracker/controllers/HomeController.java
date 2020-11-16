package com.krishproj.coronatracker.controllers;

import com.krishproj.coronatracker.models.LocationStats;
import com.krishproj.coronatracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService virusService;
    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats =  virusService.getAllStarts();
        int totalCases = allStats.stream().mapToInt(LocationStats::getLatestTotalCases).sum();
        int totalNewCases = allStats.stream().mapToInt(LocationStats::getDiffFromPreviousDay).sum();


        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalReportedCases",totalCases);
        model.addAttribute("totalNewCases",totalNewCases);
        return "home";
    }
}
