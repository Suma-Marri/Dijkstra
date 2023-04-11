public class DijkstraAlignment {

    private static final int MATCH_SCORE = 6;
    private static final int MISMATCH_SCORE = 2;
    private static final int INDEL_SCORE = 1;

    public static void main(String[] args) {
		String x = "AGTGTCT";
        String y = "ATTACG";
        

        int[][] scoreMatrix = new int[x.length() + 1][y.length() + 1];
        initializeMatrix(scoreMatrix);

        fillMatrix(scoreMatrix, x, y);

        String[] alignment = traceback(scoreMatrix, x, y);
        System.out.println("Optimal alignment:");
        System.out.println(alignment[0]);
        System.out.println(alignment[1]);
    }

    private static void initializeMatrix(int[][] scoreMatrix) {
        for (int i = 0; i < scoreMatrix.length; i++) {
            scoreMatrix[i][0] = i * INDEL_SCORE;
        }
        for (int j = 0; j < scoreMatrix[0].length; j++) {
            scoreMatrix[0][j] = j * INDEL_SCORE;
        }
    }

    private static void fillMatrix(int[][] scoreMatrix, String x, String y) {
        for (int i = 1; i <= x.length(); i++) {
            for (int j = 1; j <= y.length(); j++) {
                int match = scoreMatrix[i-1][j-1] + (x.charAt(i-1) == y.charAt(j-1) ? MATCH_SCORE : -MISMATCH_SCORE);
                int gapX = scoreMatrix[i-1][j] + INDEL_SCORE;
                int gapY = scoreMatrix[i][j-1] + INDEL_SCORE;
                scoreMatrix[i][j] = Math.max(Math.max(match, gapX), gapY);
            }
        }
        System.out.println("Score matrix:");
        printMatrix(scoreMatrix);
    }

    private static String[] traceback(int[][] scoreMatrix, String x, String y) {
        StringBuilder xAligned = new StringBuilder();
        StringBuilder yAligned = new StringBuilder();
        int i = x.length();
        int j = y.length();
        while (i > 0 || j > 0) {
            if (i > 0 && j > 0 && x.charAt(i-1) == y.charAt(j-1)) {
                xAligned.append(x.charAt(i-1));
                yAligned.append(y.charAt(j-1));
                i--;
                j--;
            } else if (i > 0 && scoreMatrix[i][j] == scoreMatrix[i-1][j] + INDEL_SCORE) {
                xAligned.append(x.charAt(i-1));
                yAligned.append('-');
                i--;
            } else {
                xAligned.append('-');
                yAligned.append(y.charAt(j-1));
                j--;
            }
        }
        String[] alignment = new String[2];
        alignment[0] = xAligned.reverse().toString();
        alignment[1] = yAligned.reverse().toString();
        return alignment;
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
