package com.ors.junk.monty.domain.service.impl;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.google.common.primitives.Longs;
import com.ors.junk.monty.domain.service.PermuteService;

public class PermuteServiceImpl implements PermuteService {
	
	Random rand=new SecureRandom(Longs.toByteArray(new Date().getTime()));

	@Override
	public List<Integer> permute(final int topBound) {
		if(topBound<0) {
			throw new RuntimeException("Provide topBound larger than 0");
		}
		final int size=topBound+1;
		List<Integer> list=new ArrayList<>(size);
		for(int i=0;i<size;i++) {
			list.set(i,i);
		}
		
		for(int i=topBound;i>0;i--) {
			int randIndex=rand.nextInt(i);
			int temp=list.get(randIndex);
			list.set(randIndex, list.get(i));
			list.set(i, temp);
		}
		return list;
	}

}
