package com.lichmama.demo.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.UrlResource;

import lombok.extern.slf4j.Slf4j;

public class ConfigUtil implements InitializingBean {
	private static final int INITIAL_CAPACITY = 1000;

	@Slf4j
	private static class ConfigMap extends ConcurrentHashMap<String, Object> {
		public ConfigMap(List<String> configLocations) {
			super(INITIAL_CAPACITY);
			initConfigMap(configLocations);
		}

		private void initConfigMap(List<String> configLocations) {
			if (configLocations == null || configLocations.size() == 0) {
				log.debug("initConfigMap finished, configLocations is null");
				return;
			}
			for (String configFile : configLocations)
				loadConfigFile(configFile);
		}

		/**
		 * support 3 protocols: http|ftp|file
		 * 
		 * @param configFile
		 * @throws IOException
		 */
		private void loadConfigFile(String configFile) {
			log.debug("loading configFile: {}", configFile);
			if (!configFile.matches("^(?:http|ftp|file)://.*?$")) {
				if (!configFile.startsWith("/"))
					configFile = "/" + configFile;
				configFile = "file://" + configFile;
			}
			try (InputStream is = new UrlResource(configFile).getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));) {
				String line;
				while ((line = br.readLine()) != null) {
					if (!line.matches("^[A-Za-z0-9._]+\\s*=.*?$"))
						continue;
					String[] keyAndValue = line.split("=", 2);
					String key = keyAndValue[0].trim();
					String value = keyAndValue[1];
					if (StringUtil.isNotEmpty(value)) {
						value = StringUtil.ltrim(value);
						value = StringUtil.rtrim(value);
					}
					put(key, value);
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
				throw new IllegalArgumentException(
						"unexpected exception occurs when load configFile [" + configFile + "]");
			}
		}
	}

	private ConfigUtil() {
	}

	private static ConfigMap configMap;
	private List<String> configLocations;

	public List<String> getConfigLocations() {
		return configLocations;
	}

	public void setConfigLocations(List<String> configLocations) {
		this.configLocations = configLocations;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		configMap = new ConfigMap(configLocations);
	}

	public static Object getConfig(String key) {
		return configMap.get(key);
	}
	
	public static Object getConfig(String key, Object preset) {
		if (!exists(key))
			return preset;
		return getConfig(key);
	}

	public static void setConfig(String key, Object value) {
		configMap.put(key, value);
	}
	
	public static void setConfig(Map<String, Object> map) {
		configMap.putAll(map);
	}

	public static boolean exists(String key) {
		return configMap.containsKey(key);
	}

	public static String getString(String key) {
		Object object = getConfig(key);
		if (object == null)
			return "";
		return String.valueOf(object);
	}

	public static int getInt(String key) {
		return Integer.parseInt(getString(key));
	}

	public static boolean getBoolean(String key) {
		return Boolean.parseBoolean(getString(key));
	}
}
