package ski.dabkow.tools.loggrepper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Greper {

	private static final String OUTPUT_FOLDER = "/Users/krzysztofdabkowski/Documents/BKW/";// "/home/oracle/Downloads/logs/output/10_20/";
	private static final String GET_GENIC_DATA = "getGenicData";
	private static final String GET_MODEL_FOR_URI = "getModelForURI";
	private static final String GET_PROFILE_FOR_URI = "getProfileForURI";

	public static void perform(String filePath, List<String> args)
			throws IOException {
		File logFile = new File(filePath);

		List<String> lines = FileUtils.readLines(logFile, "UTF-8");

		Iterator<String> argsIterator = args.iterator();
		String arg;

		while (argsIterator.hasNext()) {
			arg = argsIterator.next();

			List<String> correspondingLines = findCorrespondingLines(lines, arg);

			if (correspondingLines.size() > 0) {
				FileUtils.writeLines(new File(OUTPUT_FOLDER + arg + ".log"),
						correspondingLines);

			}
		}
	}

	public static void findTimes(List<String> logFiles, List<String> args)
			throws IOException {

		ArrayList<String> logLines = new ArrayList<String>();
		for (String file : logFiles) {
			logLines.addAll(FileUtils.readLines(new File(file), "UTF-8"));
		}

		List<RunTimesElement> elements = new ArrayList<RunTimesElement>();
		for (String logLine : logLines) {
			if (logLine.contains("datagenic-app-perf")) {
				int i = logLine.lastIndexOf('<');
				if (i != -1) {
					String[] lineElements = logLine.substring(i,
							logLine.length() - 1).split(",");
					String lineUuid = lineElements[3].trim();
					if (args.contains(lineUuid)) {
						RunTimesElement rte = new RunTimesElement(lineUuid);
						int size = lineElements.length;

						// Read duration of getGenicData
						String getGenicData = null;
						if (lineElements[size - 2].contains(GET_GENIC_DATA)) {
							getGenicData = lineElements[size - 3].trim();
						}

						// Read duration of getModelForURI
						String getModelForUri = null;
						if (lineElements[size - 2].contains(GET_MODEL_FOR_URI)) {
							getModelForUri = lineElements[size - 3].trim();
						}

						// Read duration of getProfileForURI
						String getProfileForUri = null;
						if (lineElements[size - 2]
								.contains(GET_PROFILE_FOR_URI)) {
							getProfileForUri = lineElements[size - 3].trim();
						}

						int indexRte = elements.indexOf(rte);
						if (indexRte == -1) {
							elements.add(rte);
							indexRte = elements.indexOf(rte);
						}
						rte = elements.get(indexRte);
						if (getGenicData != null) {
							rte.getGenicData = getGenicData;
						}
						if (getModelForUri != null) {
							rte.getModelForURI = getModelForUri;
						}
						if (getProfileForUri != null) {
							rte.getProfileForURI = getProfileForUri;
						}
						elements.set(indexRte, rte);

					}
				}
			}
		}
		elements.add(0, new RunTimesElement("correlation ID", "getModelForURI",
				"getGenicData", "getProfileForURI"));
		FileUtils.writeLines(new File(OUTPUT_FOLDER + "times.txt"), elements);
	}

	private static List<String> findCorrespondingLines(List<String> lines,
			String arg) {
		String regex;

		regex = "^.*(" + arg + ").*$";
		List<String> correspondingLines = new ArrayList<String>();
		Iterator<String> linesIterator;

		linesIterator = lines.iterator();

		while (linesIterator.hasNext()) {
			String line = linesIterator.next();
			int i = line.lastIndexOf('<');
			StringBuilder newLine = new StringBuilder();
			if (i != -1) {
				newLine.append(line.substring(1, line.indexOf('>') - 1));
				newLine.append(line.substring(i, line.length() - 1));
				line = newLine.toString();
			}
			if (line.matches(regex)) {
				correspondingLines.add(line);
			}
		}

		return correspondingLines;
	}

}
