package cn.ylapl.util.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 日志封装
 * Static methods for validation and emiting log messages
 */
public class LogUtil implements Serializable{
	private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

	public static <T>void debug(Class<T> obj,String str) {
		logger.debug(">>>>" + obj.getName() + ">>>"
				+ Thread.currentThread().getStackTrace()[1].getMethodName()
				+ ">>>>" + str);
	}

	public static <T>void info(Class<T> obj,String str) {
		logger.info(">>>>" + obj.getName() + ">>>"
				+ Thread.currentThread().getStackTrace()[1].getMethodName()
				+ ">>>>" + str);
	}

	public static <T>void error(Class<T> obj,String str) {
		logger.error(">>>>" + obj.getName() + ">>>"
				+ Thread.currentThread().getStackTrace()[1].getMethodName()
				+ ">>>>" + str);
	}

	public static <T>void warn(Class<T> obj,String str) {
		logger.warn(">>>>" + obj.getName() + ">>>"
				+ Thread.currentThread().getStackTrace()[1].getMethodName()
				+ ">>>>" + str);
	}
}
