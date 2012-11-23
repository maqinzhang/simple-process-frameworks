/**
 * 
 */
package com.simple.frameworks.processframework.compiler;

import java.io.BufferedReader; 
import java.io.FileReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.junit.Test;

import com.simple.frameworks.processframework.exception.ExceptionHandler;
import com.simple.frameworks.processframework.invoke.ProcessHolder;
import com.simple.frameworks.processframework.invoke.ProcessInvoker;

/**
 * @author luolishu
 * 
 */
public class TestCompiler {
	private ProcessHolder processHolder;

	private ExceptionHandler exceptionHandler;

	@Test
	public void testComplier() throws Exception {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		String className = "com.simple.frameworks.processframework.invoke.DefaultProcessInvoker0";
		String codeBlock = getClassString();
		Iterable<StringJavaFileObject> javaFileObject = createJavaFileObjects(
				className, codeBlock);
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				diagnostics, null, null);
		
		String outputDir=ProcessInvoker.class.getResource("/").getPath();

		boolean result = compiler.getTask(
				null,
				fileManager,
				diagnostics,
				Arrays.asList(new String[] { "-d",
						outputDir}),
				null, javaFileObject).call();
		if (result) {
			Class claz = Class.forName(className);
			ProcessInvoker processInvoker=(ProcessInvoker) claz.getConstructors()[0].newInstance(processHolder,exceptionHandler);
			
			System.out.println(processInvoker);
		}
	}

	static String getClassString() throws Exception {
		FileReader reader = new FileReader(
				"D:/workspace_opensource/business_framework/ProcessFramework/core/src/main/resources/ProcessInvoker");
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(reader);
		String line = br.readLine();
		sb.append(line);
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}

	static Iterable<StringJavaFileObject> createJavaFileObjects(
			String codeName, String codeBlock) {
		final StringJavaFileObject javaFileObject = new StringJavaFileObject(
				codeName, codeBlock);
		List<StringJavaFileObject> results = new ArrayList<StringJavaFileObject>();
		results.add(javaFileObject);
		return results;
	}

	static class StringJavaFileObject extends SimpleJavaFileObject {
		final String code;

		StringJavaFileObject(String souceName, String codeBlock) {
			super(URI.create("string:///" + souceName.replace('.', '/')
					+ Kind.SOURCE.extension), Kind.SOURCE);
			this.code = codeBlock;
		}

		public CharSequence getCharContent(boolean ignoreEncodingErrors) {
			return code;
		}
	}

}
