/* 
 * Git library
 * Copyright (c) Project Nayuki
 * 
 * https://www.nayuki.io/page/git-library-java
 */

package io.nayuki.git;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;


/**
 * A hashable object that can be read from and written to a Git {@link Repository}.
 * Generally speaking, subclasses of {@code GitObject} are mutable.
 * @see BlobObject
 * @see TreeObject
 * @see CommitObject
 * @see TagObject
 */
public abstract class GitObject {
	
	/*---- Constructors ----*/
	
	// Only allows subclassing within this package.
	GitObject() {}
	
	
	
	/*---- Methods ----*/
	
	/**
	 * Returns the raw byte serialization of this object, including a lightweight header.
	 * @return the raw byte serialization of this object (not {@code null})
	 */
	public abstract byte[] toBytes();
	
	
	/**
	 * Returns the hash ID of the current state of this object.
	 * @return the hash ID of this object (not {@code null})
	 */
	public ObjectId getId() {
		return new ObjectId(getSha1Hash(toBytes()));
	}
	
	
	
	/*---- Static helper functions ----*/
	
	// Returns a new byte array containing some header fields prepended to the given byte data.
	static byte[] addHeader(String type, byte[] data) {
		byte[] header = String.format("%s %d\0", type, data.length).getBytes(StandardCharsets.US_ASCII);
		byte[] result = new byte[header.length + data.length];
		System.arraycopy(header, 0, result, 0, header.length);
		System.arraycopy(data, 0, result, header.length, data.length);
		return result;
	}
	
	
	// Returns a new byte array representing the SHA-1 hash of the given array of bytes.
	static byte[] getSha1Hash(byte[] b) {
		try {
			Objects.requireNonNull(b);
			return MessageDigest.getInstance("SHA-1").digest(b);
		} catch (NoSuchAlgorithmException e) {
			throw new AssertionError(e);
		}
	}
	
}
