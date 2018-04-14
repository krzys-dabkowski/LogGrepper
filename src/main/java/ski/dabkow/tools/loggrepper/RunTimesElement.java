package ski.dabkow.tools.loggrepper;

public class RunTimesElement {

	String uuid;
	String getModelForURI;
	String getGenicData;
	String getProfileForURI;

	public RunTimesElement(String uuid) {
		this.uuid = uuid;
	}

	public RunTimesElement(String uuid, String getModelForURI,
			String getGenicData, String getProfileForURI) {
		this.uuid = uuid;
		this.getModelForURI = getModelForURI;
		this.getGenicData = getGenicData;
		this.getProfileForURI = getProfileForURI;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.append(uuid).append(",").append(getModelForURI).append(",")
				.append(getGenicData).append(",").append(getProfileForURI)
				.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof RunTimesElement)) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		RunTimesElement rteObj = (RunTimesElement) obj;
		if (rteObj.uuid == null) {
			return false;
		}
		return this.uuid.equals(rteObj.uuid);
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = uuid.hashCode() / 12;
		return result;
	}

}
