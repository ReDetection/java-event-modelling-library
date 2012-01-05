package ru.buglakov.study.term7.modelling.jpss.exceptions;

import ru.buglakov.study.term7.modelling.jpss.blocks.Waitable;

public class BlockIsBusyException extends RuntimeException {
    private final Waitable w;

	public BlockIsBusyException(Waitable w) {
        super();
        this.w = w;
	}

	public BlockIsBusyException(String message) {
		super(message);
        w=null;
	}

	public BlockIsBusyException(Throwable cause) {
		super(cause);
        w=null;
	}

	public BlockIsBusyException(String message, Throwable cause) {
		super(message, cause);
        w=null;
	}

	public BlockIsBusyException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
        w=null;
	}

    public Waitable getWaitable() {
        return w;
    }

}
