package com.stormfives.ocpay.account.service;

import com.stormfives.ocpay.account.dao.AdminMapper;
import com.stormfives.ocpay.account.dao.entity.Admin;
import com.stormfives.ocpay.account.dao.entity.AdminExample;
import com.stormfives.ocpay.common.exception.InvalidArgumentException;
import com.stormfives.ocpay.token.domain.Token;
import com.stormfives.ocpay.token.service.OauthService;
import com.stormfives.ocpay.token.vo.TokenVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lyc
 * Date: 2018/3/14
 * Time: 下午2:51
 */
@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private OauthService oauthService;

    protected Logger logger = LoggerFactory.getLogger(AdminService.class);

    public static String INIT_PWD="123456";

    /**
     * 登录
     * @param name
     * @param password
     * @return
     * @throws InvalidArgumentException
     */
    public TokenVo login(String name, String password) throws InvalidArgumentException {

        if (StringUtils.isBlank(name))
            throw new InvalidArgumentException("无效用户名!");
        // 判断账号是否存在
        Admin admin = getAdminByName(name.trim());

        if (admin == null)
            throw new InvalidArgumentException("用户名或密码错误!");

        // 密码加密
        // 验证账户密码是否正确
        boolean access = BCrypt.checkpw(password, admin.getPassword());
        if (access){
            Token token = oauthService.generateToken(admin.getId(), "ocpay");

            if (token ==null){
                logger.error("获取token失败!,param name:{},password:{}", name,password);
            }
            //正确返回token
            TokenVo tokenVo = new TokenVo();
            BeanUtils.copyProperties(token,tokenVo);

            //TODO 记录日志
            return tokenVo;
        }else {
            throw new InvalidArgumentException("用户名或密码错误!");
        }


    }

    /**
     * 根据用户名获取用户信息
     * @param name
     * @return
     */
    public Admin getAdminByName(String name) {

        AdminExample adminExample = new AdminExample();
        adminExample.or().andNameEqualTo(name);
        adminExample.setOrderByClause("id desc");
        List<Admin> adminList = adminMapper.selectByExample(adminExample);

        if (adminList!=null && adminList.size()>0)
            return adminList.get(0);

        return null;

    }

    /**
     * 新增用户,初始化密码为123456,用户首次登录需要修改密码
     * @param name
     * @param password
     * @param phone
     * @param realName
     * @param adminId
     * @return
     * @throws InvalidArgumentException
     */
    public boolean addAdmin(String name, String password, String phone, String realName ,Integer adminId) throws InvalidArgumentException {

        //判断是否存在相同号码账号
        if (StringUtils.isBlank(name))
            throw new InvalidArgumentException("用户名不能为空!");

        if (StringUtils.isBlank(phone))
            throw new InvalidArgumentException("手机号不能为空!");

        Admin admin = getAdminByName(name);

        if (admin != null)
            throw new InvalidArgumentException("改账号已存在!");

        admin = new Admin();

        admin.setName(name);
        admin.setPhone(phone);
        admin.setRealName(realName);
        admin.setCreatedBy(adminId);
        admin.setUpdatedBy(adminId);
        admin.setCreatedAt(new Date());

        //设置初始密码为123456
        if (StringUtils.isBlank(password)){

            password = BCrypt.hashpw(INIT_PWD, BCrypt.gensalt());
        }else{
            password = BCrypt.hashpw(password, BCrypt.gensalt());
        }

        admin.setPassword(password);

        int insert = adminMapper.insertSelective(admin);

        //TODO 记录日志

        return (insert>0)?true:false;
    }

    public boolean  resetPassword(Integer adminId, String password,String name) throws InvalidArgumentException {


        //判断是否存在相同号码账号
        if (StringUtils.isBlank(name))
            throw new InvalidArgumentException("用户名不能为空!");

        Admin admin = getAdminById(adminId);
        if (admin == null)
            throw new InvalidArgumentException("账号不存在!");

        admin.setName(name);

        admin.setUpdatedBy(adminId);

        admin.setUpdatedAt(new Date());

        if (StringUtils.isBlank(password)){
            throw new InvalidArgumentException("新密码不能为空!");

        }else{
            password = BCrypt.hashpw(password, BCrypt.gensalt());
        }

        admin.setPassword(password);

        int insert = adminMapper.updateByPrimaryKeySelective(admin);

        //TODO 记录日志

        return (insert>0)?true:false;

    }

    public Admin getAdminById(Integer adminId) {

        AdminExample adminExample = new AdminExample();
        adminExample.or().andIdEqualTo(adminId);

        List<Admin> adminList = adminMapper.selectByExample(adminExample);

        if (adminList != null && adminList.size()>0)
            return adminList.get(0);

        return null;
    }
}
