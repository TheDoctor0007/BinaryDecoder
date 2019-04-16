import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;

public class BinaryDecoder {
	private final Scanner input;
	
	public BinaryDecoder(String inputFilename) 
			throws FileNotFoundException {
		
		this.input = new Scanner(new File(inputFilename));
	}
	
	public String decode() 
			throws IllegalArgumentException, NumberFormatException {
		StringBuilder s = new StringBuilder();
		int count = 1;
		do {
			String file = input.nextLine();
			for (int i = 0; i < file.length(); i++) {
				if (i+8 > file.length()) {
					System.err.println("Error: Invalid character at the end of line " + count + ".");
					System.exit(1);
				}
				String sub = file.substring(i, i+8);
				int d = binaryToDecimal(sub);
				char c = (char)d;
				s.append(c);
			}
			s.append("\n");
			count++;
		} while (input.hasNextLine());
		return s.toString();
	}
	
	public static int binaryToDecimal(String binaryString) 
			throws NumberFormatException {
		int d = 0;
		int p = 0;
		for (int j = binaryString.length()-1; j >= 0; j--) {
			if (binaryString.charAt(j) == '1') {
				d += Math.pow(2, p);
			} else if (binaryString.charAt(j) == '0') {
				throw new NumberFormatException("Invalid binary number '" + binaryString + "'.");
			}
			p+=1;
		}
		return d;
	}
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java BinaryDecoder <input file>");
			System.exit(1);
		}
		BinaryDecoder binaryDecoder = null;
		try {
			binaryDecoder = new BinaryDecoder(args[0]);
		} catch (IOException ioe) {
			System.err.println("Error: File '" + args[0] + "' not found.");
			System.exit(1);
		}
		
		try {
			System.out.println(binaryDecoder.decode());
		} catch (IllegalArgumentException iae) {
			System.err.println("Error :" + iae.getMessage());
			System.exit(1);
		}
		System.exit(0);
	}

}
