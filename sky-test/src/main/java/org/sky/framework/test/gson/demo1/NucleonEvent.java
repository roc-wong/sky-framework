package org.sky.framework.test.gson.demo1;

import java.util.List;
import java.util.Map;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email swang6@ebay.com
 */
public class NucleonEvent {

	private String id;

	private String host;

	private String odbFunction;

	private String source;

	private String format;

	private String classification;

	private long detectionTime;

	private long reportedTime;

	private Severity severity;

	private NucleonEventType type;

	private NucleonMetadata metadata;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public NucleonEventType getType() {
		return type;
	}

	public void setType(NucleonEventType type) {
		this.type = type;
	}

	public enum NucleonEventType {

		nwmon_checkMem, nwmon_checkCPU_busyio;

	}

	public enum Severity {
		low;
	}

	public static class NucleonMetadata {
		private boolean passed;

		private String name;

		private List<Map<String, String>> msgs;

		private Map<String, Object> resultDataMap;

		public boolean isPassed() {
			return passed;
		}

		public void setPassed(boolean passed) {
			this.passed = passed;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Map<String, String>> getMsgs() {
			return msgs;
		}

		public void setMsgs(List<Map<String, String>> msgs) {
			this.msgs = msgs;
		}

		public Map<String, Object> getResultDataMap() {
			return resultDataMap;
		}

		public void setResultDataMap(Map<String, Object> resultDataMap) {
			this.resultDataMap = resultDataMap;
		}

	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getOdbFunction() {
		return odbFunction;
	}

	public void setOdbFunction(String odbFunction) {
		this.odbFunction = odbFunction;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public long getDetectionTime() {
		return detectionTime;
	}

	public void setDetectionTime(long detectionTime) {
		this.detectionTime = detectionTime;
	}

	public long getReportedTime() {
		return reportedTime;
	}

	public void setReportedTime(long reportedTime) {
		this.reportedTime = reportedTime;
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public NucleonMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(NucleonMetadata metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return "NucleonEvent [id=" + id + ", host=" + host + ", odbFunction=" + odbFunction + ", source=" + source
				+ ", format=" + format + ", classification=" + classification + ", detectionTime=" + detectionTime
				+ ", reportedTime=" + reportedTime + ", severity=" + severity + ", type=" + type + ", metadata="
				+ metadata.getName() + ":" + metadata.isPassed() + metadata.getMsgs() + "]";
	}

}
