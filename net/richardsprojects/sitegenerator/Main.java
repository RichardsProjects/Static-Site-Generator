package net.richardsprojects.sitegenerator;

import java.io.File;

public class Main {

	public static File projectFolder;
	
	public static void main(String[] args) {
		if (args.length == 1) {
			File project = new File(args[0]); 
			if (project.exists()) {
				projectFolder = project;
			} else {
				System.out.println("The folder you specified does not exist.");
				System.exit(0);
			}
			
			new SiteGenerator();
			
		} else {
			System.out.println("You need to provide path of the project to generate.");
			System.exit(0);
		}
	}
	
}
