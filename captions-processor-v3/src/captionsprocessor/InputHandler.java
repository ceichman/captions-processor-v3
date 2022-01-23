package captionsprocessor;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class InputHandler {
	
	private static final String WORKING_DIR = System.getProperty("user.dir");
	private static final Path WORKING_PATH = Path.of(WORKING_DIR);
	private static final Path CAPTION_FOLDER_PATH = Paths.get(WORKING_PATH.toString() + "\\src\\processor\\captionfiles");
	private static final File CAPTION_FOLDER_FILE = new File(WORKING_PATH.toString() + "\\src\\processor\\captionfiles");
	private static final Charset CHARSET = Charset.forName("US-ASCII");
	
	/**
	 * Helper method for chooseFile (deprecated).
	 * @param url The URL of a specified directory
	 * @return A File object representing such directory
	 */
	private static File toFile(URL url) {
		try {
			URI uri = url.toURI();
			return new File(uri);
		} catch (URISyntaxException urise) {
			return null;
		}
	}

	/**
	 * Opens a file chooser in the working directory captions-processor/src/processor/captionfiles.
	 * @return The user-chosen file
	 */
	protected static Path chooseFile() {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(CAPTION_FOLDER_FILE);
		fc.showOpenDialog(null);
		Path path = Paths.get(fc.getSelectedFile().toString());
		return path;
	}

	/**
	 * Creates a new file under the working directory captions-processor/src/processor/captionfiles with a specified file name.
	 * @param fileName The name of the file to be created
	 * @return The Path of the created file
	 */
	protected static Path newFile(String fileName) {
		Path path = Paths.get(CAPTION_FOLDER_PATH.toString() + "\\" + fileName);
		try {
		    // Create the empty file with default permissions, etc.
		    Path file = Files.createFile(path);
		    return file;
		} catch (FileAlreadyExistsException x) {
		    System.err.format("file named %s" +
		        " already exists%n", path);
		} catch (IOException x) {
		    // Some other sort of failure, such as permissions.
		    System.err.format("createFile error: %s%n", x);
		}
		return null;
	}
	
	/**
	 * Writes a list of specified lines to a specified file.
	 * @param lines The list of lines to be written, in order of transcription
	 * @param file The path of the target file
	 */
	private static void writeLines(List<String> lines, Path file) {
		try (BufferedWriter writer = Files.newBufferedWriter(file, CHARSET)) {
		    for (String line : lines) {
		    	writer.write(line + "\n");
		    }
		    writer.close();
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
	
	/**
	 * Records String input from a dialog with a specified prompt. Continues query until input is provided or dialog is dismissed, in which case null is returned.
	 * @param prompt The user prompt for text input
	 * @return User input as String, or null if input is interrupted
	 */
	public static String userInput(String prompt) {
		boolean validInput = false;
		String input = JOptionPane.showInputDialog(prompt, null);
		while (!validInput) {
			if (input == null) return null;
			else if (!input.equals("")) {
				validInput = true;
			}
			else {
				input = JOptionPane.showInputDialog(prompt, null);
			}
		}
		return input;
	}
	
	/**
	 * Initializes and reads input from a confirmation dialog with configurable title and prompt.
	 * @param title Title of dialog window
	 * @param prompt Prompt for user response
	 * @return
	 */
	public static boolean userConfirm(String title, String prompt) {
		int result = JOptionPane.showConfirmDialog(null, prompt, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			return true;
		}
		return false;
	}
	
	/**
	 * Creates a new file and populates it with formatted captions.
	 * @param captions The list of captions to be written to the new file
	 * @param fileName The name of the caption file
	 * @return The Path of the new (formatted) caption file
	 */
	protected static Path writeCaptionFile(List<Caption> captions, String fileName) {
		Path path = newFile(fileName);
		//populate the new file
		List<String> lines = new ArrayList<>();
		for (Caption caption : captions) {
			lines.add(String.valueOf(caption.getNumber()));
			lines.add(caption.getTiming());
			lines.add(caption.getContent());
			lines.add("");  //add emtpy breakline for formatting purposes
		}
		writeLines(lines, path);
		return path;
	}
	
	public static void main(String[] args) {
		chooseFile();
	}

}
