package crypto_analyser_db.crypto.exceptions;

/**
 * Custom exception class to handle cases when a cryptocurrency is not found in the database.
 * Extends RuntimeException, so it is an unchecked exception.
 */
public class CryptoNotFoundExceptions extends RuntimeException {

    // Unique identifier for serialization (to avoid warnings)
    private static final long serialVersionUID = 1L;

    /**
     * Constructor that accepts a Long type ID.
     * 
     * @param id the unique ID of the cryptocurrency that was not found
     */
    public CryptoNotFoundExceptions(Long id) {
        super("Crypto with ID " + id + " not found.");  // Constructs an error message with the missing crypto's ID
    }

    /**
     * Constructor that accepts a String type ID.
     * This could be useful in cases where IDs are represented as strings.
     * 
     * @param id the unique ID of the cryptocurrency that was not found
     */
    public CryptoNotFoundExceptions(String id) {
        super("Crypto with ID " + id + " not found.");  // Constructs an error message with the missing crypto's ID (string format)
    }
}
