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
package com.hiperium.logging.dao.module.model;

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

/**
 * This class contains the fields belonging to the user audit.
 * 
 * @author Andres Solorzano
 */
@Table(keyspace="haudit", name="aud_user_activity")
public class UserActivity implements Serializable {

	/**
	 * The property serialVersionUID.
	 */
	private static final long serialVersionUID = -6942509339776215353L;

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

	/** The property homeId. */
	@NotNull
	@Min(value = 1L)
	@Column(name = "home_id")
	private Long homeId;

	/** The property profileId. */
	@NotNull
	@Min(value = 1L)
	@Column(name = "profile_id")
	private Long profileId;

	/** The property serviceName. */
	@NotNull
	@Column(name = "service_name")
	private String serviceName;

	/** The property registerDate. */
	@NotNull
	@Column(name = "register_date")
	private Date registerDate;

	/** The property registerIP. */
	@NotNull
	@Column(name = "register_ip")
	private String registerIP;

	/** The property accessChannel. */
	@NotNull
	@Column(name = "access_channel")
	@Enumerated(EnumType.STRING)
	private EnumAccessChannel accessChannel;

	/** The property crudOperation. */
	@NotNull
	@Column(name = "crud_operation")
	@Enumerated(EnumType.STRING)
	private EnumCrudOperation crudOperation;

	/**
	 * Default Constructor.
	 */
	public UserActivity() {
		this.crudOperation = EnumCrudOperation.CREATE;
		this.accessChannel = EnumAccessChannel.MOBILE;
	}

	/**
	 * Get the id property.
	 * 
	 * @return the id property.
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Set the id property.
	 * 
	 * @param id
	 *            the id to set.
	 */
	public void setId(UUID id) {
		this.id = id;
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
	 * Get the homeId property.
	 * 
	 * @return the homeId property.
	 */
	public Long getHomeId() {
		return homeId;
	}

	/**
	 * Set the homeId property.
	 * 
	 * @param homeId
	 *            the homeId to set.
	 */
	public void setHomeId(Long homeId) {
		this.homeId = homeId;
	}

	/**
	 * Get the profileId property.
	 * 
	 * @return the profileId property.
	 */
	public Long getProfileId() {
		return profileId;
	}

	/**
	 * Set the profileId property.
	 * 
	 * @param profileId
	 *            the profileId to set.
	 */
	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	/**
	 * Get the crudOperation property.
	 * 
	 * @return the crudOperation property.
	 */
	public EnumCrudOperation getCrudOperation() {
		return crudOperation;
	}

	/**
	 * Set the crudOperation property.
	 * 
	 * @param crudOperation
	 *            the crudOperation to set.
	 */
	public void setCrudOperation(EnumCrudOperation crudOperation) {
		this.crudOperation = crudOperation;
	}

	/**
	 * Get the serviceName property.
	 * 
	 * @return the serviceName property.
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * Set the serviceName property.
	 * 
	 * @param serviceName
	 *            the serviceName to set.
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * Get the registerDate property.
	 * 
	 * @return the registerDate property.
	 */
	public Date getRegisterDate() {
		return registerDate;
	}

	/**
	 * Set the registerDate property.
	 * 
	 * @param registerDate
	 *            the registerDate to set.
	 */
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	/**
	 * Get the registerIP property.
	 * 
	 * @return the registerIP property.
	 */
	public String getRegisterIP() {
		return registerIP;
	}

	/**
	 * Set the registerIP property.
	 * 
	 * @param registerIP
	 *            the registerIP to set.
	 */
	public void setRegisterIP(String registerIP) {
		this.registerIP = registerIP;
	}

	/**
	 * Gets the accessChannel property.
	 * 
	 * @return the accessChannel.
	 */
	public EnumAccessChannel getAccessChannel() {
		return accessChannel;
	}

	/**
	 * Sets the accessChannel property.
	 * 
	 * @param accessChannel
	 *            the accessChannel to set.
	 */
	public void setAccessChannel(EnumAccessChannel accessChannel) {
		this.accessChannel = accessChannel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		UserActivity other = (UserActivity) obj;
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
		return "UserSessionAudit [id=" + id + ", userId=" + userId
				+ ", homeId=" + homeId + ", profileId=" + profileId
				+ ", serviceName=" + serviceName + ", registerDate="
				+ registerDate + ", registerIP=" + registerIP
				+ ", accessChannel=" + accessChannel + ", crudOperation="
				+ crudOperation + "]";
	}

}
