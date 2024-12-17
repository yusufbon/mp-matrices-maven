package edu.grinnell.csc207.util;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Bonsen Yusuf
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

/** Contains the matrix. */
  private List<List<T>> data;

/** Matrix width. */
  private int width;

/** Matrix height. */
  private int height;

/** Default value to fill matrix. */
  private T defaultValue;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height, T def) {
    if (width < 0 || height < 0) {
      throw new NegativeArraySizeException("Matrix dimensions must be non-negative.");
    } //if
    this.width = width;
    this.height = height;
    this.defaultValue = def;
    this.data = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      List<T> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        row.add(def);
      } //for
      this.data.add(row);
    } //for
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

 /**
   * Validates that the specified row and column indices are within the
   * bounds of the matrix dimensions.
   *
   * @param row The row index to check.
   * @param col The column index to check.
   *
   * @throws IndexOutOfBoundsException If the row is less than 0, greater
   *  than or equal to the height, the column is less than 0,
   *  greater than or equal to the width of the matrix.
   */
  private void validBound(int row, int col) {
    if (row < 0 || row >= height || col < 0 || col >= width) {
      throw new IndexOutOfBoundsException("Indices out of bounds: (" + row + ", " + col + ")");
    } //if
  } // validBound(int row, int col)


  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    validBound(row, col);
    return data.get(row).get(col);
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    validBound(row, col);
    data.get(row).set(col, val);
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return height;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return width;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    if (row < 0 || row > height) {
      throw new IndexOutOfBoundsException("Row index out of bounds: " + row);
    } //if
    List<T> newRow = new ArrayList<>();
    for (int j = 0; j < width; j++) {
      newRow.add(defaultValue);
    } //for
    data.add(row, newRow);
    height++;
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if (row < 0 || row > height || vals.length != width) {
      throw new ArraySizeException("Invalid row size.");
    } //if
    List<T> newRow = List.of(vals);
    data.add(row, newRow);
    height++;
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    if (col < 0 || col > width) {
      throw new IndexOutOfBoundsException("Column index out of bounds: " + col);
    } //if
    for (List<T> row : data) {
      row.add(col, defaultValue);
    } //for
    width++;
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if (col < 0 || col > width || vals.length != height) {
      throw new ArraySizeException("Invalid column size, out of bounds.");
    } //if
    for (int i = 0; i < height; i++) {
      data.get(i).add(col, vals[i]);
    } //for
    width++;
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    validBound(row, 0);
    data.remove(row);
    height--;
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    validBound(0, col);
    for (List<T> row : data) {
      row.remove(col);
    } //for
    width--;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    for (int i = startRow; i < endRow; i++) { //i = row
      for (int j = startCol; j < endCol; j++) { //j = column
        set(i, j, val);
      } //for
    } //for
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    int i = startRow;
    int j = startCol;
    while (i < endRow && j < endCol) {
      set(i, j, val);
      i += deltaRow;
      j += deltaCol;
    } //while
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix<T> clone() {        //////////////////
    MatrixV0<T> clone = new MatrixV0<>(width, height, defaultValue);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        clone.set(i, j, get(i, j));
      } //for
    } //for
    return clone;
  } //clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  public boolean equals(Object other) {
    if (!(other instanceof Matrix)) {
      return false;
    } //if
    Matrix<?> matrix = (Matrix<?>) other;
    if (matrix.width() != width || matrix.height() != height) {
      return false;
    } //if
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (!get(i, j).equals(matrix.get(i, j))) {
          return false;
        } //if
      } //for
    } //for
    return true;
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
