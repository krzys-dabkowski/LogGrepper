package ski.dabkow.tools.loggrepper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Runner {

	private static String LOGS_DIR = "/Users/krzysztofdabkowski/Documents/BKW/performance_gdm";
	private static String CORRELATION_IDS_FILE = "/Users/krzysztofdabkowski/Documents/BKW/uuids.txt";

	public static void main(String[] args) {
		// configuration
		ArrayList<String> logFiles = new ArrayList<String>();
		// String logFile =
		logFiles.add("/Users/krzysztofdabkowski/Documents/BKW/performance_gdm/server1/osb_server1_qs.log");
		logFiles.add("/Users/krzysztofdabkowski/Documents/BKW/performance_gdm/server1/osb_server1_qs.log01867");
		logFiles.add("/Users/krzysztofdabkowski/Documents/BKW/performance_gdm/server1/osb_server1_qs.log01868");
		logFiles.add("/Users/krzysztofdabkowski/Documents/BKW/performance_gdm/server2/osb_server2_qs.log");
		logFiles.add("/Users/krzysztofdabkowski/Documents/BKW/performance_gdm/server2/osb_server2_qs.log01773");
		logFiles.add("/Users/krzysztofdabkowski/Documents/BKW/performance_gdm/server2/osb_server2_qs.log01774");
		logFiles.add("/Users/krzysztofdabkowski/Documents/BKW/performance_gdm/server2/osb_server2_qs.log01775");
		logFiles.add("/Users/krzysztofdabkowski/Documents/BKW/performance_gdm/server2/osb_server2_qs.log01776");
		logFiles.add("/Users/krzysztofdabkowski/Documents/BKW/performance_gdm/server2/osb_server2_qs.log01777");
		logFiles.add("/Users/krzysztofdabkowski/Documents/BKW/performance_gdm/server2/osb_server2_qs.log01778");
		logFiles.add("/Users/krzysztofdabkowski/Documents/BKW/performance_gdm/server2/osb_server2_qs.log01779");

		// List<String> arguments = new ArrayList<String>();
		// correlation IDS
		// arguments.add("fd7ee3d3-45f0-463d-8a69-c84637559613");
		// arguments.add("d075e65f-0a66-44eb-aef8-e458839390ed");
		// arguments.add("c6b3fdd5-5ca9-480f-8523-e3427fb28f37_1");
		// arguments.add("f2db2b51-48b5-40e6-9174-8920a7a3db8a_1");
		// arguments.add("711b8339-7573-4ad0-818f-6141d28617ed_1");
		// arguments.add("3a3b7c0b-0c52-4a92-8bfd-3078a1273b4f_1");
		// arguments.add("d5ec34a6-e8ba-4d0d-9d01-134470bb7a9d_1");

		// arguments.add("be7d94c0-8970-4e2b-bd81-28cedc6f0b5c_1");
		// arguments.add("67195780-6ccd-41d7-8430-448a37ae8180_1");
		// arguments.add("d20dccf8-c90d-4720-9f6a-23f0b6da7c44_1");

		// arguments.add("ea703fe0-b934-4692-bb5b-7366cf96388e_2");
		// arguments.add("cbfdec49-67e4-4b97-9f30-4b27a6f0fe94_1");
		// arguments.add("faf604f6-dcbf-42fc-a565-17eed9954594_2");
		// arguments.add("cc89dbc7-ba3e-4056-a49d-5b4ef95138b1_1");
		// arguments.add("ed3bfddf-0f31-43e8-a94b-7f137d13894a_1");
		// arguments.add("a3c42c90-aaa1-4acb-b83f-1c2ff864142f_1");
		// arguments.add("1fccee70-e16e-4850-86d4-a5c9611da519_1");

		// models
		// arguments.add("model://TSE_OPTIMIZATION/CH.HYDRO.MAG.TRAN/MAG.Sambuco.MinVolume.m3");
		// arguments.add("model://TSE_OPTIMIZATION/CH.HYDRO.KWO.TRAN/KWO.Grimsel.MinVolume.MW");

		System.out.println("--- Generating Files...");

		try {
			// for (String logFile : logFiles) {
			// Greper.perform(logFile, arguments);

			List<String> logFileList = findLogFilesFolders(LOGS_DIR);
			// for (String arg : arguments) {
			// System.out.println(arg);
			// }
			Greper.findTimes(logFileList,
					FileUtils.readLines(new File(CORRELATION_IDS_FILE)));
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("--- Done!");
	}

	private static List<String> findLogFilesFolders(String location) {
		List<String> fileList = new ArrayList<String>();
		String[] fileArray = new File(location).list();
		if (fileArray != null) {
			for (String file : fileArray) {
				String newLocation = location + "/" + file;
				File fFile = new File(newLocation);
				boolean isFile = fFile.isFile();
				if (isFile && file.contains(".log")) {
					fileList.add(newLocation);
				} else {
					fileList.addAll(findLogFilesFolders(newLocation));
				}
			}
		}
		return fileList;
	}

}
