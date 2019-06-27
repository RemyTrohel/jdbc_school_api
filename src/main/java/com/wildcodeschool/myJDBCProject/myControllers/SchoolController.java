package com.wildcodeschool.myJDBCProject.myControllers;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        @RequestParam(required = true) String name,
        @RequestParam(required = true) int capacity,
        @RequestParam(required = true) String country
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

    @PutMapping("/api/schools/{id}")
    public School update(
        @PathVariable int id,
        @RequestParam(required = true) String name,
        @RequestParam(required = true) Integer capacity,
        @RequestParam(required = true) String country
    ) {
        School school = SchoolRepository.selectById(id);
        SchoolRepository.update(
            id,
            name != null ? name : school.getName(),
            capacity != null ? capacity : school.getCapacity(),
            country != null ? country : school.getCountry()
        );
        return SchoolRepository.selectById(id);
    }
}