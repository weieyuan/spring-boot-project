package com.wei.utils;

import java.io.IOException;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.crypto.CipherText;
import org.owasp.esapi.crypto.CipherTextSerializer;
import org.owasp.esapi.crypto.PlainText;
import org.owasp.esapi.errors.EncryptionException;

public class EncryptorUtils {

	public static String encrypt(String str) {
		String dst = null;
		try {
			PlainText oPlainText = new PlainText(str);
			CipherText oCipherText = ESAPI.encryptor().encrypt(oPlainText);
			CipherTextSerializer oCipherTextSerializer = new CipherTextSerializer(oCipherText);
			byte[] b = oCipherTextSerializer.asSerializedByteArray();
			dst = ESAPI.encoder().encodeForBase64(b, false);
		} catch (EncryptionException e) {
			e.printStackTrace();
		}
		return dst;
	}

	public static String decrypt(String str) {
		String dst = null;
		try {
			byte[] b = ESAPI.encoder().decodeFromBase64(str);
			CipherTextSerializer oCipherTextSerializer = new CipherTextSerializer(b);
			CipherText oCipherText = oCipherTextSerializer.asCipherText();
			PlainText oPlainText = ESAPI.encryptor().decrypt(oCipherText);
			dst = oPlainText.toString();
		} catch (EncryptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dst;
	}

}
