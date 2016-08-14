package com.chenxushao.java.basis.enums;


/** 
* @ClassName: StateEnum 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author chenxushao@gmail.com
* @date 2014-10-29 上午11:27:04 
*/
public enum StateEnum {

	cases("cases", "jf_periodical_cases"),
	compnent("component", "jf_periodical_component"),
	article("article", "jf_periodical_article"),
	user("user", "jf_periodical_user");

	private String identifier;

	private String tableName;

	private StateEnum(String identifier, String tableName) {
		this.identifier = identifier;
		this.tableName = tableName;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getTableName() {
		return tableName;
	}

	public static StateEnum valueOf(int ordinal) {
		StateEnum[] types = StateEnum.values();
		for (StateEnum type : types) {
			if (ordinal == type.ordinal()) {
				return type;
			}
		}
		return null;
	}

}
