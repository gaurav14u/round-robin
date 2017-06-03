package com.match.controller;
 
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.match.bean.Match;
import com.match.service.SchedulingService;

/**
 * @author shahg
 * Works only for even number of teams.
 */
@RestController
@RequestMapping("/teams")
public class MainController {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
	
	@Autowired
	SchedulingService schedulingService;
	
	  @RequestMapping(value = "/{teams}", method = RequestMethod.GET)
	  public @ResponseBody List<Match> getSchedule(@PathVariable Integer teams) {
		  
			List<Match> matches = schedulingService.getScheduleAsPerRoundRobin(teams);
			return matches;

		}
	  
	  @RequestMapping(value = "/html/{teams}", method = RequestMethod.GET)
	  public String getScheduleInHtml(@PathVariable Integer teams) {
		  
			List<Match> matches = schedulingService.getScheduleAsPerRoundRobin(teams);
			
			String html = scheduleInHtmlForDisplay(matches);
			return html;
		}

	/**
	 * Only for view on webpage.
	 */
	private String scheduleInHtmlForDisplay(List<Match> matches) {
		StringBuilder buf = new StringBuilder();
		buf.append("<html>" +
		           "<body>" +
		           "<table>" +
		           "<tr>" +
		           "<th>Team</th>" +
		           "<th>Opposition</th>" +
		           "<th>Venue</th>" +
		           "<th>Date</th>" +
		           "</tr>");
		
		for (Match match : matches) {
			buf.append("<tr><td>")
		       .append(match.getTeam1())
		       .append("</td><td>")
		       .append(match.getTeam2())
		       .append("</td><td>")
		       .append(match.getLocation())
		       .append("</td><td>")
		       .append(match.getDate())
		       .append("</td></tr>");
		}
		
		buf.append("</table>" +
		           "</body>" +
		           "</html>");
		String html = buf.toString();
		return html;
	}


}