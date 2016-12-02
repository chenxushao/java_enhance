package com.chenxushao.xstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.converters.reflection.SunUnsafeReflectionProvider;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XStreamUtil {
	// The XStream instance is thread-safe
	private static XStream xstream = new XStream();

	static {
		xstream.autodetectAnnotations(true);
	}

	public static void writeXML(Object object, File file) throws IOException {
		FileOutputStream out = new FileOutputStream(file);
		try {
			xstream.toXML(object, out);
			out.flush();
		} finally {
			out.close();
		}
	}

	public static String writeXML(Object object) {
		return xstream.toXML(object);
	}

	public static <T> T readXML(File file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		try {
			return (T) xstream.fromXML(in);
		} finally {
			in.close();
		}
	}

	public static String toXML(Object obj) {
		return xstream.toXML(obj);
	}

	public static String toXmlOptimized(Object toSerialize) {
		StringWriter sw = new StringWriter();
		xstream.marshal(toSerialize, new CompactWriter(sw));
		return sw.toString();
	}

	public static String toXML(Object obj,
			List<SingleValueConverter> converterList) {
		XStream xstream = new XStream();
		for (SingleValueConverter converter : converterList) {
			xstream.registerConverter(converter);
		}
		return xstream.toXML(obj);
	}

	public static String toXMLDateSerializer(Object obj, String dateFormat) {
		XStream xstream = new XStream();
		xstream.registerConverter(new DateConverter(dateFormat, new String[] {}));
		return xstream.toXML(obj);
	}

	public static <T> T fromXMLDateDerializer(String xml, String dateFormat) {
		// xstream.registerConverter(new DateConverter(dateFormat, new
		// String[]{}));
		// XStream xstream = new XStream();
		xstream.registerConverter(new DateConverter(dateFormat, new String[] {}));
		return (T) xstream.fromXML(xml);
	}

	public static <T> T fromXML(String xml) {
		return (T) xstream.fromXML(xml);
	}

	public static <T> T fromXML(InputStream in) {
		return (T) xstream.fromXML(in);
	}

	public static String toString(Object o) {
		StringWriter writer = new StringWriter();
		XStream xs = new XStream(new SunUnsafeReflectionProvider());
		xs.processAnnotations(o.getClass());
		xs.toXML(o, writer);
		return writer.toString();
	}

	/**
	 * @param map
	 * @return eg :
	 *         <map><entry><string>b</string><string>2</string></entry></map>
	 */
	public static String serializeMap(Map<String, String> map) {
		if (map == null) {
			map = new HashMap<String, String>();
		}
		XStream xstream = new XStream(new DomDriver());
		return xstream.toXML(map);
	}

	/**
	 * @param map
	 *            eg :
	 *            <map><entry><string>b</string><string>2</string></entry></map>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> deserializeMap(String map) {
		if (map == null || map.equals("")) {
			return new HashMap<String, String>();
		} else {
			XStream xstream = new XStream(new DomDriver());
			return (Map<String, String>) xstream.fromXML(map);
		}
	}

	/**
	 * @return eg:<list><string>a</string><string>b</string></list>
	 */
	public static String serializeList(List<?> list) {
		if (list == null) {
			list = new ArrayList<String>();
		}
		XStream xstream = new XStream(new DomDriver());
		return xstream.toXML(list);
	}

	/**
	 * @param list
	 *            eg:<list><string>a</string><string>b</string></list>
	 */
	@SuppressWarnings("unchecked")
	public static List<String> deserializeList(String list) {
		if (list == null || list.equals("")) {
			return new ArrayList<String>();
		}
		XStream xstream = new XStream(new DomDriver());
		return (List<String>) xstream.fromXML(list);
	}

	/**
	 * @param set
	 *            of Strings
	 * @return serialized Set eg:<set><string>a</string><string>b</string></set>
	 */
	public static String serializeSet(Set<?> set) {
		if (set == null) {
			set = new HashSet<String>();
		}
		XStream xstream = new XStream(new DomDriver());
		return xstream.toXML(set);
	}

	/**
	 * @param set
	 *            serialized as a String
	 *            eg:<set><string>a</string><string>b</string></set>
	 * @return deserialized Set
	 */
	public static Set<String> deserializeSet(String set) {
		if (set == null || set.equals("")) {
			return new HashSet<String>();
		}
		XStream xstream = new XStream(new DomDriver());
		return (Set<String>) xstream.fromXML(set);
	}

	public static XStream getDefaultXstream() {
		/*
		 * final XStream xstream = new XStream();
		 * xstream.alias("ActivityInstanceUUID", ActivityInstanceUUID.class);
		 * xstream.alias("LightProcessInstance",
		 * LightProcessInstanceImpl.class);
		 * xstream.alias("AttachmentDefinition",
		 * AttachmentDefinitionImpl.class); xstream.alias("ActivityInstance",
		 * ActivityInstanceImpl.class); xstream.alias("Date", Date.class);
		 * xstream.alias("ProcessInstanceUUID", ProcessInstanceUUID.class);
		 * xstream.alias("BusinessArchive", BusinessArchiveImpl.class);
		 * xstream.alias("Label", Label.class);
		 * xstream.alias("ActivityDefinition", ActivityDefinitionImpl.class);
		 * xstream.alias("Group", GroupImpl.class);
		 * xstream.alias("InitialAttachment", InitialAttachmentImpl.class);
		 * xstream.alias("Rule", RuleImpl.class);
		 * xstream.alias("ParticipantDefinitionUUID",
		 * ParticipantDefinitionUUID.class); xstream.alias("SearchQueryBuilder",
		 * SearchQueryBuilder.class); xstream.alias("LightActivityInstance",
		 * LightActivityInstanceImpl.class); xstream.alias("ProcessState",
		 * ProcessState.class); xstream.alias("ProfileMetadata",
		 * ProfileMetadataImpl.class); xstream.alias("ProcessDefinition",
		 * ProcessDefinitionImpl.class); xstream.alias("ProcessDefinitionUUID",
		 * ProcessDefinitionUUID.class); xstream.alias("RuleType",
		 * RuleType.class); xstream.alias("Membership", MembershipImpl.class);
		 * xstream.alias("ActivityState", ActivityState.class);
		 * xstream.alias("AttachmentInstance", AttachmentInstanceImpl.class);
		 * xstream.alias("User", UserImpl.class);
		 * xstream.alias("PrivilegePolicy", PrivilegePolicy.class);
		 * xstream.alias("DataFieldDefinition", DataFieldDefinitionImpl.class);
		 * xstream.alias("Object", Object.class);
		 * xstream.alias("ParticipantDefinition",
		 * ParticipantDefinitionImpl.class);
		 * xstream.alias("ActivityDefinitionUUID",
		 * ActivityDefinitionUUID.class); xstream.alias("Command",
		 * Command.class); xstream.alias("ProcessInstance",
		 * ProcessInstanceImpl.class); xstream.alias("Category",
		 * CategoryImpl.class); xstream.alias("LightProcessDefinition",
		 * LightProcessDefinitionImpl.class); xstream.alias("Role",
		 * RoleImpl.class); xstream.alias("TransitionDefinition",
		 * TransitionDefinitionImpl.class); xstream.alias("StateUpdate",
		 * StateUpdateImpl.class); xstream.alias("InstanceStateUpdate",
		 * InstanceStateUpdateImpl.class); xstream.alias("AssignUpdate",
		 * AssignUpdateImpl.class); xstream.alias("ProcessRule",
		 * ProcessRuleImpl.class); xstream.alias("ActivityRule",
		 * ActivityRuleImpl.class); xstream.alias("CustomRule",
		 * CustomRuleImpl.class); xstream.alias("CustomRule",
		 * CustomRuleImpl.class); xstream.alias("connectorExecutionDescriptor",
		 * ConnectorExecutionDescriptor.class); return xstream;
		 */
		return null;
	}
}