import java.io.*;

public class FastqPhred {

    public static void main(String[] args) throws IOException{
        for (String File : args) {
            System.out.println(File.replaceAll(".*/", "") + "\t" + FastqPhred.Run(File));
        }
    }
    public static int Run(String FastqFile) throws IOException {
        BufferedReader infile = new BufferedReader(new FileReader(FastqFile));
        int[] FormatEdge = new int[]{(int) '9', (int) 'a'};
        int[] Count = new int[2];
        String line;
        int LineNum = 0;
        while ((line = infile.readLine()) != null && LineNum <= 400) {
            if (++LineNum % 4 != 0) {
                continue;
            }
            for (int i = 0; i < line.length(); i++) {
                if ((int) line.charAt(i) <= FormatEdge[0]) {
                    Count[0]++;
                } else if ((int) line.charAt(i) >= FormatEdge[1]) {
                    Count[1]++;
                }
            }
        }
        return Count[0] >= Count[1] ? 33 : 64;
    }
}