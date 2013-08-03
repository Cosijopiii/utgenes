package main.java.ar.edu.utn.frba.ia.ag.cruzamiento;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import main.java.ar.edu.utn.frba.ia.ag.Individuo;
import main.java.ar.edu.utn.frba.ia.ag.UTgeNesUtils;

public class BinomialAzarComplemento extends Cruzamiento {
	
	@Override
	protected void cruzar(Individuo padreA, Individuo padreB) {
		
		Method getter = null;
		Method setter = null;
		
		for (Field field : padreA.getClass().getDeclaredFields()) {
			
			getter = UTgeNesUtils.armarGetter(padreA, field);
			setter = UTgeNesUtils.armarSetter(padreA, field);
			
			try {
				
				Object auxA = getter.invoke(padreA);
				Object auxB = getter.invoke(padreB);
				
				if (Math.random() <= 0.5) {
					setter.invoke(padreA, auxA);
					setter.invoke(padreB, auxB);
				}
				else {
					setter.invoke(padreA, auxB);
					setter.invoke(padreB, auxA);
				}
			}
			catch (IllegalArgumentException e) {
				System.out.println("ERROR en reflection 3");
			}
			catch (IllegalAccessException e) {
				System.out.println("ERROR en reflection 4");
			}
			catch (InvocationTargetException e) {
				System.out.println("ERROR en reflection 5");
			}
		}
	}
}
