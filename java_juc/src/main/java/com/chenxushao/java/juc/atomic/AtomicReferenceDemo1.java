package com.chenxushao.java.juc.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo1 {

	public static void main(String[] args) {
		String initialReference = "the initially referenced string";
		AtomicReference atomicReference = new AtomicReference(initialReference);
		// AtomicReference atomicReference = new
		// AtomicReference("first value referenced");

		String reference = (String) atomicReference.get();

		// String initialReference = "the initially referenced string";
		AtomicReference<String> atomicStringReference = new AtomicReference<String>(
				initialReference);

		AtomicReference atomicReference1 = new AtomicReference();

		atomicReference1.set("New object referenced");

		// String initialReference = "initial value referenced";

		// AtomicReference<String> atomicStringReference =
		// new AtomicReference<String>(initialReference);

		String newReference = "new value referenced";
		boolean exchanged = atomicStringReference.compareAndSet(
				initialReference, newReference);
		System.out.println("exchanged: " + exchanged);

		exchanged = atomicStringReference.compareAndSet(initialReference,
				newReference);
		System.out.println("exchanged: " + exchanged);

	}
}
