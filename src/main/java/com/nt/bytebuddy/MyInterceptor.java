package com.nt.bytebuddy;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;
import net.bytebuddy.implementation.bind.annotation.DefaultCall;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

public class MyInterceptor {

	@RuntimeType
	@BindingPriority(BindingPriority.DEFAULT * 3)
	public static <T> T interceptSuper(@SuperCall Callable<T> callable, @AllArguments Object[] allArguments, @Origin Method method, @Origin Class<?> clazz) throws Exception {
		long startTime = System.currentTimeMillis();
		try {
			return callable.call();
		} catch (Exception e) {
			System.out.println("********Exception occurred in method call: " + methodName(clazz, method, allArguments) + " Exception = " + e);
			throw e;
		} finally {
			System.out.println("********Method " + methodName(clazz, method, allArguments) + " completed in " + (System.currentTimeMillis() - startTime) + " miliseconds");
		}
	}

	@RuntimeType
	@BindingPriority(BindingPriority.DEFAULT * 2)
	public static Object interceptDefault(@DefaultCall Callable<?> zuper) throws Exception {
		long startTime = System.currentTimeMillis();
		try {
			return zuper.call();
		} catch (Exception e) {
			throw e;
		} finally {
			System.out.println("********intercept Default completed in " + (System.currentTimeMillis() - startTime) + " miliseconds");
		}
	}
	
	private static String methodName(Class<?> clazz, Method method, Object[] allArguments) {
		StringBuilder builder = new StringBuilder();
		builder.append(clazz.getName());
		builder.append(".");
		builder.append(method.getName());
		builder.append("(");
		for (int i = 0; i < method.getParameters().length; i++) {

			builder.append(method.getParameters()[i].getName());
			if (allArguments != null) {
				Object arg = allArguments[i];
				builder.append("=");
				builder.append(arg != null ? arg.toString() : "null");
			}

			if (i < method.getParameters().length - 1) {
				builder.append(", ");
			}
		}
		builder.append(")");
		return builder.toString();
	}
}