package league;


/**
 * Class that represents a football team
 * @author Angela
 *
 */
public class Team {

	private String name;
	private int gamesPlayed;
	private int goalsFor;
	private int goalsAgainst;
	private int wins;
	private int draws;
	private int losses;
	
	/**
	 * Constructor
	 * @param name
	 */
	public Team(String name){
		this.setName(name);
	}
	
	/**
	 * Gets the team name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the team name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * gets the current games played
	 * @return
	 */
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	
	/**
	 * Increments the games played
	 */
	public void incrementGamesPlayed() {
		this.gamesPlayed++;
	}
	
	/**
	 * Gets the goals scored
	 * @return
	 */
	public int getGoalsFor() {
		return goalsFor;
	}
	
	/**
	 * Increases the goals for total
	 * @param scored
	 */
	public void increaseGoalsFor(int scored) {
		this.goalsFor += scored;
	}
	
	/**
	 * Gets the number of wins
	 * @return
	 */
	public int getWins() {
		return wins;
	}
	
	/**
	 * Increments the number of wins
	 */
	public void incrementWins() {
		this.wins++;
	}
	
	/**
	 * Gets the goals conceded
	 * @return
	 */
	public int getGoalsAgainst() {
		return goalsAgainst;
	}
	
	/**
	 * Increases the goals against
	 * @param goalsConceeded
	 */
	public void increaseGoalsAgainst(int goalsConceeded) {
		this.goalsAgainst += goalsConceeded;
	}
	
	/**
	 * gets the draws total
	 * @return
	 */
	public int getDraws() {
		return draws;
	}
	
	/**
	 * Increments the draws
	 */
	public void incrementDraws() {
		this.draws++;
	}
	
	/**
	 * gets the losses
	 */
	public int getLosses() {
		return losses;
	}
	
	/**
	 * Increments the losses total
	 */
	public void incrementLosses() {
		this.losses++;
	}
	
	/**
	 * Calculates and returns the points total
	 * @return
	 */
	public int pointsTotal(){
		return this.getWins()*3 + this.getDraws();
	}
	
	/**
	 * Formats and then returns the stats for the team
	 * @return
	 */
	public String stats(){
		String newLine = "\n";
		StringBuilder sb = new StringBuilder();
		sb.append("Games played  : ");
		sb.append(this.getGamesPlayed());
		sb.append(newLine);
		sb.append("Goals for     : ");
		sb.append(this.getGoalsFor());
		sb.append(newLine);
		sb.append("Goals against : ");
		sb.append(this.getGoalsAgainst());
		sb.append(newLine);
		sb.append("Wins          : ");
		sb.append(this.getWins());
		sb.append(newLine);
		sb.append("Draws         : ");
		sb.append(this.getDraws());
		sb.append(newLine);
		sb.append("Losses        : ");
		sb.append(this.getLosses());
		sb.append(newLine);
		sb.append("Points        : ");
		sb.append(this.pointsTotal());
		sb.append(newLine);
			
		return sb.toString();
	}
}