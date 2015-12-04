package com.nt.bytebuddy;

public interface Forwarder<T, S> {
	T to(S target);
}