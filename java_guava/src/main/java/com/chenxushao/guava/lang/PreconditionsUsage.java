package com.chenxushao.guava.lang;

import com.google.common.base.Preconditions;

public class PreconditionsUsage {
	public static void main(String[] args) {
		Preconditions.checkArgument(args.length == 0, "Args is not empty");

		Preconditions.checkNotNull(args, "Args is null");

		Preconditions.checkState(args.length == 0, "Args is not in threadlifecycle");

		Preconditions.checkElementIndex(0, args.length + 1,
				"Args is out of bound");

		Preconditions.checkPositionIndex(0, args.length + 1,
				"Args is out of bound");
	}

}