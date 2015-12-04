package com.nt.bytebuddy;

import static net.bytebuddy.matcher.ElementMatchers.isPublic;
import static net.bytebuddy.matcher.ElementMatchers.isSetter;
import static net.bytebuddy.matcher.ElementMatchers.isGetter;
import static net.bytebuddy.matcher.ElementMatchers.nameContains;
import static net.bytebuddy.matcher.ElementMatchers.nameMatches;
import static net.bytebuddy.matcher.ElementMatchers.not;

import java.lang.instrument.Instrumentation;

import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.AgentBuilder.Transformer;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.implementation.MethodDelegation;

public class MyAgent {
	public static void premain(String arguments, Instrumentation inst) {
		System.out.println("===================MyAgent Premain===========================");
		System.out.println("===================MyAgent Premain===========================");
		System.out.println("===================MyAgent Premain===========================");
		System.out.println("===================MyAgent Premain===========================");
		System.out.println("===================MyAgent Premain===========================");
		System.out.println("===================MyAgent Premain===========================");
		System.out.println("===================MyAgent Premain===========================");
		System.out.println("===================MyAgent Premain===========================");
		System.out.println("===================MyAgent Premain===========================");
		System.out.println("===================MyAgent Premain===========================");
		System.out.println(ClassFileVersion.forCurrentJavaVersion());
		new AgentBuilder.Default().withListener(new AgentListener()).type(nameMatches("(org.springframework.web|com.nt).*").and(not(nameContains("auxiliary$")))).transform(new Transformer() {
			@Override
			public Builder<?> transform(Builder<?> builder, TypeDescription typeDescription) {
				return builder.method(isPublic().and(not(isSetter()).and(not(isGetter())))).intercept(MethodDelegation.to(MyInterceptor.class));
			}
		}).installOn(inst);

	}
}