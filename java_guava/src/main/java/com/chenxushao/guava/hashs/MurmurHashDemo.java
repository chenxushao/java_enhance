package com.chenxushao.guava.hashs;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/*
 * 
 * MurmurHash，这是一个简单高效且还是分布式的算法，在许多语言中都有着很好的支持。我们并不是说要用它来取代Java的hashCode方法，不过如果你想要生成大量的哈希值而32位已经不够用了，但又希望能有一个高效而不会影响到性能的算法，那肯定就是它了
 * 
 */
public class MurmurHashDemo {

	public static void main(String[] args) {
		HashFunction hf = Hashing.murmur3_128(); // 32bit version available as
													// well
		HashCode hc = hf.newHasher().putLong(1L)
				.putString("cxs", Charsets.UTF_8).hash();

		System.out.println(hc.padToLong());
		System.out.println(7690221543700271581L % 3);
		System.out.println(7690221543700271582L % 3);
		System.out.println(7690221543700271583L % 3);
		System.out.println(7690221543700271584L % 3);
		System.out.println(7690221543700271585L % 3);
	}

}
