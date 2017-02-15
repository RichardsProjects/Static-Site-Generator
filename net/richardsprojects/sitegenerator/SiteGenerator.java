package net.richardsprojects.sitegenerator;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class SiteGenerator {

	public File buildFolder;
	public String formattedNavbar;
	public String mainTemplate;
	
	public SiteGenerator() {
		run();
	}

	public void run() {
		// TODO: Write code that clears contents of build folder
		
		formattedNavbar = "";
		try {
			mainTemplate = Utils.loadFileContents("templates" + File.separator + "main.tpl");
		} catch (Exception e) {
			System.out.println("[!] Could not load \"templates/main.tpl\"");
			System.out.println("[!] Exiting...");
			System.exit(0);
		}
		
		// create build folder if needed
		buildFolder = new File(Main.projectFolder.toPath().toAbsolutePath()
				+ File.separator + "build");
		if (!buildFolder.exists())
			buildFolder.mkdirs();

		loadNavbarCode();
		
		// TODO: Remove this test
		try {
			Page home = new Page(new File(Main.projectFolder.toPath().toAbsolutePath() + File.separator + "home.page"));
		
			PrintWriter pw = new PrintWriter(buildFolder + File.separator + "index.html");
			pw.print(home.generateHTML(this));
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// TODO: Write code that copies contents of the resources folder to the builds folder
	}

	private void loadNavbarCode() {
		try {
			String navbar = Utils.loadFileContents("navbar.md");
			Scanner scanner = new Scanner(navbar);

			// loop through the contents line by line
			String parent = "";
			while (scanner.hasNextLine()) {
				String contents = scanner.nextLine();

				if (contents.startsWith("    ")) {
					// parse parts
					contents = contents.replace("    ", ""); // remove spaces
					String[] parts = contents.split(",");
					String name = parts[0];
					String link = parts[1];
					
					formattedNavbar += "\t<li><a href=\"" + link + "\">" + name
							+ "</a></li>\n";
				} else {
					// parse parts
					String[] parts = contents.split(",");
					String name = parts[0];
					String link = parts[1];
					boolean dropdown = Boolean.parseBoolean(parts[2]);

					if (dropdown) {
						if (parent != "") {
							formattedNavbar += "</ul>\n";
							formattedNavbar += "</li>\n";
						}
						parent = name;

						formattedNavbar += "<li class=\"dropdown\">\n";
						formattedNavbar += "\t<a class=\"dropdown-toggle\" dat"
								+ "a-toggle=\"dropdown\" role=\"button\" aria-" 
								+ "haspopup=\"true\" aria-expanded=\"false\">"
								+ name + "<span class=\"caret\"></span></a>\n";
						formattedNavbar += "<ul class=\"dropdown-menu\">\n";
					} else {
						formattedNavbar += "<li><a href=\"" + link + "\">"
								+ name + "</a></li>\n";
					}
				}
			}
			scanner.close();

		} catch (Exception e) {
			System.out
					.println("[!] There was an error loading the format for the navbar...");
			System.out.println("[!] Exiting...");
			System.exit(0);
		}
	}

}
