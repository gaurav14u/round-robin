package com.match.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.match.bean.Match;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerTest {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");

	@Autowired
	MainController mainController;
	
    /**
     * Check if Tax data loaded correctly.
     */
    @Test
    public void schedulingTest() {
    	 List<Match> matches = mainController.getSchedule(4);
    	 
    	 // total matches
     	 org.junit.Assert.assertEquals(12, matches.size());
     	 
     	 // each team should play with other team at home ground
     	 List<Match> collect = matches.stream().filter(p -> p.getTeam1().equals("team1")).collect(Collectors.toList());
     	 org.junit.Assert.assertEquals(3, collect.size());

     	 // each team should play with other team away
     	 List<Match> collect2 = matches.stream().filter(p -> p.getTeam2().equals("team1")).collect(Collectors.toList());
     	 org.junit.Assert.assertEquals(3, collect2.size());

     	 List<Match> collect3 = matches.stream().filter(p -> p.getTeam1().equals("team2")).collect(Collectors.toList());
     	 org.junit.Assert.assertEquals(3, collect3.size());
     	 
     	 List<Match> collect4 = matches.stream().filter(p -> p.getTeam2().equals("team2")).collect(Collectors.toList());
     	 org.junit.Assert.assertEquals(3, collect4.size());

     	 List<Match> collect5 = matches.stream().filter(p -> p.getTeam1().equals("team3")).collect(Collectors.toList());
     	 org.junit.Assert.assertEquals(3, collect5.size());
     	 
     	 List<Match> collect6 = matches.stream().filter(p -> p.getTeam2().equals("team3")).collect(Collectors.toList());
     	 org.junit.Assert.assertEquals(3, collect6.size());

     	 List<Match> collect7 = matches.stream().filter(p -> p.getTeam1().equals("team4")).collect(Collectors.toList());
     	 org.junit.Assert.assertEquals(3, collect7.size());
     	 
     	 List<Match> collect8 = matches.stream().filter(p -> p.getTeam2().equals("team4")).collect(Collectors.toList());
     	 org.junit.Assert.assertEquals(3, collect8.size());

     	 List<LocalDate> dates = new ArrayList<>();
     	 for (Match match : collect8) {
			String date = match.getDate();
			LocalDate parse = LocalDate.parse(date,formatter);
			dates.add(parse);
		}

     	 // check none of the teams play consecutive days.
     	 for(int i=0;i<matches.size() -1 ;i++){
     		Match match = matches.get(i);
     		Match nextMatch = matches.get(i+1);

     		LocalDate date = LocalDate.parse(match.getDate(),formatter);
     		LocalDate nextDate = LocalDate.parse(nextMatch.getDate(),formatter);

     		int dateDiff = nextDate.compareTo(date);
     		if(dateDiff == 1){
     			org.junit.Assert.assertNotEquals(match.getTeam1(), nextMatch.getTeam1());
     			org.junit.Assert.assertNotEquals(match.getTeam1(), nextMatch.getTeam2());
     			org.junit.Assert.assertNotEquals(match.getTeam2(), nextMatch.getTeam1());
     			org.junit.Assert.assertNotEquals(match.getTeam2(), nextMatch.getTeam2());

     		}
     	 }
    }
}
