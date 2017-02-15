package net.richardsprojects.sitegenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Page {

	private String pageTitle = "";
	
	private PageType type;
	
	private String content = "";
	
	/**
	 * Constructs a Page object from the specified page file.
	 * 
	 * @param file location of the page file
	 */
	public Page(File file) throws PageGenerationException {
		if (file.exists()) {
			// load data from file
			try {
				Scanner fileReader = new Scanner(file);
				
				boolean loadingContent = false;
				while (fileReader.hasNextLine()) {
					String line = fileReader.nextLine();
					if (!loadingContent) {
						if (line.startsWith("title:")) {
							pageTitle = line.replace("title:", "").trim();
						} else if (line.startsWith("type:")) {
							String type = line.replace("type:", "").trim();
							if (type.equalsIgnoreCase("home")) {
								this.type = PageType.HOME;
							} else if (type.equalsIgnoreCase("post")) {
								this.type = PageType.BLOG_POST;
							} else if (type.equalsIgnoreCase("page")) {
								this.type = PageType.PAGE;
							}
						} else if (line.startsWith("----")) {
							loadingContent = true;
						}
					} else {
						content = content + "<br />" + line;
					}
				}
			} catch (FileNotFoundException e) {
				throw new PageGenerationException();
			}
		} else {
			throw new PageGenerationException();
		}
	}
	
	public String generateHTML(SiteGenerator sg) {
		String html = "";
		
		Scanner strScanner = new Scanner(sg.mainTemplate);
		while (strScanner.hasNextLine()) {
			String line = strScanner.nextLine();
			
			// substitutions
			
			// TODO: Implement a system for converting adding the active part to an element
			// in the navbar for a page based on it's title
			
			line = line.replace("{NAVBAR}", sg.formattedNavbar);
			line = line.replace("{PAGETITLE}", this.pageTitle);
			line = line.replace("{CONTENT}", this.content);
			
			// TODO: Convert BBCode into HTML code
			
			html = html + line + "\n";
		}
		
		return html;
	}
}
