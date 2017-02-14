package net.richardsprojects.sitegenerator;

import java.io.File;

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
			// TODO: Load data from file
		} else {
			throw new PageGenerationException();
		}
	}
	
}
