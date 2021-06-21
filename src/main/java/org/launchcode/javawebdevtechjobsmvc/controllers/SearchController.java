package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping(value = "results")                //from form tag   V 3.3-4 two param, plus annotation
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Job> jobs;
        if (searchTerm.equals("all") || searchTerm.equals("")) {
            jobs = JobData.findAll();
        } else {                                                    //locale.ROOT? or english?
            jobs = JobData.findByColumnAndValue(searchType.toLowerCase(Locale.ROOT), searchTerm.toLowerCase(Locale.ROOT));
        }


        model.addAttribute("columns", columnChoices); //from search
        model.addAttribute("jobs", jobs);



        return "search"; //from search


    }




    // TODO #3 - Create a handler to process a search request and render the updated search view.



}
