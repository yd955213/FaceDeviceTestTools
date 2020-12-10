package myGson.tianmo350;
/**
 * 与AccessPermissions接口一起使用
 * @author yangdang
 *
 */
public class AccessPeriod {

	/**有效开始时间(yyyy-MM-dd HH:mm:ss) */
	public String effectiveTimeStart;
	/**有效结束时间(yyyy-MM-dd HH:mm:ss) */
	public String effectiveTimeEnd;
	/** 重复周期(1-7分别对应周一到周日，数值之间用逗号隔开)*/
	public Number repetitionCycle = 0;
	/**开始时间1 (HH:mm) */
	public String period1TimeStart;
	/**结束时间1 (HH:mm) */
	public String period1TimeEnd;
	/**开始时间2 (HH:mm) */
	public String period2TimeStart;
	/**结束时间2 (HH:mm) */
	public String Period2TimeEnd;
	/**开始时间3 (HH:mm) */
	public String Period3TimeStart;
	/**结束时间3 (HH:mm) */
	public String Period3TimeEnd;
	/**通行类型 （0： 允许通行 1：禁止通行） */
	public Number accessType;
	public String getEffectiveTimeStart() {
		return effectiveTimeStart;
	}
	public void setEffectiveTimeStart(String effectiveTimeStart) {
		this.effectiveTimeStart = effectiveTimeStart;
	}
	public String getEffectiveTimeEnd() {
		return effectiveTimeEnd;
	}
	public void setEffectiveTimeEnd(String effectiveTimeEnd) {
		this.effectiveTimeEnd = effectiveTimeEnd;
	}
	public Number getRepetitionCycle() {
		return repetitionCycle;
	}
	public void setRepetitionCycle(Number repetitionCycle) {
		this.repetitionCycle = repetitionCycle;
	}
	public String getPeriod1TimeStart() {
		return period1TimeStart;
	}
	public void setPeriod1TimeStart(String period1TimeStart) {
		this.period1TimeStart = period1TimeStart;
	}
	public String getPeriod1TimeEnd() {
		return period1TimeEnd;
	}
	public void setPeriod1TimeEnd(String period1TimeEnd) {
		this.period1TimeEnd = period1TimeEnd;
	}
	public String getPeriod2TimeStart() {
		return period2TimeStart;
	}
	public void setPeriod2TimeStart(String period2TimeStart) {
		this.period2TimeStart = period2TimeStart;
	}
	public String getPeriod2TimeEnd() {
		return Period2TimeEnd;
	}
	public void setPeriod2TimeEnd(String period2TimeEnd) {
		Period2TimeEnd = period2TimeEnd;
	}
	public String getPeriod3TimeStart() {
		return Period3TimeStart;
	}
	public void setPeriod3TimeStart(String period3TimeStart) {
		Period3TimeStart = period3TimeStart;
	}
	public String getPeriod3TimeEnd() {
		return Period3TimeEnd;
	}
	public void setPeriod3TimeEnd(String period3TimeEnd) {
		Period3TimeEnd = period3TimeEnd;
	}
	public Number getAccessType() {
		return accessType;
	}
	public void setAccessType(Number accessType) {
		this.accessType = accessType;
	}
}
