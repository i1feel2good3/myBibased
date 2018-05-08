package com.bibased.leibobo.domain;

import com.bibased.leibobo.domain.support.AbstractEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.assertj.core.util.Preconditions;

import javax.persistence.*;

/**
 * Created by boboLei on 2018/4/15
 */
@Entity
@Data
@Table(name = "b_users")
public class UserSuper extends AbstractEntity{

	@Column(name = "user_name", nullable = false)
	@Setter(AccessLevel.PACKAGE)
	private String userName;	//	用户名

	@Column(name = "real_name", nullable = false)
	@Setter(AccessLevel.PACKAGE)
	private String realName;	//	真实姓名

	@Column(name = "pass_word", nullable = false)
	@Setter(AccessLevel.PACKAGE)
	private String passWord;	//	密码

	@Column(name = "mobile_phone", nullable = false)
	@Setter(AccessLevel.PACKAGE)
	private String mobilePhone;	//	手机号

	@Column(name = "user_role", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	@Setter(AccessLevel.PACKAGE)
	private UserRoleEnum userRole;	//	用户角色

	@Column(name = "id_type",nullable = false)
	@Enumerated(EnumType.ORDINAL)
	@Setter(AccessLevel.PACKAGE)
	private UserIdType idType;		//身份类型

	@Column(name = "id_number",nullable = false)
	@Setter(AccessLevel.PACKAGE)
	private String idNumber;	//身份号

	public UserSuper(){

	}

	/**
	 * @param userName
	 * @param realName
	 * @param passWord
	 * @param mobilePhone
	 * @param userRoleCode
	 * @param idTypeCode
	 * @param idNumber
	 */
	public UserSuper(String userName,String realName,String passWord,String mobilePhone,
					 Integer userRoleCode,Integer idTypeCode,String idNumber){
		Preconditions.checkArgument(userName != null,"The String userName is null");
		Preconditions.checkArgument(realName != null,"The String realName is null");
		Preconditions.checkArgument(passWord != null,"The String password is null");
		Preconditions.checkArgument(mobilePhone != null,"The String mobilePhone is null");
		Preconditions.checkArgument(userRoleCode != null,"The String idNumber is null");
		Preconditions.checkArgument(idTypeCode != null,"The String idNumber is null");
		Preconditions.checkArgument(idNumber != null,"The String idNumber is null");

		selectIdType(userRoleCode,idTypeCode);
		init();

		setUserName(userName);
		setRealName(realName);
		setPassWord(passWord);
		setMobilePhone(mobilePhone);
		setIdNumber(idNumber);
	}

	/**
	 * @param userName
	 * @param realName
	 * @param passWord
	 * @param mobilePhone
	 * @param idTypeCode
	 * @param idNumber
	 */
	public UserSuper(String userName,String realName,String passWord,String mobilePhone,
					 Integer idTypeCode,String idNumber){
		Preconditions.checkArgument(userName != null,"The String userName is null");
		Preconditions.checkArgument(realName != null,"The String realName is null");
		Preconditions.checkArgument(passWord != null,"The String password is null");
		Preconditions.checkArgument(mobilePhone != null,"The String mobilePhone is null");
		Preconditions.checkArgument(idTypeCode != null,"The String idNumber is null");
		Preconditions.checkArgument(idNumber != null,"The String idNumber is null");

		initUserRole(idTypeCode);
		init();
		setUserRole(UserRoleEnum.USER_PATIENT);
		setUserName(userName);
		setRealName(realName);
		setPassWord(passWord);
		setMobilePhone(mobilePhone);
		setIdNumber(idNumber);
	}

	public UserSuper(String userName,String passWord){
		setUserName(userName);
		setPassWord(passWord);
		setUserRole(UserRoleEnum.USER_SUPER);
	}

	public void updatePassWord(String passWord){
		setPassWord(passWord);
	}

	//初始化版本号
	@Override
	public void init() {
		super.init();
	}

	public void initUserRole(Integer userRoleCode){
		switch (userRoleCode){
			case 0:setIdType(UserIdType.IDENTITY_CARD);break;
			case 1:setIdType(UserIdType.MILITARY_CARD);break;
			case 2:setIdType(UserIdType.OTHER);break;
		}
	}

	/**
	 *1.根据传来的code值，确定人物证件类型
	 * @param userRoleCode
	 * @param idTypeCode
	 */
	protected void selectIdType(Integer userRoleCode,Integer idTypeCode){
		switch (userRoleCode){
			case 0:setUserRole(UserRoleEnum.USER_SUPER);break;
			case 1:setUserRole(UserRoleEnum.USER_DOCTOR);break;
			case 2:setUserRole(UserRoleEnum.USER_PATIENT);break;
		}
		switch (idTypeCode){
			case 0:setIdType(UserIdType.IDENTITY_CARD);break;
			case 1:setIdType(UserIdType.MILITARY_CARD);break;
			case 2:setIdType(UserIdType.OTHER);break;
		}
	}
}
