package com.team.icr.task.entity;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Entity
public class Task implements Comparable<Task> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private int version;
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime startTime;
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime endTime;
	private int priority;

	public Task() {
	}

	public Task(final String taskName, final int version) {
		this.name = taskName;
		this.version = version;
	}

	public long getId() {
		return this.id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	public LocalDateTime getStartTime() {
		return this.startTime;
	}

	public void setStartTime(final LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return this.endTime;
	}

	public void setEndTime(final LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(final int priority) {
		this.priority = priority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.endTime == null ? 0 : this.endTime.hashCode());
		result = prime * result + (int) (this.id ^ this.id >>> 32);
		result = prime * result + (this.name == null ? 0 : this.name.hashCode());
		result = prime * result + this.priority;
		result = prime * result + (this.startTime == null ? 0 : this.startTime.hashCode());
		result = prime * result + this.version;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Task other = (Task) obj;
		if (this.endTime == null) {
			if (other.endTime != null) {
				return false;
			}
		} else if (!this.endTime.equals(other.endTime)) {
			return false;
		}
		if (this.id != other.id) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.priority != other.priority) {
			return false;
		}
		if (this.startTime == null) {
			if (other.startTime != null) {
				return false;
			}
		} else if (!this.startTime.equals(other.startTime)) {
			return false;
		}
		if (this.version != other.version) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int compareTo(final Task task) {

		if (this.equals(task)) {
			return 0;
		}

		if (this.priority == task.priority) {
			final Duration curDuration = calculateDuration();
			final Duration taskDuration = task.calculateDuration();
			return curDuration.compareTo(taskDuration);
		} else {
			return this.priority > task.priority ? 1 : -1;
		}
	}

	private Duration calculateDuration() {
		return Duration.between(this.startTime, this.endTime);
	}

}
