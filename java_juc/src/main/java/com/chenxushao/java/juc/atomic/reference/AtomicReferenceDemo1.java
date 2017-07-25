package com.chenxushao.java.juc.atomic.reference;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo1 {

	public static void main(String[] args) {
		String initialReference = "the initially referenced string";
		AtomicReference<String> atomicReference = new AtomicReference<String>(initialReference);
		// AtomicReference atomicReference = new
		// AtomicReference("first value referenced");

		String reference =  atomicReference.get();
		System.out.println("reference: "+reference);

		// String initialReference = "the initially referenced string";
		AtomicReference<String> atomicStringReference = new AtomicReference<String>(
				initialReference);

		AtomicReference<String> atomicReference1 = new AtomicReference<String>();

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