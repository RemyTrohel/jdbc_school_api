package com.wildcodeschool.myJDBCProject.myControllers;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.wildcodeschool.myJDBCProject.entities.School;
import com.wildcodeschool.myJDBCProject.repositories.SchoolRepository;

@Controller
@ResponseBody
public class SchoolController {

    @GetMapping("/api/schools")
    public List<School> getSchools(@RequestParam(defaultValue="%") String country) {
        return SchoolRepository.selectByCountry(country);
    }

    @PostMapping("/api/schools")
    public School store(
        @RequestParam String name,
        @RequestParam int capacity,
        @RequestParam String country
    ) {
        int idGeneratedByInsertion = SchoolRepository.insert(
            name,
            capacity,
            country
        );
        return SchoolRepository.selectById(
            idGeneratedByInsertion
        );
    }
}