package league;

import java.util.Comparator;

/**
 * Comparator used to compare the total points of a team in ascending order
 * @author Angela
 */
public class ComparePoints implements Comparator<Team> {

	@Override
	public int compare(Team t1, Team t2) {
		return t2.pointsTotal() - t1.pointsTotal();
	}

}