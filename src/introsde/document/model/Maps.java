package introsde.document.model;

//import introsde.document.dao.*;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

public class Maps {
	
	private String duration;
	private String distance;

	public Maps(String duration, String distance) {
		this.duration = duration;
		this.distance = distance;
	}

	public Maps() {

	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public String getDistance() {
		return this.distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

}