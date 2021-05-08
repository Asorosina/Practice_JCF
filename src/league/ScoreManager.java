package league;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 
 * @author Angela
 */
public class ScoreManager {

	/**
	 * Stores the results from file for processing
	 */
	private static ArrayList<String> results = new ArrayList<String>();

	/**
	 * Map to store the team names (key) and the the team stats (values)
	 */
	private static Map<String, Team> teams = new HashMap<String, Team>();

	private final static int WIN_POINTS = 3;
	private final static int DRAW_PONITS = 1;
	private final static int LOSS_POINTS = 0;

	/**
	 * Start point for the app
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		readScoresFile();
		processResults();
		showAllTeamsStats();
		showLeague();
	}

	/**
	 * Reads the scores line by line and stores in an arraylist
	 */
	public static void readScoresFile() {
		try {
			FileReader fr = new FileReader(new File("Results.txt"));
			BufferedReader br = new BufferedReader(fr);

			String result = br.readLine();
			while (result != null) {
				results.add(result);
				result = br.readLine();
			}

			System.out.println(results.toString());

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Process the results line by line
	 */
	public static void processResults() {

		String[] tokenedResult = null;
		String team1, team2;
		int team1Goals, team2Goals, team1Pts, team2Pts;

		for (String result : results) {

			tokenedResult = result.split(" ");

			team1 = tokenedResult[0];
			team1Goals = Integer.parseInt(tokenedResult[1]);
			team2Goals = Integer.parseInt(tokenedResult[2]);
			team2 = tokenedResult[3];
			if (team1Goals > team2Goals) {
				team1Pts = WIN_POINTS;
				team2Pts = LOSS_POINTS;
			} else if (team1Goals < team2Goals) {
				team1Pts = LOSS_POINTS;
				team2Pts = WIN_POINTS;
			} else {
				team1Pts = DRAW_PONITS;
				team2Pts = DRAW_PONITS;
			}
			
			updateTeamStats(team1, team1Goals, team2Goals, team1Pts);
			updateTeamStats(team2, team2Goals, team1Goals, team2Pts);

		}
	}

	/**
	 * Updates / stores the team stats.
	 * 
	 * @param team
	 * @param goalsScored
	 * @param goalsConceeded
	 * @param teamPts
	 */
	public static void updateTeamStats(String team, int goalsScored, int goalsConceeded, int teamPts) {
		
		if (teams.containsKey(team)) {

			Team teamDetails = teams.get(team);
			teamDetails.incrementGamesPlayed();
			teamDetails.increaseGoalsFor(goalsScored);
			teamDetails.increaseGoalsAgainst(goalsConceeded);

			switch (teamPts) {
			case WIN_POINTS:
				teamDetails.incrementWins();
				break;
			case DRAW_PONITS:
				teamDetails.incrementDraws();
				break;
			case LOSS_POINTS:
				teamDetails.incrementLosses();
				break;
			default:
				break;
			}

		} else {
			teams.put(team, new Team(team));
			updateTeamStats(team, goalsScored, goalsConceeded, teamPts);
		}
	}

	/**
	 * Shows each team stats to screen
	 */
	public static void showAllTeamsStats() {
		SortedMap<String, Team> sortedTeams = new TreeMap<>(teams);
		for (String team : sortedTeams.keySet()) {
			System.out.println(team);
			Team t = teams.get(team);
			System.out.println(t.stats() + "\n");
		}
	}

	/**
	 * Shows the league details 
	 *
	 */
	public static void showLeague() {
		List<Team> allTeams = new ArrayList<Team>(teams.values());

		Collections.sort(allTeams, new ComparePoints());

		System.out.printf("%-20s %-6s %-5s %-10s %-2s %-2s %-8s %-6s\n", "Team", "Played", "For", "Against", "W", "D",
				"L", "Points");
		for (Team t : allTeams) {
			System.out.printf("%-20s %-6d %-5d %-10d %-2d %-2d %-8d %-6d\n", t.getName(), t.getGamesPlayed(),
					t.getGoalsFor(), t.getGoalsAgainst(), t.getWins(), t.getDraws(), t.getLosses(), t.pointsTotal());
		}
	}
}
