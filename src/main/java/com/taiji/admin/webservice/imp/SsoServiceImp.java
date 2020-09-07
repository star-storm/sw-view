package com.taiji.admin.webservice.imp;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.apache.axis2.AxisFault;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.taiji.admin.config.BeanConfig;
import com.taiji.admin.constant.Constant;
import com.taiji.admin.mapper.SOrgMapper;
import com.taiji.admin.mapper.SUserMapper;
import com.taiji.admin.mapper.SUserRoleMapper;
import com.taiji.admin.model.SOrg;
import com.taiji.admin.model.SUser;
import com.taiji.admin.model.SUserRoleExample;
import com.taiji.admin.service.LoginService;
import com.taiji.admin.service.SOrgService;
import com.taiji.admin.service.SUserService;
import com.taiji.admin.utils.WebserviceUtil;
import com.taiji.admin.utils.XmlUtil;
import com.taiji.admin.utils.encrypt.Encrypt;
import com.taiji.admin.webservice.SsoService;

@WebService(targetNamespace="http://sso/services",name="sso",serviceName="ssoWebservice")  
public class SsoServiceImp implements SsoService {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private SUserService userService;
	
	@Autowired
	private SUserMapper userMapper;
	
	@Autowired
	private SUserRoleMapper userRoleMapper;
	
	@Autowired
	private SOrgService orgService;
	
	@Autowired
	private SOrgMapper orgMapper;
	
	@Autowired
	BeanConfig beanIns;
	
    @Override  
    public String yuan(String param) {  
        return "param=" + param;  
    } 
    
    @Override  
    public String login(String authTicket) throws AxisFault, DocumentException {
		System.out.println(authTicket);
		String result = WebserviceUtil.loginWebservice(beanIns.zcptLoginUrl, beanIns.zcptLoginQname, authTicket);
		String userCode = WebserviceUtil.getUserCode(result);
		System.out.println("登录用户：" + userCode);
		int n = loginService.verifyUser(request.getSession(), userCode);
		switch (n) {
		case 1:
			return "-1001";
		case 3:
		case 4:
			return "-1002";

		default:
			break;
		}
		return "-000";
	}
    
    @Override 
    public String SynchronizedInfo(int operateId, String operateCode) throws DocumentException, IOException {
    	System.out.println(operateCode);
    	String result = null;    	
    	if (operateId == 11 || operateId == 12 || operateId == 13) {
    		result = WebserviceUtil.synUserWebservice(beanIns.zcptSynUrl, beanIns.zcptSynQname, operateId, operateCode);
    		System.out.println("返回结果：" + result);
    		switch (operateId) {
    		case 11://新建用户
    		case 12://编辑用户
    			return updateUser(result);
    		case 13://删除用户
    			return deleteUser(result);
    		default:
    			break;
    		}
    	}
    	else if (operateId == 41 || operateId == 42 || operateId == 43) {
    		result = WebserviceUtil.synOrgWebservice(beanIns.zcptSynUrl, beanIns.zcptSynQname, operateId, operateCode);
        	System.out.println("返回结果：" + result);
    		switch (operateId) {
    		case 41://新建组织formatOrg
    		case 42://编辑组织
    			return updateOrg(result);
    		case 43://删除组织
    			return deleteOrg(result);
    		default:
    			break;
    		}
    	}
    	return "-000";
    }
	
	/**
	 * 新建，更新user
	 */
    @Override
	public String updateUser(String s) throws IOException, DocumentException {
		List<Map<String, String>> list = XmlUtil.parseXml(s, "xml", "logname","userCode","name",
			"officePhone","mobilePhone","userDuty","orgCode","userOrder","userPic","userPotitle",
			"address", "userToLoan","fax");
		return updateUser(list.get(0));
	}

