package com.stuil.cons.mapper;

import com.stuil.cons.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stuil
 * @since 2020-07-27
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> selectUser();
}
