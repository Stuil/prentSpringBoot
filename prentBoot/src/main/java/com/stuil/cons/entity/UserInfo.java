package com.stuil.cons.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author stuil
 * @since 2021-02-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserInfo extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 备用字段1
     */
    private String reserveOne;

    /**
     * 备用字段2
     */
    private String reserveTwo;

    /**
     * 备用字段3
     */
    private String reserveThree;

    /**
     * 创建时间
     */
    private LocalDateTime createData;

    /**
     * 更新时间
     */
    private LocalDateTime updateData;

    /**
     * 是否启用
     */
    private Integer disable;


}