	/**
	 * 新建，更新user
	 * @throws DocumentException 
	 * @throws IOException 
	 */
	private String updateUser(Map<String, String> map) throws IOException, DocumentException {
		SUser user = new SUser();
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
//			System.out.println("key = " + key + ", value = " + value);
			if (key.equals("userCode")) {
				SUser userTmp = userMapper.selectByUserCode(value);
				if (userTmp == null)
					user.setUserCode(value);
				else
					user.setId(userTmp.getId());
			}
			else if (key.equals("logname"))
				user.setLoginName(value);
			else if (key.equals("name")) {
				user.setName(value);
			}
			else if (key.equals("orgCode")) {
				SOrg org = orgMapper.selectByCode(value);
				if (org == null) {
					System.out.println("orgCode为["+value+"]--未找到对应组织");
					return "-403";
				}
				else {
					int orgId = org.getId();
					user.setOrgId(String.valueOf(orgId));
					user.setOrgCode(value);
					user.setOrgName(org.getName());
				}
			}
			user.setUserPwd(Encrypt.encryptString(Constant.DEFAULT_USER_PASSWORD));//密码123456--075091074065079095
		}
		SUser host = new SUser();
		host.setId(-1);
		Integer id = userService.updateUser(user, host);//更新用户表
		System.out.println("刚插入用户数据，生成的用户ID=["+id+"]");
		SUser userTmp = userMapper.selectByPrimaryKey(id);
		if (userTmp == null) {
			System.out.println("userCode为["+user.getUserCode()+"]--未找到对应用户，无法生成对应的通讯录数据");
			return "-501";
		}
		else {
			//更新用户角色
			userService.upUserRole(id, Constant.USER_ROLE_ID, host);
		}
		return "-000";
	}
	//空置转换为空串
	@SuppressWarnings("unused")
	private String parseNullValue(String value) {
		if (StringUtils.isEmpty(value))
			value = "";
		if (value.toLowerCase().equalsIgnoreCase("null"))
			value = "";
		return value;
	}
	
	/**
	 * 删除user
	 */
	public String deleteUser(String s) throws IOException, DocumentException {
		List<Map<String, String>> list = XmlUtil.parseXml(s, "xml", "userCode");
		return deleteUser(list.get(0));
	}
	
	/**
	 * 删除user
	 */
	private String deleteUser(Map<String, String> map) {
		String userCode = "";
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (key.equals("userCode")) {
				userCode = map.get(key);
				System.out.println(userCode);
				break;
			}
		}
		SUser userTmp = userMapper.selectByUserCode(userCode);
		if (userTmp == null)
			return "-601";
		
		//删除原用户角色
		SUserRoleExample example = new SUserRoleExample();
		SUserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(userTmp.getId());
		userRoleMapper.deleteByExample(example);
		
		userMapper.deleteByPrimaryKey(userTmp.getId());
		return "-000";
	}
	
	/**
	 * 新建，修改组织
	 */
    @Override
	public String updateOrg(String s) throws IOException, DocumentException {
		List<Map<String, String>> list = XmlUtil.parseXml(s, "xml", "orgCode", "parentCode", "name", "orgOrder");
		return updateOrg(list.get(0));
	}
	
	/**
	 * 新建，修改组织
	 */
	private String updateOrg(Map<String, String> map) {
		SOrg org = new SOrg();
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
//			System.out.println("key = " + key + ", value = " + value);
			if (key.equals("orgCode")) {
				SOrg orgTmp = orgMapper.selectByCode(value);
				if (orgTmp != null)
					org.setId(orgTmp.getId());
				org.setCode(value);
			}
			else if (key.equals("parentCode")) {
				if (value.equals(Constant.ORG_ROOT_PARENT)) {
					org.setPCode(value);
					org.setPid(Integer.valueOf(value));
				}
				else {
					SOrg orgTmp = orgMapper.selectByCode(value);
					if (orgTmp == null) {
						System.out.println("parentCode为["+value+"]--未找到对应父组织");
						return "-302";
					}
					else {
						int pOrgId = orgTmp.getId();
						org.setPid(pOrgId);
						org.setPCode(value);
					}
				}
			}
			else if (key.equals("name"))
				org.setName(value);
			else if (key.equals("orgOrder"))
				org.setSort(Integer.valueOf(value));
		}
		SUser host = new SUser();
		host.setId(-1);
		orgService.updateOrg(org, host);
		return "-000";
	}
	
	/**
	 * 删除org
	 */
	public String deleteOrg(String s) throws IOException, DocumentException {
		List<Map<String, String>> list = XmlUtil.parseXml(s, "xml", "orgCode");
		return deleteOrg(list.get(0));
	}
	
	/**
	 * 删除org
	 */
	private String deleteOrg(Map<String, String> map) {
		String orgCode = "";
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (key.equals("orgCode")) {
				orgCode = map.get(key);
				System.out.println(orgCode);
				break;
			}
		}
		SOrg orgTmp = orgMapper.selectByCode(orgCode);
		if (orgTmp == null)
			return "-201";
		orgMapper.deleteByPrimaryKey(orgTmp.getId());
		return "-000";
	}
    
    public static void main(String[] args) throws IOException, DocumentException {
		String xml = "";
		xml = "<userinfo>";
		xml += "<xml>\r\n";
		xml += "<logname>getLogName</logname>\r\n";
		xml += "<userCode>getUserCode</userCode>\r\n";
		xml += "<name>getName</name>\r\n";
		xml += "<sex>getSex</sex>\r\n";
		xml += "<address>getAddress</address>\r\n";

		// xml+="<postCode>"+tempUserc+"</postCode>\r\n";//邮编存放证书号
		xml += "<postCode>" + "123456" + "</postCode>\r\n";
		xml += "<officePhone>getOfficePhone</officePhone>\r\n";// 座机
		xml += "<mobilePhone>getMobilePhone</mobilePhone>\r\n";// 手机
		xml += "<fax>getFax</fax>\r\n";
		xml += "<email>getEmail</email>\r\n";
		xml += "<userType>" + "0" + "</userType>\r\n";
		// xml+="<userType>"+tempUser.getUserType()+"</userType>\r\n";
		xml += "<userPost>getUserPost</userPost>\r\n";
		xml += "<userDuty>getUserDuty</userDuty>\r\n"; // 职务
		xml += "<orgCode>getOrg().getOrgCode</orgCode>\r\n";
		xml += "<userOrder>getUserOrder</userOrder>\r\n";
		xml += "<userPic>getUserPic</userPic>\r\n";// 图片
		xml += "<userToLoan>1</userToLoan>\r\n";// 图片
		xml += "<userPotitle>getUserPotitle</userPotitle>\r\n"; // 职称
		xml += "<roles>\r\n";
		xml += "</roles>\r\n";
		xml+="</xml>\r\n";
		xml+="</userinfo>\r\n";
		   
//		List<Map<String, String>> list = XmlUtil.parseXml(xml, "xml", "logname","userCode","name",
//				"officePhone","mobilePhone","userDuty","orgCode","userOrder","userPic","userPotitle");
//		List<Map<String, String>> list = XmlUtil.parseXml(xml, "xml", "userCode");
//		System.out.println(list);
//		new SsoServiceImp().updateUser(xml);
//		new SsoServiceImp().deleteUser(xml);
		
		
		xml="<userinfo>";
		xml+="<xml>\r\n";
		xml+="<orgCode>getOrgCode</orgCode>\r\n";
		xml+="<parentCode>getParent().getOrgCode</parentCode>\r\n";
		xml+="<name>getName</name>\r\n";
		xml+="<deptBm>getBm</deptBm>\r\n";
		xml+="<address>getAddress</address>\r\n";
		xml+="<phone>getPhone</phone>\r\n";
		xml+="<orgOrder>getOrgOrder</orgOrder>\r\n";
		xml+="</xml>\r\n";
		xml+="</userinfo>\r\n";

//		List<Map<String, String>> list = XmlUtil.parseXml(xml, "xml", "orgCode", "parentCode", "name", "orgOrder");
//		List<Map<String, String>> list = XmlUtil.parseXml(xml, "xml", "orgCode");
//		System.out.println(list);
		new SsoServiceImp().deleteOrg(xml);
	}
}
