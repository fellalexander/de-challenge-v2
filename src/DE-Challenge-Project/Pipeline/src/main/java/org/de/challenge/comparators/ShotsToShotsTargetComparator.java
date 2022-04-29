package org.de.challenge.comparators;

import org.de.challenge.domain.Stats;

import java.io.Serializable;
import java.util.Comparator;

public class ShotsToShotsTargetComparator implements Comparator<Stats>, Serializable {
    @Override
    public int compare(Stats o1, Stats o2) {
        return Double.compare(o1.getShotsToShotsTargetRatio(), o2.getShotsToShotsTargetRatio());
    }
}