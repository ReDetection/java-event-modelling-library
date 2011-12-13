package ru.buglakov.study.term7.modelling.jpss.blocks;

import java.util.ArrayList;
import java.util.List;

import ru.buglakov.study.term7.modelling.jpss.Transaction;
import ru.buglakov.study.term7.modelling.jpss.blocks.DiffStopwatch.LogEntry;

public class DiffStopwatchFinish extends Stat {
	private List<LogEntry> log = new ArrayList<>();

	private final DiffStopwatch diffStopwatch;

	protected DiffStopwatchFinish(DiffStopwatch diffStopwatch) {
		this.diffStopwatch = diffStopwatch;
		
	}

	@Override
	public void receive(Transaction transaction) {
		log.add(diffStopwatch.stop(transaction.getKey()));
		transaction.sendTo(getNext());
		
	}

}
