package com.ors.junk.monty.domain.service;

import java.util.List;

public interface PermuteService {
	
	/**Return a permutation of numbers 0..topBound 
	 * 
	 * @param topBound
	 * @return
	 */
	public List<Integer> permute(int topBound);
}
