// 
// Decompiled by Procyon v0.5.36
// 

package validate;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.GameEngineImpl;
import model.SimplePlayer;
import view.GameEngineCallbackImpl;

public class Validator {
	private static final String FAIL_MSG = "\nYou are either not implementing a required interface, have modified it,\nor have one or more non-private methods that are not part of the Specification";
	private static final String SYNTHETIC_ACCESSOR = "access$";
	private static final String SWITCH_TABLE = "$SWITCH_TABLE";
	private static final String HASH_CODE = "hashCode";
	private static final String[] IGNORED_TOKENS;

	static {
		IGNORED_TOKENS = new String[] { "access$", "$SWITCH_TABLE", "hashCode" };
	}

	public static void validate(final boolean verbose) {
		validateImpl(GEImpl.class, GameEngineImpl.class, verbose);
		validateImpl(GECImpl.class, GameEngineCallbackImpl.class, verbose);
		validateImpl(SlotImpl.class, model.SlotImpl.class, verbose);
		validateImpl(PlayerImpl.class, SimplePlayer.class, verbose);
		System.out.println();
	}

	private static void validateImpl(final Class<?> validClazz, final Class<?> testClazz, final boolean verbose)
			throws ValidationException {
		final Set<Method> validMethods = createSortedSetOfNonPrivateMethodsForClass(validClazz);
		final Set<Method> testMethods = createSortedSetOfNonPrivateMethodsForClass(testClazz);
		if (verbose) {
			System.out.println("\nEXPECTED:");
			for (final Method method : validMethods) {
				System.out.println(unqualifyMethodName(method.toGenericString()));
			}
			System.out.println("\nACTUAL:");
			for (final Method method : testMethods) {
				System.out.println(unqualifyMethodName(method.toGenericString()));
			}
		}
		final boolean passed = validMethods.equals(testMethods);
		System.out.printf("\nVALIDATION RESULT for %s: %s\n", testClazz.getName(), passed ? "PASSED" : "FAILED");
		if (!passed && verbose) {
			throw new ValidationException(testClazz.getName());
		}
	}

	private static String unqualifyMethodName(final String qualName) {
		final Matcher matcher = Pattern.compile("([\\w_\\$]+\\.)+(?=[\\w_\\$]+\\()").matcher(qualName);
		if (matcher.find()) {
			return matcher.replaceFirst("");
		}
		return null;
	}

	private static Set<Method> createSortedSetOfNonPrivateMethodsForClass(final Class<?> clazz) {
		final Set<Method> methodSet = new TreeSet<Method>((m1, m2) -> unqualifyMethodName(m1.toGenericString())
				.compareTo(unqualifyMethodName(m2.toGenericString())));
		addAllDeclaredMethods(methodSet, clazz);
		return methodSet;
	}

	private static boolean ignoreMethod(final Method method) {
		boolean ignore = false;
		String[] ignored_TOKENS;
		for (int length = (ignored_TOKENS = Validator.IGNORED_TOKENS).length, i = 0; i < length; ++i) {
			final String token = ignored_TOKENS[i];
			if (method.getName().contains(token)) {
				ignore = true;
			}
		}
		return Modifier.isPrivate(method.getModifiers()) || ignore;
	}

	private static void addAllDeclaredMethods(final Set<Method> methodSet, final Class<?> clazz) {
		if (clazz.getSuperclass() != null) {
			addAllDeclaredMethods(methodSet, clazz.getSuperclass());
		}
		Method[] declaredMethods;
		for (int length = (declaredMethods = clazz.getDeclaredMethods()).length, i = 0; i < length; ++i) {
			final Method method = declaredMethods[i];
			if (!ignoreMethod(method)) {
				methodSet.add(method);
			}
		}
	}

	public static class ValidationException extends RuntimeException {
		public ValidationException(final String className) {
			super(String.format("%s: %s", className,
					"\nYou are either not implementing a required interface, have modified it,\nor have one or more non-private methods that are not part of the Specification"));
		}
	}
}
