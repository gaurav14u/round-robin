package com.match.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.match.bean.Match;

@Service
public class SchedulingService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");

	public List<Match> getScheduleAsPerRoundRobin(Integer teams) {
		int prevTeam1 = -1, prevTeam2 = -1;
		// Generate the schedule using round robin algorithm.
		int totalRounds = (teams - 1) * 2;
		int halfRoundMark = (totalRounds / 2);
		int matchesPerRound = teams / 2;
		LocalDate date = getCurrentDate();

		List<Match> matches = new ArrayList<>();
		for (int round = 0; round < totalRounds; round++) {
			for (int match = 0; match < matchesPerRound; match++) {
				int home = (round + match) % (teams - 1);
				int away = (teams - 1 - match + round) % (teams - 1);

				// Last team stays in the same place while the others
				// rotate around it.
				if (match == 0) {
					away = teams - 1;
				}

				date = checkforDateCriteria(prevTeam1, prevTeam2, date, home, away);

				// each team will play 2 matches with other team.
				if (round < halfRoundMark) {
					matches.add(new Match("team" + (home + 1), "team" + (away + 1),
							"Ground of team" + (home + 1), date.format(formatter)));

				} else {
					matches.add(new Match("team" + (away + 1), "team" + (home + 1),
							"Ground of team" + (away + 1), date.format(formatter)));
				}

				prevTeam1 = home;
				prevTeam2 = away;
			}
		}
		log.info(matches.size() + " matches will be played in this tournament.");
		return matches;
	}

	/**
	 * No teams should play on consecutive days.
	 */
	private static LocalDate checkforDateCriteria(int prevTeam1, int prevTeam2, LocalDate date, int home, int away) {
		if (home == prevTeam1 || home == prevTeam2 || away == prevTeam1 || away == prevTeam2) {
			date = getCurrentPlusDate2(date);
		} else {
			date = getCurrentPlusDate1(date);
		}
		return date;
	}

	public static LocalDate getCurrentDate() {
		return LocalDate.now();
	}

	public static LocalDate getCurrentPlusDate1(LocalDate date) {
		return date.plusDays(1);
	}

	public static LocalDate getCurrentPlusDate2(LocalDate date) {
		return date.plusDays(2);
	}
}
