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

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.datastax.driver.mapping.EnumType;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Enumerated;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.hiperium.commons.services.EnumAccessChannel;
import com.hiperium.commons.services.EnumDeviceAction;

/**
 * This class represents the user device activity audit.
 * 
 * @author Andres Solorzano
 */
@Table(keyspace="haudit", name="aud_user_device")
public class UserDevice implements Serializable {

	/** The property serialVersionUID. */
	private static final long serialVersionUID = -7961230535198343731L;

	/** The property id. */
	@PartitionKey
	@Min(value = 1L)
	@Column(name = "id")
	private UUID id;
	
	/** The property userId. */
	@NotNull
	@Min(value = 1L)
	@Column(name = "user_id")
	private Long userId;
	
	/** The property deviceId. */
	@NotNull
	@Min(value = 1L)
	@Column(name = "device_id")
	private Long deviceId;
	
	/** The property eventDate. */
	@NotNull
	@Column(name = "event_date")
	private Date eventDate;
	
	/** The property ipConnection. */
	@NotNull
	@Column(name = "ip_connection")
	private String ipConnection;
	
	/** The property accessChannel. */
	@NotNull
	@Column(name = "access_channel")
	@Enumerated(EnumType.STRING)
	private EnumAccessChannel accessChannel;
	
	/** The property action. */
	@NotNull
	@Column(name = "action")
	@Enumerated(EnumType.STRING)
	private EnumDeviceAction action;

	/**
	 * Default constructor.
	 */
	public UserDevice() {
		this.accessChannel = EnumAccessChannel.MOBILE;
		this.action = EnumDeviceAction.ACTIVATE;
	}

	/**
	 * Class constructor.
	 * 
	 * @param userId
	 * @param deviceId
	 */
	public UserDevice(Long userId, Long deviceId) {
		this.userId = userId;
		this.deviceId = deviceId;
	}

	/**
	 * Gets the id property.
	 * 
	 * @return the id property.
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Sets the id property.
	 * 
	 * @param id
	 *            the variable id to be assigned.
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * Gets the eventDate property.
	 * 
	 * @return the eventDate property.
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * Sets the eventDate property.
	 * 
	 * @param eventDate
	 *            the variable eventDate to be assigned.
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * Gets the ipConnection property.
	 * 
	 * @return the ipConnection property.
	 */
	public String getIpConnection() {
		return ipConnection;
	}

	/**
	 * Sets the ipConnection property.
	 * 
	 * @param ipConnection
	 *            the variable ipConnection to be assigned.
	 */
	public void setIpConnection(String ipConnection) {
		this.ipConnection = ipConnection;
	}

	/**
	 * Gets the accessChannel property.
	 * 
	 * @return the accessChannel property.
	 */
	public EnumAccessChannel getAccessChannel() {
		return accessChannel;
	}

	/**
	 * Sets the accessChannel property.
	 * 
	 * @param accessChannel
	 *            the variable accessChannel to be assigned.
	 */
	public void setAccessChannel(EnumAccessChannel accessChannel) {
		this.accessChannel = accessChannel;
	}

	/**
	 * Gets the action property.
	 * 
	 * @return the action property.
	 */
	public EnumDeviceAction getAction() {
		return action;
	}

	/**
	 * Sets the action property.
	 * 
	 * @param action
	 *            the variable action to be assigned.
	 */
	public void setAction(EnumDeviceAction action) {
		this.action = action;
	}

	/**
	 * Get the userId property.
	 * 
	 * @return the userId property.
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Set the userId property.
	 * 
	 * @param userId
	 *            the userId to set.
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Get the deviceId property.
	 * 
	 * @return the deviceId property.
	 */
	public Long getDeviceId() {
		return deviceId;
	}

	/**
	 * Set the deviceId property.
	 * 
	 * @param deviceId
	 *            the deviceId to set.
	 */
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDevice other = (UserDevice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserDevice [id=" + id + ", userId=" + userId + ", deviceId="
				+ deviceId + ", eventDate=" + eventDate + ", ipConnection="
				+ ipConnection + ", accessChannel=" + accessChannel
				+ ", action=" + action + "]";
	}

}
