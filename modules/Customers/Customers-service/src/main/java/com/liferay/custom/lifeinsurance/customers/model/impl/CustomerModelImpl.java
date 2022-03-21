/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.custom.lifeinsurance.customers.model.impl;

import com.liferay.custom.lifeinsurance.customers.model.Customer;
import com.liferay.custom.lifeinsurance.customers.model.CustomerModel;
import com.liferay.custom.lifeinsurance.customers.model.CustomerSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Customer service. Represents a row in the &quot;CUSTOMERS_Customer&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>CustomerModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CustomerImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CustomerImpl
 * @generated
 */
@JSON(strict = true)
public class CustomerModelImpl
	extends BaseModelImpl<Customer> implements CustomerModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a customer model instance should use the <code>Customer</code> interface instead.
	 */
	public static final String TABLE_NAME = "CUSTOMERS_Customer";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"customerId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"CustomerUserId", Types.BIGINT}, {"firstName", Types.VARCHAR},
		{"middleName", Types.VARCHAR}, {"lastName", Types.VARCHAR},
		{"email", Types.VARCHAR}, {"phoneNumber", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("customerId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CustomerUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("firstName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("middleName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("email", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("phoneNumber", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CUSTOMERS_Customer (uuid_ VARCHAR(75) null,customerId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,CustomerUserId LONG,firstName VARCHAR(75) null,middleName VARCHAR(75) null,lastName VARCHAR(75) null,email VARCHAR(75) null,phoneNumber VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table CUSTOMERS_Customer";

	public static final String ORDER_BY_JPQL =
		" ORDER BY customer.customerId DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CUSTOMERS_Customer.customerId DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CUSTOMERID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static Customer toModel(CustomerSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Customer model = new CustomerImpl();

		model.setUuid(soapModel.getUuid());
		model.setCustomerId(soapModel.getCustomerId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCustomerUserId(soapModel.getCustomerUserId());
		model.setFirstName(soapModel.getFirstName());
		model.setMiddleName(soapModel.getMiddleName());
		model.setLastName(soapModel.getLastName());
		model.setEmail(soapModel.getEmail());
		model.setPhoneNumber(soapModel.getPhoneNumber());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static List<Customer> toModels(CustomerSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Customer> models = new ArrayList<Customer>(soapModels.length);

		for (CustomerSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public CustomerModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _customerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCustomerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _customerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Customer.class;
	}

	@Override
	public String getModelClassName() {
		return Customer.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Customer, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Customer, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Customer, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Customer)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Customer, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Customer, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Customer)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Customer, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Customer, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Customer>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Customer.class.getClassLoader(), Customer.class,
			ModelWrapper.class);

		try {
			Constructor<Customer> constructor =
				(Constructor<Customer>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<Customer, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Customer, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Customer, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Customer, Object>>();
		Map<String, BiConsumer<Customer, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Customer, ?>>();

		attributeGetterFunctions.put("uuid", Customer::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<Customer, String>)Customer::setUuid);
		attributeGetterFunctions.put("customerId", Customer::getCustomerId);
		attributeSetterBiConsumers.put(
			"customerId", (BiConsumer<Customer, Long>)Customer::setCustomerId);
		attributeGetterFunctions.put("groupId", Customer::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<Customer, Long>)Customer::setGroupId);
		attributeGetterFunctions.put("companyId", Customer::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<Customer, Long>)Customer::setCompanyId);
		attributeGetterFunctions.put("userId", Customer::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Customer, Long>)Customer::setUserId);
		attributeGetterFunctions.put("userName", Customer::getUserName);
		attributeSetterBiConsumers.put(
			"userName", (BiConsumer<Customer, String>)Customer::setUserName);
		attributeGetterFunctions.put("createDate", Customer::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<Customer, Date>)Customer::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", Customer::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<Customer, Date>)Customer::setModifiedDate);
		attributeGetterFunctions.put(
			"CustomerUserId", Customer::getCustomerUserId);
		attributeSetterBiConsumers.put(
			"CustomerUserId",
			(BiConsumer<Customer, Long>)Customer::setCustomerUserId);
		attributeGetterFunctions.put("firstName", Customer::getFirstName);
		attributeSetterBiConsumers.put(
			"firstName", (BiConsumer<Customer, String>)Customer::setFirstName);
		attributeGetterFunctions.put("middleName", Customer::getMiddleName);
		attributeSetterBiConsumers.put(
			"middleName",
			(BiConsumer<Customer, String>)Customer::setMiddleName);
		attributeGetterFunctions.put("lastName", Customer::getLastName);
		attributeSetterBiConsumers.put(
			"lastName", (BiConsumer<Customer, String>)Customer::setLastName);
		attributeGetterFunctions.put("email", Customer::getEmail);
		attributeSetterBiConsumers.put(
			"email", (BiConsumer<Customer, String>)Customer::setEmail);
		attributeGetterFunctions.put("phoneNumber", Customer::getPhoneNumber);
		attributeSetterBiConsumers.put(
			"phoneNumber",
			(BiConsumer<Customer, String>)Customer::setPhoneNumber);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@JSON
	@Override
	public long getCustomerId() {
		return _customerId;
	}

	@Override
	public void setCustomerId(long customerId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_customerId = customerId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCustomerId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("customerId"));
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_groupId = groupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalGroupId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("groupId"));
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getCustomerUserId() {
		return _CustomerUserId;
	}

	@Override
	public void setCustomerUserId(long CustomerUserId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_CustomerUserId = CustomerUserId;
	}

	@Override
	public String getCustomerUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getCustomerUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setCustomerUserUuid(String CustomerUserUuid) {
	}

	@JSON
	@Override
	public String getFirstName() {
		if (_firstName == null) {
			return "";
		}
		else {
			return _firstName;
		}
	}

	@Override
	public void setFirstName(String firstName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_firstName = firstName;
	}

	@JSON
	@Override
	public String getMiddleName() {
		if (_middleName == null) {
			return "";
		}
		else {
			return _middleName;
		}
	}

	@Override
	public void setMiddleName(String middleName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_middleName = middleName;
	}

	@JSON
	@Override
	public String getLastName() {
		if (_lastName == null) {
			return "";
		}
		else {
			return _lastName;
		}
	}

	@Override
	public void setLastName(String lastName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lastName = lastName;
	}

	@JSON
	@Override
	public String getEmail() {
		if (_email == null) {
			return "";
		}
		else {
			return _email;
		}
	}

	@Override
	public void setEmail(String email) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_email = email;
	}

	@JSON
	@Override
	public String getPhoneNumber() {
		if (_phoneNumber == null) {
			return "";
		}
		else {
			return _phoneNumber;
		}
	}

	@Override
	public void setPhoneNumber(String phoneNumber) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_phoneNumber = phoneNumber;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(Customer.class.getName()));
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Customer.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Customer toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Customer>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CustomerImpl customerImpl = new CustomerImpl();

		customerImpl.setUuid(getUuid());
		customerImpl.setCustomerId(getCustomerId());
		customerImpl.setGroupId(getGroupId());
		customerImpl.setCompanyId(getCompanyId());
		customerImpl.setUserId(getUserId());
		customerImpl.setUserName(getUserName());
		customerImpl.setCreateDate(getCreateDate());
		customerImpl.setModifiedDate(getModifiedDate());
		customerImpl.setCustomerUserId(getCustomerUserId());
		customerImpl.setFirstName(getFirstName());
		customerImpl.setMiddleName(getMiddleName());
		customerImpl.setLastName(getLastName());
		customerImpl.setEmail(getEmail());
		customerImpl.setPhoneNumber(getPhoneNumber());

		customerImpl.resetOriginalValues();

		return customerImpl;
	}

	@Override
	public Customer cloneWithOriginalValues() {
		CustomerImpl customerImpl = new CustomerImpl();

		customerImpl.setUuid(this.<String>getColumnOriginalValue("uuid_"));
		customerImpl.setCustomerId(
			this.<Long>getColumnOriginalValue("customerId"));
		customerImpl.setGroupId(this.<Long>getColumnOriginalValue("groupId"));
		customerImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		customerImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		customerImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		customerImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		customerImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		customerImpl.setCustomerUserId(
			this.<Long>getColumnOriginalValue("CustomerUserId"));
		customerImpl.setFirstName(
			this.<String>getColumnOriginalValue("firstName"));
		customerImpl.setMiddleName(
			this.<String>getColumnOriginalValue("middleName"));
		customerImpl.setLastName(
			this.<String>getColumnOriginalValue("lastName"));
		customerImpl.setEmail(this.<String>getColumnOriginalValue("email"));
		customerImpl.setPhoneNumber(
			this.<String>getColumnOriginalValue("phoneNumber"));

		return customerImpl;
	}

	@Override
	public int compareTo(Customer customer) {
		int value = 0;

		if (getCustomerId() < customer.getCustomerId()) {
			value = -1;
		}
		else if (getCustomerId() > customer.getCustomerId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Customer)) {
			return false;
		}

		Customer customer = (Customer)object;

		long primaryKey = customer.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<Customer> toCacheModel() {
		CustomerCacheModel customerCacheModel = new CustomerCacheModel();

		customerCacheModel.uuid = getUuid();

		String uuid = customerCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			customerCacheModel.uuid = null;
		}

		customerCacheModel.customerId = getCustomerId();

		customerCacheModel.groupId = getGroupId();

		customerCacheModel.companyId = getCompanyId();

		customerCacheModel.userId = getUserId();

		customerCacheModel.userName = getUserName();

		String userName = customerCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			customerCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			customerCacheModel.createDate = createDate.getTime();
		}
		else {
			customerCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			customerCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			customerCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		customerCacheModel.CustomerUserId = getCustomerUserId();

		customerCacheModel.firstName = getFirstName();

		String firstName = customerCacheModel.firstName;

		if ((firstName != null) && (firstName.length() == 0)) {
			customerCacheModel.firstName = null;
		}

		customerCacheModel.middleName = getMiddleName();

		String middleName = customerCacheModel.middleName;

		if ((middleName != null) && (middleName.length() == 0)) {
			customerCacheModel.middleName = null;
		}

		customerCacheModel.lastName = getLastName();

		String lastName = customerCacheModel.lastName;

		if ((lastName != null) && (lastName.length() == 0)) {
			customerCacheModel.lastName = null;
		}

		customerCacheModel.email = getEmail();

		String email = customerCacheModel.email;

		if ((email != null) && (email.length() == 0)) {
			customerCacheModel.email = null;
		}

		customerCacheModel.phoneNumber = getPhoneNumber();

		String phoneNumber = customerCacheModel.phoneNumber;

		if ((phoneNumber != null) && (phoneNumber.length() == 0)) {
			customerCacheModel.phoneNumber = null;
		}

		return customerCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Customer, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Customer, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Customer, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((Customer)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<Customer, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Customer, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Customer, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Customer)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Customer>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private long _customerId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _CustomerUserId;
	private String _firstName;
	private String _middleName;
	private String _lastName;
	private String _email;
	private String _phoneNumber;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<Customer, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((Customer)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("customerId", _customerId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("CustomerUserId", _CustomerUserId);
		_columnOriginalValues.put("firstName", _firstName);
		_columnOriginalValues.put("middleName", _middleName);
		_columnOriginalValues.put("lastName", _lastName);
		_columnOriginalValues.put("email", _email);
		_columnOriginalValues.put("phoneNumber", _phoneNumber);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("uuid_", 1L);

		columnBitmasks.put("customerId", 2L);

		columnBitmasks.put("groupId", 4L);

		columnBitmasks.put("companyId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("CustomerUserId", 256L);

		columnBitmasks.put("firstName", 512L);

		columnBitmasks.put("middleName", 1024L);

		columnBitmasks.put("lastName", 2048L);

		columnBitmasks.put("email", 4096L);

		columnBitmasks.put("phoneNumber", 8192L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private Customer _escapedModel;

}