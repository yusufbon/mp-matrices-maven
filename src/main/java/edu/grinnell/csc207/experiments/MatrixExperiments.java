package edu.grinnell.csc207.experiments;

import edu.grinnell.csc207.util.ArraySizeException;
import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.util.MatrixV0;

import java.io.PrintWriter;

/**
 * Some experiments with matrices.
 *
 * @author Your Name Here
 * @author Samuel A. Rebelsky
 */
public class MatrixExperiments {
  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Create a figure with a matrix and a caption. The caption appears
   * above the matrix.
   *
   * @param pen
   *   The tool used to print output.
   * @param caption
   *   A caption for the matrix
   * @param matrix
   *   The matrix to print.
   */
  static void figure(PrintWriter pen, String caption, Matrix matrix) {
    pen.println("=".repeat(80));
    pen.println();
    pen.println(caption);
    pen.println();
    Matrix.print(pen, matrix);
    pen.println();
  } // figure(PrintWriter, String, Matrix)

  // +-------------+-------------------------------------------------
  // | Experiments |
  // +-------------+

  /**
   * Run the sample from the assignment.
   *
   * @param pen
   *   The pen used to print out the results.
   */
  static void assignmentSample(PrintWriter pen) {
    Matrix<String> sample = new MatrixV0<String>(5, 6, "O");
    for (int row = 0; row < 6; row++) {
      for (int col = 0; col < 5; col++) {
        sample.set(row, col, "X");
      } // for col
    } // for row

    figure(pen, "Original", sample);

    sample.insertCol(2);
    figure(pen, "insertCol(2)", sample);

    try {
      sample.insertCol(6, new String[] {"A", "B", "C", "D", "E", "F"});
      figure(pen, "insertCol(6, ABCDEF)", sample);
    } catch (ArraySizeException e) {
      pen.println("*** Failed to insert row 6. ***");
    } // try/catch
  } // assignmentSample()

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Run the experiments.
   *
   * @param args
   *   Command-line arguments (ignored)
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);

    assignmentSample(pen);

    pen.close();
  } // main(String[])
} // class MatrixExperiments
