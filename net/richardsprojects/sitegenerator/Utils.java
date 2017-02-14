package net.richardsprojects.sitegenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utils {

	public static String loadFileContents(String fileName) throws FileNotFoundException {
		String contents = "";
		File file = new File(Main.projectFolder.toPath().toAbsolutePath()
				+ File.separator + fileName);
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			contents = contents + scanner.nextLine() + "\n";
		}
		scanner.close();
		
		return contents;
	}
	
}
