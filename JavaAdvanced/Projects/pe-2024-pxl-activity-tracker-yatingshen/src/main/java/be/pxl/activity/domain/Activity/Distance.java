package be.pxl.activity.domain.Activity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

// TODO: zoek op hoe je gebruik kan maken van de annotatie @Embeddable
@Embeddable
public class Distance {
	@Column(name = "distance_value")
	private double value;
	private Unit unit;

	public Distance() {
	}

	public Distance(double value, Unit unit) {
		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public Unit getUnit() {
		return unit;
	}

	/**
	 * Adds the given {@code Distance} to this {@code Distance} instance and returns the result.
	 * The distances are converted to kilometers (KM) for calculation and the result is returned in kilometers.
	 *
	 * @param distance the {@code Distance} to add to this {@code Distance}. The provided distance will be converted
	 *                 to kilometers (if necessary) before performing the addition.
	 * @return a new {@code Distance} instance representing the sum of this {@code Distance} and the provided {@code Distance},
	 *         with the result in kilometers (KM).
	 */
	public Distance add(Distance distance) {
		double valueInKm = this.toUnit(Unit.KM).getValue();  // Huidige afstand in km
		double otherValueInKm = distance.toUnit(Unit.KM).getValue();  // Andere afstand in km

		// Voeg de afstanden samen
		double resultInKm = valueInKm + otherValueInKm;

		// Retourneer de som in kilometers
		return new Distance(resultInKm, Unit.KM);
	}

	/**
	 * Converts this {@code Distance} to the specified unit (either kilometers or meters) and returns a new {@code Distance}
	 * instance with the converted value.
	 *
	 * @param unit the {@link Unit} to which this {@code Distance} should be converted. Supported units are:
	 *             <ul>
	 *               <li>{@code Unit.KM} - Kilometers</li>
	 *               <li>{@code Unit.M} - Meters</li>
	 *             </ul>
	 * @return a new {@code Distance} instance with the value converted to the specified unit. If the unit is the same
	 *         as the current unit of this {@code Distance}, a new instance with the same value is returned.
	 */
	public Distance toUnit(Unit unit) {
		double convertedValue = this.value;

		if (this.unit == Unit.KM && unit == Unit.M) {
			convertedValue = this.value * 1000;
		} else if (this.unit == Unit.M && unit == Unit.KM) {
			convertedValue = this.value / 1000;
		}

		return new Distance(convertedValue, unit);
	}

}
