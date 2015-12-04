package com.nt.bytebuddy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;

public class AgentListener implements AgentBuilder.Listener {
	@Override
	public void onTransformation(TypeDescription typeDescription, DynamicType dynamicType) {
		System.out.println("Transformed - " + typeDescription);

	}

	@Override
	public void onIgnored(TypeDescription typeDescription) {
//		 System.out.println("Ignored - " + typeDescription);
	}

	@Override
	public void onError(String typeName, Throwable throwable) {
		System.err.println("Error - " + typeName);
		throwable.printStackTrace();
		System.exit(0);
	}

	@Override
	public void onComplete(String typeName) {
		// System.out.println("Completed - " + typeName);
	}

}
