package com.elife.droolstest;

import java.util.Objects;

import org.drools.core.factmodel.traits.Thing;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.elife.domain.Person;

public class RulesTest {
	public static void main(String[] args) {
		
		KieSession kSession = null;
		try {
			KieServices kieServices = KieServices.Factory.get();
			KieContainer kieContainer = kieServices.getKieClasspathContainer();
			kSession = kieContainer.newKieSession("ksession-rules");
			Person person = new Person();
			person.setName("Lokesh");
			person.setTime(16);
			kSession.insert(person);
			kSession.fireAllRules();
			
			System.out.println(person.getGreet());
			
			final Object[] objs = kSession.getObjects().toArray();

			for (final Object object : objs) {
				if (object instanceof Thing) {
					System.out.println(object);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (Objects.nonNull(kSession)) {
				kSession.dispose();
			}
		}
	}
}
