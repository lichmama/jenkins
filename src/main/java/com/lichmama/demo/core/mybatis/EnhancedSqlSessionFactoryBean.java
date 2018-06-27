package com.lichmama.demo.core.mybatis;

import java.io.File;
import java.io.IOException;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.StringUtils;

import com.lichmama.demo.common.util.StringUtil;

public class EnhancedSqlSessionFactoryBean extends SqlSessionFactoryBean {
	private static Logger logger = LoggerFactory.getLogger(EnhancedSqlSessionFactoryBean.class);
	private static final String START_SIGN = File.separator + "classes" + File.separator + "com" + File.separator;
	private static final String PACKAGE_DELIMITER = ";";

	@Override
	public void setTypeAliasesPackage(String typeAliasesPackage) {
		try {
			typeAliasesPackage = rebuildTypeAliasesPackage(typeAliasesPackage);
		} catch (Exception e) {
			logger.error("未知异常", e);
		}
		super.setTypeAliasesPackage(typeAliasesPackage);
	}

	/**
	 * 增加对通配符（*）的支持
	 * 
	 * @param typeAliasesPackage
	 * @return
	 * @throws IOException
	 */
	public String rebuildTypeAliasesPackage(String typeAliasesPackage) {
		ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
		StringBuffer buff = new StringBuffer();
		if (StringUtil.isEmpty(typeAliasesPackage))
			return typeAliasesPackage;
		String[] packages = StringUtils.tokenizeToStringArray(typeAliasesPackage, ",; \t\n");
		if (packages.length > 0) {
			for (int i = 0; i < packages.length; i++) {
				if (i != 0)
					buff.append(PACKAGE_DELIMITER);
				if (packages[i].contains("*")) {
					try {
						getPatternPackages(buff, packages[i], resolver);
					} catch (IOException e) {
						logger.error("查找包的资源路径时发生IO异常", e);
					}
				} else {
					// 不含有通配符（*）
					buff.append(packages[i]);
				}
			}
		}
		return buff.toString();
	}

	public void getPatternPackages(StringBuffer buff, String pattern, ResourcePatternResolver resolver)
			throws IOException {
		Resource[] resources = resolver.getResources(pattern.replace('.', File.separatorChar));
		// 未找到包的资源路径
		if (resources.length == 0)
			throw new IllegalArgumentException("typeAliasesPackage配置错误，无效的路径！");
		for (int j = 0; j < resources.length; j++) {
			if (j != 0)
				buff.append(PACKAGE_DELIMITER);
			String description = resources[j].getDescription();
			int start = description.indexOf(START_SIGN) + 9;
			int end = description.length() - 1;
			buff.append(description.substring(start, end).replace(File.separatorChar, '.'));
		}
	}
}
