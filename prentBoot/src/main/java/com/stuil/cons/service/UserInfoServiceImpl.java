package com.stuil.cons.service;

import com.stuil.cons.entity.UserInfo;
import com.stuil.cons.mapper.UserInfoMapper;
import com.stuil.cons.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stuil
 * @since 2021-02-02
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
