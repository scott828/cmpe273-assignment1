package edu.sjsu.cmpe.library.util;

import java.util.concurrent.atomic.AtomicLong;

public final class SequenceGenerator {
	private static final AtomicLong sequenceISBN = new AtomicLong();
	private static final AtomicLong sequenceReviewId = new AtomicLong();
	private static final AtomicLong sequenceAuthorId = new AtomicLong();

	private SequenceGenerator() {
	}

	public static long nextISBN() {
		return sequenceISBN.incrementAndGet();
	}

	public static long nextReviewId() {
		return sequenceReviewId.incrementAndGet();
	}

	public static long nextAuthorId() {
		return sequenceAuthorId.incrementAndGet();
	}
}