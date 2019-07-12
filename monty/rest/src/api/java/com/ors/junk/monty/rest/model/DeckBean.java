package com.ors.junk.monty.rest.model;

import com.ors.junk.monty.domain.model.Deck;

public class DeckBean implements Deck {
	public String bId;

	@Override
	public String getBId() {
		return bId;
	}

	public void setBId(String bId) {
		this.bId = bId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bId == null) ? 0 : bId.hashCode());
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
		DeckBean other = (DeckBean) obj;
		if (bId == null) {
			if (other.bId != null)
				return false;
		} else if (!bId.equals(other.bId))
			return false;
		return true;
	}

}
