package at.mschneider.weatherapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model to represent cities
 */
@Entity
public class City {

	public City() {

	}

	public City(Long owmId, String name) {
		this.owmId = owmId;
		this.name = name;
	}

	public City(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * city id from OpenWeatherMaps; currently unused
	 */
	private Long owmId;

	@Size(max=30)
	@NotNull
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOwmId() {
		return owmId;
	}

	public void setOwmId(Long owmId) {
		this.owmId = owmId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", owmId=" + owmId + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owmId == null) ? 0 : owmId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owmId == null) {
			if (other.owmId != null)
				return false;
		} else if (!owmId.equals(other.owmId))
			return false;
		return true;
	}
}
