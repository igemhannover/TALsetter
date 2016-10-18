package de.uni_hannover.igem.model;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

/* Pipetting robot interface */
public class TaleToCSV {
	public static ArrayList<ArrayList<String>> makeTable(String string) throws Exception {
		ArrayList<String> line = new ArrayList<String>(Arrays.asList(
				"target", "Name", "Enzymes", "H2O",
				"LA", "AB", "BC", "CD", "LR", "AR", "BR", "CR", "DR"
				));
		for (String part : Arrays.asList("NI", "HD", "NN", "NG", "NH", "sNI", "sHD", "sNN", "sNG", "sNH")) {
			for (Integer n : Arrays.asList(1,2,3,4,5,6)) {
				if (n <= 5 || part.charAt(0) != 's') { /* sXX have only 5 occurances */
					line.add(part + n);
				}
			}
		}
		ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
		rows.add(line);

		String rvd;
		Integer piece; /* which of the 1-3 TALEN parts are we dealing with? */
		Integer total_rvds = string.length() / 2;
		Integer total_pieces = (int) Math.ceil(total_rvds.doubleValue() / 6);
		Integer relative_index;

		/* Constants */
		Integer pos_rvds = 13;
		Integer pos_h2o = 3;
		Integer rvds_per_piece = 6;
		Integer number_of_rvd_types = 5;
		String targets[] = {"11.A1", "11.A2", "11.A3", "11.A4", "11.A5"};

		for (int rvd_index = 0; rvd_index < total_rvds; rvd_index++) {
			/* set up general parameters */
			rvd = string.substring(rvd_index*2, (rvd_index*2)+2).toLowerCase();
			relative_index = rvd_index % rvds_per_piece;
			piece = rvd_index / rvds_per_piece;
			
			/* initialize the a row on first RVD of a piece */
			if (relative_index == 0) {
				line = new ArrayList<String>(Arrays.asList(targets[piece], "Somename", "13"));
				for (int i = 0; i < 65; i++) {
					line.add("0");
				}

				/* set the N and T termini */
				int terminus_type = 4 + piece;
				if (piece == total_pieces - 1) {
					terminus_type += 4;
				}
				line.set(terminus_type, "1");
			}
			
			/* find out which wells the pipetting robot should use */
			Integer offset;
			if      (rvd.equals("ni")) { offset = 0 * rvds_per_piece; }
			else if (rvd.equals("hd")) { offset = 1 * rvds_per_piece; }
			else if (rvd.equals("nn")) { offset = 2 * rvds_per_piece; }
			else if (rvd.equals("ng")) { offset = 3 * rvds_per_piece; }
			else if (rvd.equals("nh")) { offset = 4 * rvds_per_piece; }
			else {
				throw new Exception("Unknown RVD: `" + rvd + "`");
			}
			if ((rvd_index + 1 == total_rvds) && (total_rvds % rvds_per_piece != 0)) {
				/* need to use sXX instead of XX RVDs when it's the
				 * last RVD and there are <6 RVDs in the piece */
				offset += number_of_rvd_types * rvds_per_piece;
			}
			offset += relative_index; /* e.g. move from NI1 to NI4 on 4th RVD (if it's a NI) */
			line.set(pos_rvds + offset, "1");
			
			/* add the row on the last iteration or when piece ends */
			if ((relative_index + 1 == rvds_per_piece) || (rvd_index + 1 == total_rvds)) {
				/* pad the leftover volume with water */
				line.set(pos_h2o, String.valueOf(rvds_per_piece - relative_index - 1));
				rows.add(line);
			}
		}
		return rows;
	}
	
	public static void table2csv(ArrayList<ArrayList<String>> table, String filename) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(filename));
		for (ArrayList<String> row : table) {
			pw.write(String.join(";", row) + "\n");
		}
        pw.close();
	}
}
