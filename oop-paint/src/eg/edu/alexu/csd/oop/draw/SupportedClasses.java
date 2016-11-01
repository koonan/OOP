package eg.edu.alexu.csd.oop.draw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SupportedClasses {

	private Set<Class<? extends Shape>> shapeList = new HashSet<Class<? extends Shape>>();

	@SuppressWarnings("unchecked")
	public Set<Class<? extends Shape>> classNames() {

		ZipInputStream zip = null;
		String classpath = System.getProperty("java.class.path");

		// set the system properties
		String[] s = classpath.split(File.pathSeparator);

		for (int i = 1; i < s.length; i++) {
			try {
				zip = new ZipInputStream(new FileInputStream(s[i]));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip
						.getNextEntry()) {
					if (!entry.isDirectory()
							&& entry.getName().endsWith(".class")) {
						String className = entry.getName().replace('/', '.');
						String name = className.substring(0, className.length()
								- ".class".length());
						try {
							Class<?> myClass = Class.forName(name);
							if (Shape.class.isAssignableFrom(myClass)
									&& !myClass.isInterface()
									&& !Modifier.isAbstract(myClass
											.getModifiers())) {
								shapeList.add((Class<? extends Shape>) myClass);
							}

						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}

				zip.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}

		}
		return shapeList;
	}

}
