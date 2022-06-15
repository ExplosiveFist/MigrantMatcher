package pt.migrantmatcher.strategies;

import java.util.List;

import pt.migrantmatcher.domain.Ajuda;

public interface SortStrategy {
	
	public void sortHelps(List<Ajuda> helplist);

}
