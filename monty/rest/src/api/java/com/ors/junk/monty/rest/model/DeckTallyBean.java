package com.ors.junk.monty.rest.model;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import com.ors.junk.monty.domain.model.Card;

public class DeckTallyBean {
	
	static class TallyComparator implements Comparator<Card>{

		@Override
		public int compare(Card arg0, Card arg1) {
			if(arg0.getSuite().ordinal()!=arg1.getSuite().ordinal()) {
				return arg1.getSuite().ordinal()-arg0.getSuite().ordinal();
			}else {
				return arg1.getFace().getScore()-arg0.getFace().getScore();
			}
		}
		
	}

	Map<Card,Integer> tallyMap=new TreeMap<>(new TallyComparator());

	public Map<Card, Integer> getTallyMap() {
		return tallyMap;
	}

	public void setTallyMap(Map<Card, Integer> tallyMap) {
		this.tallyMap = tallyMap;
	}
	
}
