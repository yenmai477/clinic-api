package com.yenmai.clinicrestfulapi.service.Implements;

import com.yenmai.clinicrestfulapi.dao.TaiKhoanRespository;
import com.yenmai.clinicrestfulapi.entity.TaiKhoan;
import com.yenmai.clinicrestfulapi.model.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YenMai
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    TaiKhoanRespository taiKhoanRespository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        TaiKhoan user = taiKhoanRespository.findByTenTaiKhoan(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> username or email : " + username)
                );

        return UserPrinciple.build(user);
    }
}