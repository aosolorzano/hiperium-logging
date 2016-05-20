/**
 * Product  : Hiperium Project
 * Architect: Andres Solorzano.
 * Created  : 08-05-2009 - 23:30:00
 * 
 * The contents of this file are copyrighted by Andres Solorzano 
 * and it is protected by the license: "GPL V3." You can find a copy of this 
 * license at: http://www.hiperium.com/about/licence.html
 * 
 * Copyright 2014 Andres Solorzano. All rights reserved.
 * 
 */
package com.hiperium.audit.dao.module.model;

/**
 * This enumeration specifies the CRUD operations that can be create, update and
 * delete.
 * 
 * @author Andres Solorzano
 */
public enum EnumCrudOperation {

	/** The CREATE element witch value is create. */
	CREATE("create", "crudOperation.create"),

	/** The CREATE element witch value is find. */
	READ("find", "crudOperation.find"),

	/** The UPDATE element witch value is update. */
	UPDATE("update", "crudOperation.update"),

	/** The DELETE element witch value is delete. */
	DELETE("delete", "crudOperation.delete");

	/** Property value. */
	private final String value;

	/** Property messageId. */
	private final String messageId;

	/**
	 * The Enumeration constructor.
	 * 
	 * @param valor
	 *            Value for the element of the Enumeration
	 * @param messageId
	 *            the message ID for i18n transformation
	 */
	private EnumCrudOperation(String value, String messageId) {
		this.value = value;
		this.messageId = messageId;
	}

	/**
	 * Return the value associate to the element of the enumeration.
	 * 
	 * @return the value associate with the enumeration
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Gets the messageId property.
	 * 
	 * @return the messageId.
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * Decodes a label of a specific value.
	 * 
	 * @param value
	 *            the value to be decoded
	 * @return a label of a specific value.
	 */
	public static EnumCrudOperation decodeValue(String value) {
		for (EnumCrudOperation e : EnumCrudOperation.values()) {
			if (e.getValue().contains(value)) {
				return e;
			}
		}
		return EnumCrudOperation.READ;
	}
}
