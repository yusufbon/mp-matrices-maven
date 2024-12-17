package edu.grinnell.csc207.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



/**
 * A set of tests for the MatrixV0 implementation.
 * These tests cover various aspects including matrix creation,
 * insertion, deletion, and exception handling.
 *
 * @author Bonsen Yusuf
 */
class TestsByStudent {

    /**
     * Test creating a matrix and checking its initial state.
     */
    @Test
    public void testMatrixInitialization() {
        Matrix<Integer> matrix = new MatrixV0<>(3, 3);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                assertNull(matrix.get(row, col), "Initial value should be null");
            }
        }
    }

    /**
     * Test inserting a row with default values.
     */
    @Test
    public void testInsertRowDefault() {
        Matrix<String> matrix = new MatrixV0<>(3, 3, "-");
        matrix.insertRow(1);
        assertEquals(4, matrix.height(), "Matrix should have 4 rows after insertion");
        for (int col = 0; col < 3; col++) {
            assertEquals("-", matrix.get(1, col), "Inserted row should contain default values");
        }
    }

    /**
     * Test inserting a column with specific values.
     */
    @Test
    public void testInsertColSpecificValues() throws ArraySizeException {
        Matrix<String> matrix = new MatrixV0<>(2, 2, "*");
        matrix.insertCol(1, new String[] {"A", "B"});
        assertEquals(3, matrix.width(), "Matrix should have 3 columns after insertion");
        assertEquals("A", matrix.get(0, 1), "Inserted column should match provided values");
        assertEquals("B", matrix.get(1, 1), "Inserted column should match provided values");
    }

    /**
     * Test deletion of a row.
     */
    @Test
    public void testDeleteRow() {
        Matrix<Integer> matrix = new MatrixV0<>(3, 3, 0);
        matrix.deleteRow(1);
        assertEquals(2, matrix.height(), "Matrix should have 2 rows after deletion");
    }

    /**
     * Test deletion of a column.
     */
    @Test
    public void testDeleteCol() {
        Matrix<Integer> matrix = new MatrixV0<>(4, 3, 1);
        matrix.deleteCol(2);
        assertEquals(3, matrix.width(), "Matrix should have 3 columns after deletion");
    }

    /**
     * Test fillRegion method.
     */
    @Test
    public void testFillRegion() {
        Matrix<String> matrix = new MatrixV0<>(4, 4, "");
        matrix.fillRegion(1, 1, 3, 3, "X");
        for (int row = 1; row < 3; row++) {
            for (int col = 1; col < 3; col++) {
                assertEquals("X", matrix.get(row, col), "Region should be filled with X");
            }
        }
    }

    /**
     * Test exception for invalid row insertion.
     */
    @Test
    public void testInsertRowOutOfBounds() {
        Matrix<String> matrix = new MatrixV0<>(3, 3, "");
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.insertRow(4), "Row index out of bounds");
    }

    /**
     * Test exception for invalid column insertion.
     */
    @Test
    public void testInsertColOutOfBounds() {
        Matrix<String> matrix = new MatrixV0<>(3, 3, "");
        assertThrows(IndexOutOfBoundsException.class, () -> matrix.insertCol(4), "Column index out of bounds");
    }


/**
     * Test the clone() method.
     */
    @Test
    public void testClone() {
        MatrixV0<Integer> original = new MatrixV0<>(3, 3, 1);
        original.set(1, 1, 5);
        MatrixV0<Integer> cloned = (MatrixV0<Integer>) original.clone();

        assertEquals(original, cloned, "Cloned matrix should be equal to the original.");
        assertNotSame(original, cloned, "Cloned matrix should not be the same reference.");
        assertEquals(5, cloned.get(1, 1), "Cloned matrix should contain same value.");
    }

    /**
     * Test the fillLine() method.
     */
    @Test
    public void testFillLine() {
        MatrixV0<String> matrix = new MatrixV0<>(5, 5, " ");
        matrix.fillLine(0, 0, 1, 1, 5, 5, "X");
        
        for (int i = 0; i < 5; i++) {
            assertEquals("X", matrix.get(i, i), "Matrix should have X along the diagonal.");
        }
    }

    /**
     * Test the equals() method.
     */
    @Test
    public void testEquals() {
        MatrixV0<String> matrix1 = new MatrixV0<>(2, 2, "A");
        MatrixV0<String> matrix2 = new MatrixV0<>(2, 2, "A");
        
        assertTrue(matrix1.equals(matrix2), "Matrices with the same content should be equal.");

        matrix1.set(0, 0, "B");
        assertFalse(matrix1.equals(matrix2), "Matrices with different content should not be equal.");
    }

} // TestsByStudent
