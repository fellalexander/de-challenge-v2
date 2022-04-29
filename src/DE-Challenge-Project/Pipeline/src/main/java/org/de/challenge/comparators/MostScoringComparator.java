package org.de.challenge.comparators;

import org.de.challenge.domain.Stats;

import java.io.Serializable;
import java.util.Comparator;

public class MostScoringComparator implements Comparator<Stats>, Serializable {
    @Override
    public int compare(Stats o1, Stats o2) {
        return o1.getTotalGoals()-o2.getTotalGoals();
    }
}