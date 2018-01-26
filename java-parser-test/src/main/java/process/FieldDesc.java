package process;

/**
 * Description:
 * Created by roc on 9/12/2017.
 */
public class FieldDesc {

	private Integer code;

	private String upperCaseName;

	private String lowerCaseName;

	private String type;

	private String desc;

	public Integer getCode() {
		return code;
	}

	public String getUpperCaseName() {
		return upperCaseName;
	}

	public String getLowerCaseName() {
		return lowerCaseName;
	}

	public String getDesc() {
		return desc;
	}

	public String getType() {
		return type;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static final class Builder {
		private Integer code;
		private String upperCaseName;
		private String lowerCaseName;
		private String desc;
		private String type;

		private Builder() {
		}

		public Builder setCode(Integer code) {
			this.code = code;
			return this;
		}

		public Builder setUpperCaseName(String upperCaseName) {
			this.upperCaseName = upperCaseName;
			return this;
		}

		public Builder setLowerCaseName(String lowerCaseName) {
			this.lowerCaseName = lowerCaseName;
			return this;
		}

		public Builder setDesc(String desc) {
			this.desc = desc;
			return this;
		}

		public Builder setType(String type) {
			this.type = type;
			return this;
		}

		public FieldDesc build() {
			FieldDesc fieldDesc = new FieldDesc();
			fieldDesc.desc = this.desc;
			fieldDesc.lowerCaseName = this.lowerCaseName;
			fieldDesc.code = this.code;
			fieldDesc.upperCaseName = this.upperCaseName;
			fieldDesc.type = this.type;
			return fieldDesc;
		}
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("FieldDesc{");
		sb.append("code='").append(code).append('\'');
		sb.append(", upperCaseName='").append(upperCaseName).append('\'');
		sb.append(", lowerCaseName='").append(lowerCaseName).append('\'');
		sb.append(", type='").append(type).append('\'');
		sb.append(", desc='").append(desc).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
