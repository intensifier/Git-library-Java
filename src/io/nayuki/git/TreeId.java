/* 
 * Git library
 * Copyright (c) Project Nayuki
 * 
 * https://www.nayuki.io/
 */

package io.nayuki.git;

import java.io.IOException;
import java.util.zip.DataFormatException;


/**
 * An immutable 160-bit (20-byte) SHA-1 hash, whose value should reflect a tree object.
 * @see TreeObject
 */
public class TreeId extends ObjectId {
	
	/*---- Constructors ----*/
	
	/**
	 * Constructs a tree object ID from the specified hexadecimal string.
	 * @param hexStr the hexadecimal string
	 * @throws NullPointerException if the string is {@code null}
	 * @throws IllegalArgumentException if the string isn't length 40 or has characters outside {0-9, a-f, A-F}
	 */
	public TreeId(String hexStr) {
		super(hexStr);
	}
	
	
	/**
	 * Constructs a tree object ID from the specified 20-byte array.
	 * @param bytes the byte array
	 * @throws NullPointerException if the array is {@code null}
	 * @throws IllegalArgumentException if array isn't length 20
	 */
	public TreeId(byte[] bytes) {
		super(bytes);
	}
	
	
	/**
	 * Constructs a tree object ID from 20 bytes in the specified array starting at the specified offset.
	 * @param bytes the byte array
	 * @param off the offset to start at
	 * @throws NullPointerException if the array is {@code null}
	 * @throws IndexOutOfBoundsException if the offset is negative,
	 * or there are fewer than 20 bytes remaining starting at that offset
	 */
	public TreeId(byte[] bytes, int off) {
		super(bytes, off);
	}
	
	
	
	/*---- Methods ----*/
	
	/**
	 * Reads the object data for this object ID from the associated repository.
	 * @param repo the repository to read from (not {@code null})
	 * @return the object data (not {@code null})
	 * @throws IOException if an I/O exception occurred
	 * @throws DataFormatException if malformed data was encountered during reading
	 * @throws ClassCastException if an object was successfully read but its type is not a tree object
	 */
	public TreeObject read(Repository repo) throws IOException, DataFormatException {
		return (TreeObject)super.read(repo);
	}
	
}
