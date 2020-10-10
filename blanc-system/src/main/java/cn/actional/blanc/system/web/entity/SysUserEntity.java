package cn.actional.blanc.system.web.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author actional
 * @email 854356662@qq.com
 * @date 2020-10-10 21:09:09
 */
@Data
@TableName("sys_user")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId
	private Long userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 性别 0:男 1:女 2:未知
	 */
	private String sex;
	/**
	 * 账号状态 0：启用 1：停用
	 */
	private String status;
	/**
	 * 删除状态 0：存在 1：删除
	 */
	private Boolean delFlag;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建者
	 */
	private String createBy;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 更新者
	 */
	private String updateBy;
	/**
	 * 最后登录时间
	 */
	private Date loginDate;
	/**
	 * 最后登录时间
	 */
	private Date loginIp;

}
